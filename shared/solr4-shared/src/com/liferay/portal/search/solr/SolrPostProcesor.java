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

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.util.StringPool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author André de Oliveira
 */
public class SolrPostProcesor {

	public SolrPostProcesor(String query, String keywords) {
		this.query = query;
		this.keywords = keywords;
		sb = new StringBuilder(query.length());
	}

	public String postProcess() {
		while (findPhrase()) {
			appendPhrase();
		}

		appendRemainder();

		return sb.toString();
	}

	protected void appendPhrase() {
		String before = query.substring(index, firstQuoteIndex);

		sb.append(before);

		index = secondQuoteIndex + 1;

		String phrase = query.substring(firstQuoteIndex, index);

		if (questionMark) {
			String regex = buildRegex(phrase);

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(keywords);

			if (matcher.find()) {
				sb.append(matcher.group());
				return;
			}
		}

		sb.append(phrase);
	}

	protected void appendRemainder() {
		sb.append(query.substring(index));
	}

	protected String buildRegex(String phrase) {
		StringBuilder regex = new StringBuilder(phrase.length());

		int p = 0;

		while (true) {
			int q = phrase.indexOf(StringPool.QUESTION, p);

			if (q == -1) {
				break;
			}

			String part = phrase.substring(p, q);
			regex.append(Pattern.quote(part));
			regex.append(StringPool.PERIOD);
			regex.append(StringPool.PLUS);
			p = q + 1;
		}

		String remainder = phrase.substring(p);
		regex.append(Pattern.quote(remainder));

		return regex.toString();
	}

	protected boolean findPhrase() {
		firstQuoteIndex = query.indexOf(StringPool.QUOTE, index);

		if (firstQuoteIndex == -1) {
			return false;
		}

		secondQuoteIndex = query.indexOf(
			StringPool.QUOTE, firstQuoteIndex + 1);

		if (secondQuoteIndex == -1) {
			return false;
		}

		int questionMarkIndex = query.indexOf(
			StringPool.QUESTION, firstQuoteIndex);

		if (questionMarkIndex == -1) {
			questionMark = false;
		}
		else {
			questionMark = true;
		}

		return true;
	}

	protected int firstQuoteIndex;
	protected int index;
	protected String keywords;
	protected String query;
	protected boolean questionMark;
	protected StringBuilder sb;
	protected int secondQuoteIndex;


}