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

import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.base.MessageLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * <a href="MessageLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {

	public Message addMessage(
			long userId, long folderId, String sender, String recipientsTo,
			String recipientsCc, String recipientsBcc, Date sentDate,
			String subject, String body, String flags, long size,
			long remoteMessageId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Folder folder = folderPersistence.findByPrimaryKey(folderId);
		Date now = new Date();

		long messageId = counterLocalService.increment();

		Message message = messagePersistence.create(messageId);

		message.setCompanyId(user.getCompanyId());
		message.setUserId(user.getUserId());
		message.setUserName(user.getFullName());
		message.setCreateDate(now);
		message.setModifiedDate(now);
		message.setAccountId(folder.getAccountId());
		message.setFolderId(folderId);
		message.setSender(sender);
		message.setRecipientsTo(recipientsTo);
		message.setRecipientsCc(recipientsCc);
		message.setRecipientsBcc(recipientsBcc);
		message.setSentDate(sentDate);
		message.setSubject(subject);
		message.setPreview(body);
		message.setBody(body);
		message.setFlags(flags);
		message.setSize(size);
		message.setRemoteMessageId(remoteMessageId);

		messagePersistence.update(message, false);

		return message;
	}

	public Message getMessage(long folderId, long remoteMessageId)
		throws PortalException, SystemException {

		return messagePersistence.findByF_R(folderId, remoteMessageId);
	}

	public int getMessageCount(long folderId) throws SystemException {
		return messagePersistence.countByFolderId(folderId);
	}

	public List<Message> getMessages(long folderId) throws SystemException {
		return messagePersistence.findByFolderId(folderId);
	}

	public Message updateMessage(
			long messageId, long folderId, String sender, String recipientsTo,
			String recipientsCc, String recipientsBcc, Date sentDate,
			String subject, String body, String flags, long size,
			long remoteMessageId)
		throws PortalException, SystemException {

		Message message = messagePersistence.findByPrimaryKey(messageId);

		message.setModifiedDate(new Date());
		message.setFolderId(folderId);
		message.setSender(sender);
		message.setRecipientsTo(recipientsTo);
		message.setRecipientsCc(recipientsCc);
		message.setRecipientsBcc(recipientsBcc);
		message.setSentDate(sentDate);
		message.setSubject(subject);
		message.setPreview(body);
		message.setBody(body);
		message.setFlags(flags);
		message.setSize(size);
		message.setRemoteMessageId(remoteMessageId);

		messagePersistence.update(message, false);

		return message;
	}

}