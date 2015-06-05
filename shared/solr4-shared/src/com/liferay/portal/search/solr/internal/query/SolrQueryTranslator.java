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

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.query.QueryTranslator;
import com.liferay.portal.kernel.search.query.QueryVisitor;
import com.liferay.portal.search.solr.query.BooleanQueryTranslator;
import com.liferay.portal.search.solr.query.TermQueryTranslator;
import com.liferay.portal.search.solr.query.TermRangeQueryTranslator;
import com.liferay.portal.search.solr.query.WildcardQueryTranslator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = QueryTranslator.class
)
public class SolrQueryTranslator
	implements QueryTranslator<String>,
			   QueryVisitor<org.apache.lucene.search.Query> {

	@Override
	public String translate(Query query, SearchContext searchContext) {
		org.apache.lucene.search.Query luceneQuery = query.accept(this);

		String queryString = null;

		if (luceneQuery != null) {
			queryString = luceneQuery.toString();
		}
		else {
			queryString = _postProcess(query.toString(), searchContext);
		}

		return queryString;
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		BooleanQuery booleanQuery) {

		return _booleanQueryTranslator.translate(booleanQuery, this);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(TermQuery termQuery) {
		return _termQueryTranslator.translate(termQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		TermRangeQuery termRangeQuery) {

		return _termRangeQueryTranslator.translate(termRangeQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		WildcardQuery wildcardQuery) {

		return _wildcardQueryTranslator.translate(wildcardQuery);
	}

	@Reference(unbind = "-")
	protected void setBooleanQueryTranslator(
		BooleanQueryTranslator booleanQueryTranslator) {

		_booleanQueryTranslator = booleanQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setTermQueryTranslator(
		TermQueryTranslator termQueryTranslator) {

		_termQueryTranslator = termQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setTermRangeQueryTranslator(
		TermRangeQueryTranslator termRangeQueryTranslator) {

		_termRangeQueryTranslator = termRangeQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setWildcardQueryTranslator(
		WildcardQueryTranslator wildcardQueryTranslator) {

		_wildcardQueryTranslator = wildcardQueryTranslator;
	}

	private String _postProcess(
		String queryString, SearchContext searchContext) {

		SolrPostProcesor solrPostProcesor = new SolrPostProcesor(
			queryString, searchContext.getKeywords());

		return solrPostProcesor.postProcess();
	}

	private BooleanQueryTranslator _booleanQueryTranslator;
	private TermQueryTranslator _termQueryTranslator;
	private TermRangeQueryTranslator _termRangeQueryTranslator;
	private WildcardQueryTranslator _wildcardQueryTranslator;

}