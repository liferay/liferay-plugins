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

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;

/**
 * @author Michael C. Han
 */
public class FacetProcessorUtil {

	public static void processFacet(
		SearchRequestBuilder searchRequestBuilder, Facet facet) {

		FacetProcessor<Facet> facetProcessor = _facetProcessors.get(
			facet.getClass().getName());

		if (facetProcessor == null) {
			facetProcessor = _defaultFacetProcessor;
		}

		facetProcessor.processFacet(searchRequestBuilder, facet);
	}

	public void setDefaultFacetProcessor(
		FacetProcessor<Facet> defaultFacetProcessor) {

		_defaultFacetProcessor = defaultFacetProcessor;
	}

	public void setFacetProcessors(
		Map<String, FacetProcessor<Facet>> facetProcessors) {

		_facetProcessors = facetProcessors;
	}

	private static FacetProcessor<Facet> _defaultFacetProcessor;
	private static Map<String, FacetProcessor<Facet>> _facetProcessors =
		new HashMap<String, FacetProcessor<Facet>>();

}