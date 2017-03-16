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

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
public class SolrExpandoPostProcessor {

	public SolrExpandoPostProcessor(String query) {
		_query = query;

		_sb = new StringBuilder(query.length());
	}

	public String postProcess() {
		while (findField()) {
			appendField();
		}

		appendRemainder();

		return _sb.toString();
	}

	protected void appendField() {
		String before = _query.substring(_index, _beginIndex);

		_sb.append(before);

		_index = _query.indexOf(CharPool.COLON, _beginIndex);

		String field = _query.substring(_beginIndex, _index);

		String escapedField = StringUtil.replace(
			field, CharPool.SPACE, StringPool.BACK_SLASH + StringPool.SPACE);

		_sb.append(escapedField);
	}

	protected void appendRemainder() {
		_sb.append(_query.substring(_index));
	}

	protected boolean findField() {
		_beginIndex = _query.indexOf(_PREFIX, _index);

		if (_beginIndex == -1) {
			return false;
		}

		return true;
	}

	private static final String _PREFIX = "expando/custom_fields/";

	private int _beginIndex;
	private int _index;
	private String _query;
	private StringBuilder _sb;

}