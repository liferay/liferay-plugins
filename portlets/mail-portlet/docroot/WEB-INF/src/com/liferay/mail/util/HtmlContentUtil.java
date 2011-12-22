/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
 * @author Scott Lee
 * @author Minhchau Dang
 * @author Michael C. Han
 */
public class HtmlContentUtil {

	public static String getInlineHtml(String html) {

		// Lines

		html = _bodyTagPattern.matcher(html).replaceAll(StringPool.BLANK);
		html = _doctypeTagPattern.matcher(html).replaceAll(StringPool.BLANK);
		html = _htmlTagPattern.matcher(html).replaceAll(StringPool.BLANK);
		html = _linkTagPattern.matcher(html).replaceAll(StringPool.BLANK);

		// Blocks

		html = _headTagPattern.matcher(html).replaceAll(StringPool.BLANK);
		html = _scriptTagPattern.matcher(html).replaceAll(StringPool.BLANK);
		html = _styleTagPattern.matcher(html).replaceAll(StringPool.BLANK);

		return html;
	}

	public static String getPlainText(String html) {
		html = _lineBreakPattern.matcher(html).replaceAll(StringPool.BLANK);
		html = _tagPattern.matcher(html).replaceAll(StringPool.BLANK);

		return html;
	}

	private static Pattern _bodyTagPattern = Pattern.compile(
		"</?body[^>]+>", Pattern.CASE_INSENSITIVE);
	private static Pattern _doctypeTagPattern = Pattern.compile(
		"<!doctype[^>]+>", Pattern.CASE_INSENSITIVE);
	private static Pattern _headTagPattern = Pattern.compile(
		"<head.*?</head>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	private static Pattern _htmlTagPattern = Pattern.compile(
		"</?html[^>]+>", Pattern.CASE_INSENSITIVE);
	private static Pattern _lineBreakPattern = Pattern.compile("[\r\n]+");
	private static Pattern _linkTagPattern = Pattern.compile(
		"</?link[^>]+>", Pattern.CASE_INSENSITIVE);
	private static Pattern _scriptTagPattern = Pattern.compile(
		"<script.*?</script>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	private static Pattern _styleTagPattern = Pattern.compile(
		"<style.*?</style>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	private static Pattern _tagPattern = Pattern.compile("<[^>]+>");

}