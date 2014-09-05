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

		validate(userNotificationEventId);

		User user = userPersistence.findByPrimaryKey(userId);

		long notificationEventId = counterLocalService.increment();

		UserNotificationEvent userNotificationEvent =
			userNotificationEventPersistence.create(notificationEventId);

		userNotificationEvent.setCompanyId(user.getCompanyId());
		userNotificationEvent.setUserId(user.getUserId());
		userNotificationEvent.setUserNotificationEventId(
			userNotificationEventId);
		userNotificationEvent.setTimestamp(timeStamp);
		userNotificationEvent.setDelivered(delivered);
		userNotificationEvent.setActionRequired(actionRequired);
		userNotificationEvent.setArchived(archived);

		userNotificationEventPersistence.update(userNotificationEvent);

		return userNotificationEvent;
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
			long userId, boolean delivered, boolean actionRequired, int start,
			int end)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.findByU_D_A(
			userId, delivered, actionRequired, start, end);
	}

	public int getDeliveredUserNotificationEventsCount(
			long userId, boolean delivered, boolean actionRequired)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.countByU_D_A(
			userId, delivered, actionRequired);
	}

	public UserNotificationEvent getNotificationEventByUserNotificationEventId(
			long userNotificationEventId)
		throws PortalException, SystemException {

		return userNotificationEventPersistence.findByUserNotificationEventId(
			userNotificationEventId);
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
			long notificationEventId, long timeStamp, boolean actionRequired,
			boolean delivered, boolean archived)
		throws PortalException, SystemException {

		UserNotificationEvent userNotificationEvent =
			userNotificationEventPersistence.findByPrimaryKey(
				notificationEventId);

		userNotificationEvent.setTimestamp(timeStamp);
		userNotificationEvent.setActionRequired(actionRequired);
		userNotificationEvent.setDelivered(delivered);
		userNotificationEvent.setArchived(archived);

		userNotificationEventPersistence.update(userNotificationEvent);

		return userNotificationEvent;
	}

	protected void validate(long userNotificationEventId)
		throws PortalException, SystemException {

		UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
			userNotificationEventId);
	}

}