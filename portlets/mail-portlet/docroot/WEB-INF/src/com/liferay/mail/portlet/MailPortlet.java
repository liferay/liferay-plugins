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

package com.liferay.mail.portlet;

import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.util.AttachmentHandler;
import com.liferay.mail.util.MailManager;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Scott Lee
 */
public class MailPortlet extends MVCPortlet {

	public void sendMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long accountId = ParamUtil.getLong(uploadPortletRequest, "accountId");
		long messageId = ParamUtil.getLong(uploadPortletRequest, "messageId");
		String to = ParamUtil.getString(uploadPortletRequest, "to");
		String cc = ParamUtil.getString(uploadPortletRequest, "cc");
		String bcc = ParamUtil.getString(uploadPortletRequest, "bcc");
		String subject = ParamUtil.getString(uploadPortletRequest, "subject");
		String body = ParamUtil.getString(uploadPortletRequest, "body");

		int attachmentCount = ParamUtil.getInteger(
			uploadPortletRequest, "attachmentCount");

		List<MailFile> mailFiles = new ArrayList<MailFile>();

		try {
			for (int i = 1; i <= attachmentCount; i++) {
				File file = uploadPortletRequest.getFile(
					"attachment" + i, true);
				String fileName = uploadPortletRequest.getFileName(
					"attachment" + i);
				long size = uploadPortletRequest.getSize("attachment" + i);

				if (file == null) {
					continue;
				}

				MailFile mailFile = new MailFile(file, fileName, size);

				mailFiles.add(mailFile);
			}

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);

			MailManager mailManager = MailManager.getInstance(request);

			JSONObject responseDataJSONObject = mailManager.sendMessage(
				accountId, messageId, to, cc, bcc, subject, body, mailFiles);

			String redirect =
				PortalUtil.getLayoutURL(themeDisplay) +
					"/-/mail/send_message?responseData=" +
						responseDataJSONObject;

			actionResponse.sendRedirect(redirect);
		}
		finally {
			for (MailFile mailFile : mailFiles) {
				mailFile.cleanUp();
			}
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String jspPage = resourceRequest.getParameter("jspPage");

		if (jspPage.equals("/attachment.jsp")) {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				resourceRequest);

			long attachmentId = ParamUtil.getLong(
				resourceRequest, "attachmentId");

			AttachmentHandler attachmentHandler = null;

			try {
				MailManager mailManager = MailManager.getInstance(request);

				Attachment attachment =
					AttachmentLocalServiceUtil.getAttachment(attachmentId);

				attachmentHandler = mailManager.getAttachment(attachmentId);

				if (attachmentHandler != null) {
					String contentType = MimeTypesUtil.getContentType(
						attachment.getFileName());

					PortletResponseUtil.sendFile(
						resourceRequest, resourceResponse,
						attachment.getFileName(),
						attachmentHandler.getInputStream(), contentType);
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
			finally {
				attachmentHandler.cleanUp();
			}
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MailPortlet.class);

}