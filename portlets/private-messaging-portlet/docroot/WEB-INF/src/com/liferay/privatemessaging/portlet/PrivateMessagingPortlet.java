/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.privatemessaging.portlet;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
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
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.util.PortletPropsValues;
import com.liferay.privatemessaging.util.PrivateMessagingUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Scott Lee
 * @author Eudaldo Alonso
 */
public class PrivateMessagingPortlet extends MVCPortlet {

	public void deleteMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

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
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long messageId = ParamUtil.getLong(resourceRequest, "messageId");
		String fileName = ParamUtil.getString(resourceRequest, "attachment");

		MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

		if (!PrivateMessagingUtil.isUserPartOfThread(
				themeDisplay.getUserId(), message.getThreadId())) {

			throw new PrincipalException();
		}

		FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			message.getGroupId(), message.getAttachmentsFolderId(), fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName,
			fileEntry.getContentStream(), (int)fileEntry.getSize(),
			fileEntry.getMimeType());
	}

	public void markMessagesAsRead(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

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
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.markUserThreadAsUnread(
				themeDisplay.getUserId(), mbThreadId);
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("sendMessage")) {
				sendMessage(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void sendMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

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
			new ArrayList<>();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

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
						new ObjectValuePair<>(fileName, inputStream);

					inputStreamOVPs.add(inputStreamOVP);
				}
				catch (Exception e) {
					_log.error(
						translate(actionRequest, "unable to attach file ") +
							fileName,
						e);
				}
			}

			UserThreadLocalServiceUtil.addPrivateMessage(
				userId, mbThreadId, to, subject, body, inputStreamOVPs,
				themeDisplay);

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("message", getMessage(actionRequest, e));

			jsonObject.put("success", Boolean.FALSE);
		}
		finally {
			for (ObjectValuePair<String, InputStream> inputStreamOVP :
					inputStreamOVPs) {

				InputStream inputStream = inputStreamOVP.getValue();

				StreamUtil.cleanUp(inputStream);
			}
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = GetterUtil.getString(
				resourceRequest.getResourceID());

			if (resourceID.equals("getMessageAttachment")) {
				getMessageAttachment(resourceRequest, resourceResponse);
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

	protected String getMessage(PortletRequest portletRequest, Exception key)
		throws Exception {

		String message = null;

		if (key instanceof FileExtensionException) {
			message = translate(
				portletRequest,
				"document-names-must-end-with-one-of-the-following-extensions");

			message +=
				CharPool.SPACE +
					StringUtil.merge(
						PrefsPropsUtil.getStringArray(
							PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA),
						StringPool.COMMA_AND_SPACE);
		}
		else if (key instanceof FileNameException) {
			message = translate(
				portletRequest, "please-enter-a-file-with-a-valid-file-name");
		}
		else if (key instanceof FileSizeException) {
			long fileMaxSize = PrefsPropsUtil.getLong(
				PropsKeys.DL_FILE_MAX_SIZE);

			if (fileMaxSize == 0) {
				fileMaxSize = PrefsPropsUtil.getLong(
					PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
			}

			fileMaxSize /= 1024;

			message = translate(
				portletRequest,
				"please-enter-a-file-with-a-valid-file-size-no-larger-than-x",
				fileMaxSize);
		}
		else if (key instanceof UserScreenNameException) {
			message = translate(
				portletRequest, "the-following-users-were-not-found");

			message += CharPool.SPACE + key.getMessage();
		}
		else {
			message = translate(
				portletRequest, "your-request-failed-to-complete");
		}

		return message;
	}

	protected void getUsers(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		JSONObject resultsJSONObject = JSONFactoryUtil.createJSONObject();

		JSONObject jsonObject = PrivateMessagingUtil.getJSONRecipients(
			themeDisplay.getUserId(),
			PortletPropsValues.AUTOCOMPLETE_RECIPIENT_TYPE, keywords, 0,
			PortletPropsValues.AUTOCOMPLETE_RECIPIENT_MAX);

		resultsJSONObject.put("results", jsonObject);

		writeJSON(resourceRequest, resourceResponse, resultsJSONObject);
	}

	protected boolean isValidName(String name) {
		if ((name == null) || name.contains("\\") || name.contains("\\\\") ||
			name.contains("//") || name.contains(":") || name.contains("*") ||
			name.contains("?") || name.contains("\"") || name.contains("<") ||
			name.contains(">") || name.contains("|") || name.contains("[") ||
			name.contains("]") || name.contains("../") ||
			name.contains("/..")) {

			return false;
		}

		return true;
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

		List<String> failedRecipients = new ArrayList<>();

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

	@Override
	protected void writeJSON(
			PortletRequest portletRequest, MimeResponse mimeResponse,
			Object json)
		throws IOException {

		mimeResponse.setContentType(ContentTypes.TEXT_HTML);

		PortletResponseUtil.write(mimeResponse, json.toString());

		mimeResponse.flushBuffer();
	}

	private static Log _log = LogFactoryUtil.getLog(
		PrivateMessagingPortlet.class);

}