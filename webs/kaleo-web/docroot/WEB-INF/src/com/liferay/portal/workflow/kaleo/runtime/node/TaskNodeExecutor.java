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

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.DueDateDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <a href="TaskNodeExecutor.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class TaskNodeExecutor extends BaseNodeExecutor {

	public void setDueDateCalculator(DueDateCalculator dueDateCalculator) {
		_dueDateCalculator = dueDateCalculator;
	}

	protected void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException {

		Map<String, Serializable> context = executionContext.getContext();
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

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId(),
				kaleoTask.getKaleoTaskId(), dueDate, context, serviceContext);

		KaleoTaskAssignment kaleoTaskAssignment =
			kaleoTask.getDefaultKaleoTaskAssignment();

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			kaleoTaskInstanceAssignmentLocalService.
				addKaleoTaskInstanceAssignment(
					kaleoTaskInstanceToken,
					kaleoTaskAssignment.getKaleoTaskAssignmentId(), context);

		kaleoLogLocalService.addTaskAssignmentKaleoLog(
			kaleoTaskInstanceToken, null, kaleoTaskInstanceAssignment,
			"Assigned initial task.", context, serviceContext);
	}

	protected void doExit(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException {

		Map<String, Serializable> context = executionContext.getContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		String transitionName = executionContext.getTransitionName();

		KaleoTransition kaleoTransition = null;

		if (Validator.isNull(transitionName)) {
			kaleoTransition = currentKaleoNode.getDefaultKaleoTransition();
		}
		else {
			kaleoTransition = currentKaleoNode.getKaleoTransition(
				transitionName);
		}

		ExecutionContext newExecutionContext = new ExecutionContext(
			executionContext.getKaleoInstanceToken(), context, serviceContext);

		PathElement pathElement = new PathElement(
			null, kaleoTransition.getTargetKaleoNode(), newExecutionContext);

		remainingPathElement.add(pathElement);
	}

	@BeanReference(type = KaleoInstanceTokenLocalService.class)
	protected KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService;

	@BeanReference(type = KaleoLogLocalService.class)
	protected KaleoLogLocalService kaleoLogLocalService;

	@BeanReference(type = KaleoTaskLocalService.class)
	protected KaleoTaskLocalService kaleoTaskLocalService;

	@BeanReference(type = KaleoTaskInstanceAssignmentLocalService.class)
	protected KaleoTaskInstanceAssignmentLocalService kaleoTaskInstanceAssignmentLocalService;

	@BeanReference(type = KaleoTaskInstanceTokenLocalService.class)
	protected KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService;

	private DueDateCalculator _dueDateCalculator;

}