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
 * <a href="KaleoTaskAssignmentInstanceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoTaskAssignmentInstance}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentInstance
 * @generated
 */
public class KaleoTaskAssignmentInstanceWrapper
	implements KaleoTaskAssignmentInstance {
	public KaleoTaskAssignmentInstanceWrapper(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		_kaleoTaskAssignmentInstance = kaleoTaskAssignmentInstance;
	}

	public long getPrimaryKey() {
		return _kaleoTaskAssignmentInstance.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoTaskAssignmentInstance.setPrimaryKey(pk);
	}

	public long getKaleoTaskAssignmentInstanceId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId();
	}

	public void setKaleoTaskAssignmentInstanceId(
		long kaleoTaskAssignmentInstanceId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstanceId);
	}

	public long getGroupId() {
		return _kaleoTaskAssignmentInstance.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoTaskAssignmentInstance.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoTaskAssignmentInstance.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoTaskAssignmentInstance.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoTaskAssignmentInstance.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoTaskAssignmentInstance.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstance.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskAssignmentInstance.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoTaskAssignmentInstance.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoTaskAssignmentInstance.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoTaskAssignmentInstance.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskAssignmentInstance.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoTaskAssignmentInstance.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskAssignmentInstance.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoTaskAssignmentInstance.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskAssignmentInstance.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoInstanceId() {
		return _kaleoTaskAssignmentInstance.getKaleoInstanceId();
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTaskAssignmentInstance.setKaleoInstanceId(kaleoInstanceId);
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoTaskAssignmentInstance.getKaleoInstanceTokenId();
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTaskAssignmentInstance.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId();
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public long getKaleoTaskId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskId();
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskId(kaleoTaskId);
	}

	public java.lang.String getKaleoTaskName() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskName();
	}

	public void setKaleoTaskName(java.lang.String kaleoTaskName) {
		_kaleoTaskAssignmentInstance.setKaleoTaskName(kaleoTaskName);
	}

	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskAssignmentInstance.getAssigneeClassName();
	}

	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskAssignmentInstance.setAssigneeClassName(assigneeClassName);
	}

	public long getAssigneeClassPK() {
		return _kaleoTaskAssignmentInstance.getAssigneeClassPK();
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskAssignmentInstance.setAssigneeClassPK(assigneeClassPK);
	}

	public boolean getCompleted() {
		return _kaleoTaskAssignmentInstance.getCompleted();
	}

	public boolean isCompleted() {
		return _kaleoTaskAssignmentInstance.isCompleted();
	}

	public void setCompleted(boolean completed) {
		_kaleoTaskAssignmentInstance.setCompleted(completed);
	}

	public java.util.Date getCompletionDate() {
		return _kaleoTaskAssignmentInstance.getCompletionDate();
	}

	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoTaskAssignmentInstance.setCompletionDate(completionDate);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance toEscapedModel() {
		return _kaleoTaskAssignmentInstance.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoTaskAssignmentInstance.isNew();
	}

	public boolean setNew(boolean n) {
		return _kaleoTaskAssignmentInstance.setNew(n);
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

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTaskAssignmentInstance.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignmentInstance.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskAssignmentInstance.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskAssignmentInstance.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTaskAssignmentInstance.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		return _kaleoTaskAssignmentInstance.compareTo(kaleoTaskAssignmentInstance);
	}

	public int hashCode() {
		return _kaleoTaskAssignmentInstance.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoTaskAssignmentInstance.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskAssignmentInstance.toXmlString();
	}

	public KaleoTaskAssignmentInstance getWrappedKaleoTaskAssignmentInstance() {
		return _kaleoTaskAssignmentInstance;
	}

	private KaleoTaskAssignmentInstance _kaleoTaskAssignmentInstance;
}