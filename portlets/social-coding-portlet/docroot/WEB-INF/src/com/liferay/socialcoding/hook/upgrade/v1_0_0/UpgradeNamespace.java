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

package com.liferay.socialcoding.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.socialcoding.hook.upgrade.v1_0_0.util.SVNRepositoryTable;
import com.liferay.socialcoding.hook.upgrade.v1_0_0.util.SVNRevisionTable;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class UpgradeNamespace extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameTable(
			StringUtil.replaceFirst(
				SVNRepositoryTable.TABLE_NAME, "SC_", "WOL_"),
			SVNRepositoryTable.TABLE_NAME, SVNRepositoryTable.TABLE_COLUMNS,
			SVNRepositoryTable.TABLE_SQL_CREATE,
			SVNRepositoryTable.TABLE_SQL_DROP);

		renameTable(
			StringUtil.replaceFirst(SVNRevisionTable.TABLE_NAME, "SC_", "WOL_"),
			SVNRevisionTable.TABLE_NAME, SVNRevisionTable.TABLE_COLUMNS,
			SVNRevisionTable.TABLE_SQL_CREATE, SVNRevisionTable.TABLE_SQL_DROP);
	}

	protected void renameTable(
			String oldTableName, String newTableName, Object[][] tableColumns,
			String tableSqlCreate, String tableSqlDrop)
		throws Exception {

		if (hasRows(newTableName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not renaming " + oldTableName + " to " + newTableName +
						" because " + newTableName + " has data");
			}

			return;
		}

		if (!hasRows(oldTableName)) {
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