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

package com.liferay.notifications.dockbarnotifications.poller;

import com.liferay.notifications.util.PortletPropsValues;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public class DockbarNotificationsPollerProcessor extends BasePollerProcessor {

	@Override
	protected PollerResponse doReceive(PollerRequest pollerRequest)
		throws Exception {

		return setUserNotificationsCount(pollerRequest);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
	}

	protected PollerResponse setUserNotificationsCount(
			PollerRequest pollerRequest)
		throws Exception {

		PollerResponse pollerResponse = pollerRequest.createPollerResponse();

		pollerResponse.setParameter(
			"timestamp", String.valueOf(System.currentTimeMillis()));

		if (PortletPropsValues.USER_NOTIFICATION_DOCKBAR_SPLIT) {
			int newActionableUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEventsCount(
						pollerRequest.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false,
						true);

			pollerResponse.setParameter(
				"newActionableUserNotificationsCount",
				String.valueOf(newActionableUserNotificationsCount));

			int newNonActionableUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEventsCount(
						pollerRequest.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false,
						false);

			pollerResponse.setParameter(
				"newNonActionableUserNotificationsCount",
				String.valueOf(newNonActionableUserNotificationsCount));

			int unreadActionableUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(
						pollerRequest.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
						false);

			pollerResponse.setParameter(
				"unreadActionableUserNotificationsCount",
				String.valueOf(unreadActionableUserNotificationsCount));

			int unreadNonActionableUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(
						pollerRequest.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false,
						false);

			pollerResponse.setParameter(
				"unreadNonActionableUserNotificationsCount",
				String.valueOf(unreadNonActionableUserNotificationsCount));
		}
		else {
			int newUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEventsCount(
						pollerRequest.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

			pollerResponse.setParameter(
				"newUserNotificationsCount",
				String.valueOf(newUserNotificationsCount));

			int unreadUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(
						pollerRequest.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

			pollerResponse.setParameter(
				"unreadUserNotificationsCount",
				String.valueOf(unreadUserNotificationsCount));
		}

		return pollerResponse;
	}

}