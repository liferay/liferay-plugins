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

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.WorkflowTaskAdapter;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.graph.GraphWalker;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * <a href="DefaultTaskManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class DefaultTaskManagerImpl implements TaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
			long workflowTaskInstanceId, long roleId, String comment,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return assignWorkflowTask(
				workflowTaskInstanceId, Role.class.getName(), roleId,
				comment, context, serviceContext);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public WorkflowTask assignWorkflowTaskToUser(
			long workflowTaskInstanceId, long assigneeUserId, String comment,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return assignWorkflowTask(
				workflowTaskInstanceId, User.class.getName(), assigneeUserId,
				comment, context, serviceContext);
		}
		catch (WorkflowException we) {
			throw we;
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
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public void setGraphWalker(GraphWalker graphWalker) {
		_graphWalker = graphWalker;
	}

	protected WorkflowTask assignWorkflowTask(
			long workflowTaskInstanceId, String assigneeClassName,
			long assigneeClassPK, String comment,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws Exception {

		KaleoTaskInstanceToken currentKaleoTaskInstanceToken =
			KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceToken(
				workflowTaskInstanceId);

		if (currentKaleoTaskInstanceToken.isCompleted()) {
			throw new WorkflowException(
				"Cannot reassign a completed task " + workflowTaskInstanceId);
		}

		KaleoTaskInstanceAssignment currentKaleoTaskInstanceAssignment =
			currentKaleoTaskInstanceToken.getKaleoTaskInstanceAssignment();

		KaleoTaskInstanceAssignment newKaleoTaskInstanceAssignment =
			KaleoTaskInstanceAssignmentLocalServiceUtil.
				assignKaleoTaskInstanceAssignment(
					currentKaleoTaskInstanceAssignment.
						getKaleoTaskInstanceAssignmentId(),
					assigneeClassName, assigneeClassPK, context,
					serviceContext);

		KaleoLogLocalServiceUtil.addTaskAssignmentKaleoLog(
			currentKaleoTaskInstanceToken, currentKaleoTaskInstanceAssignment,
			newKaleoTaskInstanceAssignment, comment, context, serviceContext);

		return new WorkflowTaskAdapter(
			currentKaleoTaskInstanceToken, newKaleoTaskInstanceAssignment,
			context);
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

		KaleoInstanceToken currentKaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();

		ExecutionContext executionContext = new ExecutionContext(
			currentKaleoInstanceToken, kaleoTaskInstanceToken,
			kaleoTaskInstanceAssignment, context, serviceContext);

		executionContext = _graphWalker.completeTask(comment, executionContext);

		_graphWalker.signalExit(transitionName, executionContext);

		return new WorkflowTaskAdapter(
			kaleoTaskInstanceToken, kaleoTaskInstanceAssignment, context);
	}

	private GraphWalker _graphWalker;

}