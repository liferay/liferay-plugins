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
import com.liferay.portal.kernel.workflow.WorkflowTask;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowTaskImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskImpl implements WorkflowTask {

	public WorkflowTaskImpl(TaskInstance taskInstance) {
		Token token = taskInstance.getToken();
		ProcessInstance processInstance = taskInstance.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		Set<PooledActor> pooledActors = taskInstance.getPooledActors();

		if ((pooledActors != null) && !pooledActors.isEmpty()) {
			PooledActor pooledActor = pooledActors.iterator().next();

			_assigneeRoleId = GetterUtil.getLong(pooledActor.getActorId());
		}

		_assigneeUserId = GetterUtil.getLong(taskInstance.getActorId());
		_asynchronous = !taskInstance.isBlocking();
		_optionalAttributes = taskInstance.getVariables();
		_completionDate = taskInstance.getEnd();
		_createDate = taskInstance.getCreate();
		_description = taskInstance.getDescription();
		_dueDate = taskInstance.getDueDate();
		_name = taskInstance.getName();
		_workflowDefinitionId = processDefinition.getId();
		_workflowDefinitionName = processDefinition.getName();
		_workflowDefinitionVersion = processDefinition.getVersion();
		_workflowInstanceId = token.getId();
		_workflowTaskId = taskInstance.getId();
	}

	public long getAssigneeRoleId() {
		return _assigneeRoleId;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
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

	public String getName() {
		return _name;
	}

	public Map<String, Object> getOptionalAttributes() {
		return _optionalAttributes;
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
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

	public long getWorkflowTaskId() {
		return _workflowTaskId;
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

	private long _assigneeRoleId;
	private long _assigneeUserId;
	private boolean _asynchronous;
	private Date _completionDate;
	private Date _createDate;
	private String _description;
	private Date _dueDate;
	private String _name;
	private Map<String, Object> _optionalAttributes;
	private long _workflowDefinitionId;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;
	private long _workflowTaskId;

}