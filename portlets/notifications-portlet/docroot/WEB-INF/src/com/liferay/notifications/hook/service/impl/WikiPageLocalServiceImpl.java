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
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalService;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceWrapper;

import java.io.Serializable;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lin Cui
 */
public class WikiPageLocalServiceImpl extends WikiPageLocalServiceWrapper {

	public WikiPageLocalServiceImpl(WikiPageLocalService wikiPageLocalService) {
		super(wikiPageLocalService);
	}

	@Override
	public WikiPage updateStatus(
			long userId, WikiPage page, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		WikiPage wikiPage = super.updateStatus(
			userId, page, status, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		sendNotificationEvent(
			wikiPage, getEntryURL(wikiPage, serviceContext), notificationType);

		return wikiPage;
	}

	protected String getEntryURL(WikiPage page, ServiceContext serviceContext)
		throws PortalException, SystemException {

		HttpServletRequest request = serviceContext.getRequest();

		if (request == null) {
			return StringPool.BLANK;
		}

		long plid = serviceContext.getPlid();

		long controlPanelPlid = PortalUtil.getControlPanelPlid(
			serviceContext.getCompanyId());

		if (plid == controlPanelPlid) {
			plid = PortalUtil.getPlidFromPortletId(
				page.getGroupId(), PortletKeys.WIKI);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, PortletKeys.WIKI, plid, PortletRequest.RENDER_PHASE);

			portletURL.setParameter("struts_action", "/wiki/view");
			portletURL.setParameter("pageId", String.valueOf(page.getPageId()));

			return portletURL.toString();
		}
		else {
			StringBundler sb = new StringBundler(6);

			sb.append(serviceContext.getLayoutFullURL());
			sb.append(Portal.FRIENDLY_URL_SEPARATOR);
			sb.append("wiki/");
			sb.append(page.getNodeId());
			sb.append(StringPool.SLASH);
			sb.append(
				HttpUtil.encodeURL(
					StringUtil.replace(
						page.getTitle(), _UNESCAPED_CHARS, _ESCAPED_CHARS)));

			return sb.toString();
		}
	}

	protected void sendNotificationEvent(
			WikiPage wikiPage, String entryURL, int notificationType)
		throws PortalException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"className", wikiPage.getModelClassName());
		notificationEventJSONObject.put("classPK", wikiPage.getPageId());
		notificationEventJSONObject.put("entryTitle", wikiPage.getTitle());
		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put("userId", wikiPage.getStatusByUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				wikiPage, notificationEventJSONObject));
	}

	private static final String[] _ESCAPED_CHARS = new String[] {
		"<PLUS>", "<QUESTION>", "<SLASH>"
	};

	private static final String[] _UNESCAPED_CHARS = new String[] {
		StringPool.PLUS, StringPool.QUESTION, StringPool.SLASH
	};

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			WikiPage wikiPage, JSONObject notificationEventJSONObject) {

			_wikiPage = wikiPage;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(_wikiPage, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				WikiPage wikiPage, JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			List<Subscription> subscriptions = null;

			if (notificationType ==
					UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY) {

				subscriptions = SubscriptionLocalServiceUtil.getSubscriptions(
					wikiPage.getCompanyId(), WikiNode.class.getName(),
					wikiPage.getNodeId());
			}
			else if (notificationType ==
						UserNotificationDefinition.
							NOTIFICATION_TYPE_UPDATE_ENTRY) {

				subscriptions = SubscriptionLocalServiceUtil.getSubscriptions(
					wikiPage.getCompanyId(), wikiPage.getModelClassName(),
					wikiPage.getResourcePrimKey());
			}

			if (subscriptions == null) {
				return;
			}

			for (Subscription subscription : subscriptions) {
				long userId = subscription.getUserId();

				if (UserNotificationManagerUtil.isDeliver(
						userId, PortletKeys.WIKI, 0, notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(), PortletKeys.WIKI,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(userId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private WikiPage _wikiPage;
		private JSONObject _notificationEventJSONObject;

	}

}