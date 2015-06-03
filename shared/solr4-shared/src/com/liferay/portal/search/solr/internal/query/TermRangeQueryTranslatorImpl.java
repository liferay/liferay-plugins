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

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.search.solr.query.TermRangeQueryTranslator;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermRangeQueryTranslator.class)
public class TermRangeQueryTranslatorImpl implements TermRangeQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		TermRangeQuery termRangeQuery) {

		org.apache.lucene.search.TermRangeQuery luceneTermRangeQuery =
			org.apache.lucene.search.TermRangeQuery.newStringRange(
				termRangeQuery.getField(), termRangeQuery.getLowerTerm(),
				termRangeQuery.getUpperTerm(), termRangeQuery.includesLower(),
				termRangeQuery.includesUpper());

		if (!termRangeQuery.isDefaultBoost()) {
			luceneTermRangeQuery.setBoost(termRangeQuery.getBoost());
		}

		return luceneTermRangeQuery;
	}

}