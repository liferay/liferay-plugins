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

import com.liferay.portal.settings.impl.ServiceCompanySettings;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jorge Ferrer
 */
public class ServiceCompanySettingsTest extends BaseSettingsTestCase {

	@Before
	public void setUp() throws Exception {
		_settings = new ServiceCompanySettings(getPortletPreferences());
	}

	@Test
	public void testGetValueFromPortalProperties() {
		setPortalProperties(_PORTAL_PROPERTIES_NAME, _PORTAL_PROPERTIES_VALUE);

		String value = _settings.getValue(_PORTAL_PROPERTIES_NAME, null);

		Assert.assertEquals(_PORTAL_PROPERTIES_VALUE, value);
	}

	@Test
	public void testSetValue() {
		setPortalProperties(_PORTAL_PROPERTIES_NAME, _PORTAL_PROPERTIES_VALUE);

		_settings.setValue(_PORTAL_PROPERTIES_NAME, "newValue");

		String value = _settings.getValue(_PORTAL_PROPERTIES_NAME, null);

		Assert.assertEquals("newValue", value);
	}

	protected void setPortalProperties(String name, String value) {
		ServiceCompanySettings serviceCompanySettings =
			(ServiceCompanySettings)_settings;

		Properties portalProperties = new Properties();

		portalProperties.setProperty(name, value);

		serviceCompanySettings.setPortalProperties(portalProperties);
	}

	private static final String _PORTAL_PROPERTIES_NAME =
		"portalPropertiesName";

	private static final String _PORTAL_PROPERTIES_VALUE =
		"portalPropertiesValue";

}