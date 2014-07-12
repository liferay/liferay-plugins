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
import com.liferay.notifications.util.NotificationsUtil;
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
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceWrapper;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Lin Cui
 */
public class JournalArticleLocalServiceImpl
	extends JournalArticleLocalServiceWrapper {

	public JournalArticleLocalServiceImpl(
		JournalArticleLocalService journalArticleLocalService) {

		super(journalArticleLocalService);
	}

	@Override
	public JournalArticle updateStatus(
			long userId, JournalArticle article, int status, String articleURL,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalArticle journalArticle = super.updateStatus(
			userId, article, status, articleURL, workflowContext,
			serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		String entryURL = NotificationsUtil.getEntryURL(
			_assetRendererFactory.getAssetRenderer(article.getId()),
			serviceContext);

		sendNotificationEvent(
			journalArticle,
			journalArticle.getTitle(serviceContext.getLanguageId()), entryURL,
			notificationType);

		return journalArticle;
	}

	protected void sendNotificationEvent(
			JournalArticle journalArticle, String articleTitle,
			String articleURL, int notificationType)
		throws PortalException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"className", journalArticle.getModelClassName());
		notificationEventJSONObject.put("classPK", journalArticle.getId());
		notificationEventJSONObject.put("entryTitle", articleTitle);
		notificationEventJSONObject.put("entryURL", articleURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put(
			"userId", journalArticle.getStatusByUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				journalArticle, notificationEventJSONObject));
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			JournalArticle.class.getName());

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			JournalArticle journalArticle,
			JSONObject notificationEventJSONObject) {

			_journalArticle = journalArticle;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_journalArticle, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				JournalArticle journalArticle,
				JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			List<Subscription> subscriptions =
				SubscriptionLocalServiceUtil.getSubscriptions(
					journalArticle.getCompanyId(),
					journalArticle.getModelClassName(),
					journalArticle.getGroupId());

			for (Subscription subscription : subscriptions) {
				long userId = subscription.getUserId();

				if (UserNotificationManagerUtil.isDeliver(
						userId, PortletKeys.JOURNAL, 0, notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(), PortletKeys.JOURNAL,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(userId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private JournalArticle _journalArticle;
		private JSONObject _notificationEventJSONObject;

	}

}