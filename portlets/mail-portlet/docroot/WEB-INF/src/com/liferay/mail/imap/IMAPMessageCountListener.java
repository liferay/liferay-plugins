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
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

import com.sun.mail.imap.IMAPFolder;

import javax.mail.Message;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

/**
 * <a href="IMAPMessageCountListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class IMAPMessageCountListener implements MessageCountListener {

	public IMAPMessageCountListener(User user, Account account) {
		_account = account;
		_imapUtil = new IMAPUtil(user, account);
	}

	public void messagesAdded(MessageCountEvent messageCountEvent) {
		Message[] messages = messageCountEvent.getMessages();

		IMAPFolder imapFolder = null;

		try {
			imapFolder = _imapUtil.openFolder(
				(IMAPFolder)messages[0].getFolder());

			Folder localFolder = FolderLocalServiceUtil.getFolder(
				_account.getAccountId(), imapFolder.getFullName());

			_imapUtil.storeEnvelopes(
				localFolder.getFolderId(), imapFolder, messages);
		}
		catch (Exception e) {
			_log.error("Unable to add messages", e);
		}
		finally {
			try {
				_imapUtil.closeFolder(imapFolder, false);
			}
			catch (MailException me) {
				_log.error(me, me);
			}
		}
	}

	public void messagesRemoved(MessageCountEvent messageCountEvent) {
		Message[] messages = messageCountEvent.getMessages();

		IMAPFolder imapFolder = null;

		try {
			imapFolder = _imapUtil.openFolder(
				(IMAPFolder)messages[0].getFolder());

			Folder localFolder = FolderLocalServiceUtil.getFolder(
				_account.getAccountId(), imapFolder.getFullName());

			long[] messageIds = _imapUtil.getMessageUIDs(imapFolder, messages);

			for (long messageId : messageIds) {
				com.liferay.mail.model.Message localMessage =
					MessageLocalServiceUtil.getMessage(
						localFolder.getFolderId(), messageId);

				MessageLocalServiceUtil.deleteMessage(
					localMessage.getMessageId());
			}
		}
		catch (Exception e) {
			_log.error("Unable to delete messages", e);
		}
		finally {
			try {
				_imapUtil.closeFolder(imapFolder, false);
			}
			catch (MailException me) {
				_log.error(me);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		IMAPMessageCountListener.class);

	private Account _account;
	private IMAPUtil _imapUtil;

}