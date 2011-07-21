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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrimitiveLongSet;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.KaleoSignaler;
import com.liferay.portal.workflow.kaleo.runtime.TaskManager;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WorkflowTaskManagerImpl implements WorkflowTaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
			long companyId, long userId, long workflowTaskInstanceId,
			long roleId, String comment, Date dueDate,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _taskManager.assignWorkflowTaskToRole(
			workflowTaskInstanceId, roleId, comment, dueDate, workflowContext,
			serviceContext);
	}

	public WorkflowTask assignWorkflowTaskToUser(
			long companyId, long userId, long workflowTaskInstanceId,
			long assigneeUserId, String comment, Date dueDate,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _taskManager.assignWorkflowTaskToUser(
			workflowTaskInstanceId, assigneeUserId, comment, dueDate,
			workflowContext, serviceContext);
	}

	public WorkflowTask completeWorkflowTask(
			long companyId, long userId, long workflowTaskInstanceId,
			String transitionName, String comment,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		WorkflowTaskAdapter workflowTaskAdapter =
			(WorkflowTaskAdapter)_taskManager.completeWorkflowTask(
				workflowTaskInstanceId, transitionName, comment,
				workflowContext, serviceContext);

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				workflowTaskAdapter.getKaleoTaskInstanceToken();

			KaleoInstanceToken kaleoInstanceToken =
				kaleoTaskInstanceToken.getKaleoInstanceToken();

			if (workflowContext == null) {
				workflowContext = WorkflowContextUtil.convert(
					kaleoInstanceToken.getKaleoInstance().getWorkflowContext());
			}

			workflowContext.put(
				WorkflowConstants.CONTEXT_TASK_COMMENTS, comment);
			workflowContext.put(
				WorkflowConstants.CONTEXT_TRANSITION_NAME, transitionName);

			ExecutionContext executionContext = new ExecutionContext(
				kaleoInstanceToken, workflowContext, serviceContext);

			_kaleoSignaler.signalExit(transitionName, executionContext);
		}
		catch (Exception e) {
			throw new WorkflowException("Unable to complete task", e);
		}

		return workflowTaskAdapter;
	}

	public List<String> getNextTransitionNames(
			long companyId, long userId, long workflowTaskInstanceId)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceToken(workflowTaskInstanceId);

			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();
			KaleoNode kaleoNode = kaleoTask.getKaleoNode();

			List<KaleoTransition> kaleoTransitions =
				kaleoNode.getKaleoTransitions();

			List<String> transitionNames = new ArrayList<String>(
				kaleoTransitions.size());

			for (KaleoTransition kaleoTransition : kaleoTransitions) {
				transitionNames.add(kaleoTransition.getName());
			}

			return transitionNames;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public long[] getPooledActorsIds(
			long companyId, long workflowTaskInstanceId)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceToken(workflowTaskInstanceId);
			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			List<KaleoTaskAssignment> kaleoTaskAssignments =
				KaleoTaskAssignmentLocalServiceUtil.getKaleoTaskAssignments(
					kaleoTask.getKaleoTaskId(), Role.class.getName());

			PrimitiveLongSet pooledActors = new PrimitiveLongSet();

			for (KaleoTaskAssignment kaleoTaskAssignment :
					kaleoTaskAssignments) {

				long roleId = kaleoTaskAssignment.getAssigneeClassPK();

				Role role = RoleLocalServiceUtil.getRole(roleId);

				if ((role.getType() == RoleConstants.TYPE_SITE) ||
					(role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

					List<UserGroupRole> userGroupRoles =
						UserGroupRoleLocalServiceUtil.
							getUserGroupRolesByGroupAndRole(
								kaleoTaskInstanceToken.getGroupId(), roleId);

					for (UserGroupRole userGroupRole : userGroupRoles) {
						pooledActors.add(userGroupRole.getUserId());
					}
				}
				else {
					long[] userIds = UserLocalServiceUtil.getRoleUserIds(
						roleId);

					pooledActors.addAll(userIds);
				}
			}

			return pooledActors.getArray();
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public WorkflowTask getWorkflowTask(
			long companyId, long workflowTaskInstanceId)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceToken(workflowTaskInstanceId);

			return new WorkflowTaskAdapter(
				kaleoTaskInstanceToken,
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getWorkflowContext()));
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCount(long companyId, Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoTaskInstanceTokenLocalServiceUtil.
				getKaleoTaskInstanceTokensCount(completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByRole(
			long companyId, long roleId, Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoTaskInstanceTokenLocalServiceUtil.
				getKaleoTaskInstanceTokensCount(
					Role.class.getName(), roleId, completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountBySubmittingUser(
			long companyId, long userId, Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoTaskInstanceTokenLocalServiceUtil.
				getSubmittingUserKaleoTaskInstanceTokensCount(
					userId, completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByUser(
			long companyId, long userId, Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return KaleoTaskInstanceTokenLocalServiceUtil.
				getKaleoTaskInstanceTokensCount(
					User.class.getName(), serviceContext.getUserId(),
					completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByUserRoles(
			long companyId, long userId, Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount(
				null, completed, Boolean.TRUE, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByWorkflowInstance(
			long companyId, Long userId, long workflowInstanceId,
			Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			if (userId != null) {
				serviceContext.setUserId(userId);
			}

			return KaleoTaskInstanceTokenLocalServiceUtil.
				getKaleoTaskInstanceTokensCount(
					workflowInstanceId, completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasks(
			long companyId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceTokens(
						completed, start, end, orderByComparator,
						serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByRole(
			long companyId, long roleId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceTokens(
						Role.class.getName(), roleId, completed, start, end,
						orderByComparator, serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksBySubmittingUser(
			long companyId, long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getSubmittingUserKaleoTaskInstanceTokens(
						userId, completed, start, end, orderByComparator,
						serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByUser(
			long companyId, long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceTokens(
						User.class.getName(), userId, completed, start, end,
						orderByComparator, serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByUserRoles(
			long companyId, long userId, Boolean completed, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.search(
					null, completed, Boolean.TRUE, start, end,
					orderByComparator, serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByWorkflowInstance(
			long companyId, Long userId, long workflowInstanceId,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			if (userId != null) {
				serviceContext.setUserId(userId);
			}

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceTokens(
						workflowInstanceId, completed, start, end,
						orderByComparator, serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> search(
			long companyId, long userId, String keywords, Boolean completed,
			Boolean searchByUserRoles, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.search(
					keywords, completed, searchByUserRoles, start, end,
					orderByComparator, serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> search(
			long companyId, long userId, String taskName, String assetType,
			Long[] assetPrimaryKey, Date dueDateGT, Date dueDateLT,
			Boolean completed, Boolean searchByUserRoles, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.search(
					taskName, assetType, assetPrimaryKey, dueDateGT, dueDateLT,
					completed, searchByUserRoles, andOperator, start, end,
					orderByComparator, serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int searchCount(
			long companyId, long userId, String keywords, Boolean completed,
			Boolean searchByUserRoles)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount(
				keywords, completed, searchByUserRoles, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int searchCount(
			long companyId, long userId, String taskName, String assetType,
			Long[] assetPrimaryKey, Date dueDateGT, Date dueDateLT,
			Boolean completed, Boolean searchByUserRoles, boolean andOperator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount(
				taskName, assetType, assetPrimaryKey, dueDateGT, dueDateLT,
				completed, searchByUserRoles, andOperator, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public void setKaleoSignaler(KaleoSignaler kaleoSignaler) {
		_kaleoSignaler = kaleoSignaler;
	}

	public void setTaskManager(TaskManager taskManager) {
		_taskManager = taskManager;
	}

	public WorkflowTask updateDueDate(
			long companyId, long userId, long workflowTaskInstanceId,
			String comment, Date dueDate)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _taskManager.updateDueDate(
			workflowTaskInstanceId, comment, dueDate, serviceContext);
	}

	protected List<WorkflowTask> toWorkflowTasks(
			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens)
		throws PortalException, SystemException {

		List<WorkflowTask> workflowTasks = new ArrayList<WorkflowTask>(
			kaleoTaskInstanceTokens.size());

		for (KaleoTaskInstanceToken kaleoTaskInstanceToken :
				kaleoTaskInstanceTokens) {

			WorkflowTask workflowTask = new WorkflowTaskAdapter(
				kaleoTaskInstanceToken,
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getWorkflowContext()));

			workflowTasks.add(workflowTask);
		}

		return workflowTasks;
	}

	private KaleoSignaler _kaleoSignaler;
	private TaskManager _taskManager;

}