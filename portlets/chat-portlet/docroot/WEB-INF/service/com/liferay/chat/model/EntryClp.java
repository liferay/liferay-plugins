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

package com.liferay.chat.model;

import com.liferay.chat.service.EntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryClp extends BaseModelImpl<Entry> implements Entry {
	public EntryClp() {
	}

	public Class<?> getModelClass() {
		return Entry.class;
	}

	public String getModelClassName() {
		return Entry.class.getName();
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_entryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public long getFromUserId() {
		return _fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		_fromUserId = fromUserId;
	}

	public String getFromUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getFromUserId(), "uuid", _fromUserUuid);
	}

	public void setFromUserUuid(String fromUserUuid) {
		_fromUserUuid = fromUserUuid;
	}

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		_toUserId = toUserId;
	}

	public String getToUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getToUserId(), "uuid", _toUserUuid);
	}

	public void setToUserUuid(String toUserUuid) {
		_toUserUuid = toUserUuid;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void persist() throws SystemException {
		EntryLocalServiceUtil.updateEntry(this);
	}

	@Override
	public Entry toEscapedModel() {
		return (Entry)Proxy.newProxyInstance(Entry.class.getClassLoader(),
			new Class[] { Entry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EntryClp clone = new EntryClp();

		clone.setEntryId(getEntryId());
		clone.setCreateDate(getCreateDate());
		clone.setFromUserId(getFromUserId());
		clone.setToUserId(getToUserId());
		clone.setContent(getContent());

		return clone;
	}

	public int compareTo(Entry entry) {
		int value = 0;

		if (getCreateDate() < entry.getCreateDate()) {
			value = -1;
		}
		else if (getCreateDate() > entry.getCreateDate()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		EntryClp entry = null;

		try {
			entry = (EntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = entry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", fromUserId=");
		sb.append(getFromUserId());
		sb.append(", toUserId=");
		sb.append(getToUserId());
		sb.append(", content=");
		sb.append(getContent());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.chat.model.Entry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fromUserId</column-name><column-value><![CDATA[");
		sb.append(getFromUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toUserId</column-name><column-value><![CDATA[");
		sb.append(getToUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _entryId;
	private long _createDate;
	private long _fromUserId;
	private String _fromUserUuid;
	private long _toUserId;
	private String _toUserUuid;
	private String _content;
}