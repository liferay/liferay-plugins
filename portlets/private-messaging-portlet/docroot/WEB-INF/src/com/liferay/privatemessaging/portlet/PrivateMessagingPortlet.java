/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.privatemessaging.portlet;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.Channel;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.util.PortletKeys;
import com.liferay.privatemessaging.util.PortletPropsValues;
import com.liferay.privatemessaging.util.PrivateMessagingUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott Lee
 * @author Eudaldo Alonso
 */
public class PrivateMessagingPortlet extends MVCPortlet {

	public void deleteMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.deleteUserThread(
				themeDisplay.getUserId(), mbThreadId);

			try {
				removeNotification(
					themeDisplay.getCompanyId(), mbThreadId,
					themeDisplay.getUserId());
			}
			catch (ChannelException ce) {
			}
		}
	}

	public void getMessageAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long messageId = ParamUtil.getLong(actionRequest, "messageId");
			String fileName = ParamUtil.getString(actionRequest, "attachment");

			MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

			if (!PrivateMessagingUtil.isUserPartOfThread(
					themeDisplay.getUserId(), message.getThreadId())) {

				throw new PrincipalException();
			}

			String path = message.getAttachmentsDir() + "/" + fileName;

			InputStream inputStream = DLStoreUtil.getFileAsStream(
				message.getCompanyId(), CompanyConstants.SYSTEM, path);
			int contentLength = (int)DLStoreUtil.getFileSize(
				message.getCompanyId(), CompanyConstants.SYSTEM, path);
			String contentType = MimeTypesUtil.getContentType(fileName);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);
			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				actionResponse);

			ServletResponseUtil.sendFile(
				request, response, fileName, inputStream, contentLength,
				contentType);
		}
		catch (Exception e) {
			PortalUtil.sendError(e, actionRequest, actionResponse);
		}
	}

	public void markMessagesAsRead(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.markUserThreadAsRead(
				themeDisplay.getUserId(), mbThreadId);
		}
	}

	public void markMessagesAsUnread(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.markUserThreadAsUnread(
				themeDisplay.getUserId(), mbThreadId);
		}
	}

	public void sendMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long userId = ParamUtil.getLong(uploadPortletRequest, "userId");
		long mbThreadId = ParamUtil.getLong(uploadPortletRequest, "mbThreadId");
		String to = ParamUtil.getString(uploadPortletRequest, "to");
		String subject = ParamUtil.getString(uploadPortletRequest, "subject");
		String body = ParamUtil.getString(uploadPortletRequest, "body");
		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			new ArrayList<ObjectValuePair<String, InputStream>>();

		try {
			for (int i = 1; i <= 3; i++) {
				String fileName = uploadPortletRequest.getFileName(
					"msgFile" + i);
				InputStream inputStream = uploadPortletRequest.getFileAsStream(
					"msgFile" + i);

				if (inputStream == null) {
					continue;
				}

				try {
					ObjectValuePair<String, InputStream> inputStreamOVP =
						new ObjectValuePair<String, InputStream>(
							fileName, inputStream);

					inputStreamOVPs.add(inputStreamOVP);
				}
				catch (Exception e) {
					_log.error("unable to attach file " + fileName, e);
				}
			}

			UserThreadLocalServiceUtil.addPrivateMessage(
				userId, mbThreadId, to, subject, body, inputStreamOVPs,
				themeDisplay);
		}
		catch (IOException ioe) {
			throw new PortalException("Unable to process attachment", ioe);
		}
		finally {
			for (ObjectValuePair<String, InputStream> inputStreamOVP :
					inputStreamOVPs) {

				InputStream inputStream = inputStreamOVP.getValue();

				StreamUtil.cleanUp(inputStream);
			}
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = GetterUtil.getString(
				resourceRequest.getResourceID());

			if (resourceID.equals("checkRecipients")) {
				checkRecipients(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getUsers")) {
				getUsers(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void checkRecipients(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] recipients = StringUtil.split(
			ParamUtil.getString(resourceRequest, "recipients"));

		List<String> failedRecipients = new ArrayList<String>();

		for (String recipient : recipients) {
			recipient = recipient.trim();

			int x = recipient.indexOf(CharPool.LESS_THAN);
			int y = recipient.indexOf(CharPool.GREATER_THAN);

			try {
				if ((x != -1) && (y != -1)) {
					recipient = recipient.substring(x + 1, y);
				}

				UserLocalServiceUtil.getUserByScreenName(
					themeDisplay.getCompanyId(), recipient);
			}
			catch (NoSuchUserException nsue) {
				failedRecipients.add(recipient);
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (!failedRecipients.isEmpty()) {
			StringBundler sb = new StringBundler(3);

			sb.append(StringPool.APOSTROPHE);
			sb.append(StringUtil.merge(failedRecipients, "', '"));
			sb.append(StringPool.APOSTROPHE);

			jsonObject.put("message", sb.toString());

			jsonObject.put("success", false);
		}
		else {
			jsonObject.put("success", true);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getUsers(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		JSONObject jsonObject = PrivateMessagingUtil.getJSONRecipients(
			themeDisplay.getUserId(),
			PortletPropsValues.AUTOCOMPLETE_RECIPIENT_TYPE, keywords, 0,
			PortletPropsValues.AUTOCOMPLETE_RECIPIENT_MAX);

		JSONObject results = JSONFactoryUtil.createJSONObject();

		results.put("results", jsonObject);

		writeJSON(resourceRequest, resourceResponse, results);
	}

	protected void removeNotification(
			long companyId, long mbThreadId, long userId)
		throws ChannelException {

		List<NotificationEvent> notificationEvents = null;

		try {
			notificationEvents = ChannelHubManagerUtil.getNotificationEvents(
				companyId, userId, true);
		}
		catch (UnknownChannelException e) {
			Channel channel = ChannelHubManagerUtil.getChannel(
				companyId, userId, true);

			notificationEvents = channel.getNotificationEvents();
		}

		for (NotificationEvent notificationEvent : notificationEvents) {
			JSONObject notificationEventJSONObject =
				notificationEvent.getPayload();

			String portletId = notificationEventJSONObject.getString(
				"portletId");
			long entryId = notificationEventJSONObject.getLong("entryId");

			if (portletId.equals(PortletKeys.SO_PRIVATE_MESSAGING) &&
				(entryId == mbThreadId)) {

				String uuid = notificationEvent.getUuid();

				ChannelHubManagerUtil.confirmDelivery(
					companyId, userId, uuid, true);

				ChannelHubManagerUtil.deleteUserNotificiationEvent(
					companyId, userId, uuid);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PrivateMessagingPortlet.class);

}