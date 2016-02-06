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

package com.liferay.akismet.util;

import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 */
public class PrefsPortletPropsUtil {

	public static boolean getBoolean(long companyId, String name) {
		return GetterUtil.getBoolean(getString(companyId, name));
	}

	public static int getInteger(long companyId, String name) {
		return GetterUtil.getInteger(getString(companyId, name));
	}

	public static PortletPreferences getPortletPreferences(long companyId) {
		return PortletPreferencesLocalServiceUtil.getPreferences(
			companyId, companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			PortletKeys.PREFS_PLID_SHARED, PortletKeys.AKISMET);
	}

	public static String getString(long companyId, String name) {
		PortletPreferences portletPreferences = getPortletPreferences(
			companyId);

		return _getString(portletPreferences, name);
	}

	private static String _getString(
		PortletPreferences portletPreferences, String name) {

		String defaultValue = PortletProps.get(name);

		return portletPreferences.getValue(name, defaultValue);
	}

}