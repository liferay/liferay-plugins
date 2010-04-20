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
import com.liferay.mail.util.MailConstants;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="MessageLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {

	public Message addMessage(
			long userId, long folderId, String sender, String to, String cc,
			String bcc, Date sentDate, String subject, String body,
			String flags, long size, long remoteMessageId)
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
		message.setTo(to);
		message.setCc(cc);
		message.setBcc(bcc);
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

	public void deleteMessage(long messageId)
		throws PortalException, SystemException {

		Message message = messagePersistence.findByPrimaryKey(messageId);

		deleteMessage(message);
	}

	public void deleteMessage(Message message)
		throws PortalException, SystemException {

		// Message

		messagePersistence.remove(message);

		// Attachments

		attachmentLocalService.deleteAttachments(message.getMessageId());
	}

	public void deleteMessages(long folderId)
		throws PortalException, SystemException {

		List<Message> messages = messagePersistence.findByFolderId(folderId);

		for (Message message : messages) {
			deleteMessage(message);
		}
	}

	public int getAccountUnreadMessagesCount(long accountId)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Message.class, PortletClassLoaderUtil.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accountId", accountId));
		dynamicQuery.add(
			RestrictionsFactoryUtil.not(
				RestrictionsFactoryUtil.like(
					"flags", "%" + MailConstants.FLAG_SEEN + ",%")));

		return dynamicQueryCount(dynamicQuery);
	}

	public List<Message> getCompanyMessages(long companyId, int start, int end)
		throws SystemException {

		return messagePersistence.findByCompanyId(companyId, start, end);
	}

	public int getCompanyMessagesCount(long companyId) throws SystemException {
		return messagePersistence.countByCompanyId(companyId);
	}

	public List<Message> getFolderMessages(long folderId)
		throws SystemException {

		return messagePersistence.findByFolderId(folderId);
	}

	public int getFolderMessagesCount(long folderId) throws SystemException {
		return messagePersistence.countByFolderId(folderId);
	}

	public int getFolderUnreadMessagesCount(long folderId)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Message.class, PortletClassLoaderUtil.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("folderId", folderId));
		dynamicQuery.add(
			RestrictionsFactoryUtil.not(
				RestrictionsFactoryUtil.like(
					"flags", "%" + MailConstants.FLAG_SEEN + ",%")));

		return dynamicQueryCount(dynamicQuery);
	}

	public Message getMessage(long folderId, long remoteMessageId)
		throws PortalException, SystemException {

		return messagePersistence.findByF_R(folderId, remoteMessageId);
	}

	public int populateMessages(
			List<Message> messages, long folderId, String keywords,
			int pageNumber, int messagesPerPage, String orderByField,
			String orderByType)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Message.class, PortletClassLoaderUtil.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("folderId", folderId));

		if (orderByType.equals("desc")) {
			dynamicQuery.addOrder(OrderFactoryUtil.desc(orderByField));
		}
		else {
			dynamicQuery.addOrder(OrderFactoryUtil.asc(orderByField));
		}

		if (Validator.isNotNull(keywords)) {
			String value = "%" + keywords + "%";

			Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

			conjunction.add(RestrictionsFactoryUtil.ilike("subject", value));
			conjunction.add(RestrictionsFactoryUtil.ilike("body", value));

			dynamicQuery.add(conjunction);
		}

		int start = messagesPerPage * (pageNumber - 1);
		int end = messagesPerPage * pageNumber;

		List<Object> results = messagePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);

		messages = toMessages(results);

		return dynamicQueryCount(dynamicQuery);
	}

	public Message updateFlag(long messageId, int flag, boolean value)
		throws PortalException, SystemException {

		String flagString = String.valueOf(flag);

		Message message = messagePersistence.findByPrimaryKey(messageId);

		String flags = message.getFlags();

		if (value && !StringUtil.contains(flags, flagString)) {
			message.setFlags(StringUtil.add(flags, flagString));
		}
		else if (!value && StringUtil.contains(flags, flagString)) {
			message.setFlags(StringUtil.remove(flags, flagString));
		}

		return messagePersistence.update(message, false);
	}

	public Message updateMessage(
			long messageId, long folderId, String sender, String to, String cc,
			String bcc, Date sentDate, String subject, String body,
			String flags, long size, long remoteMessageId)
		throws PortalException, SystemException {

		Message message = messagePersistence.findByPrimaryKey(messageId);

		message.setModifiedDate(new Date());
		message.setFolderId(folderId);
		message.setSender(sender);
		message.setTo(to);
		message.setCc(cc);
		message.setBcc(bcc);
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

	public Message updateMessageSize(long messageId, long size)
		throws PortalException, SystemException {

		Message message = messagePersistence.findByPrimaryKey(messageId);

		message.setSize(size);

		messagePersistence.update(message, false);

		return message;
	}

	protected List<Message> toMessages(List<Object> results) {
		List<Message> messages = new ArrayList<Message>(results.size());

		for (Object result : results) {
			messages.add((Message)result);
		}

		return messages;
	}

}