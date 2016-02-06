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

package com.liferay.testmisc.portlet;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortlet;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.PortalMessages;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.testmisc.util.PortletKeys;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletFileUpload;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TestPortlet extends LiferayPortlet {

	public void addPortalMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		PortalMessages.add(
			actionRequest, PortalMessages.KEY_JSP_PATH,
			"/portal_message/portal_message.jsp");

		PortalMessages.add(
			actionRequest, PortalMessages.KEY_PORTLET_ID,
			PortletKeys.TEST_MISC);
	}

	@Override
	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String title = ParamUtil.getString(renderRequest, "title");

		if (Validator.isNotNull(title)) {
			renderResponse.setTitle(title);
		}

		String mvcPath = ParamUtil.getString(
			renderRequest, "mvcPath", "/view.jsp");

		if (mvcPath.equals("/portlet_response/buffer_size.jsp")) {
			testResponseBufferSize(renderResponse);
		}

		include(mvcPath, renderRequest, renderResponse);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (Validator.isNotNull(actionName)) {
			super.processAction(actionRequest, actionResponse);

			return;
		}

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

		testMultipartWithCommonsFileUpload(actionRequest);
	}

	public void uploadForm3(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		testMultipartWithPortletInputStream(actionRequest);
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

	protected void testMultipartWithCommonsFileUpload(
			ActionRequest actionRequest)
		throws Exception {

		boolean multiPartContent = PortletFileUpload.isMultipartContent(
			actionRequest);

		if (_log.isInfoEnabled()) {
			if (multiPartContent) {
				_log.info("The request is a multipart request");
			}
			else {
				_log.info("The request is not a multipart request");
			}
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		PortletFileUpload portletFileUpload = new PortletFileUpload(
			diskFileItemFactory);

		List<FileItem> fileItemsList = portletFileUpload.parseRequest(
			actionRequest);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Apache commons upload was able to parse " +
					fileItemsList.size() + " items");
		}

		for (int i = 0; i < fileItemsList.size(); i++) {
			FileItem fileItem = fileItemsList.get(i);

			if (_log.isInfoEnabled()) {
				_log.info("Item " + i + " " + fileItem);
			}
		}
	}

	protected int testMultipartWithPortletInputStream(
			ActionRequest actionRequest)
		throws Exception {

		InputStream inputStream = actionRequest.getPortletInputStream();

		if (inputStream != null) {
			UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
				new UnsyncByteArrayOutputStream();

			StreamUtil.transfer(inputStream, unsyncByteArrayOutputStream);

			int size = unsyncByteArrayOutputStream.size();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Byte array size from the raw input stream is " + size);
			}

			return size;
		}

		return -1;
	}

	protected void testResponseBufferSize(RenderResponse renderResponse) {
		if (_log.isInfoEnabled()) {
			_log.info("Original buffer size " + renderResponse.getBufferSize());
		}

		renderResponse.setBufferSize(12345);

		if (_log.isInfoEnabled()) {
			_log.info("New buffer size " + renderResponse.getBufferSize());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TestPortlet.class);

}