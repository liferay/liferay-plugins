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
import com.liferay.portal.settings.Settings;

import java.io.IOException;

import java.util.Properties;

import javax.portlet.ValidatorException;

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
		return _properties.getProperty(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		String[] values = StringUtil.split(_properties.getProperty(key));

		if (ArrayUtil.isNotEmpty(values)) {
			return values;
		}

		return defaultValue;
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
	public void store() throws IOException, ValidatorException {
		throw new UnsupportedOperationException();
	}

	private Properties _properties;

}