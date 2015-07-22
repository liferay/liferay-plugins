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

import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.metadata.RawMetadataProcessorUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetImageUtil {

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

	private static final int _ORIENTATION_MIRROR_HORIZONTAL = 2;

	private static final int _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_90_CW = 7;

	private static final int _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_270_CW = 5;

	private static final int _ORIENTATION_MIRROR_VERTICAL = 4;

	private static final int _ORIENTATION_NORMAL = 1;

	private static final int _ORIENTATION_ROTATE_90_CW = 6;

	private static final int _ORIENTATION_ROTATE_180 = 3;

	private static final int _ORIENTATION_ROTATE_270_CW = 8;

}