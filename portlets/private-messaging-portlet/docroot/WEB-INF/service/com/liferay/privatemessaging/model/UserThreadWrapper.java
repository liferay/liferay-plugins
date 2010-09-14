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

	/**
	* Gets the primary key of this user thread.
	*
	* @return the primary key of this user thread
	*/
	public long getPrimaryKey() {
		return _userThread.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user thread
	*
	* @param pk the primary key of this user thread
	*/
	public void setPrimaryKey(long pk) {
		_userThread.setPrimaryKey(pk);
	}

	/**
	* Gets the user thread id of this user thread.
	*
	* @return the user thread id of this user thread
	*/
	public long getUserThreadId() {
		return _userThread.getUserThreadId();
	}

	/**
	* Sets the user thread id of this user thread.
	*
	* @param userThreadId the user thread id of this user thread
	*/
	public void setUserThreadId(long userThreadId) {
		_userThread.setUserThreadId(userThreadId);
	}

	/**
	* Gets the company id of this user thread.
	*
	* @return the company id of this user thread
	*/
	public long getCompanyId() {
		return _userThread.getCompanyId();
	}

	/**
	* Sets the company id of this user thread.
	*
	* @param companyId the company id of this user thread
	*/
	public void setCompanyId(long companyId) {
		_userThread.setCompanyId(companyId);
	}

	/**
	* Gets the user id of this user thread.
	*
	* @return the user id of this user thread
	*/
	public long getUserId() {
		return _userThread.getUserId();
	}

	/**
	* Sets the user id of this user thread.
	*
	* @param userId the user id of this user thread
	*/
	public void setUserId(long userId) {
		_userThread.setUserId(userId);
	}

	/**
	* Gets the user uuid of this user thread.
	*
	* @return the user uuid of this user thread
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThread.getUserUuid();
	}

	/**
	* Sets the user uuid of this user thread.
	*
	* @param userUuid the user uuid of this user thread
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_userThread.setUserUuid(userUuid);
	}

	/**
	* Gets the create date of this user thread.
	*
	* @return the create date of this user thread
	*/
	public java.util.Date getCreateDate() {
		return _userThread.getCreateDate();
	}

	/**
	* Sets the create date of this user thread.
	*
	* @param createDate the create date of this user thread
	*/
	public void setCreateDate(java.util.Date createDate) {
		_userThread.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this user thread.
	*
	* @return the modified date of this user thread
	*/
	public java.util.Date getModifiedDate() {
		return _userThread.getModifiedDate();
	}

	/**
	* Sets the modified date of this user thread.
	*
	* @param modifiedDate the modified date of this user thread
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_userThread.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the mb thread id of this user thread.
	*
	* @return the mb thread id of this user thread
	*/
	public long getMbThreadId() {
		return _userThread.getMbThreadId();
	}

	/**
	* Sets the mb thread id of this user thread.
	*
	* @param mbThreadId the mb thread id of this user thread
	*/
	public void setMbThreadId(long mbThreadId) {
		_userThread.setMbThreadId(mbThreadId);
	}

	/**
	* Gets the top m b message id of this user thread.
	*
	* @return the top m b message id of this user thread
	*/
	public long getTopMBMessageId() {
		return _userThread.getTopMBMessageId();
	}

	/**
	* Sets the top m b message id of this user thread.
	*
	* @param topMBMessageId the top m b message id of this user thread
	*/
	public void setTopMBMessageId(long topMBMessageId) {
		_userThread.setTopMBMessageId(topMBMessageId);
	}

	/**
	* Gets the read of this user thread.
	*
	* @return the read of this user thread
	*/
	public boolean getRead() {
		return _userThread.getRead();
	}

	/**
	* Determines if this user thread is read.
	*
	* @return <code>true</code> if this user thread is read; <code>false</code> otherwise
	*/
	public boolean isRead() {
		return _userThread.isRead();
	}

	/**
	* Sets whether this {$entity.humanName} is read.
	*
	* @param read the read of this user thread
	*/
	public void setRead(boolean read) {
		_userThread.setRead(read);
	}

	/**
	* Gets the deleted of this user thread.
	*
	* @return the deleted of this user thread
	*/
	public boolean getDeleted() {
		return _userThread.getDeleted();
	}

	/**
	* Determines if this user thread is deleted.
	*
	* @return <code>true</code> if this user thread is deleted; <code>false</code> otherwise
	*/
	public boolean isDeleted() {
		return _userThread.isDeleted();
	}

	/**
	* Sets whether this {$entity.humanName} is deleted.
	*
	* @param deleted the deleted of this user thread
	*/
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