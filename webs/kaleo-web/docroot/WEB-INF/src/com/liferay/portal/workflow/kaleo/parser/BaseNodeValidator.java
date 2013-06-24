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
import com.liferay.portal.workflow.kaleo.definition.Transition;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseNodeValidator<T extends Node>
	implements NodeValidator<T> {

	public void validate(Definition definition, T node)
		throws WorkflowException {

		doValidate(definition, node);

		validateTransitions(node.getOutgoingTransitions());
	}

	protected abstract void doValidate(Definition definition, T node)
		throws WorkflowException;

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