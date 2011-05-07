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

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;

/**
 * @author Raymond Augé
 */
public class SolrFacetFieldCollector implements FacetCollector {

	public SolrFacetFieldCollector(String fieldName, FacetField facetField) {
		_fieldName = fieldName;

		_countMap = new HashMap<String,Count>();

		if (facetField.getValues() != null) {
			for (Count count : facetField.getValues()) {
				_countMap.put(count.getName(), count);
			}
		}
	}

	public String getFieldName() {
		return _fieldName;
	}

	public TermCollector getTermCollector(String term) {
		return new SolrTermCollector(term, (int)_countMap.get(term).getCount());
	}

	public List<TermCollector> getTermCollectors() {
		if (_termCollectors == null) {
			List<TermCollector> list = new ArrayList<TermCollector>();

			for (Map.Entry<String, Count> entry : _countMap.entrySet()) {
				list.add(
					new SolrTermCollector(
						entry.getKey(), (int)entry.getValue().getCount()));
			}

			_termCollectors = list;
		}

		return _termCollectors;
	}

	private Map<String,Count> _countMap;
	private List<TermCollector> _termCollectors;
	private String _fieldName;

}