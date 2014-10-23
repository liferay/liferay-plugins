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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author André de Oliveira
 */
public class SolrPostProcesor {

	public SolrPostProcesor(String query, String keywords) {
		this._query = query;
		this._keywords = keywords;
		this._sb = new StringBuilder(query.length());
	}

	public String postProcess() {
		while (findPhrase()) {
			appendPhrase();
		}

		appendRemainder();

		return _sb.toString();
	}

	protected void appendPhrase() {
		String before = _query.substring(i, a);

		_sb.append(before);

		i = b + 1;

		String phrase = _query.substring(a, i);

		if (questionMark) {
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
		_sb.append(_query.substring(i));
	}

	protected String buildRegex(String phrase) {
		StringBuilder regex = new StringBuilder(phrase.length());

		int p = 0;

		while (true) {
			int q = phrase.indexOf('?', p);

			if (q == -1) {
				break;
			}

			String part = phrase.substring(p, q);
			regex.append(Pattern.quote(part));
			regex.append(".+");
			p = q + 1;
		}

		String remainder = phrase.substring(p);
		regex.append(Pattern.quote(remainder));

		return regex.toString();
	}

	protected boolean findPhrase() {
		a = _query.indexOf('"', i);

		if (a == -1) {
			return false;
		}

		b = _query.indexOf('"', a + 1);

		if (b == -1) {
			return false;
		}

		int q = _query.indexOf('?', a);

		if (q == -1) {
			questionMark = false;
		}
		else {
			questionMark = true;
		}

		return true;
	}

	protected int a, b, i;
	protected boolean questionMark;

	private final String _keywords;
	private final String _query;
	private final StringBuilder _sb;

}