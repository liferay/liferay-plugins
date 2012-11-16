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

package com.liferay.mail.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Attachment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Attachment
 * @generated
 */
public class AttachmentWrapper implements Attachment, ModelWrapper<Attachment> {
	public AttachmentWrapper(Attachment attachment) {
		_attachment = attachment;
	}

	public Class<?> getModelClass() {
		return Attachment.class;
	}

	public String getModelClassName() {
		return Attachment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("attachmentId", getAttachmentId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("accountId", getAccountId());
		attributes.put("folderId", getFolderId());
		attributes.put("messageId", getMessageId());
		attributes.put("contentPath", getContentPath());
		attributes.put("fileName", getFileName());
		attributes.put("size", getSize());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long attachmentId = (Long)attributes.get("attachmentId");

		if (attachmentId != null) {
			setAttachmentId(attachmentId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		String contentPath = (String)attributes.get("contentPath");

		if (contentPath != null) {
			setContentPath(contentPath);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}
	}

	/**
	* Returns the primary key of this attachment.
	*
	* @return the primary key of this attachment
	*/
	public long getPrimaryKey() {
		return _attachment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this attachment.
	*
	* @param primaryKey the primary key of this attachment
	*/
	public void setPrimaryKey(long primaryKey) {
		_attachment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the attachment ID of this attachment.
	*
	* @return the attachment ID of this attachment
	*/
	public long getAttachmentId() {
		return _attachment.getAttachmentId();
	}

	/**
	* Sets the attachment ID of this attachment.
	*
	* @param attachmentId the attachment ID of this attachment
	*/
	public void setAttachmentId(long attachmentId) {
		_attachment.setAttachmentId(attachmentId);
	}

	/**
	* Returns the company ID of this attachment.
	*
	* @return the company ID of this attachment
	*/
	public long getCompanyId() {
		return _attachment.getCompanyId();
	}

	/**
	* Sets the company ID of this attachment.
	*
	* @param companyId the company ID of this attachment
	*/
	public void setCompanyId(long companyId) {
		_attachment.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this attachment.
	*
	* @return the user ID of this attachment
	*/
	public long getUserId() {
		return _attachment.getUserId();
	}

	/**
	* Sets the user ID of this attachment.
	*
	* @param userId the user ID of this attachment
	*/
	public void setUserId(long userId) {
		_attachment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this attachment.
	*
	* @return the user uuid of this attachment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _attachment.getUserUuid();
	}

	/**
	* Sets the user uuid of this attachment.
	*
	* @param userUuid the user uuid of this attachment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_attachment.setUserUuid(userUuid);
	}

	/**
	* Returns the account ID of this attachment.
	*
	* @return the account ID of this attachment
	*/
	public long getAccountId() {
		return _attachment.getAccountId();
	}

	/**
	* Sets the account ID of this attachment.
	*
	* @param accountId the account ID of this attachment
	*/
	public void setAccountId(long accountId) {
		_attachment.setAccountId(accountId);
	}

	/**
	* Returns the folder ID of this attachment.
	*
	* @return the folder ID of this attachment
	*/
	public long getFolderId() {
		return _attachment.getFolderId();
	}

	/**
	* Sets the folder ID of this attachment.
	*
	* @param folderId the folder ID of this attachment
	*/
	public void setFolderId(long folderId) {
		_attachment.setFolderId(folderId);
	}

	/**
	* Returns the message ID of this attachment.
	*
	* @return the message ID of this attachment
	*/
	public long getMessageId() {
		return _attachment.getMessageId();
	}

	/**
	* Sets the message ID of this attachment.
	*
	* @param messageId the message ID of this attachment
	*/
	public void setMessageId(long messageId) {
		_attachment.setMessageId(messageId);
	}

	/**
	* Returns the content path of this attachment.
	*
	* @return the content path of this attachment
	*/
	public java.lang.String getContentPath() {
		return _attachment.getContentPath();
	}

	/**
	* Sets the content path of this attachment.
	*
	* @param contentPath the content path of this attachment
	*/
	public void setContentPath(java.lang.String contentPath) {
		_attachment.setContentPath(contentPath);
	}

	/**
	* Returns the file name of this attachment.
	*
	* @return the file name of this attachment
	*/
	public java.lang.String getFileName() {
		return _attachment.getFileName();
	}

	/**
	* Sets the file name of this attachment.
	*
	* @param fileName the file name of this attachment
	*/
	public void setFileName(java.lang.String fileName) {
		_attachment.setFileName(fileName);
	}

	/**
	* Returns the size of this attachment.
	*
	* @return the size of this attachment
	*/
	public long getSize() {
		return _attachment.getSize();
	}

	/**
	* Sets the size of this attachment.
	*
	* @param size the size of this attachment
	*/
	public void setSize(long size) {
		_attachment.setSize(size);
	}

	public boolean isNew() {
		return _attachment.isNew();
	}

	public void setNew(boolean n) {
		_attachment.setNew(n);
	}

	public boolean isCachedModel() {
		return _attachment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_attachment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _attachment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _attachment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_attachment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _attachment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_attachment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AttachmentWrapper((Attachment)_attachment.clone());
	}

	public int compareTo(com.liferay.mail.model.Attachment attachment) {
		return _attachment.compareTo(attachment);
	}

	@Override
	public int hashCode() {
		return _attachment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.mail.model.Attachment> toCacheModel() {
		return _attachment.toCacheModel();
	}

	public com.liferay.mail.model.Attachment toEscapedModel() {
		return new AttachmentWrapper(_attachment.toEscapedModel());
	}

	public com.liferay.mail.model.Attachment toUnescapedModel() {
		return new AttachmentWrapper(_attachment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _attachment.toString();
	}

	public java.lang.String toXmlString() {
		return _attachment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_attachment.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Attachment getWrappedAttachment() {
		return _attachment;
	}

	public Attachment getWrappedModel() {
		return _attachment;
	}

	public void resetOriginalValues() {
		_attachment.resetOriginalValues();
	}

	private Attachment _attachment;
}