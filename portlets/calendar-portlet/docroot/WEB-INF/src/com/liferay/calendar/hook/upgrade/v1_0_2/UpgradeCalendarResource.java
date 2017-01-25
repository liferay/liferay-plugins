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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Adam Brandizzi
 */
public class UpgradeCalendarResource extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeCalendarResourceUserIds();
	}

	protected long getCompanyAdminUserId(Company company)
		throws PortalException, SystemException {

		Role role = RoleLocalServiceUtil.getRole(
			company.getCompanyId(), RoleConstants.ADMINISTRATOR);

		long[] userIds = UserLocalServiceUtil.getRoleUserIds(role.getRoleId());

		return userIds[0];
	}

	protected void updateCalendarUserId(long calendarId, long userId)
		throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update Calendar set userId = ? where calendarId = ?");

			ps.setLong(1, userId);
			ps.setLong(2, calendarId);

			ps.execute();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	protected void updateCalendarUserIds(
			long groupClassNameId, long defaultUserId, long adminUserId)
		throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("select Calendar.calendarId from Calendar join ");
			sb.append("CalendarResource on Calendar.calendarResourceId = ");
			sb.append("CalendarResource.calendarResourceId where ");
			sb.append("CalendarResource.classNameId = ? and ");
			sb.append("CalendarResource.userId = ?");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, groupClassNameId);
			ps.setLong(2, defaultUserId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long calendarId = rs.getLong(1);

				updateCalendarUserId(calendarId, adminUserId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void upgradeCalendarResourceUserId(
			long groupClassNameId, long defaultUserId, long adminUserId)
		throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update CalendarResource set userId = ? where userId = ? and " +
					"classNameId = ?");

			ps.setLong(1, adminUserId);
			ps.setLong(2, defaultUserId);
			ps.setLong(3, groupClassNameId);

			ps.execute();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	protected void upgradeCalendarResourceUserIds()
		throws PortalException, SQLException, SystemException {

		for (Company company : CompanyLocalServiceUtil.getCompanies()) {
			long adminUserId = getCompanyAdminUserId(company);
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
				Group.class);
			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				company.getCompanyId());

			upgradeCalendarResourceUserId(
				classNameId, defaultUserId, adminUserId);
			updateCalendarUserIds(classNameId, defaultUserId, adminUserId);
		}
	}

}