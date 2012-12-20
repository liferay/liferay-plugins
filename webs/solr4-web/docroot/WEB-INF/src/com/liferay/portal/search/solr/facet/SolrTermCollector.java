/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.search.facet.collector.TermCollector;

/**
 * @author Raymond Augé
 */
public class SolrTermCollector implements TermCollector {

	public SolrTermCollector(String term, int frequency) {
		_term = term;
		_frequency = frequency;
	}

	public int getFrequency() {
		return _frequency;
	}

	public String getTerm() {
		return _term;
	}

	private int _frequency;
	private String _term;

}