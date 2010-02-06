/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.jbpm.dao.CustomSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowTaskManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskManagerImpl implements WorkflowTaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
			long userId, long workflowTaskId, long roleId, String comment,
			Map<String, Object> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			taskInstance.setPooledActors(String.valueOf(roleId));

			taskInstance.addComment(comment);

			if (context != null) {
				taskInstance.addVariables(context);
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
			long userId, long workflowTaskId, long assigneeUserId,
			String comment, Map<String, Object> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			String oldActorId = taskInstance.getActorId();

			Set<PooledActor> pooledActors = taskInstance.getPooledActors();

			if ((pooledActors == null) || pooledActors.isEmpty()) {
				throw new WorkflowException(
					"Workflow task " + workflowTaskId +
						" has not been assigned to a role");
			}

			PooledActor pooledActor = pooledActors.iterator().next();

			long roleId = GetterUtil.getLong(pooledActor.getActorId());

			if (!RoleLocalServiceUtil.hasUserRole(assigneeUserId, roleId)) {
				throw new WorkflowException(
					"Workflow task " + workflowTaskId +
						" cannot be assigned to user " + assigneeUserId);
			}

			taskInstance.setActorId(String.valueOf(assigneeUserId));

			taskInstance.addComment(comment);

			if (context != null) {
				taskInstance.addVariables(context);
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
			long userId, long workflowTaskId, String transitionName,
			String comment, Map<String, Object> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			Token oldToken = taskInstance.getToken();

			Node oldNode = oldToken.getNode();

			long actorId = GetterUtil.getLong(taskInstance.getActorId());

			if (actorId != userId) {
				throw new WorkflowException(
					"Workflow task " + workflowTaskId +
						" is not assigned to user " + userId);
			}

			taskInstance.addComment(comment);

			if (context != null) {
				taskInstance.addVariables(context);
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

			return new WorkflowTaskImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<String> getNextTransitionNames(long userId, long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			long actorId = GetterUtil.getLong(taskInstance.getActorId());

			if (actorId != userId) {
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

	public long[] getPooledActorsIds(long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				workflowTaskId);

			Set<PooledActor> pooledActors =	taskInstance.getPooledActors();

			if ((pooledActors == null) || pooledActors.isEmpty()) {
				return null;
			}

			PooledActor pooledActor = pooledActors.iterator().next();

			long roleId = GetterUtil.getLong(pooledActor.getActorId());

			return UserLocalServiceUtil.getRoleUserIds(roleId);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowTask getWorkflowTask(long workflowTaskId)
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

	public int getWorkflowTaskCount(Boolean completed)
		throws WorkflowException {

		return getWorkflowTaskCount(null, false, completed);
	}

	public int getWorkflowTaskCountByRole(long roleId, Boolean completed)
		throws WorkflowException {

		return getWorkflowTaskCount(new long[] {roleId}, true, completed);
	}

	public int getWorkflowTaskCountByUser(long userId, Boolean completed)
		throws WorkflowException {

		return getWorkflowTaskCount(new long[] {userId}, false, completed);
	}

	public int getWorkflowTaskCountByUserRoles(long userId, Boolean completed)
		throws WorkflowException {

		try {
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(userId);

			long[] roleIds = StringUtil.split(
				ListUtil.toString(roles, "roleId"), 0L);

			return getWorkflowTaskCount(roleIds, true, completed);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowTaskCountByWorkflowInstance(
			long workflowInstanceId, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countTaskInstances(
				-1, workflowInstanceId, null, false, completed);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowTask> getWorkflowTasks(
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowTasks(
			-1, null, false, completed, start, end,	orderByComparator);
	}

	public List<WorkflowTask> getWorkflowTasksByRole(
			long roleId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowTasks(
			-1, new long[] {roleId}, true, completed, start, end,
			orderByComparator);
	}

	public List<WorkflowTask> getWorkflowTasksByUser(
			long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowTasks(
			-1, new long[] {userId}, false, completed, start, end,
			orderByComparator);
	}

	public List<WorkflowTask> getWorkflowTasksByUserRoles(
			long userId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(userId);

			long[] roleIds = StringUtil.split(
				ListUtil.toString(roles, "roleId"), 0L);

			return getWorkflowTasks(
				-1, roleIds, true, completed, start, end, orderByComparator);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowTask> getWorkflowTasksByWorkflowInstance(
			long workflowInstanceId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowTasks(
			workflowInstanceId, null, false, completed, start, end,
			orderByComparator);
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	protected int getWorkflowTaskCount(
			long[] actorIds, boolean pooledActors, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			if (actorIds != null) {
				return customSession.countTaskInstances(
					-1, -1, ArrayUtil.toStringArray(actorIds), pooledActors,
					completed);
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
			long workflowInstanceId, long[] actorIds, boolean pooledActors,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			String[] actorIdStringArray = null;

			if (actorIds != null) {
				actorIdStringArray = ArrayUtil.toStringArray(actorIds);
			}

			List<TaskInstance> taskInstances = customSession.findTaskInstances(
				-1, workflowInstanceId, actorIdStringArray, pooledActors,
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

	protected List<WorkflowTask> toWorkflowTasks(
		List<TaskInstance> taskInstances) {

		List<WorkflowTask> taskInstanceInfos =
			new ArrayList<WorkflowTask>(taskInstances.size());

		for (TaskInstance taskInstance : taskInstances) {
			taskInstanceInfos.add(new WorkflowTaskImpl(taskInstance));
		}

		return taskInstanceInfos;
	}

	private JbpmConfiguration _jbpmConfiguration;

}