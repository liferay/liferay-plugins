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
import com.liferay.knowledgebase.admin.importer.util.KBArticleMarkdownConverter;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.PortletPropsValues;
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
import java.util.HashMap;
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
			long userId, long groupId, InputStream inputStream,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		if (inputStream == null) {
			throw new KBArticleImportException("Input stream is null");
		}

		try {
			ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(
				inputStream);

			processKBArticleFiles(userId, groupId, zipReader, serviceContext);
		}
		catch (IOException ioe) {
			throw new KBArticleImportException(ioe);
		}
	}

	protected KBArticle addKBArticleMarkdown(
			long userId, long groupId, long parentResourcePrimaryKey,
			String markdown, String fileEntryName, ZipReader zipReader,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		if (Validator.isNull(markdown)) {
			throw new KBArticleImportException(
				"Markdown is null for file entry " + fileEntryName);
		}

		KBArticleMarkdownConverter kbArticleMarkdownConverter =
			new KBArticleMarkdownConverter(markdown);

		String urlTitle = kbArticleMarkdownConverter.getUrlTitle();

		KBArticle kbArticle =
			KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				groupId, urlTitle);

		try {
			if (kbArticle == null) {
				kbArticle = KBArticleLocalServiceUtil.addKBArticle(
					userId, parentResourcePrimaryKey,
					kbArticleMarkdownConverter.getTitle(), urlTitle, markdown,
					null, null, null, serviceContext);
			}
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(4);

			sb.append("Unable to add basic KB article for file entry ");
			sb.append(fileEntryName);
			sb.append(": ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString(), e);
		}

		try {
			String html =
				kbArticleMarkdownConverter.processAttachmentsReferences(
					userId, kbArticle, zipReader,
					new HashMap<String, FileEntry>());

			return KBArticleLocalServiceUtil.updateKBArticle(
				userId, kbArticle.getResourcePrimKey(),
				kbArticleMarkdownConverter.getTitle(), html,
				kbArticle.getDescription(), null, null, null, serviceContext);
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(4);

			sb.append("Unable to update KB article for file entry ");
			sb.append(fileEntryName);
			sb.append(": ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString(), e);
		}
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
				PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME, zipReader,
				serviceContext);

			parentResourcePrimKey = parentKBArticle.getResourcePrimKey();
		}

		processSectionKBArticleFiles(
			userId, groupId, parentResourcePrimKey, zipReader,
			getFolderNameFileEntryNamesMap(zipReader), serviceContext);
	}

	protected void processSectionKBArticleFiles(
			long userId, long groupId, long parentResourcePrimaryKey,
			ZipReader zipReader,
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
				sectionIntroFileEntryName, zipReader, serviceContext);

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
					sectionMarkdown, sectionFileEntryName, zipReader,
					serviceContext);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleImporter.class);

}