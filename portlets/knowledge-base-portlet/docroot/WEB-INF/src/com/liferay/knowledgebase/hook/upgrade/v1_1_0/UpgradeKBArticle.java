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

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleAttachmentsUtil;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleLatestUpgradeColumnImpl;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleMainUpgradeColumnImpl;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleRootResourcePrimKeyUpgradeColumnImpl;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleTable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.util.TempUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Peter Shin
 */
public class UpgradeKBArticle extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameAndUpdateTable(
			StringUtil.replaceFirst(KBArticleTable.TABLE_NAME, "KB", "KB_"),
			KBArticleTable.TABLE_NAME, KBArticleTable.TABLE_COLUMNS,
			KBArticleTable.TABLE_SQL_CREATE, KBArticleTable.TABLE_SQL_DROP);
	}

	protected void renameAndUpdateTable(
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

		updateSchema(oldTableName, newTableName, tableSqlDrop);

		renameTable(oldTableName, tableColumns, tableSqlCreate);

		updateTable(newTableName, tableColumns, tableSqlCreate);

		KBArticleAttachmentsUtil.deleteAttachmentsDirectory(
			PortalUtil.getDefaultCompanyId());
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

		if (tableHasColumn(tableName, columnName)) {
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

		updateColumn(oldTableName, "kbArticleId", "LONG", "articleId");
		updateColumn(oldTableName, "rootResourcePrimKey", "LONG", "0");
		updateColumn(oldTableName, "kbTemplateId", "LONG", "0");
		updateColumn(oldTableName, "sections", "STRING", "'_general_'");
		updateColumn(oldTableName, "viewCount", "INTEGER", "0");
		updateColumn(oldTableName, "latest", "BOOLEAN", "FALSE");
		updateColumn(oldTableName, "main", "BOOLEAN", "FALSE");
		updateColumn(oldTableName, "status", "INTEGER", "0");
		updateColumn(oldTableName, "statusByUserId", "LONG", "userId");
		updateColumn(oldTableName, "statusByUserName", "STRING", "userName");
		updateColumn(oldTableName, "statusDate", "DATE", "modifiedDate");

		if (tableHasColumn(oldTableName, "articleId")) {
			runSQL("alter table " + oldTableName + " drop column articleId");
		}
	}

	protected void updateTable(
			String newTableName, Object[][] tableColumns, String tableSqlCreate)
		throws Exception {

		UpgradeColumn kbArticleIdColumn = new TempUpgradeColumnImpl(
			"kbArticleId");

		UpgradeColumn resourcePrimKeyColumn = new TempUpgradeColumnImpl(
			"resourcePrimKey");

		KBArticleRootResourcePrimKeyUpgradeColumnImpl
			rootResourcePrimKeyColumn =
				new KBArticleRootResourcePrimKeyUpgradeColumnImpl(
					resourcePrimKeyColumn);

		KBArticleLatestUpgradeColumnImpl latestColumn =
			new KBArticleLatestUpgradeColumnImpl(
				kbArticleIdColumn, resourcePrimKeyColumn);

		KBArticleMainUpgradeColumnImpl mainColumn =
			new KBArticleMainUpgradeColumnImpl(
				kbArticleIdColumn, resourcePrimKeyColumn);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			newTableName, tableColumns, kbArticleIdColumn,
			resourcePrimKeyColumn, rootResourcePrimKeyColumn, latestColumn,
			mainColumn);

		upgradeTable.setCreateSQL(tableSqlCreate);

		upgradeTable.updateTable();
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeKBArticle.class);

}