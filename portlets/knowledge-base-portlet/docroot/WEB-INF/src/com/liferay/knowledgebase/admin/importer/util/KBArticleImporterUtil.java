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
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.service.ServiceContext;

import java.util.Map;

/**
 * @author James Hinkey
 * @author Sergio González
 */
public class KBArticleImporterUtil {

	public static FileEntry extractImageFileEntry(
		String html, Map<String, FileEntry> fileEntriesMap) {

		String imageSrc = null;

		String[] lines = StringUtil.split(html, StringPool.QUOTE);

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].endsWith("src=")) {
				if ((i + 1) < lines.length) {
					imageSrc = lines[i + 1];
				}

				break;
			}
		}

		if (Validator.isNull(imageSrc)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Missing src attribute for image " + html);
			}

			return null;
		}

		String[] paths = StringUtil.split(imageSrc, StringPool.SLASH);

		if (paths.length < 1) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Expected image file path to contain a slash " + html);
			}

			return null;
		}

		String name = paths[paths.length - 1];

		return fileEntriesMap.get(name);
	}

	public static void processImageFiles(
			long groupId, String fileName, ZipReader zipReader,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		String folderName = FileUtil.stripExtension(fileName);

		folderName = StringUtil.toLowerCase(folderName);

		Folder folder = null;

		try {
			KBArticleDLUtil.deleteFolder(groupId, folderName);

			folder = KBArticleDLUtil.addFolder(
				groupId, folderName, serviceContext);
		}
		catch (Exception e) {
			throw new KBArticleImportException(e);
		}

		for (String zipEntry : zipReader.getEntries()) {
			if (!zipEntry.contains(
					PortletPropsValues.MARKDOWN_IMPORTER_IMAGE_FOLDER)) {

				continue;
			}

			String zipEntryLower = StringUtil.toLowerCase(zipEntry);

			try {
				validateImageFileExtension(zipEntryLower);
			}
			catch (KBArticleImportException kbaie) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unsupported image file suffix used in ZIP file: " +
							zipEntry);
				}

				continue;
			}

			try {
				int pos = zipEntry.lastIndexOf(StringPool.SLASH);

				KBArticleDLUtil.addFile(
					folder, zipEntry.substring(pos + 1),
					zipReader.getEntryAsInputStream(zipEntry), fileEntriesMap,
					serviceContext);
			}
			catch (Exception e) {
				StringBuilder sb = new StringBuilder(4);

				sb.append("Unable to import image file: ");
				sb.append(zipEntry);
				sb.append(". ");
				sb.append(e.getLocalizedMessage());

				throw new KBArticleImportException(sb.toString());
			}
		}
	}

	public static void validateImageFileExtension(String fileName)
		throws KBArticleImportException {

		boolean validImageFileExtension = false;

		for (String fileExtension :
				PortletPropsValues.MARKDOWN_IMPORTER_IMAGE_FILE_EXTENSIONS) {

			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(fileName, fileExtension)) {

				validImageFileExtension = true;

				break;
			}
		}

		if (!validImageFileExtension) {
			throw new KBArticleImportException(fileName);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleImporterUtil.class);

}