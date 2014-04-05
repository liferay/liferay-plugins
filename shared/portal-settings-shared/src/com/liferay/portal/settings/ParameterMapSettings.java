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

import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.Map;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class ParameterMapSettings implements Settings {

	public static final String PREFERENCES_PREFIX = "preferences--";

	public static final String SETTINGS_PREFIX = "settings--";

	public ParameterMapSettings(
		Settings settings, Map<String, String[]> parameterMap) {

		_settings = settings;
		_parameterMap = parameterMap;
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String[] values = getParameterValue(key);

		if (values != null) {
			return values[0];
		}

		return _settings.getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		String[] values = getParameterValue(key);

		if (values != null) {
			return values;
		}

		return _settings.getValues(key, defaultValue);
	}

	@Override
	public void reset(String key) {
		_settings.reset(key);
	}

	@Override
	public Settings setValue(String key, String value) {
		return _settings.setValue(key, value);
	}

	@Override
	public Settings setValues(String key, String[] values) {
		return _settings.setValues(key, values);
	}

	@Override
	public void store() throws IOException, ValidatorException {
		_settings.store();
	}

	protected String[] getParameterValue(String key) {
		String[] values = _parameterMap.get(key);

		if (values == null) {
			values = _parameterMap.get(
				PREFERENCES_PREFIX + key + StringPool.DOUBLE_DASH);
		}

		if (values == null) {
			values = _parameterMap.get(
				SETTINGS_PREFIX + key + StringPool.DOUBLE_DASH);
		}

		return values;
	}

	private Map<String, String[]> _parameterMap;
	private Settings _settings;

}