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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrimitiveLongList;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowLog;
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
import com.liferay.portal.workflow.jbpm.dao.CustomSession;
import com.liferay.portal.workflow.jbpm.util.AssigneeRetrievalUtil;
import com.liferay.portal.workflow.jbpm.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowTaskManagerImpl implements WorkflowTaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
			long companyId, long userId, long workflowTaskId, long roleId,
			String comment, Date dueDate, Map<String, Serializable> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			taskInstance.setPooledActors(String.valueOf(roleId));

			taskInstance.addComment(comment);

			setDueDate(taskInstance, dueDate);

			if (context != null) {
				taskInstance.addVariables(new HashMap<String, Object>(context));
			}

			jbpmContext.save(taskInstance);

			return new WorkflowTaskImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowTask assignWorkflowTaskToUser(
			long companyId, long userId, long workflowTaskId,
			long assigneeUserId, String comment, Date dueDate,
			Map<String, Serializable> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			String oldActorId = taskInstance.getActorId();

			taskInstance.setActorId(String.valueOf(assigneeUserId));
			taskInstance.setPooledActors(new HashSet<PooledActor>());

			taskInstance.addComment(comment);

			setDueDate(taskInstance, dueDate);

			if (context != null) {
				taskInstance.addVariables(new HashMap<String, Object>(context));
			}

			jbpmContext.save(taskInstance);

			WorkflowLogImpl workflowLogImpl = new WorkflowLogImpl();

			workflowLogImpl.setComment(comment);
			workflowLogImpl.setCreateDate(new Date());
			workflowLogImpl.setPreviousUserId(GetterUtil.getLong(oldActorId));
			workflowLogImpl.setTaskInstance(taskInstance);
			workflowLogImpl.setType(WorkflowLog.TASK_ASSIGN);
			workflowLogImpl.setUserId(assigneeUserId);

			Session session = jbpmContext.getSession();

			session.save(workflowLogImpl);

			CustomSession customSession = new CustomSession(jbpmContext);

			TaskInstanceExtensionImpl taskInstanceExtension =
				customSession.findTaskInstanceExtension(taskInstance.getId());

			List<Assignee> assignees = taskInstanceExtension.getAssignees();

			assignees.clear();

			ProcessInstance processInstance = taskInstance.getProcessInstance();

			ContextInstance contextInstance =
				processInstance.getContextInstance();

			long groupId = GetterUtil.getLong(
				(String)contextInstance.getVariable("groupId"));

			Assignee assignee = new Assignee(
				companyId, groupId, User.class.getName(), assigneeUserId);

			assignees.add(assignee);

			session.update(taskInstanceExtension);

			return new WorkflowTaskImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowTask completeWorkflowTask(
			long companyId, long userId, long workflowTaskId,
			String transitionName, String comment,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			Token oldToken = taskInstance.getToken();

			Node oldNode = oldToken.getNode();

			long actorId = GetterUtil.getLong(taskInstance.getActorId());

			if (!isWorkflowTaskAssignedToUser(
					taskInstance.getActorId(), userId)) {

				throw new WorkflowException(
					"Workflow task " + workflowTaskId +
						" is not assigned to user " + userId);
			}

			taskInstance.addComment(comment);

			if (workflowContext != null) {
				taskInstance.addVariables(
					new HashMap<String, Object>(workflowContext));
			}

			if (transitionName == null) {
				taskInstance.end();
			}
			else {
				taskInstance.end(transitionName);
			}

			jbpmContext.save(taskInstance);

			Token token = taskInstance.getToken();

			Node node = token.getNode();

			WorkflowLogImpl workflowLogImpl = new WorkflowLogImpl();

			workflowLogImpl.setCreateDate(new Date());
			workflowLogImpl.setComment(comment);
			workflowLogImpl.setPreviousState(oldNode.getName());
			workflowLogImpl.setState(node.getName());
			workflowLogImpl.setTaskInstance(taskInstance);
			workflowLogImpl.setType(WorkflowLog.TRANSITION);
			workflowLogImpl.setUserId(actorId);

			Session session = jbpmContext.getSession();

			session.save(workflowLogImpl);

			ProcessInstance processInstance = taskInstance.getProcessInstance();

			ContextInstance contextInstance =
				processInstance.getContextInstance();

			long groupId = GetterUtil.getLong(
				(String)contextInstance.getVariable("groupId"));

			TaskMgmtInstance taskMgmtInstance =
				taskInstance.getTaskMgmtInstance();

			Collection<TaskInstance> taskInstances =
				taskMgmtInstance.getTaskInstances();

			CustomSession customSession = new CustomSession(jbpmContext);

			for (TaskInstance curTaskInstance : taskInstances) {
				TaskInstanceExtensionImpl taskInstanceExtensionImpl =
					customSession.findTaskInstanceExtension(
						curTaskInstance.getId());

				if (taskInstanceExtensionImpl == null) {
					List<Assignee> assignees =
						AssigneeRetrievalUtil.getAssignees(
							companyId, groupId, curTaskInstance.getActorId(),
							curTaskInstance.getPooledActors());

					String workflowContextJSON =
						WorkflowContextUtil.convertToJSON(workflowContext);

					taskInstanceExtensionImpl = new TaskInstanceExtensionImpl(
						companyId, groupId, userId, assignees,
						workflowContextJSON, curTaskInstance);

					session.save(taskInstanceExtensionImpl);
				}
			}

			return new WorkflowTaskImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<String> getNextTransitionNames(
			long companyId, long userId, long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			if (!isWorkflowTaskAssignedToUser(
					taskInstance.getActorId(), userId)) {

				throw new WorkflowException(
					"Workflow task " + workflowTaskId +
						" is not assigned to user " + userId);
			}

			Task task = taskInstance.getTask();
			TaskNode taskNode = task.getTaskNode();
			List<Transition> transitions = taskNode.getLeavingTransitions();

			List<String> transitionNames = new ArrayList<String>(
				transitions.size());

			for (Transition transition : transitions) {
				transitionNames.add(transition.getName());
			}

			return transitionNames;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public long[] getPooledActorsIds(long companyId, long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			CustomSession customSession = new CustomSession(jbpmContext);

			TaskInstanceExtensionImpl taskInstanceExtensionImpl =
				customSession.findTaskInstanceExtension(taskInstance.getId());

			List<Assignee> assignees = taskInstanceExtensionImpl.getAssignees();

			PrimitiveLongList pooledActors = new PrimitiveLongList();

			for (Assignee assignee : assignees) {
				 String assigneeClassName = assignee.getAssigneeClassName();

				 if (assigneeClassName.equals(Role.class.getName())) {
					long roleId = assignee.getAssigneeClassPK();

					Role role = RoleLocalServiceUtil.getRole(roleId);

					if ((role.getType() == RoleConstants.TYPE_SITE) ||
						(role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

						List<UserGroupRole> userGroupRoles =
							UserGroupRoleLocalServiceUtil.
								getUserGroupRolesByGroupAndRole(
									taskInstanceExtensionImpl.getGroupId(),
									roleId);

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
			}

			return pooledActors.getArray();
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowTask getWorkflowTask(
			long companyId, long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			return new WorkflowTaskImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowTaskCount(long companyId, Boolean completed)
		throws WorkflowException {

		return getWorkflowTaskCount(null, false, completed);
	}

	public int getWorkflowTaskCountByRole(
			long companyId, long roleId, Boolean completed)
		throws WorkflowException {

		try {
			Role role = RoleLocalServiceUtil.getRole(roleId);

			String[] actorIds = new String[] {
				String.valueOf(roleId), role.getName()};

			return getWorkflowTaskCount(actorIds, true, completed);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountBySubmittingUser(
			long companyId, long userId, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countTaksInstancesBySubmittingUser(
				companyId, userId, completed);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByUser(
			long companyId, long userId, Boolean completed)
		throws WorkflowException {

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			String[] actorIds = new String[] {
				String.valueOf(userId), user.getEmailAddress()};

			return getWorkflowTaskCount(actorIds, false, completed);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByUserRoles(
			long companyId, long userId, Boolean completed)
		throws WorkflowException {

		try {
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(userId);

			String[] actorIds = getActorIds(roles);

			return getWorkflowTaskCount(actorIds, true, completed);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByWorkflowInstance(
			long companyId, Long userId, long workflowInstanceId,
			Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			String[] actorIds = null;

			if ((userId != null) && (userId > 0)) {
				actorIds = new String[] {String.valueOf(userId)};
			}

			return customSession.countTaskInstances(
				-1, workflowInstanceId, actorIds, false, completed);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowTask> getWorkflowTasks(
			long companyId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowTasks(
			-1, null, false, completed, start, end,	orderByComparator);
	}

	public List<WorkflowTask> getWorkflowTasksByRole(
			long companyId, long roleId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			Role role = RoleLocalServiceUtil.getRole(roleId);

			String[] actorIds = new String[] {
				String.valueOf(roleId), role.getName()};

			return getWorkflowTasks(
				-1, actorIds, true, completed, start, end, orderByComparator);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksBySubmittingUser(
			long companyId, long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			List<TaskInstance> taskInstances =
				customSession.findTaskInstancesBySubmittingUser(
					companyId, userId, completed, start, end,
					orderByComparator);

			return toWorkflowTasks(taskInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowTask> getWorkflowTasksByUser(
			long companyId, long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			String[] actorIds = new String[] {
				String.valueOf(userId), user.getEmailAddress()};

			return getWorkflowTasks(
				-1, actorIds, false, completed, start, end, orderByComparator);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByUserRoles(
			long companyId, long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(userId);

			String[] actorIds = getActorIds(roles);

			return getWorkflowTasks(
				-1, actorIds, true, completed, start, end, orderByComparator);
		}
		catch (WorkflowException we) {
			throw we;
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

		String[] actorIds = null;

		if ((userId != null) && (userId > 0)) {
			actorIds = new String[] {String.valueOf(userId)};
		}

		return getWorkflowTasks(
			workflowInstanceId, actorIds, false, completed, start, end,
			orderByComparator);
	}

	public List<WorkflowTask> search(
			long companyId, long userId, String keywords, Boolean completed,
			Boolean searchByUserRoles, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			List<TaskInstance> taskInstances =
				customSession.searchTaskInstances(
					keywords, keywords, null, null, null, completed,
					searchByUserRoles, false, start, end, orderByComparator,
					serviceContext);

			return toWorkflowTasks(taskInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowTask> search(
			long companyId, long userId, String taskName, String assetType,
			Long[] assetPrimaryKeys, Date dueDateGT, Date dueDateLT,
			Boolean completed, Boolean searchByUserRoles, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			List<TaskInstance> taskInstances =
				customSession.searchTaskInstances(
					taskName, assetType, assetPrimaryKeys, dueDateGT, dueDateLT,
					completed, searchByUserRoles, andOperator, start, end,
					orderByComparator, serviceContext);

			return toWorkflowTasks(taskInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int searchCount(
			long companyId, long userId, String keywords,
			Boolean completed, Boolean searchByUserRoles)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return customSession.searchCountTaskInstances(
				keywords, keywords, null, null, null, completed,
				searchByUserRoles, false, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int searchCount(
			long companyId, long userId, String taskName, String assetType,
			Long[] assetPrimaryKeys, Date dueDateGT, Date dueDateLT,
			Boolean completed, Boolean searchByUserRoles, boolean andOperator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return customSession.searchCountTaskInstances(
				taskName, assetType, assetPrimaryKeys, dueDateGT, dueDateLT,
				completed, searchByUserRoles, andOperator, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	public WorkflowTask updateDueDate(
			long companyId, long userId, long workflowTaskId,
			String comment, Date dueDate)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			taskInstance.addComment(comment);

			setDueDate(taskInstance, dueDate);

			jbpmContext.save(taskInstance);

			Token token = taskInstance.getToken();

			Node node = token.getNode();

			WorkflowLogImpl workflowLogImpl = new WorkflowLogImpl();

			workflowLogImpl.setComment(comment);
			workflowLogImpl.setCreateDate(new Date());
			workflowLogImpl.setState(node.getName());
			workflowLogImpl.setTaskInstance(taskInstance);
			workflowLogImpl.setType(WorkflowLog.TASK_UPDATE);
			workflowLogImpl.setUserId(userId);

			Session session = jbpmContext.getSession();

			session.save(workflowLogImpl);

			return new WorkflowTaskImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected String[] getActorIds(List<Role> roles) {
		return StringUtil.split(
			ListUtil.toString(roles, "roleId").concat(
				StringPool.COMMA).concat(ListUtil.toString(roles, "name")));
	}

	protected int getWorkflowTaskCount(
			String[] actorIds, boolean pooledActors, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			if (actorIds != null) {
				return customSession.countTaskInstances(
					-1, -1, actorIds, pooledActors,	completed);
			}
			else {
				return customSession.countTaskInstances(
					-1, -1, null, pooledActors,	completed);
			}
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected List<WorkflowTask> getWorkflowTasks(
			long workflowInstanceId, String[] actorIds, boolean pooledActors,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			List<TaskInstance> taskInstances = customSession.findTaskInstances(
				-1, workflowInstanceId, actorIds, pooledActors,
				completed, start, end, orderByComparator);

			return toWorkflowTasks(taskInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected boolean isWorkflowTaskAssignedToUser(String actorId, long userId)
		throws WorkflowException {

		try {
			if (actorId.equals(String.valueOf(userId))) {
				return true;
			}

			if (Validator.isEmailAddress(actorId)) {
				User user = UserLocalServiceUtil.getUser(userId);

				if (actorId.equals(user.getEmailAddress())) {
					return true;
				}
			}

			return false;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	protected void setDueDate(TaskInstance taskInstance, Date dueDate) {
		if (dueDate == null) {
			return;
		}

		Calendar cal = CalendarFactoryUtil.getCalendar(LocaleUtil.getDefault());

		cal.setTime(dueDate);

		taskInstance.setDueDate(cal.getTime());
	}

	protected List<WorkflowTask> toWorkflowTasks(
			List<TaskInstance> taskInstances)
		throws PortalException, SystemException {

		List<WorkflowTask> taskInstanceInfos =
			new ArrayList<WorkflowTask>(taskInstances.size());

		for (TaskInstance taskInstance : taskInstances) {
			taskInstanceInfos.add(new WorkflowTaskImpl(taskInstance));
		}

		return taskInstanceInfos;
	}

	private JbpmConfiguration _jbpmConfiguration;

}