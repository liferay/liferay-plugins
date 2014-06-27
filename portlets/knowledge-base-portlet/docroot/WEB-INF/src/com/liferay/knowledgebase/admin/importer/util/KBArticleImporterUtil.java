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

package com.liferay.knowledgebase.admin.importer.util;

import com.liferay.knowledgebase.KBArticleImportException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.service.ServiceContext;

import java.io.File;
import java.io.IOException;

import java.util.Map;

/**
 * @author James Hinkey
 */
public class KBArticleImporterUtil {

	/**
	 * Returns a file entry for the first <code>img</code> tag parsed from the
	 * specified HTML text.
	 *
	 * <p>
	 * The corresponding file entry must be imported into the document library
	 * before calling this method
	 * </p>
	 *
	 * @param  html the HTML text from which to extract an image file entry
	 * @return a file entry for the first <code>img</code> tag parsed from the
	 *         specified HTML text
	 */
	public static FileEntry extractImageFileEntry(
		String html, Map<String, FileEntry> fileEntriesMap) {

		String imageSource = null;

		String[] lines = StringUtil.split(html, "\"");

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].endsWith("src=")) {
				if ((i + 1) < lines.length) {
					imageSource = lines[i + 1];
				}

				break;
			}
		}

		if (Validator.isNull(imageSource)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Missing src attribute for image " + html);
			}

			return null;
		}

		String[] paths = StringUtil.split(imageSource, StringPool.SLASH);

		if (paths.length < 0) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Expected image file path to contain a slash " + html);
			}

			return null;
		}

		String name = paths[paths.length - 1];

		return fileEntriesMap.get(name);
	}

	/**
	 * Processes the ZIP file's image files, adding them to the document
	 * library.
	 *
	 * @param fileName
	 * @param zipReader a zip reader containing a folder of image files
	 * @param fileEntriesMap
	 * @param serviceContext
	 */
	public static void processImageFiles(
			String fileName, ZipReader zipReader,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws IOException, KBArticleImportException {

		if (fileName.contains(StringPool.PERIOD)) {
			fileName = fileName.substring(
				0, fileName.lastIndexOf(StringPool.PERIOD));
		}

		File tempDir = new File(_TMP_DIR);

		tempDir.mkdir();

		String dirPath = _TMP_DIR + StringPool.SLASH + fileName;

		File fileTempDir = new File(dirPath);
		File fileImagesTempDir = new File(dirPath + StringPool.SLASH + _IMAGES);

		fileTempDir.mkdir();
		fileImagesTempDir.mkdir();

		String namespaceFolderName = StringUtil.toLowerCase(fileName);

		Folder imagesFolder = null;

		try {
			KBArticleDLUtil.deleteFolder(namespaceFolderName, serviceContext);

			imagesFolder = KBArticleDLUtil.addFolder(
				namespaceFolderName, serviceContext);
		}
		catch (Exception e) {
			throw new KBArticleImportException(e);
		}

		for (String zipEntry : zipReader.getEntries()) {
			if (!zipEntry.contains(_IMAGES)) {
				continue;
			}

			String zipEntryLower = StringUtil.toLowerCase(zipEntry);

			if (!zipEntryLower.endsWith(".bmp") &&
				!zipEntryLower.endsWith(".gif") &&
				!zipEntryLower.endsWith(".jpeg") &&
				!zipEntryLower.endsWith(".jpg") &&
				!zipEntryLower.endsWith(".png")) {

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unsupported image file suffix used in ZIP file: " +
							zipEntry);
				}

				continue;
			}

			File picturesFile = new File(dirPath + StringPool.SLASH + zipEntry);

			if (picturesFile.isDirectory()) {
				continue;
			}

			try {
				FileUtil.write(
					picturesFile, zipReader.getEntryAsInputStream(zipEntry));

				KBArticleDLUtil.addFile(
					imagesFolder, picturesFile, fileEntriesMap, serviceContext);
			}
			catch (Exception e) {
				StringBuffer sb = new StringBuffer(
					"Unable to import image file: ");

				sb.append(zipEntry);
				sb.append(". ");
				sb.append(e.getLocalizedMessage());

				throw new KBArticleImportException(sb.toString());
			}
		}

		fileImagesTempDir.delete();
		fileTempDir.delete();

		tempDir.delete();
	}

	private static final String _IMAGES = "images";

	private static final String _TMP_DIR =
		SystemProperties.get(SystemProperties.TMP_DIR) + "/kb";

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleImporterUtil.class);

}