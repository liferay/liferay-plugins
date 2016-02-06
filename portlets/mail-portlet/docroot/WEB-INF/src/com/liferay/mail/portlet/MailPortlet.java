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

package com.liferay.mail.portlet;

import com.liferay.mail.model.Attachment;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.util.AttachmentHandler;
import com.liferay.mail.util.MailManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Scott Lee
 */
public class MailPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String mvcPath = resourceRequest.getParameter("mvcPath");

		if (mvcPath.equals("/attachment.jsp")) {
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