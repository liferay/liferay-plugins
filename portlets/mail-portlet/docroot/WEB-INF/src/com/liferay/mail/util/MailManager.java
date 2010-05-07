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

package com.liferay.mail.util;

import com.liferay.mail.MailException;
import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.model.Message;
import com.liferay.mail.model.MessageDisplay;
import com.liferay.mail.model.MessagesDisplay;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="MailManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class MailManager {

	public static MailManager getInstance(HttpServletRequest request)
		throws PortalException, SystemException {

		User user = PortalUtil.getUser(request);

		if (user == null) {
			return null;
		}

		return new MailManager(user);
	}

	public MailManager(User user) {
		_user = user;
	}

	public JSONObject addAccount(
			String address, String personalName, String protocol,
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String unencryptedPassword, boolean savePassword,
			String signature, boolean useSignature, String folderPrefix,
			boolean defaultSender)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), protocol);

			Account account = mailbox.addAccount(
				address, personalName, protocol, incomingHostName, incomingPort,
				incomingSecure, outgoingHostName, outgoingPort, outgoingSecure,
				login, unencryptedPassword, savePassword, signature,
				useSignature, folderPrefix, defaultSender);

			mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), account.getAccountId());

			mailbox.synchronize();

			return createJSONResult("success", "account-has-been-created");
		}
		catch (MailException me) {
			if (me.getType() == MailException.ACCOUNT_ALREADY_EXISTS) {
				return createJSONResult(
					"failure",
					"an-account-with-the-same-address-already-exists");
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-add-account");
		}
	}

	public JSONObject addFolder(long accountId, String displayName)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), accountId);

			mailbox.addFolder(displayName);

			return createJSONResult("success", "folder-has-been-created");
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_ALREADY_EXISTS) {
				return createJSONResult(
					"failure",
					"a-folder-with-the-same-name-already-exists");
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-create-folder");
		}
	}

	public JSONObject deleteAccount(long accountId)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), accountId);

			mailbox.deleteAccount();

			return createJSONResult("success", "account-has-been-deleted");
		}
		catch (MailException me) {
			_log.error(me, me);

			return createJSONResult("failure", "unable-to-delete-account");
		}
	}

	public JSONObject deleteAttachment(long attachmentId)
		throws PortalException, SystemException {

		try {
			Attachment attachment = AttachmentLocalServiceUtil.getAttachment(
				attachmentId);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), attachment.getAccountId());

			mailbox.deleteAttachment(attachmentId);

			return createJSONResult("success", "attachment-has-been-deleted");
		}
		catch (MailException me) {
			_log.error(me, me);

			return createJSONResult("failure", "unable-to-delete-attachment");
		}
	}

	public JSONObject deleteFolder(long folderId)
		throws PortalException, SystemException {

		try {
			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), folder.getAccountId());

			mailbox.deleteFolder(folderId);

			return createJSONResult("success", "folder-has-been-deleted");
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_REQUIRED) {
				return createJSONResult(
					"failure",
					"this-is-a-required-folder-and-can-not-be-deleted");
			}
			else if (me.getType() == MailException.FOLDER_DELETE_FAILED) {
				return createJSONResult(
					"failure",
					"the-mail-server-will-not-allow-this-folder-to-be-deleted");
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-delete-folder");
		}
	}

	public JSONObject deleteMessages(long[] messageIds)
		throws PortalException, SystemException {

		try {
			if (messageIds.length > 0) {
				return createJSONResult("failure", "no-messages-selected");
			}

			Message message = MessageLocalServiceUtil.getMessage(
				messageIds[0]);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), message.getAccountId());

			mailbox.deleteMessages(message.getFolderId(), messageIds);

			Account account = AccountLocalServiceUtil.getAccount(
				message.getAccountId());

			if (account.getDraftFolderId() == message.getFolderId()) {
				return createJSONResult(
					"success", "drafts-have-been-discarded");
			}
			else {
				return createJSONResult(
					"success", "messages-have-been-deleted");
			}
		}
		catch (MailException me) {
			_log.error(me, me);

			return createJSONResult("failure", "unable-to-delete-messages");
		}
	}

	public JSONObject flagMessages(long[] messageIds, int flag, boolean value)
		throws PortalException, SystemException {

		try {
			if (messageIds.length > 0) {
				return createJSONResult("failure", "no-messages-selected");
			}

			Message message = MessageLocalServiceUtil.getMessage(
				messageIds[0]);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), message.getAccountId());

			mailbox.updateFlags(
				message.getFolderId(), messageIds, flag, value);

			return createJSONResult("success", "messages-have-been-flagged");
		}
		catch (MailException me) {
			if (me.getType() == MailException.MESSAGE_INVALID_FLAG) {
				return createJSONResult(
					"failure", "this-flag-is-not-supported");
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-flag-messages");
		}
	}

	public List<Account> getAccounts() throws SystemException {
		return AccountLocalServiceUtil.getAccounts(_user.getUserId());
	}

	public long getAccountUnreadMessagesCount(long accountId)
		throws SystemException {

		return MessageLocalServiceUtil.getAccountUnreadMessagesCount(accountId);
	}

	public InputStream getAttachment(long attachmentId)
		throws IOException, PortalException, SystemException {

		Attachment attachment = AttachmentLocalServiceUtil.getAttachment(
			attachmentId);

		Mailbox mailbox = MailboxFactoryUtil.getMailbox(
			_user.getUserId(), attachment.getAccountId());

		return mailbox.getAttachment(attachmentId);
	}

	public List<Folder> getFolders(long accountId) throws SystemException {
		return  FolderLocalServiceUtil.getFolders(accountId);
	}

	public long getFolderUnreadMessagesCount(long folderId)
		throws SystemException {

		return MessageLocalServiceUtil.getFolderUnreadMessagesCount(folderId);
	}

	public MessageDisplay getMessageDisplay(
			long folderId, String keywords, int messageNumber,
			String orderByField, String orderByType, boolean synchronize)
		throws PortalException, SystemException {

		if (folderId <= 0) {
			return null;
		}

		MessagesDisplay messagesDisplay = getMessagesDisplay(
			folderId, messageNumber, 1, orderByField, orderByType, keywords);

		List<Message> messages = messagesDisplay.getMessages();

		Message message = messages.get(0);

		if (synchronize && Validator.isNull(message.getBody())) {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), message.getAccountId());

			mailbox.synchronizeMessage(message.getMessageId());

			return getMessageDisplay(
				folderId, keywords, messageNumber, orderByField, orderByType,
				synchronize);
		}

		List<Attachment> attachments =
			AttachmentLocalServiceUtil.getAttachments(message.getMessageId());

		return new MessageDisplay(
			message, attachments, messagesDisplay.getMessageCount());
	}

	public MessagesDisplay getMessagesDisplay(
			long folderId, int pageNumber, int messagesPerPage,
			String orderByField, String orderByType, String keywords)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		if (folderId != 0) {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), folder.getAccountId());

			if ((pageNumber == 1) &&
					orderByField.equals(MailConstants.ORDER_BY_SENT_DATE) &&
					orderByType.equals("desc") && Validator.isNull(keywords)) {

				sendSynchronizePageMessage(
					folderId, pageNumber, messagesPerPage);
			}

			return mailbox.getMessagesDisplay(
				folderId, keywords, pageNumber, messagesPerPage, orderByField,
				orderByType);
		}

		return new MessagesDisplay(
			new ArrayList<Message>(), pageNumber, messagesPerPage, 0);
	}

	public JSONObject moveMessages(long folderId, long[] messageIds)
		throws PortalException, SystemException {

		try {
			if (messageIds.length == 0) {
				return createJSONResult("failure", "no-messages-selected");
			}

			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			Account account = AccountLocalServiceUtil.getAccount(
				folder.getAccountId());

			if ((account.getDraftFolderId() == folderId) ||
				(account.getSentFolderId() == folderId)) {

				throw new MailException(
					MailException.FOLDER_INVALID_DESTINATION);
			}

			Message message = MessageLocalServiceUtil.getMessage(
				messageIds[0]);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), message.getAccountId());

			mailbox.moveMessages(folderId, messageIds);

			return createJSONResult("success", "messages-have-been-moved");
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_INVALID_DESTINATION) {
				return createJSONResult(
					"failure", "cant-move-messages-to-this-folder");
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-move-messages");
		}
	}

	public JSONObject renameFolder(long folderId, String displayName)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), folder.getAccountId());

			mailbox.renameFolder(folderId, displayName);

			return createJSONResult("success", "folder-renamed-successfully");
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_RENAME_FAILED) {
				return createJSONResult(
					"failure", "cant-move-messages-to-this-folder");
			}
			else if (me.getType() == MailException.FOLDER_ALREADY_EXISTS) {
				return createJSONResult(
					"failure", "a-folder-with-the-same-name-already-exists");
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-rename-folder");
		}
	}

	public JSONObject saveDraft(
			long accountId, long messageId, String to, String cc, String bcc,
			String subject, String body, List<MailFile> mailFiles)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), accountId);

			Message message = mailbox.saveDraft(
				accountId, messageId, to, cc, bcc, subject, body, mailFiles);

			return createJSONResult(
				"success", "saved successfully",
				String.valueOf(message.getMessageId()));
		}
		catch (MailException me) {
			if (me.getType() == MailException.MESSAGE_HAS_NO_RECIPIENTS) {
				return createJSONResult(
					"failure", "please-specify-at-least-one-recipient");
			}
			else if (me.getType() == MailException.MESSAGE_INVALID_ADDRESS) {
				return createJSONResult(
					"failure",
					"please-make-sure-the-following-address-is-properly" +
						"-formatted",
					me.getValue());
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-save-draft");
		}
	}

	public JSONObject sendMessage(
			long accountId, long messageId, String to, String cc, String bcc,
			String subject, String body, List<MailFile> mailFiles)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), accountId);

			Message message = mailbox.saveDraft(
				accountId, messageId, to, cc, bcc, subject, body, mailFiles);

			mailbox.sendMessage(accountId, message.getMessageId());

			return createJSONResult("success", "sent-successfully");
		}
		catch (MailException me) {
			if (me.getType() == MailException.MESSAGE_HAS_NO_RECIPIENTS) {
				return createJSONResult(
					"failure", "please-specify-at-least-one-recipient");
			}
			else if (me.getType() == MailException.MESSAGE_INVALID_ADDRESS) {
				return createJSONResult(
					"failure",
					"please-make-sure-the-following-address-is-properly" +
						"-formatted",
					me.getValue());
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-send-message");
		}
	}

	public void sendSynchronizeAccountMessage(long accountId)
		throws PortalException, SystemException {

		sendSynchronizeMessage(accountId, 0, 0, 0, 0);
	}

	public void sendSynchronizeFolderMessage(long folderId)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		sendSynchronizeMessage(folder.getAccountId(), folderId, 0, 0, 0);
	}

	public void sendSynchronizeMessageMessage(long messageId)
		throws PortalException, SystemException {

		Message message = MessageLocalServiceUtil.getMessage(messageId);

		sendSynchronizeMessage(message.getAccountId(), 0, messageId, 0, 0);
	}

	public void sendSynchronizePageMessage(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException, SystemException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		sendSynchronizeMessage(
			folder.getAccountId(), folderId, 0, pageNumber, messagesPerPage);
	}

	public JSONObject updateAccount(
			long accountId, String personalName, String unencryptedPassword,
			boolean savePassword, String signature, boolean useSignature,
			String folderPrefix, boolean defaultSender)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), accountId);

			mailbox.updateAccount(
				accountId, personalName, unencryptedPassword, savePassword,
				signature, useSignature, folderPrefix, defaultSender);

			return createJSONResult("success", "account-has-been-updated");
		}
		catch (MailException me) {
			if (me.getType() == MailException.MESSAGE_HAS_NO_RECIPIENTS) {
				return createJSONResult(
					"failure", "please-specify-at-least-one-recipient");
			}
			else if (me.getType() == MailException.MESSAGE_INVALID_ADDRESS) {
				return createJSONResult(
					"failure",
					"please-make-sure-the-following-address-is-properly" +
						"-formatted",
					me.getValue());
			}

			_log.error(me, me);

			return createJSONResult("failure", "unable-to-update-account");
		}
	}

	protected JSONObject createJSONResult(String status, String message) {
		return createJSONResult(status, message, null);
	}

	protected JSONObject createJSONResult(
		String status, String message, String value) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("status", status);
		jsonObject.put("message", LanguageUtil.get(_user.getLocale(), message));

		if (Validator.isNotNull(value)) {
			jsonObject.put("value", value);
		}

		return jsonObject;
	}

	protected void sendSynchronizeMessage(
			long accountId, long folderId, long messageId, int pageNumber,
			int messagesPerPage)
		throws PortalException, SystemException {

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("userId", _user.getUserId());
		jsonObj.put("accountId", accountId);
		jsonObj.put("folderId", folderId);
		jsonObj.put("messageId", messageId);
		jsonObj.put("pageNumber", pageNumber);
		jsonObj.put("messagesPerPage", messagesPerPage);
		jsonObj.put("password", account.getPasswordDecrypted());

		MessageBusUtil.sendMessage("liferay/mail_synchronization", jsonObj);
	}

	private static Log _log = LogFactoryUtil.getLog(MailManager.class);

	private User _user;

}