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

/**
 * @author Marcellus Tavares
 */
public class Assignee {

	public Assignee() {
	}

	public Assignee(
		long companyId, long groupId, String assigneeClassName,
		long assigneeClassPK) {

		_companyId = companyId;
		_groupId = groupId;
		_assigneeClassName = assigneeClassName;
		_assigneeClassPK = assigneeClassPK;
	}

	public String getAssigneeClassName() {
		return _assigneeClassName;
	}

	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	public long getAssigneeId() {
		return _assigneeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getTaskInstanceExtensionId() {
		return _taskInstanceExtensionId;
	}

	public void setAssigneeClassName(String assigneeClassName) {
		_assigneeClassName = assigneeClassName;
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;
	}

	public void setAssigneeId(long assigneeId) {
		_assigneeId = assigneeId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setTaskInstanceExtensionId(long taskInstanceExtensionId) {
		_taskInstanceExtensionId = taskInstanceExtensionId;
	}

	private long _assigneeId;
	private String _assigneeClassName;
	private long _assigneeClassPK;
	private long _companyId;
	private long _groupId;
	private long _taskInstanceExtensionId;

}