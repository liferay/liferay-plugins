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

package com.liferay.notifications.service.impl;

import com.liferay.notifications.model.UserNotificationEvent;
import com.liferay.notifications.service.base.UserNotificationEventLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;

import java.util.List;

/**
 * @author Calvin Keum
 */
public class UserNotificationEventLocalServiceImpl
	extends UserNotificationEventLocalServiceBaseImpl {

	public UserNotificationEvent addUserNotificationEvent(
			long userNotificationEventId, long userId, long timeStamp,
			boolean actionRequired, boolean delivered, boolean archived)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
			userNotificationEventId);

		long notificationEventId = counterLocalService.increment();

		UserNotificationEvent userNotificationEvent =
			userNotificationEventPersistence.create(notificationEventId);

		userNotificationEvent.setUserId(user.getUserId());
		userNotificationEvent.setUserNotificationEventId(
			userNotificationEventId);
		userNotificationEvent.setTimestamp(timeStamp);
		userNotificationEvent.setActionRequired(actionRequired);
		userNotificationEvent.setDelivered(delivered);
		userNotificationEvent.setArchived(archived);

		userNotificationEventPersistence.update(userNotificationEvent, false);

		return userNotificationEvent;
	}

	@Override
	public UserNotificationEvent deleteUserNotificationEvent(
			long notificationEventId)
		throws PortalException, SystemException {

		userNotificationEventPersistence.findByPrimaryKey(notificationEventId);

		return userNotificationEventPersistence.remove(notificationEventId);
	}

	public UserNotificationEvent
		fetchNotificationEventByUserNotificationEventId(
			long UserNotificationEventId)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.fetchByUserNotificationEventId(
			UserNotificationEventId);
	}

	public List<UserNotificationEvent> getArchivedUserNotificationEvents(
			long userId, boolean actionRequired, boolean archived, int start,
			int end)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.findByU_A_A(
			userId, actionRequired, archived, start, end);
	}

	public int getArchivedUserNotificationEventsCount(
			long userId, boolean actionRequired, boolean archived)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.countByU_A_A(
			userId, actionRequired, archived);
	}

	public List<UserNotificationEvent> getDeliveredUserNotificationEvents(
			long userId, boolean actionRequired, boolean delivered, int start,
			int end)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.findByU_A_D(
			userId, actionRequired, delivered, start, end);
	}

	public int getDeliveredUserNotificationEventsCount(
			long userId, boolean actionRequired, boolean delivered)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.countByU_A_D(
			userId, actionRequired, delivered);
	}

	public List<UserNotificationEvent> getUserNotificationEvents(
			long userId, boolean actionRequired, int start, int end)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.findByU_A(
			userId, actionRequired, start, end);
	}

	public int getUserNotificationEventsCount(
			long userId, boolean actionRequired)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.countByU_A(
			userId, actionRequired);
	}

	public UserNotificationEvent updateUserNotificationEvent(
			long notificationEventId, long userNotificationEventId, long userId,
			long timeStamp, boolean actionRequired, boolean delivered,
			boolean archived)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
			userNotificationEventId);

		UserNotificationEvent userNotificationEvent =
			userNotificationEventPersistence.findByPrimaryKey(
				notificationEventId);

		userNotificationEvent.setUserId(user.getUserId());
		userNotificationEvent.setUserNotificationEventId(
			userNotificationEventId);
		userNotificationEvent.setTimestamp(timeStamp);
		userNotificationEvent.setActionRequired(actionRequired);
		userNotificationEvent.setDelivered(delivered);
		userNotificationEvent.setArchived(archived);

		userNotificationEventPersistence.update(userNotificationEvent, false);

		return userNotificationEvent;
	}

}