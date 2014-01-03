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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoInstance}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstance
 * @generated
 */
public class KaleoInstanceWrapper implements KaleoInstance,
	ModelWrapper<KaleoInstance> {
	public KaleoInstanceWrapper(KaleoInstance kaleoInstance) {
		_kaleoInstance = kaleoInstance;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoInstance.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoDefinitionName", getKaleoDefinitionName());
		attributes.put("kaleoDefinitionVersion", getKaleoDefinitionVersion());
		attributes.put("rootKaleoInstanceTokenId", getRootKaleoInstanceTokenId());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());
		attributes.put("workflowContext", getWorkflowContext());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoInstanceId = (Long)attributes.get("kaleoInstanceId");

		if (kaleoInstanceId != null) {
			setKaleoInstanceId(kaleoInstanceId);
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

		String kaleoDefinitionName = (String)attributes.get(
				"kaleoDefinitionName");

		if (kaleoDefinitionName != null) {
			setKaleoDefinitionName(kaleoDefinitionName);
		}

		Integer kaleoDefinitionVersion = (Integer)attributes.get(
				"kaleoDefinitionVersion");

		if (kaleoDefinitionVersion != null) {
			setKaleoDefinitionVersion(kaleoDefinitionVersion);
		}

		Long rootKaleoInstanceTokenId = (Long)attributes.get(
				"rootKaleoInstanceTokenId");

		if (rootKaleoInstanceTokenId != null) {
			setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Boolean completed = (Boolean)attributes.get("completed");

		if (completed != null) {
			setCompleted(completed);
		}

		Date completionDate = (Date)attributes.get("completionDate");

		if (completionDate != null) {
			setCompletionDate(completionDate);
		}

		String workflowContext = (String)attributes.get("workflowContext");

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}
	}

	/**
	* Returns the primary key of this kaleo instance.
	*
	* @return the primary key of this kaleo instance
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoInstance.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo instance.
	*
	* @param primaryKey the primary key of this kaleo instance
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoInstance.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo instance ID of this kaleo instance.
	*
	* @return the kaleo instance ID of this kaleo instance
	*/
	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstance.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo instance.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo instance
	*/
	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstance.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the group ID of this kaleo instance.
	*
	* @return the group ID of this kaleo instance
	*/
	@Override
	public long getGroupId() {
		return _kaleoInstance.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo instance.
	*
	* @param groupId the group ID of this kaleo instance
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoInstance.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo instance.
	*
	* @return the company ID of this kaleo instance
	*/
	@Override
	public long getCompanyId() {
		return _kaleoInstance.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo instance.
	*
	* @param companyId the company ID of this kaleo instance
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoInstance.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo instance.
	*
	* @return the user ID of this kaleo instance
	*/
	@Override
	public long getUserId() {
		return _kaleoInstance.getUserId();
	}

	/**
	* Sets the user ID of this kaleo instance.
	*
	* @param userId the user ID of this kaleo instance
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoInstance.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo instance.
	*
	* @return the user uuid of this kaleo instance
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo instance.
	*
	* @param userUuid the user uuid of this kaleo instance
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoInstance.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo instance.
	*
	* @return the user name of this kaleo instance
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoInstance.getUserName();
	}

	/**
	* Sets the user name of this kaleo instance.
	*
	* @param userName the user name of this kaleo instance
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoInstance.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo instance.
	*
	* @return the create date of this kaleo instance
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoInstance.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo instance.
	*
	* @param createDate the create date of this kaleo instance
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoInstance.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo instance.
	*
	* @return the modified date of this kaleo instance
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoInstance.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo instance.
	*
	* @param modifiedDate the modified date of this kaleo instance
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoInstance.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo instance.
	*
	* @return the kaleo definition ID of this kaleo instance
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoInstance.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo instance.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo instance
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoInstance.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo definition name of this kaleo instance.
	*
	* @return the kaleo definition name of this kaleo instance
	*/
	@Override
	public java.lang.String getKaleoDefinitionName() {
		return _kaleoInstance.getKaleoDefinitionName();
	}

	/**
	* Sets the kaleo definition name of this kaleo instance.
	*
	* @param kaleoDefinitionName the kaleo definition name of this kaleo instance
	*/
	@Override
	public void setKaleoDefinitionName(java.lang.String kaleoDefinitionName) {
		_kaleoInstance.setKaleoDefinitionName(kaleoDefinitionName);
	}

	/**
	* Returns the kaleo definition version of this kaleo instance.
	*
	* @return the kaleo definition version of this kaleo instance
	*/
	@Override
	public int getKaleoDefinitionVersion() {
		return _kaleoInstance.getKaleoDefinitionVersion();
	}

	/**
	* Sets the kaleo definition version of this kaleo instance.
	*
	* @param kaleoDefinitionVersion the kaleo definition version of this kaleo instance
	*/
	@Override
	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_kaleoInstance.setKaleoDefinitionVersion(kaleoDefinitionVersion);
	}

	/**
	* Returns the root kaleo instance token ID of this kaleo instance.
	*
	* @return the root kaleo instance token ID of this kaleo instance
	*/
	@Override
	public long getRootKaleoInstanceTokenId() {
		return _kaleoInstance.getRootKaleoInstanceTokenId();
	}

	/**
	* Sets the root kaleo instance token ID of this kaleo instance.
	*
	* @param rootKaleoInstanceTokenId the root kaleo instance token ID of this kaleo instance
	*/
	@Override
	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_kaleoInstance.setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);
	}

	/**
	* Returns the class name of this kaleo instance.
	*
	* @return the class name of this kaleo instance
	*/
	@Override
	public java.lang.String getClassName() {
		return _kaleoInstance.getClassName();
	}

	/**
	* Sets the class name of this kaleo instance.
	*
	* @param className the class name of this kaleo instance
	*/
	@Override
	public void setClassName(java.lang.String className) {
		_kaleoInstance.setClassName(className);
	}

	/**
	* Returns the class p k of this kaleo instance.
	*
	* @return the class p k of this kaleo instance
	*/
	@Override
	public long getClassPK() {
		return _kaleoInstance.getClassPK();
	}

	/**
	* Sets the class p k of this kaleo instance.
	*
	* @param classPK the class p k of this kaleo instance
	*/
	@Override
	public void setClassPK(long classPK) {
		_kaleoInstance.setClassPK(classPK);
	}

	/**
	* Returns the completed of this kaleo instance.
	*
	* @return the completed of this kaleo instance
	*/
	@Override
	public boolean getCompleted() {
		return _kaleoInstance.getCompleted();
	}

	/**
	* Returns <code>true</code> if this kaleo instance is completed.
	*
	* @return <code>true</code> if this kaleo instance is completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isCompleted() {
		return _kaleoInstance.isCompleted();
	}

	/**
	* Sets whether this kaleo instance is completed.
	*
	* @param completed the completed of this kaleo instance
	*/
	@Override
	public void setCompleted(boolean completed) {
		_kaleoInstance.setCompleted(completed);
	}

	/**
	* Returns the completion date of this kaleo instance.
	*
	* @return the completion date of this kaleo instance
	*/
	@Override
	public java.util.Date getCompletionDate() {
		return _kaleoInstance.getCompletionDate();
	}

	/**
	* Sets the completion date of this kaleo instance.
	*
	* @param completionDate the completion date of this kaleo instance
	*/
	@Override
	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoInstance.setCompletionDate(completionDate);
	}

	/**
	* Returns the workflow context of this kaleo instance.
	*
	* @return the workflow context of this kaleo instance
	*/
	@Override
	public java.lang.String getWorkflowContext() {
		return _kaleoInstance.getWorkflowContext();
	}

	/**
	* Sets the workflow context of this kaleo instance.
	*
	* @param workflowContext the workflow context of this kaleo instance
	*/
	@Override
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoInstance.setWorkflowContext(workflowContext);
	}

	@Override
	public boolean isNew() {
		return _kaleoInstance.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoInstance.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoInstance.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoInstance.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoInstance.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoInstance.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoInstance.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoInstance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoInstance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoInstance.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoInstanceWrapper((KaleoInstance)_kaleoInstance.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		return _kaleoInstance.compareTo(kaleoInstance);
	}

	@Override
	public int hashCode() {
		return _kaleoInstance.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoInstance> toCacheModel() {
		return _kaleoInstance.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstance toEscapedModel() {
		return new KaleoInstanceWrapper(_kaleoInstance.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstance toUnescapedModel() {
		return new KaleoInstanceWrapper(_kaleoInstance.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoInstance.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoInstance.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstance.persist();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition getKaleoDefinition()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getKaleoDefinition();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getRootKaleoInstanceToken(workflowContext,
			serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getRootKaleoInstanceToken(serviceContext);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoInstanceWrapper)) {
			return false;
		}

		KaleoInstanceWrapper kaleoInstanceWrapper = (KaleoInstanceWrapper)obj;

		if (Validator.equals(_kaleoInstance, kaleoInstanceWrapper._kaleoInstance)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoInstance getWrappedKaleoInstance() {
		return _kaleoInstance;
	}

	@Override
	public KaleoInstance getWrappedModel() {
		return _kaleoInstance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoInstance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoInstance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoInstance.resetOriginalValues();
	}

	private KaleoInstance _kaleoInstance;
}