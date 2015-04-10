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

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryVisitor;
import com.liferay.portal.search.solr.query.BooleanQueryTranslator;

import org.apache.lucene.search.BooleanClause.Occur;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
public class BooleanQueryTranslatorImpl implements BooleanQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		BooleanQuery booleanQuery,
		QueryVisitor<org.apache.lucene.search.Query> queryVisitor) {

		org.apache.lucene.search.BooleanQuery luceneBooleanQuery =
			new org.apache.lucene.search.BooleanQuery();

		for (BooleanClause booleanClause : booleanQuery.clauses()) {
			_addClause(booleanClause, luceneBooleanQuery, queryVisitor);
		}

		return luceneBooleanQuery;
	}

	private void _addClause(
		BooleanClause booleanClause,
		org.apache.lucene.search.BooleanQuery booleanQuery,
		QueryVisitor<org.apache.lucene.search.Query> queryVisitor) {

		BooleanClauseOccur booleanClauseOccur =
			booleanClause.getBooleanClauseOccur();

		Query query = booleanClause.getQuery();

		org.apache.lucene.search.Query luceneQuery = query.accept(queryVisitor);

		if (booleanClauseOccur.equals(BooleanClauseOccur.MUST)) {
			booleanQuery.add(luceneQuery, Occur.MUST);

			return;
		}

		if (booleanClauseOccur.equals(BooleanClauseOccur.MUST_NOT)) {
			booleanQuery.add(luceneQuery, Occur.MUST_NOT);

			return;
		}

		if (booleanClauseOccur.equals(BooleanClauseOccur.SHOULD)) {
			booleanQuery.add(luceneQuery, Occur.SHOULD);

			return;
		}

		throw new IllegalArgumentException();
	}

}