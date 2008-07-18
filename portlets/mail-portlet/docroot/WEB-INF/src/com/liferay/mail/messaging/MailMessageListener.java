/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.mail.messaging;

import com.liferay.mail.util.AccountLock;
import com.liferay.mail.util.MailBoxManager;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MailMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailMessageListener implements MessageListener {

	public void receive(Object message) {
		throw new UnsupportedOperationException();
	}

	public void receive(String message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(String message) throws Exception {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject(message);

		String emailAddress = jsonObj.getString("emailAddress");
		long userId = jsonObj.getLong("userId");

		String key = AccountLock.getKey(userId, emailAddress);

		if (AccountLock.acquireLock(key)) {
			try {
				User user = UserLocalServiceUtil.getUser(userId);

				MailBoxManager mailBoxManager =
					new MailBoxManager(user, emailAddress);

				mailBoxManager.synchronizeAccount();
			}
			finally {
				AccountLock.releaseLock(key);
			}
		}
	}

	private static Log _log = LogFactory.getLog(MailMessageListener.class);

}