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

package com.liferay.notifications.dockbarnotifications.poller;

import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public class DockbarNotificationsPollerProcessor extends BasePollerProcessor {

	@Override
	protected void doReceive(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		setNewUserNotificationsCount(pollerRequest, pollerResponse);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
	}

	protected void setNewUserNotificationsCount(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		int count =
			UserNotificationEventLocalServiceUtil.
				getDeliveredUserNotificationEventsCount(
					pollerRequest.getUserId(), false);

		pollerResponse.setParameter("count", String.valueOf(count));
	}

}