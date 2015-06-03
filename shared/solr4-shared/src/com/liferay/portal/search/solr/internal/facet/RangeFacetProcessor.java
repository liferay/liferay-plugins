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

package com.liferay.portal.search.solr.internal.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.facet.FacetProcessor;

import org.apache.solr.client.solrj.SolrQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {"class.name=com.liferay.portal.kernel.search.facet.RangeFacet"}
)
public class RangeFacetProcessor implements FacetProcessor<SolrQuery> {

	@Override
	public void processFacet(SolrQuery solrQuery, Facet facet) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		solrQuery.addFacetField(facetConfiguration.getFieldName());

		JSONObject dataJSONObject = facetConfiguration.getData();

		JSONArray rangesJSONArray = dataJSONObject.getJSONArray("ranges");

		if (rangesJSONArray == null) {
			return;
		}

		for (int i = 0; i < rangesJSONArray.length(); i++) {
			JSONObject rangeJSONObject = rangesJSONArray.getJSONObject(i);

			String range = rangeJSONObject.getString("range");

			String facetQuery =
				facetConfiguration.getFieldName() + StringPool.COLON + range;

			solrQuery.addFacetQuery(facetQuery);
		}
	}

}