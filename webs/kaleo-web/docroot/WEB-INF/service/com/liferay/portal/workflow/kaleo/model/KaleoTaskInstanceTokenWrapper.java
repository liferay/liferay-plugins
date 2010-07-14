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
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoTaskInstanceToken}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceToken
 * @generated
 */
public class KaleoTaskInstanceTokenWrapper implements KaleoTaskInstanceToken {
	public KaleoTaskInstanceTokenWrapper(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
	}

	public long getPrimaryKey() {
		return _kaleoTaskInstanceToken.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoTaskInstanceToken.setPrimaryKey(pk);
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId();
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceToken.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public long getGroupId() {
		return _kaleoTaskInstanceToken.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoTaskInstanceToken.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoTaskInstanceToken.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoTaskInstanceToken.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoTaskInstanceToken.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoTaskInstanceToken.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskInstanceToken.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoTaskInstanceToken.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoTaskInstanceToken.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoTaskInstanceToken.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskInstanceToken.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoTaskInstanceToken.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskInstanceToken.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoTaskInstanceToken.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskInstanceToken.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoInstanceId() {
		return _kaleoTaskInstanceToken.getKaleoInstanceId();
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTaskInstanceToken.setKaleoInstanceId(kaleoInstanceId);
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoTaskInstanceToken.getKaleoInstanceTokenId();
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTaskInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	public long getKaleoTaskId() {
		return _kaleoTaskInstanceToken.getKaleoTaskId();
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskInstanceToken.setKaleoTaskId(kaleoTaskId);
	}

	public java.lang.String getKaleoTaskName() {
		return _kaleoTaskInstanceToken.getKaleoTaskName();
	}

	public void setKaleoTaskName(java.lang.String kaleoTaskName) {
		_kaleoTaskInstanceToken.setKaleoTaskName(kaleoTaskName);
	}

	public java.lang.String getClassName() {
		return _kaleoTaskInstanceToken.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_kaleoTaskInstanceToken.setClassName(className);
	}

	public long getClassPK() {
		return _kaleoTaskInstanceToken.getClassPK();
	}

	public void setClassPK(long classPK) {
		_kaleoTaskInstanceToken.setClassPK(classPK);
	}

	public long getCompletionUserId() {
		return _kaleoTaskInstanceToken.getCompletionUserId();
	}

	public void setCompletionUserId(long completionUserId) {
		_kaleoTaskInstanceToken.setCompletionUserId(completionUserId);
	}

	public java.lang.String getCompletionUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getCompletionUserUuid();
	}

	public void setCompletionUserUuid(java.lang.String completionUserUuid) {
		_kaleoTaskInstanceToken.setCompletionUserUuid(completionUserUuid);
	}

	public boolean getCompleted() {
		return _kaleoTaskInstanceToken.getCompleted();
	}

	public boolean isCompleted() {
		return _kaleoTaskInstanceToken.isCompleted();
	}

	public void setCompleted(boolean completed) {
		_kaleoTaskInstanceToken.setCompleted(completed);
	}

	public java.util.Date getCompletionDate() {
		return _kaleoTaskInstanceToken.getCompletionDate();
	}

	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoTaskInstanceToken.setCompletionDate(completionDate);
	}

	public java.util.Date getDueDate() {
		return _kaleoTaskInstanceToken.getDueDate();
	}

	public void setDueDate(java.util.Date dueDate) {
		_kaleoTaskInstanceToken.setDueDate(dueDate);
	}

	public java.lang.String getWorkflowContext() {
		return _kaleoTaskInstanceToken.getWorkflowContext();
	}

	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoTaskInstanceToken.setWorkflowContext(workflowContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken toEscapedModel() {
		return _kaleoTaskInstanceToken.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoTaskInstanceToken.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTaskInstanceToken.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTaskInstanceToken.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskInstanceToken.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTaskInstanceToken.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTaskInstanceToken.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskInstanceToken.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskInstanceToken.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskInstanceToken.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTaskInstanceToken.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return _kaleoTaskInstanceToken.compareTo(kaleoTaskInstanceToken);
	}

	public int hashCode() {
		return _kaleoTaskInstanceToken.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoTaskInstanceToken.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskInstanceToken.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getKaleoInstanceToken();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoTask()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getKaleoTask();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();
	}

	public KaleoTaskInstanceToken getWrappedKaleoTaskInstanceToken() {
		return _kaleoTaskInstanceToken;
	}

	private KaleoTaskInstanceToken _kaleoTaskInstanceToken;
}