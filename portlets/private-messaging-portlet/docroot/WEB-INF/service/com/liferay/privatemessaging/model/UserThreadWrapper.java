/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserThread}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserThread
 * @generated
 */
public class UserThreadWrapper implements UserThread, ModelWrapper<UserThread> {
	public UserThreadWrapper(UserThread userThread) {
		_userThread = userThread;
	}

	public Class<?> getModelClass() {
		return UserThread.class;
	}

	public String getModelClassName() {
		return UserThread.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userThreadId", getUserThreadId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("mbThreadId", getMbThreadId());
		attributes.put("topMBMessageId", getTopMBMessageId());
		attributes.put("read", getRead());
		attributes.put("deleted", getDeleted());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long userThreadId = (Long)attributes.get("userThreadId");

		if (userThreadId != null) {
			setUserThreadId(userThreadId);
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

		Long mbThreadId = (Long)attributes.get("mbThreadId");

		if (mbThreadId != null) {
			setMbThreadId(mbThreadId);
		}

		Long topMBMessageId = (Long)attributes.get("topMBMessageId");

		if (topMBMessageId != null) {
			setTopMBMessageId(topMBMessageId);
		}

		Boolean read = (Boolean)attributes.get("read");

		if (read != null) {
			setRead(read);
		}

		Boolean deleted = (Boolean)attributes.get("deleted");

		if (deleted != null) {
			setDeleted(deleted);
		}
	}

	/**
	* Returns the primary key of this user thread.
	*
	* @return the primary key of this user thread
	*/
	public long getPrimaryKey() {
		return _userThread.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user thread.
	*
	* @param primaryKey the primary key of this user thread
	*/
	public void setPrimaryKey(long primaryKey) {
		_userThread.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user thread ID of this user thread.
	*
	* @return the user thread ID of this user thread
	*/
	public long getUserThreadId() {
		return _userThread.getUserThreadId();
	}

	/**
	* Sets the user thread ID of this user thread.
	*
	* @param userThreadId the user thread ID of this user thread
	*/
	public void setUserThreadId(long userThreadId) {
		_userThread.setUserThreadId(userThreadId);
	}

	/**
	* Returns the company ID of this user thread.
	*
	* @return the company ID of this user thread
	*/
	public long getCompanyId() {
		return _userThread.getCompanyId();
	}

	/**
	* Sets the company ID of this user thread.
	*
	* @param companyId the company ID of this user thread
	*/
	public void setCompanyId(long companyId) {
		_userThread.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this user thread.
	*
	* @return the user ID of this user thread
	*/
	public long getUserId() {
		return _userThread.getUserId();
	}

	/**
	* Sets the user ID of this user thread.
	*
	* @param userId the user ID of this user thread
	*/
	public void setUserId(long userId) {
		_userThread.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user thread.
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
	* Returns the user name of this user thread.
	*
	* @return the user name of this user thread
	*/
	public java.lang.String getUserName() {
		return _userThread.getUserName();
	}

	/**
	* Sets the user name of this user thread.
	*
	* @param userName the user name of this user thread
	*/
	public void setUserName(java.lang.String userName) {
		_userThread.setUserName(userName);
	}

	/**
	* Returns the create date of this user thread.
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
	* Returns the modified date of this user thread.
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
	* Returns the mb thread ID of this user thread.
	*
	* @return the mb thread ID of this user thread
	*/
	public long getMbThreadId() {
		return _userThread.getMbThreadId();
	}

	/**
	* Sets the mb thread ID of this user thread.
	*
	* @param mbThreadId the mb thread ID of this user thread
	*/
	public void setMbThreadId(long mbThreadId) {
		_userThread.setMbThreadId(mbThreadId);
	}

	/**
	* Returns the top m b message ID of this user thread.
	*
	* @return the top m b message ID of this user thread
	*/
	public long getTopMBMessageId() {
		return _userThread.getTopMBMessageId();
	}

	/**
	* Sets the top m b message ID of this user thread.
	*
	* @param topMBMessageId the top m b message ID of this user thread
	*/
	public void setTopMBMessageId(long topMBMessageId) {
		_userThread.setTopMBMessageId(topMBMessageId);
	}

	/**
	* Returns the read of this user thread.
	*
	* @return the read of this user thread
	*/
	public boolean getRead() {
		return _userThread.getRead();
	}

	/**
	* Returns <code>true</code> if this user thread is read.
	*
	* @return <code>true</code> if this user thread is read; <code>false</code> otherwise
	*/
	public boolean isRead() {
		return _userThread.isRead();
	}

	/**
	* Sets whether this user thread is read.
	*
	* @param read the read of this user thread
	*/
	public void setRead(boolean read) {
		_userThread.setRead(read);
	}

	/**
	* Returns the deleted of this user thread.
	*
	* @return the deleted of this user thread
	*/
	public boolean getDeleted() {
		return _userThread.getDeleted();
	}

	/**
	* Returns <code>true</code> if this user thread is deleted.
	*
	* @return <code>true</code> if this user thread is deleted; <code>false</code> otherwise
	*/
	public boolean isDeleted() {
		return _userThread.isDeleted();
	}

	/**
	* Sets whether this user thread is deleted.
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _userThread.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userThread.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userThread.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userThread.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserThreadWrapper((UserThread)_userThread.clone());
	}

	public int compareTo(
		com.liferay.privatemessaging.model.UserThread userThread) {
		return _userThread.compareTo(userThread);
	}

	@Override
	public int hashCode() {
		return _userThread.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.privatemessaging.model.UserThread> toCacheModel() {
		return _userThread.toCacheModel();
	}

	public com.liferay.privatemessaging.model.UserThread toEscapedModel() {
		return new UserThreadWrapper(_userThread.toEscapedModel());
	}

	public com.liferay.privatemessaging.model.UserThread toUnescapedModel() {
		return new UserThreadWrapper(_userThread.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userThread.toString();
	}

	public java.lang.String toXmlString() {
		return _userThread.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userThread.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserThread getWrappedUserThread() {
		return _userThread;
	}

	public UserThread getWrappedModel() {
		return _userThread;
	}

	public void resetOriginalValues() {
		_userThread.resetOriginalValues();
	}

	private UserThread _userThread;
}