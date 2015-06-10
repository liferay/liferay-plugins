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

import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.search.solr.query.MultiMatchQueryTranslator;

import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MultiMatchQueryTranslator.class)
public class MultiMatchQueryTranslatorImpl
	implements MultiMatchQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		MultiMatchQuery multiMatchQuery) {

		BooleanQuery booleanQuery = new BooleanQuery();

		MultiMatchQuery.Type multiMatchQueryType = multiMatchQuery.getType();

		Map<String, Float> fieldBoosts = multiMatchQuery.getFieldsBoosts();

		for (String field : multiMatchQuery.getFields()) {
			Term term = new Term(field, multiMatchQuery.getValue());

			Query query = null;

			if (multiMatchQueryType == MultiMatchQuery.Type.PHRASE) {
				PhraseQuery phraseQuery = new PhraseQuery();

				phraseQuery.add(term);

				if (multiMatchQuery.getSlop() != null) {
					phraseQuery.setSlop(multiMatchQuery.getSlop());
				}

				query = phraseQuery;
			}
			else if (multiMatchQueryType ==
						MultiMatchQuery.Type.PHRASE_PREFIX) {

				query = new PrefixQuery(term);
			}
			else {
				query = new TermQuery(term);
			}

			if (fieldBoosts.containsKey(field)) {
				Float fieldBoost = fieldBoosts.get(field);

				query.setBoost(fieldBoost);
			}

			booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		}

		return booleanQuery;
	}

}