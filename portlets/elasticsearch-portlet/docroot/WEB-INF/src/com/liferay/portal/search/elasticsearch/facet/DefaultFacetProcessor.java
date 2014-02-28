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

import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class DefaultFacetProcessor implements FacetProcessor<Facet> {

	@Override
	public void processFacet(
		SearchRequestBuilder searchRequestBuilder, Facet facet) {

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		String fieldName = facetConfiguration.getFieldName();

		TermsFacetBuilder termsFacetBuilder = new TermsFacetBuilder(fieldName);

		termsFacetBuilder.field(fieldName);

		searchRequestBuilder.addFacet(termsFacetBuilder);
	}

}