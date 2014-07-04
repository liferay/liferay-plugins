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

package com.liferay.knowledgebase.admin.importer;

import com.liferay.knowledgebase.KBArticleImportException;
import com.liferay.knowledgebase.admin.importer.util.KBArticleImporterUtil;
import com.liferay.knowledgebase.admin.importer.util.KBArticleMarkdownConverter;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author James Hinkey
 * @author Sergio González
 */
public class KBArticleImporter {

	public void processZipFile(
			long userId, long groupId, String fileName, InputStream inputStream,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		if (inputStream == null) {
			throw new KBArticleImportException("Input stream is null");
		}

		try {
			ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(
				inputStream);

			KBArticleImporterUtil.processImageFiles(
				groupId, fileName, zipReader, fileEntriesMap, serviceContext);

			processKBArticleFiles(
				userId, groupId, zipReader, fileEntriesMap, serviceContext);
		}
		catch (IOException ioe) {
			throw new KBArticleImportException(ioe);
		}
	}

	protected KBArticle addKBArticleMarkdown(
			long userId, long groupId, long parentResourcePrimaryKey,
			String markdown, String fileEntryName,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		if (Validator.isNull(markdown)) {
			throw new KBArticleImportException(
				"Markdown is null for file entry " + fileEntryName);
		}

		KBArticleMarkdownConverter kbArticleMarkdownConverter =
			new KBArticleMarkdownConverter(markdown, fileEntriesMap);

		try {
			return applyContentToKBArticle(
				userId, groupId, parentResourcePrimaryKey,
				kbArticleMarkdownConverter.getTitle(),
				kbArticleMarkdownConverter.getUrlTitle(),
				kbArticleMarkdownConverter.getHtml(), serviceContext);
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(4);

			sb.append("Unable to add KB article for file entry ");
			sb.append(fileEntryName);
			sb.append(": ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString(), e);
		}
	}

	protected KBArticle applyContentToKBArticle(
			long userId, long groupId, long parentResourcePrimaryKey,
			String title, String urlTitle, String html,
			ServiceContext serviceContext)
		throws PortalException {

		KBArticle kbArticle =
			KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				groupId, urlTitle);

		if (kbArticle != null) {
			kbArticle = KBArticleLocalServiceUtil.updateKBArticle(
				userId, kbArticle.getResourcePrimKey(), title, html,
				kbArticle.getDescription(), null, null, serviceContext);
		}
		else {
			kbArticle = KBArticleLocalServiceUtil.addKBArticle(
				userId, parentResourcePrimaryKey, title, urlTitle, html, null,
				null, null, serviceContext);
		}

		return kbArticle;
	}

	protected Map<String, List<String>> getFolderNameFileEntryNamesMap(
		ZipReader zipReader) {

		Map<String, List<String>> folderNameFileEntryNamesMap =
			new TreeMap<String, List<String>>();

		for (String zipEntry : zipReader.getEntries()) {
			String extension = FileUtil.getExtension(zipEntry);

			if (!ArrayUtil.contains(
					PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_EXTENSIONS,
					StringPool.PERIOD.concat(extension)) ||
				zipEntry.equals(
					PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME)) {

				continue;
			}

			String folderName = zipEntry.substring(
				0, zipEntry.lastIndexOf(StringPool.SLASH));

			List<String> fileEntryNames = folderNameFileEntryNamesMap.get(
				folderName);

			if (fileEntryNames == null) {
				fileEntryNames = new ArrayList<String>();
			}

			fileEntryNames.add(zipEntry);

			folderNameFileEntryNamesMap.put(folderName, fileEntryNames);
		}

		return folderNameFileEntryNamesMap;
	}

	protected void processKBArticleFiles(
			long userId, long groupId, ZipReader zipReader,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		long parentResourcePrimKey =
			KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY;

		String markdown = zipReader.getEntryAsString(
			PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME);

		if (Validator.isNotNull(markdown)) {
			KBArticle parentKBArticle = addKBArticleMarkdown(
				userId, groupId,
				KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, markdown,
				PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME,
				fileEntriesMap, serviceContext);

			parentResourcePrimKey = parentKBArticle.getResourcePrimKey();
		}

		processSectionKBArticleFiles(
			userId, groupId, parentResourcePrimKey, zipReader, fileEntriesMap,
			getFolderNameFileEntryNamesMap(zipReader), serviceContext);
	}

	protected void processSectionKBArticleFiles(
			long userId, long groupId, long parentResourcePrimaryKey,
			ZipReader zipReader, Map<String, FileEntry> fileEntriesMap,
			Map<String, List<String>> folderNameFileEntryNamesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		Set<String> folderNames = folderNameFileEntryNamesMap.keySet();

		for (String folderName : folderNames) {
			List<String> fileEntryNames = folderNameFileEntryNamesMap.get(
				folderName);

			String sectionIntroFileEntryName = null;

			List<String> sectionFileEntryNames = new ArrayList<String>();

			for (String fileEntryName : fileEntryNames) {
				if (fileEntryName.endsWith(
						PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_INTRO)) {

					sectionIntroFileEntryName = fileEntryName;
				}
				else {
					sectionFileEntryNames.add(fileEntryName);
				}
			}

			if (Validator.isNull(sectionIntroFileEntryName) &&
				!sectionFileEntryNames.isEmpty()) {

				StringBundler sb = new StringBundler(4);

				sb.append("No file entry ending in ");
				sb.append(PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_INTRO);
				sb.append(" accompanies file entry ");
				sb.append(sectionFileEntryNames.get(0));

				throw new KBArticleImportException(sb.toString());
			}

			KBArticle sectionIntroKBArticle = addKBArticleMarkdown(
				userId, groupId, parentResourcePrimaryKey,
				zipReader.getEntryAsString(sectionIntroFileEntryName),
				sectionIntroFileEntryName, fileEntriesMap, serviceContext);

			for (String sectionFileEntryName : sectionFileEntryNames) {
				String sectionMarkdown = zipReader.getEntryAsString(
					sectionFileEntryName);

				if (Validator.isNull(sectionMarkdown)) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Missing Markdown in file entry " +
								sectionFileEntryName);
					}
				}

				addKBArticleMarkdown(
					userId, groupId, sectionIntroKBArticle.getResourcePrimKey(),
					sectionMarkdown, sectionFileEntryName, fileEntriesMap,
					serviceContext);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleImporter.class);

}