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

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.filter.BooleanFilterTranslator;
import com.liferay.portal.search.solr.filter.DateRangeTermFilterTranslator;
import com.liferay.portal.search.solr.filter.ExistsFilterTranslator;
import com.liferay.portal.search.solr.filter.GeoBoundingBoxFilterTranslator;
import com.liferay.portal.search.solr.filter.GeoDistanceFilterTranslator;
import com.liferay.portal.search.solr.filter.GeoDistanceRangeFilterTranslator;
import com.liferay.portal.search.solr.filter.GeoPolygonFilterTranslator;
import com.liferay.portal.search.solr.filter.MissingFilterTranslator;
import com.liferay.portal.search.solr.filter.PrefixFilterTranslator;
import com.liferay.portal.search.solr.filter.QueryFilterTranslator;
import com.liferay.portal.search.solr.filter.RangeTermFilterTranslator;
import com.liferay.portal.search.solr.filter.TermFilterTranslator;
import com.liferay.portal.search.solr.filter.TermsFilterTranslator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = FilterTranslator.class
)
public class SolrFilterTranslator
	implements FilterTranslator<String>,
			   FilterVisitor<org.apache.lucene.search.Query> {

	@Override
	public String translate(Filter filter, SearchContext searchContext) {
		String filterString = StringPool.BLANK;

		if (filter != null) {
			org.apache.lucene.search.Query luceneQuery = filter.accept(this);

			if (luceneQuery != null) {
				filterString = luceneQuery.toString();
			}
		}

		if (searchContext != null) {
			filterString = includeCompanyId(filterString, searchContext);
		}

		return filterString;
	}

	@Override
	public org.apache.lucene.search.Query visit(BooleanFilter booleanFilter) {
		return _booleanQueryTranslator.translate(booleanFilter, this);
	}

	@Override
	public org.apache.lucene.search.Query visit(
		DateRangeTermFilter dateRangeTermFilter) {

		return _dateRangeTermFilterTranslator.translate(dateRangeTermFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(ExistsFilter existsFilter) {
		return _existsFilterTranslator.translate(existsFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(
		GeoBoundingBoxFilter geoBoundingBoxFilter) {

		return _geoBoundingBoxFilterTranslator.translate(geoBoundingBoxFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(
		GeoDistanceFilter geoDistanceFilter) {

		return _geoDistanceFilterTranslator.translate(geoDistanceFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(
		GeoDistanceRangeFilter geoDistanceRangeFilter) {

		return _geoDistanceRangeFilterTranslator.translate(
			geoDistanceRangeFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(
		GeoPolygonFilter geoPolygonFilter) {

		return _geoPolygonFilterTranslator.translate(geoPolygonFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(MissingFilter missingFilter) {
		return _missingFilterTranslator.translate(missingFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(PrefixFilter prefixFilter) {
		return _prefixFilterTranslator.translate(prefixFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(QueryFilter queryFilter) {
		return _queryFilterTranslator.translate(queryFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(
		RangeTermFilter rangeTermFilter) {

		return _rangeTermFilterTranslator.translate(rangeTermFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(TermFilter termFilter) {
		return _termFilterTranslator.translate(termFilter);
	}

	@Override
	public org.apache.lucene.search.Query visit(TermsFilter termsFilter) {
		return _termsFilterTranslator.translate(termsFilter);
	}

	@Reference(unbind = "-")
	protected void setBooleanQueryTranslator(
		BooleanFilterTranslator booleanQueryTranslator) {

		_booleanQueryTranslator = booleanQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setDateRangeTermFilterTranslator(
		DateRangeTermFilterTranslator dateRangeTermFilterTranslator) {

		_dateRangeTermFilterTranslator = dateRangeTermFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setExistsFilterTranslator(
		ExistsFilterTranslator existsFilterTranslator) {

		_existsFilterTranslator = existsFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setGeoBoundingBoxFilterTranslator(
		GeoBoundingBoxFilterTranslator geoBoundingBoxFilterTranslator) {

		_geoBoundingBoxFilterTranslator = geoBoundingBoxFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setGeoDistanceFilterTranslator(
		GeoDistanceFilterTranslator geoDistanceFilterTranslator) {

		_geoDistanceFilterTranslator = geoDistanceFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setGeoDistanceRangeFilterTranslator(
		GeoDistanceRangeFilterTranslator geoDistanceRangeFilterTranslator) {

		_geoDistanceRangeFilterTranslator = geoDistanceRangeFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setGeoPolygonFilterTranslator(
		GeoPolygonFilterTranslator geoPolygonFilterTranslator) {

		_geoPolygonFilterTranslator = geoPolygonFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setMissingFilterTranslator(
		MissingFilterTranslator missingFilterTranslator) {

		_missingFilterTranslator = missingFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setPrefixFilterTranslator(
		PrefixFilterTranslator prefixFilterTranslator) {

		_prefixFilterTranslator = prefixFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setQueryFilterTranslator(
		QueryFilterTranslator queryFilterTranslator) {

		_queryFilterTranslator = queryFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setRangeTermFilterTranslator(
		RangeTermFilterTranslator rangeTermFilterTranslator) {

		_rangeTermFilterTranslator = rangeTermFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setTermFilterTranslator(
		TermFilterTranslator termFilterTranslator) {

		_termFilterTranslator = termFilterTranslator;
	}

	@Reference(unbind = "-")
	protected void setTermsFilterTranslator(
		TermsFilterTranslator termsFilterTranslator) {

		_termsFilterTranslator = termsFilterTranslator;
	}

	protected String includeCompanyId(
		String filterString, SearchContext searchContext) {

		StringBundler sb = null;

		if (Validator.isNotNull(filterString)) {
			sb = new StringBundler(11);

			sb.append(StringPool.PLUS);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(filterString);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.SPACE);
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(StringPool.PLUS);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(Field.COMPANY_ID);
		sb.append(StringPool.COLON);
		sb.append(searchContext.getCompanyId());
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private BooleanFilterTranslator _booleanQueryTranslator;
	private DateRangeTermFilterTranslator _dateRangeTermFilterTranslator;
	private ExistsFilterTranslator _existsFilterTranslator;
	private GeoBoundingBoxFilterTranslator _geoBoundingBoxFilterTranslator;
	private GeoDistanceFilterTranslator _geoDistanceFilterTranslator;
	private GeoDistanceRangeFilterTranslator _geoDistanceRangeFilterTranslator;
	private GeoPolygonFilterTranslator _geoPolygonFilterTranslator;
	private MissingFilterTranslator _missingFilterTranslator;
	private PrefixFilterTranslator _prefixFilterTranslator;
	private QueryFilterTranslator _queryFilterTranslator;
	private RangeTermFilterTranslator _rangeTermFilterTranslator;
	private TermFilterTranslator _termFilterTranslator;
	private TermsFilterTranslator _termsFilterTranslator;

}