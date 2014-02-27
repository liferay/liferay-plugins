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

import com.liferay.portlet.PortletPreferencesImpl;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jorge Ferrer
 */
public abstract class BaseSettingsTestCase {

	@Test
	public void testGetNonnexistentValue() {
		String value = _settings.getValue("nonexistentName", null);

		Assert.assertNull(value);
	}

	@Test
	public void testGetValidValue() {
		String value = _settings.getValue(_SETTINGS_NAME, null);

		Assert.assertEquals(_SETTINGS_VALUE, value);
	}

	@Test
	public void testSetExistentValue() {
		_settings.setValue(_SETTINGS_NAME, "newValue");

		String value = _settings.getValue(_SETTINGS_NAME, null);

		Assert.assertEquals("newValue", value);
	}

	@Test
	public void testSetNonexistentValue() {
		_settings.setValue("nonexistentName", "newValue");

		String value = _settings.getValue("nonexistentName", null);

		Assert.assertEquals("newValue", value);
	}

	protected PortletPreferences getPortletPreferences()
		throws ReadOnlyException {

		PortletPreferences portletPreferences = new PortletPreferencesImpl();

		portletPreferences.setValue(_SETTINGS_NAME, _SETTINGS_VALUE);

		return portletPreferences;
	}

	protected Settings _settings;

	private static final String _SETTINGS_NAME = "settingsName";

	private static final String _SETTINGS_VALUE = "settingsValue";

}