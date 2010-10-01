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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.DueDateDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorUtil;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelector;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationUtil;

import java.io.Serializable;

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

	protected void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();
		KaleoTask kaleoTask = kaleoTaskLocalService.getKaleoNodeKaleoTask(
			currentKaleoNode.getKaleoNodeId());

		Date dueDate = null;

		if (kaleoTask.getDueDateDuration() > 0) {
			DueDateDuration dueDateDuration = new DueDateDuration(
				kaleoTask.getDueDateDuration(),
				DurationScale.parse(kaleoTask.getDueDateScale()));

			dueDate = _dueDateCalculator.getDueDate(
				new Date(), dueDateDuration);
		}

		Collection<KaleoTaskAssignment> kaleoTaskAssignments =
			_taskAssignmentSelector.calculateTaskAssignments(
				kaleoTask.getKaleoTaskAssignments(), executionContext);

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId(),
				kaleoTask.getKaleoTaskId(), kaleoTask.getName(),
				kaleoTaskAssignments, dueDate, workflowContext, serviceContext);

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