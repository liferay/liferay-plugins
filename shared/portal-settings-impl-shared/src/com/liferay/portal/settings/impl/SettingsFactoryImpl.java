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
	public Settings getCompanyServiceSettings(
			long companyId, String serviceName)
		throws SystemException {

		return getCompanySettings(companyId, serviceName);
	}

	@Override
	public Settings getGroupServiceSettings(long groupId, String serviceName)
		throws PortalException, SystemException {

		return getGroupSettings(groupId, serviceName);
	}

	@Override
	public Settings getPortletInstanceSettings(Layout layout, String portletId)
		throws PortalException, SystemException {

		return new PortletPreferencesSettings(
			getPortletInstancePortletPreferences(layout, portletId),
			getGroupSettings(layout.getCompanyId(), portletId));
	}

	protected PortletPreferences getCompanyPortletPreferences(
			long companyId, String key)
		throws SystemException {

		return PortletPreferencesLocalServiceUtil.getPreferences(
			companyId, companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0, key);
	}

	protected Settings getCompanySettings(long companyId, String key)
		throws SystemException {

		return new PortletPreferencesSettings(
			getCompanyPortletPreferences(companyId, key),
			getPortalPreferencesSettings(companyId, key));
	}

	protected PortletPreferences getGroupPortletPreferences(
			long companyId, long groupId, String key)
		throws SystemException {

		return PortletPreferencesLocalServiceUtil.getPreferences(
			companyId, groupId, PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, key);
	}

	protected Settings getGroupSettings(long groupId, String key)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		long companyId = group.getCompanyId();

		return new PortletPreferencesSettings(
			getGroupPortletPreferences(companyId, groupId, key),
			getCompanySettings(companyId, key));
	}

	protected PortletPreferences getPortalPreferences(long companyId)
		throws SystemException {

		return PortalPreferencesLocalServiceUtil.getPreferences(
			companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY);
	}

	protected PortletPreferencesSettings getPortalPreferencesSettings(
			long companyId, String key)
		throws SystemException {

		return new PortletPreferencesSettings(
			getPortalPreferences(companyId), getPortalPropertiesSettings(key));
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

	protected PropertiesSettings getPortalPropertiesSettings(
		String serviceName) {

		return new PropertiesSettings(getPortalProperties(serviceName));
	}

	protected PortletPreferences getPortletInstancePortletPreferences(
			Layout layout, String portletId)
		throws SystemException {

		long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

		if (PortletConstants.hasUserId(portletId)) {
			ownerId = PortletConstants.getUserId(portletId);
			ownerType = PortletKeys.PREFS_OWNER_TYPE_USER;
		}

		return PortletPreferencesLocalServiceUtil.getPreferences(
			layout.getCompanyId(), ownerId, ownerType, layout.getPlid(),
			portletId);
	}

	private Map<String, Properties> _propertiesMap =
		new ConcurrentHashMap<String, Properties>();

}