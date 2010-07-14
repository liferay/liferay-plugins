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
 * This class is a wrapper for {@link KaleoNotificationRecipient}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipient
 * @generated
 */
public class KaleoNotificationRecipientWrapper
	implements KaleoNotificationRecipient {
	public KaleoNotificationRecipientWrapper(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		_kaleoNotificationRecipient = kaleoNotificationRecipient;
	}

	public long getPrimaryKey() {
		return _kaleoNotificationRecipient.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoNotificationRecipient.setPrimaryKey(pk);
	}

	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipient.getKaleoNotificationRecipientId();
	}

	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_kaleoNotificationRecipient.setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
	}

	public long getGroupId() {
		return _kaleoNotificationRecipient.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoNotificationRecipient.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoNotificationRecipient.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoNotificationRecipient.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoNotificationRecipient.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoNotificationRecipient.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipient.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNotificationRecipient.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoNotificationRecipient.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoNotificationRecipient.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoNotificationRecipient.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoNotificationRecipient.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoNotificationRecipient.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoNotificationRecipient.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoNotificationRecipient.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNotificationRecipient.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoNotificationId() {
		return _kaleoNotificationRecipient.getKaleoNotificationId();
	}

	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationRecipient.setKaleoNotificationId(kaleoNotificationId);
	}

	public java.lang.String getRecipientClassName() {
		return _kaleoNotificationRecipient.getRecipientClassName();
	}

	public void setRecipientClassName(java.lang.String recipientClassName) {
		_kaleoNotificationRecipient.setRecipientClassName(recipientClassName);
	}

	public long getRecipientClassPK() {
		return _kaleoNotificationRecipient.getRecipientClassPK();
	}

	public void setRecipientClassPK(long recipientClassPK) {
		_kaleoNotificationRecipient.setRecipientClassPK(recipientClassPK);
	}

	public int getRecipientRoleType() {
		return _kaleoNotificationRecipient.getRecipientRoleType();
	}

	public void setRecipientRoleType(int recipientRoleType) {
		_kaleoNotificationRecipient.setRecipientRoleType(recipientRoleType);
	}

	public java.lang.String getAddress() {
		return _kaleoNotificationRecipient.getAddress();
	}

	public void setAddress(java.lang.String address) {
		_kaleoNotificationRecipient.setAddress(address);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient toEscapedModel() {
		return _kaleoNotificationRecipient.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoNotificationRecipient.isNew();
	}

	public void setNew(boolean n) {
		_kaleoNotificationRecipient.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoNotificationRecipient.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoNotificationRecipient.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoNotificationRecipient.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoNotificationRecipient.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNotificationRecipient.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNotificationRecipient.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNotificationRecipient.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoNotificationRecipient.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient) {
		return _kaleoNotificationRecipient.compareTo(kaleoNotificationRecipient);
	}

	public int hashCode() {
		return _kaleoNotificationRecipient.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoNotificationRecipient.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoNotificationRecipient.toXmlString();
	}

	public KaleoNotificationRecipient getWrappedKaleoNotificationRecipient() {
		return _kaleoNotificationRecipient;
	}

	private KaleoNotificationRecipient _kaleoNotificationRecipient;
}