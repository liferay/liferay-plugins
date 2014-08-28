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
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Calvin Keum
 */
public class AlloyNotificationProcessor {

	public void process(
			HttpServletRequest request, ThemeDisplay themeDisplay,
			String controller, String actionId)
		throws Exception {
	}

	protected void sendNotification(
			String portletKey, long userId, int notificationType,
			int deliveryType, long classPK, String title, String body)
		throws Exception {

		if (UserNotificationManagerUtil.isDeliver(
				userId, portletKey, 0, notificationType, deliveryType)) {

			JSONObject notificationEventJSONObject =
				JSONFactoryUtil.createJSONObject();

			notificationEventJSONObject.put("classPK", classPK);
			notificationEventJSONObject.put("userId", userId);
			notificationEventJSONObject.put("title", title);
			notificationEventJSONObject.put("body", body);

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