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

import com.liferay.mail.MailException;
import com.liferay.mail.MailFile;
import com.liferay.mail.MessagesDisplay;
import com.liferay.mail.NoSuchFolderException;
import com.liferay.mail.NoSuchMessageException;
import com.liferay.mail.imap.IMAPConnection;
import com.liferay.mail.imap.IMAPUtil;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.mail.util.MailConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.mail.InternetAddressUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * <a href="IMAPMailbox.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class IMAPMailbox extends BaseMailbox {

	public IMAPMailbox() {
	}

	public Folder addFolder(String displayName)
		throws PortalException, SystemException {

		IMAPUtil imapUtil = getIMAPUtil();

		String[] folderData = imapUtil.addFolder(displayName);

		return FolderLocalServiceUtil.addFolder(
			user.getUserId(), account.getAccountId(), folderData[0],
			folderData[1], 0);
	}

	public void deleteAttachment(long attachmentId)
		throws PortalException, SystemException {

		AttachmentLocalServiceUtil.deleteAttachment(attachmentId);
	}

	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		if ((account.getDraftFolderId() == folderId)
				|| (account.getInboxFolderId() == folderId)
				|| (account.getSentFolderId() == folderId)
				|| (account.getTrashFolderId() == folderId)) {

			throw new MailException(MailException.FOLDER_REQUIRED);
		}

		IMAPUtil imapUtil = getIMAPUtil();

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		imapUtil.deleteFolder(folder.getFolderId());

		FolderLocalServiceUtil.deleteFolder(folderId);
	}

	public void deleteMessages(long folderId, long[] messageIds)
		throws PortalException, SystemException {

		IMAPUtil imapUtil = getIMAPUtil();

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		if ((account.getDraftFolderId() != folderId) &&
				(account.getTrashFolderId() != folderId)) {

			Folder trashFolder = FolderLocalServiceUtil.getFolder(
				account.getTrashFolderId());

			imapUtil.moveMessages(
				folder.getFolderId(), trashFolder.getFolderId(),
				messageIds, true);
		}
		else {
			imapUtil.deleteMessages(folderId, messageIds);
		}
	}

	public InputStream getAttachment(long attachmentId)
		throws IOException, PortalException, SystemException {

		Attachment attachment = AttachmentLocalServiceUtil.getAttachment(
			attachmentId);

		Message message = MessageLocalServiceUtil.getMessage(
			attachment.getMessageId());

		if (account.getDraftFolderId() == attachment.getFolderId()) {
			return AttachmentLocalServiceUtil.getInputStream(
				attachmentId);
		}
		else {
			IMAPUtil imapUtil = getIMAPUtil();

			Folder folder = FolderLocalServiceUtil.getFolder(
				attachment.getFolderId());

			return imapUtil.getAttachment(
				folder.getFolderId(), message.getRemoteMessageId(),
				attachment.getContentPath());
		}
	}

	public Message getMessage(
			long folderId, String keywords, int messageNumber,
			String orderByField, String orderByType)
		throws PortalException, SystemException {

		MessagesDisplay messagesDisplay = getMessagesDisplay(
			folderId, keywords, messageNumber, 1, orderByField, orderByType);

		return messagesDisplay.getMessages().get(0);
	}

	public MessagesDisplay getMessagesDisplay(
			long folderId, String keywords, int pageNumber, int messagesPerPage,
			String orderByField, String orderByType)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		if (orderByField.equals(MailConstants.ORDER_BY_ADDRESS)) {
			if (account.getSentFolderId() == folderId) {
				orderByField = "to";
			}
			else {
				orderByField = "sender";
			}
		}
		else if (!orderByField.equals(MailConstants.ORDER_BY_SENT_DATE) &&
				!orderByField.equals(MailConstants.ORDER_BY_SIZE) &&
				!orderByField.equals(MailConstants.ORDER_BY_SUBJECT)) {

			_log.error("unknown orderbyfield " + orderByField);

			orderByField = MailConstants.ORDER_BY_SENT_DATE;
		}

		keywords = keywords.trim();

		List<Message> messages = new ArrayList<Message>();

		int messageCount = MessageLocalServiceUtil.populateMessages(
			messages, folderId, keywords, pageNumber, messagesPerPage,
			orderByField, orderByType);

		if (Validator.isNotNull(keywords)) {
			return new MessagesDisplay(
				messages, pageNumber, messagesPerPage, messageCount);
		}
		else {
			return new MessagesDisplay(
				messages, pageNumber, messagesPerPage,
				folder.getRemoteMessageCount());
		}
	}

	public void moveMessages(long folderId, long[] messageIds)
		throws PortalException, SystemException {

		IMAPUtil imapUtil = getIMAPUtil();

		for (long messageId : messageIds) {
			Message message = MessageLocalServiceUtil.getMessage(messageId);

			Account account = AccountLocalServiceUtil.getAccount(
				message.getAccountId());

			long sourceFolderId = message.getFolderId();

			if ((account.getDraftFolderId() == sourceFolderId)
					|| (account.getSentFolderId() == sourceFolderId)) {

				throw new MailException(
					MailException.FOLDER_INVALID_DESTINATION);
			}

			imapUtil.moveMessages(
				sourceFolderId, folderId, new long[] { messageId }, true);
		}
	}

	public InternetAddress[] parseAddresses(String addresses)
		throws PortalException, SystemException {

		InternetAddress[] internetAddresses = new InternetAddress[0];

		try {
			internetAddresses = InternetAddress.parse(addresses, true);

			for (int i = 0; i < internetAddresses.length; i++) {
				InternetAddress internetAddress = internetAddresses[i];

				if (!Validator.isEmailAddress(internetAddress.getAddress())) {
					throw new MailException(
						MailException.MESSAGE_INVALID_ADDRESS,
						internetAddress.getPersonal().concat("<").concat(
							internetAddress.getAddress()).concat(">"));
				}
			}
		}
		catch (AddressException ae) {
			throw new MailException(
				MailException.MESSAGE_INVALID_ADDRESS, ae, addresses);
		}

		return internetAddresses;
	}

	public void renameFolder(long folderId, String displayName)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		IMAPUtil imapUtil = getIMAPUtil();

		String[] folderData = imapUtil.renameFolder(
			folder.getFolderId(), displayName);

		FolderLocalServiceUtil.updateFolder(
			folderId, folderData[0], folderData[1],
			folder.getRemoteMessageCount());
	}

	public Message saveDraft(
			long accountId, long messageId, String to, String cc, String bcc,
			String subject, String body, List<MailFile> mailFiles)
		throws PortalException, SystemException {

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		String sender = user.getFullName().concat(" <").concat(
			account.getAddress()).concat(">");

		String flags = MailConstants.FLAG_DRAFT + StringPool.COMMA;
		long remoteMessageId = 0;
		Date sentDate = null;

		InternetAddress[] parsedTo = parseAddresses(to);
		InternetAddress[] parsedCc = parseAddresses(cc);
		InternetAddress[] parsedBcc = parseAddresses(bcc);

		if (parsedTo.length + parsedCc.length + parsedBcc.length == 0) {
			throw new MailException(MailException.MESSAGE_HAS_NO_RECIPIENTS);
		}

		if (messageId != 0) {
			return MessageLocalServiceUtil.updateMessage(
				messageId, account.getDraftFolderId(), sender,
				InternetAddressUtil.toString(parsedTo),
				InternetAddressUtil.toString(parsedCc),
				InternetAddressUtil.toString(parsedBcc), sentDate, subject,
				body, flags, remoteMessageId);
		}
		else {
			return MessageLocalServiceUtil.addMessage(
				user.getUserId(), account.getDraftFolderId(), sender, to, cc,
				bcc, sentDate, subject, body, flags, remoteMessageId);
		}
	}

	public void sendMessage(long accountId, long messageId)
		throws PortalException, SystemException {

		List<Attachment> attachments =
			AttachmentLocalServiceUtil.getAttachments(messageId);

		List<MailFile> mailFiles = new ArrayList<MailFile>();

		for (Attachment attachment : attachments) {
			File file = AttachmentLocalServiceUtil.getFile(
				attachment.getAttachmentId());

			MailFile mailFile = new MailFile(
				file, attachment.getFileName(), attachment.getSize());

			mailFiles.add(mailFile);
		}

		IMAPUtil imapUtil = getIMAPUtil();

		Message message = MessageLocalServiceUtil.getMessage(messageId);

		Address[] to = parseAddresses(message.getTo());
		Address[] cc = parseAddresses(message.getCc());
		Address[] bcc = parseAddresses(message.getBcc());

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		imapUtil.sendMessage(
			account.getPersonalName(), account.getAddress(), to, cc, bcc,
			message.getSubject(), message.getBody(), mailFiles);
	}

	public void synchronize() throws PortalException, SystemException {
		IMAPUtil imapUtil = getIMAPUtil();

		_log.debug("Synchronizing account, updating folders");

		List<javax.mail.Folder> imapFolders = imapUtil.getFolders();

		long draftFolderId = account.getDraftFolderId();
		long inboxFolderId = account.getInboxFolderId();
		long sentFolderId = account.getSentFolderId();
		long trashFolderId = account.getTrashFolderId();
		
		for (javax.mail.Folder imapFolder : imapFolders) {
			Folder folder;

			try {
				folder = FolderLocalServiceUtil.getFolder(
					account.getAccountId(), imapFolder.getFullName());
			}
			catch (NoSuchFolderException fe) {
				folder = FolderLocalServiceUtil.addFolder(
					user.getUserId(), account.getAccountId(),
					imapFolder.getFullName(), imapFolder.getName(), 0);
			}

			// Check for special account folders

			String folderName = imapFolder.getName().toLowerCase();

			if ((inboxFolderId == 0) && (folderName.indexOf("inbox") != -1)) {
				inboxFolderId = folder.getFolderId();
			}
			else if ((draftFolderId == 0) &&
					(folderName.indexOf("draft") != -1)) {

				draftFolderId = folder.getFolderId();
			}
			else if ((sentFolderId == 0) &&
					(folderName.indexOf("sent") != -1)) {

				sentFolderId = folder.getFolderId();
			}
			else if ((trashFolderId == 0) &&
					(folderName.indexOf("trash") != -1)) {

				trashFolderId = folder.getFolderId();
			}
		}

		AccountLocalServiceUtil.updateAccountFolders(
			account.getAccountId(), inboxFolderId, draftFolderId, sentFolderId,
			trashFolderId);

		_log.debug("Synchronizing account, downloading new messages");

		List<Folder> folders = FolderLocalServiceUtil.getFolders(
			account.getAccountId());

		for (Folder folder : folders) {
			synchronizeFolder(folder.getFolderId());
		}
	}

	public void synchronizeFolder(long folderId)
		throws PortalException, SystemException {

		_log.debug("downloading new messages for folder " + folderId);

		IMAPUtil imapUtil = getIMAPUtil();

		imapUtil.storeEnvelopes(folderId);
	}

	public void synchronizeMessage(long messageId)
		throws PortalException, SystemException {

		Message message = MessageLocalServiceUtil.getMessage(messageId);

		IMAPUtil imapUtil = getIMAPUtil();

		long remoteMessageId = message.getRemoteMessageId();

		if (remoteMessageId != 0) {
			try {
				imapUtil.storeMessages(
					message.getFolderId(),
					new long[] { message.getRemoteMessageId() });
			}
			catch (IOException ioe) {
				throw new MailException(ioe);
			}
		}
	}

	public void synchronizePage(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException, SystemException {

		IMAPUtil imapUtil = getIMAPUtil();

		long[] remoteMessageIds = imapUtil.getMessageUIDs(
			folderId, pageNumber, messagesPerPage);

		List<Long> missingRemoteMessageIds = new ArrayList<Long>();

		for (long remoteMessageId : remoteMessageIds) {
			try {
				MessageLocalServiceUtil.getMessage(
					folderId, remoteMessageId);
			}
			catch (NoSuchMessageException nsme) {
				_log.debug("remoteMessageId not cached " + remoteMessageId);

				missingRemoteMessageIds.add(new Long(remoteMessageId));
			}
		}

		if (!missingRemoteMessageIds.isEmpty()) {
			// add to download queue
			//	imapUtil.storeEnvelopes(folderId, remoteMessageIds);
		}
	}

	public void updateFlags(
			long folderId, long[] messageIds, int flag, boolean value)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		Account account = AccountLocalServiceUtil.getAccount(
			folder.getAccountId());

		IMAPUtil imapUtil = getIMAPUtil();

		if (account.getDraftFolderId() == folder.getFolderId()) {
			imapUtil.updateFlags(
				folder.getFolderId(), messageIds, flag, value, false);
		}
		else {
			imapUtil.updateFlags(
				folder.getFolderId(), messageIds, flag, value, true);
		}
	}

	public void validateAccount(
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password)
		throws PortalException {

		IMAPConnection imapConnection = new IMAPConnection(
			incomingHostName, incomingPort, incomingSecure, outgoingHostName,
			outgoingPort, outgoingSecure, login, password);

		imapConnection.testConnection();
	}

	protected IMAPUtil getIMAPUtil() {
		return new IMAPUtil(user, account);
	}

	private static Log _log = LogFactoryUtil.getLog(IMAPMailbox.class);

}