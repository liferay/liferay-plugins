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

package com.liferay.portal.workflow.edoras;

import com.liferay.portal.kernel.workflow.TaskInstanceInfo;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.edorasframework.process.workflow.api.WorkflowTask;

/**
 * <a href="TaskInstanceInfoImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class TaskInstanceInfoImpl implements TaskInstanceInfo {

	public TaskInstanceInfoImpl() {

	}

	public TaskInstanceInfoImpl(WorkflowTask workflowTask) {
		_assignRoleId =
			WorkflowManagerUtil.getPrimaryKey(workflowTask.getAssignedRoleId());
		_assignUserId =
			WorkflowManagerUtil.getPrimaryKey(workflowTask.getAssignedUserId());
		_asynchronous = workflowTask.getMetaData().isAsync();
		_attributes = Collections.emptyMap();
		_completionDate = workflowTask.getCompletionDate();
		_createDate = workflowTask.getCreationDate();
		_description = workflowTask.getMetaData().getDescription();
		_dueDate = workflowTask.getDueDate();
		_taskInstanceId = workflowTask.getPrimaryKey();
		_taskMetaId = workflowTask.getMetaData().getName();
		_taskName = workflowTask.getMetaData().getLabel();
		_workflowDefinitionName = workflowTask.getProcessModelId();
		_workflowDefinitionVersion = workflowTask.getProcessModelVersion();
		_workflowInstanceId = workflowTask.getProcessInstance().getPrimaryKey();
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
		return _taskMetaId;
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

	private long _assignRoleId;
	private long _assignUserId;
	private boolean _asynchronous;
	private Map<String, Object> _attributes;
	private Date _completionDate;
	private Date _createDate;
	private String _description;
	private Date _dueDate;
	private long _taskInstanceId;
	private String _taskMetaId;
	private String _taskName;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;

}