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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.settings.Settings;
import com.liferay.util.xml.XMLFormatter;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Jorge Ferrer
 * @author Iván Zaera
 */
public class PortletPreferencesSettings implements Settings {

	public PortletPreferencesSettings(PortletPreferences portletPreferences) {
		this(portletPreferences, null);
	}

	public PortletPreferencesSettings(
		PortletPreferences portletPreferences, Settings defaultSettings) {

		_portletPreferences = portletPreferences;
		_defaultSettings = defaultSettings;
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = normalizeValue(_portletPreferences.getValue(key, null));

		if (isNull(value) && (_defaultSettings != null)) {
			value = _defaultSettings.getValue(key, defaultValue);
		}

		if (isNull(value)) {
			value = defaultValue;
		}

		return value;
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		String[] values = normalizeValues(
			_portletPreferences.getValues(key, null));

		if (ArrayUtil.isEmpty(values) && (_defaultSettings != null)) {
			values = _defaultSettings.getValues(key, defaultValue);
		}

		if (ArrayUtil.isEmpty(values)) {
			values = defaultValue;
		}

		return normalizeValues(values);
	}

	@Override
	public void reset(String key) {
		try {
			_portletPreferences.reset(key);
		}
		catch (ReadOnlyException roe) {
			_log.error(
				"Portlet preferences used to persist settings should never " +
					"be read only",
				roe);
		}
	}

	@Override
	public Settings setValue(String key, String value) {
		try {
			_portletPreferences.setValue(key, value);
		}
		catch (ReadOnlyException roe) {
			_log.error(
				"Portlet preferences used to persist settings should never " +
					"be read only",
				roe);
		}

		return this;
	}

	@Override
	public Settings setValues(String key, String[] values) {
		try {
			_portletPreferences.setValues(key, values);
		}
		catch (ReadOnlyException roe) {
			_log.error(
				"Portlet preferences used to persist settings should never " +
					"be read only",
				roe);
		}

		return this;
	}

	@Override
	public void store() throws IOException, ValidatorException {
		_portletPreferences.store();
	}

	protected boolean isNull(String value) {
		if ((value == null) || value.equals(_NULL_VALUE)) {
			return true;
		}

		return false;
	}

	protected String normalizeValue(String value) {
		if (isNull(value)) {
			return null;
		}

		return XMLFormatter.fromCompactSafe(value);
	}

	protected String[] normalizeValues(String[] values) {
		if (values == null) {
			return null;
		}

		if (values.length == 1) {
			String actualValue = normalizeValue(values[0]);

			if (actualValue == null) {
				return null;
			}

			return new String[] {actualValue};
		}

		String[] actualValues = new String[values.length];

		for (int i = 0; i < actualValues.length; i++) {
			actualValues[i] = normalizeValue(values[i]);
		}

		return actualValues;
	}

	private static final String _NULL_VALUE = "NULL_VALUE";

	private static Log _log = LogFactoryUtil.getLog(
		PortletPreferencesSettings.class);

	private Settings _defaultSettings;
	private PortletPreferences _portletPreferences;

}