/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public Class<?> getModelClass() {
		return KaleoTask.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskId", getKaleoTaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskId = (Long)attributes.get("kaleoTaskId");

		if (kaleoTaskId != null) {
			setKaleoTaskId(kaleoTaskId);
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

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	* Returns the primary key of this kaleo task.
	*
	* @return the primary key of this kaleo task
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoTask.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task.
	*
	* @param primaryKey the primary key of this kaleo task
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoTask.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task ID of this kaleo task.
	*
	* @return the kaleo task ID of this kaleo task
	*/
	@Override
	public long getKaleoTaskId() {
		return _kaleoTask.getKaleoTaskId();
	}

	/**
	* Sets the kaleo task ID of this kaleo task.
	*
	* @param kaleoTaskId the kaleo task ID of this kaleo task
	*/
	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTask.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns the group ID of this kaleo task.
	*
	* @return the group ID of this kaleo task
	*/
	@Override
	public long getGroupId() {
		return _kaleoTask.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task.
	*
	* @param groupId the group ID of this kaleo task
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoTask.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task.
	*
	* @return the company ID of this kaleo task
	*/
	@Override
	public long getCompanyId() {
		return _kaleoTask.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task.
	*
	* @param companyId the company ID of this kaleo task
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoTask.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task.
	*
	* @return the user ID of this kaleo task
	*/
	@Override
	public long getUserId() {
		return _kaleoTask.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task.
	*
	* @param userId the user ID of this kaleo task
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoTask.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task.
	*
	* @return the user uuid of this kaleo task
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task.
	*
	* @param userUuid the user uuid of this kaleo task
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTask.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo task.
	*
	* @return the user name of this kaleo task
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoTask.getUserName();
	}

	/**
	* Sets the user name of this kaleo task.
	*
	* @param userName the user name of this kaleo task
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoTask.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo task.
	*
	* @return the create date of this kaleo task
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoTask.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task.
	*
	* @param createDate the create date of this kaleo task
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTask.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo task.
	*
	* @return the modified date of this kaleo task
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoTask.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task.
	*
	* @param modifiedDate the modified date of this kaleo task
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTask.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo task.
	*
	* @return the kaleo definition ID of this kaleo task
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoTask.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTask.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo node ID of this kaleo task.
	*
	* @return the kaleo node ID of this kaleo task
	*/
	@Override
	public long getKaleoNodeId() {
		return _kaleoTask.getKaleoNodeId();
	}

	/**
	* Sets the kaleo node ID of this kaleo task.
	*
	* @param kaleoNodeId the kaleo node ID of this kaleo task
	*/
	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTask.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the name of this kaleo task.
	*
	* @return the name of this kaleo task
	*/
	@Override
	public java.lang.String getName() {
		return _kaleoTask.getName();
	}

	/**
	* Sets the name of this kaleo task.
	*
	* @param name the name of this kaleo task
	*/
	@Override
	public void setName(java.lang.String name) {
		_kaleoTask.setName(name);
	}

	/**
	* Returns the description of this kaleo task.
	*
	* @return the description of this kaleo task
	*/
	@Override
	public java.lang.String getDescription() {
		return _kaleoTask.getDescription();
	}

	/**
	* Sets the description of this kaleo task.
	*
	* @param description the description of this kaleo task
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kaleoTask.setDescription(description);
	}

	@Override
	public boolean isNew() {
		return _kaleoTask.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoTask.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoTask.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoTask.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoTask.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTask.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTask.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTask.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoTask.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoTask.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTask.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskWrapper((KaleoTask)_kaleoTask.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask) {
		return _kaleoTask.compareTo(kaleoTask);
	}

	@Override
	public int hashCode() {
		return _kaleoTask.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTask> toCacheModel() {
		return _kaleoTask.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTask toEscapedModel() {
		return new KaleoTaskWrapper(_kaleoTask.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTask toUnescapedModel() {
		return new KaleoTaskWrapper(_kaleoTask.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTask.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoTask.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTask.persist();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getKaleoNode();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getKaleoTaskAssignments();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KaleoTask getWrappedKaleoTask() {
		return _kaleoTask;
	}

	@Override
	public KaleoTask getWrappedModel() {
		return _kaleoTask;
	}

	@Override
	public void resetOriginalValues() {
		_kaleoTask.resetOriginalValues();
	}

	private KaleoTask _kaleoTask;
}