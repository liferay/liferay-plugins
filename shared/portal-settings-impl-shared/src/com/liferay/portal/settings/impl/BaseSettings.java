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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.settings.Settings;
import com.liferay.util.xml.XMLFormatter;

import java.io.IOException;

import java.util.Properties;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Raymond Augé
 * @author Jorge Ferrer
 */
public abstract class BaseSettings implements Settings {

	@Override
	public String getValue(String key, String defaultValue) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		String value = null;

		if (portletInstancePortletPreferences != null) {
			value = portletInstancePortletPreferences.getValue(key, null);
		}

		if (_isNull(value) && (groupPortletPreferences != null)) {
			value = groupPortletPreferences.getValue(key, null);
		}

		if (_isNull(value) && (companyPortletPreferences != null)) {
			value = companyPortletPreferences.getValue(key, null);
		}

		if (_isNull(value) && (portalProperties != null)) {
			value = portalProperties.getProperty(key, null);
		}

		if (!_isNull(value)) {
			return normalizeValue(value);
		}

		return normalizeValue(defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		String[] values = groupPortletPreferences.getValues(key, defaultValue);

		if (portletInstancePortletPreferences != null) {
			values = portletInstancePortletPreferences.getValues(key, null);
		}

		if (ArrayUtil.isEmpty(values) && (groupPortletPreferences != null)) {
			values = groupPortletPreferences.getValues(key, null);
		}

		if (ArrayUtil.isEmpty(values) && (companyPortletPreferences != null)) {
			values = companyPortletPreferences.getValues(key, null);
		}

		if (ArrayUtil.isEmpty(values) && (portalProperties != null)) {
			values = StringUtil.split(portalProperties.getProperty(key));
		}

		if (ArrayUtil.isNotEmpty(values)) {
			return normalizeValues(values);
		}

		return normalizeValues(defaultValue);
	}

	@Override
	public Settings setValue(String key, String value) {
		try {
			PortletPreferences writeablePortletPreferences =
				getWriteablePortletPreferences();

			writeablePortletPreferences.setValue(key, value);
		}
		catch (ReadOnlyException roe) {
			_log.error("This should never happen", roe);
		}

		return this;
	}

	@Override
	public Settings setValues(String key, String[] values) {
		try {
			PortletPreferences writeablePortletPreferences =
				getWriteablePortletPreferences();

			writeablePortletPreferences.setValues(key, values);
		}
		catch (ReadOnlyException roe) {
			_log.error("This should never happen", roe);
		}

		return this;
	}

	public void store() throws IOException, ValidatorException {
		PortletPreferences writeablePortletPreferences =
			getWriteablePortletPreferences();

		writeablePortletPreferences.store();
	}

	protected BaseSettings() {
	}

	protected abstract PortletPreferences getWriteablePortletPreferences();

	protected String normalizeValue(String value) {
		if (_isNull(value)) {
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

	protected PortletPreferences companyPortletPreferences;
	protected PortletPreferences groupPortletPreferences;
	protected Properties portalProperties;
	protected PortletPreferences portletInstancePortletPreferences;

	private boolean _isNull(String value) {
		if ((value == null) || value.equals(_NULL_VALUE)) {
			return true;
		}

		return false;
	}

	private static final String _NULL_VALUE = "NULL_VALUE";

	private static Log _log = LogFactoryUtil.getLog(BaseSettings.class);

}