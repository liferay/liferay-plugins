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

package com.liferay.asset.entry.set.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.metadata.RawMetadataProcessorUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetImageUtil {

	public static JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file)
		throws PortalException, SystemException {

		try {
			rotateImage(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		FileEntry fileEntry = addFileEntry(
			userId, classNameId, classPK, portletId, file,
			AssetEntrySetConstants.IMAGE_TYPE_RAW, file.getName());

		return getImageJSONObject(
			JSONFactoryUtil.createJSONObject(), fileEntry,
			AssetEntrySetConstants.IMAGE_TYPE_RAW);
	}

	public static JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file, Map<String, String> imageMaxSizes)
		throws PortalException, SystemException {

		FileEntry rawFileEntry = null;

		ImageBag imageBag = null;

		try {
			rotateImage(file);

			rawFileEntry = addFileEntry(
				userId, classNameId, classPK, portletId, file,
				AssetEntrySetConstants.IMAGE_TYPE_RAW, file.getName());

			imageBag = ImageToolUtil.read(rawFileEntry.getContentStream());
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		JSONObject imageJSONObject = JSONFactoryUtil.createJSONObject();

		for (String imageType : imageMaxSizes.keySet()) {
			FileEntry fileEntry = rawFileEntry;

			if (imageType.equals(AssetEntrySetConstants.IMAGE_TYPE_FULL) &&
				Validator.equals(
					MimeTypesUtil.getContentType(file),
					ContentTypes.IMAGE_GIF)) {

				fileEntry = addFileEntry(
					userId, classNameId, classPK, portletId, file, imageType,
					file.getName());
			}
			else if (!imageType.equals(AssetEntrySetConstants.IMAGE_TYPE_RAW)) {
				fileEntry = addScaledImageFileEntry(
					userId, classNameId, 0L, portletId, imageBag, imageType,
					file.getName(), imageMaxSizes.get(imageType));
			}

			imageJSONObject = getImageJSONObject(
				imageJSONObject, fileEntry, imageType);
		}

		return imageJSONObject;
	}

	public static FileEntry addScaledImageFileEntry(
			long userId, long classNameId, long classPK, String portletId,
			ImageBag imageBag, String imageType, String originalFileName,
			String imageMaxSize)
		throws PortalException, SystemException {

		File scaledFile = null;

		String[] maxDimensions = imageMaxSize.split("x");

		RenderedImage scaledRenderedImage = ImageToolUtil.scale(
			imageBag.getRenderedImage(),
			GetterUtil.getInteger(maxDimensions[0]),
			GetterUtil.getInteger(maxDimensions[1]));

		try {
			scaledFile = FileUtil.createTempFile(
				ImageToolUtil.getBytes(
					scaledRenderedImage, imageBag.getType()));

			return addFileEntry(
				userId, classNameId, classPK, portletId, scaledFile, imageType,
				originalFileName);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			FileUtil.delete(scaledFile);
		}
	}

	public static JSONObject getImageJSONObject(
			JSONObject imageJSONObject, FileEntry fileEntry, String imageType)
		throws PortalException, SystemException {

		JSONObject fileEntryIdsJSONObject = imageJSONObject.getJSONObject(
			"fileEntryIds");

		if (fileEntryIdsJSONObject == null) {
			fileEntryIdsJSONObject = JSONFactoryUtil.createJSONObject();
		}

		fileEntryIdsJSONObject.put(imageType, fileEntry.getFileEntryId());

		imageJSONObject.put("fileEntryIds", fileEntryIdsJSONObject);

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(
			fileEntry.getFileEntryId());

		UnicodeProperties extraSettingsProperties =
			dlFileEntry.getExtraSettingsProperties();

		JSONObject fileEntryImageJSONObject =
			JSONFactoryUtil.createJSONObject(
				extraSettingsProperties.getProperty(
					"fileEntryImageJSONObject"));

		Iterator<String> iterator = fileEntryImageJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			imageJSONObject.put(key, fileEntryImageJSONObject.getString(key));
		}

		return imageJSONObject;
	}

	public static int getOrientation(File file) {
		Map<String, Fields> fieldsMap = null;

		try {
			fieldsMap = RawMetadataProcessorUtil.getRawMetadataMap(
				FileUtil.getExtension(file.getName()),
				MimeTypesUtil.getContentType(file), file);
		}
		catch (Exception e) {
			return _ORIENTATION_NORMAL;
		}

		Fields rawMetadataFields = fieldsMap.get("TIKARAWMETADATA");

		if (rawMetadataFields == null) {
			return _ORIENTATION_NORMAL;
		}

		Field orientationField = rawMetadataFields.get("TIFF_ORIENTATION");

		if (orientationField == null) {
			return _ORIENTATION_NORMAL;
		}

		return GetterUtil.getInteger(orientationField.getValue());
	}

	public static void rotateImage(File file) throws IOException {
		AffineTransform affineTransform = new AffineTransform();

		ImageBag imageBag = ImageToolUtil.read(file);

		RenderedImage renderedImage = imageBag.getRenderedImage();

		boolean reverseDimensions = false;

		int orientation = getOrientation(file);

		if (orientation == _ORIENTATION_MIRROR_HORIZONTAL) {
			affineTransform.scale(-1.0, 1.0);

			affineTransform.translate(-renderedImage.getWidth(), 0);
		}
		else if (orientation == _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_90_CW) {
			affineTransform.scale(-1.0, 1.0);

			affineTransform.translate(-renderedImage.getHeight(), 0);

			affineTransform.translate(0, renderedImage.getWidth());

			affineTransform.rotate(Math.toRadians(270));

			reverseDimensions = true;
		}
		else if (orientation == _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_270_CW) {
			affineTransform.rotate(-Math.toRadians(90));

			affineTransform.scale(-1.0, 1.0);

			reverseDimensions = true;
		}
		else if (orientation == _ORIENTATION_MIRROR_VERTICAL) {
			affineTransform.scale(1.0, -1.0);

			affineTransform.translate(0, -renderedImage.getHeight());
		}
		else if (orientation == _ORIENTATION_NORMAL) {
			return;
		}
		else if (orientation == _ORIENTATION_ROTATE_90_CW) {
			affineTransform.translate(renderedImage.getHeight(), 0);

			affineTransform.rotate(Math.toRadians(90));

			reverseDimensions = true;
		}
		else if (orientation == _ORIENTATION_ROTATE_180) {
			affineTransform.translate(
				renderedImage.getWidth(), renderedImage.getHeight());

			affineTransform.rotate(Math.toRadians(180));
		}
		else if (orientation == _ORIENTATION_ROTATE_270_CW) {
			affineTransform.translate(0, renderedImage.getWidth());

			affineTransform.rotate(Math.toRadians(270));

			reverseDimensions = true;
		}
		else {
			return;
		}

		AffineTransformOp affineTransformOp = new AffineTransformOp(
			affineTransform, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage oldBufferedImage = ImageToolUtil.getBufferedImage(
			renderedImage);

		int height = oldBufferedImage.getHeight();
		int width = oldBufferedImage.getWidth();

		if (reverseDimensions) {
			height = oldBufferedImage.getWidth();
			width = oldBufferedImage.getHeight();
		}

		BufferedImage newBufferedImage = affineTransformOp.filter(
			oldBufferedImage,
			new BufferedImage(width, height, oldBufferedImage.getType()));

		ImageIO.write(newBufferedImage, imageBag.getType(), file);
	}

	protected static FileEntry addFileEntry(
			long userId, long classNameId, long classPK, String portletId,
			File file, String imageType, String originalFileName)
		throws PortalException, SystemException {

		long groupId = 0;

		User user = UserLocalServiceUtil.getUser(userId);

		if (user.isDefaultUser()) {
			Group group = GroupLocalServiceUtil.getGroup(
				user.getCompanyId(), GroupConstants.GUEST);

			groupId = group.getGroupId();
		}
		else {
			groupId = user.getGroupId();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			groupId, portletId, serviceContext);

		serviceContext.setAttribute(
			"className", PortalUtil.getClassName(classNameId));
		serviceContext.setAttribute("classPK", String.valueOf(classPK));

		String fileName =
			System.currentTimeMillis() + imageType + originalFileName;

		String contentType = MimeTypesUtil.getContentType(fileName);

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			repository.getRepositoryId(), 0L, fileName, contentType, fileName,
			originalFileName, StringPool.BLANK, file, serviceContext);

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(
			fileEntry.getFileEntryId());

		UnicodeProperties extraSettingsProperties =
			dlFileEntry.getExtraSettingsProperties();

		JSONObject fileEntryImageJSONObject =
			JSONFactoryUtil.createJSONObject();

		try {
			Image image = ImageToolUtil.getImage(
				DLFileEntryLocalServiceUtil.getFileAsStream(
					fileEntry.getFileEntryId(), fileEntry.getVersion(), false));

			fileEntryImageJSONObject.put(
				"height_" + imageType, image.getHeight());
			fileEntryImageJSONObject.put(
				"width_" + imageType, image.getWidth());
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		fileEntryImageJSONObject.put(
			"imageURL_" + imageType,
			DLUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, true));
		fileEntryImageJSONObject.put("mimeType", fileEntry.getMimeType());
		fileEntryImageJSONObject.put("name", fileEntry.getTitle());

		extraSettingsProperties.put(
			"fileEntryImageJSONObject", fileEntryImageJSONObject.toString());

		DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);

		return fileEntry;
	}

	private static final int _ORIENTATION_MIRROR_HORIZONTAL = 2;

	private static final int _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_90_CW = 7;

	private static final int _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_270_CW = 5;

	private static final int _ORIENTATION_MIRROR_VERTICAL = 4;

	private static final int _ORIENTATION_NORMAL = 1;

	private static final int _ORIENTATION_ROTATE_90_CW = 6;

	private static final int _ORIENTATION_ROTATE_180 = 3;

	private static final int _ORIENTATION_ROTATE_270_CW = 8;

}