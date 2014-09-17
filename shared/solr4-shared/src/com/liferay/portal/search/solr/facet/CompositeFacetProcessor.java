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

import com.liferay.portal.kernel.search.facet.Facet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class CompositeFacetProcessor<T> implements FacetProcessor<T> {

	public void processFacet(T searchQuery, Facet facet) {
		Class<?> clazz = facet.getClass();

		FacetProcessor facetProcessor = _facetProcessors.get(clazz.getName());

		if (facetProcessor == null) {
			facetProcessor = _defaultFacetProcessor;
		}

		facetProcessor.processFacet(searchQuery, facet);
	}

	public void setDefaultFacetProcessor(
		FacetProcessor<?> defaultFacetProcessor) {

		_defaultFacetProcessor = defaultFacetProcessor;
	}

	public void setFacetProcessors(
		Map<String, FacetProcessor<?>> facetProcessors) {

		_facetProcessors = facetProcessors;
	}

	private FacetProcessor _defaultFacetProcessor;
	private Map<String, FacetProcessor<?>> _facetProcessors =
		new HashMap<String, FacetProcessor<?>>();

}