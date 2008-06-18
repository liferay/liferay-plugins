/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.mail.portlet;

import com.liferay.mail.util.MailBoxManager;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

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
public class MailPortlet extends JSPPortlet {

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				req, ActionRequest.ACTION_NAME);

			if (actionName.equals("sendMessage")) {
				sendMessage(req);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void serveResource(ResourceRequest req, ResourceResponse res)
		throws IOException, PortletException {

		String jspPage = req.getParameter("jspPage");

		if (jspPage.equals("/attachment.jsp")) {
			HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(req);

			String emailAddress = ParamUtil.getString(req, "emailAddress");
			String folderName = ParamUtil.getString(req, "folderName");
			int messageUid = ParamUtil.getInteger(req, "messageUid");
			String fileName = ParamUtil.getString(req, "fileName");
			String contentPath = ParamUtil.getString(req, "contentPath");

			try {
				MailBoxManager mailBoxManager = new MailBoxManager(
					PortalUtil.getUser(httpReq), emailAddress);

				Part messagePart = mailBoxManager.getAttachment(
					folderName, messageUid, contentPath);

				InputStream is = messagePart.getInputStream();
				String contentType = MimeTypesUtil.getContentType(fileName);

				PortletResponseUtil.sendFile(res, fileName, is, contentType);
			}
			catch (MessagingException me) {
			}
			catch (PortalException pe) {
			}
			catch (SystemException se) {
			}
		}
		else {
			super.serveResource(req, res);
		}
	}

	protected void sendMessage(ActionRequest req) throws Exception {
		UploadPortletRequest uploadReq = PortalUtil.getUploadPortletRequest(
			req);

		String emailAddress = ParamUtil.getString(
			uploadReq, "sendEmailAddress");
		String messageType = ParamUtil.getString(uploadReq, "sendMessageType");

		String folderName = ParamUtil.getString(uploadReq, "sendFolderName");
		long messageUid = ParamUtil.getLong(uploadReq, "sendMessageUid");
		String fromEmailAddress = ParamUtil.getString(
			uploadReq, "sendFromEmailAddress");
		String recipientTo = ParamUtil.getString(uploadReq, "sendTo");
		String recipientCc = ParamUtil.getString(uploadReq, "sendCc");
		String recipientBcc = ParamUtil.getString(uploadReq, "sendBcc");
		String subject = ParamUtil.getString(uploadReq, "sendSubject");
		String body = ParamUtil.getString(uploadReq, "sendBody");

		MailBoxManager mailBoxManager = new MailBoxManager(
			PortalUtil.getUser(uploadReq), emailAddress);

		// Create message parts

		Multipart multipart = new MimeMultipart();

		// Add content to multipart

		BodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setText(body);

		multipart.addBodyPart(messageBodyPart);

		// Add attachment to multipart

		File file = uploadReq.getFile("sendAttachments");

		if (Validator.isNotNull(file)) {
			if (multipart == null) {
				multipart = new MimeMultipart();
			}

			String fileName = file.getName();
			DataSource dataSource = new FileDataSource(file);

			BodyPart messageAttachmentPart = new MimeBodyPart();

			messageAttachmentPart.setDataHandler(new DataHandler(dataSource));
			messageAttachmentPart.setFileName(fileName);

			multipart.addBodyPart(messageAttachmentPart);
		}

		mailBoxManager.sendMessage(
			messageType, folderName, messageUid, fromEmailAddress, recipientTo,
			recipientCc, recipientBcc, subject, multipart);
	}

}