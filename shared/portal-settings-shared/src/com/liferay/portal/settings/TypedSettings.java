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

import com.liferay.portal.kernel.util.GetterUtil;

import java.io.IOException;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class TypedSettings implements Settings {

	public TypedSettings(Settings settings) {
		_settings = settings;
	}

	public boolean getBooleanValue(String key, boolean defaultValue) {
		String value = getValue(key, String.valueOf(defaultValue));

		return GetterUtil.getBoolean(value);
	}

	}

	public int getIntegerValue(String key, int defaultValue) {
		String value = getValue(key, String.valueOf(defaultValue));

		return GetterUtil.getInteger(value);
	}

	public long getLongValue(String key, long defaultValue) {
		String value = getValue(key, String.valueOf(defaultValue));

		return GetterUtil.getLong(value);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		return _settings.getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		return _settings.getValues(key, defaultValue);
	}

	public Settings setBooleanValue(String key, boolean value) {
		return setValue(key, String.valueOf(value));
	}

	public Settings setIntegerValue(String key, int value) {
		return setValue(key, String.valueOf(value));
	}

	public Settings setLongValue(String key, long value) {
		return setValue(key, String.valueOf(value));
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

	private Settings _settings;

}