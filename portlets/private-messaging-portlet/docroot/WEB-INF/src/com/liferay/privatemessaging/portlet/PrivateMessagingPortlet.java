/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.util.PrivateMessagingUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Scott Lee
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
		List<ObjectValuePair<String, File>> files =
			new ArrayList<ObjectValuePair<String, File>>();

		for (int i = 1; i <= 3; i++) {
			File file = uploadPortletRequest.getFile("msgFile" + i);
			String fileName = uploadPortletRequest.getFileName("msgFile" + i);

			try {
				if ((file != null) && (file.length() > 0)) {
					ObjectValuePair<String, File> ovp =
						new ObjectValuePair<String, File>(fileName, file);

					files.add(ovp);
				}
			}
			catch (Exception e) {
				_log.error("unable to attach file " + fileName, e);
			}
		}

		UserThreadLocalServiceUtil.addPrivateMessage(
			userId, mbThreadId, to, subject, body, files, themeDisplay);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PrivateMessagingPortlet.class);

}