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

package com.liferay.portal.search.solr.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author Michael C. Han
 * @author André de Oliveira
 * @author Tibor Lipusz
 */
public class RangeFacetProcessor implements FacetProcessor<SolrQuery> {

	@Override
	public void processFacet(SolrQuery solrQuery, Facet facet) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		solrQuery.addFacetField(facetConfiguration.getFieldName());

		addConfigurationRanges(facetConfiguration, solrQuery);

		addCustomRange(facet, solrQuery);
	}

	protected void addConfigurationRanges(
		FacetConfiguration facetConfiguration, SolrQuery solrQuery) {

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			return;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject rangeJSONObject = jsonArray.getJSONObject(i);

			String range = rangeJSONObject.getString("range");

			String facetQuery =
				facetConfiguration.getFieldName() + StringPool.COLON + range;

			solrQuery.addFacetQuery(facetQuery);
		}
	}

	protected void addCustomRange(Facet facet, SolrQuery solrQuery) {
		SearchContext searchContext = facet.getSearchContext();

		String range = GetterUtil.getString(
			searchContext.getAttribute(facet.getFieldId()));

		if (Validator.isNull(range)) {
			return;
		}

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		String facetQuery =
			facetConfiguration.getFieldName() + StringPool.COLON + range;

		solrQuery.addFacetQuery(facetQuery);
	}

}