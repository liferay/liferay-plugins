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

import com.liferay.mail.util.AccountLock;
import com.liferay.mail.util.MailBoxManager;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * <a href="MailMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailMessageListener implements MessageListener {

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

		String emailAddress = jsonObj.getString("emailAddress");
		long userId = jsonObj.getLong("userId");

		String key = AccountLock.getKey(userId, emailAddress);

		if (AccountLock.acquireLock(key)) {
			try {
				User user = UserLocalServiceUtil.getUser(userId);

				MailBoxManager mailBoxManager = new MailBoxManager(
					user, emailAddress);

				mailBoxManager.synchronizeAccount();
			}
			finally {
				AccountLock.releaseLock(key);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MailMessageListener.class);

}