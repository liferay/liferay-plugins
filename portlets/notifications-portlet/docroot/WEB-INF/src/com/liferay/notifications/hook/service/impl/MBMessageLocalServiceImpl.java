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
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceWrapper;

import java.io.Serializable;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lin Cui
 */
public class MBMessageLocalServiceImpl extends MBMessageLocalServiceWrapper {

	public MBMessageLocalServiceImpl(
		MBMessageLocalService mbMessagePageLocalService) {

		super(mbMessagePageLocalService);
	}

	@Override
	public MBMessage updateStatus(
			long userId, long messageId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessage mbMessage = super.updateStatus(
			userId, messageId, status, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		sendNotificationEvent(
			mbMessage, getEntryURL(mbMessage, serviceContext),
			notificationType);

		return mbMessage;
	}

	protected String getEntryURL(
			MBMessage mbMessage, ServiceContext serviceContext)
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
				mbMessage.getGroupId(), PortletKeys.MESSAGE_BOARDS);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, PortletKeys.MESSAGE_BOARDS, plid,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"struts_action", "/message_boards/view_message");
			portletURL.setParameter(
				"messageId", String.valueOf(mbMessage.getMessageId()));

			return portletURL.toString();
		}
		else {
			StringBundler sb = new StringBundler(5);

			sb.append(serviceContext.getLayoutFullURL());
			sb.append(Portal.FRIENDLY_URL_SEPARATOR);
			sb.append("message_boards/view_message/");
			sb.append(mbMessage.getMessageId());

			return sb.toString();
		}
	}

	protected void sendNotificationEvent(
			MBMessage mbMessage, String entryURL, int notificationType)
		throws PortalException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"className", mbMessage.getModelClassName());
		notificationEventJSONObject.put("classPK", mbMessage.getMessageId());
		notificationEventJSONObject.put("entryTitle", mbMessage.getSubject());
		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put(
			"userId", mbMessage.getStatusByUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				mbMessage, notificationEventJSONObject));
	}

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			MBMessage mbMessage, JSONObject notificationEventJSONObject) {

			_mbMessage = mbMessage;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(_mbMessage, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				MBMessage mbMessage, JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			List<Subscription> subscriptions =
				SubscriptionLocalServiceUtil.getSubscriptions(
				mbMessage.getCompanyId(), MBCategory.class.getName(),
				mbMessage.getCategoryId());

			for (Subscription subscription : subscriptions) {
				long userId = subscription.getUserId();

				if (UserNotificationManagerUtil.isDeliver(
						userId, PortletKeys.MESSAGE_BOARDS, 0, notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(),
							PortletKeys.MESSAGE_BOARDS,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(userId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private MBMessage _mbMessage;
		private JSONObject _notificationEventJSONObject;

	}

}