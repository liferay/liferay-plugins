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

package com.liferay.mail.service.impl;

import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.NoSuchFileException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.base.AttachmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.List;

/**
 * <a href="AttachmentLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class AttachmentLocalServiceImpl extends AttachmentLocalServiceBaseImpl {

	public Attachment addAttachment(
			long userId, long messageId, String contentPath, String fileName,
			long size, File file)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Message message = messagePersistence.findByPrimaryKey(messageId);

		long attachmentId = counterLocalService.increment();

		Attachment attachment = attachmentPersistence.create(attachmentId);

		attachment.setCompanyId(user.getCompanyId());
		attachment.setUserId(user.getUserId());
		attachment.setAccountId(message.getAccountId());
		attachment.setFolderId(message.getFolderId());
		attachment.setMessageId(messageId);
		attachment.setContentPath(contentPath);
		attachment.setFileName(fileName);
		attachment.setSize(size);

		if (Validator.isNotNull(file)) {
			try {
				DLServiceUtil.addDirectory(
					attachment.getCompanyId(), _repositoryId,
					getDirectoryPath(attachment.getMessageId()));
			}
			catch (DuplicateDirectoryException dde) {
			}

			try {
				String filePath = getFilePath(
					attachment.getMessageId(), fileName);

				DLServiceUtil.addFile(
					attachment.getCompanyId(), _portletId, _groupId,
					_repositoryId, filePath, 0, StringPool.BLANK, new Date(),
					new ServiceContext(), file);
			}
			catch (DuplicateFileException dfe) {
				if (_log.isDebugEnabled()) {
					_log.debug(dfe.getMessage());
				}
			}
		}

		attachmentPersistence.update(attachment, false);

		return attachment;
	}

	public void deleteAttachment(long attachmentId)
		throws PortalException, SystemException {

		Attachment attachment = attachmentPersistence.findByPrimaryKey(
			attachmentId);

		try {
			DLServiceUtil.deleteFile(
				attachment.getCompanyId(), _portletId, _repositoryId,
				getFilePath(
					attachment.getMessageId(), attachment.getFileName()));
		}
		catch (NoSuchDirectoryException nsde) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsde.getMessage());
			}
		}
		catch (NoSuchFileException nsfe) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsfe.getMessage());
			}
		}

		attachmentPersistence.remove(attachmentId);
	}

	public void deleteAttachments(long messageId)
		throws PortalException, SystemException {

		List<Attachment> attachments = attachmentPersistence.findByMessageId(
			messageId);

		for (Attachment attachment : attachments) {
			deleteAttachment(attachment);
		}

		Message message = messagePersistence.findByPrimaryKey(messageId);

		try {
			DLServiceUtil.deleteDirectory(
				message.getCompanyId(), _portletId, _repositoryId,
				getDirectoryPath(messageId));
		}
		catch (NoSuchDirectoryException nsde) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsde.getMessage());
			}
		}
	}

	public File getAttachmentAsFile(long attachmentId)
		throws IOException, PortalException, SystemException {

		byte[] bytes = getAttachmentAsByteArray(attachmentId);

		File file = FileUtil.createTempFile();

		FileUtil.write(file, bytes);

		return file;
	}

	public InputStream getAttachmentAsStream(long attachmentId)
		throws PortalException, SystemException {

		return new ByteArrayInputStream(getAttachmentAsByteArray(attachmentId));
	}

	public List<Attachment> getAttachments(long messageId)
		throws SystemException {

		return attachmentPersistence.findByMessageId(messageId);
	}

	protected byte[] getAttachmentAsByteArray(long attachmentId)
		throws PortalException, SystemException {

		Attachment attachment = attachmentPersistence.findByPrimaryKey(
			attachmentId);

		return DLServiceUtil.getFile(
			attachment.getCompanyId(), _repositoryId,
			getFilePath(
				attachment.getMessageId(), attachment.getFileName()));
	}

	protected String getDirectoryPath(long messageId) {
		return "mail/" + messageId;
	}

	protected String getFilePath(long messageId, String filename) {
		return getDirectoryPath(messageId) + "/" + filename;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AttachmentLocalServiceImpl.class);

	private long _groupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;
	private String _portletId = CompanyConstants.SYSTEM_STRING;
	private long _repositoryId = CompanyConstants.SYSTEM;

}