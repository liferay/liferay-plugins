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

package com.liferay.mail.mailbox;

import com.liferay.mail.MailFile;
import com.liferay.mail.MessagesDisplay;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.InputStream;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <a href="IMAPMailbox.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class IMAPMailbox extends BaseMailbox {

	public IMAPMailbox() {
	}

	public Folder addFolder(String displayName) {
		return null;
	}

	public void deleteAttachment(long attachmentId) {
	}

	public void deleteFolder(long folderId) {
	}

	public void deleteMessages(long folderId, long[] messageIds) {
	}

	public InputStream getAttachment(long attachmentId) {
		return null;
	}

	public List<Folder> getFolders() {
		return null;
	}

	public Message getMessage(
		long folderId, String keywords, int messageNumber,
		String orderByField, String orderByType) {

		return null;
	}

	public List<Message> getMessages(
		long folderId, String keywords, int pageNumber, int messagesPerPage,
		String orderByField, String orderByType) {

		return null;
	}

	public void moveMessages(long folderId, long[] messageIds) {
	}

	public InternetAddress[] parseAddresses(String addresses) {
		return null;
	}

	public MessagesDisplay getMessagesDisplay(
			long folderId, String keywords, int pageNumber, int messagesPerPage,
			String orderByField, String orderByType)
		throws PortalException, SystemException {

		return null;
	}

	public Message saveDraft(
		long accountId, long messageId, String to, String cc, String bcc,
		String subject, String body, List<MailFile> mailFiles) {

		return null;
	}

	public void sendMessage(long accountId, long messageId) {
	}

	public void synchronize() {
	}

	public void synchronize(long folderId) {
	}

	public void synchronizeMessage(long messageId)
		throws PortalException, SystemException {
	}
	
	public void synchronizePage(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException, SystemException {
	}

	public void updateFolder(long folderId, String displayName) {
	}

	public void updateMessagesFlag(
		long folderId, long[] messageIds, int flag, boolean value) {
	}

	public void validateAccount(
		String incomingHostName, int incomingPort, boolean incomingSecure,
		String outgoingHostName, int outgoingPort, boolean outgoingSecure,
		String login, String password) {
	}

}