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

import com.liferay.calendar.hook.upgrade.v1_0_0.util.CalendarBookingTable;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.calendar.model.CalEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.portlet.PortletPreferences;

/**
 * @author Jenny Chen
 */
public class UpgradeCalendarBooking extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type CalendarBooking description TEXT null");
		}
		catch (SQLException sqle) {
			upgradeTable(
					CalendarBookingTable.TABLE_NAME,
					CalendarBookingTable.TABLE_COLUMNS,
					CalendarBookingTable.TABLE_SQL_CREATE,
					CalendarBookingTable.TABLE_SQL_ADD_INDEXES);
		}

		updatePortletPreferences();
	}

	protected void updatePortletPreferences() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select portletPreferencesId from PortletPreferences where " +
					"preferences like '%classNameIds%'");

			rs = ps.executeQuery();

			while (rs.next()) {
				long portletPreferencesId = rs.getLong("portletPreferencesId");

				String calendarBookingId = String.valueOf(
					PortalUtil.getClassNameId(CalendarBooking.class));

				String calEventId = String.valueOf(
					PortalUtil.getClassNameId(CalEvent.class));

				com.liferay.portal.model.PortletPreferences
					portletPreferencesModel =
						PortletPreferencesLocalServiceUtil.
							getPortletPreferences(portletPreferencesId);

				Layout layout = LayoutLocalServiceUtil.getLayout(
					portletPreferencesModel.getPlid());

				PortletPreferences portletPreferences =
					PortletPreferencesFactoryUtil.fromXML(
						layout.getCompanyId(),
						portletPreferencesModel.getOwnerId(),
						portletPreferencesModel.getOwnerType(),
						portletPreferencesModel.getPlid(),
						portletPreferencesModel.getPortletId(),
						portletPreferencesModel.getPreferences());

				String[] classNameIds = GetterUtil.getStringValues(
					portletPreferences.getValues("classNameIds", null));

				for (String classNameId : classNameIds) {
					if (classNameId.equals(calEventId)) {
						ArrayUtil.replace(
							classNameIds, String.valueOf(classNameId),
							calendarBookingId);

						portletPreferences.setValues(
							"classNameIds", classNameIds);

						break;
					}
				}

				String preferences = PortletPreferencesFactoryUtil.toXML(
					portletPreferences);

				portletPreferencesModel.setPreferences(preferences);

				PortletPreferencesLocalServiceUtil.updatePortletPreferences(
					portletPreferencesModel);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}