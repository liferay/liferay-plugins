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
 * This class is a wrapper for {@link KaleoTaskInstanceToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceToken
 * @generated
 */
public class KaleoTaskInstanceTokenWrapper implements KaleoTaskInstanceToken,
	ModelWrapper<KaleoTaskInstanceToken> {
	public KaleoTaskInstanceTokenWrapper(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskInstanceToken.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskInstanceToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskInstanceTokenId", getKaleoTaskInstanceTokenId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("kaleoTaskId", getKaleoTaskId());
		attributes.put("kaleoTaskName", getKaleoTaskName());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("completionUserId", getCompletionUserId());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());
		attributes.put("dueDate", getDueDate());
		attributes.put("workflowContext", getWorkflowContext());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskInstanceTokenId = (Long)attributes.get(
				"kaleoTaskInstanceTokenId");

		if (kaleoTaskInstanceTokenId != null) {
			setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
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

		Long kaleoInstanceId = (Long)attributes.get("kaleoInstanceId");

		if (kaleoInstanceId != null) {
			setKaleoInstanceId(kaleoInstanceId);
		}

		Long kaleoInstanceTokenId = (Long)attributes.get("kaleoInstanceTokenId");

		if (kaleoInstanceTokenId != null) {
			setKaleoInstanceTokenId(kaleoInstanceTokenId);
		}

		Long kaleoTaskId = (Long)attributes.get("kaleoTaskId");

		if (kaleoTaskId != null) {
			setKaleoTaskId(kaleoTaskId);
		}

		String kaleoTaskName = (String)attributes.get("kaleoTaskName");

		if (kaleoTaskName != null) {
			setKaleoTaskName(kaleoTaskName);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long completionUserId = (Long)attributes.get("completionUserId");

		if (completionUserId != null) {
			setCompletionUserId(completionUserId);
		}

		Boolean completed = (Boolean)attributes.get("completed");

		if (completed != null) {
			setCompleted(completed);
		}

		Date completionDate = (Date)attributes.get("completionDate");

		if (completionDate != null) {
			setCompletionDate(completionDate);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		String workflowContext = (String)attributes.get("workflowContext");

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}
	}

	/**
	* Returns the primary key of this kaleo task instance token.
	*
	* @return the primary key of this kaleo task instance token
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoTaskInstanceToken.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task instance token.
	*
	* @param primaryKey the primary key of this kaleo task instance token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoTaskInstanceToken.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo task instance token.
	*
	* @return the kaleo task instance token ID of this kaleo task instance token
	*/
	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId();
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo task instance token.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo task instance token
	*/
	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceToken.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns the group ID of this kaleo task instance token.
	*
	* @return the group ID of this kaleo task instance token
	*/
	@Override
	public long getGroupId() {
		return _kaleoTaskInstanceToken.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task instance token.
	*
	* @param groupId the group ID of this kaleo task instance token
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoTaskInstanceToken.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task instance token.
	*
	* @return the company ID of this kaleo task instance token
	*/
	@Override
	public long getCompanyId() {
		return _kaleoTaskInstanceToken.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task instance token.
	*
	* @param companyId the company ID of this kaleo task instance token
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoTaskInstanceToken.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task instance token.
	*
	* @return the user ID of this kaleo task instance token
	*/
	@Override
	public long getUserId() {
		return _kaleoTaskInstanceToken.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task instance token.
	*
	* @param userId the user ID of this kaleo task instance token
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoTaskInstanceToken.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task instance token.
	*
	* @return the user uuid of this kaleo task instance token
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task instance token.
	*
	* @param userUuid the user uuid of this kaleo task instance token
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskInstanceToken.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo task instance token.
	*
	* @return the user name of this kaleo task instance token
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoTaskInstanceToken.getUserName();
	}

	/**
	* Sets the user name of this kaleo task instance token.
	*
	* @param userName the user name of this kaleo task instance token
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoTaskInstanceToken.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo task instance token.
	*
	* @return the create date of this kaleo task instance token
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoTaskInstanceToken.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task instance token.
	*
	* @param createDate the create date of this kaleo task instance token
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskInstanceToken.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo task instance token.
	*
	* @return the modified date of this kaleo task instance token
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoTaskInstanceToken.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task instance token.
	*
	* @param modifiedDate the modified date of this kaleo task instance token
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskInstanceToken.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo task instance token.
	*
	* @return the kaleo definition ID of this kaleo task instance token
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoTaskInstanceToken.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task instance token.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task instance token
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskInstanceToken.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo instance ID of this kaleo task instance token.
	*
	* @return the kaleo instance ID of this kaleo task instance token
	*/
	@Override
	public long getKaleoInstanceId() {
		return _kaleoTaskInstanceToken.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo task instance token.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo task instance token
	*/
	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTaskInstanceToken.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance token ID of this kaleo task instance token.
	*
	* @return the kaleo instance token ID of this kaleo task instance token
	*/
	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoTaskInstanceToken.getKaleoInstanceTokenId();
	}

	/**
	* Sets the kaleo instance token ID of this kaleo task instance token.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo task instance token
	*/
	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTaskInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Returns the kaleo task ID of this kaleo task instance token.
	*
	* @return the kaleo task ID of this kaleo task instance token
	*/
	@Override
	public long getKaleoTaskId() {
		return _kaleoTaskInstanceToken.getKaleoTaskId();
	}

	/**
	* Sets the kaleo task ID of this kaleo task instance token.
	*
	* @param kaleoTaskId the kaleo task ID of this kaleo task instance token
	*/
	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskInstanceToken.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns the kaleo task name of this kaleo task instance token.
	*
	* @return the kaleo task name of this kaleo task instance token
	*/
	@Override
	public java.lang.String getKaleoTaskName() {
		return _kaleoTaskInstanceToken.getKaleoTaskName();
	}

	/**
	* Sets the kaleo task name of this kaleo task instance token.
	*
	* @param kaleoTaskName the kaleo task name of this kaleo task instance token
	*/
	@Override
	public void setKaleoTaskName(java.lang.String kaleoTaskName) {
		_kaleoTaskInstanceToken.setKaleoTaskName(kaleoTaskName);
	}

	/**
	* Returns the class name of this kaleo task instance token.
	*
	* @return the class name of this kaleo task instance token
	*/
	@Override
	public java.lang.String getClassName() {
		return _kaleoTaskInstanceToken.getClassName();
	}

	/**
	* Sets the class name of this kaleo task instance token.
	*
	* @param className the class name of this kaleo task instance token
	*/
	@Override
	public void setClassName(java.lang.String className) {
		_kaleoTaskInstanceToken.setClassName(className);
	}

	/**
	* Returns the class p k of this kaleo task instance token.
	*
	* @return the class p k of this kaleo task instance token
	*/
	@Override
	public long getClassPK() {
		return _kaleoTaskInstanceToken.getClassPK();
	}

	/**
	* Sets the class p k of this kaleo task instance token.
	*
	* @param classPK the class p k of this kaleo task instance token
	*/
	@Override
	public void setClassPK(long classPK) {
		_kaleoTaskInstanceToken.setClassPK(classPK);
	}

	/**
	* Returns the completion user ID of this kaleo task instance token.
	*
	* @return the completion user ID of this kaleo task instance token
	*/
	@Override
	public long getCompletionUserId() {
		return _kaleoTaskInstanceToken.getCompletionUserId();
	}

	/**
	* Sets the completion user ID of this kaleo task instance token.
	*
	* @param completionUserId the completion user ID of this kaleo task instance token
	*/
	@Override
	public void setCompletionUserId(long completionUserId) {
		_kaleoTaskInstanceToken.setCompletionUserId(completionUserId);
	}

	/**
	* Returns the completion user uuid of this kaleo task instance token.
	*
	* @return the completion user uuid of this kaleo task instance token
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getCompletionUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getCompletionUserUuid();
	}

	/**
	* Sets the completion user uuid of this kaleo task instance token.
	*
	* @param completionUserUuid the completion user uuid of this kaleo task instance token
	*/
	@Override
	public void setCompletionUserUuid(java.lang.String completionUserUuid) {
		_kaleoTaskInstanceToken.setCompletionUserUuid(completionUserUuid);
	}

	/**
	* Returns the completed of this kaleo task instance token.
	*
	* @return the completed of this kaleo task instance token
	*/
	@Override
	public boolean getCompleted() {
		return _kaleoTaskInstanceToken.getCompleted();
	}

	/**
	* Returns <code>true</code> if this kaleo task instance token is completed.
	*
	* @return <code>true</code> if this kaleo task instance token is completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isCompleted() {
		return _kaleoTaskInstanceToken.isCompleted();
	}

	/**
	* Sets whether this kaleo task instance token is completed.
	*
	* @param completed the completed of this kaleo task instance token
	*/
	@Override
	public void setCompleted(boolean completed) {
		_kaleoTaskInstanceToken.setCompleted(completed);
	}

	/**
	* Returns the completion date of this kaleo task instance token.
	*
	* @return the completion date of this kaleo task instance token
	*/
	@Override
	public java.util.Date getCompletionDate() {
		return _kaleoTaskInstanceToken.getCompletionDate();
	}

	/**
	* Sets the completion date of this kaleo task instance token.
	*
	* @param completionDate the completion date of this kaleo task instance token
	*/
	@Override
	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoTaskInstanceToken.setCompletionDate(completionDate);
	}

	/**
	* Returns the due date of this kaleo task instance token.
	*
	* @return the due date of this kaleo task instance token
	*/
	@Override
	public java.util.Date getDueDate() {
		return _kaleoTaskInstanceToken.getDueDate();
	}

	/**
	* Sets the due date of this kaleo task instance token.
	*
	* @param dueDate the due date of this kaleo task instance token
	*/
	@Override
	public void setDueDate(java.util.Date dueDate) {
		_kaleoTaskInstanceToken.setDueDate(dueDate);
	}

	/**
	* Returns the workflow context of this kaleo task instance token.
	*
	* @return the workflow context of this kaleo task instance token
	*/
	@Override
	public java.lang.String getWorkflowContext() {
		return _kaleoTaskInstanceToken.getWorkflowContext();
	}

	/**
	* Sets the workflow context of this kaleo task instance token.
	*
	* @param workflowContext the workflow context of this kaleo task instance token
	*/
	@Override
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoTaskInstanceToken.setWorkflowContext(workflowContext);
	}

	@Override
	public boolean isNew() {
		return _kaleoTaskInstanceToken.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoTaskInstanceToken.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoTaskInstanceToken.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskInstanceToken.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoTaskInstanceToken.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskInstanceToken.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTaskInstanceToken.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskInstanceToken.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoTaskInstanceToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoTaskInstanceToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskInstanceToken.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskInstanceTokenWrapper((KaleoTaskInstanceToken)_kaleoTaskInstanceToken.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return _kaleoTaskInstanceToken.compareTo(kaleoTaskInstanceToken);
	}

	@Override
	public int hashCode() {
		return _kaleoTaskInstanceToken.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> toCacheModel() {
		return _kaleoTaskInstanceToken.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken toEscapedModel() {
		return new KaleoTaskInstanceTokenWrapper(_kaleoTaskInstanceToken.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken toUnescapedModel() {
		return new KaleoTaskInstanceTokenWrapper(_kaleoTaskInstanceToken.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTaskInstanceToken.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoTaskInstanceToken.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceToken.persist();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getKaleoInstanceToken();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoTask()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getKaleoTask();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskInstanceTokenWrapper)) {
			return false;
		}

		KaleoTaskInstanceTokenWrapper kaleoTaskInstanceTokenWrapper = (KaleoTaskInstanceTokenWrapper)obj;

		if (Validator.equals(_kaleoTaskInstanceToken,
					kaleoTaskInstanceTokenWrapper._kaleoTaskInstanceToken)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoTaskInstanceToken getWrappedKaleoTaskInstanceToken() {
		return _kaleoTaskInstanceToken;
	}

	@Override
	public KaleoTaskInstanceToken getWrappedModel() {
		return _kaleoTaskInstanceToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoTaskInstanceToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoTaskInstanceToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoTaskInstanceToken.resetOriginalValues();
	}

	private KaleoTaskInstanceToken _kaleoTaskInstanceToken;
}