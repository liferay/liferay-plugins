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

import com.liferay.portal.settings.impl.PortletPreferencesSettings;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class PortletPreferencesSettingsTest {

	public PortletPreferencesSettingsTest() {
		Mockito.when(
			_portletPreferences.getValue(
				Matchers.eq(_PORTLET_PREFERENCES_KEY), Matchers.anyString())
		).thenReturn(
			_PORTLET_PREFERENCES_VALUE
		);

		Mockito.when(
			_portletPreferences.getValues(
				Matchers.eq(_PORTLET_PREFERENCES_ARRAY_KEY),
				(String[])Matchers.any())
		).thenReturn(
			_PORTLET_PREFERENCES_ARRAY_VALUE
		);

		Mockito.when(
			_defaultSettings.getValue(
				Matchers.eq(_DEFAULT_SETTINGS_KEY), Matchers.anyString())
		).thenReturn(
			_DEFAULT_SETTINGS_VALUE
		);

		Mockito.when(
			_defaultSettings.getValues(
				Matchers.eq(_DEFAULT_SETTINGS_ARRAY_KEY),
				(String[])Matchers.any())
		).thenReturn(
			_DEFAULT_SETTINGS_ARRAY_VALUE
		);
	}

	@Test
	public void testGetValuesWithExistingDefaultSettingsKey() {
		Assert.assertArrayEquals(
			_DEFAULT_SETTINGS_ARRAY_VALUE,
			_portletPreferencesSettings.getValues(
				_DEFAULT_SETTINGS_ARRAY_KEY, null));
	}

	@Test
	public void testGetValuesWithExistingPortletPreferencesKey() {
		Assert.assertArrayEquals(
			_PORTLET_PREFERENCES_ARRAY_VALUE,
			_portletPreferencesSettings.getValues(
				_PORTLET_PREFERENCES_ARRAY_KEY, null));
	}

	@Test
	public void testGetValuesWithMissingKey() {
		String[] defaultValue = {"default0", "default1"};

		Assert.assertArrayEquals(
			defaultValue,
			_portletPreferencesSettings.getValues(
				"missing_keys", defaultValue));
	}

	@Test
	public void testGetValueWithExistingDefaultSettingsKey() {
		Assert.assertEquals(
			_DEFAULT_SETTINGS_VALUE,
			_portletPreferencesSettings.getValue(_DEFAULT_SETTINGS_KEY, null));
	}

	@Test
	public void testGetValueWithExistingPortletPreferencesKey() {
		Assert.assertEquals(
			_PORTLET_PREFERENCES_VALUE,
			_portletPreferencesSettings.getValue(
				_PORTLET_PREFERENCES_KEY, null));
	}

	@Test
	public void testGetValueWithMissingKey() {
		Assert.assertEquals(
			"default",
			_portletPreferencesSettings.getValue("missing_key", "default"));
	}

	@Test
	public void testSetValueSetsPropertyInPortletPreferences()
		throws Exception {

		_portletPreferencesSettings.setValue("key", "value");

		Mockito.verify(_portletPreferences).setValue("key", "value");
	}

	@Test
	public void testSetValuesSetsPropertyInPortletPreferences()
		throws Exception {

		String[] values = {"a", "b"};

		_portletPreferencesSettings.setValues("key", values);

		Mockito.verify(_portletPreferences).setValues("key", values);
	}

	@Test
	public void testStoreIsPerformedOnPortletPreferences() throws Exception {
		_portletPreferencesSettings.store();

		Mockito.verify(_portletPreferences).store();
	}

	private static final String _DEFAULT_SETTINGS_ARRAY_KEY = "default_keys";

	private static final String[] _DEFAULT_SETTINGS_ARRAY_VALUE = {
		"default_value0", "default_value1"};

	private static final String _DEFAULT_SETTINGS_KEY = "default_key";

	private static final String _DEFAULT_SETTINGS_VALUE = "default_value";

	private static final String _PORTLET_PREFERENCES_ARRAY_KEY = "portlet_keys";

	private static final String[] _PORTLET_PREFERENCES_ARRAY_VALUE = {
		"portlet_value0", "portlet_value1"};

	private static final String _PORTLET_PREFERENCES_KEY = "portlet_key";

	private static final String _PORTLET_PREFERENCES_VALUE = "portlet_value";

	private Settings _defaultSettings = PowerMockito.mock(Settings.class);
	private PortletPreferences _portletPreferences = PowerMockito.mock(
		PortletPreferences.class);
	private PortletPreferencesSettings _portletPreferencesSettings =
		new PortletPreferencesSettings(_portletPreferences, _defaultSettings);

}