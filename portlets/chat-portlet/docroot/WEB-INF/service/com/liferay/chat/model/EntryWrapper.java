/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @generated
 */
@ProviderType
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

	@Override
	public boolean isCachedModel() {
		return _entry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entry.isNew();
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
	public ExpandoBridge getExpandoBridge() {
		return _entry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.chat.model.Entry> toCacheModel() {
		return _entry.toCacheModel();
	}

	@Override
	public int compareTo(com.liferay.chat.model.Entry entry) {
		return _entry.compareTo(entry);
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

	@Override
	public int hashCode() {
		return _entry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EntryWrapper((Entry)_entry.clone());
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
	* Returns the from user uuid of this entry.
	*
	* @return the from user uuid of this entry
	*/
	@Override
	public java.lang.String getFromUserUuid() {
		return _entry.getFromUserUuid();
	}

	/**
	* Returns the to user uuid of this entry.
	*
	* @return the to user uuid of this entry
	*/
	@Override
	public java.lang.String getToUserUuid() {
		return _entry.getToUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _entry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entry.toXmlString();
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
	* Returns the entry ID of this entry.
	*
	* @return the entry ID of this entry
	*/
	@Override
	public long getEntryId() {
		return _entry.getEntryId();
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
	* Returns the primary key of this entry.
	*
	* @return the primary key of this entry
	*/
	@Override
	public long getPrimaryKey() {
		return _entry.getPrimaryKey();
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

	@Override
	public void persist() {
		_entry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entry.setCachedModel(cachedModel);
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
	* Sets the create date of this entry.
	*
	* @param createDate the create date of this entry
	*/
	@Override
	public void setCreateDate(long createDate) {
		_entry.setCreateDate(createDate);
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

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_entry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_entry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_entry.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the from user uuid of this entry.
	*
	* @param fromUserUuid the from user uuid of this entry
	*/
	@Override
	public void setFromUserUuid(java.lang.String fromUserUuid) {
		_entry.setFromUserUuid(fromUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_entry.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_entry.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the to user uuid of this entry.
	*
	* @param toUserUuid the to user uuid of this entry
	*/
	@Override
	public void setToUserUuid(java.lang.String toUserUuid) {
		_entry.setToUserUuid(toUserUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntryWrapper)) {
			return false;
		}

		EntryWrapper entryWrapper = (EntryWrapper)obj;

		if (Validator.equals(_entry, entryWrapper._entry)) {
			return true;
		}

		return false;
	}

	@Override
	public Entry getWrappedModel() {
		return _entry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entry.resetOriginalValues();
	}

	private final Entry _entry;
}