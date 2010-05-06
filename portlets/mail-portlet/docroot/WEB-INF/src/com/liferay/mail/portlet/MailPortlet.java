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
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.util.MailManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.IOException;
import java.io.InputStream;

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
						resourceResponse, attachment.getFileName(), is,
						contentType);
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

	private static Log _log = LogFactoryUtil.getLog(MailPortlet.class);

}