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

package com.liferay.pushnotifications.sender.android;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.Sender;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsKeys;
import com.liferay.pushnotifications.util.PortletPropsValues;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.List;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class AndroidPushNotificationsSender implements PushNotificationsSender {

	@Override
	public synchronized void reset() {
		_sender = null;
	}

	@Override
	public void send(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws Exception {

		Sender sender = getSender();

		if (sender == null) {
			return;
		}

		Message message = buildMessage(payloadJSONObject);

		int retries = PrefsPropsUtil.getInteger(
			PortletPropsKeys.ANDROID_RETRIES,
			PortletPropsValues.ANDROID_RETRIES);

		sender.send(message, tokens, retries);
	}

	protected Message buildMessage(JSONObject payloadJSONObject) {
		Builder builder = new Builder();

		builder.addData(
			PushNotificationsConstants.KEY_PAYLOAD,
			payloadJSONObject.toString());

		return builder.build();
	}

	protected synchronized Sender getSender() throws Exception {
		if (_sender == null) {
			String key = PrefsPropsUtil.getString(
				PortletPropsKeys.ANDROID_API_KEY,
				PortletPropsValues.ANDROID_API_KEY);

			if (Validator.isNull(key)) {
				throw new PushNotificationsException(
					"The property \"android.api.key\" is not set in " +
						"portlet.properties");
			}

			_sender = new Sender(key);
		}

		return _sender;
	}

	private Sender _sender;

}