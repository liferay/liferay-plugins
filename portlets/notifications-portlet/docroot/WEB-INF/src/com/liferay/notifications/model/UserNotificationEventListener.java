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

package com.liferay.notifications.model;

import com.liferay.notifications.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.UserNotificationEvent;

/**
 * @author Calvin Keum
 */
public class UserNotificationEventListener
	extends BaseModelListener<UserNotificationEvent> {

	@Override
	public void onAfterCreate(UserNotificationEvent userNotificationEvent) {
		try {
			boolean actionRequired = isActionRequired(
				userNotificationEvent.getPayload());

			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId(),
				userNotificationEvent.getUserId(),
				userNotificationEvent.getTimestamp(), actionRequired,
				userNotificationEvent.getDelivered(),
				userNotificationEvent.getArchived());
		}
		catch (Exception e) {
		}
	};

	@Override
	public void onAfterRemove(UserNotificationEvent userNotificationEvent) {
		System.out.println("onAfterRemove");

		try {
			com.liferay.notifications.model.UserNotificationEvent
				notificationEvent =
					UserNotificationEventLocalServiceUtil.
						fetchNotificationEventByUserNotificationEventId(
							userNotificationEvent.getUserNotificationEventId());

			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				notificationEvent);
		}
		catch (Exception e) {
		}
	}

	@Override
	public void onAfterUpdate(UserNotificationEvent userNotificationEvent) {
		try {
			boolean actionRequired = isActionRequired(
				userNotificationEvent.getPayload());

			com.liferay.notifications.model.UserNotificationEvent
				notificationEvent =
					UserNotificationEventLocalServiceUtil.
					fetchNotificationEventByUserNotificationEventId(
						userNotificationEvent.getUserNotificationEventId());

			UserNotificationEventLocalServiceUtil.updateUserNotificationEvent(
				notificationEvent.getNotificationEventId(),
				userNotificationEvent.getUserNotificationEventId(),
				userNotificationEvent.getUserId(),
				userNotificationEvent.getTimestamp(),
				isActionRequired(userNotificationEvent.getPayload()),
				userNotificationEvent.getDelivered(),
				userNotificationEvent.getArchived());
		}
		catch (Exception e) {
		}
	};

	protected boolean isActionRequired(String payload) throws JSONException {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

		boolean actionRequired = jsonObject.getBoolean("actionRequired");

		return actionRequired;
	}

}