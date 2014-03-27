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

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.util.LocalizationImpl;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class TypedSettingsTest extends PowerMockito {

	public TypedSettingsTest() {
		LocalizationUtil localizationUtil = new LocalizationUtil();

		localizationUtil.setLocalization(new LocalizationImpl());

		_settings = mock(Settings.class);

		when(_settings.getValue(_KEY, null)).thenReturn("valueDefault");

		when(_settings.getValue(_KEY + "_es_ES", null)).thenReturn(
			"value_es_ES");

		when(_settings.getValue(_KEY + "_en_US", null)).thenReturn(
			"value_en_US");

		when(_settings.getValue(_KEY + "_en_GB", null)).thenReturn(
			"value_en_GB");

		_typedSettings = new TypedSettings(
			_settings, _DEFAULT_LOCALE, _AVAILABLE_LOCALES);
	}

	@Test
	public void testGetLocalizedValue() {
		LocalizedValue localizedValue = _typedSettings.getLocalizedValue(_KEY);

		Assert.assertEquals("value_es_ES", localizedValue.getDefaultValue());
		Assert.assertEquals(
			"value_es_ES", localizedValue.get(new Locale("es", "ES")));
		Assert.assertEquals(
			"value_en_US", localizedValue.get(new Locale("en", "US")));
		Assert.assertEquals(
			"value_en_GB", localizedValue.get(new Locale("en", "GB")));
	}

	private static final Locale[] _AVAILABLE_LOCALES = {
		new Locale("es", "ES"), new Locale("en", "US"), new Locale("en", "GB")
	};

	private static final Locale _DEFAULT_LOCALE = new Locale("es", "ES");

	private static final String _KEY = "key";

	private Settings _settings;
	private TypedSettings _typedSettings;

}