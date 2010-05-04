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
import com.liferay.mail.NoSuchMessageException;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.mail.util.HtmlContentUtil;
import com.liferay.mail.util.MailConstants;
import com.liferay.mail.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.mail.InternetAddressUtil;

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
import javax.mail.Folder;
import javax.mail.Message.RecipientType;
import javax.mail.Message;
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
		_imapConnection = new IMAPConnection(account);
	}

	public String[] addFolder(String displayName) throws MailException {
		try {
			String fullName = displayName;

			if (Validator.isNotNull(_account.getFolderPrefix())) {
				Store store = _imapConnection.getStore(true);

				IMAPFolder defaultIMAPFolder =
					(IMAPFolder)store.getDefaultFolder();

				fullName =
					_account.getFolderPrefix() +
						defaultIMAPFolder.getSeparator() + displayName;
			}

			IMAPFolder imapFolder = getFolder(fullName);

			if (imapFolder.exists()) {
				throw new MailException(MailException.FOLDER_ALREADY_EXISTS);
			}
			else {
				if (imapFolder.create(IMAPFolder.HOLDS_MESSAGES)) {
					return new String[] {
						imapFolder.getFullName(), imapFolder.getName()
					};
				}

				throw new MailException(MailException.FOLDER_CREATE_FAILED);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void closeFolder(IMAPFolder imapFolder, boolean expunge)
		throws MailException {

		try {
			if ((imapFolder == null) || !imapFolder.isOpen()) {
				return;
			}

			if (expunge) {
				imapFolder.expunge();
			}

			imapFolder.close(expunge);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void deleteFolder(long storedFolderId)
		throws PortalException, SystemException {

		try {
			IMAPFolder imapFolder = getFolder(storedFolderId);

			if (!imapFolder.exists()) {
				throw new MailException(MailException.FOLDER_DOES_NOT_EXIST);
			}

			imapFolder.delete(true);

			if (imapFolder.exists()) {
				throw new MailException(MailException.FOLDER_DELETE_FAILED);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public void deleteMessages(long storedFolderId, long[] messageIds)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			imapFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			List<Message> messages = getMessages(imapFolder, messageIds, true);

			for (Message message : messages) {
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
			long storedFolderId, long messageId, String contentPath)
		throws IOException, PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			Message message = getMessage(imapFolder, messageId);

			if (message == null) {
				throw new MailException(
					MailException.MESSAGE_NOT_FOUND_ON_SERVER);
			}

			Part part = getPart(message, contentPath);

			return part.getInputStream();
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public List<Folder> getFolders() throws MailException {
		try {
			List<Folder> folders = new ArrayList<Folder>();

			Store store = _imapConnection.getStore(true);

			Folder folder = store.getDefaultFolder();

			getFolders(folders, folder.list());

			return folders;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public long[] getMessageUIDs(IMAPFolder imapFolder, Message[] messages)
		throws MailException {

		try {
			FetchProfile fetchProfile = new FetchProfile();

			fetchProfile.add(UIDFolder.FetchProfileItem.UID);

			imapFolder.fetch(messages, fetchProfile);

			long[] messageIds = new long[messages.length];

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				messageIds[i] = imapFolder.getUID(message);
			}

			return messageIds;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public long[] getMessageUIDs(
			long storedFolderId, int pageNumber, int messagesPerPage)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			int[] messageIndexes = getMessageIndexes(
				imapFolder.getMessageCount(), pageNumber, messagesPerPage);

			Message[] messages = imapFolder.getMessages(
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

	public void moveMessages(
			long sourceStoredFolderId, long destinationStoredFolderId,
			long[] messageIds, boolean deleteMissingMessages)
		throws PortalException, SystemException {

		IMAPFolder sourceIMAPFolder = null;
		IMAPFolder destinationIMAPFolder = null;

		try {
			sourceIMAPFolder = openFolder(sourceStoredFolderId);

			sourceIMAPFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			destinationIMAPFolder = openFolder(destinationStoredFolderId);

			destinationIMAPFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			List<Message> messages = getMessages(
				sourceIMAPFolder, messageIds, deleteMissingMessages);

			for (Message message : messages) {
				destinationIMAPFolder.appendMessages(new Message[] {message});

				message.setFlag(Flags.Flag.DELETED, true);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(sourceIMAPFolder, true);
			closeFolder(destinationIMAPFolder, false);
		}
	}

	public IMAPFolder openFolder(IMAPFolder imapFolder) throws MailException {
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

	public String[] renameFolder(long storedFolderId, String newDisplayName)
		throws PortalException, SystemException {

		try {
			IMAPFolder imapFolder = getFolder(storedFolderId);

			String newFullName = StringPool.BLANK;

			if (Validator.isNotNull(_account.getFolderPrefix())) {
				Store store = _imapConnection.getStore(true);

				IMAPFolder defaultIMAPFolder =
					(IMAPFolder)store.getDefaultFolder();

				newFullName =
					_account.getFolderPrefix() +
						defaultIMAPFolder.getSeparator() + newDisplayName;
			}

			if (!imapFolder.exists()) {
				throw new MailException(MailException.FOLDER_DOES_NOT_EXIST);
			}

			if (imapFolder.renameTo(getFolder(newFullName))) {
				return new String[] {
					imapFolder.getFullName(), imapFolder.getName()
				};
			}
			else {
				throw new MailException(MailException.FOLDER_RENAME_FAILED);
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

			Message message = createMessage(
				personalName, sender, to, cc, bcc, subject, body, mailFiles);

			Transport transport = _imapConnection.getTransport();

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

			imapFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account));

			imapFolder.appendMessages(new Message[] {message});
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

	public void storeEnvelopes(long storedFolderId)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			com.liferay.mail.model.Folder storedFolder =
				FolderLocalServiceUtil.getFolder(storedFolderId);

			int messageCount = imapFolder.getMessageCount();

			FolderLocalServiceUtil.updateFolder(
				storedFolderId, storedFolder.getFullName(),
				storedFolder.getDisplayName(), messageCount);

			if (messageCount == 0) {
				return;
			}

			Message oldestMessage = getMessage(
				storedFolderId, imapFolder, true);
			Message newestMessage = getMessage(
				storedFolderId, imapFolder, false);

			Message messages[] = new Message[0];

			if ((oldestMessage == null) && (newestMessage == null)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Downloading messages from folder " +
							imapFolder.getFullName() + " for the first time");
				}

				int startingMessageNumber =
					messageCount - PortletPropsValues.MESSAGES_SYNC_COUNT;

				if (startingMessageNumber < 1) {
					startingMessageNumber = 1;
				}

				messages = imapFolder.getMessages(
					startingMessageNumber, messageCount);

				storeEnvelopes(storedFolderId, imapFolder, messages);
			}
			else {
				int newestMessageNumber = newestMessage.getMessageNumber();

				if (newestMessageNumber != messageCount) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Downloading new messages from folder " +
								imapFolder.getFullName());
					}

					messages = imapFolder.getMessages(
						newestMessageNumber + 1, messageCount);

					storeEnvelopes(storedFolderId, imapFolder, messages);
				}

				int oldestMessageNumber = oldestMessage.getMessageNumber();

				if (oldestMessageNumber != 1) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Downloading old messages from folder " +
								imapFolder.getFullName());
					}

					int startingMessageNumber =
						oldestMessageNumber -
							PortletPropsValues.MESSAGES_SYNC_COUNT;

					if (startingMessageNumber < 1) {
						startingMessageNumber = 1;
					}

					messages = imapFolder.getMessages(
						startingMessageNumber, oldestMessageNumber - 1);

					storeEnvelopes(storedFolderId, imapFolder, messages);
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

	public void storeEnvelopes(
			long storedFolderId, IMAPFolder imapFolder, Message[] messages)
		throws PortalException, SystemException {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		try {
			FetchProfile fetchProfile = new FetchProfile();

			fetchProfile.add(UIDFolder.FetchProfileItem.UID);
			fetchProfile.add(UIDFolder.FetchProfileItem.ENVELOPE);

			imapFolder.fetch(messages, fetchProfile);

			for (Message message : messages) {
				String sender = InternetAddressUtil.toString(message.getFrom());
				String to = InternetAddressUtil.toString(
					message.getRecipients(RecipientType.TO));
				String cc = InternetAddressUtil.toString(
					message.getRecipients(RecipientType.CC));
				String bcc = InternetAddressUtil.toString(
					message.getRecipients(RecipientType.BCC));
				Date sentDate = message.getSentDate();
				String subject = message.getSubject();
				long messageId = imapFolder.getUID(message);

				MessageLocalServiceUtil.addMessage(
					_user.getUserId(), storedFolderId, sender, to, cc, bcc,
					sentDate, subject, null, StringPool.BLANK, messageId);
			}

			com.liferay.mail.model.Folder storedFolder =
				FolderLocalServiceUtil.getFolder(storedFolderId);

			FolderLocalServiceUtil.updateFolder(
				storedFolderId, storedFolder.getFullName(),
				storedFolder.getDisplayName(), imapFolder.getMessageCount());
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}

		if (_log.isDebugEnabled()) {
			stopWatch.stop();

			_log.debug(
				"Downloaded " + messages.length + " messages from folder " +
					imapFolder.getFullName() + " completed in " +
						stopWatch.getTime() + " ms");
		}
	}

	public void storeEnvelopes(long storedFolderId, long[] messageIds)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			Message[] messages = imapFolder.getMessagesByUID(messageIds);

			storeEnvelopes(storedFolderId, imapFolder, messages);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void storeMessages(long storedFolderId, long[] messageIds)
		throws IOException, PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			Message[] messages = imapFolder.getMessagesByUID(messageIds);

			FetchProfile fetchProfile = new FetchProfile();

			fetchProfile.add(UIDFolder.FetchProfileItem.UID);
			fetchProfile.add(UIDFolder.FetchProfileItem.CONTENT_INFO);

			imapFolder.fetch(messages, fetchProfile);

			for (Message message : messages) {
				String flags = getFlags(message);

				long messageId = imapFolder.getUID(message);

				com.liferay.mail.model.Message storedMessage =
					MessageLocalServiceUtil.getMessage(
						storedFolderId, messageId);

				StringBundler bodyPlain = new StringBundler();
				StringBundler bodyHtml = new StringBundler();
				List<MailFile> mailFiles = new ArrayList<MailFile>();

				getParts(
					_user.getUserId(), bodyPlain, bodyHtml, StringPool.BLANK,
					message, mailFiles);

				if (bodyHtml.length() == 0) {
					bodyHtml = bodyPlain;
				}

				if (flags.indexOf(MailConstants.FLAG_SEEN) == -1) {
					message.setFlag(Flags.Flag.SEEN, false);
				}

				for (MailFile mailFile : mailFiles) {
					AttachmentLocalServiceUtil.addAttachment(
						_user.getUserId(), storedMessage.getMessageId(),
						mailFile.getContentPath(), mailFile.getFileName(),
						mailFile.getSize(), mailFile.getFile());
				}

				MessageLocalServiceUtil.updateMessageContent(
					storedMessage.getMessageId(), bodyHtml.toString(), flags);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void updateFlags(
			long storedFolderId, long[] messageIds, int flag, boolean value,
			boolean deleteMissingMessages)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(storedFolderId);

			List<Message> messages = getMessages(
				imapFolder, messageIds, deleteMissingMessages);

			for (Message message : messages) {
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

	protected Message createMessage(
			String personalName, String sender, Address[] to, Address[] cc,
			Address[] bcc, String subject, String body,
			List<MailFile> mailFiles)
		throws MessagingException, UnsupportedEncodingException {

		Message message = new MimeMessage(_imapConnection.getSession());

		message.setFrom(new InternetAddress(sender, personalName));
		message.addRecipients(Message.RecipientType.TO, to);
		message.addRecipients(Message.RecipientType.CC, cc);
		message.addRecipients(Message.RecipientType.BCC, bcc);
		message.setSentDate(new Date());
		message.setSubject(subject);

		MimeMultipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(body, ContentTypes.TEXT_HTML_UTF8);

		multipart.addBodyPart(messageBodyPart);

		if (mailFiles != null) {
			for (MailFile mailFile : mailFiles) {
				File file = mailFile.getFile();

				if (!file.exists()) {
					continue;
				}

				DataSource dataSource = new FileDataSource(file);

				BodyPart attachmentBodyPart = new MimeBodyPart();

				attachmentBodyPart.setDataHandler(
					new DataHandler(dataSource));
				attachmentBodyPart.setFileName(mailFile.getFileName());

				multipart.addBodyPart(attachmentBodyPart);
			}
		}

		message.setContent(multipart);

		return message;
	}

	protected String getFlags(Message message) throws MessagingException {
		StringBundler sb = new StringBundler();

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

	protected IMAPFolder getFolder(long storedFolderId)
		throws MessagingException, PortalException, SystemException {

		Store store = _imapConnection.getStore(true);

		com.liferay.mail.model.Folder storedFolder =
			FolderLocalServiceUtil.getFolder(storedFolderId);

		return (IMAPFolder)store.getFolder(storedFolder.getFullName());
	}

	protected IMAPFolder getFolder(String fullName)
		throws MailException, MessagingException {

		Store store = _imapConnection.getStore(true);

		return (IMAPFolder)store.getFolder(fullName);
	}

	protected void getFolders(List<Folder> allFolders, Folder[] folders) {
		for (Folder folder : folders) {
			try {
				int folderType = folder.getType();

				if ((folderType & Folder.HOLDS_MESSAGES) != 0) {
					allFolders.add(folder);
				}

				if ((folderType & Folder.HOLDS_FOLDERS) != 0) {
					getFolders(allFolders, folder.list());
				}
			}
			catch (MessagingException me) {
				_log.error("Unable to get folder " + folder.getFullName(), me);
			}
		}
	}

	protected Message getMessage(IMAPFolder imapFolder, long messageId)
		throws MessagingException {

		return imapFolder.getMessageByUID(messageId);
	}

	protected Message getMessage(
			long storedFolderId, IMAPFolder imapFolder, boolean oldest)
		throws MessagingException, PortalException, SystemException {

		com.liferay.mail.model.Message storedMessage = null;

		try {
			storedMessage = MessageLocalServiceUtil.getRemoteMessage(
				storedFolderId, oldest);
		}
		catch (NoSuchMessageException nsme) {
			return null;
		}

		long messageId = storedMessage.getRemoteMessageId();

		Message message = getMessage(imapFolder, messageId);

		if (message == null) {
			MessageLocalServiceUtil.deleteMessage(storedMessage);

			return getMessage(storedFolderId, imapFolder, oldest);
		}
		else {
			return message;
		}
	}

	protected int[] getMessageIndexes(
			int messageCount, int page, int messagesPerPage)
		throws MailException {

		int pageCount =
			(int)(Math.ceil(messageCount / (double) messagesPerPage));

		if (messageCount == 0) {
			return new int[] {0, 0};
		}
		else if (page > pageCount) {
			throw new MailException(MailException.FOLDER_PAGE_DOES_NOT_EXIST);
		}

		int startIndex = messageCount - (page * messagesPerPage) + 1;
		int endIndex = messageCount - ((page - 1) * messagesPerPage);

		if (startIndex < 1) {
			startIndex = 1;
		}

		if (endIndex < 1) {
			endIndex = 1;
		}

		return new int[] {startIndex, endIndex};
	}

	protected List<Message> getMessages(
			IMAPFolder imapFolder, long[] storedMessageIds,
			boolean deleteMissingMessages)
		throws MessagingException, PortalException, SystemException {

		long[] messageIds = new long[storedMessageIds.length];

		for (int i = 0; i < storedMessageIds.length; i++) {
			com.liferay.mail.model.Message storedmessage =
				MessageLocalServiceUtil.getMessage(storedMessageIds[i]);

			messageIds[i] = storedmessage.getRemoteMessageId();
		}

		List<Message> messages = new ArrayList<javax.mail.Message>();

		Message[] messagesArray = imapFolder.getMessagesByUID(messageIds);

		for (int i = 0; i < messagesArray.length; i++) {
			Message message = messagesArray[i];

			if (message != null) {
				messages.add(message);
			}
			else if (deleteMissingMessages && (messageIds[i] != 0)) {
				MessageLocalServiceUtil.deleteMessage(storedMessageIds[i]);
			}
		}

		return messages;
	}

	protected Part getPart(Part part, String contentPath)
		throws IOException, MessagingException {

		int index = GetterUtil.getInteger(
			StringUtil.split(contentPath.substring(1), StringPool.PERIOD)[0]);

		if (part.getContent() instanceof Multipart) {
			String prefix = String.valueOf(index).concat(StringPool.PERIOD);

			Multipart multipart = (Multipart)part.getContent();

			for (int i = 0; i < multipart.getCount(); i++) {
				if (index == i) {
					return getPart(
						multipart.getBodyPart(i),
						contentPath.substring(prefix.length()));
				}
			}
		}

		return part;
	}

	protected void getParts(
			long userId, StringBundler bodyPlain, StringBundler bodyHtml,
			String contentPath, Part part, List<MailFile> mailFiles)
		throws IOException, MessagingException {

		String fileName = part.getFileName();
		Object content = part.getContent();

		if (content instanceof Multipart) {
			Multipart multipart = (Multipart)content;

			for (int i = 0; i < multipart.getCount(); i++) {
				Part curPart = multipart.getBodyPart(i);

				getParts(
					userId, bodyPlain, bodyHtml,
					contentPath.concat(StringPool.PERIOD).concat(
						String.valueOf(i)),
					curPart, mailFiles);
			}
		}
		else if (Validator.isNull(fileName)) {
			String contentType = part.getContentType().toLowerCase();

			if (contentType.startsWith(ContentTypes.TEXT_PLAIN)) {
				bodyPlain.append(
					content.toString().replaceAll("\r\n", "<br />"));
			}
			else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
				bodyHtml.append(
					HtmlContentUtil.getInlineHtml(content.toString()));
			}
			//else if (contentType.startsWith(ContentTypes.MESSAGE_RFC822)) {
			//}
		}
		else {
			MailFile mailFile = new MailFile(
				contentPath.concat(StringPool.PERIOD).concat("-1"), fileName,
				part.getSize());

			mailFiles.add(mailFile);
		}
	}

	protected InternetAddress[] getRecipients(long storedMessageId)
		throws PortalException, SystemException {

		try {
			com.liferay.mail.model.Message storedMessage =
				MessageLocalServiceUtil.getMessage(storedMessageId);

			StringBundler sb = new StringBundler(6);

			sb.append(storedMessage.getTo());
			sb.append(StringPool.COMMA);
			sb.append(storedMessage.getCc());
			sb.append(StringPool.COMMA);
			sb.append(storedMessage.getBcc());
			sb.append(StringPool.COMMA);

			return InternetAddress.parse(sb.toString(), true);
		}
		catch (AddressException ae) {
			throw new MailException(MailException.MESSAGE_INVALID_ADDRESS, ae);
		}
	}

	protected InternetAddress[] getRecipients(
			long storedMessageId, RecipientType recipientType)
		throws PortalException, SystemException {

		try {
			com.liferay.mail.model.Message storedMessage =
				MessageLocalServiceUtil.getMessage(storedMessageId);

			if (recipientType.equals(RecipientType.TO)) {
				return InternetAddress.parse(storedMessage.getTo());
			}
			else if (recipientType.equals(RecipientType.CC)) {
				return InternetAddress.parse(storedMessage.getCc());
			}
			else if (recipientType.equals(RecipientType.BCC)) {
				return InternetAddress.parse(storedMessage.getBcc());
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

	protected IMAPFolder openFolder(long storedFolderId)
		throws MessagingException, PortalException, SystemException {

		return openFolder(getFolder(storedFolderId));
	}

	private static Log _log = LogFactoryUtil.getLog(IMAPUtil.class);

	private Account _account;
	private IMAPConnection _imapConnection;
	private User _user;

}