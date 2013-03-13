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

package com.liferay.ddlform.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marcellus Tavares
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	public UpgradePortletPreferences() {
		_preferencesMap.put("detailDDMTemplateId", "formDDMTemplateId");
	}

	@Override
	protected String[] getPortletIds() {
		return new String[] {"1_WAR_ddlformportlet_INSTANCE_%"};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences preferences = PortletPreferencesFactoryUtil.fromXML(
			companyId, ownerId, ownerType, plid, portletId, xml);

		Map<String, String[]> preferencesMap = preferences.getMap();

		for (String name : _preferencesMap.keySet()) {
			String[] values = preferencesMap.get(name);

			if (values == null) {
				continue;
			}

			preferences.reset(name);

			preferences.setValues(_preferencesMap.get(name), values);
		}

		return PortletPreferencesFactoryUtil.toXML(preferences);
	}

	private Map<String, String> _preferencesMap = new HashMap<String, String>();

}