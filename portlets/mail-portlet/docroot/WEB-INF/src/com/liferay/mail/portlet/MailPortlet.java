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

import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.util.MailManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

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
 */
public class MailPortlet extends MVCPortlet {

	public void saveOrSendMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		MailManager mailManager = MailManager.getInstance(request);

		long draftMessageId = ParamUtil.getLong(
			uploadRequest, "draftMessageId");
		long accountId = ParamUtil.getLong(uploadRequest, "accountId");
		String to = ParamUtil.getString(uploadRequest, "to");
		String cc = ParamUtil.getString(uploadRequest, "cc");
		String bcc = ParamUtil.getString(uploadRequest, "bcc");
		String subject = ParamUtil.getString(uploadRequest, "subject");
		String body = ParamUtil.getString(uploadRequest, "body");
		boolean sendMessage = ParamUtil.getBoolean(
			uploadRequest, "sendMessage");
//		long forwardedMessageId = ParamUtil.getLong(
//			uploadRequest, "sendForwardMessageId");
//		String forwardedAttachmentIds = ParamUtil.getString(
//			uploadRequest, "sendForwardAttachmentIds");

		// Get all attachments

		int attachmentCount = ParamUtil.getInteger(
			uploadRequest, "attachmentCount");

		List<MailFile> mailFiles = new ArrayList<MailFile>();

		// Get real attachments

//		for (int i = 1; i <= attachmentCount; i++) {
//			File file = uploadRequest.getFile("sendAttachment" + i);
//			String filename = uploadRequest.getFileName(
//				"sendAttachment" + i);
//
//			if (FileUtil.getBytes(file) != null) {
//				mailFiles.add(new MailFile(filename, file.length(), file));
//			}
//		}

		// Get pseudo forwarded attachments

//		long[] attachmentIds = GetterUtil.getLongValues(
//			forwardedAttachmentIds.split(","));
//
//		for (long attachmentId : attachmentIds) {
//			try {
//				Attachment attachment =
//					AttachmentLocalServiceUtil.getAttachment(attachmentId);
//
//				mailFiles.add(
//					new MailFile(
//						attachment.getFilename(), attachment.getSize(),
//						messageManager.getAttachment(
//							accountEntryId, attachmentId)));
//			}
//			catch (NoSuchAttachmentException nsae) {
//			}
//		}

		// Send or save

		JSONObject jsonObj = null;

		if (sendMessage) {
			jsonObj = mailManager.sendMessage(
				accountId, draftMessageId, to, cc, bcc, subject, body,
				mailFiles);
		}
		else {
			jsonObj = mailManager.saveDraft(
				accountId, draftMessageId, to, cc, bcc, subject, body,
				mailFiles);
		}

//		sendRedirect(actionRequest, actionResponse, jsonObj);
	}

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String jspPage = resourceRequest.getParameter("jspPage");

		if (jspPage.equals("/attachment.jsp")) {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				resourceRequest);

			long attachmentId = ParamUtil.getLong(
				resourceRequest, "attachmentId");

			try {
				MailManager mailManager = MailManager.getInstance(request);

				Attachment attachment =
					AttachmentLocalServiceUtil.getAttachment(attachmentId);

				InputStream is = mailManager.getAttachment(
					attachmentId);

				if (Validator.isNotNull(is)) {
					String contentType = MimeTypesUtil.getContentType(
						attachment.getFileName());

					PortletResponseUtil.sendFile(
						resourceRequest, resourceResponse,
						attachment.getFileName(), is, contentType);
				}
			}
			catch (Exception e) {
				_log.error(e.getMessage());
			}
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	protected void addAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortalException, SystemException {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long messageId = ParamUtil.getLong(actionRequest, "messageId");
		int numOfFiles = ParamUtil.getInteger(actionRequest, "numOfFiles");

		List<ObjectValuePair<String, byte[]>> files =
			new ArrayList<ObjectValuePair<String, byte[]>>();

		if (numOfFiles == 0) {
			File file = uploadRequest.getFile("file");
			String fileName = uploadRequest.getFileName("file");

			if (file != null) {
				byte[] bytes = FileUtil.getBytes(file);

				AttachmentLocalServiceUtil.addAttachment(
					userId, messageId, StringPool.BLANK, fileName, bytes.length,
					file);
			}
		}
		else {
			for (int i = 1; i <= numOfFiles; i++) {
				File file = uploadRequest.getFile("file" + i);

				String fileName = uploadRequest.getFileName("file" + i);

				if (file != null) {
					byte[] bytes = FileUtil.getBytes(file);

					AttachmentLocalServiceUtil.addAttachment(
						userId, messageId, StringPool.BLANK, fileName,
						bytes.length, file);
				}
			}
		}
	}

	protected void deleteAttachment(ActionRequest actionRequest)
		throws Exception {

		long attachmentId = ParamUtil.getLong(actionRequest, "attachmentId");

		AttachmentLocalServiceUtil.deleteAttachment(attachmentId);
	}

	private static Log _log = LogFactoryUtil.getLog(MailPortlet.class);

}