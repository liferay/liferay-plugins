/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TasksEntry}.
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntry
 * @generated
 */
public class TasksEntryWrapper implements TasksEntry, ModelWrapper<TasksEntry> {
	public TasksEntryWrapper(TasksEntry tasksEntry) {
		_tasksEntry = tasksEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return TasksEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TasksEntry.class.getName();
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

	/**
	* Returns the primary key of this tasks entry.
	*
	* @return the primary key of this tasks entry
	*/
	@Override
	public long getPrimaryKey() {
		return _tasksEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this tasks entry.
	*
	* @param primaryKey the primary key of this tasks entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_tasksEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the tasks entry ID of this tasks entry.
	*
	* @return the tasks entry ID of this tasks entry
	*/
	@Override
	public long getTasksEntryId() {
		return _tasksEntry.getTasksEntryId();
	}

	/**
	* Sets the tasks entry ID of this tasks entry.
	*
	* @param tasksEntryId the tasks entry ID of this tasks entry
	*/
	@Override
	public void setTasksEntryId(long tasksEntryId) {
		_tasksEntry.setTasksEntryId(tasksEntryId);
	}

	/**
	* Returns the group ID of this tasks entry.
	*
	* @return the group ID of this tasks entry
	*/
	@Override
	public long getGroupId() {
		return _tasksEntry.getGroupId();
	}

	/**
	* Sets the group ID of this tasks entry.
	*
	* @param groupId the group ID of this tasks entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_tasksEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this tasks entry.
	*
	* @return the company ID of this tasks entry
	*/
	@Override
	public long getCompanyId() {
		return _tasksEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this tasks entry.
	*
	* @param companyId the company ID of this tasks entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_tasksEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this tasks entry.
	*
	* @return the user ID of this tasks entry
	*/
	@Override
	public long getUserId() {
		return _tasksEntry.getUserId();
	}

	/**
	* Sets the user ID of this tasks entry.
	*
	* @param userId the user ID of this tasks entry
	*/
	@Override
	public void setUserId(long userId) {
		_tasksEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this tasks entry.
	*
	* @return the user uuid of this tasks entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this tasks entry.
	*
	* @param userUuid the user uuid of this tasks entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_tasksEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this tasks entry.
	*
	* @return the user name of this tasks entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _tasksEntry.getUserName();
	}

	/**
	* Sets the user name of this tasks entry.
	*
	* @param userName the user name of this tasks entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_tasksEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this tasks entry.
	*
	* @return the create date of this tasks entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _tasksEntry.getCreateDate();
	}

	/**
	* Sets the create date of this tasks entry.
	*
	* @param createDate the create date of this tasks entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_tasksEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this tasks entry.
	*
	* @return the modified date of this tasks entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _tasksEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this tasks entry.
	*
	* @param modifiedDate the modified date of this tasks entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_tasksEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the title of this tasks entry.
	*
	* @return the title of this tasks entry
	*/
	@Override
	public java.lang.String getTitle() {
		return _tasksEntry.getTitle();
	}

	/**
	* Sets the title of this tasks entry.
	*
	* @param title the title of this tasks entry
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_tasksEntry.setTitle(title);
	}

	/**
	* Returns the priority of this tasks entry.
	*
	* @return the priority of this tasks entry
	*/
	@Override
	public int getPriority() {
		return _tasksEntry.getPriority();
	}

	/**
	* Sets the priority of this tasks entry.
	*
	* @param priority the priority of this tasks entry
	*/
	@Override
	public void setPriority(int priority) {
		_tasksEntry.setPriority(priority);
	}

	/**
	* Returns the assignee user ID of this tasks entry.
	*
	* @return the assignee user ID of this tasks entry
	*/
	@Override
	public long getAssigneeUserId() {
		return _tasksEntry.getAssigneeUserId();
	}

	/**
	* Sets the assignee user ID of this tasks entry.
	*
	* @param assigneeUserId the assignee user ID of this tasks entry
	*/
	@Override
	public void setAssigneeUserId(long assigneeUserId) {
		_tasksEntry.setAssigneeUserId(assigneeUserId);
	}

	/**
	* Returns the assignee user uuid of this tasks entry.
	*
	* @return the assignee user uuid of this tasks entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getAssigneeUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntry.getAssigneeUserUuid();
	}

	/**
	* Sets the assignee user uuid of this tasks entry.
	*
	* @param assigneeUserUuid the assignee user uuid of this tasks entry
	*/
	@Override
	public void setAssigneeUserUuid(java.lang.String assigneeUserUuid) {
		_tasksEntry.setAssigneeUserUuid(assigneeUserUuid);
	}

	/**
	* Returns the resolver user ID of this tasks entry.
	*
	* @return the resolver user ID of this tasks entry
	*/
	@Override
	public long getResolverUserId() {
		return _tasksEntry.getResolverUserId();
	}

	/**
	* Sets the resolver user ID of this tasks entry.
	*
	* @param resolverUserId the resolver user ID of this tasks entry
	*/
	@Override
	public void setResolverUserId(long resolverUserId) {
		_tasksEntry.setResolverUserId(resolverUserId);
	}

	/**
	* Returns the resolver user uuid of this tasks entry.
	*
	* @return the resolver user uuid of this tasks entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getResolverUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntry.getResolverUserUuid();
	}

	/**
	* Sets the resolver user uuid of this tasks entry.
	*
	* @param resolverUserUuid the resolver user uuid of this tasks entry
	*/
	@Override
	public void setResolverUserUuid(java.lang.String resolverUserUuid) {
		_tasksEntry.setResolverUserUuid(resolverUserUuid);
	}

	/**
	* Returns the due date of this tasks entry.
	*
	* @return the due date of this tasks entry
	*/
	@Override
	public java.util.Date getDueDate() {
		return _tasksEntry.getDueDate();
	}

	/**
	* Sets the due date of this tasks entry.
	*
	* @param dueDate the due date of this tasks entry
	*/
	@Override
	public void setDueDate(java.util.Date dueDate) {
		_tasksEntry.setDueDate(dueDate);
	}

	/**
	* Returns the finish date of this tasks entry.
	*
	* @return the finish date of this tasks entry
	*/
	@Override
	public java.util.Date getFinishDate() {
		return _tasksEntry.getFinishDate();
	}

	/**
	* Sets the finish date of this tasks entry.
	*
	* @param finishDate the finish date of this tasks entry
	*/
	@Override
	public void setFinishDate(java.util.Date finishDate) {
		_tasksEntry.setFinishDate(finishDate);
	}

	/**
	* Returns the status of this tasks entry.
	*
	* @return the status of this tasks entry
	*/
	@Override
	public int getStatus() {
		return _tasksEntry.getStatus();
	}

	/**
	* Sets the status of this tasks entry.
	*
	* @param status the status of this tasks entry
	*/
	@Override
	public void setStatus(int status) {
		_tasksEntry.setStatus(status);
	}

	@Override
	public boolean isNew() {
		return _tasksEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_tasksEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _tasksEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_tasksEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _tasksEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _tasksEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_tasksEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _tasksEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_tasksEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_tasksEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_tasksEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TasksEntryWrapper((TasksEntry)_tasksEntry.clone());
	}

	@Override
	public int compareTo(com.liferay.tasks.model.TasksEntry tasksEntry) {
		return _tasksEntry.compareTo(tasksEntry);
	}

	@Override
	public int hashCode() {
		return _tasksEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.tasks.model.TasksEntry> toCacheModel() {
		return _tasksEntry.toCacheModel();
	}

	@Override
	public com.liferay.tasks.model.TasksEntry toEscapedModel() {
		return new TasksEntryWrapper(_tasksEntry.toEscapedModel());
	}

	@Override
	public com.liferay.tasks.model.TasksEntry toUnescapedModel() {
		return new TasksEntryWrapper(_tasksEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _tasksEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _tasksEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_tasksEntry.persist();
	}

	@Override
	public java.lang.String getAssigneeFullName() {
		return _tasksEntry.getAssigneeFullName();
	}

	@Override
	public java.lang.String getPriorityLabel() {
		return _tasksEntry.getPriorityLabel();
	}

	@Override
	public java.lang.String getReporterFullName() {
		return _tasksEntry.getReporterFullName();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _tasksEntry.getStatusLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TasksEntryWrapper)) {
			return false;
		}

		TasksEntryWrapper tasksEntryWrapper = (TasksEntryWrapper)obj;

		if (Validator.equals(_tasksEntry, tasksEntryWrapper._tasksEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public TasksEntry getWrappedTasksEntry() {
		return _tasksEntry;
	}

	@Override
	public TasksEntry getWrappedModel() {
		return _tasksEntry;
	}

	@Override
	public void resetOriginalValues() {
		_tasksEntry.resetOriginalValues();
	}

	private TasksEntry _tasksEntry;
}