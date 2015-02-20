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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Adam Brandizzi
 */
public class UpgradeCalendar extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			PreparedStatement ps = con.prepareStatement(
				"select C.calendarId, CR.classNameId, U.timeZoneId from " +
					"Calendar C inner join CalendarResource CR on "+
					"C.calendarResourceId = CR.calendarResourceId inner join " +
					"User_ U on CR.userId = U.userId");

			ResultSet rs = ps.executeQuery();

			long userClassNameId = getClassNameId(con, User.class.getName());
			String portalTimeZoneId = PropsUtil.get(
				PropsKeys.COMPANY_DEFAULT_TIME_ZONE);
			PreparedStatement updatePs = con.prepareStatement(
				"update Calendar set timeZoneId = ? where calendarId = ?");

			while (rs.next()) {
				long calendarId = rs.getLong(1);
				long classNameId = rs.getLong(2);
				String timeZoneId = portalTimeZoneId;

				if (classNameId == userClassNameId) {
					timeZoneId = rs.getString(3);
				}

				updatePs.setString(1, timeZoneId);
				updatePs.setLong(2, calendarId);

				int count = updatePs.executeUpdate();

				if (count <= 0) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Calendar " + calendarId +
							" did not get its time zone set");
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	protected long getClassNameId(Connection con, String className)
		throws SQLException {

		PreparedStatement ps = con.prepareStatement(
			"select classNameId from ClassName_ where value = ?");

		ps.setString(1, className);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getLong("classNameId");
		}

		return 0;
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeCalendar.class);

}