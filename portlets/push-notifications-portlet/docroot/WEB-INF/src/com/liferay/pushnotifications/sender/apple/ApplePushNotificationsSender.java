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

package com.liferay.pushnotifications.sender.apple;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.service.PushNotificationsDeviceLocalServiceUtil;
import com.liferay.pushnotifications.util.PortletPropsValues;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;
import com.notnoop.apns.PayloadBuilder;

import java.util.List;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class ApplePushNotificationsSender implements PushNotificationsSender {

	public ApplePushNotificationsSender() {
		ApnsServiceBuilder appleServiceBuilder = APNS.newService();

		appleServiceBuilder.withCert(
			PortletPropsValues.APPLE_CERTIFICATE_PATH,
			PortletPropsValues.APPLE_CERTIFICATE_PASSWORD);

		if (PortletPropsValues.APPLE_SANDBOX) {
			appleServiceBuilder.withSandboxDestination();
		}

		_apnsService = appleServiceBuilder.build();
	}

	@Override
	public void send(JSONObject jsonObject) {
		String payload = null;

		try {
			long userId = jsonObject.getLong("userId");

			List<String> tokens =
				PushNotificationsDeviceLocalServiceUtil.getTokens(
					userId, "ios", QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (tokens.isEmpty()) {
				return;
			}

			payload = buildPayload(jsonObject);

			_apnsService.push(tokens, payload);
		}
		catch (Exception e) {
			_log.error("Could not send notification " + payload, e);
		}
	}

	protected String buildPayload(JSONObject jsonObject) {
		PayloadBuilder payload = PayloadBuilder.newPayload();

		String body = jsonObject.getString("entryTitle");

		if (body != null) {
			payload.alertBody(body);
		}

		return payload.build();
	}

	private static Log _log = LogFactoryUtil.getLog(
		ApplePushNotificationsSender.class);

	private ApnsService _apnsService;

}