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

package com.liferay.mail.mailbox;

import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.model.Message;
import com.liferay.mail.model.MessagesDisplay;
import com.liferay.mail.util.AttachmentHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.io.IOException;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * @author Scott Lee
 */
public interface Mailbox {

	public Account addAccount(
			String address, String personalName, String protocol,
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password, boolean savePassword,
			String signature, boolean useSignature, String folderPrefix,
			boolean defaultSender)
		throws PortalException;

	public Folder addFolder(String displayName) throws PortalException;

	public void deleteAccount() throws PortalException;

	public void deleteAttachment(long attachmentId) throws PortalException;

	public void deleteFolder(long folderId) throws PortalException;

	public void deleteMessages(long folderId, long[] messageIds)
		throws PortalException;

	public Account getAccount();

	public AttachmentHandler getAttachment(long attachmentId)
		throws IOException, PortalException;

	public Message getMessage(
			long folderId, String keywords, int messageNumber,
			String orderByField, String orderByType)
		throws PortalException;

	public MessagesDisplay getMessagesDisplay(
			long folderId, String keywords, int pageNumber, int messagesPerPage,
			String orderByField, String orderByType)
		throws PortalException;

	public User getUser();

	public boolean hasNewMessages(long folderId) throws PortalException;

	public void moveMessages(long folderId, long[] messageIds)
		throws PortalException;

	public InternetAddress[] parseAddresses(String addresses)
		throws PortalException;

	public void renameFolder(long folderId, String displayName)
		throws PortalException;

	public Message saveDraft(
			long accountId, long messageId, String to, String cc, String bcc,
			String subject, String body, List<MailFile> mailFiles)
		throws PortalException;

	public void sendMessage(long accountId, long messageId)
		throws PortalException;

	public void setAccount(Account account);

	public void setUser(User user);

	public void synchronize() throws PortalException;

	public void synchronizeFolder(long folderId) throws PortalException;

	public void synchronizeMessage(long messageId) throws PortalException;

	public void synchronizePage(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException;

	public Account updateAccount(
			long accountId, String personalName, String password,
			boolean savePassword, String signature, boolean useSignature,
			String folderPrefix, boolean defaultSender)
		throws PortalException;

	public void updateFlags(
			long folderId, long[] messageIds, int flag, boolean value)
		throws PortalException;

	public void updateFolders() throws PortalException;

	public void updateFolders(
			long inboxFolderId, long draftFolderId, long sentFolderId,
			long trashFolderId)
		throws PortalException;

	public void validateAccount(
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password)
		throws PortalException;

}