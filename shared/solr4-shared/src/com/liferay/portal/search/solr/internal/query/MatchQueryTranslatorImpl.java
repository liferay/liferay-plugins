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

import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.search.solr.query.MatchQueryTranslator;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.TermQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MatchQueryTranslator.class)
public class MatchQueryTranslatorImpl implements MatchQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(MatchQuery matchQuery) {
		org.apache.lucene.search.Query query = null;

		Term term = new Term(matchQuery.getField(), matchQuery.getValue());

		MatchQuery.Type type = matchQuery.getType();

		if (type == MatchQuery.Type.PHRASE) {
			PhraseQuery phraseQuery = new PhraseQuery();

			phraseQuery.add(term);

			if (matchQuery.getSlop() != null) {
				phraseQuery.setSlop(matchQuery.getSlop());
			}

			query = phraseQuery;
		}
		else if (type == MatchQuery.Type.PHRASE_PREFIX) {
			query = new PrefixQuery(term);
		}
		else {
			query = new TermQuery(term);
		}

		if (!matchQuery.isDefaultBoost()) {
			query.setBoost(matchQuery.getBoost());
		}

		return query;
	}

}