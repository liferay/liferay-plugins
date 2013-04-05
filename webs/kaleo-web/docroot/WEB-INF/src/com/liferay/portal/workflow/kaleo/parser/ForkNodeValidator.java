/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Fork;
import com.liferay.portal.workflow.kaleo.definition.Join;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.definition.Transition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class ForkNodeValidator extends BaseNodeValidator<Fork> {

	@Override
	protected void doValidate(Definition definition, Fork fork)
		throws WorkflowException {

		if (fork.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for fork " + fork.getName());
		}

		if (fork.getOutgoingTransitionsCount() < 2) {
			throw new WorkflowException(
				"Less than 2 outgoing transitions found for fork " +
					fork.getName());
		}

		traverse(fork);
	}

	protected List<Node> getUnvisitedTargetNodes(
		List<Node> targetNodes, Collection<Transition> outgoingTransitions) {

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

		for (Transition transition : fork.getOutgoingTransitionsList()) {
			targetNodes.add(transition.getTargetNode());
		}

		for (int i = 1; i < targetNodes.size(); i++) {
			Node targetNode = targetNodes.get(i);

			if (targetNode.getNodeType().equals(NodeType.FORK)) {
				Join localJoin = traverse((Fork)targetNode);

				List<Node> unvisitedTargetNodes = getUnvisitedTargetNodes(
					targetNodes, localJoin.getOutgoingTransitionsList());

				if (unvisitedTargetNodes.size() > 1) {
					joinIncomingTransitions += unvisitedTargetNodes.size() - 1;
				}

				targetNodes.addAll(unvisitedTargetNodes);
			}
			else if (targetNode.getNodeType().equals(NodeType.JOIN)) {
				if (Validator.isNull(join)) {
					join = (Join)targetNode;
				}
				else if (!Validator.equals(join, targetNode)) {
					throw new WorkflowException(
						"Fork " + fork.getName() + " and join " +
							targetNode.getName() + " are not paired");
				}
			}
			else {
				List<Node> unvisitedTargetNodes = getUnvisitedTargetNodes(
					targetNodes, targetNode.getOutgoingTransitionsList());

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

}