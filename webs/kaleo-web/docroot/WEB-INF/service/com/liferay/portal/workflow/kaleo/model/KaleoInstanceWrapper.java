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
 * This class is a wrapper for {@link KaleoInstance}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstance
 * @generated
 */
public class KaleoInstanceWrapper implements KaleoInstance {
	public KaleoInstanceWrapper(KaleoInstance kaleoInstance) {
		_kaleoInstance = kaleoInstance;
	}

	public long getPrimaryKey() {
		return _kaleoInstance.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoInstance.setPrimaryKey(pk);
	}

	public long getKaleoInstanceId() {
		return _kaleoInstance.getKaleoInstanceId();
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstance.setKaleoInstanceId(kaleoInstanceId);
	}

	public long getGroupId() {
		return _kaleoInstance.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoInstance.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoInstance.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoInstance.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoInstance.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoInstance.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstance.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoInstance.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoInstance.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoInstance.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoInstance.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoInstance.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoInstance.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoInstance.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoInstance.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoInstance.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public java.lang.String getKaleoDefinitionName() {
		return _kaleoInstance.getKaleoDefinitionName();
	}

	public void setKaleoDefinitionName(java.lang.String kaleoDefinitionName) {
		_kaleoInstance.setKaleoDefinitionName(kaleoDefinitionName);
	}

	public int getKaleoDefinitionVersion() {
		return _kaleoInstance.getKaleoDefinitionVersion();
	}

	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_kaleoInstance.setKaleoDefinitionVersion(kaleoDefinitionVersion);
	}

	public long getRootKaleoInstanceTokenId() {
		return _kaleoInstance.getRootKaleoInstanceTokenId();
	}

	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_kaleoInstance.setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);
	}

	public java.lang.String getClassName() {
		return _kaleoInstance.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_kaleoInstance.setClassName(className);
	}

	public long getClassPK() {
		return _kaleoInstance.getClassPK();
	}

	public void setClassPK(long classPK) {
		_kaleoInstance.setClassPK(classPK);
	}

	public boolean getCompleted() {
		return _kaleoInstance.getCompleted();
	}

	public boolean isCompleted() {
		return _kaleoInstance.isCompleted();
	}

	public void setCompleted(boolean completed) {
		_kaleoInstance.setCompleted(completed);
	}

	public java.util.Date getCompletionDate() {
		return _kaleoInstance.getCompletionDate();
	}

	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoInstance.setCompletionDate(completionDate);
	}

	public java.lang.String getWorkflowContext() {
		return _kaleoInstance.getWorkflowContext();
	}

	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoInstance.setWorkflowContext(workflowContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance toEscapedModel() {
		return _kaleoInstance.toEscapedModel();
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

	public void setEscapedModel(boolean escapedModel) {
		_kaleoInstance.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoInstance.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoInstance.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoInstance.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoInstance.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		return _kaleoInstance.compareTo(kaleoInstance);
	}

	public int hashCode() {
		return _kaleoInstance.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoInstance.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoInstance.toXmlString();
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

	public KaleoInstance getWrappedKaleoInstance() {
		return _kaleoInstance;
	}

	private KaleoInstance _kaleoInstance;
}