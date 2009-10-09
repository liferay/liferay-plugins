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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.TaskInstanceInfo;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="TaskInstanceInfoImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class TaskInstanceInfoImpl implements TaskInstanceInfo {

	public TaskInstanceInfoImpl(TaskInstance taskInstance) {
		ProcessInstance processInstance = taskInstance.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		Set<PooledActor> pooledActors = taskInstance.getPooledActors();

		if ((pooledActors != null) && !pooledActors.isEmpty()) {
			PooledActor pooledActor = pooledActors.iterator().next();

			_assignRoleId = GetterUtil.getLong(pooledActor.getActorId());
		}

		_assignUserId = GetterUtil.getLong(taskInstance.getActorId());
		_asynchronous = !taskInstance.isBlocking();
		_attributes = taskInstance.getVariables();
		_completionDate = taskInstance.getEnd();
		_createDate = taskInstance.getCreate();
		_description = taskInstance.getDescription();
		_dueDate = taskInstance.getDueDate();
		_taskName = taskInstance.getName();
		_taskInstanceId = taskInstance.getId();
		_workflowDefinitionName = processDefinition.getName();
		_workflowDefinitionVersion = processDefinition.getVersion();
		_workflowInstanceId = processInstance.getId();
	}

	public long getAssignedRoleId() {
		return _assignRoleId;
	}

	public long getAssignedUserId() {
		return _assignUserId;
	}

	public Map<String, Object> getAttributes() {
		return _attributes;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public long getTaskInstanceId() {
		return _taskInstanceId;
	}

	public String getTaskMetaId() {
		return _TASK_META_ID;
	}

	public String getTaskName() {
		return _taskName;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public boolean isAsynchronous() {
		return _asynchronous;
	}

	public boolean isCompleted() {
		if (_completionDate != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private static final String _TASK_META_ID = StringPool.BLANK;

	private long _assignRoleId;
	private long _assignUserId;
	private boolean _asynchronous;
	private Map<String, Object> _attributes;
	private Date _completionDate;
	private Date _createDate;
	private String _description;
	private Date _dueDate;
	private long _taskInstanceId;
	private String _taskName;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;

}