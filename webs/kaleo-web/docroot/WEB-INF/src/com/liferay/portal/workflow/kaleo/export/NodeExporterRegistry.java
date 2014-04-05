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

package com.liferay.portal.workflow.kaleo.export;

import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.util.NodeTypeDependentObjectRegistry;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NodeExporterRegistry {

	public static NodeExporter getNodeExporter(NodeType nodeType) {
		return _nodeExporters.getNodeTypeDependentObjects(nodeType);
	}

	public void setNodeExporter(Map<String, NodeExporter> nodeExporters) {
		_nodeExporters.setNodeTypeDependentObjects(nodeExporters);
	}

	private static NodeTypeDependentObjectRegistry<NodeExporter>
		_nodeExporters = new NodeTypeDependentObjectRegistry<NodeExporter>();

}