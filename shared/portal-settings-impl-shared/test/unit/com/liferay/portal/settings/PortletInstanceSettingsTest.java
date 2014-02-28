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

import com.liferay.portal.settings.impl.PortletInstanceSettings;
import com.liferay.portlet.PortletPreferencesImpl;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jorge Ferrer
 */
public class PortletInstanceSettingsTest extends ServiceGroupSettingsTest {

	@Before
	@Override
	public void setUp() throws Exception {
		_settings = new PortletInstanceSettings(getPortletPreferences());
	}

	@Test
	public void testGetValueFromGroupPortletPreferences() throws Exception {
		setGroupPortletPreferences(
			_GROUP_PORTLET_PREFERENCES_NAME, _GROUP_PORTLET_PREFERENCES_VALUE);

		String value = _settings.getValue(
			_GROUP_PORTLET_PREFERENCES_NAME, null);

		Assert.assertEquals(_GROUP_PORTLET_PREFERENCES_VALUE, value);
	}

	protected void setGroupPortletPreferences(String name, String value)
		throws Exception {

		PortletInstanceSettings portletInstanceSettings =
			(PortletInstanceSettings)_settings;

		PortletPreferences groupPortletPreferences =
			new PortletPreferencesImpl();

		groupPortletPreferences.setValue(name, value);

		portletInstanceSettings.setGroupPortletPreferences(
			groupPortletPreferences);
	}

	private static final String _GROUP_PORTLET_PREFERENCES_NAME =
		"groupPortletPreferencesName";

	private static final String _GROUP_PORTLET_PREFERENCES_VALUE =
		"groupPortletPreferencesValue";

}