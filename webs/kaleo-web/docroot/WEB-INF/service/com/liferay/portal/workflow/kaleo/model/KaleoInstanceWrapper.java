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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoInstance}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstance
 * @generated
 */
public class KaleoInstanceWrapper implements KaleoInstance,
	ModelWrapper<KaleoInstance> {
	public KaleoInstanceWrapper(KaleoInstance kaleoInstance) {
		_kaleoInstance = kaleoInstance;
	}

	public Class<?> getModelClass() {
		return KaleoInstance.class;
	}

	public String getModelClassName() {
		return KaleoInstance.class.getName();
	}

	/**
	* Returns the primary key of this kaleo instance.
	*
	* @return the primary key of this kaleo instance
	*/
	public long getPrimaryKey() {
		return _kaleoInstance.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo instance.
	*
	* @param primaryKey the primary key of this kaleo instance
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoInstance.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo instance ID of this kaleo instance.
	*
	* @return the kaleo instance ID of this kaleo instance
	*/
	public long getKaleoInstanceId() {
		return _kaleoInstance.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo instance.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo instance
	*/
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstance.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the group ID of this kaleo instance.
	*
	* @return the group ID of this kaleo instance
	*/
	public long getGroupId() {
		return _kaleoInstance.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo instance.
	*
	* @param groupId the group ID of this kaleo instance
	*/
	public void setGroupId(long groupId) {
		_kaleoInstance.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo instance.
	*
	* @return the company ID of this kaleo instance
	*/
	public long getCompanyId() {
		return _kaleoInstance.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo instance.
	*
	* @param companyId the company ID of this kaleo instance
	*/
	public void setCompanyId(long companyId) {
		_kaleoInstance.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo instance.
	*
	* @return the user ID of this kaleo instance
	*/
	public long getUserId() {
		return _kaleoInstance.getUserId();
	}

	/**
	* Sets the user ID of this kaleo instance.
	*
	* @param userId the user ID of this kaleo instance
	*/
	public void setUserId(long userId) {
		_kaleoInstance.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo instance.
	*
	* @return the user uuid of this kaleo instance
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo instance.
	*
	* @param userUuid the user uuid of this kaleo instance
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoInstance.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo instance.
	*
	* @return the user name of this kaleo instance
	*/
	public java.lang.String getUserName() {
		return _kaleoInstance.getUserName();
	}

	/**
	* Sets the user name of this kaleo instance.
	*
	* @param userName the user name of this kaleo instance
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoInstance.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo instance.
	*
	* @return the create date of this kaleo instance
	*/
	public java.util.Date getCreateDate() {
		return _kaleoInstance.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo instance.
	*
	* @param createDate the create date of this kaleo instance
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoInstance.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo instance.
	*
	* @return the modified date of this kaleo instance
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoInstance.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo instance.
	*
	* @param modifiedDate the modified date of this kaleo instance
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoInstance.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo instance.
	*
	* @return the kaleo definition ID of this kaleo instance
	*/
	public long getKaleoDefinitionId() {
		return _kaleoInstance.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo instance.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo instance
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoInstance.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo definition name of this kaleo instance.
	*
	* @return the kaleo definition name of this kaleo instance
	*/
	public java.lang.String getKaleoDefinitionName() {
		return _kaleoInstance.getKaleoDefinitionName();
	}

	/**
	* Sets the kaleo definition name of this kaleo instance.
	*
	* @param kaleoDefinitionName the kaleo definition name of this kaleo instance
	*/
	public void setKaleoDefinitionName(java.lang.String kaleoDefinitionName) {
		_kaleoInstance.setKaleoDefinitionName(kaleoDefinitionName);
	}

	/**
	* Returns the kaleo definition version of this kaleo instance.
	*
	* @return the kaleo definition version of this kaleo instance
	*/
	public int getKaleoDefinitionVersion() {
		return _kaleoInstance.getKaleoDefinitionVersion();
	}

	/**
	* Sets the kaleo definition version of this kaleo instance.
	*
	* @param kaleoDefinitionVersion the kaleo definition version of this kaleo instance
	*/
	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_kaleoInstance.setKaleoDefinitionVersion(kaleoDefinitionVersion);
	}

	/**
	* Returns the root kaleo instance token ID of this kaleo instance.
	*
	* @return the root kaleo instance token ID of this kaleo instance
	*/
	public long getRootKaleoInstanceTokenId() {
		return _kaleoInstance.getRootKaleoInstanceTokenId();
	}

	/**
	* Sets the root kaleo instance token ID of this kaleo instance.
	*
	* @param rootKaleoInstanceTokenId the root kaleo instance token ID of this kaleo instance
	*/
	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_kaleoInstance.setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);
	}

	/**
	* Returns the class name of this kaleo instance.
	*
	* @return the class name of this kaleo instance
	*/
	public java.lang.String getClassName() {
		return _kaleoInstance.getClassName();
	}

	/**
	* Sets the class name of this kaleo instance.
	*
	* @param className the class name of this kaleo instance
	*/
	public void setClassName(java.lang.String className) {
		_kaleoInstance.setClassName(className);
	}

	/**
	* Returns the class p k of this kaleo instance.
	*
	* @return the class p k of this kaleo instance
	*/
	public long getClassPK() {
		return _kaleoInstance.getClassPK();
	}

	/**
	* Sets the class p k of this kaleo instance.
	*
	* @param classPK the class p k of this kaleo instance
	*/
	public void setClassPK(long classPK) {
		_kaleoInstance.setClassPK(classPK);
	}

	/**
	* Returns the completed of this kaleo instance.
	*
	* @return the completed of this kaleo instance
	*/
	public boolean getCompleted() {
		return _kaleoInstance.getCompleted();
	}

	/**
	* Returns <code>true</code> if this kaleo instance is completed.
	*
	* @return <code>true</code> if this kaleo instance is completed; <code>false</code> otherwise
	*/
	public boolean isCompleted() {
		return _kaleoInstance.isCompleted();
	}

	/**
	* Sets whether this kaleo instance is completed.
	*
	* @param completed the completed of this kaleo instance
	*/
	public void setCompleted(boolean completed) {
		_kaleoInstance.setCompleted(completed);
	}

	/**
	* Returns the completion date of this kaleo instance.
	*
	* @return the completion date of this kaleo instance
	*/
	public java.util.Date getCompletionDate() {
		return _kaleoInstance.getCompletionDate();
	}

	/**
	* Sets the completion date of this kaleo instance.
	*
	* @param completionDate the completion date of this kaleo instance
	*/
	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoInstance.setCompletionDate(completionDate);
	}

	/**
	* Returns the workflow context of this kaleo instance.
	*
	* @return the workflow context of this kaleo instance
	*/
	public java.lang.String getWorkflowContext() {
		return _kaleoInstance.getWorkflowContext();
	}

	/**
	* Sets the workflow context of this kaleo instance.
	*
	* @param workflowContext the workflow context of this kaleo instance
	*/
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoInstance.setWorkflowContext(workflowContext);
	}

	public boolean isNew() {
		return _kaleoInstance.isNew();
	}

	public void setNew(boolean n) {
		_kaleoInstance.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoInstance.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoInstance.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoInstance.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoInstance.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoInstance.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoInstance.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoInstanceWrapper((KaleoInstance)_kaleoInstance.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		return _kaleoInstance.compareTo(kaleoInstance);
	}

	@Override
	public int hashCode() {
		return _kaleoInstance.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoInstance> toCacheModel() {
		return _kaleoInstance.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance toEscapedModel() {
		return new KaleoInstanceWrapper(_kaleoInstance.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoInstance.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoInstance.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstance.persist();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition getKaleoDefinition()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getKaleoDefinition();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getRootKaleoInstanceToken(workflowContext,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getRootKaleoInstanceToken(serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoInstance getWrappedKaleoInstance() {
		return _kaleoInstance;
	}

	public KaleoInstance getWrappedModel() {
		return _kaleoInstance;
	}

	public void resetOriginalValues() {
		_kaleoInstance.resetOriginalValues();
	}

	private KaleoInstance _kaleoInstance;
}