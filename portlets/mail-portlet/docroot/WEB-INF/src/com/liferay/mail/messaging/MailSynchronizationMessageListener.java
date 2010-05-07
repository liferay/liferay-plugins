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

package com.liferay.mail.messaging;

import com.liferay.mail.NoSuchAccountException;
import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.util.AccountLock;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.StringPool;

/**
 * <a href="MailSynchronizationMessageListener.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Scott Lee
 */
public class MailSynchronizationMessageListener implements MessageListener {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message) throws Exception {
		JSONObject jsonObj = (JSONObject)message.getPayload();

		long userId = jsonObj.getLong("userId");
		long accountId = jsonObj.getLong("accountId");
		long folderId = jsonObj.getLong("folderId");
		long messageId = jsonObj.getLong("messageId");
		int pageNumber = jsonObj.getInt("pageNumber");
		int messagesPerPage = jsonObj.getInt("messagesPerPage");
		String password = jsonObj.getString("password");

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Starting synch for accountId " + accountId + " folderId " +
					folderId + " and messageId " + messageId);
		}

		String key = AccountLock.getKey(userId, accountId, folderId, messageId);

		try {
			if (!password.equals(StringPool.BLANK) &&
					AccountLock.acquireLock(key)) {

				Mailbox mailbox = MailboxFactoryUtil.getMailbox(
					userId, accountId);

				if (messageId != 0) {
					mailbox.synchronizeMessage(messageId);
				}
				else if (folderId != 0) {
					if (pageNumber != 0) {
						mailbox.synchronizePage(
							folderId, pageNumber, messagesPerPage);
					}
					else {
						mailbox.synchronizeFolder(folderId);
					}
				}
				else {
					mailbox.synchronize();
				}
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to acquire synch lock for accountId " +
							accountId + " and folderId " + folderId +
								" and messageId " + messageId);
				}
			}
		}
		catch (NoSuchAccountException nsae) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping syncronization of accountId " + accountId);
			}
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Ending synch for accountId " + accountId + " folderId " +
						folderId + " and messageId " + messageId);
			}

			AccountLock.releaseLock(key);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
			MailSynchronizationMessageListener.class);

}