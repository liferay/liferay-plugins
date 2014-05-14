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

package com.liferay.portal.settings;

import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class InMemorySettings implements Settings {

	public InMemorySettings() {
		_map = new HashMap<String, String[]>();
	}

	@Override
	public Settings getDefaultSettings() {
		return null;
	}

	@Override
	public Collection<String> getSetKeys() {
		return _map.keySet();
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String[] values = _map.get(key);

		if (values == null) {
			return defaultValue;
		}

		return values[0];
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		String[] values = _map.get(key);

		if (values == null) {
			return defaultValue;
		}

		return values;
	}

	@Override
	public void reset(String key) {
		_map.remove(key);
	}

	@Override
	public Settings setValue(String key, String value) {
		_map.put(key, new String[] { value });

		return this;
	}

	@Override
	public Settings setValues(String key, String[] values) {
		_map.put(key, values);

		return this;
	}

	@Override
	public void store() throws IOException, ValidatorException {
		throw new UnsupportedOperationException();
	}

	private Map<String, String[]> _map;

}