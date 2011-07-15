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

package com.liferay.mail.service.impl;

import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.base.AttachmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.DuplicateDirectoryException;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.List;

/**
 * @author Scott Lee
 */
public class AttachmentLocalServiceImpl extends AttachmentLocalServiceBaseImpl {

	public Attachment addAttachment(
			long userId, long messageId, String contentPath, String fileName,
			long size, File file)
		throws PortalException, SystemException {

		// Attachment

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

		attachmentPersistence.update(attachment, false);

		// File

		if (file != null) {
			String directoryPath = getDirectoryPath(attachment.getMessageId());

			try {
				DLStoreUtil.addDirectory(
					attachment.getCompanyId(), _REPOSITORY_ID, directoryPath);
			}
			catch (DuplicateDirectoryException dde) {
			}

			String filePath = getFilePath(
				attachment.getMessageId(), fileName);

			try {
				DLStoreUtil.addFile(
					attachment.getCompanyId(), _PORTLET_ID, _GROUP_ID,
					_REPOSITORY_ID, filePath, new ServiceContext(), file);
			}
			catch (DuplicateFileException dfe) {
				if (_log.isDebugEnabled()) {
					_log.debug(dfe, dfe);
				}
			}
		}

		return attachment;
	}

	@Override
	public void deleteAttachment(long attachmentId)
		throws PortalException, SystemException {

		// Attachment

		Attachment attachment = attachmentPersistence.findByPrimaryKey(
			attachmentId);

		attachmentPersistence.remove(attachmentId);

		// File

		String filePath = getFilePath(
			attachment.getMessageId(), attachment.getFileName());

		try {
			DLStoreUtil.deleteFile(
				attachment.getCompanyId(), _PORTLET_ID, _REPOSITORY_ID,
				filePath);
		}
		catch (NoSuchDirectoryException nsde) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsde, nsde);
			}
		}
		catch (NoSuchFileException nsfe) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsfe, nsfe);
			}
		}
	}

	public void deleteAttachments(long companyId, long messageId)
		throws PortalException, SystemException {

		// Attachments

		List<Attachment> attachments = attachmentPersistence.findByMessageId(
			messageId);

		for (Attachment attachment : attachments) {
			deleteAttachment(attachment);
		}

		// File

		String directoryPath = getDirectoryPath(messageId);

		try {
			DLStoreUtil.deleteDirectory(
				companyId, _PORTLET_ID, _REPOSITORY_ID, directoryPath);
		}
		catch (NoSuchDirectoryException nsde) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsde.getMessage());
			}
		}
	}

	public List<Attachment> getAttachments(long messageId)
		throws SystemException {

		return attachmentPersistence.findByMessageId(messageId);
	}

	public File getFile(long attachmentId)
		throws PortalException, SystemException {

		try {
			File file = FileUtil.createTempFile();

			FileUtil.write(file, getInputStream(attachmentId));

			return file;
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	public InputStream getInputStream(long attachmentId)
		throws PortalException, SystemException {

		Attachment attachment = attachmentPersistence.findByPrimaryKey(
			attachmentId);

		String filePath = getFilePath(
			attachment.getMessageId(), attachment.getFileName());

		return DLStoreUtil.getFileAsStream(
			attachment.getCompanyId(), _REPOSITORY_ID, filePath);
	}

	protected String getDirectoryPath(long messageId) {
		return _DIRECTORY_PATH_PREFIX.concat(String.valueOf(messageId));
	}

	protected String getFilePath(long messageId, String filename) {
		return getDirectoryPath(messageId).concat(StringPool.SLASH).concat(
			filename);
	}

	private static final String _DIRECTORY_PATH_PREFIX = "mail/";

	private static final long _GROUP_ID =
		GroupConstants.DEFAULT_PARENT_GROUP_ID;

	private static final String _PORTLET_ID = CompanyConstants.SYSTEM_STRING;

	private static final long _REPOSITORY_ID = CompanyConstants.SYSTEM;

	private static Log _log = LogFactoryUtil.getLog(
		AttachmentLocalServiceImpl.class);

}