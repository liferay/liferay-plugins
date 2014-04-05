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

package com.liferay.portal.search.elasticsearch.facet;

import com.liferay.portal.kernel.search.facet.collector.DefaultTermCollector;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.range.RangeFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class ElasticsearchFacetFieldCollector implements FacetCollector {

	public ElasticsearchFacetFieldCollector(Facet facet) {
		if (facet instanceof RangeFacet) {
			initialize((RangeFacet)facet);
		}
		else {
			initialize((TermsFacet)facet);
		}
	}

	@Override
	public String getFieldName() {
		return _facet.getName();
	}

	@Override
	public TermCollector getTermCollector(String term) {
		int count = 0;

		if (_counts.containsKey(term)) {
			count = _counts.get(term);
		}

		return new DefaultTermCollector(term, count);
	}

	@Override
	public List<TermCollector> getTermCollectors() {
		if (_termCollectors != null) {
			return _termCollectors;
		}

		List<TermCollector> termCollectors = new ArrayList<TermCollector>();

		for (Map.Entry<String, Integer> entry : _counts.entrySet()) {
			TermCollector termCollector = new DefaultTermCollector(
				entry.getKey(), entry.getValue());

			termCollectors.add(termCollector);
		}

		_termCollectors = termCollectors;

		return _termCollectors;
	}

	protected void initialize(RangeFacet rangeFacet) {
		_facet = rangeFacet;

		for (RangeFacet.Entry entry : rangeFacet.getEntries()) {
			StringBundler sb = new StringBundler(5);

			sb.append(StringPool.OPEN_BRACKET);
			sb.append(entry.getFromAsString());
			sb.append(_TO_STRING);
			sb.append(entry.getToAsString());
			sb.append(StringPool.CLOSE_BRACKET);

			_counts.put(sb.toString(), (int)entry.getCount());
		}
	}

	protected void initialize(TermsFacet termsFacet) {
		_facet = termsFacet;

		for (TermsFacet.Entry entry : termsFacet.getEntries()) {
			_counts.put(entry.getTerm().string(), entry.getCount());
		}
	}

	private static final String _TO_STRING = " TO ";

	private Map<String, Integer> _counts =
		new ConcurrentHashMap<String, Integer>();
	private Facet _facet;
	private List<TermCollector> _termCollectors;

}