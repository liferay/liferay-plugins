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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTask}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTask
 * @generated
 */
public class KaleoTaskWrapper implements KaleoTask, ModelWrapper<KaleoTask> {
	public KaleoTaskWrapper(KaleoTask kaleoTask) {
		_kaleoTask = kaleoTask;
	}

	public Class<?> getModelClass() {
		return KaleoTask.class;
	}

	public String getModelClassName() {
		return KaleoTask.class.getName();
	}

	/**
	* Returns the primary key of this kaleo task.
	*
	* @return the primary key of this kaleo task
	*/
	public long getPrimaryKey() {
		return _kaleoTask.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task.
	*
	* @param primaryKey the primary key of this kaleo task
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoTask.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task ID of this kaleo task.
	*
	* @return the kaleo task ID of this kaleo task
	*/
	public long getKaleoTaskId() {
		return _kaleoTask.getKaleoTaskId();
	}

	/**
	* Sets the kaleo task ID of this kaleo task.
	*
	* @param kaleoTaskId the kaleo task ID of this kaleo task
	*/
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTask.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns the group ID of this kaleo task.
	*
	* @return the group ID of this kaleo task
	*/
	public long getGroupId() {
		return _kaleoTask.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task.
	*
	* @param groupId the group ID of this kaleo task
	*/
	public void setGroupId(long groupId) {
		_kaleoTask.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task.
	*
	* @return the company ID of this kaleo task
	*/
	public long getCompanyId() {
		return _kaleoTask.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task.
	*
	* @param companyId the company ID of this kaleo task
	*/
	public void setCompanyId(long companyId) {
		_kaleoTask.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task.
	*
	* @return the user ID of this kaleo task
	*/
	public long getUserId() {
		return _kaleoTask.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task.
	*
	* @param userId the user ID of this kaleo task
	*/
	public void setUserId(long userId) {
		_kaleoTask.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task.
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
	* Returns the user name of this kaleo task.
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
	* Returns the create date of this kaleo task.
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
	* Returns the modified date of this kaleo task.
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
	* Returns the kaleo definition ID of this kaleo task.
	*
	* @return the kaleo definition ID of this kaleo task
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTask.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTask.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo node ID of this kaleo task.
	*
	* @return the kaleo node ID of this kaleo task
	*/
	public long getKaleoNodeId() {
		return _kaleoTask.getKaleoNodeId();
	}

	/**
	* Sets the kaleo node ID of this kaleo task.
	*
	* @param kaleoNodeId the kaleo node ID of this kaleo task
	*/
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTask.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the name of this kaleo task.
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
	* Returns the description of this kaleo task.
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTask.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTask.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTask.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTask.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskWrapper((KaleoTask)_kaleoTask.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask) {
		return _kaleoTask.compareTo(kaleoTask);
	}

	@Override
	public int hashCode() {
		return _kaleoTask.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTask> toCacheModel() {
		return _kaleoTask.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask toEscapedModel() {
		return new KaleoTaskWrapper(_kaleoTask.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTask.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTask.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTask.persist();
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

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoTask getWrappedKaleoTask() {
		return _kaleoTask;
	}

	public KaleoTask getWrappedModel() {
		return _kaleoTask;
	}

	public void resetOriginalValues() {
		_kaleoTask.resetOriginalValues();
	}

	private KaleoTask _kaleoTask;
}