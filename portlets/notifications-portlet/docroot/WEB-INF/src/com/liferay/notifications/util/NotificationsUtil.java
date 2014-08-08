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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRenderer;

import java.io.Serializable;

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

		Iterator<Long> iterator =
			UserNotificationEventLocalServiceUtil.dynamicQuery(
				dynamicQuery).iterator();

		if (iterator.hasNext()) {
			Long count = iterator.next();

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

		Iterator<Long> iterator =
			UserNotificationEventLocalServiceUtil.dynamicQuery(
				dynamicQuery).iterator();

		if (iterator.hasNext()) {
			Long count = iterator.next();

			if (count != null) {
				return count.intValue();
			}
		}

		return 0;
	}

	public static String getEntryURL(
		AssetRenderer assetRenderer, String portletKey,
		ServiceContext serviceContext) {

		try {
			long controlPanelPlid = PortalUtil.getControlPanelPlid(
				serviceContext.getCompanyId());

			if (serviceContext.getPlid() == controlPanelPlid) {
				LiferayPortletRequest liferayPortletRequest =
					serviceContext.getLiferayPortletRequest();

				ThemeDisplay themeDisplay =
					(ThemeDisplay)liferayPortletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				long plid = PortalUtil.getPlidFromPortletId(
					assetRenderer.getGroupId(), portletKey);

				themeDisplay.setPlid(plid);
			}

			return assetRenderer.getURLViewInContext(
				serviceContext.getLiferayPortletRequest(),
				serviceContext.getLiferayPortletResponse(), null);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static List<UserNotificationEvent> getUserNotificationEvents(
			long userId, boolean actionable, int start, int end)
		throws SystemException {

		DynamicQuery dynamicQuery = getDynamicQuery(userId, actionable);

		dynamicQuery.addOrder(OrderFactoryUtil.desc("timestamp"));

		return UserNotificationEventLocalServiceUtil.dynamicQuery(
			dynamicQuery, start, end);
	}

	public static int getUserNotificationEventsCount(
			long userId, boolean actionable)
		throws SystemException {

		DynamicQuery dynamicQuery = getDynamicQuery(userId, actionable);

		dynamicQuery.setProjection(ProjectionFactoryUtil.rowCount());

		Iterator<Long> iterator =
			UserNotificationEventLocalServiceUtil.dynamicQuery(
				dynamicQuery).iterator();

		if (iterator.hasNext()) {
			Long count = iterator.next();

			if (count != null) {
				return count.intValue();
			}
		}

		return 0;
	}

	public static void sendNotificationEvent(
			long companyId, String subscriptionClassName,
			long subscriptionClassPK, String portletKey, String className,
			long classPK, String entryTitle, String entryURL,
			int notificationType, long userId)
		throws PortalException, SystemException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put("className", className);
		notificationEventJSONObject.put("classPK", classPK);
		notificationEventJSONObject.put("entryTitle", entryTitle);
		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put("userId", userId);

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				companyId, subscriptionClassName, subscriptionClassPK,
				portletKey, notificationEventJSONObject));
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

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			long companyId, String className, long classPK, String portletKey,
			JSONObject notificationEventJSONObject) {

			_companyId = companyId;
			_className = className;
			_classPK = classPK;
			_notificationEventJSONObject = notificationEventJSONObject;
			_portletKey = portletKey;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_companyId, _className, _classPK, _portletKey,
					_notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				long companyId, String className, long classPK,
				String portletKey, JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			long userId = notificationEventJSONObject.getLong("userId");

			List<Subscription> subscriptions =
				SubscriptionLocalServiceUtil.getSubscriptions(
					companyId, className, classPK);

			for (Subscription subscription : subscriptions) {
				long subscriberUserId = subscription.getUserId();

				if (subscriberUserId == userId) {
					continue;
				}

				if (UserNotificationManagerUtil.isDeliver(
						subscriberUserId, portletKey, 0, notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(), portletKey,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(
							subscriberUserId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private String _className;
		private long _classPK;
		private long _companyId;
		private JSONObject _notificationEventJSONObject;
		private String _portletKey;
	}

}