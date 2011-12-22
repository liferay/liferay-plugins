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

package com.liferay.portal.workflow.kaleo.deployment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;
import com.liferay.portal.workflow.kaleo.WorkflowDefinitionAdapter;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalServiceUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DefaultWorkflowDeployer implements WorkflowDeployer {

	public WorkflowDefinition deploy(
			String title, Definition definition, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDefinition kaleoDefinition = null;

		try {
			kaleoDefinition =
				KaleoDefinitionLocalServiceUtil.incrementKaleoDefinition(
					definition, title, serviceContext);
		}
		catch (NoSuchDefinitionException nsde) {
			kaleoDefinition =
				KaleoDefinitionLocalServiceUtil.addKaleoDefinition(
					definition.getName(), title, definition.getDescription(),
					definition.getContent(), definition.getVersion(),
					serviceContext);
		}

		long kaleoDefinitionId = kaleoDefinition.getKaleoDefinitionId();

		Collection<Node> nodes = definition.getNodes();

		Map<String, KaleoNode> kaleoNodesMap = new HashMap<String, KaleoNode>();

		for (Node node : nodes) {
			KaleoNode kaleoNode = KaleoNodeLocalServiceUtil.addKaleoNode(
				kaleoDefinitionId, node, serviceContext);

			kaleoNodesMap.put(node.getName(), kaleoNode);

			NodeType nodeType = node.getNodeType();

			if (nodeType.equals(NodeType.TASK)) {
				Task task = (Task)node;

				KaleoTaskLocalServiceUtil.addKaleoTask(
					kaleoDefinitionId, kaleoNode.getKaleoNodeId(), task,
					serviceContext);
			}
			else if (nodeType.equals(NodeType.CONDITION)) {
				Condition condition = (Condition)node;

				KaleoConditionLocalServiceUtil.addKaleoCondition(
					kaleoDefinitionId, kaleoNode.getKaleoNodeId(), condition,
					serviceContext);
			}
		}

		for (Node node : nodes) {
			Collection<Transition> transitions = node.getTransitionsEntries();

			KaleoNode kaleoNode = kaleoNodesMap.get(node.getName());

			for (Transition transition : transitions) {
				KaleoNode sourceKaleoNode = kaleoNodesMap.get(
					transition.getSourceNode().getName());

				if (sourceKaleoNode == null) {
					throw new WorkflowException(
						"Unable to find source node " +
							transition.getSourceNode());
				}

				KaleoNode targetKaleoNode = kaleoNodesMap.get(
					transition.getTargetNode().getName());

				if (targetKaleoNode == null) {
					throw new WorkflowException(
						"Unable to find target node " +
							transition.getTargetNode());
				}

				KaleoTransitionLocalServiceUtil.addKaleoTransition(
					kaleoNode.getKaleoDefinitionId(),
					kaleoNode.getKaleoNodeId(), transition, sourceKaleoNode,
					targetKaleoNode, serviceContext);
			}
		}

		State initialState = definition.getInitialState();

		if (initialState == null) {
			throw new WorkflowException("No initial state found in definition");
		}

		String startKaleoNodeName = initialState.getName();

		KaleoNode kaleoNode = kaleoNodesMap.get(startKaleoNodeName);

		KaleoDefinitionLocalServiceUtil.activateKaleoDefinition(
			kaleoDefinitionId, kaleoNode.getKaleoNodeId(), serviceContext);

		return new WorkflowDefinitionAdapter(kaleoDefinition);
	}

}