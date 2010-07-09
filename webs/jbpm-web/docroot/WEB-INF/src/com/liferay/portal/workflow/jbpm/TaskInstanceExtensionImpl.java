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

package com.liferay.portal.workflow.jbpm;

import java.util.List;

import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author Marcellus Tavares
 */
public class TaskInstanceExtensionImpl {

	public TaskInstanceExtensionImpl() {
	}

	public TaskInstanceExtensionImpl(
		long companyId, long groupId, long userId, List<Assignee> assignees,
		String workflowContext, TaskInstance taskInstance) {

		_companyId = companyId;
		_groupId = groupId;
		_userId = userId;
		_assignees = assignees;
		_workflowContext = workflowContext;
		_taskInstance = taskInstance;
	}

	public List<Assignee> getAssignees() {
		return _assignees;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public TaskInstance getTaskInstance() {
		return _taskInstance;
	}

	public long getTaskInstanceExtensionId() {
		return _taskInstanceExtensionId;
	}

	public long getUserId() {
		return _userId;
	}

	public String getWorkflowContext() {
		return _workflowContext;
	}

	public void setAssignees(List<Assignee> assignees) {
		_assignees = assignees;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		_taskInstance = taskInstance;
	}

	public void setTaskInstanceExtensionId(long taskInstanceExtensionId) {
		_taskInstanceExtensionId = taskInstanceExtensionId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	private List<Assignee> _assignees;
	private long _companyId;
	private long _groupId;
	private TaskInstance _taskInstance;
	private long _taskInstanceExtensionId;
	private long _userId;
	private String _workflowContext;

}