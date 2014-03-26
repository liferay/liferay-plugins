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

package com.liferay.portal.settings.impl;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.settings.Settings;
import com.liferay.util.ContentUtil;

import java.util.Properties;

/**
 * @author Jorge Ferrer
 * @author Iván Zaera
 */
public class PropertiesSettings implements Settings {

	public PropertiesSettings(Properties properties) {
		_properties = properties;
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = getProperty(key);

		if (Validator.isNotNull(value)) {
			return value;
		}

		return defaultValue;
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		String[] values = StringUtil.split(getProperty(key));

		if (ArrayUtil.isNotEmpty(values)) {
			return values;
		}

		return defaultValue;
	}

	@Override
	public void reset(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Settings setValue(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Settings setValues(String key, String[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void store() {
		throw new UnsupportedOperationException();
	}

	protected String getProperty(String key) {
		String value = _properties.getProperty(key);

		if (value != null) {
			if (isURLValue("resource", value)) {
				return ContentUtil.get(getURLValue("resource", value));
			}
		}

		return value;
	}

	private String getURLValue(String protocol, String value) {
		return value.substring(protocol.length() + 3, value.length() - 1);
	}

	private boolean isURLValue(String protocol, String value) {
		String prefix = "${" + protocol + ":";

		if (value.startsWith(prefix) && value.endsWith("}")) {
			return true;
		}

		return false;
	}

	private Properties _properties;

}