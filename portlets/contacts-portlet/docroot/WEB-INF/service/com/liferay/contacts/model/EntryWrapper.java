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

package com.liferay.contacts.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Entry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Entry
 * @generated
 */
public class EntryWrapper implements Entry, ModelWrapper<Entry> {
	public EntryWrapper(Entry entry) {
		_entry = entry;
	}

	public Class<?> getModelClass() {
		return Entry.class;
	}

	public String getModelClassName() {
		return Entry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fullName", getFullName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("comments", getComments());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

	/**
	* Returns the primary key of this entry.
	*
	* @return the primary key of this entry
	*/
	public long getPrimaryKey() {
		return _entry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this entry.
	*
	* @param primaryKey the primary key of this entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_entry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this entry.
	*
	* @return the entry ID of this entry
	*/
	public long getEntryId() {
		return _entry.getEntryId();
	}

	/**
	* Sets the entry ID of this entry.
	*
	* @param entryId the entry ID of this entry
	*/
	public void setEntryId(long entryId) {
		_entry.setEntryId(entryId);
	}

	/**
	* Returns the group ID of this entry.
	*
	* @return the group ID of this entry
	*/
	public long getGroupId() {
		return _entry.getGroupId();
	}

	/**
	* Sets the group ID of this entry.
	*
	* @param groupId the group ID of this entry
	*/
	public void setGroupId(long groupId) {
		_entry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this entry.
	*
	* @return the company ID of this entry
	*/
	public long getCompanyId() {
		return _entry.getCompanyId();
	}

	/**
	* Sets the company ID of this entry.
	*
	* @param companyId the company ID of this entry
	*/
	public void setCompanyId(long companyId) {
		_entry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this entry.
	*
	* @return the user ID of this entry
	*/
	public long getUserId() {
		return _entry.getUserId();
	}

	/**
	* Sets the user ID of this entry.
	*
	* @param userId the user ID of this entry
	*/
	public void setUserId(long userId) {
		_entry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this entry.
	*
	* @return the user uuid of this entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entry.getUserUuid();
	}

	/**
	* Sets the user uuid of this entry.
	*
	* @param userUuid the user uuid of this entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_entry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this entry.
	*
	* @return the user name of this entry
	*/
	public java.lang.String getUserName() {
		return _entry.getUserName();
	}

	/**
	* Sets the user name of this entry.
	*
	* @param userName the user name of this entry
	*/
	public void setUserName(java.lang.String userName) {
		_entry.setUserName(userName);
	}

	/**
	* Returns the create date of this entry.
	*
	* @return the create date of this entry
	*/
	public java.util.Date getCreateDate() {
		return _entry.getCreateDate();
	}

	/**
	* Sets the create date of this entry.
	*
	* @param createDate the create date of this entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_entry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this entry.
	*
	* @return the modified date of this entry
	*/
	public java.util.Date getModifiedDate() {
		return _entry.getModifiedDate();
	}

	/**
	* Sets the modified date of this entry.
	*
	* @param modifiedDate the modified date of this entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_entry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the full name of this entry.
	*
	* @return the full name of this entry
	*/
	public java.lang.String getFullName() {
		return _entry.getFullName();
	}

	/**
	* Sets the full name of this entry.
	*
	* @param fullName the full name of this entry
	*/
	public void setFullName(java.lang.String fullName) {
		_entry.setFullName(fullName);
	}

	/**
	* Returns the email address of this entry.
	*
	* @return the email address of this entry
	*/
	public java.lang.String getEmailAddress() {
		return _entry.getEmailAddress();
	}

	/**
	* Sets the email address of this entry.
	*
	* @param emailAddress the email address of this entry
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_entry.setEmailAddress(emailAddress);
	}

	/**
	* Returns the comments of this entry.
	*
	* @return the comments of this entry
	*/
	public java.lang.String getComments() {
		return _entry.getComments();
	}

	/**
	* Sets the comments of this entry.
	*
	* @param comments the comments of this entry
	*/
	public void setComments(java.lang.String comments) {
		_entry.setComments(comments);
	}

	public boolean isNew() {
		return _entry.isNew();
	}

	public void setNew(boolean n) {
		_entry.setNew(n);
	}

	public boolean isCachedModel() {
		return _entry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_entry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _entry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _entry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_entry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _entry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_entry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EntryWrapper((Entry)_entry.clone());
	}

	public int compareTo(com.liferay.contacts.model.Entry entry) {
		return _entry.compareTo(entry);
	}

	@Override
	public int hashCode() {
		return _entry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.contacts.model.Entry> toCacheModel() {
		return _entry.toCacheModel();
	}

	public com.liferay.contacts.model.Entry toEscapedModel() {
		return new EntryWrapper(_entry.toEscapedModel());
	}

	public com.liferay.contacts.model.Entry toUnescapedModel() {
		return new EntryWrapper(_entry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _entry.toString();
	}

	public java.lang.String toXmlString() {
		return _entry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_entry.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Entry getWrappedEntry() {
		return _entry;
	}

	public Entry getWrappedModel() {
		return _entry;
	}

	public void resetOriginalValues() {
		_entry.resetOriginalValues();
	}

	private Entry _entry;
}