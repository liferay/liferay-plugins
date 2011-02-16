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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorUtil;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelector;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class TaskNodeExecutor extends BaseNodeExecutor {

	public void setDueDateCalculator(DueDateCalculator dueDateCalculator) {
		_dueDateCalculator = dueDateCalculator;
	}

	public void setTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector) {

		_taskAssignmentSelector = taskAssignmentSelector;
	}

	protected Date calculateDueDate(KaleoTask kaleoTask)
		throws PortalException, SystemException {
		Date dueDate = null;

		KaleoTimer defaultTimer = kaleoTimerLocalService.getDefaultKaleoTimer(
			kaleoTask.getKaleoTaskId());

		if (defaultTimer != null) {
			DelayDuration delayDuration = new DelayDuration(
				defaultTimer.getDuration(),
				DurationScale.parse(defaultTimer.getScale()));

			dueDate = _dueDateCalculator.getDueDate(new Date(), delayDuration);
		}

		return dueDate;
	}

	protected KaleoTaskInstanceToken createTaskInstanceToken(
			ExecutionContext executionContext,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext,
			KaleoInstanceToken kaleoInstanceToken,
			KaleoTask kaleoTask, Date dueDate)
		throws SystemException, PortalException {
		Collection<KaleoTaskAssignment> configuredKaleoTaskAssignments =
			kaleoTask.getKaleoTaskAssignments();

		Collection<KaleoTaskAssignment> kaleoTaskAssignments =
			new ArrayList<KaleoTaskAssignment>();

		for (KaleoTaskAssignment configuredKaleoTaskAssignment :
				configuredKaleoTaskAssignments) {

			Collection<KaleoTaskAssignment> calculatedKaleoTaskAssignments =
				_taskAssignmentSelector.calculateTaskAssignments(
					configuredKaleoTaskAssignment, executionContext);

			kaleoTaskAssignments.addAll(calculatedKaleoTaskAssignments);
		}

		return kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(
			kaleoInstanceToken.getKaleoInstanceTokenId(),
			kaleoTask.getKaleoTaskId(), kaleoTask.getName(),
			kaleoTaskAssignments, dueDate, workflowContext, serviceContext);
	}

	protected void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();
		KaleoTask kaleoTask = kaleoTaskLocalService.getKaleoNodeKaleoTask(
			currentKaleoNode.getKaleoNodeId());

		Date dueDate = calculateDueDate(kaleoTask);

		KaleoTaskInstanceToken kaleoTaskInstanceToken = createTaskInstanceToken(
			executionContext, workflowContext, serviceContext,
			kaleoInstanceToken, kaleoTask, dueDate);

		executionContext.setKaleoTaskInstanceToken(kaleoTaskInstanceToken);

		ActionExecutorUtil.executeKaleoActions(
			currentKaleoNode.getKaleoNodeId(), ExecutionType.ON_ASSIGNMENT,
			executionContext);

		NotificationUtil.sendKaleoNotifications(
			currentKaleoNode.getKaleoNodeId(), ExecutionType.ON_ASSIGNMENT,
			executionContext);

		kaleoLogLocalService.addTaskAssignmentKaleoLog(
			null, kaleoTaskInstanceToken, "Assigned initial task.",
			workflowContext, serviceContext);
	}

	protected void doExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException {
	}

	protected void doExit(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		String transitionName = executionContext.getTransitionName();

		KaleoTransition kaleoTransition = null;

		if (Validator.isNull(transitionName)) {
			kaleoTransition = transitionSelector.selectKaleoTransition(
				currentKaleoNode, executionContext);
		}
		else {
			kaleoTransition = currentKaleoNode.getKaleoTransition(
				transitionName);
		}

		ExecutionContext newExecutionContext = new ExecutionContext(
			executionContext.getKaleoInstanceToken(), workflowContext,
			serviceContext);

		PathElement pathElement = new PathElement(
			null, kaleoTransition.getTargetKaleoNode(), newExecutionContext);

		remainingPathElement.add(pathElement);
	}

	private DueDateCalculator _dueDateCalculator;
	private TaskAssignmentSelector _taskAssignmentSelector;

}
