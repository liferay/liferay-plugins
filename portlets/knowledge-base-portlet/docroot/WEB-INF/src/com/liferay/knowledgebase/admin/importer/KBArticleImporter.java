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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author James Hinkey
 */
public class KBArticleImporter {

	/**
	 * Processes the ZIP file's content and pictures, importing them as kb
	 * articles and document library files respectively.
	 *
	 * @param userId
	 * @param groupId
	 * @param fileName
	 * @param inputStream a inputStream containing a folder of image files to
*        add to the document library and folders of Markdown files to be
*        processed into a hierarchy of kb articles
	 * @param serviceContext
	 */
	public void processZipFile(
			long userId, long groupId, String fileName, InputStream inputStream,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws IOException, KBArticleImportException {

		if (inputStream == null) {
			throw new KBArticleImportException("Null import file");
		}

		ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(inputStream);

		KBArticleImporterUtil.processImageFiles(
			groupId, fileName, zipReader, fileEntriesMap, serviceContext);

		processArticleFiles(
			userId, groupId, zipReader, fileEntriesMap, serviceContext);
	}

	protected KBArticle applyContentToKBArticle(
			long userId, long groupId, long parentResourcePrimaryKey,
			String title, String urlTitle, String html,
			ServiceContext serviceContext)
		throws PortalException {

		KBArticle article = KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
			groupId, urlTitle);

		if (article != null) {
			article = KBArticleLocalServiceUtil.updateKBArticle(
				userId, article.getResourcePrimKey(), title, html,
				article.getDescription(), null, null, serviceContext);
		}
		else {
			article = KBArticleLocalServiceUtil.addKBArticle(
				userId, parentResourcePrimaryKey, title, urlTitle, html, null,
				null, null, serviceContext);
		}

		return article;
	}

	protected KBArticle addKBArticleMarkdown(
			long userId, long groupId, long parentResourcePrimaryKey,
			String markdown, String fileEntry,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		if (Validator.isNull(markdown)) {
			throw new KBArticleImportException(
				"Null or empty Markdown in file entry: " + fileEntry);
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
			StringBuilder sb = new StringBuilder(4);

			sb.append("Unable to create KBArticle for file entry: ");
			sb.append(fileEntry);
			sb.append(". ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString(), e);
		}
	}

	protected void processArticleFiles(
			long userId, long groupId, ZipReader zipReader,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws IOException, KBArticleImportException {

		// Create map of the ZIP files folders to Markdown files, extracting the
		// root home page Markdown file along the way.

		String homeMarkdown = zipReader.getEntryAsString(
			PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME);

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

			List<String> fileEntries = _folderFileEntryMap.get(folderName);

			if (fileEntries == null) {
				fileEntries = new ArrayList<String>();
			}

			fileEntries.add(zipEntry);

			_folderFileEntryMap.put(folderName, fileEntries);
		}

		if (Validator.isNull(homeMarkdown)) {
			throw new KBArticleImportException(
				"Missing file entry: " +
					PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME);
		}

		KBArticle homeKBArticle = addKBArticleMarkdown(
			userId, groupId,
			KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
			homeMarkdown, PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_HOME,
			fileEntriesMap, serviceContext);

		// Create kb articles for each chapter home Markdown file and each
		// chapter's tutorial Markdown files.

		Set<String> folders = _folderFileEntryMap.keySet();

		Iterator<String> folderIter = folders.iterator();
		while (folderIter.hasNext()) {
			String folder = folderIter.next();

			List<String> zipFileEntries = _folderFileEntryMap.get(folder);

			String chapterHomeFileEntry = null;

			List<String> chapterMarkdownFileEntries = new ArrayList<String>();

			for (String fileEntry : zipFileEntries) {
				if (fileEntry.endsWith(
						PortletPropsValues.
							MARKDOWN_IMPORTER_ARTICLE_INTRODUCTION) ||
					fileEntry.endsWith(
						PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_INTRO)) {

					chapterHomeFileEntry = fileEntry;
				}
				else {
					chapterMarkdownFileEntries.add(fileEntry);
				}
			}

			if (Validator.isNull(chapterHomeFileEntry)) {
				throw new KBArticleImportException(
					"Missing intro file entry in folder: " + folder);
			}

			String chapterIntroMarkdown = zipReader.getEntryAsString(
				chapterHomeFileEntry);

			KBArticle chapterIntroKBArticle = addKBArticleMarkdown(
				userId, groupId, homeKBArticle.getResourcePrimKey(),
				chapterIntroMarkdown, chapterHomeFileEntry, fileEntriesMap,
				serviceContext);

			for (String tutorialFileEntry : chapterMarkdownFileEntries) {
				String tutorialMarkdown = zipReader.getEntryAsString(
					tutorialFileEntry);

				if (Validator.isNotNull(tutorialMarkdown)) {
					addKBArticleMarkdown(
						userId, groupId,
						chapterIntroKBArticle.getResourcePrimKey(),
						tutorialMarkdown, tutorialFileEntry, fileEntriesMap,
						serviceContext);
				}
				else {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Missing Markdown in file entry: " +
								tutorialFileEntry);
					}
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleImporter.class);

	private Map<String, List<String>> _folderFileEntryMap =
		new TreeMap<String, List<String>>();

}