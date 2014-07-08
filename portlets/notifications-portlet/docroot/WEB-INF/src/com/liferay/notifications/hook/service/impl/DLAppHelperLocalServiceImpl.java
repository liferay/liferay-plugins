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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceWrapper;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lin Cui
 */
public class DLAppHelperLocalServiceImpl
	extends DLAppHelperLocalServiceWrapper {

	public DLAppHelperLocalServiceImpl(
		DLAppHelperLocalService dlAppHelperLocalService) {

		super(dlAppHelperLocalService);
	}

	@Override
	public void updateStatus(
			long userId, FileEntry fileEntry, FileVersion latestFileVersion,
			int oldStatus, int newStatus,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		super.updateStatus(
			userId, fileEntry, latestFileVersion, oldStatus, newStatus,
			workflowContext, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		sendNotificationEvent(
			latestFileVersion,
			getEntryURL(
				latestFileVersion.getGroupId(),
				latestFileVersion.getFileEntryId(), serviceContext),
			notificationType);
	}

	protected String getEntryURL(
			long groupId, long fileEntryId, ServiceContext serviceContext)
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
				groupId, PortletKeys.DOCUMENT_LIBRARY);
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.DOCUMENT_LIBRARY, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"struts_action", "/document_library/view_file_entry");
		portletURL.setParameter("fileEntryId", String.valueOf(fileEntryId));

		return portletURL.toString();
	}

	protected void sendNotificationEvent(
			FileVersion fileVersion, String entryURL, int notificationType)
		throws PortalException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"className", DLFileEntryConstants.getClassName());
		notificationEventJSONObject.put(
			"classPK", fileVersion.getFileEntryId());
		notificationEventJSONObject.put("entryTitle", fileVersion.getTitle());
		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("notificationType", notificationType);
		notificationEventJSONObject.put(
			"userId", fileVersion.getStatusByUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				fileVersion, notificationEventJSONObject));
	}

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			FileVersion fileVersion, JSONObject notificationEventJSONObject) {

			_fileVersion = fileVersion;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_fileVersion, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				FileVersion fileVersion, JSONObject notificationEventJSONObject)
			throws PortalException, SystemException {

			int notificationType = notificationEventJSONObject.getInt(
				"notificationType");

			List<Subscription> subscriptions =
				SubscriptionLocalServiceUtil.getSubscriptions(
				fileVersion.getCompanyId(), Folder.class.getName(),
				fileVersion.getGroupId());

			for (Subscription subscription : subscriptions) {
				long userId = subscription.getUserId();

				if (UserNotificationManagerUtil.isDeliver(
						userId, PortletKeys.DOCUMENT_LIBRARY, 0,
						notificationType,
						UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

					NotificationEvent notificationEvent =
						NotificationEventFactoryUtil.createNotificationEvent(
							System.currentTimeMillis(),
							PortletKeys.DOCUMENT_LIBRARY,
							notificationEventJSONObject);

					UserNotificationEventLocalServiceUtil.
						addUserNotificationEvent(userId, notificationEvent);
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private FileVersion _fileVersion;
		private JSONObject _notificationEventJSONObject;

	}

}