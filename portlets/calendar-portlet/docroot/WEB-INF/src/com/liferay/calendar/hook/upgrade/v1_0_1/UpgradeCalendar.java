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

package com.liferay.calendar.hook.upgrade.v1_0_1;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Adam Brandizzi
 */
public class UpgradeCalendar extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(6);

			sb.append("select Calendar.calendarId, CalendarResource.");
			sb.append("classNameId, User_.timeZoneId from Calendar ");
			sb.append("inner join CalendarResource on Calendar.");
			sb.append("calendarResourceId = CalendarResource.");
			sb.append("calendarResourceId inner join User_ on ");
			sb.append("CalendarResource.userId = User_.userId");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			long userClassNameId = PortalUtil.getClassNameId(User.class);

			while (rs.next()) {
				long calendarId = rs.getLong(1);
				long classNameId = rs.getLong(2);

				String timeZoneId = _PORTAL_TIME_ZONE_ID;

				if (classNameId == userClassNameId) {
					timeZoneId = rs.getString(3);
				}

				updateCalendarTimeZoneId(calendarId, timeZoneId);
			}
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	protected void updateCalendarTimeZoneId(long calendarId, String timeZoneId)
		throws Exception {

		runSQL(
			"update Calendar set timeZoneId = '" + timeZoneId +
				"' where calendarId = " + calendarId);
	}

	private static final String _PORTAL_TIME_ZONE_ID = PropsUtil.get(
		PropsKeys.COMPANY_DEFAULT_TIME_ZONE);

}