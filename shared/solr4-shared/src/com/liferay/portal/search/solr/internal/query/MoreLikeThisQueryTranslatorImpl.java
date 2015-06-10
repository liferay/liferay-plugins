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

import com.liferay.portal.kernel.search.generic.MoreLikeThisQuery;
import com.liferay.portal.search.solr.query.MoreLikeThisQueryTranslator;

import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.core.KeywordAnalyzer;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MoreLikeThisQueryTranslator.class)
public class MoreLikeThisQueryTranslatorImpl
	implements MoreLikeThisQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		MoreLikeThisQuery moreLikeThisQuery) {

		List<String> fields = moreLikeThisQuery.getFields();

		org.apache.lucene.queries.mlt.MoreLikeThisQuery
			luceneMoreLikeThisQuery =
				new org.apache.lucene.queries.mlt.MoreLikeThisQuery(
					moreLikeThisQuery.getLikeText(),
					fields.toArray(new String[fields.size()]),
					new KeywordAnalyzer(), fields.get(0));

		if (moreLikeThisQuery.getMaxQueryTerms() != null) {
			luceneMoreLikeThisQuery.setMaxQueryTerms(
				moreLikeThisQuery.getMaxQueryTerms());
		}

		if (moreLikeThisQuery.getMinDocFrequency() != null) {
			luceneMoreLikeThisQuery.setMinDocFreq(
				moreLikeThisQuery.getMinDocFrequency());
		}

		if (moreLikeThisQuery.getMinTermFrequency() != null) {
			luceneMoreLikeThisQuery.setMinTermFrequency(
				moreLikeThisQuery.getMinTermFrequency());
		}

		if (!moreLikeThisQuery.isDefaultBoost()) {
			luceneMoreLikeThisQuery.setBoost(moreLikeThisQuery.getBoost());
		}

		if (!moreLikeThisQuery.getStopWords().isEmpty()) {
			Set<String> stopWords = moreLikeThisQuery.getStopWords();

			luceneMoreLikeThisQuery.setStopWords(stopWords);
		}

		return luceneMoreLikeThisQuery;
	}

}