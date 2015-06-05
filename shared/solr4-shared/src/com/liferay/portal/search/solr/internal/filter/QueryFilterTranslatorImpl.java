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

package com.liferay.portal.search.solr.internal.filter;

import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.query.QueryTranslator;
import com.liferay.portal.search.solr.filter.QueryFilterTranslator;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = QueryFilterTranslator.class)
public class QueryFilterTranslatorImpl implements QueryFilterTranslator {

	@Override
	public org.apache.lucene.search.Query translate(QueryFilter queryFilter) {
		String queryString = _queryTranslator.translate(
			queryFilter.getQuery(), null);

		QueryParser queryParser = new QueryParser(
			Version.LUCENE_43, "uuid", new KeywordAnalyzer());

		try {
			Query query = queryParser.parse(queryString);

			return query;
		}
		catch (Exception e) {
			throw new IllegalStateException(
				"Unable to parse query: " + queryString, e);
		}
	}

	@Reference(unbind = "-")
	protected void setQueryTranslator(QueryTranslator<String> queryTranslator) {
		_queryTranslator = queryTranslator;
	}

	private QueryTranslator<String> _queryTranslator;

}