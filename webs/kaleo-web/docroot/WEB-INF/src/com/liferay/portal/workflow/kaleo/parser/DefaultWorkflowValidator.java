/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Fork;
import com.liferay.portal.workflow.kaleo.definition.Join;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Transition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class DefaultWorkflowValidator implements WorkflowValidator {

	public void validate(Definition definition) throws WorkflowException {
		State initialState = definition.getInitialState();

		if (initialState == null) {
			throw new WorkflowException("No initial state defined");
		}

		List<State> terminalStates = definition.getTerminalStates();

		if (terminalStates.isEmpty()) {
			throw new WorkflowException("No terminal states defined");
		}

		Collection<Node> nodes = definition.getNodes();

		for (Node node : nodes) {
			validateNode(definition, node);
		}

		if (definition.getForksCount() != definition.getJoinsCount()) {
			throw new WorkflowException(
				"There are unbalanced fork and join nodes");
		}

		List<Fork> forks = definition.getForks();

		for (Fork fork : forks) {
			traverse(fork);
		}
	}

	protected List<Node> getUnvisitedTargetNodes(
			List<Node> targetNodes, Collection<Transition> outgoingTransitions)
		throws WorkflowException {

		List<Node> unvisitedTargetNodes = new ArrayList<Node>();

		for (Transition outgoingTransition : outgoingTransitions) {
			Node targetNode = outgoingTransition.getTargetNode();

			if (!targetNodes.contains(targetNode)) {
				unvisitedTargetNodes.add(targetNode);
			}
		}

		return unvisitedTargetNodes;
	}

	protected Join traverse(Fork fork) throws WorkflowException {
		Join join = null;

		int joinIncomingTransitions = fork.getOutgoingTransitionsCount();

		List<Node> targetNodes = new ArrayList<Node>();

		targetNodes.add(fork);

		for (Transition transition : fork.getOutgoingTransitionsEntries()) {
			targetNodes.add(transition.getTargetNode());
		}

		for (int i = 1; i < targetNodes.size(); i++) {
			Node targetNode = targetNodes.get(i);

			if (targetNode instanceof Fork) {
				Join localJoin = traverse((Fork)targetNode);

				List<Node> unvisitedTargetNodes = getUnvisitedTargetNodes(
					targetNodes, localJoin.getOutgoingTransitionsEntries());

				if (unvisitedTargetNodes.size() > 1) {
					joinIncomingTransitions += unvisitedTargetNodes.size() - 1;
				}

				targetNodes.addAll(unvisitedTargetNodes);
			}
			else if (targetNode instanceof Join) {
				if (Validator.isNull(join)) {
					join = (Join)targetNode;
				}
				else if (!Validator.equals(join, targetNode)) {
					throw new WorkflowException(
						"Fork and Join are not in pair {" +
							fork.getName() + ", " + targetNode.getName() + "}");
				}
			}
			else {
				List<Node> unvisitedTargetNodes = getUnvisitedTargetNodes(
					targetNodes, targetNode.getOutgoingTransitionsEntries());

				if (unvisitedTargetNodes.size() > 1) {
					joinIncomingTransitions += unvisitedTargetNodes.size() - 1;
				}

				targetNodes.addAll(unvisitedTargetNodes);
			}
		}

		if (join == null) {
			throw new WorkflowException(
				"No matching join found for fork " + fork.getName());
		}
		else if (join.getIncomingTransitionsCount() !=
					joinIncomingTransitions) {

			throw new WorkflowException(
				"Incorrect number of incoming transitions for join " +
					join.getName());
		}

		return join;
	}

	protected void validateCondition(Condition condition)
		throws WorkflowException {

		if (condition.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for condition " +
					condition.getName());
		}

		if (condition.getOutgoingTransitionsCount() < 2) {
			throw new WorkflowException(
				"Less than 2 outgoing transitions found for condition " +
					condition.getName());
		}
	}

	protected void validateFork(Fork fork) throws WorkflowException {
		if (fork.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for fork " + fork.getName());
		}

		if (fork.getOutgoingTransitionsCount() < 2) {
			throw new WorkflowException(
				"Less than 2 outgoing transitions found for fork " +
					fork.getName());
		}
	}

	protected void validateInitialState(Definition definition, State state)
		throws WorkflowException {

		if (!Validator.equals(definition.getInitialState(), state)) {
			throw new WorkflowException(
				"Multiple initial states defined {" + state.getName() +
					", " + definition.getInitialState().getName() + "}");
		}

		if (state.getIncomingTransitionsCount() > 0) {
			throw new WorkflowException(
				"Incoming transitions were found for initial state " +
					state.getName());
		}

		if (state.getOutgoingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No outgoing transition found for initial state " +
					state.getName());
		}
	}

	protected void validateJoin(Join join) throws WorkflowException {
		if (join.getOutgoingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No outgoing transition found for join " + join.getName());
		}
	}

	protected void validateNode(Definition definition, Node node)
		throws WorkflowException {

		if (node instanceof Condition) {
			validateCondition((Condition)node);
		}
		else if (node instanceof Fork) {
			validateFork((Fork)node);
		}
		else if (node instanceof Join) {
			validateJoin((Join)node);
		}
		else if (node instanceof State) {
			validateState(definition, (State)node);
		}
		else if (node instanceof Task) {
			validateTask((Task)node);
		}

		validateTransitions(node.getOutgoingTransitions());
	}

	protected void validateState(Definition definition, State state)
		throws WorkflowException {

		if (state.isInitial()) {
			validateInitialState(definition, state);
		}
		else if (state.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for state " + state.getName());
		}
	}

	protected void validateTask(Task task) throws WorkflowException {
		if (task.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for task " + task.getName());
		}

		if (task.getOutgoingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No outgoing transition found for task " + task.getName());
		}
	}

	protected void validateTransition(Transition transition)
		throws WorkflowException {

		if (transition.getTargetNode() == null) {
			throw new WorkflowException(
				"Unable to find target node for transition " +
					transition.getName());
		}
	}

	protected void validateTransitions(Map<String, Transition> transitions)
		throws WorkflowException {

		for (Transition transition : transitions.values()) {
			validateTransition(transition);
		}
	}

}