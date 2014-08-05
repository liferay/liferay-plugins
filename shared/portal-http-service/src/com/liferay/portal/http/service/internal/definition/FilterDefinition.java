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

package com.liferay.portal.http.service.internal.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

/**
 * @author Raymond Augé
 */
public class FilterDefinition {

	public void addURLPattern(String urlPattern) {
		_urlPatterns.add(urlPattern);
	}

	public Filter getFilter() {
		return _filter;
	}

	public Map<String, String> getInitParameters() {
		return _initParameters;
	}

	public String getName() {
		return _name;
	}

	public List<String> getURLPatterns() {
		return _urlPatterns;
	}

	public void setFilter(Filter filter) {
		_filter = filter;
	}

	public void setInitParameter(String key, String value) {
		_initParameters.put(key, value);
	}

	public void setInitParameters(Map<String, String> initParameters) {
		_initParameters = initParameters;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setURLPatterns(List<String> urlPatterns) {
		_urlPatterns = urlPatterns;
	}

	private Filter _filter;
	private Map<String, String> _initParameters = new HashMap<String, String>();
	private String _name;
	private List<String> _urlPatterns = new ArrayList<String>();

}