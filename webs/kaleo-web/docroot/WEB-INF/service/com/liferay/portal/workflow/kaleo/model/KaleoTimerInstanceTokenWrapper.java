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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTimerInstanceToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceToken
 * @generated
 */
@ProviderType
public class KaleoTimerInstanceTokenWrapper implements KaleoTimerInstanceToken,
	ModelWrapper<KaleoTimerInstanceToken> {
	public KaleoTimerInstanceTokenWrapper(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		_kaleoTimerInstanceToken = kaleoTimerInstanceToken;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTimerInstanceToken.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTimerInstanceToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTimerInstanceTokenId",
			getKaleoTimerInstanceTokenId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("kaleoTaskInstanceTokenId", getKaleoTaskInstanceTokenId());
		attributes.put("kaleoTimerId", getKaleoTimerId());
		attributes.put("kaleoTimerName", getKaleoTimerName());
		attributes.put("blocking", getBlocking());
		attributes.put("completionUserId", getCompletionUserId());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());
		attributes.put("workflowContext", getWorkflowContext());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTimerInstanceTokenId = (Long)attributes.get(
				"kaleoTimerInstanceTokenId");

		if (kaleoTimerInstanceTokenId != null) {
			setKaleoTimerInstanceTokenId(kaleoTimerInstanceTokenId);
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

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
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

		Long kaleoTaskInstanceTokenId = (Long)attributes.get(
				"kaleoTaskInstanceTokenId");

		if (kaleoTaskInstanceTokenId != null) {
			setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
		}

		Long kaleoTimerId = (Long)attributes.get("kaleoTimerId");

		if (kaleoTimerId != null) {
			setKaleoTimerId(kaleoTimerId);
		}

		String kaleoTimerName = (String)attributes.get("kaleoTimerName");

		if (kaleoTimerName != null) {
			setKaleoTimerName(kaleoTimerName);
		}

		Boolean blocking = (Boolean)attributes.get("blocking");

		if (blocking != null) {
			setBlocking(blocking);
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

		String workflowContext = (String)attributes.get("workflowContext");

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTimerInstanceTokenWrapper((KaleoTimerInstanceToken)_kaleoTimerInstanceToken.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		return _kaleoTimerInstanceToken.compareTo(kaleoTimerInstanceToken);
	}

	/**
	* Returns the blocking of this kaleo timer instance token.
	*
	* @return the blocking of this kaleo timer instance token
	*/
	@Override
	public boolean getBlocking() {
		return _kaleoTimerInstanceToken.getBlocking();
	}

	/**
	* Returns the company ID of this kaleo timer instance token.
	*
	* @return the company ID of this kaleo timer instance token
	*/
	@Override
	public long getCompanyId() {
		return _kaleoTimerInstanceToken.getCompanyId();
	}

	/**
	* Returns the completed of this kaleo timer instance token.
	*
	* @return the completed of this kaleo timer instance token
	*/
	@Override
	public boolean getCompleted() {
		return _kaleoTimerInstanceToken.getCompleted();
	}

	/**
	* Returns the completion date of this kaleo timer instance token.
	*
	* @return the completion date of this kaleo timer instance token
	*/
	@Override
	public Date getCompletionDate() {
		return _kaleoTimerInstanceToken.getCompletionDate();
	}

	/**
	* Returns the completion user ID of this kaleo timer instance token.
	*
	* @return the completion user ID of this kaleo timer instance token
	*/
	@Override
	public long getCompletionUserId() {
		return _kaleoTimerInstanceToken.getCompletionUserId();
	}

	/**
	* Returns the completion user uuid of this kaleo timer instance token.
	*
	* @return the completion user uuid of this kaleo timer instance token
	*/
	@Override
	public java.lang.String getCompletionUserUuid() {
		return _kaleoTimerInstanceToken.getCompletionUserUuid();
	}

	/**
	* Returns the create date of this kaleo timer instance token.
	*
	* @return the create date of this kaleo timer instance token
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoTimerInstanceToken.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTimerInstanceToken.getExpandoBridge();
	}

	/**
	* Returns the group ID of this kaleo timer instance token.
	*
	* @return the group ID of this kaleo timer instance token
	*/
	@Override
	public long getGroupId() {
		return _kaleoTimerInstanceToken.getGroupId();
	}

	/**
	* Returns the kaleo class name of this kaleo timer instance token.
	*
	* @return the kaleo class name of this kaleo timer instance token
	*/
	@Override
	public java.lang.String getKaleoClassName() {
		return _kaleoTimerInstanceToken.getKaleoClassName();
	}

	/**
	* Returns the kaleo class p k of this kaleo timer instance token.
	*
	* @return the kaleo class p k of this kaleo timer instance token
	*/
	@Override
	public long getKaleoClassPK() {
		return _kaleoTimerInstanceToken.getKaleoClassPK();
	}

	/**
	* Returns the kaleo definition ID of this kaleo timer instance token.
	*
	* @return the kaleo definition ID of this kaleo timer instance token
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoTimerInstanceToken.getKaleoDefinitionId();
	}

	/**
	* Returns the kaleo instance ID of this kaleo timer instance token.
	*
	* @return the kaleo instance ID of this kaleo timer instance token
	*/
	@Override
	public long getKaleoInstanceId() {
		return _kaleoTimerInstanceToken.getKaleoInstanceId();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceToken.getKaleoInstanceToken();
	}

	/**
	* Returns the kaleo instance token ID of this kaleo timer instance token.
	*
	* @return the kaleo instance token ID of this kaleo timer instance token
	*/
	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoTimerInstanceToken.getKaleoInstanceTokenId();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		return _kaleoTimerInstanceToken.getKaleoTaskInstanceToken();
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo timer instance token.
	*
	* @return the kaleo task instance token ID of this kaleo timer instance token
	*/
	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTimerInstanceToken.getKaleoTaskInstanceTokenId();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer getKaleoTimer()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceToken.getKaleoTimer();
	}

	/**
	* Returns the kaleo timer ID of this kaleo timer instance token.
	*
	* @return the kaleo timer ID of this kaleo timer instance token
	*/
	@Override
	public long getKaleoTimerId() {
		return _kaleoTimerInstanceToken.getKaleoTimerId();
	}

	/**
	* Returns the kaleo timer instance token ID of this kaleo timer instance token.
	*
	* @return the kaleo timer instance token ID of this kaleo timer instance token
	*/
	@Override
	public long getKaleoTimerInstanceTokenId() {
		return _kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId();
	}

	/**
	* Returns the kaleo timer name of this kaleo timer instance token.
	*
	* @return the kaleo timer name of this kaleo timer instance token
	*/
	@Override
	public java.lang.String getKaleoTimerName() {
		return _kaleoTimerInstanceToken.getKaleoTimerName();
	}

	/**
	* Returns the modified date of this kaleo timer instance token.
	*
	* @return the modified date of this kaleo timer instance token
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoTimerInstanceToken.getModifiedDate();
	}

	/**
	* Returns the primary key of this kaleo timer instance token.
	*
	* @return the primary key of this kaleo timer instance token
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoTimerInstanceToken.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTimerInstanceToken.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this kaleo timer instance token.
	*
	* @return the user ID of this kaleo timer instance token
	*/
	@Override
	public long getUserId() {
		return _kaleoTimerInstanceToken.getUserId();
	}

	/**
	* Returns the user name of this kaleo timer instance token.
	*
	* @return the user name of this kaleo timer instance token
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoTimerInstanceToken.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo timer instance token.
	*
	* @return the user uuid of this kaleo timer instance token
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoTimerInstanceToken.getUserUuid();
	}

	/**
	* Returns the workflow context of this kaleo timer instance token.
	*
	* @return the workflow context of this kaleo timer instance token
	*/
	@Override
	public java.lang.String getWorkflowContext() {
		return _kaleoTimerInstanceToken.getWorkflowContext();
	}

	@Override
	public int hashCode() {
		return _kaleoTimerInstanceToken.hashCode();
	}

	/**
	* Returns <code>true</code> if this kaleo timer instance token is blocking.
	*
	* @return <code>true</code> if this kaleo timer instance token is blocking; <code>false</code> otherwise
	*/
	@Override
	public boolean isBlocking() {
		return _kaleoTimerInstanceToken.isBlocking();
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoTimerInstanceToken.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this kaleo timer instance token is completed.
	*
	* @return <code>true</code> if this kaleo timer instance token is completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isCompleted() {
		return _kaleoTimerInstanceToken.isCompleted();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoTimerInstanceToken.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kaleoTimerInstanceToken.isNew();
	}

	@Override
	public void persist() {
		_kaleoTimerInstanceToken.persist();
	}

	/**
	* Sets whether this kaleo timer instance token is blocking.
	*
	* @param blocking the blocking of this kaleo timer instance token
	*/
	@Override
	public void setBlocking(boolean blocking) {
		_kaleoTimerInstanceToken.setBlocking(blocking);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoTimerInstanceToken.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this kaleo timer instance token.
	*
	* @param companyId the company ID of this kaleo timer instance token
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoTimerInstanceToken.setCompanyId(companyId);
	}

	/**
	* Sets whether this kaleo timer instance token is completed.
	*
	* @param completed the completed of this kaleo timer instance token
	*/
	@Override
	public void setCompleted(boolean completed) {
		_kaleoTimerInstanceToken.setCompleted(completed);
	}

	/**
	* Sets the completion date of this kaleo timer instance token.
	*
	* @param completionDate the completion date of this kaleo timer instance token
	*/
	@Override
	public void setCompletionDate(Date completionDate) {
		_kaleoTimerInstanceToken.setCompletionDate(completionDate);
	}

	/**
	* Sets the completion user ID of this kaleo timer instance token.
	*
	* @param completionUserId the completion user ID of this kaleo timer instance token
	*/
	@Override
	public void setCompletionUserId(long completionUserId) {
		_kaleoTimerInstanceToken.setCompletionUserId(completionUserId);
	}

	/**
	* Sets the completion user uuid of this kaleo timer instance token.
	*
	* @param completionUserUuid the completion user uuid of this kaleo timer instance token
	*/
	@Override
	public void setCompletionUserUuid(java.lang.String completionUserUuid) {
		_kaleoTimerInstanceToken.setCompletionUserUuid(completionUserUuid);
	}

	/**
	* Sets the create date of this kaleo timer instance token.
	*
	* @param createDate the create date of this kaleo timer instance token
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoTimerInstanceToken.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoTimerInstanceToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoTimerInstanceToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTimerInstanceToken.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kaleo timer instance token.
	*
	* @param groupId the group ID of this kaleo timer instance token
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoTimerInstanceToken.setGroupId(groupId);
	}

	/**
	* Sets the kaleo class name of this kaleo timer instance token.
	*
	* @param kaleoClassName the kaleo class name of this kaleo timer instance token
	*/
	@Override
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoTimerInstanceToken.setKaleoClassName(kaleoClassName);
	}

	/**
	* Sets the kaleo class p k of this kaleo timer instance token.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo timer instance token
	*/
	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoTimerInstanceToken.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Sets the kaleo definition ID of this kaleo timer instance token.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo timer instance token
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTimerInstanceToken.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the kaleo instance ID of this kaleo timer instance token.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo timer instance token
	*/
	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTimerInstanceToken.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Sets the kaleo instance token ID of this kaleo timer instance token.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo timer instance token
	*/
	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTimerInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo timer instance token.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo timer instance token
	*/
	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTimerInstanceToken.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Sets the kaleo timer ID of this kaleo timer instance token.
	*
	* @param kaleoTimerId the kaleo timer ID of this kaleo timer instance token
	*/
	@Override
	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimerInstanceToken.setKaleoTimerId(kaleoTimerId);
	}

	/**
	* Sets the kaleo timer instance token ID of this kaleo timer instance token.
	*
	* @param kaleoTimerInstanceTokenId the kaleo timer instance token ID of this kaleo timer instance token
	*/
	@Override
	public void setKaleoTimerInstanceTokenId(long kaleoTimerInstanceTokenId) {
		_kaleoTimerInstanceToken.setKaleoTimerInstanceTokenId(kaleoTimerInstanceTokenId);
	}

	/**
	* Sets the kaleo timer name of this kaleo timer instance token.
	*
	* @param kaleoTimerName the kaleo timer name of this kaleo timer instance token
	*/
	@Override
	public void setKaleoTimerName(java.lang.String kaleoTimerName) {
		_kaleoTimerInstanceToken.setKaleoTimerName(kaleoTimerName);
	}

	/**
	* Sets the modified date of this kaleo timer instance token.
	*
	* @param modifiedDate the modified date of this kaleo timer instance token
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoTimerInstanceToken.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_kaleoTimerInstanceToken.setNew(n);
	}

	/**
	* Sets the primary key of this kaleo timer instance token.
	*
	* @param primaryKey the primary key of this kaleo timer instance token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoTimerInstanceToken.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTimerInstanceToken.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this kaleo timer instance token.
	*
	* @param userId the user ID of this kaleo timer instance token
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoTimerInstanceToken.setUserId(userId);
	}

	/**
	* Sets the user name of this kaleo timer instance token.
	*
	* @param userName the user name of this kaleo timer instance token
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoTimerInstanceToken.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kaleo timer instance token.
	*
	* @param userUuid the user uuid of this kaleo timer instance token
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTimerInstanceToken.setUserUuid(userUuid);
	}

	/**
	* Sets the workflow context of this kaleo timer instance token.
	*
	* @param workflowContext the workflow context of this kaleo timer instance token
	*/
	@Override
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoTimerInstanceToken.setWorkflowContext(workflowContext);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> toCacheModel() {
		return _kaleoTimerInstanceToken.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken toEscapedModel() {
		return new KaleoTimerInstanceTokenWrapper(_kaleoTimerInstanceToken.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTimerInstanceToken.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken toUnescapedModel() {
		return new KaleoTimerInstanceTokenWrapper(_kaleoTimerInstanceToken.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoTimerInstanceToken.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTimerInstanceTokenWrapper)) {
			return false;
		}

		KaleoTimerInstanceTokenWrapper kaleoTimerInstanceTokenWrapper = (KaleoTimerInstanceTokenWrapper)obj;

		if (Validator.equals(_kaleoTimerInstanceToken,
					kaleoTimerInstanceTokenWrapper._kaleoTimerInstanceToken)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoTimerInstanceToken getWrappedKaleoTimerInstanceToken() {
		return _kaleoTimerInstanceToken;
	}

	@Override
	public KaleoTimerInstanceToken getWrappedModel() {
		return _kaleoTimerInstanceToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoTimerInstanceToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoTimerInstanceToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoTimerInstanceToken.resetOriginalValues();
	}

	private final KaleoTimerInstanceToken _kaleoTimerInstanceToken;
}