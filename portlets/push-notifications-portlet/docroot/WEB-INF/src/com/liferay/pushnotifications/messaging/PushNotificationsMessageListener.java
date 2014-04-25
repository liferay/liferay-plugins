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

package com.liferay.pushnotifications.messaging;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.service.PushNotificationsDeviceLocalServiceUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class PushNotificationsMessageListener implements MessageListener {

	public Map<String, PushNotificationsSender> getPushNotificationsSenders() {
		return _pushNotificationsSenders;
	}

	@Override
	public void receive(Message message) {
		JSONObject jsonObject = (JSONObject)message.getPayload();

		long userId = jsonObject.getLong("userId");

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received message " + jsonObject + " for user " + userId);
		}

		try {
			for (Map.Entry<String, PushNotificationsSender> entry :
					_pushNotificationsSenders.entrySet()) {

				String platform = entry.getKey();

				List<String> tokens =
					PushNotificationsDeviceLocalServiceUtil.getTokens(
						userId, platform, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				if (tokens.isEmpty()) {
					continue;
				}

				PushNotificationsSender pushNotificationsSender =
					entry.getValue();

				pushNotificationsSender.send(tokens, jsonObject);
			}
		}
		catch (Exception e) {
			_log.error("Unable to send notification", e);
		}
	}

	public void setPushNotificationsSenders(
		Map<String, PushNotificationsSender> pushNotificationSenders) {

		_pushNotificationsSenders = pushNotificationSenders;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsMessageListener.class);

	private Map<String, PushNotificationsSender> _pushNotificationsSenders;

}