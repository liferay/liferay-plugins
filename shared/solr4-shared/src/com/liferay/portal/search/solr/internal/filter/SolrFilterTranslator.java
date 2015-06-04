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

import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.DateRangeTermFilter;
import com.liferay.portal.kernel.search.filter.ExistsFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.FilterTranslator;
import com.liferay.portal.kernel.search.filter.FilterVisitor;
import com.liferay.portal.kernel.search.filter.GeoBoundingBoxFilter;
import com.liferay.portal.kernel.search.filter.GeoDistanceFilter;
import com.liferay.portal.kernel.search.filter.GeoDistanceRangeFilter;
import com.liferay.portal.kernel.search.filter.GeoPolygonFilter;
import com.liferay.portal.kernel.search.filter.MissingFilter;
import com.liferay.portal.kernel.search.filter.PrefixFilter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = FilterTranslator.class
)
public class SolrFilterTranslator
	implements FilterTranslator<String>, FilterVisitor<String> {

	@Override
	public String translate(Filter filter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(BooleanFilter booleanFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(DateRangeTermFilter dateRangeTermFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(ExistsFilter existsFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(GeoBoundingBoxFilter geoBoundingBoxFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(GeoDistanceFilter geoDistanceFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(GeoDistanceRangeFilter geoDistanceRangeFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(GeoPolygonFilter geoPolygonFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(MissingFilter missingFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(PrefixFilter prefixFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(QueryFilter queryFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(RangeTermFilter rangeTermFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(TermFilter termFilter) {
		return StringPool.BLANK;
	}

	@Override
	public String visit(TermsFilter termsFilter) {
		return StringPool.BLANK;
	}

}