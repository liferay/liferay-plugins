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

package com.liferay.portal.settings.impl;

import com.liferay.portal.settings.Settings;

import java.util.Properties;

import javax.portlet.PortletPreferences;

/**
 * @author Jorge Ferrer
 */
public class ServiceCompanySettings extends BaseSettings {

	public ServiceCompanySettings(
		PortletPreferences companyPortletPreferences) {

		this.companyPortletPreferences = companyPortletPreferences;
	}

	public Settings setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;

		return this;
	}

	protected ServiceCompanySettings() {
	}

	@Override
	protected PortletPreferences getWriteablePortletPreferences() {
		return companyPortletPreferences;
	}

}