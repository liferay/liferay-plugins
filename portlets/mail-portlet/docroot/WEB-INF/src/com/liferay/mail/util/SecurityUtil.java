/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.util;

import com.liferay.portal.kernel.util.StringPool;

import java.util.regex.Pattern;

/**
 * <a href="SecurityUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 * @author Minhchau Dang
 * @author Michael C. Han
 */
public class SecurityUtil {

	public static String stripHtml(String html) {
		html = _TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);
		html = _LINE_BREAK_PATTERN.matcher(html).replaceAll(StringPool.BLANK);

		return html;
	}

	public static String stripUnsafeHtmlAndCss(String html) {

		// Remove lines (external stylesheets, html, body)

		html = _LINK_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);
		html = _HTML_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);
		html = _BODY_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);
		html = _DOCTYPE_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);

		// Remove blocks (script, style, head)

		html = _SCRIPT_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);
		html = _STYLE_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);
		html = _HEAD_TAG_PATTERN.matcher(html).replaceAll(StringPool.BLANK);

		return html;
	}

	private static final Pattern _BODY_TAG_PATTERN =
		Pattern.compile("</?body[^>]+>", Pattern.CASE_INSENSITIVE);

	private static final Pattern _DOCTYPE_TAG_PATTERN =
		Pattern.compile("<!doctype[^>]+>", Pattern.CASE_INSENSITIVE);

	private static final Pattern _HEAD_TAG_PATTERN =
		Pattern.compile("<head.*?</head>", Pattern.CASE_INSENSITIVE +
			Pattern.DOTALL);

	private static final Pattern _HTML_TAG_PATTERN =
		Pattern.compile("</?html[^>]+>", Pattern.CASE_INSENSITIVE);

	private static final Pattern _LINE_BREAK_PATTERN =
		Pattern.compile("[\r\n]+");

	private static final Pattern _LINK_TAG_PATTERN =
		Pattern.compile("</?link[^>]+>", Pattern.CASE_INSENSITIVE);

	private static final Pattern _SCRIPT_TAG_PATTERN =
		Pattern.compile("<script.*?</script>", Pattern.CASE_INSENSITIVE +
			Pattern.DOTALL);

	private static final Pattern _STYLE_TAG_PATTERN =
		Pattern.compile("<style.*?</style>", Pattern.CASE_INSENSITIVE +
			Pattern.DOTALL);

	private static final Pattern _TAG_PATTERN = Pattern.compile("<[^>]+>");

}