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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsValues;

import java.util.List;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class AndroidPushNotificationsSender implements PushNotificationsSender {

	public AndroidPushNotificationsSender() {
		String key = PortletPropsValues.ANDROID_API_KEY;

		if (Validator.isNull(key)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"The property \"android.api.key\" is not set in " +
						"portlet.properties");
			}

			return;
		}

		_sender = new Sender(key);
	}

	@Override
	public void send(List<String> tokens, JSONObject jsonObject)
		throws Exception {

		if (_sender == null) {
			return;
		}

		Message message = buildMessage(jsonObject);

		_sender.send(message, tokens, PortletPropsValues.ANDROID_RETRIES);
	}

	protected Message buildMessage(JSONObject jsonObject) {
		Builder builder = new Builder();

		String message = jsonObject.getString("message");

		if (message != null) {
			builder.addData("data", message);
		}

		return builder.build();
	}

	private static Log _log = LogFactoryUtil.getLog(
		AndroidPushNotificationsSender.class);

	private Sender _sender;

}