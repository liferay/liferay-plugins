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

package com.liferay.calendar.hook.upgrade.v1_0_2;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.calendar.model.CalEvent;

import javax.portlet.PortletPreferences;

/**
 * @author Bryan Engler
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		StringBundler sb = new StringBundler(5);

		sb.append("(preferences like '%classNameIds%");
		sb.append(PortalUtil.getClassNameId(CalEvent.class));
		sb.append("%') or (preferences like '%anyAssetType%");
		sb.append(PortalUtil.getClassNameId(CalEvent.class));
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

		replaceClassNameId(portletPreferences, "anyAssetType");
		replaceClassNameId(portletPreferences, "classNameIds");

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private void replaceClassNameId(
			PortletPreferences portletPreferences, String name)
		throws Exception {

		String[] values = GetterUtil.getStringValues(
			portletPreferences.getValues(name, null));

		ArrayUtil.replace(
			values, String.valueOf(PortalUtil.getClassNameId(CalEvent.class)),
			String.valueOf(PortalUtil.getClassNameId(CalendarBooking.class)));

		portletPreferences.setValues(name, values);
	}

}