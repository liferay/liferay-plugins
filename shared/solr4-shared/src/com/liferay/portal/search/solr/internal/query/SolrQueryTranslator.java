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
import com.liferay.portal.kernel.search.generic.DisMaxQuery;
import com.liferay.portal.kernel.search.generic.FuzzyLikeThisQuery;
import com.liferay.portal.kernel.search.generic.FuzzyQuery;
import com.liferay.portal.kernel.search.generic.MatchAllQuery;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.search.generic.MoreLikeThisQuery;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.NestedQuery;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.query.QueryTranslator;
import com.liferay.portal.kernel.search.query.QueryVisitor;
import com.liferay.portal.search.solr.query.BooleanQueryTranslator;
import com.liferay.portal.search.solr.query.DisMaxQueryTranslator;
import com.liferay.portal.search.solr.query.FuzzyLikeThisQueryTranslator;
import com.liferay.portal.search.solr.query.FuzzyQueryTranslator;
import com.liferay.portal.search.solr.query.LuceneQueryConverter;
import com.liferay.portal.search.solr.query.MatchAllQueryTranslator;
import com.liferay.portal.search.solr.query.MatchQueryTranslator;
import com.liferay.portal.search.solr.query.MoreLikeThisQueryTranslator;
import com.liferay.portal.search.solr.query.MultiMatchQueryTranslator;
import com.liferay.portal.search.solr.query.NestedQueryTranslator;
import com.liferay.portal.search.solr.query.StringQueryTranslator;
import com.liferay.portal.search.solr.query.TermQueryTranslator;
import com.liferay.portal.search.solr.query.TermRangeQueryTranslator;
import com.liferay.portal.search.solr.query.WildcardQueryTranslator;

import org.apache.lucene.search.MatchAllDocsQuery;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = {LuceneQueryConverter.class, QueryTranslator.class}
)
public class SolrQueryTranslator
	implements LuceneQueryConverter, QueryTranslator<String>,
			   QueryVisitor<org.apache.lucene.search.Query> {

	@Override
	public org.apache.lucene.search.Query convert(Query query) {
		return query.accept(this);
	}

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
	public org.apache.lucene.search.Query visitQuery(DisMaxQuery disMaxQuery) {
		return _disMaxQueryTranslator.translate(disMaxQuery, this);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		FuzzyLikeThisQuery fuzzyLikeThisQuery) {

		return _fuzzyLikeThisQueryTranslator.translate(fuzzyLikeThisQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(FuzzyQuery fuzzyQuery) {
		return _fuzzyQueryTranslator.translate(fuzzyQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		MatchAllQuery matchAllQuery) {

		org.apache.lucene.search.MatchAllDocsQuery matchAllDocsQuery =
			new MatchAllDocsQuery();

		if (!matchAllQuery.isDefaultBoost()) {
			matchAllDocsQuery.setBoost(matchAllQuery.getBoost());
		}

		return matchAllDocsQuery;
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(MatchQuery matchQuery) {
		return _matchQueryTranslator.translate(matchQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		MoreLikeThisQuery moreLikeThisQuery) {

		return _moreLikeThisQueryTranslator.translate(moreLikeThisQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		MultiMatchQuery multiMatchQuery) {

		return _multiMatchQueryTranslator.translate(multiMatchQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(NestedQuery nestedQuery) {
		return _nestedQueryTranslator.translate(nestedQuery, this);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(StringQuery stringQuery) {
		return _stringQueryTranslator.translate(stringQuery);
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
	protected void setDisMaxQueryTranslator(
		DisMaxQueryTranslator disMaxQueryTranslator) {

		_disMaxQueryTranslator = disMaxQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setFuzzyLikeThisQueryTranslator(
		FuzzyLikeThisQueryTranslator fuzzyLikeThisQueryTranslator) {

		_fuzzyLikeThisQueryTranslator = fuzzyLikeThisQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setFuzzyQueryTranslator(
		FuzzyQueryTranslator fuzzyQueryTranslator) {

		_fuzzyQueryTranslator = fuzzyQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMatchAllQueryTranslator(
		MatchAllQueryTranslator matchAllQueryTranslator) {

		_matchAllQueryTranslator = matchAllQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMatchQueryTranslator(
		MatchQueryTranslator matchQueryTranslator) {

		_matchQueryTranslator = matchQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMoreLikeThisQueryTranslator(
		MoreLikeThisQueryTranslator moreLikeThisQueryTranslator) {

		_moreLikeThisQueryTranslator = moreLikeThisQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMultiMatchQueryTranslator(
		MultiMatchQueryTranslator multiMatchQueryTranslator) {

		_multiMatchQueryTranslator = multiMatchQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setNestedQueryTranslator(
		NestedQueryTranslator nestedQueryTranslator) {

		_nestedQueryTranslator = nestedQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setStringQueryTranslator(
		StringQueryTranslator stringQueryTranslator) {

		_stringQueryTranslator = stringQueryTranslator;
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
	private DisMaxQueryTranslator _disMaxQueryTranslator;
	private FuzzyLikeThisQueryTranslator _fuzzyLikeThisQueryTranslator;
	private FuzzyQueryTranslator _fuzzyQueryTranslator;
	private MatchAllQueryTranslator _matchAllQueryTranslator;
	private MatchQueryTranslator _matchQueryTranslator;
	private MoreLikeThisQueryTranslator _moreLikeThisQueryTranslator;
	private MultiMatchQueryTranslator _multiMatchQueryTranslator;
	private NestedQueryTranslator _nestedQueryTranslator;
	private StringQueryTranslator _stringQueryTranslator;
	private TermQueryTranslator _termQueryTranslator;
	private TermRangeQueryTranslator _termRangeQueryTranslator;
	private WildcardQueryTranslator _wildcardQueryTranslator;

}