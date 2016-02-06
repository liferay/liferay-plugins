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

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Calvin Keum
 */
public abstract class AlloyNotificationEventHelper {

	public abstract void addUserNotificationEvents(
			HttpServletRequest request, String controllerPath,
			String actionPath, JSONObject payloadJSONObject)
		throws Exception;

	protected void addUserNotificationEvent(
			String portletKey, long userId, int notificationType,
			int deliveryType, Object... attributes)
		throws Exception {

		if ((attributes.length == 0) || ((attributes.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Attributes length is not an even number");
		}

		if (UserNotificationManagerUtil.isDeliver(
				userId, portletKey, 0, notificationType, deliveryType)) {

			JSONObject notificationEventJSONObject =
				JSONFactoryUtil.createJSONObject();

			for (int i = 0; i < attributes.length; i += 2) {
				String key = String.valueOf(attributes[i]);
				String value = String.valueOf(attributes[i + 1]);

				notificationEventJSONObject.put(key, value);
			}

			NotificationEvent notificationEvent =
				NotificationEventFactoryUtil.createNotificationEvent(
					System.currentTimeMillis(), portletKey,
					notificationEventJSONObject);

			notificationEvent.setDeliveryRequired(0);

			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
				userId, notificationEvent);
		}
	}

}