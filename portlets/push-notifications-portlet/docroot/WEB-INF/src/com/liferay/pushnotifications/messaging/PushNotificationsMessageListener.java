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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.pushnotifications.service.PushNotificationsDeviceLocalServiceUtil;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class PushNotificationsMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		JSONObject payloadJSONObject = (JSONObject)message.getPayload();

		JSONArray toUserIdsJSONArray = payloadJSONObject.getJSONArray(
			PushNotificationsConstants.KEY_TO_USER_IDS);

		long[] toUserIds = new long[toUserIdsJSONArray.length()];

		for (int i = 0; i < toUserIdsJSONArray.length(); i++) {
			toUserIds[i] = toUserIdsJSONArray.getLong(i);
		}

		payloadJSONObject.remove(PushNotificationsConstants.KEY_TO_USER_IDS);

		try {
			PushNotificationsDeviceLocalServiceUtil.sendPushNotification(
				toUserIds, payloadJSONObject);
		}
		catch (Exception e) {
			_log.error("Unable to send notification", e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsMessageListener.class);

}