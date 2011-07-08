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

package com.liferay.mail.vaadin;

import com.liferay.mail.MailException;
import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.mailbox.PasswordRetriever;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.mail.util.MailConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Henri Sara
 */
public class MessageUtil {

	private static Log _log = LogFactoryUtil.getLog(MessageUtil.class);

	public static void moveMessagesTo(List<Message> messages,
			Folder folder) throws PortalException, SystemException {

		if (messages == null || messages.isEmpty()) {
			// nothing to move
			return;
		}

		Account account = AccountLocalServiceUtil.getAccount(folder
				.getAccountId());
		if ((account.getDraftFolderId() == folder.getFolderId())
				|| (account.getSentFolderId() == folder.getFolderId())) {

			throw new MailException(MailException.FOLDER_INVALID_DESTINATION);
		}

		long[] messageIds = new long[messages.size()];
		for (int i = 0; i < messages.size(); i++) {
			messageIds[i] = messages.get(i).getMessageId();
		}

		Message message = messages.get(0);
		Mailbox mailbox = getMailbox(message.getAccountId());

		mailbox.moveMessages(folder.getFolderId(), messageIds);

		Controller.get().notifyMessagesAddedOrRemovedListeners();
	}

	public static boolean isSeen(Message m) {

		return isFlagged(m, MailConstants.FLAG_SEEN);
	}

	public static boolean isImportant(Message m) {

		return isFlagged(m, MailConstants.FLAG_FLAGGED);
	}

	private static boolean isFlagged(Message m, int flag) {
		for (String flagStr : m.getFlags().split(",")) {
			if (flagStr.equals(String.valueOf(flag))) {
				return true;
			}
		}
		return false;
	}

	public static void deleteMessages(List<Message> messages)
		throws PortalException, SystemException {

		if (messages == null || messages.isEmpty()) {
			// nothing to delete
			return;
		}

		try {
			Message message = messages.get(0);
			Mailbox mailbox = getMailbox(message.getAccountId());

			long[] messageIds = new long[messages.size()];
			for (int i=0; i<messages.size(); ++i) {
				messageIds[i] = messages.get(i).getMessageId();
			}

			// move to thrash if not in drafts or thrash
			mailbox.deleteMessages(message.getFolderId(), messageIds);
		} catch (MailException me) {
			_log.error(me);
			// rethrow for the caller
			throw me;
		}

		Controller.get().notifyMessagesAddedOrRemovedListeners();
	}

	public static String createReplyMessage(Message originalMessage) {

		String newMessageBody = "<p>&nbsp;</p><br /><br />";
		newMessageBody += Lang.get("on-x-x-wrote", formatDate(originalMessage
				.getSentDate(), Controller.get().getUserLocale()),
				originalMessage.getSender());
		newMessageBody += "<br /><br />";
		newMessageBody += originalMessage.getBody();

		return newMessageBody;
	}

	public static String createForwardMessage(Message originalMessage) {

		String newMessageBody =
			"<p>&nbsp;</p><br /><br />---------- " +
				Lang.get("forwarded-message") + " ----------<br />";
		newMessageBody +=
			Lang.get("from") + ": " + originalMessage.getSender() + "<br />";
		newMessageBody += Lang.get("date")
				+ ": "
				+ formatDate(originalMessage.getSentDate(), Controller.get()
						.getUserLocale()) + "<br />";
		newMessageBody +=
			Lang.get("subject") + ": " + originalMessage.getSubject() +
				"<br />";
		newMessageBody +=
			Lang.get("to") + ": " + originalMessage.getTo() +
				"<br />";

		String cc = originalMessage.getCc();
		if (cc != null && !cc.equals("")) {
			newMessageBody +=
				Lang.get("cc") + ": " + originalMessage.getCc() +
					"<br />";
		}

		newMessageBody += "<br/>";
		newMessageBody += originalMessage.getBody();

		return newMessageBody;
	}

	public static Account getAccountForMessage(Message message)
		throws PortalException, SystemException {

		return getAccount(message.getAccountId());
	}

	public static Account getAccount(long accountId)
		throws PortalException, SystemException {

		return AccountLocalServiceUtil.getAccount(accountId);
	}

	public static Message getFullMessage(Message message, boolean forceRefresh)
			throws PortalException, SystemException {

		if (message == null) {
			return message;
		}
		// if the message has a body, it has already been retrieved from the server
		if (forceRefresh || message.getBody() == null || "".equals(message.getBody())) {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(Controller.get()
					.getUser().getUserId(), message.getAccountId(), Controller
					.get().getPasswordRetriever().getPassword(
							message.getAccountId()));
			mailbox.synchronizeMessage(message.getMessageId());

			return MessageLocalServiceUtil.getMessage(message.getMessageId());
		} else {
			// could perhaps even skip this sometimes if the message is already loaded
			return MessageLocalServiceUtil.getMessage(message.getMessageId());
		}
	}

	public static void markMessageRead(Message message, boolean read)
		throws PortalException, SystemException {

		MessageLocalServiceUtil.updateFlag(message.getMessageId(),
				MailConstants.FLAG_SEEN, read);

		// TODO reload message??
		Controller.get().notifyMessageModifyListeners(message.getMessageId());
	}

	public static void flagMessageAsImportant(Message message, boolean important)
		throws PortalException, SystemException {

		MessageLocalServiceUtil.updateFlag(message.getMessageId(),
				MailConstants.FLAG_FLAGGED, important);

		// TODO reload message??
		Controller.get().notifyMessageModifyListeners(message.getMessageId());
	}

	public static void setAnswered(Message message, boolean answered)
		throws PortalException, SystemException {

		MessageLocalServiceUtil.updateFlag(message.getMessageId(),
				MailConstants.FLAG_ANSWERED, answered);

		// TODO reload message??
		Controller.get().notifyMessageModifyListeners(message.getMessageId());

	}

	// Sending
	// if sendMessage is false, just save draft
	public static Message saveOrSendMessage(Account account, String to,
			String cc, String bcc, String subject, String body,
			List<MailFile> attachments, boolean sendMessage, long draftMessageId)
			throws PortalException, SystemException {

		long accountId = account.getAccountId();

		if (!isAccountOwner(account)) {
			// error handling
			throw new MailException(Lang.get("unable-to-access-account"));
		}

		// Send or save

		if (sendMessage) {
			if ((to == null || "".equals(to.trim()))
					&& (cc == null || "".equals(cc.trim()))
					&& (bcc == null || "".equals(bcc.trim()))) {
				throw new MailException(MailException.MESSAGE_HAS_NO_RECIPIENTS);
			}
		}

		Mailbox mailbox = getMailbox(accountId);

		Message message = mailbox.saveDraft(accountId, draftMessageId, to,
				cc, bcc, subject, body, attachments);

		if (sendMessage) {
			mailbox.sendMessage(accountId, message.getMessageId());
		}

		return message;
	}

	protected static boolean isAccountOwner(Account account) {

		return (account != null)
				&& (account.getUserId() == Controller.get().getUser()
						.getUserId());
	}

	/**
	 * Returns the {@link Mailbox} for an {@link Account}.
	 *
	 * @return {@link Mailbox}
	 */
	public static Mailbox getMailbox(long accountId) throws PortalException,
			SystemException {
		User user = Controller.get().getUser();
		Mailbox mailbox = MailboxFactoryUtil.getMailbox(user.getUserId(),
				accountId, getPassword(accountId));
		return mailbox;
	}

	private static String getPassword(long accountId) throws PortalException,
			SystemException {
		PasswordRetriever passwordRetriever = Controller.get().getPasswordRetriever();
		if (passwordRetriever != null) {
			return passwordRetriever.getPassword(accountId);
		} else {
			_log.debug("Password retriever not available");
			return "";
		}
	}

	// formatting from old FormatUtil, may be available elsewhere as well

	public static String formatSize(double size, Locale locale) {
		NumberFormat numberFormat = NumberFormat.getInstance(locale);

		return numberFormat.format((int)Math.ceil(size / 1000)) + " KB";
	}

	private static final String DATE_UNAVAILABLE = "----";

	public static String formatDate(Date date, Locale locale) {

		if (Validator.isNotNull(date)) {
			Calendar now = Calendar.getInstance();
			Calendar date2 = Calendar.getInstance();

			date2.setTime(date);

			DateFormat df = null;

			if (now.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
				if ((now.get(Calendar.MONTH) == date2.get(Calendar.MONTH))
						&& (now.get(Calendar.DATE) == date2.get(Calendar.DATE))) {

					df = DateFormat.getTimeInstance(DateFormat.SHORT, locale);

					return df.format(date);
				} else {
					df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

					try {
						SimpleDateFormat sdf = (SimpleDateFormat) df;

						sdf.applyPattern(sdf.toPattern().replaceAll("y", ""));
						sdf.applyPattern(sdf.toPattern().replaceAll(", ", ""));

						return sdf.format(date);
					} catch (ClassCastException e) {
						return df.format(date);
					}
				}
			} else {
				df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

				return df.format(date);
			}
		}

		return DATE_UNAVAILABLE;
	}

}