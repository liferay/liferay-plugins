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

import com.liferay.notifications.model.UserNotificationEvent;
import com.liferay.notifications.service.UserNotificationEventLocalServiceUtil;
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
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetRenderer;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lin Cui
 */
public class NotificationsUtil {

	public static List<com.liferay.portal.model.UserNotificationEvent>
		getArchivedUserNotificationEvents(
			long userId, boolean actionRequired, boolean archived, int start,
			int end)
		throws PortalException, SystemException {

		List<UserNotificationEvent> notificationEvents =
			UserNotificationEventLocalServiceUtil.
				getArchivedUserNotificationEvents(
					userId, actionRequired, archived, start, end);

		return getUserNotificationEvents(notificationEvents);
	}

	public static int getArchivedUserNotificationEventsCount(
			long userId, boolean actionRequired, boolean archived)
		throws PortalException, SystemException {

		return
			UserNotificationEventLocalServiceUtil.
				getArchivedUserNotificationEventsCount(
					userId, actionRequired, archived);
	}

	public static List<com.liferay.portal.model.UserNotificationEvent>
			getDeliveredUserNotificationEvents(
				long userId, boolean delivered, boolean actionRequired,
				int start, int end)
		throws PortalException, SystemException {

		List<UserNotificationEvent> notificationEvents =
			UserNotificationEventLocalServiceUtil.
				getDeliveredUserNotificationEvents(
					userId, delivered, actionRequired, start, end);

		return getUserNotificationEvents(notificationEvents);
	}

	public static int getDeliveredUserNotificationEventsCount(
			long userId, boolean delivered, boolean actionRequired)
		throws PortalException, SystemException {

		return
			UserNotificationEventLocalServiceUtil.
				getDeliveredUserNotificationEventsCount(
					userId, delivered, actionRequired);
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

	public static List<com.liferay.portal.model.UserNotificationEvent>
		getUserNotificationEvents(
			long userId, boolean actionRequired, int start, int end)
		throws PortalException, SystemException {

		List<UserNotificationEvent> notificationEvents =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvents(
				userId, actionRequired, start, end);

		return getUserNotificationEvents(notificationEvents);
	}

	public static int getUserNotificationEventsCount(
			long userId, boolean actionRequired)
		throws PortalException, SystemException {

		return
			UserNotificationEventLocalServiceUtil.
				getUserNotificationEventsCount(userId, actionRequired);
	}

	public static void sendNotificationEvent(
			long companyId, String portletKey, String className, long classPK,
			String entryTitle, String entryURL, int notificationType,
			List<ObjectValuePair<String, Long>> subscribersOVPs, long userId)
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
				companyId, portletKey, notificationEventJSONObject,
				subscribersOVPs));
	}

	protected static List<com.liferay.portal.model.UserNotificationEvent>
		getUserNotificationEvents(
			List<UserNotificationEvent> notificationEvents)
		throws PortalException, SystemException {

		List<com.liferay.portal.model.UserNotificationEvent>
			userNotificationEvents =
				new ArrayList<com.liferay.portal.model.UserNotificationEvent>();

		for (UserNotificationEvent userNotificationEvent : notificationEvents) {
			userNotificationEvents.add(
				com.liferay.portal.service.
					UserNotificationEventLocalServiceUtil.
						getUserNotificationEvent(
							userNotificationEvent.
								getUserNotificationEventId()));
		}

		return userNotificationEvents;
	}

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			long companyId, String portletKey,
			JSONObject notificationEventJSONObject,
			List<ObjectValuePair<String, Long>> subscribersOVPs) {

			_companyId = companyId;
			_notificationEventJSONObject = notificationEventJSONObject;
			_portletKey = portletKey;
			_subscribersOVPs = subscribersOVPs;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_companyId, _portletKey, _notificationEventJSONObject,
					_subscribersOVPs);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				long companyId, String portletKey,
				JSONObject notificationEventJSONObject,
				List<ObjectValuePair<String, Long>> subscribersOVPs)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			long userId = notificationEventJSONObject.getLong("userId");

			Set<Long> subscriberUserIds = new HashSet<Long>();

			for (ObjectValuePair<String, Long> ovp : subscribersOVPs) {
				String className = ovp.getKey();
				long classPK = ovp.getValue();

				List<Subscription> subscriptions =
					SubscriptionLocalServiceUtil.getSubscriptions(
						companyId, className, classPK);

				for (Subscription subscription : subscriptions) {
					long subscriberUserId = subscription.getUserId();

					if (subscriberUserId == userId) {
						continue;
					}

					if (subscriberUserIds.contains(subscriberUserId)) {
						continue;
					}

					subscriberUserIds.add(subscriberUserId);

					if (UserNotificationManagerUtil.isDeliver(
							subscriberUserId, portletKey, 0, notificationType,
							UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

						NotificationEvent notificationEvent =
							NotificationEventFactoryUtil.
								createNotificationEvent(
									System.currentTimeMillis(), portletKey,
									notificationEventJSONObject);

						com.liferay.portal.service.
							UserNotificationEventLocalServiceUtil.
								addUserNotificationEvent(
									subscriberUserId, notificationEvent);
					}
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private long _companyId;
		private JSONObject _notificationEventJSONObject;
		private String _portletKey;
		private List<ObjectValuePair<String, Long>> _subscribersOVPs;

	}

}