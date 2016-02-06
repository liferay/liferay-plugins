/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * @author Bruno Farache
 */
public class JabberMessageListener implements MessageListener {

	public JabberMessageListener(long companyId, long userId) {
		_companyId = companyId;
		_userId = userId;
	}

	@Override
	public void processMessage(Chat chat, Message message) {
		try {
			String body = message.getBody();

			if (Validator.isNull(body)) {
				return;
			}

			String from = message.getFrom();

			String resource = JabberUtil.getResource(from);

			if (StringUtil.equalsIgnoreCase(
					resource, PortletPropsValues.JABBER_RESOURCE)) {

				return;
			}

			long fromUserId = UserLocalServiceUtil.getUserIdByScreenName(
				_companyId, JabberUtil.getScreenName(from));

			EntryLocalServiceUtil.addEntry(fromUserId, _userId, body);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JabberMessageListener.class);

	private long _companyId;
	private long _userId;

}