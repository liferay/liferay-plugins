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

package com.liferay.calendar.hook.upgrade.v1_0_0;

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

		String calEventClassNameId = String.valueOf(
			PortalUtil.getClassNameId(CalEvent.class));

		sb.append("(preferences like '%classNameIds%");
		sb.append(calEventClassNameId);
		sb.append("%') or (preferences like '%anyAssetType%");
		sb.append(calEventClassNameId);
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

		String calendarBookingClassNameId = String.valueOf(
			PortalUtil.getClassNameId(CalendarBooking.class));

		String calEventClassNameId = String.valueOf(
			PortalUtil.getClassNameId(CalEvent.class));

		replaceClassNameId(
			portletPreferences, "classNameIds", calEventClassNameId,
			calendarBookingClassNameId);
		replaceClassNameId(
			portletPreferences, "anyAssetType", calEventClassNameId,
			calendarBookingClassNameId);

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private void replaceClassNameId(
			PortletPreferences portletPreferences, String preferenceName,
			String calEventClassNameId, String calendarBookingClassNameId)
		throws Exception {

		String[] classNameIds = GetterUtil.getStringValues(
			portletPreferences.getValues(preferenceName, null));

		ArrayUtil.replace(
			classNameIds, calEventClassNameId, calendarBookingClassNameId);

		portletPreferences.setValues(preferenceName, classNameIds);
	}

}