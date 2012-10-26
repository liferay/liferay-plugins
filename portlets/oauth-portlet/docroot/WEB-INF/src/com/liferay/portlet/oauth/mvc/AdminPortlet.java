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

package com.liferay.portlet.oauth.mvc;

import com.liferay.portal.ImageTypeException;
import com.liferay.portal.RequiredFieldException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.service.ApplicationLocalServiceUtil;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 *
 * @author Igor Beslic
 *
 */
public class AdminPortlet extends MVCPortlet {

	public void addApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		String name = ParamUtil.getString(actionRequest, OAuthConstants.NAME);
		String description = ParamUtil.getString(
			actionRequest, OAuthConstants.DESCRIPTION);
		String website = ParamUtil.getString(
			actionRequest, OAuthConstants.WEBSITE);
		String callBackURL = ParamUtil.getString(
			actionRequest, OAuthConstants.CALLBACK_URL);
		int accessLevel = ParamUtil.getInteger(
			actionRequest, OAuthConstants.ACCESS_TYPE,
			OAuthConstants.ACCESS_TYPE_READ);

		try {

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			Application application =
				ApplicationLocalServiceUtil.addApplication(
					serviceContext.getUserId(), name, description, website,
					callBackURL, accessLevel, serviceContext);

			actionRequest.setAttribute(OAuthConstants.BEAN_ID, application);
		}
		catch (Exception e) {
			if (e instanceof SystemException) {
				_log.error(e, e);
			}
			else if (e instanceof RequiredFieldException ||
					 e instanceof MalformedURLException) {

				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw new PortletException(e.fillInStackTrace());
			}
		}
	}

	public void addTempImageFile(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		InputStream inputStream = uploadPortletRequest.getFileAsStream(
			"fileName");

		String mimeType = MimeTypesUtil.getContentType(inputStream, null);

		if (!MimeTypesUtil.isWebImage(mimeType)) {
			throw new ImageTypeException();
		}

		addTempImageFile(
			themeDisplay.getUserId(), getTempImageFileName(actionRequest),
			getTempImageFolderName(), getTempImageFilePath(actionRequest),
			inputStream);
	}

	public void deleteApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long applicationId = ParamUtil.getLong(
				actionRequest, OAuthConstants.APPLICATION_ID);

		try {
			if (applicationId > 0) {
				ServiceContext serviceContext =
						ServiceContextFactory.getInstance(actionRequest);

				ApplicationLocalServiceUtil.deleteApplication(
					applicationId, serviceContext);
			}
			else {
				SessionErrors.add(
					actionRequest,
					"can-not-complete-operation-without-application-id");
			}
		}
		catch (Exception e) {
			if (e instanceof SystemException) {
				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw new PortletException(e.fillInStackTrace());
			}
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renberResponse)
		throws IOException, PortletException {

		long applicationId = ParamUtil.getLong(
			renderRequest, OAuthConstants.APPLICATION_ID);

		if (applicationId > 0) {
			try {
				Application application =
					ApplicationLocalServiceUtil.fetchApplication(applicationId);

				renderRequest.setAttribute(OAuthConstants.BEAN_ID, application);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(renderRequest, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e.fillInStackTrace());
				}
			}
		}

		super.render(renderRequest, renberResponse);
	}

	public void saveTempImageFile(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long applicationId = ParamUtil.getLong(
			actionRequest, "applicationId", 0);

		String tempFilePath = getTempImageFilePath(actionRequest);
		InputStream tempImageStream = null;

		try {
			tempImageStream = getTempImageStream(tempFilePath);

			if (tempImageStream == null) {
				throw new UploadException();
			}

			ImageBag imageBag = ImageToolUtil.read(tempImageStream);

			RenderedImage renderedImage = imageBag.getRenderedImage();

			String cropRegionJSON = ParamUtil.getString(
				actionRequest, "cropRegion");

			if (Validator.isNotNull(cropRegionJSON)) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					cropRegionJSON);

				int height = jsonObject.getInt("height");
				int width = jsonObject.getInt("width");
				int x = jsonObject.getInt("x");
				int y = jsonObject.getInt("y");

				renderedImage = getCroppedRenderedImage(
					renderedImage, height, width, x, y);
			}

			byte[] bytes = ImageToolUtil.getBytes(
				renderedImage, imageBag.getType());

			saveTempImageFile(applicationId, bytes);
		}
		finally {
			TempFileUtil.deleteTempFile(tempFilePath);
			StreamUtil.cleanUp(tempImageStream);
		}

		addSuccessMessage(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);

			if (cmd.equals(Constants.GET_TEMP)) {
				String folderName = getTempImageFilePath(resourceRequest);

				InputStream tempImageStream = getTempImageStream(folderName);

				if (tempImageStream != null) {
					serveTempImageFile(resourceResponse, tempImageStream);
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	public void updateApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long applicationId = ParamUtil.getLong(
			actionRequest, OAuthConstants.APPLICATION_ID);

		if (applicationId > 0) {
			String name = ParamUtil.getString(
				actionRequest, OAuthConstants.NAME);
			String description = ParamUtil.getString(
				actionRequest, OAuthConstants.DESCRIPTION);
			String website = ParamUtil.getString(
				actionRequest, OAuthConstants.WEBSITE);
			String callBackURL = ParamUtil.getString(
				actionRequest, OAuthConstants.CALLBACK_URL);

			try {
				Application application =
					ApplicationLocalServiceUtil.fetchApplication(applicationId);

				ServiceContext serviceContext =
						ServiceContextFactory.getInstance(actionRequest);

				application = ApplicationLocalServiceUtil
					.updateApplication(
						serviceContext.getUserId(), applicationId, name,
						description, website, callBackURL, serviceContext);

				actionRequest.setAttribute(OAuthConstants.BEAN_ID, application);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(actionRequest, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e);
				}
			}
		}
		else {
			SessionErrors.add(
				actionRequest,
				"can-not-complete-operation-without-application-id");
		}
	}

	protected void addTempImageFile(
			long userId, String tempImageFileName, String tempImageFolderName,
			String tempImageFilePath, InputStream inputStream)
		throws Exception {

		try {
			TempFileUtil.addTempFile(
				userId, tempImageFileName, tempImageFolderName, inputStream);
		}
		catch (DuplicateFileException dfe) {
			TempFileUtil.deleteTempFile(tempImageFilePath);

			TempFileUtil.addTempFile(
				userId, tempImageFileName, tempImageFolderName, inputStream);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected RenderedImage getCroppedRenderedImage(
		RenderedImage renderedImage, int height, int width, int x, int y) {

		Rectangle rectangle = new Rectangle(width, height);

		Rectangle croppedRectangle = rectangle.intersection(
			new Rectangle(renderedImage.getWidth(), renderedImage.getHeight()));

		BufferedImage bufferedImage = ImageToolUtil.getBufferedImage(
			renderedImage);

		return bufferedImage.getSubimage(
			x, y, croppedRectangle.width, croppedRectangle.height);
	}

	protected String getTempImageFileName(PortletRequest portletRequest) {
		long applicationId = ParamUtil.getLong(
			portletRequest, OAuthConstants.APPLICATION_ID);

		return String.valueOf(applicationId);
	}

	protected String getTempImageFilePath(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return TempFileUtil.getTempFileName(
			themeDisplay.getUserId(), getTempImageFileName(portletRequest),
			getTempImageFolderName());
	}

	protected String getTempImageFolderName() {
		Class<?> clazz = getClass();

		return clazz.getName();
	}

	protected InputStream getTempImageStream(String tempFilePath) {
		try {
			return TempFileUtil.getTempFileAsStream(tempFilePath);
		}
		catch (Exception e) {
			return null;
		}
	}

	protected void saveTempImageFile(long applicationId, byte[] bytes)
		throws Exception {

		ApplicationLocalServiceUtil.updateLogo(applicationId, bytes);
	}

	protected void serveTempImageFile(
		MimeResponse mimeResponse, InputStream tempImageStream)
		throws Exception {

		ImageBag imageBag = ImageToolUtil.read(tempImageStream);

		byte[] bytes = ImageToolUtil.getBytes(
			imageBag.getRenderedImage(), imageBag.getType());

		String contentType = MimeTypesUtil.getContentType(
			"A." + imageBag.getType());

		mimeResponse.setContentType(contentType);

		PortletResponseUtil.write(mimeResponse, bytes);
	}

	private static Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}