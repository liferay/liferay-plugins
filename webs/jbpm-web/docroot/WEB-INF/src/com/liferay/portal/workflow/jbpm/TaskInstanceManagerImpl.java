/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.TaskInstanceInfo;
import com.liferay.portal.kernel.workflow.TaskInstanceManager;
import com.liferay.portal.kernel.workflow.UserCredential;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.jbpm.db.CustomSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="TaskInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class TaskInstanceManagerImpl implements TaskInstanceManager {

	public TaskInstanceInfo assignTaskInstanceToRole(
			long taskInstanceId, long roleId, String comment,
			Map<String, Object> attributes, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				taskInstanceId);

			if (attributes != null) {
				taskInstance.addVariables(attributes);
			}

			taskInstance.addComment(comment);

			taskInstance.setPooledActors(String.valueOf(roleId));

			jbpmContext.save(taskInstance);

			return new TaskInstanceInfoImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public TaskInstanceInfo assignTaskInstanceToUser(
			long taskInstanceId, UserCredential userCredential, String comment,
			Map<String, Object> attributes, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				taskInstanceId);

			if (attributes != null) {
				taskInstance.addVariables(attributes);
			}

			taskInstance.addComment(comment);

			Set<PooledActor> pooledActors = taskInstance.getPooledActors();

			if ((pooledActors == null) || pooledActors.isEmpty()) {
				throw new WorkflowException(
					"Task has not been assigned to a role");
			}

			PooledActor pooledActor = pooledActors.iterator().next();

			long roleId = GetterUtil.getLong(pooledActor.getActorId());

			Set<Long> roleIds = userCredential.getRoleIds();

			if (!roleIds.contains(roleId)) {
				throw new WorkflowException(
					userCredential.getUserId() +
						" does not have the role to be assigned to task " +
							taskInstanceId);
			}

			taskInstance.setActorId(String.valueOf(userCredential.getUserId()));

			jbpmContext.save(taskInstance);

			return new TaskInstanceInfoImpl(taskInstance);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public TaskInstanceInfo completeTaskInstance(
			long taskInstanceId, long userId, String comment,
			Map<String, Object> attributes, Map<String, Object> parameters)
		throws WorkflowException {

		return completeTaskInstance(
			taskInstanceId, userId, null, comment, attributes, parameters);
	}

	public TaskInstanceInfo completeTaskInstance(
			long taskInstanceId, long userId, String activityName,
			String comment, Map<String, Object> attributes,
			Map<String, Object> parameters)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				taskInstanceId);

			long actorId = GetterUtil.getLong(taskInstance.getActorId());

			if (actorId != userId) {
				throw new WorkflowException(
					"Task instance " + taskInstanceId +
						" is not assigned to user " + userId);
			}

			if (attributes != null) {
				taskInstance.addVariables(attributes);
			}

			taskInstance.addComment(comment);

			if (activityName == null) {
				taskInstance.end();
			}
			else {
				taskInstance.end(activityName);
			}

			jbpmContext.save(taskInstance);

			return new TaskInstanceInfoImpl(taskInstance);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<String> getPossibleNextActivityNames(
			long taskInstanceId, long userId, Map<String, Object> parameters)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();

			TaskInstance taskInstance = taskMgmtSession.loadTaskInstance(
				taskInstanceId);

			long actorId = GetterUtil.getLong(taskInstance.getActorId());

			if (actorId != userId) {
				throw new WorkflowException(
					"Task instance " + taskInstanceId +
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
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getTaskInstanceInfoCountByCredential(
			UserCredential userCredential)
		throws WorkflowException {

		return getTaskInstanceInfoCountByCredential(userCredential, false);
	}

	public int getTaskInstanceInfoCountByCredential(
			UserCredential userCredential, boolean completed)
		throws WorkflowException {

		long[] roleIds = ArrayUtil.toArray(
			userCredential.getRoleIds().toArray(new Long[0]));

		int pooledActorCount =
			getTaskInstanceInfoCount(roleIds, true, completed);

		int singleActorCount = getTaskInstanceInfoCount(
			new long[] {userCredential.getUserId()}, false, completed);

		return pooledActorCount + singleActorCount;
	}

	public int getTaskInstanceInfoCountByRole(long roleId)
		throws WorkflowException {

		return getTaskInstanceInfoCountByRole(roleId, false);
	}

	public int getTaskInstanceInfoCountByRole(long roleId, boolean completed)
		throws WorkflowException {

		return getTaskInstanceInfoCount(new long[] {roleId}, true, completed);
	}

	public int getTaskInstanceInfoCountByUser(long userId)
		throws WorkflowException {

		return getTaskInstanceInfoCountByUser(userId, false);
	}

	public int getTaskInstanceInfoCountByUser(long userId, boolean completed)
		throws WorkflowException {

		return getTaskInstanceInfoCount(new long[] {userId}, false, completed);
	}

	public int getTaskInstanceInfoCountByWorkflowInstance(
			long workflowInstanceId)
		throws WorkflowException {

		return getTaskInstanceInfoCountByWorkflowInstance(
			workflowInstanceId, false);
	}

	public int getTaskInstanceInfoCountByWorkflowInstance(
			long workflowInstanceId, boolean completed)
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

	public List<TaskInstanceInfo> getTaskInstanceInfosByCredential(
			UserCredential userCredential, boolean completed, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		long[] roleIds = ArrayUtil.toArray(
			userCredential.getRoleIds().toArray(new Long[0]));

		List<TaskInstanceInfo> pooledActorTaskInstanceInfos =
			getTaskInstanceInfos(
				-1, roleIds, true, completed, start, end, orderByComparator);

		List<TaskInstanceInfo> singleActorTaskInstanceInfos =
			getTaskInstanceInfos(
				-1, new long[] {userCredential.getUserId()}, false, completed,
				start, end, orderByComparator);

		List<TaskInstanceInfo> taskInstanceInfos =
			new ArrayList<TaskInstanceInfo>(
				pooledActorTaskInstanceInfos.size() +
					singleActorTaskInstanceInfos.size());

		taskInstanceInfos.addAll(pooledActorTaskInstanceInfos);
		taskInstanceInfos.addAll(singleActorTaskInstanceInfos);

		return taskInstanceInfos;
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByCredential(
			UserCredential userCredential, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		long[] roleIds = ArrayUtil.toArray(
			userCredential.getRoleIds().toArray(new Long[0]));

		List<TaskInstanceInfo> pooledActorTaskInstanceInfos =
			getTaskInstanceInfos(
				-1, roleIds, true, null, start, end, orderByComparator);

		List<TaskInstanceInfo> singleActorTaskInstanceInfos =
			getTaskInstanceInfos(
				-1, new long[] {userCredential.getUserId()}, false, null, start,
				end, orderByComparator);

		List<TaskInstanceInfo> taskInstanceInfos =
			new ArrayList<TaskInstanceInfo>(
				pooledActorTaskInstanceInfos.size() +
					singleActorTaskInstanceInfos.size());

		taskInstanceInfos.addAll(pooledActorTaskInstanceInfos);
		taskInstanceInfos.addAll(singleActorTaskInstanceInfos);

		return taskInstanceInfos;
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByRole(
			long roleId, boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getTaskInstanceInfos(
			-1, new long[] {roleId}, true, completed, start, end,
			orderByComparator);
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByRole(
			long roleId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getTaskInstanceInfos(
			-1, new long[] {roleId}, true, null, start, end, orderByComparator);
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByUser(
			long userId, boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getTaskInstanceInfos(
			-1, new long[] {userId}, false, completed, start, end,
			orderByComparator);
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByUser(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getTaskInstanceInfos(
			-1, new long[] {userId}, false, null, start, end,
			orderByComparator);
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByWorkflowInstance(
			long workflowInstanceId, boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getTaskInstanceInfos(
			workflowInstanceId, null, false, completed, start, end,
			orderByComparator);
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByWorkflowInstance(
			long workflowInstanceId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getTaskInstanceInfos(
			workflowInstanceId, null, false, null, start, end,
			orderByComparator);
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	protected int getTaskInstanceInfoCount(
			long[] actorIds, boolean pooledActors, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countTaskInstances(
				-1, -1, ArrayUtil.toStringArray(actorIds), pooledActors,
				completed);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected List<TaskInstanceInfo> getTaskInstanceInfos(
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

			return toTaskInstanceInfos(taskInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected List<TaskInstanceInfo> toTaskInstanceInfos(
		List<TaskInstance> taskInstances) {

		List<TaskInstanceInfo> taskInstanceInfos =
			new ArrayList<TaskInstanceInfo>(taskInstances.size());

		for (TaskInstance taskInstance : taskInstances) {
			taskInstanceInfos.add(new TaskInstanceInfoImpl(taskInstance));
		}

		return taskInstanceInfos;
	}

	private JbpmConfiguration _jbpmConfiguration;

}