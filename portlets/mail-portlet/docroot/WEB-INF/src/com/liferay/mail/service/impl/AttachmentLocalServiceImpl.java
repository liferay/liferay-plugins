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

import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.base.AttachmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * <a href="AttachmentLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class AttachmentLocalServiceImpl extends AttachmentLocalServiceBaseImpl {

	public Attachment addAttachment(
			long userId, long messageId, String contentPath, String fileName,
			long size)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Message message = messagePersistence.findByPrimaryKey(messageId);

		Folder folder = folderPersistence.findByPrimaryKey(
			message.getFolderId());

		long attachmentId = counterLocalService.increment();

		Attachment attachment = attachmentPersistence.create(attachmentId);

		attachment.setCompanyId(user.getCompanyId());
		attachment.setUserId(user.getUserId());
		attachment.setAccountId(folder.getAccountId());
		attachment.setFolderId(folder.getFolderId());
		attachment.setMessageId(messageId);
		attachment.setContentPath(contentPath);
		attachment.setFileName(fileName);
		attachment.setSize(size);

		attachmentPersistence.update(attachment, false);

		return attachment;
	}

	public List<Attachment> getAttachments(long messageId)
		throws SystemException {

		return attachmentPersistence.findByMessageId(messageId);
	}

}