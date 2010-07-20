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
 * This class is a wrapper for {@link Attachment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Attachment
 * @generated
 */
public class AttachmentWrapper implements Attachment {
	public AttachmentWrapper(Attachment attachment) {
		_attachment = attachment;
	}

	public long getPrimaryKey() {
		return _attachment.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_attachment.setPrimaryKey(pk);
	}

	public long getAttachmentId() {
		return _attachment.getAttachmentId();
	}

	public void setAttachmentId(long attachmentId) {
		_attachment.setAttachmentId(attachmentId);
	}

	public long getCompanyId() {
		return _attachment.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_attachment.setCompanyId(companyId);
	}

	public long getUserId() {
		return _attachment.getUserId();
	}

	public void setUserId(long userId) {
		_attachment.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _attachment.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_attachment.setUserUuid(userUuid);
	}

	public long getAccountId() {
		return _attachment.getAccountId();
	}

	public void setAccountId(long accountId) {
		_attachment.setAccountId(accountId);
	}

	public long getFolderId() {
		return _attachment.getFolderId();
	}

	public void setFolderId(long folderId) {
		_attachment.setFolderId(folderId);
	}

	public long getMessageId() {
		return _attachment.getMessageId();
	}

	public void setMessageId(long messageId) {
		_attachment.setMessageId(messageId);
	}

	public java.lang.String getContentPath() {
		return _attachment.getContentPath();
	}

	public void setContentPath(java.lang.String contentPath) {
		_attachment.setContentPath(contentPath);
	}

	public java.lang.String getFileName() {
		return _attachment.getFileName();
	}

	public void setFileName(java.lang.String fileName) {
		_attachment.setFileName(fileName);
	}

	public long getSize() {
		return _attachment.getSize();
	}

	public void setSize(long size) {
		_attachment.setSize(size);
	}

	public com.liferay.mail.model.Attachment toEscapedModel() {
		return _attachment.toEscapedModel();
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

	public void setEscapedModel(boolean escapedModel) {
		_attachment.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _attachment.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _attachment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_attachment.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _attachment.clone();
	}

	public int compareTo(com.liferay.mail.model.Attachment attachment) {
		return _attachment.compareTo(attachment);
	}

	public int hashCode() {
		return _attachment.hashCode();
	}

	public java.lang.String toString() {
		return _attachment.toString();
	}

	public java.lang.String toXmlString() {
		return _attachment.toXmlString();
	}

	public Attachment getWrappedAttachment() {
		return _attachment;
	}

	private Attachment _attachment;
}