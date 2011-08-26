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

package com.liferay.mail.imap;

import com.liferay.mail.MailException;
import com.liferay.mail.NoSuchMessageException;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.mail.util.AttachmentHandler;
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
 * @author Scott Lee
 */
public class IMAPAccessor {

	public IMAPAccessor(User user, Account account, String password) {
		_user = user;
		_account = account;
		_password = password;
		_imapConnection = new IMAPConnection(account, password);
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

	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		try {
			IMAPFolder imapFolder = getFolder(folderId);

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

	public void deleteMessages(long folderId, long[] messageIds)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			imapFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account, _password));

			List<Message> jxMessages = getMessages(
				imapFolder, messageIds, true);

			for (Message jxMessage : jxMessages) {
				jxMessage.setFlag(Flags.Flag.DELETED, true);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, true);
		}
	}

	public AttachmentHandler getAttachment(
			long folderId, long messageId, String contentPath)
		throws IOException, PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			Message jxMessage = getMessage(imapFolder, messageId);

			if (jxMessage == null) {
				throw new MailException(
					MailException.MESSAGE_NOT_FOUND_ON_SERVER);
			}

			Part part = getPart(jxMessage, contentPath);

			AttachmentHandler attachmentHandler = new IMAPAttachmentHandler(
				part.getInputStream(), imapFolder);

			return attachmentHandler;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public List<Folder> getFolders() throws MailException {
		try {
			List<Folder> jxFolders = new ArrayList<Folder>();

			Store store = _imapConnection.getStore(true);

			Folder jxFolder = store.getDefaultFolder();

			getFolders(jxFolders, jxFolder.list());

			return jxFolders;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	public long[] getMessageUIDs(IMAPFolder imapFolder, Message[] jxMessages)
		throws MailException {

		try {
			FetchProfile fetchProfile = new FetchProfile();

			fetchProfile.add(UIDFolder.FetchProfileItem.UID);

			imapFolder.fetch(jxMessages, fetchProfile);

			long[] remoteMessageIds = new long[jxMessages.length];

			for (int i = 0; i < jxMessages.length; i++) {
				Message jxMessage = jxMessages[i];

				remoteMessageIds[i] = imapFolder.getUID(jxMessage);
			}

			return remoteMessageIds;
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

			int[] messageIndexes = getMessageIndexes(
				imapFolder.getMessageCount(), pageNumber, messagesPerPage);

			if (messageIndexes[0] == 0) {
				return new long[0];
			}

			Message[] jxMessages = imapFolder.getMessages(
				messageIndexes[0], messageIndexes[1]);

			return getMessageUIDs(imapFolder, jxMessages);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public boolean hasNewMessages(long folderId)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			int messageCount = imapFolder.getMessageCount();

			Message jxMessage = getMessage(folderId, imapFolder, false);

			if (jxMessage == null) {
				if (messageCount <= 0) {
					return false;
				}

				return true;
			}

			if (messageCount == jxMessage.getMessageNumber()) {
				return false;
			}

			return true;
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void moveMessages(
			long sourceFolderId, long destinationFolderId, long[] messageIds,
			boolean deleteMissingMessages)
		throws PortalException, SystemException {

		IMAPFolder sourceIMAPFolder = null;
		IMAPFolder destinationIMAPFolder = null;

		try {
			sourceIMAPFolder = openFolder(sourceFolderId);

			sourceIMAPFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account, _password));

			destinationIMAPFolder = openFolder(destinationFolderId);

			destinationIMAPFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account, _password));

			List<Message> jxMessages = getMessages(
				sourceIMAPFolder, messageIds, deleteMissingMessages);

			for (Message jxMessage : jxMessages) {
				destinationIMAPFolder.appendMessages(new Message[] {jxMessage});

				jxMessage.setFlag(Flags.Flag.DELETED, true);
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

	public String[] renameFolder(long folderId, String newDisplayName)
		throws PortalException, SystemException {

		try {
			IMAPFolder imapFolder = getFolder(folderId);

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

			Message jxMessage = createMessage(
				personalName, sender, to, cc, bcc, subject, body, mailFiles);

			Transport transport = _imapConnection.getTransport();

			transport.sendMessage(jxMessage, jxMessage.getAllRecipients());

			transport.close();

			imapFolder.addMessageCountListener(
				new IMAPMessageCountListener(_user, _account, _password));

			imapFolder.appendMessages(new Message[] {jxMessage});
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

	public void storeContents(long folderId, long[] remoteMessageIds)
		throws IOException, PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			Message[] jxMessages = imapFolder.getMessagesByUID(
				remoteMessageIds);

			FetchProfile fetchProfile = new FetchProfile();

			fetchProfile.add(UIDFolder.FetchProfileItem.CONTENT_INFO);
			fetchProfile.add(UIDFolder.FetchProfileItem.FLAGS);
			fetchProfile.add(UIDFolder.FetchProfileItem.UID);

			imapFolder.fetch(jxMessages, fetchProfile);

			for (Message jxMessage : jxMessages) {
				String flags = getFlags(jxMessage);

				long remoteMessageId = imapFolder.getUID(jxMessage);

				com.liferay.mail.model.Message message =
					MessageLocalServiceUtil.getMessage(
						folderId, remoteMessageId);

				StringBundler bodyPlain = new StringBundler();
				StringBundler bodyHtml = new StringBundler();
				List<MailFile> mailFiles = new ArrayList<MailFile>();

				getParts(
					_user.getUserId(), bodyPlain, bodyHtml, StringPool.BLANK,
					jxMessage, mailFiles);

				if (bodyHtml.length() == 0) {
					if (bodyPlain.length() == 0) {
						bodyHtml.append("&nbsp;");
					}
					else {
						bodyHtml = bodyPlain;
					}
				}

				if (flags.indexOf(MailConstants.FLAG_SEEN) == -1) {
					jxMessage.setFlag(Flags.Flag.SEEN, false);
				}

				AttachmentLocalServiceUtil.deleteAttachments(
					message.getCompanyId(), message.getMessageId());

				for (MailFile mailFile : mailFiles) {
					AttachmentLocalServiceUtil.addAttachment(
						_user.getUserId(), message.getMessageId(),
						mailFile.getContentPath(), mailFile.getFileName(),
						mailFile.getSize(), mailFile.getFile());
				}

				MessageLocalServiceUtil.updateContent(
					message.getMessageId(), bodyHtml.toString(), flags);
			}
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void storeEnvelopes(long folderId, boolean allMessages)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			com.liferay.mail.model.Folder folder =
				FolderLocalServiceUtil.fetchFolder(folderId);

			if (folder == null) {
				return;
			}

			imapFolder = openFolder(folderId);

			int messageCount = imapFolder.getMessageCount();

			FolderLocalServiceUtil.updateFolder(
				folderId, folder.getFullName(), folder.getDisplayName(),
				messageCount);

			if (messageCount == 0) {
				return;
			}

			Message oldestJxMessage = getMessage(folderId, imapFolder, true);
			Message newestJxMessage = getMessage(folderId, imapFolder, false);

			Message[] jxMessages = new Message[0];

			if (allMessages) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Downloading all messages from folder " +
							imapFolder.getFullName());
				}

				jxMessages = imapFolder.getMessages();
			}
			else if ((oldestJxMessage == null) && (newestJxMessage == null)) {
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

				jxMessages = imapFolder.getMessages(
					startingMessageNumber, messageCount);
			}
			else {
				int oldestMessageNumber = oldestJxMessage.getMessageNumber();
				int newestMessageNumber = newestJxMessage.getMessageNumber();

				if (newestMessageNumber != messageCount) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Downloading new messages from folder " +
								imapFolder.getFullName());
					}

					jxMessages = imapFolder.getMessages(
						newestMessageNumber + 1, messageCount);
				}
				else if (oldestMessageNumber != 1) {
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

					jxMessages = imapFolder.getMessages(
						startingMessageNumber, oldestMessageNumber - 1);
				}
			}

			storeEnvelopes(folderId, imapFolder, jxMessages);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void storeEnvelopes(
			long folderId, IMAPFolder imapFolder, Message[] jxMessages)
		throws PortalException, SystemException {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		try {
			FetchProfile fetchProfile = new FetchProfile();

			fetchProfile.add(UIDFolder.FetchProfileItem.ENVELOPE);
			fetchProfile.add(UIDFolder.FetchProfileItem.FLAGS);
			fetchProfile.add(UIDFolder.FetchProfileItem.UID);

			imapFolder.fetch(jxMessages, fetchProfile);

			for (Message jxMessage : jxMessages) {
				String sender = InternetAddressUtil.toString(
					jxMessage.getFrom());
				String to = InternetAddressUtil.toString(
					jxMessage.getRecipients(RecipientType.TO));
				String cc = InternetAddressUtil.toString(
					jxMessage.getRecipients(RecipientType.CC));
				String bcc = InternetAddressUtil.toString(
					jxMessage.getRecipients(RecipientType.BCC));
				Date sentDate = jxMessage.getSentDate();
				String subject = jxMessage.getSubject();
				String flags = getFlags(jxMessage);
				long remoteMessageId = imapFolder.getUID(jxMessage);

				try {
					MessageLocalServiceUtil.getMessage(
						folderId, remoteMessageId);
				}
				catch (NoSuchMessageException nsme) {
					MessageLocalServiceUtil.addMessage(
						_user.getUserId(), folderId, sender, to, cc, bcc,
						sentDate, subject, StringPool.BLANK, flags,
						remoteMessageId);
				}
			}

			com.liferay.mail.model.Folder folder =
				FolderLocalServiceUtil.getFolder(folderId);

			FolderLocalServiceUtil.updateFolder(
				folderId, folder.getFullName(), folder.getDisplayName(),
				imapFolder.getMessageCount());
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}

		if (_log.isDebugEnabled()) {
			stopWatch.stop();

			_log.debug(
				"Downloaded " + jxMessages.length + " messages from folder " +
					imapFolder.getFullName() + " completed in " +
						stopWatch.getTime() + " ms");
		}
	}

	public void storeEnvelopes(long folderId, long[] remoteMessageIds)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			Message[] jxMessages = imapFolder.getMessagesByUID(
				remoteMessageIds);

			storeEnvelopes(folderId, imapFolder, jxMessages);
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
		finally {
			closeFolder(imapFolder, false);
		}
	}

	public void updateFlags(
			long folderId, long[] messageIds, int flag, boolean value,
			boolean deleteMissingMessages)
		throws PortalException, SystemException {

		IMAPFolder imapFolder = null;

		try {
			imapFolder = openFolder(folderId);

			List<Message> jxMessages = getMessages(
				imapFolder, messageIds, deleteMissingMessages);

			for (Message jxMessage : jxMessages) {
				if (flag == MailConstants.FLAG_FLAGGED) {
					jxMessage.setFlag(Flags.Flag.FLAGGED, value);
				}
				else if (flag == MailConstants.FLAG_SEEN) {
					jxMessage.setFlag(Flags.Flag.SEEN, value);
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

		Message jxMessage = new MimeMessage(_imapConnection.getSession());

		jxMessage.setFrom(new InternetAddress(sender, personalName));
		jxMessage.addRecipients(Message.RecipientType.TO, to);
		jxMessage.addRecipients(Message.RecipientType.CC, cc);
		jxMessage.addRecipients(Message.RecipientType.BCC, bcc);
		jxMessage.setSentDate(new Date());
		jxMessage.setSubject(subject);

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

		jxMessage.setContent(multipart);

		return jxMessage;
	}

	protected String getFlags(Message jxMessage) throws MessagingException {
		StringBundler sb = new StringBundler();

		if (jxMessage.isSet(Flags.Flag.FLAGGED)) {
			sb.append(MailConstants.FLAG_FLAGGED);
			sb.append(StringPool.COMMA);
		}

		if (jxMessage.isSet(Flags.Flag.SEEN)) {
			sb.append(MailConstants.FLAG_SEEN);
			sb.append(StringPool.COMMA);
		}

		return sb.toString();
	}

	protected IMAPFolder getFolder(long folderId)
		throws MessagingException, PortalException, SystemException {

		Store store = _imapConnection.getStore(true);

		com.liferay.mail.model.Folder folder = FolderLocalServiceUtil.getFolder(
			folderId);

		return (IMAPFolder)store.getFolder(folder.getFullName());
	}

	protected IMAPFolder getFolder(String fullName)
		throws MailException, MessagingException {

		Store store = _imapConnection.getStore(true);

		return (IMAPFolder)store.getFolder(fullName);
	}

	protected void getFolders(List<Folder> allJxFolders, Folder[] jxFolders) {
		for (Folder jxFolder : jxFolders) {
			try {
				int folderType = jxFolder.getType();

				if ((folderType & Folder.HOLDS_MESSAGES) != 0) {
					allJxFolders.add(jxFolder);
				}

				if ((folderType & Folder.HOLDS_FOLDERS) != 0) {
					getFolders(allJxFolders, jxFolder.list());
				}
			}
			catch (MessagingException me) {
				_log.error(
					"Unable to get folder " + jxFolder.getFullName(), me);
			}
		}
	}

	protected Message getMessage(IMAPFolder imapFolder, long remoteMessageId)
		throws MessagingException {

		return imapFolder.getMessageByUID(remoteMessageId);
	}

	protected Message getMessage(
			long folderId, IMAPFolder imapFolder, boolean oldest)
		throws MessagingException, PortalException, SystemException {

		com.liferay.mail.model.Message message = null;

		try {
			message = MessageLocalServiceUtil.getRemoteMessage(
				folderId, oldest);
		}
		catch (NoSuchMessageException nsme) {
			return null;
		}

		long remoteMessageId = message.getRemoteMessageId();

		Message jxMessage = getMessage(imapFolder, remoteMessageId);

		if (jxMessage == null) {
			MessageLocalServiceUtil.deleteMessage(message);

			return getMessage(folderId, imapFolder, oldest);
		}
		else {
			return jxMessage;
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
			IMAPFolder imapFolder, long[] messageIds,
			boolean deleteMissingMessages)
		throws MessagingException, PortalException, SystemException {

		long[] remoteMessageIds = new long[messageIds.length];

		for (int i = 0; i < messageIds.length; i++) {
			com.liferay.mail.model.Message message =
				MessageLocalServiceUtil.getMessage(messageIds[i]);

			remoteMessageIds[i] = message.getRemoteMessageId();
		}

		List<Message> jxMessages = new ArrayList<Message>();

		Message[] jxMessagesArray = imapFolder.getMessagesByUID(
			remoteMessageIds);

		for (int i = 0; i < jxMessagesArray.length; i++) {
			Message jxMessage = jxMessagesArray[i];

			if (jxMessage != null) {
				jxMessages.add(jxMessage);
			}
			else if (deleteMissingMessages && (remoteMessageIds[i] != 0)) {
				MessageLocalServiceUtil.deleteMessage(messageIds[i]);
			}
		}

		return jxMessages;
	}

	protected Part getPart(Part part, String contentPath)
		throws IOException, MessagingException {

		int index = GetterUtil.getInteger(
			StringUtil.split(contentPath.substring(1), StringPool.PERIOD)[0]);

		if (!(part.getContent() instanceof Multipart)) {
			return part;
		}

		Multipart multipart = (Multipart)part.getContent();

		for (int i = 0; i < multipart.getCount(); i++) {
			if (i != index) {
				continue;
			}

			String prefix = String.valueOf(index).concat(StringPool.PERIOD);

			return getPart(
				multipart.getBodyPart(i),
				contentPath.substring(prefix.length()));
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

	protected InternetAddress[] getRecipients(long messageId)
		throws PortalException, SystemException {

		try {
			com.liferay.mail.model.Message message =
				MessageLocalServiceUtil.getMessage(messageId);

			StringBundler sb = new StringBundler(6);

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

	protected InternetAddress[] getRecipients(
			long messageId, RecipientType recipientType)
		throws PortalException, SystemException {

		try {
			com.liferay.mail.model.Message message =
				MessageLocalServiceUtil.getMessage(messageId);

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

	protected IMAPFolder openFolder(long folderId)
		throws MessagingException, PortalException, SystemException {

		return openFolder(getFolder(folderId));
	}

	private static Log _log = LogFactoryUtil.getLog(IMAPAccessor.class);

	private Account _account;
	private IMAPConnection _imapConnection;
	private String _password;
	private User _user;

}