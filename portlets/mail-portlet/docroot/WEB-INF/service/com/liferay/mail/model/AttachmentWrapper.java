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

package com.liferay.mail.model;

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
 * This class is a wrapper for {@link Attachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Attachment
 * @generated
 */
@ProviderType
public class AttachmentWrapper implements Attachment, ModelWrapper<Attachment> {
	public AttachmentWrapper(Attachment attachment) {
		_attachment = attachment;
	}

	@Override
	public Class<?> getModelClass() {
		return Attachment.class;
	}

	@Override
	public String getModelClassName() {
		return Attachment.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public boolean isCachedModel() {
		return _attachment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _attachment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _attachment.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _attachment.getExpandoBridge();
	}

	@Override
	public com.liferay.mail.model.Attachment toEscapedModel() {
		return new AttachmentWrapper(_attachment.toEscapedModel());
	}

	@Override
	public com.liferay.mail.model.Attachment toUnescapedModel() {
		return new AttachmentWrapper(_attachment.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.mail.model.Attachment> toCacheModel() {
		return _attachment.toCacheModel();
	}

	@Override
	public int compareTo(com.liferay.mail.model.Attachment attachment) {
		return _attachment.compareTo(attachment);
	}

	@Override
	public int hashCode() {
		return _attachment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _attachment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AttachmentWrapper((Attachment)_attachment.clone());
	}

	/**
	* Returns the content path of this attachment.
	*
	* @return the content path of this attachment
	*/
	@Override
	public java.lang.String getContentPath() {
		return _attachment.getContentPath();
	}

	/**
	* Returns the file name of this attachment.
	*
	* @return the file name of this attachment
	*/
	@Override
	public java.lang.String getFileName() {
		return _attachment.getFileName();
	}

	/**
	* Returns the user uuid of this attachment.
	*
	* @return the user uuid of this attachment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _attachment.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _attachment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _attachment.toXmlString();
	}

	/**
	* Returns the account ID of this attachment.
	*
	* @return the account ID of this attachment
	*/
	@Override
	public long getAccountId() {
		return _attachment.getAccountId();
	}

	/**
	* Returns the attachment ID of this attachment.
	*
	* @return the attachment ID of this attachment
	*/
	@Override
	public long getAttachmentId() {
		return _attachment.getAttachmentId();
	}

	/**
	* Returns the company ID of this attachment.
	*
	* @return the company ID of this attachment
	*/
	@Override
	public long getCompanyId() {
		return _attachment.getCompanyId();
	}

	/**
	* Returns the folder ID of this attachment.
	*
	* @return the folder ID of this attachment
	*/
	@Override
	public long getFolderId() {
		return _attachment.getFolderId();
	}

	/**
	* Returns the message ID of this attachment.
	*
	* @return the message ID of this attachment
	*/
	@Override
	public long getMessageId() {
		return _attachment.getMessageId();
	}

	/**
	* Returns the primary key of this attachment.
	*
	* @return the primary key of this attachment
	*/
	@Override
	public long getPrimaryKey() {
		return _attachment.getPrimaryKey();
	}

	/**
	* Returns the size of this attachment.
	*
	* @return the size of this attachment
	*/
	@Override
	public long getSize() {
		return _attachment.getSize();
	}

	/**
	* Returns the user ID of this attachment.
	*
	* @return the user ID of this attachment
	*/
	@Override
	public long getUserId() {
		return _attachment.getUserId();
	}

	@Override
	public void persist() {
		_attachment.persist();
	}

	/**
	* Sets the account ID of this attachment.
	*
	* @param accountId the account ID of this attachment
	*/
	@Override
	public void setAccountId(long accountId) {
		_attachment.setAccountId(accountId);
	}

	/**
	* Sets the attachment ID of this attachment.
	*
	* @param attachmentId the attachment ID of this attachment
	*/
	@Override
	public void setAttachmentId(long attachmentId) {
		_attachment.setAttachmentId(attachmentId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_attachment.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this attachment.
	*
	* @param companyId the company ID of this attachment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_attachment.setCompanyId(companyId);
	}

	/**
	* Sets the content path of this attachment.
	*
	* @param contentPath the content path of this attachment
	*/
	@Override
	public void setContentPath(java.lang.String contentPath) {
		_attachment.setContentPath(contentPath);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_attachment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_attachment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_attachment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this attachment.
	*
	* @param fileName the file name of this attachment
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_attachment.setFileName(fileName);
	}

	/**
	* Sets the folder ID of this attachment.
	*
	* @param folderId the folder ID of this attachment
	*/
	@Override
	public void setFolderId(long folderId) {
		_attachment.setFolderId(folderId);
	}

	/**
	* Sets the message ID of this attachment.
	*
	* @param messageId the message ID of this attachment
	*/
	@Override
	public void setMessageId(long messageId) {
		_attachment.setMessageId(messageId);
	}

	@Override
	public void setNew(boolean n) {
		_attachment.setNew(n);
	}

	/**
	* Sets the primary key of this attachment.
	*
	* @param primaryKey the primary key of this attachment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_attachment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_attachment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the size of this attachment.
	*
	* @param size the size of this attachment
	*/
	@Override
	public void setSize(long size) {
		_attachment.setSize(size);
	}

	/**
	* Sets the user ID of this attachment.
	*
	* @param userId the user ID of this attachment
	*/
	@Override
	public void setUserId(long userId) {
		_attachment.setUserId(userId);
	}

	/**
	* Sets the user uuid of this attachment.
	*
	* @param userUuid the user uuid of this attachment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_attachment.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AttachmentWrapper)) {
			return false;
		}

		AttachmentWrapper attachmentWrapper = (AttachmentWrapper)obj;

		if (Validator.equals(_attachment, attachmentWrapper._attachment)) {
			return true;
		}

		return false;
	}

	@Override
	public Attachment getWrappedModel() {
		return _attachment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _attachment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _attachment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_attachment.resetOriginalValues();
	}

	private final Attachment _attachment;
}