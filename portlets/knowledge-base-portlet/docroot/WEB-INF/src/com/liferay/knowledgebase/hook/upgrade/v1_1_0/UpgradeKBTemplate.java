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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBTemplateTable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Peter Shin
 */
public class UpgradeKBTemplate extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameAndUpdateTable(
			StringUtil.replaceFirst(KBTemplateTable.TABLE_NAME, "KB", "KB_"),
			KBTemplateTable.TABLE_NAME, KBTemplateTable.TABLE_COLUMNS,
			KBTemplateTable.TABLE_SQL_CREATE, KBTemplateTable.TABLE_SQL_DROP);
	}

	protected void renameAndUpdateTable(
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

		updateSchema(oldTableName, newTableName, tableSqlDrop);

		renameTable(oldTableName, tableColumns, tableSqlCreate);
	}

	protected void renameTable(
			String oldTableName, Object[][] tableColumns, String tableSqlCreate)
		throws Exception {

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			oldTableName, tableColumns);

		upgradeTable.setCreateSQL(tableSqlCreate);

		upgradeTable.updateTable();
	}

	protected void updateColumn(
			String tableName, String columnName, String dataType, String data)
		throws Exception {

		if (hasColumn(tableName, columnName)) {
			return;
		}

		String dataTypeUpperCase = StringUtil.toUpperCase(dataType);

		StringBundler sb = new StringBundler(6);

		sb.append("alter table ");
		sb.append(tableName);
		sb.append(" add ");
		sb.append(columnName);
		sb.append(StringPool.SPACE);
		sb.append(dataTypeUpperCase);

		String sql = sb.toString();

		if (dataTypeUpperCase.equals("DATE") || dataType.equals("STRING")) {
			sql = sql.concat(" null");
		}

		runSQL(sql);

		sb.setIndex(0);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");
		sb.append(columnName);
		sb.append(" = ");
		sb.append(data);

		runSQL(sb.toString());
	}

	protected void updateSchema(
			String oldTableName, String newTableName, String tableSqlDrop)
		throws Exception {

		if (hasTable(newTableName)) {
			runSQL(tableSqlDrop);
		}

		updateColumn(oldTableName, "kbTemplateId", "LONG", "templateId");
		updateColumn(oldTableName, "engineType", "INTEGER", "0");
		updateColumn(oldTableName, "cacheable", "BOOLEAN", "TRUE");

		if (hasColumn(oldTableName, "templateId")) {
			runSQL("alter table " + oldTableName + " drop column templateId");
		}

		if (hasColumn(oldTableName, "description")) {
			runSQL("alter table " + oldTableName + " drop column description");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeKBTemplate.class);

}