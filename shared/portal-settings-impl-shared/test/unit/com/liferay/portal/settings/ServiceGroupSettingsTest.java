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

import com.liferay.portal.settings.impl.ServiceGroupSettings;
import com.liferay.portlet.PortletPreferencesImpl;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jorge Ferrer
 */
public class ServiceGroupSettingsTest extends ServiceCompanySettingsTest {

	@Before
	@Override
	public void setUp() throws Exception {
		_settings = new ServiceGroupSettings(getPortletPreferences());
	}

	@Test
	public void testGetValueFromCompanyPortletPreferences() throws Exception {
		setCompanyPortletPreferences(
			_COMPANY_PORTLET_PREFERENCES_NAME,
			_COMPANY_PORTLET_PREFERENCES_VALUE);

		String value = _settings.getValue(
			_COMPANY_PORTLET_PREFERENCES_NAME, null);

		Assert.assertEquals(_COMPANY_PORTLET_PREFERENCES_VALUE, value);
	}

	protected void setCompanyPortletPreferences(String name, String value)
		throws Exception {

		ServiceGroupSettings serviceGroupSettings =
			(ServiceGroupSettings)_settings;

		PortletPreferences companyPortletPreferences =
			new PortletPreferencesImpl();

		companyPortletPreferences.setValue(name, value);

		serviceGroupSettings.setCompanyPortletPreferences(
			companyPortletPreferences);
	}

	private static final String _COMPANY_PORTLET_PREFERENCES_NAME =
		"companyPortletPreferencesName";

	private static final String _COMPANY_PORTLET_PREFERENCES_VALUE =
		"companyPortletPreferencesValue";

}