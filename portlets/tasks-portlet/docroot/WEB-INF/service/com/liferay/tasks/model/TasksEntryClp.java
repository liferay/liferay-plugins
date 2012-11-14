/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.tasks.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.tasks.service.TasksEntryLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class TasksEntryClp extends BaseModelImpl<TasksEntry>
	implements TasksEntry {
	public TasksEntryClp() {
	}

	public Class<?> getModelClass() {
		return TasksEntry.class;
	}

	public String getModelClassName() {
		return TasksEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _tasksEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTasksEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_tasksEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("tasksEntryId", getTasksEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("priority", getPriority());
		attributes.put("assigneeUserId", getAssigneeUserId());
		attributes.put("resolverUserId", getResolverUserId());
		attributes.put("dueDate", getDueDate());
		attributes.put("finishDate", getFinishDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long tasksEntryId = (Long)attributes.get("tasksEntryId");

		if (tasksEntryId != null) {
			setTasksEntryId(tasksEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		Long assigneeUserId = (Long)attributes.get("assigneeUserId");

		if (assigneeUserId != null) {
			setAssigneeUserId(assigneeUserId);
		}

		Long resolverUserId = (Long)attributes.get("resolverUserId");

		if (resolverUserId != null) {
			setResolverUserId(resolverUserId);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		Date finishDate = (Date)attributes.get("finishDate");

		if (finishDate != null) {
			setFinishDate(finishDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getTasksEntryId() {
		return _tasksEntryId;
	}

	public void setTasksEntryId(long tasksEntryId) {
		_tasksEntryId = tasksEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_assigneeUserId = assigneeUserId;
	}

	public String getAssigneeUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getAssigneeUserId(), "uuid",
			_assigneeUserUuid);
	}

	public void setAssigneeUserUuid(String assigneeUserUuid) {
		_assigneeUserUuid = assigneeUserUuid;
	}

	public long getResolverUserId() {
		return _resolverUserId;
	}

	public void setResolverUserId(long resolverUserId) {
		_resolverUserId = resolverUserId;
	}

	public String getResolverUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getResolverUserId(), "uuid",
			_resolverUserUuid);
	}

	public void setResolverUserUuid(String resolverUserUuid) {
		_resolverUserUuid = resolverUserUuid;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public void setFinishDate(Date finishDate) {
		_finishDate = finishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public java.lang.String getReporterFullName() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getPriorityLabel() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getStatusLabel() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getAssigneeFullName() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getTasksEntryRemoteModel() {
		return _tasksEntryRemoteModel;
	}

	public void setTasksEntryRemoteModel(BaseModel<?> tasksEntryRemoteModel) {
		_tasksEntryRemoteModel = tasksEntryRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TasksEntryLocalServiceUtil.addTasksEntry(this);
		}
		else {
			TasksEntryLocalServiceUtil.updateTasksEntry(this);
		}
	}

	@Override
	public TasksEntry toEscapedModel() {
		return (TasksEntry)ProxyUtil.newProxyInstance(TasksEntry.class.getClassLoader(),
			new Class[] { TasksEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TasksEntryClp clone = new TasksEntryClp();

		clone.setTasksEntryId(getTasksEntryId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setPriority(getPriority());
		clone.setAssigneeUserId(getAssigneeUserId());
		clone.setResolverUserId(getResolverUserId());
		clone.setDueDate(getDueDate());
		clone.setFinishDate(getFinishDate());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(TasksEntry tasksEntry) {
		int value = 0;

		if (getPriority() < tasksEntry.getPriority()) {
			value = -1;
		}
		else if (getPriority() > tasksEntry.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(getDueDate(), tasksEntry.getDueDate());

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(getCreateDate(), tasksEntry.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TasksEntryClp tasksEntry = null;

		try {
			tasksEntry = (TasksEntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = tasksEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{tasksEntryId=");
		sb.append(getTasksEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append(", assigneeUserId=");
		sb.append(getAssigneeUserId());
		sb.append(", resolverUserId=");
		sb.append(getResolverUserId());
		sb.append(", dueDate=");
		sb.append(getDueDate());
		sb.append(", finishDate=");
		sb.append(getFinishDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.tasks.model.TasksEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>tasksEntryId</column-name><column-value><![CDATA[");
		sb.append(getTasksEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeUserId</column-name><column-value><![CDATA[");
		sb.append(getAssigneeUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resolverUserId</column-name><column-value><![CDATA[");
		sb.append(getResolverUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dueDate</column-name><column-value><![CDATA[");
		sb.append(getDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>finishDate</column-name><column-value><![CDATA[");
		sb.append(getFinishDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _tasksEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private int _priority;
	private long _assigneeUserId;
	private String _assigneeUserUuid;
	private long _resolverUserId;
	private String _resolverUserUuid;
	private Date _dueDate;
	private Date _finishDate;
	private int _status;
	private BaseModel<?> _tasksEntryRemoteModel;
}