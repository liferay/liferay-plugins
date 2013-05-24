/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.model;

import com.liferay.portal.model.ModelWrapper;

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

	@Override
	public Class<?> getModelClass() {
		return Entry.class;
	}

	@Override
	public String getModelClassName() {
		return Entry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("createDate", getCreateDate());
		attributes.put("fromUserId", getFromUserId());
		attributes.put("toUserId", getToUserId());
		attributes.put("content", getContent());
		attributes.put("flag", getFlag());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long fromUserId = (Long)attributes.get("fromUserId");

		if (fromUserId != null) {
			setFromUserId(fromUserId);
		}

		Long toUserId = (Long)attributes.get("toUserId");

		if (toUserId != null) {
			setToUserId(toUserId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer flag = (Integer)attributes.get("flag");

		if (flag != null) {
			setFlag(flag);
		}
	}

	/**
	* Returns the primary key of this entry.
	*
	* @return the primary key of this entry
	*/
	@Override
	public long getPrimaryKey() {
		return _entry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this entry.
	*
	* @param primaryKey the primary key of this entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_entry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this entry.
	*
	* @return the entry ID of this entry
	*/
	@Override
	public long getEntryId() {
		return _entry.getEntryId();
	}

	/**
	* Sets the entry ID of this entry.
	*
	* @param entryId the entry ID of this entry
	*/
	@Override
	public void setEntryId(long entryId) {
		_entry.setEntryId(entryId);
	}

	/**
	* Returns the create date of this entry.
	*
	* @return the create date of this entry
	*/
	@Override
	public long getCreateDate() {
		return _entry.getCreateDate();
	}

	/**
	* Sets the create date of this entry.
	*
	* @param createDate the create date of this entry
	*/
	@Override
	public void setCreateDate(long createDate) {
		_entry.setCreateDate(createDate);
	}

	/**
	* Returns the from user ID of this entry.
	*
	* @return the from user ID of this entry
	*/
	@Override
	public long getFromUserId() {
		return _entry.getFromUserId();
	}

	/**
	* Sets the from user ID of this entry.
	*
	* @param fromUserId the from user ID of this entry
	*/
	@Override
	public void setFromUserId(long fromUserId) {
		_entry.setFromUserId(fromUserId);
	}

	/**
	* Returns the from user uuid of this entry.
	*
	* @return the from user uuid of this entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getFromUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entry.getFromUserUuid();
	}

	/**
	* Sets the from user uuid of this entry.
	*
	* @param fromUserUuid the from user uuid of this entry
	*/
	@Override
	public void setFromUserUuid(java.lang.String fromUserUuid) {
		_entry.setFromUserUuid(fromUserUuid);
	}

	/**
	* Returns the to user ID of this entry.
	*
	* @return the to user ID of this entry
	*/
	@Override
	public long getToUserId() {
		return _entry.getToUserId();
	}

	/**
	* Sets the to user ID of this entry.
	*
	* @param toUserId the to user ID of this entry
	*/
	@Override
	public void setToUserId(long toUserId) {
		_entry.setToUserId(toUserId);
	}

	/**
	* Returns the to user uuid of this entry.
	*
	* @return the to user uuid of this entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getToUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entry.getToUserUuid();
	}

	/**
	* Sets the to user uuid of this entry.
	*
	* @param toUserUuid the to user uuid of this entry
	*/
	@Override
	public void setToUserUuid(java.lang.String toUserUuid) {
		_entry.setToUserUuid(toUserUuid);
	}

	/**
	* Returns the content of this entry.
	*
	* @return the content of this entry
	*/
	@Override
	public java.lang.String getContent() {
		return _entry.getContent();
	}

	/**
	* Sets the content of this entry.
	*
	* @param content the content of this entry
	*/
	@Override
	public void setContent(java.lang.String content) {
		_entry.setContent(content);
	}

	/**
	* Returns the flag of this entry.
	*
	* @return the flag of this entry
	*/
	@Override
	public int getFlag() {
		return _entry.getFlag();
	}

	/**
	* Sets the flag of this entry.
	*
	* @param flag the flag of this entry
	*/
	@Override
	public void setFlag(int flag) {
		_entry.setFlag(flag);
	}

	@Override
	public boolean isNew() {
		return _entry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_entry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _entry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _entry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _entry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_entry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _entry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_entry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_entry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_entry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EntryWrapper((Entry)_entry.clone());
	}

	@Override
	public int compareTo(com.liferay.chat.model.Entry entry) {
		return _entry.compareTo(entry);
	}

	@Override
	public int hashCode() {
		return _entry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.chat.model.Entry> toCacheModel() {
		return _entry.toCacheModel();
	}

	@Override
	public com.liferay.chat.model.Entry toEscapedModel() {
		return new EntryWrapper(_entry.toEscapedModel());
	}

	@Override
	public com.liferay.chat.model.Entry toUnescapedModel() {
		return new EntryWrapper(_entry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _entry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_entry.persist();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Entry getWrappedEntry() {
		return _entry;
	}

	@Override
	public Entry getWrappedModel() {
		return _entry;
	}

	@Override
	public void resetOriginalValues() {
		_entry.resetOriginalValues();
	}

	private Entry _entry;
}