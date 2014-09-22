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

package com.liferay.notifications.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public class UserNotificationEventImpl extends UserNotificationEventBaseImpl {

	public UserNotificationEventImpl() {
	}

	public long getDeliverBy() throws PortalException, SystemException {
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
				getUserNotificationEventId());

		return userNotificationEvent.getDeliverBy();
	}

	public String getPayload() throws PortalException, SystemException {
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
				getUserNotificationEventId());

		return userNotificationEvent.getPayload();
	}

	public String getType() throws PortalException, SystemException {
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
				getUserNotificationEventId());

		return userNotificationEvent.getType();
	}

	public com.liferay.portal.model.UserNotificationEvent
		getUserNotificationEvent() throws PortalException, SystemException {

		return UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
				getUserNotificationEventId());
	}

}