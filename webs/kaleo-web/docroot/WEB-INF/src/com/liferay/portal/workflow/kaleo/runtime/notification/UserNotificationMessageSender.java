/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class UserNotificationMessageSender
	extends BaseNotificationSender implements NotificationSender {

	@Override
	protected void doSendNotification(
			Set<NotificationRecipient> notificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws Exception {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(), PortletKeys.WORKFLOW_TASKS,
				notificationEventJSONObject);

		notificationEvent.setDeliveryRequired(0);

		for (NotificationRecipient notificationRecipient :
				notificationRecipients) {

			if (notificationRecipient.getUserId() > 0) {
				UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
					notificationRecipient.getUserId(), notificationEvent);
			}
		}
	}

}