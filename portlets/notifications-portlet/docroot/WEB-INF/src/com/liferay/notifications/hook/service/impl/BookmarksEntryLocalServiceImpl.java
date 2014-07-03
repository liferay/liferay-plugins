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

package com.liferay.notifications.hook.service.impl;

import com.liferay.compat.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceWrapper;

import java.io.Serializable;

import java.util.List;

/**
 * @author Lin Cui
 */
public class BookmarksEntryLocalServiceImpl
	extends BookmarksEntryLocalServiceWrapper {

	public BookmarksEntryLocalServiceImpl(
		BookmarksEntryLocalService bookmarksEntryLocalService) {

		super(bookmarksEntryLocalService);
	}

	@Override
	public BookmarksEntry addEntry(
			long userId, long groupId, long folderId, String name, String url,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BookmarksEntry bookmarksEntry = super.addEntry(
			userId, groupId, folderId, name, url, description, serviceContext);

		sendNotificationEvent(
			bookmarksEntry, getEntryURL(bookmarksEntry, serviceContext),
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY, userId);

		return bookmarksEntry;
	}

	@Override
	public BookmarksEntry updateEntry(
			long userId, long entryId, long groupId, long folderId, String name,
			String url, String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BookmarksEntry bookmarksEntry = super.updateEntry(
			userId, entryId, groupId, folderId, name, url, description,
			serviceContext);

		sendNotificationEvent(
			bookmarksEntry, getEntryURL(bookmarksEntry, serviceContext),
			UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY, userId);

		return bookmarksEntry;
	}

	protected String getEntryURL(
		BookmarksEntry bookmarksEntry, ServiceContext serviceContext) {

		StringBundler sb = new StringBundler(5);

		sb.append(serviceContext.getLayoutFullURL());
		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("bookmarks");
		sb.append(StringPool.SLASH);
		sb.append(bookmarksEntry.getEntryId());

		return sb.toString();
	}

	protected void sendNotificationEvent(
			BookmarksEntry bookmarksEntry, String entryURL,
			int notificationType, long statusByUserId)
		throws PortalException, SystemException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (notificationType ==
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY) {

			notificationEventJSONObject.put(
				"className", BookmarksFolder.class.getName());
			notificationEventJSONObject.put(
				"classPK", bookmarksEntry.getFolderId());
		}
		else if (notificationType ==
					UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY) {

			notificationEventJSONObject.put(
				"className", bookmarksEntry.getModelClassName());
			notificationEventJSONObject.put(
				"classPK", bookmarksEntry.getEntryId());
		}

		notificationEventJSONObject.put("entryTitle", bookmarksEntry.getName());
		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put("userId", statusByUserId);

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				bookmarksEntry, notificationEventJSONObject));
	}

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			BookmarksEntry bookmarksEntry,
			JSONObject notificationEventJSONObject) {
				_bookmarksEntry = bookmarksEntry;
				_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_bookmarksEntry, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				BookmarksEntry bookmarksEntry,
				JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			long statusByUserId = notificationEventJSONObject.getLong("userId");

			List<Subscription> subscriptions = null;

			if (notificationType ==
					UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY) {

				subscriptions = SubscriptionLocalServiceUtil.getSubscriptions(
					bookmarksEntry.getCompanyId(),
					BookmarksFolder.class.getName(),
					bookmarksEntry.getGroupId());
			}
			else if (notificationType ==
						UserNotificationDefinition.
							NOTIFICATION_TYPE_UPDATE_ENTRY) {

				subscriptions = SubscriptionLocalServiceUtil.getSubscriptions(
					bookmarksEntry.getCompanyId(),
					bookmarksEntry.getModelClassName(),
					bookmarksEntry.getEntryId());
			}

			if (subscriptions == null) {
				return;
			}

			for (Subscription subscription : subscriptions) {
				long userId = subscription.getUserId();

				if (userId == statusByUserId) {
					continue;
				}

				if (UserNotificationManagerUtil.isDeliver(
						userId, PortletKeys.BOOKMARKS, 0, notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(), PortletKeys.BOOKMARKS,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(userId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private BookmarksEntry _bookmarksEntry;
		private JSONObject _notificationEventJSONObject;

	}

}