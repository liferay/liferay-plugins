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
 * This class is a wrapper for {@link KaleoTaskAssignment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignment
 * @generated
 */
public class KaleoTaskAssignmentWrapper implements KaleoTaskAssignment {
	public KaleoTaskAssignmentWrapper(KaleoTaskAssignment kaleoTaskAssignment) {
		_kaleoTaskAssignment = kaleoTaskAssignment;
	}

	public long getPrimaryKey() {
		return _kaleoTaskAssignment.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoTaskAssignment.setPrimaryKey(pk);
	}

	public long getKaleoTaskAssignmentId() {
		return _kaleoTaskAssignment.getKaleoTaskAssignmentId();
	}

	public void setKaleoTaskAssignmentId(long kaleoTaskAssignmentId) {
		_kaleoTaskAssignment.setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
	}

	public long getGroupId() {
		return _kaleoTaskAssignment.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoTaskAssignment.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoTaskAssignment.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoTaskAssignment.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoTaskAssignment.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoTaskAssignment.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignment.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskAssignment.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoTaskAssignment.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoTaskAssignment.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoTaskAssignment.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskAssignment.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoTaskAssignment.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskAssignment.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoTaskAssignment.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskAssignment.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoNodeId() {
		return _kaleoTaskAssignment.getKaleoNodeId();
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTaskAssignment.setKaleoNodeId(kaleoNodeId);
	}

	public long getKaleoTaskId() {
		return _kaleoTaskAssignment.getKaleoTaskId();
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskAssignment.setKaleoTaskId(kaleoTaskId);
	}

	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskAssignment.getAssigneeClassName();
	}

	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskAssignment.setAssigneeClassName(assigneeClassName);
	}

	public long getAssigneeClassPK() {
		return _kaleoTaskAssignment.getAssigneeClassPK();
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskAssignment.setAssigneeClassPK(assigneeClassPK);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment toEscapedModel() {
		return _kaleoTaskAssignment.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoTaskAssignment.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTaskAssignment.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTaskAssignment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskAssignment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTaskAssignment.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTaskAssignment.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignment.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskAssignment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTaskAssignment.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment) {
		return _kaleoTaskAssignment.compareTo(kaleoTaskAssignment);
	}

	public int hashCode() {
		return _kaleoTaskAssignment.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoTaskAssignment.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskAssignment.toXmlString();
	}

	public KaleoTaskAssignment getWrappedKaleoTaskAssignment() {
		return _kaleoTaskAssignment;
	}

	private KaleoTaskAssignment _kaleoTaskAssignment;
}