/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.socialnetworking.hook.upgrade.v1_0_0.util.MeetupsEntryTable;
import com.liferay.socialnetworking.hook.upgrade.v1_0_0.util.MeetupsRegistrationTable;
import com.liferay.socialnetworking.hook.upgrade.v1_0_0.util.WallEntryTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class UpgradeNamespace extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		renameTable(
			StringUtil.replaceFirst(
				MeetupsEntryTable.TABLE_NAME, "SC_", "WOL_"),
			MeetupsEntryTable.TABLE_NAME, MeetupsEntryTable.TABLE_COLUMNS,
			MeetupsEntryTable.TABLE_SQL_CREATE);

		renameTable(
			StringUtil.replaceFirst(
				MeetupsRegistrationTable.TABLE_NAME, "SC_", "WOL_"),
			MeetupsRegistrationTable.TABLE_NAME,
			MeetupsRegistrationTable.TABLE_COLUMNS,
			MeetupsRegistrationTable.TABLE_SQL_CREATE);

		renameTable(
			StringUtil.replaceFirst(WallEntryTable.TABLE_NAME, "SC_", "WOL_"),
			WallEntryTable.TABLE_NAME, WallEntryTable.TABLE_COLUMNS,
			WallEntryTable.TABLE_SQL_CREATE);
	}

	protected boolean hasData(String tableName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select count(*) from " + tableName);

			rs = ps.executeQuery();

			while (rs.next()) {
				long count = rs.getLong(1);

				if (count > 0) {
					return true;
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return false;
	}

	protected void renameTable(
			String oldTableName, String newTableName, Object[][] tableColumns,
			String tableSqlCreate)
		throws Exception {

		if (hasData(newTableName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not renaming " + oldTableName + " to " + newTableName +
						" because " + newTableName + " has data");
			}

			return;
		}

		if (!hasData(oldTableName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not renaming " + oldTableName + " to " + newTableName +
						" because " + oldTableName + " has no data");
			}

			return;
		}

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			oldTableName, tableColumns);

		upgradeTable.setCreateSQL(tableSqlCreate);

		upgradeTable.updateTable();
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeNamespace.class);

}