/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Transition;

import java.util.Collection;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class DefaultWorkflowValidator implements WorkflowValidator {

	public void validate(Definition definition) throws WorkflowException {
		Collection<Node> nodes = definition.getNodes();

		for (Node node : nodes) {
			checkInitialState(definition, node);

			Collection<Transition> transitions = node.getTransitionsEntries();

			for (Transition transition : transitions) {
				checkTransition(definition, transition);
			}
		}
	}

	protected void checkInitialState(Definition definition, Node node)
		throws WorkflowException {

		if (node instanceof State) {
			State state = (State)node;

			if (state.isInitial()) {
				if (definition.getInitialState() != null) {
					throw new WorkflowException(
						"Duplicate initial state " + state.getName());
				}

				definition.setInitialState(state);
			}
		}
	}
	protected void checkTransition(
			Definition definition, Transition transition)
		throws WorkflowException {

		String targetName = transition.getTargetName();

		Node targetNode = definition.getNode(targetName);

		if (targetNode == null) {
			throw new WorkflowException(
				"Unable to find target node " + targetName);
		}

		transition.setTargetNode(targetNode);
	}

}