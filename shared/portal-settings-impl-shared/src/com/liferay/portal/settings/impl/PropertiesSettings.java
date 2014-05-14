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

package com.liferay.portal.settings.impl;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.settings.Settings;
import com.liferay.util.ContentUtil;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author Jorge Ferrer
 * @author Iván Zaera
 */
public class PropertiesSettings implements Settings {

	public PropertiesSettings(Properties properties) {
		_properties = properties;
	}

	public Settings getDefaultSettings() {
		return null;
	}

	@Override
	public Collection<String> getSetKeys() {
		Enumeration<?> propertyNames = _properties.propertyNames();

		Set<String> setKeys = new HashSet<String>();

		while (propertyNames.hasMoreElements()) {
			String propertyName = propertyNames.nextElement().toString();

			setKeys.add(propertyName);
		}

		return setKeys;
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

		if (isLocationVariable("resource", value)) {
			return ContentUtil.get(getLocation("resource", value));
		}

		return value;
	}

	private String getLocation(String protocol, String value) {
		return value.substring(protocol.length() + 3, value.length() - 1);
	}

	private boolean isLocationVariable(String protocol, String value) {
		if (value == null) {
			return false;
		}

		String prefix =
			StringPool.DOLLAR + StringPool.OPEN_CURLY_BRACE + protocol +
				StringPool.COLON;

		if (value.startsWith(prefix) &&
			value.endsWith(StringPool.CLOSE_CURLY_BRACE)) {

			return true;
		}

		return false;
	}

	private Properties _properties;

}