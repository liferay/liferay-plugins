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

import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NodeValidatorRegistry {

	public NodeValidator<Node> getValidator(NodeType nodeType) {
		NodeValidator<Node> nodeValidator = _nodeValidators.get(nodeType);

		if (nodeValidator == null) {
			throw new IllegalArgumentException(
				"No node validator found for " + nodeType);
		}

		return nodeValidator;
	}

	public void setNodeValidators(
		Map<NodeType, NodeValidator<Node>> nodeValidators) {

		_nodeValidators = nodeValidators;
	}

	private Map<NodeType, NodeValidator<Node>> _nodeValidators =
		new HashMap<NodeType, NodeValidator<Node>>();

}