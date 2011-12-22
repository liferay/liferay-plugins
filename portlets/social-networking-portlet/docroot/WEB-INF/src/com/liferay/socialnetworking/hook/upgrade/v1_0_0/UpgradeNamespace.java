/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.socialnetworking.hook.upgrade.v1_0_0.util.MeetupsEntryTable;
import com.liferay.socialnetworking.hook.upgrade.v1_0_0.util.MeetupsRegistrationTable;
import com.liferay.socialnetworking.hook.upgrade.v1_0_0.util.WallEntryTable;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class UpgradeNamespace extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameTable(
			StringUtil.replaceFirst(
				MeetupsEntryTable.TABLE_NAME, "SN_", "WOL_"),
			MeetupsEntryTable.TABLE_NAME, MeetupsEntryTable.TABLE_COLUMNS,
			MeetupsEntryTable.TABLE_SQL_CREATE,
			MeetupsEntryTable.TABLE_SQL_DROP);

		renameTable(
			StringUtil.replaceFirst(
				MeetupsRegistrationTable.TABLE_NAME, "SN_", "WOL_"),
			MeetupsRegistrationTable.TABLE_NAME,
			MeetupsRegistrationTable.TABLE_COLUMNS,
			MeetupsRegistrationTable.TABLE_SQL_CREATE,
			MeetupsRegistrationTable.TABLE_SQL_DROP);

		renameTable(
			StringUtil.replaceFirst(WallEntryTable.TABLE_NAME, "SN_", "WOL_"),
			WallEntryTable.TABLE_NAME, WallEntryTable.TABLE_COLUMNS,
			WallEntryTable.TABLE_SQL_CREATE, WallEntryTable.TABLE_SQL_DROP);
	}

	protected void renameTable(
			String oldTableName, String newTableName, Object[][] tableColumns,
			String tableSqlCreate, String tableSqlDrop)
		throws Exception {

		if (tableHasData(newTableName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not renaming " + oldTableName + " to " + newTableName +
						" because " + newTableName + " has data");
			}

			return;
		}

		if (!tableHasData(oldTableName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not renaming " + oldTableName + " to " + newTableName +
						" because " + oldTableName + " has no data");
			}

			return;
		}

		runSQL(tableSqlDrop);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			oldTableName, tableColumns);

		upgradeTable.setCreateSQL(tableSqlCreate);

		upgradeTable.updateTable();
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeNamespace.class);

}