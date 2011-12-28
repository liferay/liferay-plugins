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

package com.liferay.randombibleverse.util;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.randombibleverse.model.Verse;

/**
 * @author Brian Wing Shun Chan
 */
public class VerseWebCacheItem implements WebCacheItem {

	public VerseWebCacheItem(
		String location, String versionId, String language) {

		_location = location;
		_versionId = versionId;
		_language = language;
	}

	public Object convert(String key) throws WebCacheException {
		try {
			Verse verse = null;

			if (_language.equalsIgnoreCase("fi")) {
				verse = getUskonkirjat(verse);
			}
			else {
				verse = getBiblegateway(verse);
			}

			return verse;
		}
		catch (Exception e) {
			throw new WebCacheException(
				_location + " " + _versionId + " " + e.toString());
		}
	}

	private Verse getBiblegateway(Verse verse) throws Exception {
		StringBundler sb = new StringBundler();

		sb.append("http://www.biblegateway.com/passage/?search=");
		sb.append(HttpUtil.encodeURL(_location));
		sb.append("&version=");
		sb.append(_versionId);

		String text = HttpUtil.URLtoString(sb.toString());

		int x = text.indexOf("result-text-style");
		x = text.indexOf(">", x);

		int y = text.indexOf("</div>", x);

		text = text.substring(x + 1, y);

		y = text.indexOf("Footnotes:");

		if (y != -1) {
			text = text.substring(0, y);
		}
		else {
			y = text.indexOf("Cross references:");

			if (y != -1) {
				text = text.substring(0, y);
			}
		}

		// Strip everything between <span> and </span>

		text = HtmlUtil.stripBetween(text, "span");

		// Strip everything between <sup> and </sup>

		text = HtmlUtil.stripBetween(text, "sup");

		// Strip everything between <h4> and </h4>

		text = HtmlUtil.stripBetween(text, "h4");

		// Strip everything between <h5> and </h5>

		text = HtmlUtil.stripBetween(text, "h5");

		// Strip HTML

		text = HtmlUtil.stripHtml(text).trim();

		// Strip &nbsp;

		text = StringUtil.replace(text, "&nbsp;", "");

		// Strip carriage returns

		text = StringUtil.replace(text, "\n", "");

		// Strip double spaces

		while (text.indexOf("  ") != -1) {
			text = StringUtil.replace(text, "  ", " ");
		}

		// Replace " with &quot;

		text = StringUtil.replace(text, "\"", "&quot;");

		// Trim

		text = text.trim();

		return new Verse(_location, text);
	}

	private Verse getUskonkirjat(Verse verse) throws Exception {
		StringBundler sb = new StringBundler();

		sb.append("http://raamattu.uskonkirjat.net/servlet/biblesite.Bible?");
		sb.append("ctx=0&formname=search&formrnd=1225797093590&mod1=FinPR");
		sb.append("&mod2=&mod3=&ref=");
		sb.append(HttpUtil.encodeURL(_location));
		sb.append("&rng=0&search=&submit2=Lue");

		String text = HttpUtil.URLtoString(sb.toString());

		int x = text.indexOf("<div class=\"text\">");
		x = text.indexOf(">", x + 1);

		int y = text.lastIndexOf("</div>");

		text = text.substring(x + 1, y);

		// Strip HTML

		text = HtmlUtil.stripHtml(text).trim();

		// Strip &nbsp; and other extra characters

		text = StringUtil.replace(text, "&nbsp;", "");
		text = StringUtil.replace(text, "(", "");
		text = StringUtil.replace(text, ")", "");
		text = StringUtil.replace(text, ":", "");
		text = text.replaceAll("\\d+", "");

		// Strip carriage returns

		text = StringUtil.replace(text, "\n", "");

		// Strip double spaces

		while (text.indexOf("  ") != -1) {
			text = StringUtil.replace(text, "  ", " ");
		}

		// Replace " with &quot;

		text = StringUtil.replace(text, "\"", "&quot;");

		// Trim

		text = text.trim();

		return new Verse(_location, text);
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.WEEK * 52;

	private String _language;
	private String _location;
	private String _versionId;

}