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

package com.liferay.mail.imap;

import com.liferay.mail.MailException;
import com.liferay.mail.MailFile;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.mail.util.HtmlContentUtil;
import com.liferay.mail.util.MailConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.mail.InternetAddressUtil;
import com.liferay.util.portlet.PortletProps;

import com.sun.mail.imap.IMAPFolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.time.StopWatch;

/**
 * <a href="IMAPUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class IMAPUtil {

	public IMAPUtil(User user, Account account) {
		_user = user;
		_account = account;
		_connection = new IMAPConnection(
			account.getIncomingHostName(), account.getIncomingPort(),
			account.getIncomingSecure(), account.getOutgoingHostName(),
			account.getOutgoingPort(), account.getOutgoingSecure(),
			account.getLogin(), account.getPasswordDecrypted());
	}

	public String[] addFolder(String displayName) throws MailException {
		try {
			String fullName = displayName;

			if (Validator.isNotNull(_account.getFolderPrefix())) {
				IMAPFolder defaultFolder =
					(IMAPFolder)_connection.getStore(true).getDefaultFolder();

				fullName = _account.getFolderPrefix() +
					defaultFolder.getSeparator() + displayName;
			}

			IMAPFolder newFolder = getFolder(fullName);

			if (newFolder.exists()) {
				throw new MailException(MailException.FOLDER_ALREADY_EXISTS);
			}
			else {
				if (newFolder.create(IMAPFolder.HOLDS_MESSAGES)) {
					return new String [] {
						newFolder.getFullName(), newFolder.getName()
					};
				}

				throw new MailException(MailException.FOLDER_DENIED_BY_SERVER);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void closeFolder(IMAPFolder folder, boolean expunge)
		throws MailException {

		try {
			if ((folder != null) && (folder.isOpen())) {
				if (expunge) {

					// Need this to trigger message count listener

					folder.expunge();
				}

				folder.close(expunge);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		try {
			IMAPFolder folder = getFolder(folderId);

			if (folder.exists()) {
				folder.delete(true);

				if (folder.exists()) {
					throw new MailException(
						MailException.FOLDER_DENIED_BY_SERVER);
				}
			}
			else {
				throw new MailException(MailException.FOLDER_DOES_NOT_EXISTS);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void deleteMessages(long folderId, long[] messageIds)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			imapFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			List<javax.mail.Message> messages = getMessages(
				imapFolder, messageIds, true);

			for (javax.mail.Message message : messages) {
				message.setFlag(Flags.Flag.DELETED, true);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, true);
		}
	}

	public InputStream getAttachment(
			long folderId, long remoteMessageId, String contentPath)
		throws IOException, PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			javax.mail.Message message = getMessage(
				imapFolder, remoteMessageId);

			if (message == null) {
				throw new MailException(
					MailException.MESSAGE_NOT_FOUND_ON_SERVER);
			}

			return getMessagePart(message, contentPath).getInputStream();
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public List<javax.mail.Folder> getFolders() throws MailException {
		try {
			Store store = _connection.getStore(true);

			javax.mail.Folder rootFolder = store.getDefaultFolder();

			List<javax.mail.Folder> allFolders =
				new ArrayList<javax.mail.Folder>();

			javax.mail.Folder[] folders = rootFolder.list();

			for (javax.mail.Folder folder : folders) {
				try {
					int folderType = folder.getType();

					if ((folderType & javax.mail.Folder.HOLDS_MESSAGES) != 0) {
						allFolders.add(folder);
					}

					if ((folderType & javax.mail.Folder.HOLDS_FOLDERS) != 0) {
						populateFolders(allFolders, folder.list());
					}
				}
				catch (MessagingException me) {
					_log.error("Skipping IMAP folder: " + me.getMessage());
				}
			}

			return allFolders;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public long[] getMessageUIDs(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			int messageCount = imapFolder.getMessageCount();

			int[] messageIndexes = getMessageIndexes(
				messageCount, pageNumber, messagesPerPage);

			javax.mail.Message[] messages = imapFolder.getMessages(
				messageIndexes[0], messageIndexes[1]);

			return getMessageUIDs(imapFolder, messages);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public long[] getMessageUIDs(
			IMAPFolder imapFolder, javax.mail.Message[] messages)
		throws MailException {

		try {
			long messageUIDs[] = new long[messages.length];

			FetchProfile fp = new FetchProfile();

			fp.add(UIDFolder.FetchProfileItem.UID);

			imapFolder.fetch(messages, fp);

			for (int i = 0; i < messages.length; i++) {
				javax.mail.Message message = messages[i];

				messageUIDs[i] = imapFolder.getUID(message);
			}

			return messageUIDs;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void moveMessages(
			long sourceFolderId, long destinationFolderId, long[] messageIds,
			boolean deleteMissingMessages)
		throws PortalException, SystemException {

		IMAPFolder sourceFolder = null;
		IMAPFolder destinationFolder = null;

		try {
			sourceFolder = openFolder(sourceFolderId);
			destinationFolder = openFolder(destinationFolderId);

			sourceFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));
			destinationFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			List<javax.mail.Message> messages = getMessages(
				sourceFolder, messageIds, deleteMissingMessages);

			for (javax.mail.Message message : messages) {
				destinationFolder.appendMessages(
					new javax.mail.Message[] { message });

				message.setFlag(Flags.Flag.DELETED, true);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(sourceFolder, true);
			closeFolder(destinationFolder, false);
		}
	}

	public IMAPFolder openFolder(IMAPFolder imapFolder)
		throws MailException {

		try {
			if (imapFolder.isOpen()) {
				return imapFolder;
			}

			imapFolder.open(IMAPFolder.READ_WRITE);

			return imapFolder;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public String[] renameFolder(long folderId, String newDisplayName)
		throws PortalException, SystemException {

		try {
			IMAPFolder imapFolder = getFolder(folderId);

			String newFullName = StringPool.BLANK;

			if (Validator.isNotNull(_account.getFolderPrefix())) {
				IMAPFolder defaultFolder =
					(IMAPFolder)_connection.getStore(true).getDefaultFolder();

				newFullName = _account.getFolderPrefix() +
					defaultFolder.getSeparator() + newDisplayName;
			}

			if (imapFolder.exists()) {
				if (imapFolder.renameTo(getFolder(newFullName))) {
					return new String [] {
						imapFolder.getFullName(), imapFolder.getName()
					};
				}
				else {
					throw new MailException(
						MailException.FOLDER_DENIED_BY_SERVER);
				}
			}
			else {
				throw new MailException(MailException.FOLDER_DOES_NOT_EXISTS);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void sendMessage(
			String personalName, String sender, Address[] to, Address[] cc,
			Address[] bcc, String subject, String body,
			List<MailFile> mailFiles)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(_account.getSentFolderId());

			javax.mail.Message message = createMessage(
				personalName, sender, to, cc, bcc, subject, body, mailFiles);

			Transport transport = _connection.getTransport();

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

			imapFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			imapFolder.appendMessages(new javax.mail.Message[] {message});
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		catch (UnsupportedEncodingException uee) {
			throw new MailException(uee);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void storeMessagesContent(long folderId, long[] remoteMessageIds)
		throws IOException, PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			javax.mail.Message[] messages = imapFolder.getMessagesByUID(
				remoteMessageIds);

			storeMessagesContent(imapFolder, messages, folderId);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void storeMessagesEnvelope(
			IMAPFolder imapFolder, javax.mail.Message[] messages, long folderId)
		throws PortalException, SystemException {

		StopWatch sw = null;

		if (_log.isDebugEnabled()) {
			sw = new StopWatch();

			sw.start();
		}

		try {
			FetchProfile fp = new FetchProfile();

			fp.add(UIDFolder.FetchProfileItem.UID);
			fp.add(UIDFolder.FetchProfileItem.ENVELOPE);

			imapFolder.fetch(messages, fp);

			for (int i = 0; i < messages.length; i++) {
				javax.mail.Message message = messages[i];

				String sender = InternetAddressUtil.toString(message.getFrom());
				String to = InternetAddressUtil.toString(
					message.getRecipients(RecipientType.TO));
				String cc = InternetAddressUtil.toString(
					message.getRecipients(RecipientType.CC));
				String bcc = InternetAddressUtil.toString(
					message.getRecipients(RecipientType.BCC));
				Date sentDate = message.getSentDate();
				String subject = message.getSubject();
				long remoteMessageId = imapFolder.getUID(message);

				MessageLocalServiceUtil.addMessage(
					_user.getUserId(), folderId, sender, to, cc, bcc,
					sentDate, subject, null, StringPool.BLANK,
					remoteMessageId);
			}

			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			FolderLocalServiceUtil.updateFolder(
				folderId, folder.getFullName(), folder.getDisplayName(),
				imapFolder.getMessageCount());
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}

		if (_log.isDebugEnabled()) {
			sw.stop();

			_log.debug(
				"downloading " + messages.length + " messages for folder " +
					folderId + " completed in " + sw.getTime() + " ms");
		}
	}

	public void storeMessagesEnvelope(long folderId, long[] remoteMessageIds)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			javax.mail.Message[] messages = imapFolder.getMessagesByUID(
				remoteMessageIds);

			storeMessagesEnvelope(imapFolder, messages, folderId);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void storeNewMessagesEnvelope(long folderId)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			int messageCount = imapFolder.getMessageCount();

			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			FolderLocalServiceUtil.updateFolder(
				folderId, folder.getFullName(), folder.getDisplayName(),
				messageCount);

			if (messageCount > 0) {
				StopWatch sw = null;

				if (_log.isDebugEnabled()) {
					sw = new StopWatch();

					sw.start();
				}

				javax.mail.Message oldestMessage = getStoredMessage(
					imapFolder, folderId, true);
				javax.mail.Message newestMessage = getStoredMessage(
					imapFolder, folderId, false);

				if (_log.isDebugEnabled()) {
					sw.stop();

					_log.debug(
						"getStoredMessage (oldest and newest) completed in " +
						sw.getTime() + " ms");

					sw.reset();
					sw.start();
				}

				javax.mail.Message messages[] = new javax.mail.Message[0];

				if ((oldestMessage != null) && (newestMessage != null)) {
					_log.debug(
						"downloading messages in folder for the first time");

					if ((messageCount - _MAX_MESSAGES_TO_STORE_PER_SYNC) < 0) {
						messages = imapFolder.getMessages(1, messageCount);
					}
					else {
						messages = imapFolder.getMessages(
							messageCount - _MAX_MESSAGES_TO_STORE_PER_SYNC,
							messageCount);
					}

					storeMessagesEnvelope(imapFolder, messages, folderId);
				}
				else {
					if (newestMessage != null) {
						_log.debug("downloading new messages in folder");

						int messageNumber = newestMessage.getMessageNumber();

						if (messageNumber != messageCount) {
							messages = imapFolder.getMessages(
								messageNumber, messageCount);

							storeMessagesEnvelope(
								imapFolder, messages, folderId);
						}
					}

					if ((oldestMessage != null) && (messageCount != 1)) {
						_log.debug("downloading old messages in folder");

						int messageNumber = oldestMessage.getMessageNumber();

						if ((messageNumber - _MAX_MESSAGES_TO_STORE_PER_SYNC)
								< 0) {

							messages = imapFolder.getMessages(1, messageNumber);
						}
						else {
							messages = imapFolder.getMessages(
								messageNumber - _MAX_MESSAGES_TO_STORE_PER_SYNC,
								messageNumber);
						}

						storeMessagesEnvelope(imapFolder, messages, folderId);
					}
				}

				if (_log.isDebugEnabled()) {
					sw.stop();

					_log.debug(
						"determining start and end positions for downloading " +
							"messages completed in " + sw.getTime() + " ms");
				}
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void updateMessagesFlag(
			long folderId, long[] messageIds, int flag, boolean value,
			boolean deleteMissingMessages)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			List<javax.mail.Message> messages = getMessages(
				imapFolder, messageIds, deleteMissingMessages);

			for (javax.mail.Message message : messages) {
				if (flag == MailConstants.FLAG_FLAGGED) {
					message.setFlag(Flags.Flag.FLAGGED, value);
				}
				else if (flag == MailConstants.FLAG_SEEN) {
					message.setFlag(Flags.Flag.SEEN, value);
				}
				else {
					throw new MailException(MailException.MESSAGE_INVALID_FLAG);
				}
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, true);
		}
	}

	protected javax.mail.Message createMessage(
			String personalName, String sender, Address[] to, Address[] cc,
			Address[] bcc, String subject, String body,
			List<MailFile> mailFiles)
		throws MessagingException, UnsupportedEncodingException {

		// Create new message

		javax.mail.Message message = new MimeMessage(_connection.getSession());

		// Fill message fields

		message.setFrom(new InternetAddress(sender, personalName));
		message.addRecipients(javax.mail.Message.RecipientType.TO, to);
		message.addRecipients(javax.mail.Message.RecipientType.CC, cc);
		message.addRecipients(javax.mail.Message.RecipientType.BCC, bcc);
		message.setSentDate(new Date());
		message.setSubject(subject);

		MimeMultipart multipart = new MimeMultipart();

		// Add message body to multipart

		BodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(body, ContentTypes.TEXT_HTML_UTF8);

		multipart.addBodyPart(messageBodyPart);

		// Add message attachments to multipart

		if (mailFiles != null) {
			for (MailFile mailFile : mailFiles) {
				File file = mailFile.getFile();
				String fileName = mailFile.getFileName();

				if (!file.exists()) {
					continue;
				}

				DataSource dataSource = new FileDataSource(file);
				BodyPart messageAttachmentPart = new MimeBodyPart();

				messageAttachmentPart.setDataHandler(
					new DataHandler(dataSource));
				messageAttachmentPart.setFileName(fileName);

				multipart.addBodyPart(messageAttachmentPart);
			}
		}

		message.setContent(multipart);

		return message;
	}

	protected String extractFlags(javax.mail.Message message)
		throws MessagingException {

		StringBuilder sb = new StringBuilder();

		if (message.isSet(Flags.Flag.FLAGGED)) {
			sb.append(MailConstants.FLAG_FLAGGED);
			sb.append(StringPool.COMMA);
		}

		if (message.isSet(Flags.Flag.SEEN)) {
			sb.append(MailConstants.FLAG_SEEN);
			sb.append(StringPool.COMMA);
		}

		return sb.toString();
	}

	protected void extractMessageParts(
			long userId, StringBuilder bodyPlain, StringBuilder bodyHtml,
			String contentPath, Part messagePart, List<MailFile> mailFiles)
		throws IOException, MessagingException {

		String contentType = messagePart.getContentType().toLowerCase();
		String filename = messagePart.getFileName();
		Object content = messagePart.getContent();

		if (content instanceof Multipart) {

			// Multipart

			Multipart multipart = (Multipart)content;

			for (int i = 0; i < multipart.getCount(); i++) {
				Part curPart = multipart.getBodyPart(i);

				extractMessageParts(
					userId, bodyPlain, bodyHtml,
					contentPath.concat(StringPool.PERIOD).concat(
						String.valueOf(i)),
					curPart, mailFiles);
			}
		}
		else if (Validator.isNull(filename)) {

			// Get plain message

			if (contentType.startsWith(ContentTypes.TEXT_PLAIN)) {
				bodyPlain.append(
					content.toString().replaceAll("\r\n", "<br />"));
			}

			// Get HTML message

			if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
				bodyHtml.append(
					HtmlContentUtil.getInlineHtml(content.toString()));
			}

			// Get forward message

			if (contentType.startsWith(ContentTypes.MESSAGE_RFC822)) {
				//getBody(
				//	sb, contentPath + StringPool.PERIOD + 0, messagePart,
				//	attachments);
			}
		}
		else {

			// Attachment

			MailFile mailFile = new MailFile(
				contentPath.concat(StringPool.PERIOD).concat("-1"), filename,
				messagePart.getSize());

			mailFiles.add(mailFile);
		}
	}

	protected IMAPFolder getFolder(long folderId)
		throws MessagingException, PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		return (IMAPFolder)_connection.getStore(true).getFolder(
			folder.getFullName());
	}

	protected IMAPFolder getFolder(String fullName)
		throws MailException, MessagingException {

		return (IMAPFolder)_connection.getStore(true).getFolder(fullName);
	}

	protected javax.mail.Message getMessage(
			IMAPFolder folder, long remoteMessageId)
		throws MessagingException {

		return folder.getMessageByUID(remoteMessageId);
	}

	protected int[] getMessageIndexes(
			int messageCount, int page, int messagesPerPage)
		throws MailException {

		int pageCount =
			(int)(Math.ceil(messageCount / (double) messagesPerPage));

		if (messageCount == 0) {
			return new int[] { 0, 0 };
		}
		else if (page > pageCount) {
			throw new MailException(MailException.FOLDER_PAGE_DOES_NOT_EXIST);
		}

		// If there are 20 messages/page and 50 messages total
		//
		// page 1 = { 31 , 50 };
		// page 2 = { 11 , 30 };
		// page 3 = { 1 , 10 };

		int startIndex = messageCount - (page * messagesPerPage) + 1;
		int endIndex = messageCount - ((page - 1) * messagesPerPage);

		if (startIndex < 1) {
			startIndex = 1;
		}

		if (endIndex < 1) {
			endIndex = 1;
		}

		return new int[] { startIndex, endIndex };
	}

	protected Part getMessagePart(Part part, String contentPath)
		throws IOException, MessagingException {

		int index = GetterUtil.getInteger(
			StringUtil.split(contentPath.substring(1), StringPool.PERIOD)[0]);

		if (part.getContent() instanceof Multipart) {
			String prefix = String.valueOf(index).concat(StringPool.PERIOD);

			Multipart multipart = (Multipart)part.getContent();

			for (int i = 0; i < multipart.getCount(); i++) {
				if (index == i) {
					return getMessagePart(
						multipart.getBodyPart(i),
						contentPath.substring(prefix.length()));
				}
			}
		}

		return part;
	}

	protected List<javax.mail.Message> getMessages(
			IMAPFolder imapFolder, long[] messageIds,
			boolean deleteMissingMessages)
		throws MessagingException, PortalException, SystemException {

		long[] messageUIDs = new long[messageIds.length];

		for (int i = 0; i < messageIds.length; i++) {
			Message storeMessage = MessageLocalServiceUtil.getMessage(
				messageIds[i]);

			messageUIDs[i] = storeMessage.getRemoteMessageId();
		}

		javax.mail.Message[] allMessages = imapFolder.getMessagesByUID(
			messageUIDs);

		List<javax.mail.Message> messages =
			new ArrayList<javax.mail.Message>();

		for (int i = 0; i < allMessages.length; i++) {
			javax.mail.Message message = allMessages[i];

			if (message != null) {
				messages.add(message);
			}
			else if (deleteMissingMessages && (messageUIDs[i] != 0)) {
				MessageLocalServiceUtil.deleteMessage(messageIds[i]);
			}
		}

		return messages;
	}

	protected InternetAddress[] getRecipients(
			long messageId, RecipientType recipientType)
		throws PortalException, SystemException {

		try {
			Message message = MessageLocalServiceUtil.getMessage(messageId);

			if (recipientType.equals(RecipientType.TO)) {
				return InternetAddress.parse(message.getTo());
			}
			else if (recipientType.equals(RecipientType.CC)) {
				return InternetAddress.parse(message.getCc());
			}
			else if (recipientType.equals(RecipientType.BCC)) {
				return InternetAddress.parse(message.getBcc());
			}
			else {
				throw new IllegalArgumentException(
					"Invalid recipient type " + recipientType);
			}
		}
		catch (AddressException ae) {
			throw new MailException(MailException.MESSAGE_INVALID_ADDRESS, ae);
		}
	}

	protected InternetAddress[] getRecipients(long messageId)
		throws PortalException, SystemException {

		try {
			Message message = MessageLocalServiceUtil.getMessage(messageId);

			StringBuilder sb = new StringBuilder();

			sb.append(message.getTo());
			sb.append(StringPool.COMMA);
			sb.append(message.getCc());
			sb.append(StringPool.COMMA);
			sb.append(message.getBcc());
			sb.append(StringPool.COMMA);

			return InternetAddress.parse(sb.toString(), true);
		}
		catch (AddressException ae) {
			throw new MailException(MailException.MESSAGE_INVALID_ADDRESS, ae);
		}
	}

	protected javax.mail.Message getStoredMessage(
			IMAPFolder imapFolder, long folderId, boolean oldest)
		throws MessagingException, PortalException, SystemException {

		Message message = MessageLocalServiceUtil.getRemoteMessage(
			folderId, oldest);

		// Get oldest/newest local message which exists on the mail server

		if (message == null) {
			return null;
		}
		else {
			javax.mail.Message remoteMessage = getMessage(
				imapFolder, message.getRemoteMessageId());

			if (remoteMessage == null) {
				MessageLocalServiceUtil.deleteMessage(message);

				return getStoredMessage(imapFolder, folderId, oldest);
			}
			else {
				return remoteMessage;
			}
		}
	}

	protected IMAPFolder openFolder(long folderId)
		throws MessagingException, PortalException, SystemException {

		return openFolder(getFolder(folderId));
	}

	protected void populateFolders(
			List<javax.mail.Folder> allFolders, javax.mail.Folder[] folders) {

		for (javax.mail.Folder folder : folders) {
			try {
				int folderType = folder.getType();

				if ((folderType & javax.mail.Folder.HOLDS_MESSAGES) != 0) {
					allFolders.add(folder);
				}

				if ((folderType & javax.mail.Folder.HOLDS_FOLDERS) != 0) {
					populateFolders(allFolders, folder.list());
				}
			}
			catch (MessagingException me) {
				_log.error("Skipping IMAP folder: " + me.getMessage());
			}
		}
	}

	protected void storeMessagesContent(
			IMAPFolder imapFolder, javax.mail.Message[] messages, long folderId)
		throws IOException, MessagingException, PortalException,
			SystemException {

		FetchProfile fp = new FetchProfile();

		fp.add(UIDFolder.FetchProfileItem.UID);
		fp.add(UIDFolder.FetchProfileItem.CONTENT_INFO);

		imapFolder.fetch(messages, fp);

		for (int i = 0; i < messages.length; i++) {
			javax.mail.Message remoteMessage = messages[i];

			// Get flags before body to preserve the "read/unread" flag

			String flags = extractFlags(remoteMessage);
			long remoteMessageId = imapFolder.getUID(remoteMessage);

			Message message = MessageLocalServiceUtil.getMessage(
				folderId, remoteMessageId);

			// Get message parts

			List<MailFile> mailFiles = new ArrayList<MailFile>();
			StringBuilder bodyPlain = new StringBuilder();
			StringBuilder bodyHtml = new StringBuilder();

			extractMessageParts(
				_user.getUserId(), bodyPlain, bodyHtml, StringPool.BLANK,
				remoteMessage, mailFiles);

			if (bodyHtml.length() == 0) {
				bodyHtml = bodyPlain;
			}

			if (flags.indexOf(MailConstants.FLAG_SEEN) == -1) {
				remoteMessage.setFlag(Flags.Flag.SEEN, false);
			}

			for (MailFile mailFile : mailFiles) {
				AttachmentLocalServiceUtil.addAttachment(
					_user.getUserId(), message.getMessageId(),
					mailFile.getContentPath(), mailFile.getFileName(),
					mailFile.getSize(), mailFile.getFile());
			}

			MessageLocalServiceUtil.updateMessageContent(
				message.getMessageId(), bodyHtml.toString(), flags);
		}
	}

	private static final int _MAX_MESSAGES_TO_STORE_PER_SYNC =
		GetterUtil.getInteger(
			PortletProps.get("max.messages.to.store.per.sync"));

	private static Log _log = LogFactoryUtil.getLog(IMAPUtil.class);

	private Account _account;
	private IMAPConnection _connection;
	private User _user;

}