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

package com.liferay.tika.metadata;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xuggler.XugglerUtil;
import com.liferay.portlet.documentlibrary.util.AudioProcessorUtil;
import com.liferay.portlet.documentlibrary.util.VideoProcessorUtil;

import com.xuggle.xuggler.IContainer;

import java.io.File;
import java.io.InputStream;

import java.text.DecimalFormat;

import org.apache.tika.metadata.Metadata;

/**
 * @author Juan González
 * @author Alexander Chow
 */
public class XugglerRawMetadataProcessor extends BaseRawMetadataProcessor {

	@Override
	public void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception {

		return;
	}

	@Override
	public void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception {

		return;
	}

	protected String convertTime(long microseconds) {
		long milliseconds = microseconds / 1000L;

		StringBundler sb = new StringBundler(7);

		sb.append(_decimalFormatter.format(milliseconds / Time.HOUR));
		sb.append(StringPool.COLON);
		sb.append(
			_decimalFormatter.format(milliseconds % Time.HOUR / Time.MINUTE));
		sb.append(StringPool.COLON);
		sb.append(
			_decimalFormatter.format(milliseconds % Time.MINUTE / Time.SECOND));
		sb.append(StringPool.PERIOD);
		sb.append(_decimalFormatter.format(milliseconds % Time.SECOND / 10));

		return sb.toString();
	}

	protected Metadata extractMetadata(File file) throws Exception {
		IContainer container = IContainer.make();

		try {
			Metadata metadata = new Metadata();

			if (container.open(
					file.getCanonicalPath(), IContainer.Type.READ, null) < 0) {

				throw new IllegalArgumentException("Could not open stream");
			}

			if (container.queryStreamMetaData() < 0) {
				throw new IllegalStateException(
					"Could not query stream metadata");
			}

			long microseconds = container.getDuration();

			metadata.set(XMPDM.DURATION, convertTime(microseconds));

			return metadata;
		}
		finally {
			if (container.isOpened()) {
				container.close();
			}
		}
	}

	@Override
	@SuppressWarnings("unused")
	protected Metadata extractMetadata(
			String extension, String mimeType, File file)
		throws SystemException {

		Metadata metadata = null;

		if (!isSupported(mimeType)) {
			return metadata;
		}

		try {
			metadata = extractMetadata(file);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return metadata;
	}

	@Override
	@SuppressWarnings("unused")
	protected Metadata extractMetadata(
			String extension, String mimeType, InputStream inputStream)
		throws SystemException {

		Metadata metadata = null;

		File file = null;

		if (!isSupported(mimeType)) {
			return metadata;
		}

		try {
			file = FileUtil.createTempFile(extension);

			FileUtil.write(file, inputStream);

			metadata = extractMetadata(file);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			FileUtil.delete(file);
		}

		return metadata;
	}

	protected boolean isSupported(String mimeType) {
		if (XugglerUtil.isEnabled()) {
			if (AudioProcessorUtil.isAudioSupported(mimeType)) {
				return true;
			}

			if (VideoProcessorUtil.isVideoSupported(mimeType)) {
				return true;
			}
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(
		XugglerRawMetadataProcessor.class);

	private static DecimalFormat _decimalFormatter = new DecimalFormat("00");

}