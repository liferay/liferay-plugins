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

package com.liferay.portal.settings;

import java.io.IOException;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class FallbackSettings implements Settings {

	public FallbackSettings(Settings settings, FallbackKeys fallbackKeys) {
		_settings = settings;
		_fallbackKeys = fallbackKeys;
	}

	@Override
	public String getValue(String key, String defaultValue) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}

		String value = _settings.getValue(key, null);

		if (value != null) {
			return value;
		}

		String[] fallbackKeysArray = _fallbackKeys.get(key);

		for (String fallbackKey : fallbackKeysArray) {
			value = _settings.getValue(fallbackKey, null);

			if (value != null) {
				return value;
			}
		}

		return defaultValue;
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}

		String[] values = _settings.getValues(key, null);

		if (values != null) {
			return values;
		}

		String[] fallbackKeysArray = _fallbackKeys.get(key);

		for (String fallbackKey : fallbackKeysArray) {
			values = _settings.getValues(fallbackKey, null);

			if (values != null) {
				return values;
			}
		}

		return defaultValue;
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

	private FallbackKeys _fallbackKeys;
	private Settings _settings;

}