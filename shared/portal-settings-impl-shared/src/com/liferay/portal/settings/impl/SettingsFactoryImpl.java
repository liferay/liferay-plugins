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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.settings.Settings;
import com.liferay.portal.settings.SettingsFactory;
import com.liferay.portal.util.PortletKeys;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletPreferences;

/**
 * @author Raymond Augé
 * @author Jorge Ferrer
 */
@DoPrivileged
public class SettingsFactoryImpl implements SettingsFactory {

	@Override
	public void clearCache() {
		_propertiesMap.clear();
	}

	@Override
	public Settings getPortletInstanceSettings(Layout layout, String portletId)
		throws SystemException {

		long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

		if (PortletConstants.hasUserId(portletId)) {
			ownerId = PortletConstants.getUserId(portletId);
			ownerType = PortletKeys.PREFS_OWNER_TYPE_USER;
		}

		PortletPreferences portletInstancePortletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				layout.getCompanyId(), ownerId, ownerType, layout.getPlid(),
				portletId);

		PortletInstanceSettings portletInstanceSettings =
			new PortletInstanceSettings(portletInstancePortletPreferences);

		PortletPreferences companyPortletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				layout.getCompanyId(), layout.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT_DEFAULTS_COMPANY, 0,
				portletId);

		portletInstanceSettings.setCompanyPortletPreferences(
			companyPortletPreferences);

		PortletPreferences groupPortletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				layout.getCompanyId(), layout.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT_DEFAULTS_GROUP, 0,
				portletId);

		portletInstanceSettings.setGroupPortletPreferences(
			groupPortletPreferences);

		portletInstanceSettings.setPortalPreferences(
			getPortalPreferences(layout.getCompanyId()));

		portletInstanceSettings.setPortalProperties(
			getPortalProperties(portletId));

		return portletInstanceSettings;
	}

	@Override
	public Settings getServiceCompanySettings(
			long companyId, String serviceName)
		throws SystemException {

		PortletPreferences companyPortletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				companyId, companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0,
				serviceName);

		ServiceCompanySettings serviceCompanySettings =
			new ServiceCompanySettings(companyPortletPreferences);

		serviceCompanySettings.setPortalPreferences(
			getPortalPreferences(companyId));

		serviceCompanySettings.setPortalProperties(
			getPortalProperties(serviceName));

		return serviceCompanySettings;
	}

	@Override
	public Settings getServiceGroupSettings(long groupId, String serviceName)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		PortletPreferences groupPortletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				group.getCompanyId(), groupId,
				PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, serviceName);

		ServiceGroupSettings serviceGroupSettings = new ServiceGroupSettings(
			groupPortletPreferences);

		PortletPreferences companyPortletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				group.getCompanyId(), group.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP_DEFAULTS_COMPANY, 0,
				serviceName);

		serviceGroupSettings.setCompanyPortletPreferences(
			companyPortletPreferences);

		serviceGroupSettings.setPortalPreferences(
			getPortalPreferences(group.getCompanyId()));

		serviceGroupSettings.setPortalProperties(
			getPortalProperties(serviceName));

		return serviceGroupSettings;
	}

	protected PortletPreferences getPortalPreferences(long companyId)
		throws SystemException {

		return PortalPreferencesLocalServiceUtil.getPreferences(
			companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY);
	}

	protected Properties getPortalProperties(String key) {
		Properties portalProperties = _propertiesMap.get(key);

		if (portalProperties != null) {
			return portalProperties;
		}

		portalProperties = PropsUtil.getProperties();

		_propertiesMap.put(key, portalProperties);

		return portalProperties;
	}

	private Map<String, Properties> _propertiesMap =
		new ConcurrentHashMap<String, Properties>();

}