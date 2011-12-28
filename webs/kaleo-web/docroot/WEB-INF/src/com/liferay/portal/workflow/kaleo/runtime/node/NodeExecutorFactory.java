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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.NodeType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NodeExecutorFactory {

	public static NodeExecutor getNodeExecutor(NodeType nodeType)
		throws WorkflowException {

		NodeExecutor nodeExecutor = _nodeExecutors.get(nodeType);

		if (nodeExecutor == null) {
			throw new WorkflowException("Invalid node type " + nodeType);
		}

		return nodeExecutor;
	}

	public void setNodeExectors(Map<String, NodeExecutor> nodeExecutors) {
		_nodeExecutors = new HashMap<NodeType, NodeExecutor>();

		for (Map.Entry<String, NodeExecutor> entry : nodeExecutors.entrySet()) {
			NodeType nodeType = NodeType.valueOf(entry.getKey());

			_nodeExecutors.put(nodeType, entry.getValue());
		}
	}

	public static Map<NodeType, NodeExecutor> _nodeExecutors;

}