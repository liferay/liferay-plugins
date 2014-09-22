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

package com.liferay.compat.portal.kernel.notifications;

import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.NotificationEvent;

import java.lang.reflect.Method;

/**
 * @author Andrew Betts
 */
public class ChannelHubManagerUtil
	extends com.liferay.portal.kernel.notifications.ChannelHubManagerUtil {

	public static void sendNotificationEvent(
			long companyId, long userId, NotificationEvent notificationEvent)
		throws ChannelException {

		try {
			Class<?> portalChannelHubManagerUtilClass =
				com.liferay.portal.kernel.notifications.
					ChannelHubManagerUtil.class;

			Method method = portalChannelHubManagerUtilClass.getMethod(
				"sendClusterNotificationEvent", long.class, long.class,
				NotificationEvent.class);

			method.invoke(null, companyId, userId, notificationEvent);
		}
		catch (Exception e) {
			com.liferay.portal.kernel.notifications.ChannelHubManagerUtil.
				sendNotificationEvent(companyId, userId, notificationEvent);
		}
	}

}