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

package com.liferay.portal.workflow.kaleo.model;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTask}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTask
 * @generated
 */
public class KaleoTaskWrapper implements KaleoTask {
	public KaleoTaskWrapper(KaleoTask kaleoTask) {
		_kaleoTask = kaleoTask;
	}

	/**
	* Gets the primary key of this kaleo task.
	*
	* @return the primary key of this kaleo task
	*/
	public long getPrimaryKey() {
		return _kaleoTask.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task
	*
	* @param pk the primary key of this kaleo task
	*/
	public void setPrimaryKey(long pk) {
		_kaleoTask.setPrimaryKey(pk);
	}

	/**
	* Gets the kaleo task id of this kaleo task.
	*
	* @return the kaleo task id of this kaleo task
	*/
	public long getKaleoTaskId() {
		return _kaleoTask.getKaleoTaskId();
	}

	/**
	* Sets the kaleo task id of this kaleo task.
	*
	* @param kaleoTaskId the kaleo task id of this kaleo task
	*/
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTask.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Gets the group id of this kaleo task.
	*
	* @return the group id of this kaleo task
	*/
	public long getGroupId() {
		return _kaleoTask.getGroupId();
	}

	/**
	* Sets the group id of this kaleo task.
	*
	* @param groupId the group id of this kaleo task
	*/
	public void setGroupId(long groupId) {
		_kaleoTask.setGroupId(groupId);
	}

	/**
	* Gets the company id of this kaleo task.
	*
	* @return the company id of this kaleo task
	*/
	public long getCompanyId() {
		return _kaleoTask.getCompanyId();
	}

	/**
	* Sets the company id of this kaleo task.
	*
	* @param companyId the company id of this kaleo task
	*/
	public void setCompanyId(long companyId) {
		_kaleoTask.setCompanyId(companyId);
	}

	/**
	* Gets the user id of this kaleo task.
	*
	* @return the user id of this kaleo task
	*/
	public long getUserId() {
		return _kaleoTask.getUserId();
	}

	/**
	* Sets the user id of this kaleo task.
	*
	* @param userId the user id of this kaleo task
	*/
	public void setUserId(long userId) {
		_kaleoTask.setUserId(userId);
	}

	/**
	* Gets the user uuid of this kaleo task.
	*
	* @return the user uuid of this kaleo task
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task.
	*
	* @param userUuid the user uuid of this kaleo task
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTask.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this kaleo task.
	*
	* @return the user name of this kaleo task
	*/
	public java.lang.String getUserName() {
		return _kaleoTask.getUserName();
	}

	/**
	* Sets the user name of this kaleo task.
	*
	* @param userName the user name of this kaleo task
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoTask.setUserName(userName);
	}

	/**
	* Gets the create date of this kaleo task.
	*
	* @return the create date of this kaleo task
	*/
	public java.util.Date getCreateDate() {
		return _kaleoTask.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task.
	*
	* @param createDate the create date of this kaleo task
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTask.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this kaleo task.
	*
	* @return the modified date of this kaleo task
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoTask.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task.
	*
	* @param modifiedDate the modified date of this kaleo task
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTask.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the kaleo definition id of this kaleo task.
	*
	* @return the kaleo definition id of this kaleo task
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTask.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition id of this kaleo task.
	*
	* @param kaleoDefinitionId the kaleo definition id of this kaleo task
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTask.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Gets the kaleo node id of this kaleo task.
	*
	* @return the kaleo node id of this kaleo task
	*/
	public long getKaleoNodeId() {
		return _kaleoTask.getKaleoNodeId();
	}

	/**
	* Sets the kaleo node id of this kaleo task.
	*
	* @param kaleoNodeId the kaleo node id of this kaleo task
	*/
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTask.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Gets the name of this kaleo task.
	*
	* @return the name of this kaleo task
	*/
	public java.lang.String getName() {
		return _kaleoTask.getName();
	}

	/**
	* Sets the name of this kaleo task.
	*
	* @param name the name of this kaleo task
	*/
	public void setName(java.lang.String name) {
		_kaleoTask.setName(name);
	}

	/**
	* Gets the description of this kaleo task.
	*
	* @return the description of this kaleo task
	*/
	public java.lang.String getDescription() {
		return _kaleoTask.getDescription();
	}

	/**
	* Sets the description of this kaleo task.
	*
	* @param description the description of this kaleo task
	*/
	public void setDescription(java.lang.String description) {
		_kaleoTask.setDescription(description);
	}

	/**
	* Gets the due date duration of this kaleo task.
	*
	* @return the due date duration of this kaleo task
	*/
	public double getDueDateDuration() {
		return _kaleoTask.getDueDateDuration();
	}

	/**
	* Sets the due date duration of this kaleo task.
	*
	* @param dueDateDuration the due date duration of this kaleo task
	*/
	public void setDueDateDuration(double dueDateDuration) {
		_kaleoTask.setDueDateDuration(dueDateDuration);
	}

	/**
	* Gets the due date scale of this kaleo task.
	*
	* @return the due date scale of this kaleo task
	*/
	public java.lang.String getDueDateScale() {
		return _kaleoTask.getDueDateScale();
	}

	/**
	* Sets the due date scale of this kaleo task.
	*
	* @param dueDateScale the due date scale of this kaleo task
	*/
	public void setDueDateScale(java.lang.String dueDateScale) {
		_kaleoTask.setDueDateScale(dueDateScale);
	}

	public boolean isNew() {
		return _kaleoTask.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTask.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTask.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTask.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTask.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTask.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTask.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTask.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTask.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTask.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask) {
		return _kaleoTask.compareTo(kaleoTask);
	}

	public int hashCode() {
		return _kaleoTask.hashCode();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask toEscapedModel() {
		return _kaleoTask.toEscapedModel();
	}

	public java.lang.String toString() {
		return _kaleoTask.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTask.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getKaleoNode();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getKaleoTaskAssignments();
	}

	public KaleoTask getWrappedKaleoTask() {
		return _kaleoTask;
	}

	private KaleoTask _kaleoTask;
}