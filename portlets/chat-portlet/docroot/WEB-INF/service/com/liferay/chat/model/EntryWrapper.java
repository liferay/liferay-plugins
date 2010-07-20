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

package com.liferay.chat.model;

/**
 * <p>
 * This class is a wrapper for {@link Entry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Entry
 * @generated
 */
public class EntryWrapper implements Entry {
	public EntryWrapper(Entry entry) {
		_entry = entry;
	}

	public long getPrimaryKey() {
		return _entry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_entry.setPrimaryKey(pk);
	}

	public long getEntryId() {
		return _entry.getEntryId();
	}

	public void setEntryId(long entryId) {
		_entry.setEntryId(entryId);
	}

	public long getCreateDate() {
		return _entry.getCreateDate();
	}

	public void setCreateDate(long createDate) {
		_entry.setCreateDate(createDate);
	}

	public long getFromUserId() {
		return _entry.getFromUserId();
	}

	public void setFromUserId(long fromUserId) {
		_entry.setFromUserId(fromUserId);
	}

	public java.lang.String getFromUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entry.getFromUserUuid();
	}

	public void setFromUserUuid(java.lang.String fromUserUuid) {
		_entry.setFromUserUuid(fromUserUuid);
	}

	public long getToUserId() {
		return _entry.getToUserId();
	}

	public void setToUserId(long toUserId) {
		_entry.setToUserId(toUserId);
	}

	public java.lang.String getToUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entry.getToUserUuid();
	}

	public void setToUserUuid(java.lang.String toUserUuid) {
		_entry.setToUserUuid(toUserUuid);
	}

	public java.lang.String getContent() {
		return _entry.getContent();
	}

	public void setContent(java.lang.String content) {
		_entry.setContent(content);
	}

	public com.liferay.chat.model.Entry toEscapedModel() {
		return _entry.toEscapedModel();
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

	public void setEscapedModel(boolean escapedModel) {
		_entry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _entry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _entry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_entry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _entry.clone();
	}

	public int compareTo(com.liferay.chat.model.Entry entry) {
		return _entry.compareTo(entry);
	}

	public int hashCode() {
		return _entry.hashCode();
	}

	public java.lang.String toString() {
		return _entry.toString();
	}

	public java.lang.String toXmlString() {
		return _entry.toXmlString();
	}

	public Entry getWrappedEntry() {
		return _entry;
	}

	private Entry _entry;
}