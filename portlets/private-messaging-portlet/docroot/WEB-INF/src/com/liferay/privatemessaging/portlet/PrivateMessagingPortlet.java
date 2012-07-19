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
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.util.PortletKeys;
import com.liferay.privatemessaging.util.PortletPropsValues;
import com.liferay.privatemessaging.util.PrivateMessagingUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					mbThreadId);
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

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

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

				validateAttachment(fileName, inputStream);

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
		catch (Exception e) {
			if (e instanceof IOException) {
				throw new PortalException("Unable to process attachment", e);
			}
			else if (e instanceof FileExtensionException ||
					 e instanceof FileNameException ||
					 e instanceof FileSizeException ||
					 e instanceof UserScreenNameException) {

				SessionErrors.add(actionRequest, e.getClass());
			}
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

			if (resourceID.equals("checkData")) {
				checkData(resourceRequest, resourceResponse);
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

	protected void checkData(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(resourceRequest);

		String to = ParamUtil.getString(uploadPortletRequest, "to");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			validateTo(to, themeDisplay);

			for (int i = 1; i <= 3; i++) {
				String fileName = uploadPortletRequest.getFileName(
					"msgFile" + i);
				InputStream inputStream = uploadPortletRequest.getFileAsStream(
					"msgFile" + i);

				if (inputStream == null) {
					continue;
				}

				validateAttachment(fileName, inputStream);
			}

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			jsonObject.put("message", getMessage(resourceRequest, e));
			jsonObject.put("success", false);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected String getMessage(PortletRequest portletRequest, Exception key)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String message = null;

		if (key instanceof FileExtensionException) {
			message = themeDisplay.translate(
				"document-names-must-end-with-one-of-the-following-extensions");

			message +=
				CharPool.SPACE +
					StringUtil.merge(
						PrefsPropsUtil.getStringArray(
							PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA),
						StringPool.COMMA_AND_SPACE);
		}
		else if (key instanceof FileNameException) {
			message = themeDisplay.translate(
				"please-enter-a-file-with-a-valid-file-name");
		}
		else if (key instanceof FileSizeException) {
			long fileMaxSize = PrefsPropsUtil.getLong(
				PropsKeys.DL_FILE_MAX_SIZE);

			if (fileMaxSize == 0) {
				fileMaxSize = PrefsPropsUtil.getLong(
					PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
			}

			fileMaxSize /= 1024;

			message = themeDisplay.translate(
				"please-enter-a-file-with-a-valid-file-size-no-larger-than-x",
				fileMaxSize);
		}
		else if (key instanceof UserScreenNameException) {
			message = themeDisplay.translate(
				"the-following-users-were-not-found");

			message += CharPool.SPACE + key.getMessage();
		}
		else {
			message = themeDisplay.translate("your-request-failed-to-complete");
		}

		return message;
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

	protected boolean isValidName(String name) {
		if ((name == null) ||
			name.contains("\\") ||
			name.contains("\\\\") ||
			name.contains("//") ||
			name.contains(":") ||
			name.contains("*") ||
			name.contains("?") ||
			name.contains("\"") ||
			name.contains("<") ||
			name.contains(">") ||
			name.contains("|") ||
			name.contains("[") ||
			name.contains("]") ||
			name.contains("../") ||
			name.contains("/..")) {

			return false;
		}

		return true;
	}

	protected void removeNotification(
			long companyId, long userId, long mbThreadId)
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

			if (portletId.equals(PortletKeys.PRIVATE_MESSAGING) &&
				(entryId == mbThreadId)) {

				ChannelHubManagerUtil.deleteUserNotificiationEvent(
					companyId, userId, notificationEvent.getUuid());
			}
		}
	}

	protected void validateAttachment(String fileName, InputStream inputStream)
		throws Exception {

		if (inputStream instanceof ByteArrayFileInputStream) {
			ByteArrayFileInputStream byteArrayFileInputStream =
				(ByteArrayFileInputStream)inputStream;

			File file = byteArrayFileInputStream.getFile();

			if ((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
				((file == null) ||
				 (file.length() >
				  PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {

				throw new FileSizeException(fileName);
			}
		}

		if (!isValidName(fileName)) {
			throw new FileNameException(fileName);
		}

		String[] fileExtensions = PrefsPropsUtil.getStringArray(
			PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA);

		boolean validFileExtension = false;

		for (String fileExtension : fileExtensions) {
			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(fileName, fileExtension)) {

				validFileExtension = true;

				break;
			}
		}

		if (!validFileExtension) {
			throw new FileExtensionException(fileName);
		}
	}

	protected void validateTo(String to, ThemeDisplay themeDisplay)
		throws Exception {

		if (Validator.isNull(to)) {
			return;
		}

		String[] recipients = StringUtil.split(to);

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

		if (!failedRecipients.isEmpty()) {
			StringBundler sb = new StringBundler(3);

			sb.append(StringPool.APOSTROPHE);
			sb.append(StringUtil.merge(failedRecipients, "', '"));
			sb.append(StringPool.APOSTROPHE);

			throw new UserScreenNameException(sb.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PrivateMessagingPortlet.class);

}