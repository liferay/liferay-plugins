/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.mail.model.MailAccount;
import com.liferay.mail.util.MailBoxManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="MailPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailPortlet extends MVCPortlet {

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("saveOrSendMessage")) {
				saveOrSendMessage(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String jspPage = resourceRequest.getParameter("jspPage");

		if (jspPage.equals("/attachment.jsp")) {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				resourceRequest);

			String emailAddress = ParamUtil.getString(
				resourceRequest, "emailAddress");
			String folderName = ParamUtil.getString(
				resourceRequest, "folderName");
			int messageUid = ParamUtil.getInteger(
				resourceRequest, "messageUid");
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			String contentPath = ParamUtil.getString(
				resourceRequest, "contentPath");

			try {
				MailBoxManager mailBoxManager = new MailBoxManager(
					PortalUtil.getUser(request), emailAddress);

				Part messagePart = mailBoxManager.getAttachment(
					folderName, messageUid, contentPath);

				if (Validator.isNotNull(messagePart)) {
					InputStream is = messagePart.getInputStream();
					String contentType = MimeTypesUtil.getContentType(fileName);

					PortletResponseUtil.sendFile(
						resourceResponse, fileName, is, contentType);
				}
			}
			catch (MessagingException me) {
			}
			catch (PortalException pe) {
			}
			catch (SystemException se) {
			}
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	protected void saveOrSendMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadReq = PortalUtil.getUploadPortletRequest(
			actionRequest);

		String emailAddress = ParamUtil.getString(
			uploadReq, "sendEmailAddress");

		MailBoxManager mailBoxManager = new MailBoxManager(
			PortalUtil.getUser(uploadReq), emailAddress);

		String oldDraftMessageUid = ParamUtil.getString(
			uploadReq, "sendDraftMessageUid");
		String from = ParamUtil.getString(uploadReq, "sendFrom");
		String to = ParamUtil.getString(uploadReq, "sendTo");
		String cc = ParamUtil.getString(uploadReq, "sendCc");
		String bcc = ParamUtil.getString(uploadReq, "sendBcc");
		String subject = ParamUtil.getString(uploadReq, "sendSubject");
		File attachments = uploadReq.getFile("sendAttachments");
		String body = ParamUtil.getString(uploadReq, "sendBody");
		boolean sendMessage = ParamUtil.getBoolean(uploadReq, "sendMessage");

		Message message = mailBoxManager.createMessage(
			from, to, cc, bcc, subject, body, new File[] {attachments});

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		if (sendMessage) {
			MailAccount fromMailAccount = new MailAccount(
				PortalUtil.getUser(uploadReq), from);

			jsonObj = mailBoxManager.sendMessage(fromMailAccount, message);
		}
		else {
			jsonObj = mailBoxManager.saveMessage(message, oldDraftMessageUid);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadReq.getAttribute(
			WebKeys.THEME_DISPLAY);

		String url =
			PortalUtil.getLayoutURL(themeDisplay) + "/-/mail/message_result?" +
				jsonObj.toString();

		String redirect = ParamUtil.getString(uploadReq, "redirect");

		actionResponse.sendRedirect(redirect);
	}

}