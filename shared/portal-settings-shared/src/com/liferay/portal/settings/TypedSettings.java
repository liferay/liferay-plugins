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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 * @author Jorge Ferrer
 */
public class TypedSettings implements Settings {

	public TypedSettings(Settings settings) {
		this(
			settings, LocaleUtil.getSiteDefault(),
			LanguageUtil.getAvailableLocales());
	}

	public TypedSettings(
		Settings settings, Locale defaultLocale, Locale... availableLocales) {

		_settings = settings;
		_defaultLocale = defaultLocale;
		_availableLocales = availableLocales;
	}

	public boolean getBooleanValue(String key) {
		return getBooleanValue(key, false);
	}

	public boolean getBooleanValue(String key, boolean defaultValue) {
		String value = getValue(key, null);

		return GetterUtil.getBoolean(value, defaultValue);
	}

	public double getDoubleValue(String key) {
		return getDoubleValue(key, 0);
	}

	public double getDoubleValue(String key, double defaultValue) {
		String value = getValue(key, null);

		return GetterUtil.getDouble(value, defaultValue);
	}

	public float getFloatValue(String key) {
		return getFloatValue(key, 0);
	}

	public float getFloatValue(String key, float defaultValue) {
		String value = getValue(key, null);

		return GetterUtil.getFloat(value, defaultValue);
	}

	public int getIntegerValue(String key) {
		return getIntegerValue(key, 0);
	}

	public int getIntegerValue(String key, int defaultValue) {
		String value = getValue(key, null);

		return GetterUtil.getInteger(value, defaultValue);
	}

	public LocalizedValue getLocalizedValue(String key) {
		LocalizedValue localizedValue = new LocalizedValue(
			key, _defaultLocale, _availableLocales);

		for (Locale locale : _availableLocales) {
			String localizedPreference = LocalizationUtil.getLocalizedName(
				key, LocaleUtil.toLanguageId(locale));

			localizedValue.put(locale, getValue(localizedPreference, null));
		}

		String defaultValue = localizedValue.get(_defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return localizedValue;
		}

		localizedValue.put(_defaultLocale, getValue(key, null));

		return localizedValue;
	}

	public long getLongValue(String key) {
		return getLongValue(key, 0);
	}

	public long getLongValue(String key, long defaultValue) {
		String value = getValue(key, null);

		return GetterUtil.getLong(value, defaultValue);
	}

	public String getValue(String key) {
		return getValue(key, StringPool.BLANK);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		return _settings.getValue(key, defaultValue);
	}

	public String[] getValues(String key) {
		return getValues(key, StringPool.EMPTY_ARRAY);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		return _settings.getValues(key, defaultValue);
	}

	@Override
	public void reset(String key) {
		_settings.reset(key);
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

	private Locale[] _availableLocales;
	private Locale _defaultLocale;
	private Settings _settings;

}