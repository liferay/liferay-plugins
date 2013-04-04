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

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.State;

import java.util.Collection;
import java.util.List;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class DefaultWorkflowValidator implements WorkflowValidator {

	public void setNodeValidatorRegistry(
		NodeValidatorRegistry nodeValidatorRegistry) {

		_nodeValidatorRegistry = nodeValidatorRegistry;
	}

	public void validate(Definition definition) throws WorkflowException {
		State initialState = definition.getInitialState();

		if (initialState == null) {
			throw new WorkflowException("No initial state defined");
		}

		List<State> terminalStates = definition.getTerminalStates();

		if (terminalStates.isEmpty()) {
			throw new WorkflowException("No terminal states defined");
		}

		if (definition.getForksCount() != definition.getJoinsCount()) {
			throw new WorkflowException(
				"There are unbalanced fork and join nodes");
		}

		Collection<Node> nodes = definition.getNodes();

		for (Node node : nodes) {
			NodeValidator<Node> nodeValidator =
				_nodeValidatorRegistry.getValidator(node.getNodeType());

			nodeValidator.validate(definition, node);
		}
	}

	private NodeValidatorRegistry _nodeValidatorRegistry;

}