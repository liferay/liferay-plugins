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

package com.liferay.privatemessaging.model;

/**
 * <p>
 * This class is a wrapper for {@link UserThread}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserThread
 * @generated
 */
public class UserThreadWrapper implements UserThread {
	public UserThreadWrapper(UserThread userThread) {
		_userThread = userThread;
	}

	public long getPrimaryKey() {
		return _userThread.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_userThread.setPrimaryKey(pk);
	}

	public long getUserThreadId() {
		return _userThread.getUserThreadId();
	}

	public void setUserThreadId(long userThreadId) {
		_userThread.setUserThreadId(userThreadId);
	}

	public long getCompanyId() {
		return _userThread.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_userThread.setCompanyId(companyId);
	}

	public long getUserId() {
		return _userThread.getUserId();
	}

	public void setUserId(long userId) {
		_userThread.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThread.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_userThread.setUserUuid(userUuid);
	}

	public java.util.Date getCreateDate() {
		return _userThread.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_userThread.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _userThread.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_userThread.setModifiedDate(modifiedDate);
	}

	public long getMbThreadId() {
		return _userThread.getMbThreadId();
	}

	public void setMbThreadId(long mbThreadId) {
		_userThread.setMbThreadId(mbThreadId);
	}

	public long getTopMBMessageId() {
		return _userThread.getTopMBMessageId();
	}

	public void setTopMBMessageId(long topMBMessageId) {
		_userThread.setTopMBMessageId(topMBMessageId);
	}

	public boolean getRead() {
		return _userThread.getRead();
	}

	public boolean isRead() {
		return _userThread.isRead();
	}

	public void setRead(boolean read) {
		_userThread.setRead(read);
	}

	public boolean getDeleted() {
		return _userThread.getDeleted();
	}

	public boolean isDeleted() {
		return _userThread.isDeleted();
	}

	public void setDeleted(boolean deleted) {
		_userThread.setDeleted(deleted);
	}

	public boolean isNew() {
		return _userThread.isNew();
	}

	public void setNew(boolean n) {
		_userThread.setNew(n);
	}

	public boolean isCachedModel() {
		return _userThread.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userThread.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userThread.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_userThread.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userThread.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userThread.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userThread.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _userThread.clone();
	}

	public int compareTo(
		com.liferay.privatemessaging.model.UserThread userThread) {
		return _userThread.compareTo(userThread);
	}

	public int hashCode() {
		return _userThread.hashCode();
	}

	public com.liferay.privatemessaging.model.UserThread toEscapedModel() {
		return _userThread.toEscapedModel();
	}

	public java.lang.String toString() {
		return _userThread.toString();
	}

	public java.lang.String toXmlString() {
		return _userThread.toXmlString();
	}

	public UserThread getWrappedUserThread() {
		return _userThread;
	}

	private UserThread _userThread;
}