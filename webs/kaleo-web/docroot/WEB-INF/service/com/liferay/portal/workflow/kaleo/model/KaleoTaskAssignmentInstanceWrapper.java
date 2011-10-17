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
 * This class is a wrapper for {@link KaleoTaskAssignmentInstance}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentInstance
 * @generated
 */
public class KaleoTaskAssignmentInstanceWrapper
	implements KaleoTaskAssignmentInstance,
		ModelWrapper<KaleoTaskAssignmentInstance> {
	public KaleoTaskAssignmentInstanceWrapper(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		_kaleoTaskAssignmentInstance = kaleoTaskAssignmentInstance;
	}

	public Class<?> getModelClass() {
		return KaleoTaskAssignmentInstance.class;
	}

	public String getModelClassName() {
		return KaleoTaskAssignmentInstance.class.getName();
	}

	/**
	* Returns the primary key of this kaleo task assignment instance.
	*
	* @return the primary key of this kaleo task assignment instance
	*/
	public long getPrimaryKey() {
		return _kaleoTaskAssignmentInstance.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task assignment instance.
	*
	* @param primaryKey the primary key of this kaleo task assignment instance
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoTaskAssignmentInstance.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task assignment instance ID of this kaleo task assignment instance.
	*
	* @return the kaleo task assignment instance ID of this kaleo task assignment instance
	*/
	public long getKaleoTaskAssignmentInstanceId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId();
	}

	/**
	* Sets the kaleo task assignment instance ID of this kaleo task assignment instance.
	*
	* @param kaleoTaskAssignmentInstanceId the kaleo task assignment instance ID of this kaleo task assignment instance
	*/
	public void setKaleoTaskAssignmentInstanceId(
		long kaleoTaskAssignmentInstanceId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Returns the group ID of this kaleo task assignment instance.
	*
	* @return the group ID of this kaleo task assignment instance
	*/
	public long getGroupId() {
		return _kaleoTaskAssignmentInstance.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task assignment instance.
	*
	* @param groupId the group ID of this kaleo task assignment instance
	*/
	public void setGroupId(long groupId) {
		_kaleoTaskAssignmentInstance.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task assignment instance.
	*
	* @return the company ID of this kaleo task assignment instance
	*/
	public long getCompanyId() {
		return _kaleoTaskAssignmentInstance.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task assignment instance.
	*
	* @param companyId the company ID of this kaleo task assignment instance
	*/
	public void setCompanyId(long companyId) {
		_kaleoTaskAssignmentInstance.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task assignment instance.
	*
	* @return the user ID of this kaleo task assignment instance
	*/
	public long getUserId() {
		return _kaleoTaskAssignmentInstance.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task assignment instance.
	*
	* @param userId the user ID of this kaleo task assignment instance
	*/
	public void setUserId(long userId) {
		_kaleoTaskAssignmentInstance.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task assignment instance.
	*
	* @return the user uuid of this kaleo task assignment instance
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstance.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task assignment instance.
	*
	* @param userUuid the user uuid of this kaleo task assignment instance
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskAssignmentInstance.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo task assignment instance.
	*
	* @return the user name of this kaleo task assignment instance
	*/
	public java.lang.String getUserName() {
		return _kaleoTaskAssignmentInstance.getUserName();
	}

	/**
	* Sets the user name of this kaleo task assignment instance.
	*
	* @param userName the user name of this kaleo task assignment instance
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoTaskAssignmentInstance.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo task assignment instance.
	*
	* @return the create date of this kaleo task assignment instance
	*/
	public java.util.Date getCreateDate() {
		return _kaleoTaskAssignmentInstance.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task assignment instance.
	*
	* @param createDate the create date of this kaleo task assignment instance
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskAssignmentInstance.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo task assignment instance.
	*
	* @return the modified date of this kaleo task assignment instance
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoTaskAssignmentInstance.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task assignment instance.
	*
	* @param modifiedDate the modified date of this kaleo task assignment instance
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskAssignmentInstance.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo task assignment instance.
	*
	* @return the kaleo definition ID of this kaleo task assignment instance
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTaskAssignmentInstance.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task assignment instance.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task assignment instance
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskAssignmentInstance.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo instance ID of this kaleo task assignment instance.
	*
	* @return the kaleo instance ID of this kaleo task assignment instance
	*/
	public long getKaleoInstanceId() {
		return _kaleoTaskAssignmentInstance.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo task assignment instance.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo task assignment instance
	*/
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTaskAssignmentInstance.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance token ID of this kaleo task assignment instance.
	*
	* @return the kaleo instance token ID of this kaleo task assignment instance
	*/
	public long getKaleoInstanceTokenId() {
		return _kaleoTaskAssignmentInstance.getKaleoInstanceTokenId();
	}

	/**
	* Sets the kaleo instance token ID of this kaleo task assignment instance.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo task assignment instance
	*/
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTaskAssignmentInstance.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo task assignment instance.
	*
	* @return the kaleo task instance token ID of this kaleo task assignment instance
	*/
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId();
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo task assignment instance.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo task assignment instance
	*/
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns the kaleo task ID of this kaleo task assignment instance.
	*
	* @return the kaleo task ID of this kaleo task assignment instance
	*/
	public long getKaleoTaskId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskId();
	}

	/**
	* Sets the kaleo task ID of this kaleo task assignment instance.
	*
	* @param kaleoTaskId the kaleo task ID of this kaleo task assignment instance
	*/
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns the kaleo task name of this kaleo task assignment instance.
	*
	* @return the kaleo task name of this kaleo task assignment instance
	*/
	public java.lang.String getKaleoTaskName() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskName();
	}

	/**
	* Sets the kaleo task name of this kaleo task assignment instance.
	*
	* @param kaleoTaskName the kaleo task name of this kaleo task assignment instance
	*/
	public void setKaleoTaskName(java.lang.String kaleoTaskName) {
		_kaleoTaskAssignmentInstance.setKaleoTaskName(kaleoTaskName);
	}

	/**
	* Returns the assignee class name of this kaleo task assignment instance.
	*
	* @return the assignee class name of this kaleo task assignment instance
	*/
	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskAssignmentInstance.getAssigneeClassName();
	}

	/**
	* Sets the assignee class name of this kaleo task assignment instance.
	*
	* @param assigneeClassName the assignee class name of this kaleo task assignment instance
	*/
	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskAssignmentInstance.setAssigneeClassName(assigneeClassName);
	}

	/**
	* Returns the assignee class p k of this kaleo task assignment instance.
	*
	* @return the assignee class p k of this kaleo task assignment instance
	*/
	public long getAssigneeClassPK() {
		return _kaleoTaskAssignmentInstance.getAssigneeClassPK();
	}

	/**
	* Sets the assignee class p k of this kaleo task assignment instance.
	*
	* @param assigneeClassPK the assignee class p k of this kaleo task assignment instance
	*/
	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskAssignmentInstance.setAssigneeClassPK(assigneeClassPK);
	}

	/**
	* Returns the completed of this kaleo task assignment instance.
	*
	* @return the completed of this kaleo task assignment instance
	*/
	public boolean getCompleted() {
		return _kaleoTaskAssignmentInstance.getCompleted();
	}

	/**
	* Returns <code>true</code> if this kaleo task assignment instance is completed.
	*
	* @return <code>true</code> if this kaleo task assignment instance is completed; <code>false</code> otherwise
	*/
	public boolean isCompleted() {
		return _kaleoTaskAssignmentInstance.isCompleted();
	}

	/**
	* Sets whether this kaleo task assignment instance is completed.
	*
	* @param completed the completed of this kaleo task assignment instance
	*/
	public void setCompleted(boolean completed) {
		_kaleoTaskAssignmentInstance.setCompleted(completed);
	}

	/**
	* Returns the completion date of this kaleo task assignment instance.
	*
	* @return the completion date of this kaleo task assignment instance
	*/
	public java.util.Date getCompletionDate() {
		return _kaleoTaskAssignmentInstance.getCompletionDate();
	}

	/**
	* Sets the completion date of this kaleo task assignment instance.
	*
	* @param completionDate the completion date of this kaleo task assignment instance
	*/
	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoTaskAssignmentInstance.setCompletionDate(completionDate);
	}

	public boolean isNew() {
		return _kaleoTaskAssignmentInstance.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTaskAssignmentInstance.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTaskAssignmentInstance.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskAssignmentInstance.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTaskAssignmentInstance.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignmentInstance.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTaskAssignmentInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskAssignmentInstance.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskAssignmentInstance.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskAssignmentInstanceWrapper((KaleoTaskAssignmentInstance)_kaleoTaskAssignmentInstance.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		return _kaleoTaskAssignmentInstance.compareTo(kaleoTaskAssignmentInstance);
	}

	@Override
	public int hashCode() {
		return _kaleoTaskAssignmentInstance.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> toCacheModel() {
		return _kaleoTaskAssignmentInstance.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance toEscapedModel() {
		return new KaleoTaskAssignmentInstanceWrapper(_kaleoTaskAssignmentInstance.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTaskAssignmentInstance.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskAssignmentInstance.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstance.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoTaskAssignmentInstance getWrappedKaleoTaskAssignmentInstance() {
		return _kaleoTaskAssignmentInstance;
	}

	public KaleoTaskAssignmentInstance getWrappedModel() {
		return _kaleoTaskAssignmentInstance;
	}

	public void resetOriginalValues() {
		_kaleoTaskAssignmentInstance.resetOriginalValues();
	}

	private KaleoTaskAssignmentInstance _kaleoTaskAssignmentInstance;
}