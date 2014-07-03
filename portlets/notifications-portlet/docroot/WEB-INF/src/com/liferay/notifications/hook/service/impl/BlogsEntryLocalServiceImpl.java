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
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalService;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceWrapper;

import java.io.Serializable;

import java.util.List;

/**
 * @author Lin Cui
 */
public class BlogsEntryLocalServiceImpl extends BlogsEntryLocalServiceWrapper {

	public BlogsEntryLocalServiceImpl(
		BlogsEntryLocalService blogsEntryLocalService) {

		super(blogsEntryLocalService);
	}

	@Override
	public BlogsEntry updateStatus(
			long userId, long entryId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		BlogsEntry blogsEntry = super.updateStatus(
			userId, entryId, status, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(serviceContext.getLayoutFullURL());
		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("blogs");
		sb.append(StringPool.SLASH);
		sb.append(blogsEntry.getEntryId());

		sendNotificationEvent(blogsEntry, sb.toString(), notificationType);

		return blogsEntry;
	}

	protected void sendNotificationEvent(
			BlogsEntry blogsEntry, String entryURL, int notificationType)
		throws PortalException, SystemException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"className", blogsEntry.getModelClassName());
		notificationEventJSONObject.put("classPK", blogsEntry.getEntryId());
		notificationEventJSONObject.put("entryTitle", blogsEntry.getTitle());
		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put(
			"userId", blogsEntry.getStatusByUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				blogsEntry, notificationEventJSONObject));
	}

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			BlogsEntry blogsEntry, JSONObject notificationEventJSONObject) {

			_blogsEntry = blogsEntry;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_blogsEntry, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				BlogsEntry blogsEntry, JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			List<Subscription> subscriptions =
				SubscriptionLocalServiceUtil.getSubscriptions(
					blogsEntry.getCompanyId(), blogsEntry.getModelClassName(),
					blogsEntry.getGroupId());

			for (Subscription subscription : subscriptions) {
				long userId = subscription.getUserId();

				if (UserNotificationManagerUtil.isDeliver(
						userId, PortletKeys.BLOGS, 0, notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(), PortletKeys.BLOGS,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(userId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private BlogsEntry _blogsEntry;
		private JSONObject _notificationEventJSONObject;

	}

}