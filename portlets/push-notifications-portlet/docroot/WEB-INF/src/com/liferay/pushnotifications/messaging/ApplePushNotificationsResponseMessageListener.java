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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.pushnotifications.sender.Response;
import com.liferay.pushnotifications.sender.apple.AppleResponse;
import com.liferay.pushnotifications.service.PushNotificationsDeviceLocalServiceUtil;

import com.notnoop.apns.DeliveryError;

/**
 * @author Bruno Farache
 */
public class ApplePushNotificationsResponseMessageListener
	implements MessageListener {

	@Override
	public void receive(Message message) {
		Response response = (Response)message.getPayload();

		if (response.isSucceeded() || !(response instanceof AppleResponse)) {
			return;
		}

		AppleResponse appleResponse = (AppleResponse)response;

		String invalidTokenErrorName = DeliveryError.INVALID_TOKEN.name();
		String status = appleResponse.getStatus();

		if (invalidTokenErrorName.equals(status)) {
			String token = appleResponse.getToken();

			try {
				PushNotificationsDeviceLocalServiceUtil.
					deletePushNotificationsDevice(token);

				if (_log.isWarnEnabled()) {
					_log.warn("Token " + token + " is invalid and was deleted");
				}
			}
			catch (Exception e) {
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ApplePushNotificationsResponseMessageListener.class);

}