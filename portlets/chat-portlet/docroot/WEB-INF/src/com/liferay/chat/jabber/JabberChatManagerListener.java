/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.jabber;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;

/**
 * @author Bruno Farache
 */
public class JabberChatManagerListener implements ChatManagerListener {

	public JabberChatManagerListener(long companyId, long userId) {
		_companyId = companyId;
		_userId = userId;
	}

	public void chatCreated(Chat chat, boolean createdLocally) {
		if (!createdLocally) {
			MessageListener messageListener = new JabberMessageListener(
				_companyId, _userId);

			chat.addMessageListener(messageListener);
		}
	}

	private long _companyId;
	private long _userId;

}