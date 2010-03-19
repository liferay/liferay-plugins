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

package com.liferay.portal.workflow.kaleo.runtime;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.WorkflowTaskAdapter;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * <a href="DefaultTaskManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {Exception.class})
public class DefaultTaskManagerImpl implements TaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
			long workflowTaskInstanceId, long roleId, String comment,
			Date dueDate, Map<String, Serializable> context,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return assignWorkflowTask(
				workflowTaskInstanceId, Role.class.getName(), roleId, comment,
				dueDate, context, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public WorkflowTask assignWorkflowTaskToUser(
			long workflowTaskInstanceId, long assigneeUserId, String comment,
			Date dueDate, Map<String, Serializable> context,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return assignWorkflowTask(
				workflowTaskInstanceId, User.class.getName(), assigneeUserId,
				comment, dueDate, context, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public WorkflowTask completeWorkflowTask(
			long workflowTaskInstanceId, String transitionName, String comment,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return doCompleteWorkflowTask(
				workflowTaskInstanceId, transitionName, comment, context,
				serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public void setKaleoSignaler(KaleoSignaler kaleoSignaler) {
		_kaleoSignaler = kaleoSignaler;
	}

	public WorkflowTask updateDueDate(
			long workflowTaskInstanceId, String comment, Date dueDate,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceToken(workflowTaskInstanceId);

			if (kaleoTaskInstanceToken.isCompleted()) {
				throw new WorkflowException(
					"Cannot update due date for completed task " +
						workflowTaskInstanceId);
			}

			if (dueDate != null) {
				KaleoTaskInstanceTokenLocalServiceUtil.updateDueDate(
					workflowTaskInstanceId, dueDate, serviceContext);
			}

			KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
				kaleoTaskInstanceToken.getKaleoTaskInstanceAssignment();

			Map<String, Serializable> context = ContextUtil.convert(
				kaleoTaskInstanceAssignment.getContext());

			KaleoLogLocalServiceUtil.addTaskAssignmentKaleoLog(
				kaleoTaskInstanceToken, kaleoTaskInstanceAssignment,
				kaleoTaskInstanceAssignment, comment, context, serviceContext);

			return new WorkflowTaskAdapter(
				kaleoTaskInstanceToken, kaleoTaskInstanceAssignment, context);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	protected WorkflowTask assignWorkflowTask(
			long workflowTaskInstanceId, String assigneeClassName,
			long assigneeClassPK, String comment, Date dueDate,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws Exception {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceToken(
				workflowTaskInstanceId);

		if (kaleoTaskInstanceToken.isCompleted()) {
			throw new WorkflowException(
				"Cannot reassign a completed task " + workflowTaskInstanceId);
		}

		if (dueDate != null) {
			KaleoTaskInstanceTokenLocalServiceUtil.updateDueDate(
				workflowTaskInstanceId, dueDate, serviceContext);
		}

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			kaleoTaskInstanceToken.getKaleoTaskInstanceAssignment();

		KaleoTaskInstanceAssignment newKaleoTaskInstanceAssignment =
			KaleoTaskInstanceAssignmentLocalServiceUtil.
				assignKaleoTaskInstanceAssignment(
					kaleoTaskInstanceAssignment.
						getKaleoTaskInstanceAssignmentId(),
					assigneeClassName, assigneeClassPK, context,
					serviceContext);

		KaleoLogLocalServiceUtil.addTaskAssignmentKaleoLog(
			kaleoTaskInstanceToken, kaleoTaskInstanceAssignment,
			newKaleoTaskInstanceAssignment, comment, context, serviceContext);

		return new WorkflowTaskAdapter(
			kaleoTaskInstanceToken, newKaleoTaskInstanceAssignment, context);
	}

	protected WorkflowTask doCompleteWorkflowTask(
			long workflowTaskInstanceId, String transitionName, String comment,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws Exception {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceToken(
				workflowTaskInstanceId);

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			KaleoTaskInstanceAssignmentLocalServiceUtil.
				getKaleoTaskInstanceAssignment(
					workflowTaskInstanceId, User.class.getName(),
					serviceContext.getUserId());

		if (kaleoTaskInstanceAssignment.isCompleted()) {
			throw new WorkflowException(
				"Cannot complete an already completed task " +
					workflowTaskInstanceId + " for user " +
						serviceContext.getUserId());
		}

		kaleoTaskInstanceAssignment =
			kaleoTaskInstanceAssignmentLocalService.
				completeKaleoTaskInstanceAssignment(
					kaleoTaskInstanceAssignment.
						getKaleoTaskInstanceAssignmentId());

		kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.completeKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
				serviceContext);

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenLocalService.getKaleoInstanceToken(
				kaleoTaskInstanceToken.getKaleoInstanceTokenId());

		KaleoInstanceToken parentKaleoInstanceToken =
			kaleoInstanceTokenLocalService.getKaleoInstanceToken(
				kaleoInstanceToken.getParentKaleoInstanceTokenId());

		kaleoLogLocalService.addTaskCompletionKaleoLog(
			kaleoTaskInstanceToken, kaleoTaskInstanceAssignment, comment,
			context, serviceContext);

		ExecutionContext executionContext = new ExecutionContext(
			parentKaleoInstanceToken, context, serviceContext);

		_kaleoSignaler.signalExit(transitionName, executionContext);

		return new WorkflowTaskAdapter(
			kaleoTaskInstanceToken, kaleoTaskInstanceAssignment, context);
	}

	private KaleoSignaler _kaleoSignaler;

	@BeanReference(type = KaleoInstanceTokenLocalService.class)
	protected KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService;

	@BeanReference(type = KaleoLogLocalService.class)
	protected KaleoLogLocalService kaleoLogLocalService;

	@BeanReference(type = KaleoTaskInstanceAssignmentLocalService.class)
	protected KaleoTaskInstanceAssignmentLocalService kaleoTaskInstanceAssignmentLocalService;

	@BeanReference(type = KaleoTaskInstanceTokenLocalService.class)
	protected KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService;

}