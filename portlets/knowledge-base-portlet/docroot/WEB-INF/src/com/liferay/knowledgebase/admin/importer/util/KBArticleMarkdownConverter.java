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
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.IOException;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Sergio González
 */
public class KBArticleMarkdownConverter {

	public KBArticleMarkdownConverter(
			String markdown, Map<String, FileEntry> fileEntriesMap)
		throws KBArticleImportException {

		MarkdownConverter markdownConverter =
			MarkdownConverterFactoryUtil.create();

		String html = null;

		try {
			html = markdownConverter.convert(markdown);
		}
		catch (IOException ioe) {
			throw new KBArticleImportException(
				"Unable to convert Markdown to HTML: " +
					ioe.getLocalizedMessage(),
				ioe);
		}

		String heading = getHeading(html);

		if (Validator.isNull(heading)) {
			throw new KBArticleImportException(
				"Unable to extract heading from converted HTML: " + html);
		}

		_urlTitle = getUrlTitle(heading);

		_title = stripIds(heading);

		html = stripIds(html);

		_html = updateDocumentLibraryReferences(html, fileEntriesMap);
	}

	public String getHtml() {
		return _html;
	}

	public String getTitle() {
		return _title;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	protected String getHeading(String html) {
		int x = html.indexOf("<h1>");
		int y = html.indexOf("</h1>");

		if ((x == -1) || (y == -1) || (x > y)) {
			return null;
		}

		return html.substring(x + 4, y);
	}

	protected String getUrlTitle(String heading) {
		String urlTitle = null;

		int x = heading.indexOf("[](id=");
		int y = heading.indexOf(StringPool.CLOSE_PARENTHESIS, x);

		if (y > (x + 1)) {
			int equalsSign = heading.indexOf(StringPool.EQUAL, x);

			urlTitle = heading.substring(equalsSign + 1, y);

			urlTitle = StringUtil.replace(
				urlTitle, StringPool.SPACE, StringPool.DASH);

			urlTitle = StringUtil.toLowerCase(urlTitle);
		}

		return urlTitle;
	}

	protected String stripIds(String content) {
		int index = content.indexOf("[](id=");

		if (index == -1) {
			return content;
		}

		StringBundler sb = new StringBundler();

		do {
			int x = content.indexOf(StringPool.EQUAL, index);
			int y = content.indexOf(StringPool.CLOSE_PARENTHESIS, x);

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
				// content

				break;
			}
		}
		while ((index = content.indexOf("[](id=")) != -1);

		sb.append(content);

		return sb.toString();
	}

	protected String updateDocumentLibraryReferences(
		String html, Map<String, FileEntry> fileEntriesMap) {

		Set<Integer> indexes = new TreeSet<Integer>();

		int index = 0;

		while ((index = html.indexOf("<img", index)) > -1) {
			indexes.add(index);

			index += 4;
		}

		if (indexes.isEmpty()) {
			return html;
		}

		StringBundler sb = new StringBundler();

		int previousIndex = 0;

		for (int curIndex : indexes) {
			if (curIndex < 0) {
				break;
			}

			if (curIndex > previousIndex) {

				// Append text from previous position up to image tag

				String text = html.substring(previousIndex, curIndex);

				sb.append(text);
			}

			int pos = html.indexOf("/>", curIndex);

			if (pos < 0) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Expected close tag for image " +
							html.substring(curIndex));
				}

				sb.append(html.substring(curIndex));

				previousIndex = curIndex;

				break;
			}

			String text = html.substring(curIndex, pos);

			FileEntry fileEntry = KBArticleImporterUtil.extractImageFileEntry(
				text, fileEntriesMap);

			if (fileEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to find image source " + text);
				}

				sb.append("<img alt=\"missing image\" src=\"\" ");
			}
			else {
				String imageSrc = StringPool.BLANK;

				try {
					imageSrc = DLUtil.getPreviewURL(
						fileEntry, fileEntry.getFileVersion(), null,
						StringPool.BLANK);
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to obtain image url from fileEntry " +
								fileEntry.getFileEntryId());
					}
				}

				sb.append("<img alt=\"");
				sb.append(HtmlUtil.escapeAttribute(fileEntry.getTitle()));
				sb.append("\" src=\"");
				sb.append(imageSrc);
				sb.append("\" ");
			}

			previousIndex = pos;
		}

		if (previousIndex < html.length()) {
			sb.append(html.substring(previousIndex));
		}

		return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleMarkdownConverter.class);

	private String _html;
	private String _title;
	private String _urlTitle;

}