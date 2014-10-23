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
		_query = query;
		_keywords = keywords;

		_sb = new StringBuilder(query.length());
	}

	public String postProcess() {
		while (findPhrase()) {
			appendPhrase();
		}

		appendRemainder();

		return _sb.toString();
	}

	protected void appendPhrase() {
		String before = _query.substring(_index, firstQuoteIndex);

		_sb.append(before);

		_index = _secondQuoteIndex + 1;

		String phrase = _query.substring(firstQuoteIndex, _index);

		if (_questionMark) {
			String regex = buildRegex(phrase);

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(_keywords);

			if (matcher.find()) {
				_sb.append(matcher.group());
				return;
			}
		}

		_sb.append(phrase);
	}

	protected void appendRemainder() {
		_sb.append(_query.substring(_index));
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
		firstQuoteIndex = _query.indexOf(StringPool.QUOTE, _index);

		if (firstQuoteIndex == -1) {
			return false;
		}

		_secondQuoteIndex = _query.indexOf(
			StringPool.QUOTE, firstQuoteIndex + 1);

		if (_secondQuoteIndex == -1) {
			return false;
		}

		int questionMarkIndex = _query.indexOf(
			StringPool.QUESTION, firstQuoteIndex);

		if (questionMarkIndex == -1) {
			_questionMark = false;
		}
		else {
			_questionMark = true;
		}

		return true;
	}

	private int firstQuoteIndex;
	private int _index;
	private String _keywords;
	private String _query;
	private boolean _questionMark;
	private StringBuilder _sb;
	private int _secondQuoteIndex;

}