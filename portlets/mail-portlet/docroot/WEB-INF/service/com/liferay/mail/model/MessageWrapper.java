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

/**
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

	public Class<?> getModelClass() {
		return Message.class;
	}

	public String getModelClassName() {
		return Message.class.getName();
	}

	/**
	* Returns the primary key of this message.
	*
	* @return the primary key of this message
	*/
	public long getPrimaryKey() {
		return _message.getPrimaryKey();
	}

	/**
	* Sets the primary key of this message.
	*
	* @param primaryKey the primary key of this message
	*/
	public void setPrimaryKey(long primaryKey) {
		_message.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the message ID of this message.
	*
	* @return the message ID of this message
	*/
	public long getMessageId() {
		return _message.getMessageId();
	}

	/**
	* Sets the message ID of this message.
	*
	* @param messageId the message ID of this message
	*/
	public void setMessageId(long messageId) {
		_message.setMessageId(messageId);
	}

	/**
	* Returns the company ID of this message.
	*
	* @return the company ID of this message
	*/
	public long getCompanyId() {
		return _message.getCompanyId();
	}

	/**
	* Sets the company ID of this message.
	*
	* @param companyId the company ID of this message
	*/
	public void setCompanyId(long companyId) {
		_message.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this message.
	*
	* @return the user ID of this message
	*/
	public long getUserId() {
		return _message.getUserId();
	}

	/**
	* Sets the user ID of this message.
	*
	* @param userId the user ID of this message
	*/
	public void setUserId(long userId) {
		_message.setUserId(userId);
	}

	/**
	* Returns the user uuid of this message.
	*
	* @return the user uuid of this message
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _message.getUserUuid();
	}

	/**
	* Sets the user uuid of this message.
	*
	* @param userUuid the user uuid of this message
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_message.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this message.
	*
	* @return the user name of this message
	*/
	public java.lang.String getUserName() {
		return _message.getUserName();
	}

	/**
	* Sets the user name of this message.
	*
	* @param userName the user name of this message
	*/
	public void setUserName(java.lang.String userName) {
		_message.setUserName(userName);
	}

	/**
	* Returns the create date of this message.
	*
	* @return the create date of this message
	*/
	public java.util.Date getCreateDate() {
		return _message.getCreateDate();
	}

	/**
	* Sets the create date of this message.
	*
	* @param createDate the create date of this message
	*/
	public void setCreateDate(java.util.Date createDate) {
		_message.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this message.
	*
	* @return the modified date of this message
	*/
	public java.util.Date getModifiedDate() {
		return _message.getModifiedDate();
	}

	/**
	* Sets the modified date of this message.
	*
	* @param modifiedDate the modified date of this message
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_message.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account ID of this message.
	*
	* @return the account ID of this message
	*/
	public long getAccountId() {
		return _message.getAccountId();
	}

	/**
	* Sets the account ID of this message.
	*
	* @param accountId the account ID of this message
	*/
	public void setAccountId(long accountId) {
		_message.setAccountId(accountId);
	}

	/**
	* Returns the folder ID of this message.
	*
	* @return the folder ID of this message
	*/
	public long getFolderId() {
		return _message.getFolderId();
	}

	/**
	* Sets the folder ID of this message.
	*
	* @param folderId the folder ID of this message
	*/
	public void setFolderId(long folderId) {
		_message.setFolderId(folderId);
	}

	/**
	* Returns the sender of this message.
	*
	* @return the sender of this message
	*/
	public java.lang.String getSender() {
		return _message.getSender();
	}

	/**
	* Sets the sender of this message.
	*
	* @param sender the sender of this message
	*/
	public void setSender(java.lang.String sender) {
		_message.setSender(sender);
	}

	/**
	* Returns the to of this message.
	*
	* @return the to of this message
	*/
	public java.lang.String getTo() {
		return _message.getTo();
	}

	/**
	* Sets the to of this message.
	*
	* @param to the to of this message
	*/
	public void setTo(java.lang.String to) {
		_message.setTo(to);
	}

	/**
	* Returns the cc of this message.
	*
	* @return the cc of this message
	*/
	public java.lang.String getCc() {
		return _message.getCc();
	}

	/**
	* Sets the cc of this message.
	*
	* @param cc the cc of this message
	*/
	public void setCc(java.lang.String cc) {
		_message.setCc(cc);
	}

	/**
	* Returns the bcc of this message.
	*
	* @return the bcc of this message
	*/
	public java.lang.String getBcc() {
		return _message.getBcc();
	}

	/**
	* Sets the bcc of this message.
	*
	* @param bcc the bcc of this message
	*/
	public void setBcc(java.lang.String bcc) {
		_message.setBcc(bcc);
	}

	/**
	* Returns the sent date of this message.
	*
	* @return the sent date of this message
	*/
	public java.util.Date getSentDate() {
		return _message.getSentDate();
	}

	/**
	* Sets the sent date of this message.
	*
	* @param sentDate the sent date of this message
	*/
	public void setSentDate(java.util.Date sentDate) {
		_message.setSentDate(sentDate);
	}

	/**
	* Returns the subject of this message.
	*
	* @return the subject of this message
	*/
	public java.lang.String getSubject() {
		return _message.getSubject();
	}

	/**
	* Sets the subject of this message.
	*
	* @param subject the subject of this message
	*/
	public void setSubject(java.lang.String subject) {
		_message.setSubject(subject);
	}

	/**
	* Returns the preview of this message.
	*
	* @return the preview of this message
	*/
	public java.lang.String getPreview() {
		return _message.getPreview();
	}

	/**
	* Sets the preview of this message.
	*
	* @param preview the preview of this message
	*/
	public void setPreview(java.lang.String preview) {
		_message.setPreview(preview);
	}

	/**
	* Returns the body of this message.
	*
	* @return the body of this message
	*/
	public java.lang.String getBody() {
		return _message.getBody();
	}

	/**
	* Sets the body of this message.
	*
	* @param body the body of this message
	*/
	public void setBody(java.lang.String body) {
		_message.setBody(body);
	}

	/**
	* Returns the flags of this message.
	*
	* @return the flags of this message
	*/
	public java.lang.String getFlags() {
		return _message.getFlags();
	}

	/**
	* Sets the flags of this message.
	*
	* @param flags the flags of this message
	*/
	public void setFlags(java.lang.String flags) {
		_message.setFlags(flags);
	}

	/**
	* Returns the size of this message.
	*
	* @return the size of this message
	*/
	public long getSize() {
		return _message.getSize();
	}

	/**
	* Sets the size of this message.
	*
	* @param size the size of this message
	*/
	public void setSize(long size) {
		_message.setSize(size);
	}

	/**
	* Returns the remote message ID of this message.
	*
	* @return the remote message ID of this message
	*/
	public long getRemoteMessageId() {
		return _message.getRemoteMessageId();
	}

	/**
	* Sets the remote message ID of this message.
	*
	* @param remoteMessageId the remote message ID of this message
	*/
	public void setRemoteMessageId(long remoteMessageId) {
		_message.setRemoteMessageId(remoteMessageId);
	}

	public boolean isNew() {
		return _message.isNew();
	}

	public void setNew(boolean n) {
		_message.setNew(n);
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

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_message.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _message.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_message.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MessageWrapper((Message)_message.clone());
	}

	public int compareTo(com.liferay.mail.model.Message message) {
		return _message.compareTo(message);
	}

	@Override
	public int hashCode() {
		return _message.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.mail.model.Message> toCacheModel() {
		return _message.toCacheModel();
	}

	public com.liferay.mail.model.Message toEscapedModel() {
		return new MessageWrapper(_message.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _message.toString();
	}

	public java.lang.String toXmlString() {
		return _message.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_message.persist();
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

	public void resetOriginalValues() {
		_message.resetOriginalValues();
	}

	private Message _message;
}