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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsKeys;
import com.liferay.pushnotifications.util.PortletPropsValues;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

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

	@Override
	public synchronized void reset() {
		_apnsService = null;
	}

	@Override
	public void send(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws Exception {

		ApnsService apnsService = getApnsService();

		if (apnsService == null) {
			return;
		}

		String payload = buildPayload(payloadJSONObject);

		apnsService.push(tokens, payload);
	}

	protected String buildPayload(JSONObject payloadJSONObject) {
		PayloadBuilder builder = PayloadBuilder.newPayload();

		String body = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_BODY);

		if (Validator.isNotNull(body)) {
			builder.alertBody(body);
		}

		payloadJSONObject.remove(PushNotificationsConstants.KEY_BODY);

		builder.customField(
			PushNotificationsConstants.KEY_PAYLOAD,
			payloadJSONObject.toString());

		return builder.build();
	}

	protected synchronized ApnsService getApnsService() throws Exception {
		if (_apnsService == null) {
			ApnsServiceBuilder appleServiceBuilder = APNS.newService();

			String path = PrefsPropsUtil.getString(
				PortletPropsKeys.APPLE_CERTIFICATE_PATH,
				PortletPropsValues.APPLE_CERTIFICATE_PATH);

			if (Validator.isNull(path)) {
				throw new PushNotificationsException(
					"The property \"apple.certificate.path\" is not set in " +
						"portlet.properties");
			}

			String password = PrefsPropsUtil.getString(
				PortletPropsKeys.APPLE_CERTIFICATE_PASSWORD,
				PortletPropsValues.APPLE_CERTIFICATE_PASSWORD);

			if (Validator.isNull(password)) {
				throw new PushNotificationsException(
					"The property \"apple.certificate.password\" is not set " +
						"in portlet.properties");
			}

			appleServiceBuilder.withCert(path, password);

			boolean sandbox = PrefsPropsUtil.getBoolean(
				PortletPropsKeys.APPLE_SANDBOX,
				PortletPropsValues.APPLE_SANDBOX);

			if (sandbox) {
				appleServiceBuilder.withSandboxDestination();
			}
			else {
				appleServiceBuilder.withProductionDestination();
			}

			_apnsService = appleServiceBuilder.build();
		}

		return _apnsService;
	}

	private ApnsService _apnsService;

}