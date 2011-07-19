/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.model;

import com.liferay.mail.service.MessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageClp extends BaseModelImpl<Message> implements Message {
	public MessageClp() {
	}

	public Class<?> getModelClass() {
		return Message.class;
	}

	public String getModelClassName() {
		return Message.class.getName();
	}

	public long getPrimaryKey() {
		return _messageId;
	}

	public void setPrimaryKey(long primaryKey) {
		setMessageId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_messageId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public String getSender() {
		return _sender;
	}

	public void setSender(String sender) {
		_sender = sender;
	}

	public String getTo() {
		return _to;
	}

	public void setTo(String to) {
		_to = to;
	}

	public String getCc() {
		return _cc;
	}

	public void setCc(String cc) {
		_cc = cc;
	}

	public String getBcc() {
		return _bcc;
	}

	public void setBcc(String bcc) {
		_bcc = bcc;
	}

	public Date getSentDate() {
		return _sentDate;
	}

	public void setSentDate(Date sentDate) {
		_sentDate = sentDate;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getPreview() {
		return _preview;
	}

	public void setPreview(String preview) {
		_preview = preview;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getFlags() {
		return _flags;
	}

	public void setFlags(String flags) {
		_flags = flags;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public long getRemoteMessageId() {
		return _remoteMessageId;
	}

	public void setRemoteMessageId(long remoteMessageId) {
		_remoteMessageId = remoteMessageId;
	}

	public long getGroupId() {
		throw new UnsupportedOperationException();
	}

	public boolean hasFlag(int flag) {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		MessageLocalServiceUtil.updateMessage(this);
	}

	@Override
	public Message toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (Message)Proxy.newProxyInstance(Message.class.getClassLoader(),
				new Class[] { Message.class }, new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		MessageClp clone = new MessageClp();

		clone.setMessageId(getMessageId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountId(getAccountId());
		clone.setFolderId(getFolderId());
		clone.setSender(getSender());
		clone.setTo(getTo());
		clone.setCc(getCc());
		clone.setBcc(getBcc());
		clone.setSentDate(getSentDate());
		clone.setSubject(getSubject());
		clone.setPreview(getPreview());
		clone.setBody(getBody());
		clone.setFlags(getFlags());
		clone.setSize(getSize());
		clone.setRemoteMessageId(getRemoteMessageId());

		return clone;
	}

	public int compareTo(Message message) {
		int value = 0;

		value = DateUtil.compareTo(getSentDate(), message.getSentDate());

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

		MessageClp message = null;

		try {
			message = (MessageClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = message.getPrimaryKey();

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
		StringBundler sb = new StringBundler(39);

		sb.append("{messageId=");
		sb.append(getMessageId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountId=");
		sb.append(getAccountId());
		sb.append(", folderId=");
		sb.append(getFolderId());
		sb.append(", sender=");
		sb.append(getSender());
		sb.append(", to=");
		sb.append(getTo());
		sb.append(", cc=");
		sb.append(getCc());
		sb.append(", bcc=");
		sb.append(getBcc());
		sb.append(", sentDate=");
		sb.append(getSentDate());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", preview=");
		sb.append(getPreview());
		sb.append(", body=");
		sb.append(getBody());
		sb.append(", flags=");
		sb.append(getFlags());
		sb.append(", size=");
		sb.append(getSize());
		sb.append(", remoteMessageId=");
		sb.append(getRemoteMessageId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(61);

		sb.append("<model><model-name>");
		sb.append("com.liferay.mail.model.Message");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>messageId</column-name><column-value><![CDATA[");
		sb.append(getMessageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountId</column-name><column-value><![CDATA[");
		sb.append(getAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>folderId</column-name><column-value><![CDATA[");
		sb.append(getFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sender</column-name><column-value><![CDATA[");
		sb.append(getSender());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>to</column-name><column-value><![CDATA[");
		sb.append(getTo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cc</column-name><column-value><![CDATA[");
		sb.append(getCc());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bcc</column-name><column-value><![CDATA[");
		sb.append(getBcc());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sentDate</column-name><column-value><![CDATA[");
		sb.append(getSentDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>preview</column-name><column-value><![CDATA[");
		sb.append(getPreview());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>flags</column-name><column-value><![CDATA[");
		sb.append(getFlags());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remoteMessageId</column-name><column-value><![CDATA[");
		sb.append(getRemoteMessageId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _messageId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountId;
	private long _folderId;
	private String _sender;
	private String _to;
	private String _cc;
	private String _bcc;
	private Date _sentDate;
	private String _subject;
	private String _preview;
	private String _body;
	private String _flags;
	private long _size;
	private long _remoteMessageId;
}