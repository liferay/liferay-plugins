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

import com.liferay.portal.kernel.search.QueryPreProcessConfiguration;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.solr.query.TermQueryTranslator;

import java.util.List;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.util.Version;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermQueryTranslator.class)
public class TermQueryTranslatorImpl implements TermQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(TermQuery termQuery) {
		org.apache.lucene.search.Query luceneQuery = null;

		QueryTerm queryTerm = termQuery.getQueryTerm();

		String field = queryTerm.getField();
		String value = queryTerm.getValue();

		if ((_queryPreProcessConfiguration != null) &&
			_queryPreProcessConfiguration.isSubstringSearchAlways(field)) {

			luceneQuery = toCaseInsensitiveSubstringQuery(field, value);
		}

		luceneQuery = new org.apache.lucene.search.TermQuery(
			new Term(field, value));

		if (!termQuery.isDefaultBoost()) {
			luceneQuery.setBoost(termQuery.getBoost());
		}

		return luceneQuery;
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setQueryPreProcessConfiguration(
		QueryPreProcessConfiguration queryPreProcessConfiguration) {

		_queryPreProcessConfiguration = queryPreProcessConfiguration;
	}

	protected org.apache.lucene.search.Query toCaseInsensitiveSubstringQuery(
		org.apache.lucene.search.TermQuery termQuery) {

		Term term = termQuery.getTerm();

		String value = term.text();

		value = StringUtil.replace(value, StringPool.PERCENT, StringPool.BLANK);
		value = StringUtil.toLowerCase(value);
		value = StringPool.STAR + value + StringPool.STAR;

		return new org.apache.lucene.search.WildcardQuery(
			new Term(term.field(), value));
	}

	protected org.apache.lucene.search.Query toCaseInsensitiveSubstringQuery(
		String field, String value) {

		org.apache.lucene.search.Query query = _parseLuceneQuery(field, value);

		if (query instanceof org.apache.lucene.search.BooleanQuery) {
			return toCaseInsensitiveSubstringQuery(
				(org.apache.lucene.search.BooleanQuery)query);
		}

		if (query instanceof org.apache.lucene.search.TermQuery) {
			return toCaseInsensitiveSubstringQuery(
				(org.apache.lucene.search.TermQuery)query);
		}

		throw new IllegalArgumentException();
	}

	protected void unsetQueryPreProcessConfiguration(
		QueryPreProcessConfiguration queryPreProcessConfiguration) {

		_queryPreProcessConfiguration = null;
	}

	private org.apache.lucene.search.Query _parseLuceneQuery(
		String field, String value) {

		QueryParser queryParser = new QueryParser(
			Version.LUCENE_43, field, new KeywordAnalyzer());

		try {
			return queryParser.parse(value);
		}
		catch (ParseException pe) {
			throw new IllegalArgumentException(pe);
		}
	}

	private org.apache.lucene.search.Query toCaseInsensitiveSubstringQuery(
		org.apache.lucene.search.BooleanQuery booleanQuery) {

		org.apache.lucene.search.BooleanQuery newBooleanQuery =
			new org.apache.lucene.search.BooleanQuery();

		List<BooleanClause> clauses = booleanQuery.clauses();

		for (BooleanClause booleanClause : clauses) {
			org.apache.lucene.search.TermQuery termQuery =
				(org.apache.lucene.search.TermQuery)booleanClause.getQuery();

			org.apache.lucene.search.Query query =
				toCaseInsensitiveSubstringQuery(termQuery);

			newBooleanQuery.add(query, booleanClause.getOccur());
		}

		return newBooleanQuery;
	}

	private QueryPreProcessConfiguration _queryPreProcessConfiguration;

}