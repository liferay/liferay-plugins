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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrimitiveLongList;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.TaskManager;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowTaskManagerImpl.java.html}"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowTaskManagerImpl implements WorkflowTaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
			long companyId, long userId, long workflowTaskInstanceId,
			long roleId, String comment, Date dueDate,
			Map<String, Serializable> context)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _taskManager.assignWorkflowTaskToRole(
			workflowTaskInstanceId, roleId, comment, dueDate, context,
			serviceContext);
	}

	public WorkflowTask assignWorkflowTaskToUser(
			long companyId, long userId, long workflowTaskInstanceId,
			long assigneeUserId, String comment, Date dueDate,
			Map<String, Serializable> context)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _taskManager.assignWorkflowTaskToUser(
			workflowTaskInstanceId, assigneeUserId, comment, dueDate, context,
			serviceContext);
	}

	public WorkflowTask completeWorkflowTask(
			long companyId, long userId, long workflowTaskInstanceId,
			String transitionName, String comment,
			Map<String, Serializable> context)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _taskManager.completeWorkflowTask(
			workflowTaskInstanceId, transitionName, comment, context,
			serviceContext);
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
					Role.class.getName(), kaleoTask.getKaleoTaskId());

			PrimitiveLongList pooledActors = new PrimitiveLongList();

			for (KaleoTaskAssignment kaleoTaskAssignment :
					kaleoTaskAssignments) {

				long roleId = kaleoTaskAssignment.getAssigneeClassPK();

				long[] userIds = UserLocalServiceUtil.getRoleUserIds(roleId);

				pooledActors.addAll(userIds);
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
				kaleoTaskInstanceToken.getKaleoTaskInstanceAssignment(),
				ContextUtil.convert(kaleoTaskInstanceToken.getContext()));
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

			List<Long> roleIds = getRoleIds(serviceContext);

			return KaleoTaskInstanceTokenLocalServiceUtil.
				getKaleoTaskInstanceTokensCount(
					roleIds, completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByWorkflowInstance(
			long companyId, long workflowInstanceId, Boolean completed)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

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

			List<Long> roleIds = getRoleIds(serviceContext);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceTokens(
						roleIds, completed, start, end, orderByComparator,
						serviceContext);

			return toWorkflowTasks(kaleoTaskInstanceTokens);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByWorkflowInstance(
			long companyId, long workflowInstanceId, Boolean completed,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

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

	protected List<Long> getRoleIds(ServiceContext serviceContext)
		throws SystemException, PortalException {

		List<Role> roles = RoleLocalServiceUtil.getUserRoles(
			serviceContext.getUserId());

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(
			serviceContext.getUserId(), true);

		List<Role> relatedRoles = RoleLocalServiceUtil.getUserRelatedRoles(
			serviceContext.getUserId(), groups);

		List<Long> roleIds = new ArrayList<Long>(
			roles.size() + relatedRoles.size());

		for (Role role : roles) {
			roleIds.add(role.getRoleId());
		}

		for (Role relatedRole : relatedRoles) {
			roleIds.add(relatedRole.getRoleId());
		}

		return roleIds;
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
				kaleoTaskInstanceToken.getKaleoTaskInstanceAssignment(),
				ContextUtil.convert(kaleoTaskInstanceToken.getContext()));

			workflowTasks.add(workflowTask);
		}

		return workflowTasks;
	}

	private TaskManager _taskManager;

}