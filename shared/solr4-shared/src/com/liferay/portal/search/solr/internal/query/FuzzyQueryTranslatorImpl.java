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

import com.liferay.portal.kernel.search.generic.FuzzyQuery;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.solr.query.FuzzyQueryTranslator;

import org.apache.lucene.index.Term;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = FuzzyQueryTranslator.class)
public class FuzzyQueryTranslatorImpl implements FuzzyQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(FuzzyQuery fuzzyQuery) {
		Term term = new Term(fuzzyQuery.getField(), fuzzyQuery.getValue());

		int maxEdits = GetterUtil.getInteger(fuzzyQuery.getMaxEdits(), 0);
		int prefixLength = GetterUtil.getInteger(fuzzyQuery.getPrefixLength());
		int maxExpansions = GetterUtil.getInteger(
			fuzzyQuery.getMaxExpansions(), 50);

		if (!fuzzyQuery.isDefaultBoost()) {
			fuzzyQuery.setBoost(fuzzyQuery.getBoost());
		}

		org.apache.lucene.search.FuzzyQuery luceneFuzzyQuery =
			new org.apache.lucene.search.FuzzyQuery(
				term, maxEdits, prefixLength, maxExpansions, false);

		return luceneFuzzyQuery;
	}

}