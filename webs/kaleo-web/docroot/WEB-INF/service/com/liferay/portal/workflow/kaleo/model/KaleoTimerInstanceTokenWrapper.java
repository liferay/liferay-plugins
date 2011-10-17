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
 * This class is a wrapper for {@link KaleoTimerInstanceToken}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTimerInstanceToken
 * @generated
 */
public class KaleoTimerInstanceTokenWrapper implements KaleoTimerInstanceToken,
	ModelWrapper<KaleoTimerInstanceToken> {
	public KaleoTimerInstanceTokenWrapper(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		_kaleoTimerInstanceToken = kaleoTimerInstanceToken;
	}

	public Class<?> getModelClass() {
		return KaleoTimerInstanceToken.class;
	}

	public String getModelClassName() {
		return KaleoTimerInstanceToken.class.getName();
	}

	/**
	* Returns the primary key of this kaleo timer instance token.
	*
	* @return the primary key of this kaleo timer instance token
	*/
	public long getPrimaryKey() {
		return _kaleoTimerInstanceToken.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo timer instance token.
	*
	* @param primaryKey the primary key of this kaleo timer instance token
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoTimerInstanceToken.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo timer instance token ID of this kaleo timer instance token.
	*
	* @return the kaleo timer instance token ID of this kaleo timer instance token
	*/
	public long getKaleoTimerInstanceTokenId() {
		return _kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId();
	}

	/**
	* Sets the kaleo timer instance token ID of this kaleo timer instance token.
	*
	* @param kaleoTimerInstanceTokenId the kaleo timer instance token ID of this kaleo timer instance token
	*/
	public void setKaleoTimerInstanceTokenId(long kaleoTimerInstanceTokenId) {
		_kaleoTimerInstanceToken.setKaleoTimerInstanceTokenId(kaleoTimerInstanceTokenId);
	}

	/**
	* Returns the group ID of this kaleo timer instance token.
	*
	* @return the group ID of this kaleo timer instance token
	*/
	public long getGroupId() {
		return _kaleoTimerInstanceToken.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo timer instance token.
	*
	* @param groupId the group ID of this kaleo timer instance token
	*/
	public void setGroupId(long groupId) {
		_kaleoTimerInstanceToken.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo timer instance token.
	*
	* @return the company ID of this kaleo timer instance token
	*/
	public long getCompanyId() {
		return _kaleoTimerInstanceToken.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo timer instance token.
	*
	* @param companyId the company ID of this kaleo timer instance token
	*/
	public void setCompanyId(long companyId) {
		_kaleoTimerInstanceToken.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo timer instance token.
	*
	* @return the user ID of this kaleo timer instance token
	*/
	public long getUserId() {
		return _kaleoTimerInstanceToken.getUserId();
	}

	/**
	* Sets the user ID of this kaleo timer instance token.
	*
	* @param userId the user ID of this kaleo timer instance token
	*/
	public void setUserId(long userId) {
		_kaleoTimerInstanceToken.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo timer instance token.
	*
	* @return the user uuid of this kaleo timer instance token
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerInstanceToken.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo timer instance token.
	*
	* @param userUuid the user uuid of this kaleo timer instance token
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTimerInstanceToken.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo timer instance token.
	*
	* @return the user name of this kaleo timer instance token
	*/
	public java.lang.String getUserName() {
		return _kaleoTimerInstanceToken.getUserName();
	}

	/**
	* Sets the user name of this kaleo timer instance token.
	*
	* @param userName the user name of this kaleo timer instance token
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoTimerInstanceToken.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo timer instance token.
	*
	* @return the create date of this kaleo timer instance token
	*/
	public java.util.Date getCreateDate() {
		return _kaleoTimerInstanceToken.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo timer instance token.
	*
	* @param createDate the create date of this kaleo timer instance token
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTimerInstanceToken.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo timer instance token.
	*
	* @return the modified date of this kaleo timer instance token
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoTimerInstanceToken.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo timer instance token.
	*
	* @param modifiedDate the modified date of this kaleo timer instance token
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTimerInstanceToken.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo class name of this kaleo timer instance token.
	*
	* @return the kaleo class name of this kaleo timer instance token
	*/
	public java.lang.String getKaleoClassName() {
		return _kaleoTimerInstanceToken.getKaleoClassName();
	}

	/**
	* Sets the kaleo class name of this kaleo timer instance token.
	*
	* @param kaleoClassName the kaleo class name of this kaleo timer instance token
	*/
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoTimerInstanceToken.setKaleoClassName(kaleoClassName);
	}

	/**
	* Returns the kaleo class p k of this kaleo timer instance token.
	*
	* @return the kaleo class p k of this kaleo timer instance token
	*/
	public long getKaleoClassPK() {
		return _kaleoTimerInstanceToken.getKaleoClassPK();
	}

	/**
	* Sets the kaleo class p k of this kaleo timer instance token.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo timer instance token
	*/
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoTimerInstanceToken.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Returns the kaleo definition ID of this kaleo timer instance token.
	*
	* @return the kaleo definition ID of this kaleo timer instance token
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTimerInstanceToken.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo timer instance token.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo timer instance token
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTimerInstanceToken.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo instance ID of this kaleo timer instance token.
	*
	* @return the kaleo instance ID of this kaleo timer instance token
	*/
	public long getKaleoInstanceId() {
		return _kaleoTimerInstanceToken.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo timer instance token.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo timer instance token
	*/
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTimerInstanceToken.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance token ID of this kaleo timer instance token.
	*
	* @return the kaleo instance token ID of this kaleo timer instance token
	*/
	public long getKaleoInstanceTokenId() {
		return _kaleoTimerInstanceToken.getKaleoInstanceTokenId();
	}

	/**
	* Sets the kaleo instance token ID of this kaleo timer instance token.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo timer instance token
	*/
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTimerInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo timer instance token.
	*
	* @return the kaleo task instance token ID of this kaleo timer instance token
	*/
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTimerInstanceToken.getKaleoTaskInstanceTokenId();
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo timer instance token.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo timer instance token
	*/
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTimerInstanceToken.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns the kaleo timer ID of this kaleo timer instance token.
	*
	* @return the kaleo timer ID of this kaleo timer instance token
	*/
	public long getKaleoTimerId() {
		return _kaleoTimerInstanceToken.getKaleoTimerId();
	}

	/**
	* Sets the kaleo timer ID of this kaleo timer instance token.
	*
	* @param kaleoTimerId the kaleo timer ID of this kaleo timer instance token
	*/
	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimerInstanceToken.setKaleoTimerId(kaleoTimerId);
	}

	/**
	* Returns the kaleo timer name of this kaleo timer instance token.
	*
	* @return the kaleo timer name of this kaleo timer instance token
	*/
	public java.lang.String getKaleoTimerName() {
		return _kaleoTimerInstanceToken.getKaleoTimerName();
	}

	/**
	* Sets the kaleo timer name of this kaleo timer instance token.
	*
	* @param kaleoTimerName the kaleo timer name of this kaleo timer instance token
	*/
	public void setKaleoTimerName(java.lang.String kaleoTimerName) {
		_kaleoTimerInstanceToken.setKaleoTimerName(kaleoTimerName);
	}

	/**
	* Returns the blocking of this kaleo timer instance token.
	*
	* @return the blocking of this kaleo timer instance token
	*/
	public boolean getBlocking() {
		return _kaleoTimerInstanceToken.getBlocking();
	}

	/**
	* Returns <code>true</code> if this kaleo timer instance token is blocking.
	*
	* @return <code>true</code> if this kaleo timer instance token is blocking; <code>false</code> otherwise
	*/
	public boolean isBlocking() {
		return _kaleoTimerInstanceToken.isBlocking();
	}

	/**
	* Sets whether this kaleo timer instance token is blocking.
	*
	* @param blocking the blocking of this kaleo timer instance token
	*/
	public void setBlocking(boolean blocking) {
		_kaleoTimerInstanceToken.setBlocking(blocking);
	}

	/**
	* Returns the completion user ID of this kaleo timer instance token.
	*
	* @return the completion user ID of this kaleo timer instance token
	*/
	public long getCompletionUserId() {
		return _kaleoTimerInstanceToken.getCompletionUserId();
	}

	/**
	* Sets the completion user ID of this kaleo timer instance token.
	*
	* @param completionUserId the completion user ID of this kaleo timer instance token
	*/
	public void setCompletionUserId(long completionUserId) {
		_kaleoTimerInstanceToken.setCompletionUserId(completionUserId);
	}

	/**
	* Returns the completion user uuid of this kaleo timer instance token.
	*
	* @return the completion user uuid of this kaleo timer instance token
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getCompletionUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerInstanceToken.getCompletionUserUuid();
	}

	/**
	* Sets the completion user uuid of this kaleo timer instance token.
	*
	* @param completionUserUuid the completion user uuid of this kaleo timer instance token
	*/
	public void setCompletionUserUuid(java.lang.String completionUserUuid) {
		_kaleoTimerInstanceToken.setCompletionUserUuid(completionUserUuid);
	}

	/**
	* Returns the completed of this kaleo timer instance token.
	*
	* @return the completed of this kaleo timer instance token
	*/
	public boolean getCompleted() {
		return _kaleoTimerInstanceToken.getCompleted();
	}

	/**
	* Returns <code>true</code> if this kaleo timer instance token is completed.
	*
	* @return <code>true</code> if this kaleo timer instance token is completed; <code>false</code> otherwise
	*/
	public boolean isCompleted() {
		return _kaleoTimerInstanceToken.isCompleted();
	}

	/**
	* Sets whether this kaleo timer instance token is completed.
	*
	* @param completed the completed of this kaleo timer instance token
	*/
	public void setCompleted(boolean completed) {
		_kaleoTimerInstanceToken.setCompleted(completed);
	}

	/**
	* Returns the completion date of this kaleo timer instance token.
	*
	* @return the completion date of this kaleo timer instance token
	*/
	public java.util.Date getCompletionDate() {
		return _kaleoTimerInstanceToken.getCompletionDate();
	}

	/**
	* Sets the completion date of this kaleo timer instance token.
	*
	* @param completionDate the completion date of this kaleo timer instance token
	*/
	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoTimerInstanceToken.setCompletionDate(completionDate);
	}

	/**
	* Returns the workflow context of this kaleo timer instance token.
	*
	* @return the workflow context of this kaleo timer instance token
	*/
	public java.lang.String getWorkflowContext() {
		return _kaleoTimerInstanceToken.getWorkflowContext();
	}

	/**
	* Sets the workflow context of this kaleo timer instance token.
	*
	* @param workflowContext the workflow context of this kaleo timer instance token
	*/
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoTimerInstanceToken.setWorkflowContext(workflowContext);
	}

	public boolean isNew() {
		return _kaleoTimerInstanceToken.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTimerInstanceToken.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTimerInstanceToken.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTimerInstanceToken.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTimerInstanceToken.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTimerInstanceToken.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTimerInstanceToken.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTimerInstanceToken.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTimerInstanceToken.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTimerInstanceTokenWrapper((KaleoTimerInstanceToken)_kaleoTimerInstanceToken.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		return _kaleoTimerInstanceToken.compareTo(kaleoTimerInstanceToken);
	}

	@Override
	public int hashCode() {
		return _kaleoTimerInstanceToken.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> toCacheModel() {
		return _kaleoTimerInstanceToken.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken toEscapedModel() {
		return new KaleoTimerInstanceTokenWrapper(_kaleoTimerInstanceToken.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTimerInstanceToken.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTimerInstanceToken.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTimerInstanceToken.persist();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerInstanceToken.getKaleoInstanceToken();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerInstanceToken.getKaleoTaskInstanceToken();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer getKaleoTimer()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerInstanceToken.getKaleoTimer();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoTimerInstanceToken getWrappedKaleoTimerInstanceToken() {
		return _kaleoTimerInstanceToken;
	}

	public KaleoTimerInstanceToken getWrappedModel() {
		return _kaleoTimerInstanceToken;
	}

	public void resetOriginalValues() {
		_kaleoTimerInstanceToken.resetOriginalValues();
	}

	private KaleoTimerInstanceToken _kaleoTimerInstanceToken;
}