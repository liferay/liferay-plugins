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

import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.search.solr.query.WildcardQueryTranslator;

import org.apache.lucene.index.Term;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = WildcardQueryTranslator.class)
public class WildcardQueryTranslatorImpl implements WildcardQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		WildcardQuery wildcardQuery) {

		QueryTerm queryTerm = wildcardQuery.getQueryTerm();

		org.apache.lucene.search.WildcardQuery luceneWildcardQuery =
			new org.apache.lucene.search.WildcardQuery(
				new Term(queryTerm.getField(), queryTerm.getValue()));

		if (!wildcardQuery.isDefaultBoost()) {
			luceneWildcardQuery.setBoost(wildcardQuery.getBoost());
		}

		return luceneWildcardQuery;
	}

}