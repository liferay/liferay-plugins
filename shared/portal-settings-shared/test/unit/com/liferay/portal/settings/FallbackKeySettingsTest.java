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

import org.junit.Assert;
import org.junit.Test;

import org.mockito.InOrder;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class FallbackKeySettingsTest extends PowerMockito {

	public FallbackKeySettingsTest() {
		_settings = mock(Settings.class);

		_fallbackKeys = new FallbackKeys();
		_fallbackKeys.addPath("key1", "key2", "key3");
		_fallbackKeys.addPath("key2", "key7");
		_fallbackKeys.addPath("key3", "key5");

		_fallbackKeySettings = new FallbackKeySettings(
			_settings, _fallbackKeys);
	}

	@Test
	public void testGetValuesWhenConfigured() {
		String[] defaultValues = {"default"};
		String[] mockValues = {"value"};

		when(
			_settings.getValues("key2", null)
		).thenReturn(
			mockValues
		);

		String[] values = _fallbackKeySettings.getValues("key1", defaultValues);

		Assert.assertArrayEquals(mockValues, values);

		verifyGetValues("key1", "key2");
	}

	@Test
	public void testGetValuesWhenUnconfigured() {
		String[] defaultValues = {"default"};

		String[] values = _fallbackKeySettings.getValues("key1", defaultValues);

		Assert.assertArrayEquals(defaultValues, values);

		verifyGetValues("key1", "key2", "key3");
	}

	@Test
	public void testGetValueWhenConfigured() {
		when(
			_settings.getValue("key2", null)
		).thenReturn(
			"value"
		);

		String value = _fallbackKeySettings.getValue("key1", "default");

		Assert.assertEquals("value", value);

		verifyGetValue("key1", "key2");
	}

	@Test
	public void testGetValueWhenUnconfigured() {
		String value = _fallbackKeySettings.getValue("key1", "default");

		Assert.assertEquals("default", value);

		verifyGetValue("key1", "key2", "key3");
	}

	protected void verifyGetValue(String... keys) {
		InOrder inOrder = Mockito.inOrder(_settings);

		for (String key :  keys) {
			inOrder.verify(_settings).getValue(key, null);
		}

		inOrder.verifyNoMoreInteractions();
	}

	protected void verifyGetValues(String... keys) {
		InOrder inOrder = Mockito.inOrder(_settings);

		for (String key :  keys) {
			inOrder.verify(_settings).getValues(key, null);
		}

		inOrder.verifyNoMoreInteractions();
	}

	private FallbackKeys _fallbackKeys;
	private FallbackKeySettings _fallbackKeySettings;
	private Settings _settings;

}