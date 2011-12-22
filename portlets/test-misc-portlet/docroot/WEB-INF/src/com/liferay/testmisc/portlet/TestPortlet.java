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

package com.liferay.testmisc.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortlet;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.portlet.PortletRequestUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TestPortlet extends LiferayPortlet {

	@Override
	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String jspPage = ParamUtil.getString(
			renderRequest, "jspPage", "/view.jsp");

		if (jspPage.equals("/renderResponseponse/buffer_size.jsp")) {
			testResponseBufferSize(renderResponse);
		}

		include(jspPage, renderRequest, renderResponse);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		String fileName = "logo.png";

		PortletContext portletContext = getPortletContext();

		InputStream inputStream = portletContext.getResourceAsStream(
			"/WEB-INF/images/logo.png");

		String contentType = MimeTypesUtil.getContentType(fileName);

		ServletResponseUtil.sendFile(
			request, response, fileName, inputStream, contentType);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String fileName = resourceRequest.getResourceID();

		InputStream inputStream = getPortletContext().getResourceAsStream(
			"/WEB-INF/images/logo.png");

		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			null, resourceResponse, fileName, inputStream, contentType);
	}

	public void uploadForm1(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		String actionRequestTitle = ParamUtil.getString(actionRequest, "title");
		String uploadPortletRequestTitle = ParamUtil.getString(
			uploadPortletRequest, "title");

		File file = uploadPortletRequest.getFile("fileName");

		if (_log.isInfoEnabled()) {
			_log.info("actionRequestTitle " + actionRequestTitle);
			_log.info("uploadPortletRequestTitle " + uploadPortletRequestTitle);
			_log.info("File " + file + " " + file.length());
		}
	}

	public void uploadForm2(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletRequestUtil.testMultipartWithCommonsFileUpload(actionRequest);
	}

	public void uploadForm3(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletRequestUtil.testMultipartWithPortletInputStream(actionRequest);
	}

	protected void include(
			String path, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher =
			getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}

	protected void testResponseBufferSize(RenderResponse renderResponse) {
		_log.info("Original buffer size " + renderResponse.getBufferSize());

		renderResponse.setBufferSize(12345);

		_log.info("New buffer size " + renderResponse.getBufferSize());
	}

	private static Log _log = LogFactoryUtil.getLog(TestPortlet.class);

}