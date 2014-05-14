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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Layout;

import java.util.List;

/**
 * @author Raymond Augé
 * @author Jorge Ferrer
 */
public class SettingsFactoryUtil {

	public static void clearCache() {
		getSettingsFactory().clearCache();
	}

	public static Settings getCompanyServiceSettings(
			long companyId, String serviceName)
		throws PortalException, SystemException {

		return getSettingsFactory().getCompanyServiceSettings(
			companyId, serviceName);
	}

	public static Settings getGroupServiceCompanyDefaultSettings(
			long companyId, String serviceName)
		throws PortalException, SystemException {

		return getSettingsFactory().getGroupServiceCompanyDefaultSettings(
			companyId, serviceName);
	}

	public static Settings getGroupServiceSettings(
			long groupId, String serviceName)
		throws PortalException, SystemException {

		return getSettingsFactory().getGroupServiceSettings(
			groupId, serviceName);
	}

	public static ArchivedSettings getPortletInstanceArchivedSettings(
			long groupId, String portletId, String name)
		throws PortalException, SystemException {

		return getSettingsFactory().getPortletInstanceArchivedSettings(
			groupId, portletId, name);
	}

	public static List<ArchivedSettings> getPortletInstanceArchivedSettingsList(
			long groupId, String portletId)
		throws PortalException, SystemException {

		return getSettingsFactory().getPortletInstanceArchivedSettingsList(
			groupId, portletId);
	}

	public static Settings getPortletInstanceCompanyDefaultSettings(
			long companyId, String portletId)
		throws PortalException, SystemException {

		return getSettingsFactory().getPortletInstanceCompanyDefaultSettings(
			companyId, portletId);
	}

	public static Settings getPortletInstanceGroupDefaultSettings(
			long groupId, String portletId)
		throws PortalException, SystemException {

		return getSettingsFactory().getPortletInstanceGroupDefaultSettings(
			groupId, portletId);
	}

	public static Settings getPortletInstanceSettings(
			Layout layout, String portletId)
		throws PortalException, SystemException {

		return getSettingsFactory().getPortletInstanceSettings(
			layout, portletId);
	}

	public static SettingsFactory getSettingsFactory() {
		PortalRuntimePermission.checkGetBeanProperty(SettingsFactoryUtil.class);

		return _settingsFactory;
	}

	public void setSettingsFactory(SettingsFactory settingsFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_settingsFactory = settingsFactory;
	}

	private static SettingsFactory _settingsFactory;

}