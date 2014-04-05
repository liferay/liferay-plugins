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

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class ParameterMapSettingsTest extends PowerMockito {

	public ParameterMapSettingsTest() {
		_settings = mock(Settings.class);

		_parameterMapSettings = new ParameterMapSettings(
			_settings, _parameterMap);
	}

	@Test
	public void testGetValuesWhenFoundInParameterMap() {
		String[] values = {"requestValue1", "requestValue2"};

		_parameterMap.put("preferences--key--", values);

		Assert.assertArrayEquals(
			values,
			_parameterMapSettings.getValues(
				"key", new String[] {"defaultValue"}));
	}

	@Test
	public void testGetValuesWhenFoundInSettings() {
		String[] values = {"settingsValue1", "settingsValue2"};

		mockSettingsGetValues("key", values);

		Assert.assertArrayEquals(
			values,
			_parameterMapSettings.getValues(
				"key", new String[] {"defaultValue"}));
	}

	@Test
	public void testGetValueWhenFoundInParameterMap() {
		_parameterMap.put("preferences--key--", new String[] {"requestValue"});

		mockSettingsGetValue("key", "settingsValue");

		Assert.assertEquals(
			"requestValue",
			_parameterMapSettings.getValue("key", "defaultValue"));
	}

	@Test
	public void testGetValueWhenFoundInSettings() {
		mockSettingsGetValue("key", "settingsValue");

		Assert.assertEquals(
			"settingsValue",
			_parameterMapSettings.getValue("key", "defaultValue"));
	}

	protected void mockSettingsGetValue(String key, String value) {
		when(
			_settings.getValue(Matchers.eq(key), Matchers.anyString())
		).thenReturn(
			value
		);
	}

	protected void mockSettingsGetValues(String key, String... values) {
		when(
			_settings.getValues(Matchers.eq(key), Matchers.any(String[].class))
		).thenReturn(
			values
		);
	}

	private Map<String, String[]> _parameterMap =
		new HashMap<String, String[]>();
	private ParameterMapSettings _parameterMapSettings;
	private Settings _settings;

}