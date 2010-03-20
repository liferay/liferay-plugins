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
 * <a href="KaleoTaskInstanceAssignmentSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoTaskInstanceAssignment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignment
 * @generated
 */
public class KaleoTaskInstanceAssignmentWrapper
	implements KaleoTaskInstanceAssignment {
	public KaleoTaskInstanceAssignmentWrapper(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment) {
		_kaleoTaskInstanceAssignment = kaleoTaskInstanceAssignment;
	}

	public long getPrimaryKey() {
		return _kaleoTaskInstanceAssignment.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoTaskInstanceAssignment.setPrimaryKey(pk);
	}

	public long getKaleoTaskInstanceAssignmentId() {
		return _kaleoTaskInstanceAssignment.getKaleoTaskInstanceAssignmentId();
	}

	public void setKaleoTaskInstanceAssignmentId(
		long kaleoTaskInstanceAssignmentId) {
		_kaleoTaskInstanceAssignment.setKaleoTaskInstanceAssignmentId(kaleoTaskInstanceAssignmentId);
	}

	public long getCompanyId() {
		return _kaleoTaskInstanceAssignment.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoTaskInstanceAssignment.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoTaskInstanceAssignment.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoTaskInstanceAssignment.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignment.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskInstanceAssignment.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoTaskInstanceAssignment.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoTaskInstanceAssignment.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoTaskInstanceAssignment.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskInstanceAssignment.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoTaskInstanceAssignment.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskInstanceAssignment.setModifiedDate(modifiedDate);
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId();
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceAssignment.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public long getKaleoTaskId() {
		return _kaleoTaskInstanceAssignment.getKaleoTaskId();
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskInstanceAssignment.setKaleoTaskId(kaleoTaskId);
	}

	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskInstanceAssignment.getAssigneeClassName();
	}

	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskInstanceAssignment.setAssigneeClassName(assigneeClassName);
	}

	public long getAssigneeClassPK() {
		return _kaleoTaskInstanceAssignment.getAssigneeClassPK();
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskInstanceAssignment.setAssigneeClassPK(assigneeClassPK);
	}

	public java.util.Date getCompletionDate() {
		return _kaleoTaskInstanceAssignment.getCompletionDate();
	}

	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoTaskInstanceAssignment.setCompletionDate(completionDate);
	}

	public java.lang.String getContext() {
		return _kaleoTaskInstanceAssignment.getContext();
	}

	public void setContext(java.lang.String context) {
		_kaleoTaskInstanceAssignment.setContext(context);
	}

	public KaleoTaskInstanceAssignment toEscapedModel() {
		return _kaleoTaskInstanceAssignment.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoTaskInstanceAssignment.isNew();
	}

	public boolean setNew(boolean n) {
		return _kaleoTaskInstanceAssignment.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTaskInstanceAssignment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskInstanceAssignment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTaskInstanceAssignment.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTaskInstanceAssignment.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskInstanceAssignment.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskInstanceAssignment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskInstanceAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTaskInstanceAssignment.clone();
	}

	public int compareTo(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment) {
		return _kaleoTaskInstanceAssignment.compareTo(kaleoTaskInstanceAssignment);
	}

	public int hashCode() {
		return _kaleoTaskInstanceAssignment.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoTaskInstanceAssignment.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskInstanceAssignment.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignment.getKaleoTaskInstanceToken();
	}

	public boolean isCompleted() {
		return _kaleoTaskInstanceAssignment.isCompleted();
	}

	public KaleoTaskInstanceAssignment getWrappedKaleoTaskInstanceAssignment() {
		return _kaleoTaskInstanceAssignment;
	}

	private KaleoTaskInstanceAssignment _kaleoTaskInstanceAssignment;
}