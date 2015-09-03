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

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.metadata.RawMetadataProcessorUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.util.portlet.PortletProps;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetImageUtil {

	public static JSONObject addImageFile(
			long userId, File file, String imageType)
		throws PortalException, SystemException {

		try {
			AssetEntrySetImageUtil.rotateImage(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		FileEntry fileEntry = addFileEntry(userId, file, imageType);

		return getImageJSONObject(
			JSONFactoryUtil.createJSONObject(), fileEntry, imageType);
	}

	public static FileEntry addScaledImageFileEntry(
			long userId, ImageBag imageBag, String imageType)
		throws PortalException, SystemException {

		File scaledFile = null;

		RenderedImage rawRenderedImage = imageBag.getRenderedImage();

		String imageMaxSize = PortletProps.get(
			PortletPropsKeys.ASSET_ENTRY_SET_IMAGE_TYPE, new Filter(imageType));

		String[] maxDimensions = imageMaxSize.split("x");

		RenderedImage scaledRenderedImage = ImageToolUtil.scale(
			rawRenderedImage, GetterUtil.getInteger(maxDimensions[0]),
			GetterUtil.getInteger(maxDimensions[1]));

		try {
			scaledFile = FileUtil.createTempFile(
				ImageToolUtil.getBytes(
					scaledRenderedImage, imageBag.getType()));

			return addFileEntry(userId, scaledFile, imageType);
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

		try {
			Image image = ImageToolUtil.getImage(fileEntry.getContentStream());

			imageJSONObject.put("height_" + imageType, image.getHeight());
			imageJSONObject.put("width_" + imageType, image.getWidth());
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		imageJSONObject.put(
			"imageURL_" + imageType,
			DLUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, true));
		imageJSONObject.put("mimeType", fileEntry.getMimeType());
		imageJSONObject.put("name", fileEntry.getTitle());

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
		}
		else if (orientation == _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_270_CW) {
			affineTransform.rotate(-Math.toRadians(90));

			affineTransform.scale(-1.0, 1.0);
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
		}
		else if (orientation == _ORIENTATION_ROTATE_180) {
			affineTransform.translate(
				renderedImage.getWidth(), renderedImage.getHeight());

			affineTransform.rotate(Math.toRadians(180));
		}
		else if (orientation == _ORIENTATION_ROTATE_270_CW) {
			affineTransform.translate(0, renderedImage.getWidth());

			affineTransform.rotate(Math.toRadians(270));
		}
		else {
			return;
		}

		AffineTransformOp affineTransformOp = new AffineTransformOp(
			affineTransform, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage oldBufferedImage = ImageToolUtil.getBufferedImage(
			renderedImage);

		BufferedImage newBufferedImage = affineTransformOp.filter(
			oldBufferedImage,
			new BufferedImage(
				oldBufferedImage.getHeight(), oldBufferedImage.getWidth(),
				oldBufferedImage.getType()));

		ImageIO.write(newBufferedImage, imageBag.getType(), file);
	}

	protected static FileEntry addFileEntry(long userId, File file, String type)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		String fileName =
			Calendar.getInstance().getTimeInMillis() + type + file.getName();

		return PortletFileRepositoryUtil.addPortletFileEntry(
			user.getGroupId(), userId, AssetEntrySet.class.getName(), 0L,
			PortletKeys.ASSET_ENTRY_SET, 0L, file, fileName, null, false);
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