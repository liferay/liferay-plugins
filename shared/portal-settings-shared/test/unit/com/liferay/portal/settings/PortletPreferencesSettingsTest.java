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

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class PortletPreferencesSettingsTest {

	@Before
	public void setUp() {
		_portletPreferences = PowerMockito.mock(PortletPreferences.class);

		Mockito.when(
			_portletPreferences.getValue(
				Matchers.eq(_PORTLET_PREFERENCES_SINGLE_KEY),
				Matchers.anyString())
		).thenReturn(
			_PORTLET_PREFERENCES_SINGLE_VALUE
		);

		Mockito.when(
			_portletPreferences.getValues(
				Matchers.eq(_PORTLET_PREFERENCES_MULTIPLE_KEY),
				(String[])Matchers.any())
		).thenReturn(
			_PORTLET_PREFERENCES_MULTIPLE_VALUES
		);

		Settings defaultSettings = PowerMockito.mock(Settings.class);

		Mockito.when(
			defaultSettings.getValue(
				Matchers.eq(_DEFAULT_SETTINGS_SINGLE_KEY), Matchers.anyString())
		).thenReturn(
			_DEFAULT_SETTINGS_SINGLE_VALUE
		);

		Mockito.when(
			defaultSettings.getValues(
				Matchers.eq(_DEFAULT_SETTINGS_MULTIPLE_KEY),
				(String[])Matchers.any())
		).thenReturn(
			_DEFAULT_SETTINGS_MULTIPLE_VALUES
		);

		_portletPreferencesSettings = new PortletPreferencesSettings(
			_portletPreferences, defaultSettings);
	}

	@Test
	public void testGetValuesWithExistingDefaultSettingsKey() {
		Assert.assertArrayEquals(
			_DEFAULT_SETTINGS_MULTIPLE_VALUES,
			_portletPreferencesSettings.getValues(
				_DEFAULT_SETTINGS_MULTIPLE_KEY, null));
	}

	@Test
	public void testGetValuesWithExistingPortletPreferencesKey() {
		Assert.assertArrayEquals(
			_PORTLET_PREFERENCES_MULTIPLE_VALUES,
			_portletPreferencesSettings.getValues(
				_PORTLET_PREFERENCES_MULTIPLE_KEY, null));
	}

	@Test
	public void testGetValuesWithMissingKey() {
		String[] defaultValue = {"a", "b"};

		Assert.assertArrayEquals(
			defaultValue,
			_portletPreferencesSettings.getValues("missingKeys", defaultValue));
	}

	@Test
	public void testGetValueWithExistingDefaultSettingsKey() {
		Assert.assertEquals(
			_DEFAULT_SETTINGS_SINGLE_VALUE,
			_portletPreferencesSettings.getValue(
				_DEFAULT_SETTINGS_SINGLE_KEY, null));
	}

	@Test
	public void testGetValueWithExistingPortletPreferencesKey() {
		Assert.assertEquals(
			_PORTLET_PREFERENCES_SINGLE_VALUE,
			_portletPreferencesSettings.getValue(
				_PORTLET_PREFERENCES_SINGLE_KEY, null));
	}

	@Test
	public void testGetValueWithMissingKey() {
		Assert.assertEquals(
			"default",
			_portletPreferencesSettings.getValue("missingKey", "default"));
	}

	@Test
	public void testSetValueSetsPropertyInPortletPreferences()
		throws Exception {

		_portletPreferencesSettings.setValue("key", "value");

		Mockito.verify(_portletPreferences);

		_portletPreferences.setValue("key", "value");
	}

	@Test
	public void testSetValuesSetsPropertyInPortletPreferences()
		throws Exception {

		String[] values = {"a", "b"};

		_portletPreferencesSettings.setValues("key", values);

		Mockito.verify(_portletPreferences);

		_portletPreferences.setValues("key", values);
	}

	@Test
	public void testStoreIsPerformedOnPortletPreferences() throws Exception {
		_portletPreferencesSettings.store();

		Mockito.verify(_portletPreferences);

		_portletPreferences.store();
	}

	private static final String _DEFAULT_SETTINGS_MULTIPLE_KEY = "defaultKeys";

	private static final String[] _DEFAULT_SETTINGS_MULTIPLE_VALUES =
		{"defaultValue0", "defaultValue1"};

	private static final String _DEFAULT_SETTINGS_SINGLE_KEY = "defaultKey";

	private static final String _DEFAULT_SETTINGS_SINGLE_VALUE = "defaultValue";

	private static final String _PORTLET_PREFERENCES_MULTIPLE_KEY =
		"portletKeys";

	private static final String[] _PORTLET_PREFERENCES_MULTIPLE_VALUES =
		{"portletValue0", "portletValue1"};

	private static final String _PORTLET_PREFERENCES_SINGLE_KEY = "portletKey";

	private static final String _PORTLET_PREFERENCES_SINGLE_VALUE =
		"portletValue";

	private PortletPreferences _portletPreferences;
	private PortletPreferencesSettings _portletPreferencesSettings;

}