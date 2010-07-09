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

package com.liferay.mail.model;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link Message}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Message
 * @generated
 */
public class MessageWrapper implements Message {
	public MessageWrapper(Message message) {
		_message = message;
	}

	public long getPrimaryKey() {
		return _message.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_message.setPrimaryKey(pk);
	}

	public long getMessageId() {
		return _message.getMessageId();
	}

	public void setMessageId(long messageId) {
		_message.setMessageId(messageId);
	}

	public long getCompanyId() {
		return _message.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_message.setCompanyId(companyId);
	}

	public long getUserId() {
		return _message.getUserId();
	}

	public void setUserId(long userId) {
		_message.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _message.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_message.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _message.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_message.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _message.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_message.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _message.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_message.setModifiedDate(modifiedDate);
	}

	public long getAccountId() {
		return _message.getAccountId();
	}

	public void setAccountId(long accountId) {
		_message.setAccountId(accountId);
	}

	public long getFolderId() {
		return _message.getFolderId();
	}

	public void setFolderId(long folderId) {
		_message.setFolderId(folderId);
	}

	public java.lang.String getSender() {
		return _message.getSender();
	}

	public void setSender(java.lang.String sender) {
		_message.setSender(sender);
	}

	public java.lang.String getTo() {
		return _message.getTo();
	}

	public void setTo(java.lang.String to) {
		_message.setTo(to);
	}

	public java.lang.String getCc() {
		return _message.getCc();
	}

	public void setCc(java.lang.String cc) {
		_message.setCc(cc);
	}

	public java.lang.String getBcc() {
		return _message.getBcc();
	}

	public void setBcc(java.lang.String bcc) {
		_message.setBcc(bcc);
	}

	public java.util.Date getSentDate() {
		return _message.getSentDate();
	}

	public void setSentDate(java.util.Date sentDate) {
		_message.setSentDate(sentDate);
	}

	public java.lang.String getSubject() {
		return _message.getSubject();
	}

	public void setSubject(java.lang.String subject) {
		_message.setSubject(subject);
	}

	public java.lang.String getPreview() {
		return _message.getPreview();
	}

	public void setPreview(java.lang.String preview) {
		_message.setPreview(preview);
	}

	public java.lang.String getBody() {
		return _message.getBody();
	}

	public void setBody(java.lang.String body) {
		_message.setBody(body);
	}

	public java.lang.String getFlags() {
		return _message.getFlags();
	}

	public void setFlags(java.lang.String flags) {
		_message.setFlags(flags);
	}

	public long getSize() {
		return _message.getSize();
	}

	public void setSize(long size) {
		_message.setSize(size);
	}

	public long getRemoteMessageId() {
		return _message.getRemoteMessageId();
	}

	public void setRemoteMessageId(long remoteMessageId) {
		_message.setRemoteMessageId(remoteMessageId);
	}

	public com.liferay.mail.model.Message toEscapedModel() {
		return _message.toEscapedModel();
	}

	public boolean isNew() {
		return _message.isNew();
	}

	public boolean setNew(boolean n) {
		return _message.setNew(n);
	}

	public boolean isCachedModel() {
		return _message.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_message.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _message.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_message.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _message.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _message.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_message.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _message.clone();
	}

	public int compareTo(com.liferay.mail.model.Message message) {
		return _message.compareTo(message);
	}

	public int hashCode() {
		return _message.hashCode();
	}

	public java.lang.String toString() {
		return _message.toString();
	}

	public java.lang.String toXmlString() {
		return _message.toXmlString();
	}

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _message.getGroupId();
	}

	public boolean hasFlag(int flag) {
		return _message.hasFlag(flag);
	}

	public Message getWrappedMessage() {
		return _message;
	}

	private Message _message;
}