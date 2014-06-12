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

package com.liferay.notifications.util;

import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Lin Cui
 */
public class NotificationsUtil {

	public static List<UserNotificationEvent> getArchivedUserNotificationEvents(
			long userId, boolean actionable, boolean archived, int start,
			int end)
		throws SystemException {

		DynamicQuery dynamicQuery = getDynamicQuery(userId, actionable);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", archived));

		dynamicQuery.addOrder(OrderFactoryUtil.desc("timestamp"));

		return UserNotificationEventLocalServiceUtil.dynamicQuery(
			dynamicQuery, start, end);
	}

	public static int getArchivedUserNotificationEventsCount(
			long userId, boolean actionable, boolean archived)
		throws SystemException {

		DynamicQuery dynamicQuery = getDynamicQuery(userId, actionable);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", archived));

		dynamicQuery.setProjection(ProjectionFactoryUtil.rowCount());

		Iterator<Long> itr =
			UserNotificationEventLocalServiceUtil.dynamicQuery(
				dynamicQuery).iterator();

		if (itr.hasNext()) {
			Long count = itr.next();

			if (count != null) {
				return count.intValue();
			}
		}

		return 0;
	}

	public static List<UserNotificationEvent>
			getDeliveredUserNotificationEvents(
				long userId, boolean actionable, boolean delivered, int start,
				int end)
		throws SystemException {

		DynamicQuery dynamicQuery = getDynamicQuery(userId, actionable);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("delivered", delivered));

		dynamicQuery.addOrder(OrderFactoryUtil.desc("timestamp"));

		return UserNotificationEventLocalServiceUtil.dynamicQuery(
			dynamicQuery, start, end);
	}

	public static int getDeliveredUserNotificationEventsCount(
			long userId, boolean actionable, boolean delivered)
		throws SystemException {

		DynamicQuery dynamicQuery = getDynamicQuery(userId, actionable);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("delivered", delivered));

		dynamicQuery.setProjection(ProjectionFactoryUtil.rowCount());

		Iterator<Long> itr =
			UserNotificationEventLocalServiceUtil.dynamicQuery(
				dynamicQuery).iterator();

		if (itr.hasNext()) {
			Long count = itr.next();

			if (count != null) {
				return count.intValue();
			}
		}

		return 0;
	}

	protected static DynamicQuery getDynamicQuery(
			long userId, boolean actionable)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserNotificationEvent.class);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userId", userId));

		if (actionable) {
			Disjunction disjuncton = RestrictionsFactoryUtil.disjunction();

			disjuncton.add(
				RestrictionsFactoryUtil.in(
					"type", NotificationsConstants.ACTIONABLE_TYPES));

			dynamicQuery.add(disjuncton);
		}
		else {
			Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

			for (String actionableType :
					NotificationsConstants.ACTIONABLE_TYPES) {

				conjunction.add(
					RestrictionsFactoryUtil.ne("type", actionableType));
			}

			dynamicQuery.add(conjunction);
		}

		return dynamicQuery;
	}

}