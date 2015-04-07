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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AttachmentSoap implements Serializable {
	public static AttachmentSoap toSoapModel(Attachment model) {
		AttachmentSoap soapModel = new AttachmentSoap();

		soapModel.setAttachmentId(model.getAttachmentId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setFolderId(model.getFolderId());
		soapModel.setMessageId(model.getMessageId());
		soapModel.setContentPath(model.getContentPath());
		soapModel.setFileName(model.getFileName());
		soapModel.setSize(model.getSize());

		return soapModel;
	}

	public static AttachmentSoap[] toSoapModels(Attachment[] models) {
		AttachmentSoap[] soapModels = new AttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AttachmentSoap[][] toSoapModels(Attachment[][] models) {
		AttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AttachmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AttachmentSoap[] toSoapModels(List<Attachment> models) {
		List<AttachmentSoap> soapModels = new ArrayList<AttachmentSoap>(models.size());

		for (Attachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AttachmentSoap[soapModels.size()]);
	}

	public AttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _attachmentId;
	}

	public void setPrimaryKey(long pk) {
		setAttachmentId(pk);
	}

	public long getAttachmentId() {
		return _attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		_attachmentId = attachmentId;
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

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public String getContentPath() {
		return _contentPath;
	}

	public void setContentPath(String contentPath) {
		_contentPath = contentPath;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	private long _attachmentId;
	private long _companyId;
	private long _userId;
	private long _accountId;
	private long _folderId;
	private long _messageId;
	private String _contentPath;
	private String _fileName;
	private long _size;
}