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

package com.liferay.calendar.hook.upgrade.v1_0_3;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Inácio Nery
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		StringBundler sb = new StringBundler(5);

		sb.append("(preferences like '%isoTimeFormat%");
		sb.append(Boolean.TRUE.toString());
		sb.append("%') or (preferences like '%isoTimeFormat%");
		sb.append(Boolean.FALSE.toString());
		sb.append("%')");

		return sb.toString();
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		String isoTimeFormat = portletPreferences.getValue(
			"isoTimeFormat", Boolean.FALSE.toString());

		if (isoTimeFormat.equals(Boolean.TRUE.toString())) {
			portletPreferences.setValue("timeFormat", "24-hour");
		}
		else {
			portletPreferences.setValue("timeFormat", "am-pm");
		}

		portletPreferences.reset("isoTimeFormat");

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}