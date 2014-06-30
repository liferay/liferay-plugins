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
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.markdown.converter.MarkdownConverter;
import com.liferay.markdown.converter.factory.MarkdownConverterFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author James Hinkey
 */
public class KBArticleHierarchyImporter {

	public KBArticleHierarchyImporter() {
		super();
	}

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

	protected KBArticle createKBArticleFromMarkdown(
			long userId, long groupId, long parentResourcePrimaryKey,
			String markdown, String fileEntry,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws KBArticleImportException {

		if (Validator.isNull(markdown)) {
			throw new KBArticleImportException(
				"Null or empty Markdown in file entry: " + fileEntry);
		}

		String html = null;

		try {
			html = _markdownConverter.convert(markdown);
		}
		catch (IOException ioe) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Unable to convert Markdown to HTML for file entry: ");
			sb.append(fileEntry);
			sb.append(". ");
			sb.append(ioe.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString(), ioe);
		}

		String heading = getTitleLineFromHtml(html);

		if (Validator.isNull(heading)) {
			StringBuilder sb = new StringBuilder(5);

			sb.append(
				"Unable to extract heading from HTML of converted file entry:");
			sb.append(StringPool.SPACE);
			sb.append(fileEntry);
			sb.append(", HTML: ");
			sb.append(html);

			throw new KBArticleImportException(sb.toString());
		}

		String urlTitle = getUrlTitleFromHeading(heading);

		String title = stripIds(heading);

		html = stripIds(html);

		html = referToImageFileInDocLibrary(html, fileEntriesMap);

		KBArticle article;

		try {
			article = applyContentToKBArticle(
				userId, groupId, parentResourcePrimaryKey, title, urlTitle,
				html, serviceContext);
		}
		catch (Exception e) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Unable to create KBArticle for file entry: ");
			sb.append(fileEntry);
			sb.append(". ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString(), e);
		}

		return article;
	}

	protected String getTitleLineFromHtml(String html) {
		int beginHeaderTag = html.indexOf("<h1>");
		int endHeaderTag = html.indexOf("</h1>");

		if ((beginHeaderTag == -1) ||
			(endHeaderTag == -1) ||
			(beginHeaderTag > endHeaderTag)) {

			return null;
		}

		String title = html.substring(beginHeaderTag + 4, endHeaderTag);

		return title;
	}

	protected String getUrlTitleFromHeading(String title) {
		String urlTitle = null;

		int idIndex = title.indexOf("[](id=");
		int closeParen = title.indexOf(")", idIndex);

		if (closeParen > (idIndex + 1)) {
			int equalsSign = title.indexOf("=", idIndex);

			urlTitle = title.substring(equalsSign + 1, closeParen);

			urlTitle = StringUtil.replace(
				urlTitle, StringPool.SPACE, StringPool.DASH);

			urlTitle = StringUtil.toLowerCase(urlTitle);
		}

		return urlTitle;
	}

	protected boolean isHeader(String text) {

		// Extract the first line if the text consists of multiple lines

		String firstLine = StringUtil.extractFirst(text, StringPool.NEW_LINE);

		String currentLine = null;

		if (firstLine != null) {
			currentLine = firstLine;
		}
		else {

			// Use entire text if it does not consist of multiple lines

			currentLine = text;
		}

		Matcher matcher = _headerTagPattern.matcher(currentLine);

		if (matcher.matches()) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void processArticleFiles(
			long userId, long groupId, ZipReader zipReader,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws IOException, KBArticleImportException {

		// Create map of the ZIP files folders to Markdown files, extracting the
		// root home page Markdown file along the way.

		String rootHomeMarkdown = null;

		for (String zipEntry : zipReader.getEntries()) {
			if (zipEntry.endsWith(".markdown") || zipEntry.endsWith(".md")) {
				int lastSlash = zipEntry.lastIndexOf(StringPool.SLASH);

				if (lastSlash == -1) {
					if (zipEntry.equals(_HOME_MARKDOWN)) {
						rootHomeMarkdown = zipReader.getEntryAsString(
							_HOME_MARKDOWN);
					}

					continue;
				}

				String folder = zipEntry.substring(0, lastSlash);

				List<String> fileEntries = _folderFileEntryMap.get(folder);

				if (fileEntries == null) {
					fileEntries = new ArrayList<String>();
				}

				fileEntries.add(zipEntry);

				_folderFileEntryMap.put(folder, fileEntries);
			}
		}

		if (Validator.isNull(rootHomeMarkdown)) {
			throw new KBArticleImportException(
				"Missing file entry: " + _HOME_MARKDOWN);
		}

		KBArticle rootHomeKBArticle = createKBArticleFromMarkdown(
			userId, groupId,
			KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
			rootHomeMarkdown, _HOME_MARKDOWN, fileEntriesMap, serviceContext);

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
				if (fileEntry.endsWith(_INTRODUCTION_MARKDOWN) ||
					fileEntry.endsWith(_INTRO_MARKDOWN)) {

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

			KBArticle chapterIntroKBArticle = createKBArticleFromMarkdown(
				userId, groupId, rootHomeKBArticle.getResourcePrimKey(),
				chapterIntroMarkdown, chapterHomeFileEntry, fileEntriesMap,
				serviceContext);

			for (String tutorialFileEntry : chapterMarkdownFileEntries) {
				String tutorialMarkdown = zipReader.getEntryAsString(
					tutorialFileEntry);

				if (Validator.isNotNull(tutorialMarkdown)) {
					createKBArticleFromMarkdown(
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

	protected String referToImageFileInDocLibrary(
		String html, Map<String, FileEntry> fileEntriesMap) {

		Set<Integer> indexes = new TreeSet<Integer>();

		int index = 0;
		while ((index = html.indexOf("<img", index)) > -1) {
			indexes.add(index);

			index += 4;
		}

		StringBundler sb = new StringBundler();

		if (indexes.isEmpty()) {
			sb.append(html);

			return sb.toString();
		}

		int previousIndex = 0;

		Iterator<Integer> iterator = indexes.iterator();

		while (iterator.hasNext()) {
			int currentIndex = iterator.next();

			if (currentIndex < 0) {
				break;
			}

			if (currentIndex > previousIndex) {

				// Append text from previous position up to image tag

				String text = html.substring(previousIndex, currentIndex);

				sb.append(text);
			}

			int pos = html.indexOf("/>", currentIndex);

			if (pos < 0 ) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Expected close tag for image " +
							html.substring(currentIndex));
				}

				sb.append(html.substring(currentIndex));

				previousIndex = currentIndex;

				break;
			}

			String text = html.substring(currentIndex, pos);

			FileEntry fileEntry = KBArticleImporterUtil.extractImageFileEntry(
				text, fileEntriesMap);

			if (fileEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to find image source " + text);
				}

				sb.append("<img alt=\"missing image\" src=\"\" ");
			}
			else {
				sb.append("<img alt=\"");
				sb.append(HtmlUtil.escapeAttribute(fileEntry.getTitle()));
				sb.append("\" src=\"/c/document_library/get_file?groupId=");
				sb.append(fileEntry.getGroupId());
				sb.append("&uuid=");
				sb.append(fileEntry.getUuid());
				sb.append("\" ");
			}

			previousIndex = pos;
		}

		if (previousIndex < html.length()) {
			String text = html.substring(previousIndex);

			sb.append(text);
		}

		return sb.toString();
	}

	protected String stripIds(String content) {
		int index = content.indexOf("[](id=");

		if (index == -1) {
			return content;
		}

		StringBundler sb = new StringBundler();

		do {
			int x = content.indexOf("=", index);
			int y = content.indexOf(")", x);

			if (y != -1) {
				sb.append(StringUtil.trimTrailing(content.substring(0, index)));

				content = content.substring(y + 1);
			}
			else {
				if (_log.isWarnEnabled()) {
					String msg = content.substring(index);

					// Get the invalid id text from the content

					int spaceIndex = content.indexOf(StringPool.SPACE);

					if (spaceIndex != -1) {
						msg = content.substring(index, spaceIndex);
					}

					_log.warn(
						"Missing ')' for web content containing header id " +
						msg);
				}

				// Since no close parenthesis remains in the content, stop
				// stripping out IDs and simply include all of the remaining
				// content.

				break;
			}
		}
		while ((index = content.indexOf("[](id=")) != -1);

		sb.append(content);

		return sb.toString();
	}

	private static final String _HOME_MARKDOWN = "home.markdown";

	private static final String _INTRO_MARKDOWN = "intro.markdown";

	private static final String _INTRODUCTION_MARKDOWN = "introduction.markdown";

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleHierarchyImporter.class);

	private Map<String, List<String>> _folderFileEntryMap =
		new TreeMap<String, List<String>>();
	private Pattern _headerTagPattern = Pattern.compile(
		"^(<h\\d>.*)(.*</h\\d>).*");
	private MarkdownConverter _markdownConverter =
		MarkdownConverterFactoryUtil.create();

}