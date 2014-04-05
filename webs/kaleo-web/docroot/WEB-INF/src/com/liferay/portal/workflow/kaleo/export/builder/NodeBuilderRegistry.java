/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.export.builder;

import com.liferay.portal.workflow.kaleo.util.NodeTypeDependentObjectRegistry;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NodeBuilderRegistry {

	public static NodeBuilder getNodeBuilder(String nodeTypeString) {
		return _nodeBuilders.getNodeTypeDependentObjects(nodeTypeString);
	}

	public void setNodeBuilders(Map<String, NodeBuilder> nodeBuilders) {
		_nodeBuilders.setNodeTypeDependentObjects(nodeBuilders);
	}

	protected static NodeTypeDependentObjectRegistry<NodeBuilder>
		_nodeBuilders = new NodeTypeDependentObjectRegistry<NodeBuilder>();

}