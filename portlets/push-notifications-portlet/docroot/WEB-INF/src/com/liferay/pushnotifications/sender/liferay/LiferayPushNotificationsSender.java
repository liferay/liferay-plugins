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

package com.liferay.pushnotifications.sender.liferay;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsValues;

import java.util.List;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * @author Bruno Farache
 */
public class LiferayPushNotificationsSender implements PushNotificationsSender {

	@Override
	public void reset() {
	}

	@Override
	public void send(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws Exception {

		HttpRequest httpRequest = HttpRequest.post(
			PortletPropsValues.LIFERAY_SERVER);

		httpRequest.basicAuthentication(
			PortletPropsValues.LIFERAY_USERNAME,
			PortletPropsValues.LIFERAY_PASSWORD);

		httpRequest.path(
			"/api/jsonws/push-notifications-portlet.pushnotificationsdevice" +
				"/send-push-notification");

		httpRequest.timeout(PortletPropsValues.LIFERAY_TIMEOUT);

		httpRequest.form("platform", platform);

		JSONArray tokensJSONArray = JSONFactoryUtil.createJSONArray();

		for (String token : tokens) {
			tokensJSONArray.put(token);
		}

		httpRequest.form("tokens", tokensJSONArray.toString());
		httpRequest.form("payload", payloadJSONObject.toString());

		HttpResponse httpResponse = httpRequest.send();

		if (httpResponse.statusCode() != 200) {
			throw new PushNotificationsException(
				"Unable to send push notification to Liferay Push " +
					httpResponse.bodyText());
		}
	}

}