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
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import java.io.InputStream;

import java.util.Map;

/**
 * @author James Hinkey
 * @author Sergio González
 */
public class KBArticleImporterUtil {

	public static FileEntry addImageFileEntry(
			String imageFileName, long userId, KBArticle kbArticle,
			ZipReader zipReader, Map<String, FileEntry> fileEntriesMap)
		throws PortalException {

		try {
			validateImageFileExtension(imageFileName);
		}
		catch (KBArticleImportException kbaie) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unsupported image file suffix used in ZIP file " +
						imageFileName);
			}
		}

		try {
			return addImageFileEntry(
				userId, kbArticle, imageFileName,
				zipReader.getEntryAsInputStream(
					PortletPropsValues.MARKDOWN_IMPORTER_IMAGE_FOLDER +
						imageFileName),
				fileEntriesMap);
		}
		catch (Exception e) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Unable to import image file ");
			sb.append(imageFileName);
			sb.append(": ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString());
		}
	}

	public static String extractImageFileName(String html) {
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

		return paths[paths.length - 1];
	}

	public static void validateImageFileExtension(String imageFileName)
		throws KBArticleImportException {

		boolean validImageFileExtension = false;

		for (String fileExtension :
				PortletPropsValues.MARKDOWN_IMPORTER_IMAGE_FILE_EXTENSIONS) {

			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(imageFileName, fileExtension)) {

				validImageFileExtension = true;

				break;
			}
		}

		if (!validImageFileExtension) {
			throw new KBArticleImportException(imageFileName);
		}
	}

	protected static FileEntry addImageFileEntry(
			long userId, KBArticle kbArticle, String imageFileName,
			InputStream inputStream, Map<String, FileEntry> fileEntriesMap)
		throws PortalException, SystemException {

		FileEntry fileEntry = fileEntriesMap.get(imageFileName);

		if (fileEntry != null) {
			return fileEntry;
		}

		String mimeType = MimeTypesUtil.getContentType(imageFileName);

		try {
			PortletFileRepositoryUtil.getPortletFileEntry(
				kbArticle.getGroupId(), kbArticle.getAttachmentsFolderId(),
				imageFileName);

			PortletFileRepositoryUtil.deletePortletFileEntry(
				kbArticle.getGroupId(), kbArticle.getAttachmentsFolderId(),
				imageFileName);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		fileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			kbArticle.getGroupId(), userId, KBArticle.class.getName(),
			kbArticle.getClassPK(), PortletKeys.KNOWLEDGE_BASE_ARTICLE,
			kbArticle.getAttachmentsFolderId(), inputStream, imageFileName,
			mimeType, false);

		fileEntriesMap.put(imageFileName, fileEntry);

		return fileEntry;
	}

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleImporterUtil.class);

}