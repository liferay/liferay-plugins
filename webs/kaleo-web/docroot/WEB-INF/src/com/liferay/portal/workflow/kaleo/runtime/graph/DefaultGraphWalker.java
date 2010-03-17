/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.graph;

import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.ActionType;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <a href="DefaultGraphWalker.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class DefaultGraphWalker implements GraphWalker {

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {PortalException.class, SystemException.class})
	public ExecutionContext completeTask(
			String comment, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssigment =
			executionContext.getKaleoTaskInstanceAssigment();

		kaleoTaskInstanceAssigment =
			KaleoTaskInstanceAssignmentLocalServiceUtil.
				completeKaleoTaskInstanceAssignment(
					kaleoTaskInstanceAssigment.
						getKaleoTaskInstanceAssignmentId());

		kaleoTaskInstanceToken =
			KaleoTaskInstanceTokenLocalServiceUtil.
				completeKaleoTaskInstanceToken(
					kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
					executionContext.getServiceContext());

		KaleoInstanceToken kaleoInstanceToken =
			KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceToken(
				kaleoTaskInstanceToken.getKaleoInstanceTokenId());

		KaleoInstanceToken parentKaleoInstanceToken =
			KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceToken(
				kaleoInstanceToken.getParentKaleoInstanceTokenId());

		KaleoLogLocalServiceUtil.addTaskCompletionKaleoLog(
			kaleoTaskInstanceToken, kaleoTaskInstanceAssigment, comment,
			executionContext.getContext(),
			executionContext.getServiceContext());

		return new ExecutionContext(
			parentKaleoInstanceToken, executionContext.getContext(),
			executionContext.getServiceContext());
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {PortalException.class, SystemException.class})
	public ExecutionContext initialize(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Map<String, Serializable> context,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDefinition kaleoDefinition =
			KaleoDefinitionLocalServiceUtil.getKaleoDefinition(
				workflowDefinitionName, workflowDefinitionVersion,
				serviceContext);

		if (!kaleoDefinition.isActive()) {
			throw new WorkflowException(
				"Inactive workflow definition with name " +
					workflowDefinitionName + " and version " +
						workflowDefinitionVersion);
		}

		KaleoInstance kaleoInstance =
			KaleoInstanceLocalServiceUtil.addKaleoInstance(
				kaleoDefinition.getKaleoDefinitionId(),
				kaleoDefinition.getName(), kaleoDefinition.getVersion(),
				context, serviceContext);

		KaleoInstanceToken rootKaleoInstanceToken =
			kaleoInstance.getRootKaleoInstanceToken(context, serviceContext);

		KaleoLogLocalServiceUtil.addWorkflowInstanceStartKaleoLog(
			rootKaleoInstanceToken, serviceContext);

		return new ExecutionContext(
			rootKaleoInstanceToken, context, serviceContext);
	}

	public void signalEntry(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();
		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();
		KaleoDefinition kaleoDefinition = kaleoInstance.getKaleoDefinition();

		KaleoNode kaleoStartNode = kaleoDefinition.getKaleoStartNode();

		executionContext.setTransitionName(transitionName);

		List<PathElement> remainingPathElement = new ArrayList<PathElement>();

		PathElement startPathElement = new PathElement(
			null, kaleoStartNode, executionContext);

		remainingPathElement.add(startPathElement);

		follow(remainingPathElement);

		checkKaleoInstanceComplete(executionContext);
	}

	public void signalExit(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		List<PathElement> remainingPathElement = new ArrayList<PathElement>();

		PathElement pathElement = new PathElement(
			currentKaleoNode, null, executionContext);

		remainingPathElement.add(pathElement);

		follow(remainingPathElement);

		checkKaleoInstanceComplete(executionContext);
	}

	protected void checkKaleoInstanceComplete(ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		if (!kaleoInstanceToken.isCompleted()) {
			return;
		}

		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		if (!kaleoInstance.isCompleted()) {
			return;
		}

		KaleoLogLocalServiceUtil.addWorkflowInstanceEndKaleoLog(
			kaleoInstanceToken, executionContext.getServiceContext());
	}

	protected void executeKaleoActions(
			long kaleoNodeId, ActionType actionType,
			ExecutionContext executionContext, ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<KaleoAction> kaleoActions =
			KaleoActionLocalServiceUtil.getKaleoActions(
				kaleoNodeId, actionType.getType());

		for (KaleoAction kaleoAction : kaleoActions) {
			long startTime = System.currentTimeMillis();
			String comment = "Action completed successfully.";

			try {
				ActionExecutor actionExecutor =
					ActionExecutorFactory.getActionExecutor(
						kaleoAction.getLanguage());

				actionExecutor.execute(kaleoAction, executionContext);
			}
			catch (Exception e) {
				comment = e.getMessage();
			}
			finally {
				KaleoLogLocalServiceUtil.addActionExecutionKaleoLog(
					executionContext.getKaleoInstanceToken(), kaleoAction,
					startTime, System.currentTimeMillis(), comment,
					executionContext.getServiceContext());
			}
		}
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {PortalException.class, SystemException.class})
	protected void follow(
			KaleoNode sourceKaleoNode, KaleoNode targetKaleoNode,
			List<PathElement> remainingPathElement,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		if (sourceKaleoNode != null) {
			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(sourceKaleoNode.getType()));

			nodeExecutor.exit(
				sourceKaleoNode, executionContext, remainingPathElement);

			executeKaleoActions(
				sourceKaleoNode.getKaleoNodeId(), ActionType.ON_EXIT,
				executionContext, executionContext.getServiceContext());
		}

		if (targetKaleoNode != null) {
			KaleoLogLocalServiceUtil.addNodeEntryKaleoLog(
				executionContext.getKaleoInstanceToken(), sourceKaleoNode,
				targetKaleoNode, executionContext.getServiceContext());

			executeKaleoActions(
				targetKaleoNode.getKaleoNodeId(), ActionType.ON_ENTRY,
				executionContext, executionContext.getServiceContext());

			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(targetKaleoNode.getType()));

			nodeExecutor.enter(
				targetKaleoNode, executionContext, remainingPathElement);
		}
	}

	protected void follow(List<PathElement> remainingPathElement)
		throws PortalException, SystemException {

		while (!remainingPathElement.isEmpty()) {
			PathElement pathElement = remainingPathElement.remove(0);

			follow(
				pathElement.getStartNode(), pathElement.getTargetNode(),
				remainingPathElement, pathElement.getExecutionContext());
		}
	}

}