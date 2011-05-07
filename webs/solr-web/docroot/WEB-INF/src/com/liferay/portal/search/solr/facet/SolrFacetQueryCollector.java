/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class SolrFacetQueryCollector implements FacetCollector {

	public SolrFacetQueryCollector(
		String fieldName, Map<String, Integer> facetQueries) {

		_fieldName = fieldName;
		_termMap = new HashMap<String,Integer>();

		if (!facetQueries.isEmpty()) {
			for (Map.Entry<String,Integer> entry : facetQueries.entrySet()) {
				_termMap.put(_getTerm(entry.getKey()), entry.getValue());
			}
		}
	}

	public String getFieldName() {
		return _fieldName;
	}

	public TermCollector getTermCollector(String term) {
		return new SolrTermCollector(term, _termMap.get(term).intValue());
	}

	public List<TermCollector> getTermCollectors() {
		if (_termCollectors == null) {
			List<TermCollector> list = new ArrayList<TermCollector>();

			for (Map.Entry<String, Integer> entry : _termMap.entrySet()) {
				list.add(
					new SolrTermCollector(
						entry.getKey(), entry.getValue().intValue()));
			}

			_termCollectors = list;
		}

		return _termCollectors;
	}

	private String _getTerm(String term) {
		return term.substring(_fieldName.length() + 1);
	}

	private List<TermCollector> _termCollectors;
	private Map<String,Integer> _termMap;
	private String _fieldName;

}