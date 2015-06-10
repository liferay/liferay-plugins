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
import com.liferay.portal.search.solr.filter.QueryFilterTranslator;
import com.liferay.portal.search.solr.query.LuceneQueryConverter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = QueryFilterTranslator.class)
public class QueryFilterTranslatorImpl implements QueryFilterTranslator {

	@Override
	public org.apache.lucene.search.Query translate(QueryFilter queryFilter) {
		return _luceneQueryConverter.convert(queryFilter.getQuery());
	}

	@Reference(unbind = "-")
	protected void setLuceneQueryConverter(
		LuceneQueryConverter luceneQueryConverter) {

		_luceneQueryConverter = luceneQueryConverter;
	}

	private LuceneQueryConverter _luceneQueryConverter;

}