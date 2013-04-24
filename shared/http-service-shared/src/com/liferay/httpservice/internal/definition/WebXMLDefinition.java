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

package com.liferay.httpservice.internal.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class WebXMLDefinition {

	public void addListenerDefinition(ListenerDefinition listenerDefinition) {
		_listenerDefinitions.add(listenerDefinition);
	}

	public Map<String, String> getContextParameters() {
		return _contextParameters;
	}

	public Map<String, FilterDefinition> getFilterDefinitions() {
		return _filterDefinitions;
	}

	public List<ListenerDefinition> getListenerDefinitions() {
		return _listenerDefinitions;
	}

	public Map<String, ServletDefinition> getServletDefinitions() {
		return _servletDefinitions;
	}

	public void setContextParameter(String name, String value) {
		_contextParameters.put(name, value);
	}

	public void setContextParameters(Map<String, String> contextParameters) {
		_contextParameters = contextParameters;
	}

	public void setFilterDefinition(
		String name, FilterDefinition filterDefinition) {

		_filterDefinitions.put(name, filterDefinition);
	}

	public void setFilterDefinitions(
		Map<String, FilterDefinition> filterDefinitions) {

		_filterDefinitions = filterDefinitions;
	}

	public void setListenerDefinitions(
		List<ListenerDefinition> listenerDefinitions) {

		_listenerDefinitions = listenerDefinitions;
	}

	public void setServletDefinition(
		String name, ServletDefinition servletDefinition) {

		_servletDefinitions.put(name, servletDefinition);
	}

	public void setServletDefinitions(
		Map<String, ServletDefinition> servletDefinitions) {

		_servletDefinitions = servletDefinitions;
	}

	private Map<String, String> _contextParameters =
		new HashMap<String, String>();
	private Map<String, FilterDefinition> _filterDefinitions =
		new HashMap<String, FilterDefinition>();
	private List<ListenerDefinition> _listenerDefinitions =
		new ArrayList<ListenerDefinition>();
	private Map<String, ServletDefinition> _servletDefinitions =
		new HashMap<String, ServletDefinition>();

}