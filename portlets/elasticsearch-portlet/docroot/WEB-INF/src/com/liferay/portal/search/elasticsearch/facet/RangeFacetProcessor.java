/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.elasticsearch.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.RangeFacet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.StringPool;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.facet.range.RangeFacetBuilder;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class RangeFacetProcessor implements FacetProcessor<RangeFacet> {

	@Override
	public void processFacet(
		SearchRequestBuilder searchRequestBuilder, RangeFacet rangeFacet) {

		FacetConfiguration facetConfiguration =
			rangeFacet.getFacetConfiguration();

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			return;
		}

		RangeFacetBuilder rangeFacetBuilder = new RangeFacetBuilder(
			facetConfiguration.getFieldName());

		rangeFacetBuilder.field(facetConfiguration.getFieldName());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject rangeJSONObject = jsonArray.getJSONObject(i);

			String range = rangeJSONObject.getString("range");

			range = range.replace(StringPool.OPEN_BRACKET, StringPool.BLANK);
			range = range.replace(StringPool.CLOSE_BRACKET, StringPool.BLANK);

			String[] rangeParts = range.split(StringPool.SPACE);

			rangeFacetBuilder.addRange(rangeParts[0], rangeParts[2]);
		}

		searchRequestBuilder.addFacet(rangeFacetBuilder);
	}

}