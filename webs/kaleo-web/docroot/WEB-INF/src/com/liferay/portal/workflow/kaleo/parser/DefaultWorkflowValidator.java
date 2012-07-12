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
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Transition;

import java.util.Collection;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class DefaultWorkflowValidator implements WorkflowValidator {

	public void validate(Definition definition) throws WorkflowException {
		Collection<Node> nodes = definition.getNodes();

		for (Node node : nodes) {
			validateNode(definition, node);
		}
	}

	protected void validateNode(Definition definition, Node node)
		throws WorkflowException {

		if (node instanceof State) {
			validateState(definition, (State)node);
		}

		validateTransitions(node.getTransitions());
	}

	protected void validateState(Definition definition, State state)
		throws WorkflowException {

		if (state.isInitial() &&
			!Validator.equals(definition.getInitialState(), state)) {

			throw new WorkflowException(
				"Multiple initial states defined " + state.getName());
		}
		else if (state.isTerminal() &&
				 !Validator.equals(definition.getTerminalState(), state)) {

			throw new WorkflowException(
				"Multiple terminal states defined " + state.getName());
		}
	}

	protected void validateTransitions(Map<String, Transition> transitions)
		throws WorkflowException {

		for (Transition transition : transitions.values()) {
			if (transition.getTargetNode() == null) {
				throw new WorkflowException(
					"Unable to find target node " + transition.getName());
			}
		}
	}

}