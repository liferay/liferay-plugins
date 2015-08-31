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

package com.liferay.sync.hook.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sync.model.SyncConstants;

/**
 * @author Shinn Lok
 */
public class UpgradeSyncDLObject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateColumn(
			"DLFileEntry", "userId", "userId", null, "fileEntryId", 0,
			new String[] {
				SyncConstants.TYPE_FILE, SyncConstants.TYPE_PRIVATE_WORKING_COPY
			});
		updateColumn(
			"DLFolder", "userId", "userId", null, "folderId", 0,
			new String[] {SyncConstants.TYPE_FOLDER});

		updateColumn(
			"DLFileEntry", "userName", "userName", null, "fileEntryId", "''",
			new String[] {
				SyncConstants.TYPE_FILE, SyncConstants.TYPE_PRIVATE_WORKING_COPY
			});
		updateColumn(
			"DLFolder", "userName", "userName", null, "folderId", "''",
			new String[] {SyncConstants.TYPE_FOLDER});

		updateColumn(
			"DLFileEntry", "treePath", "treePath", null, "fileEntryId", "''",
			new String[] {
				SyncConstants.TYPE_FILE, SyncConstants.TYPE_PRIVATE_WORKING_COPY
			});
		updateColumn(
			"DLFolder", "treePath", "treePath", null, "folderId", "''",
			new String[] {SyncConstants.TYPE_FOLDER});

		updateColumn(
			"DLFileVersion", "versionId", "fileVersionId", "version",
			"fileEntryId", 0,
			new String[] {
				SyncConstants.TYPE_FILE, SyncConstants.TYPE_PRIVATE_WORKING_COPY
			});
	}

	protected void updateColumn(
			String tableName, String columnName1, String columnName2,
			String columnName3, String primaryKeyColumnName,
			Object columnDefaultValue, String[] types)
		throws Exception {

		StringBundler sb = new StringBundler(types.length * 4 + 24);

		sb.append("update SyncDLObject set ");
		sb.append(columnName1);
		sb.append(" = (select ");
		sb.append(columnName2);
		sb.append(" from ");
		sb.append(tableName);
		sb.append(" where (");
		sb.append(tableName);
		sb.append(StringPool.PERIOD);
		sb.append(primaryKeyColumnName);
		sb.append(" = SyncDLObject.typePK");

		if (columnName3 != null) {
			sb.append(" and ");
			sb.append(tableName);
			sb.append(StringPool.PERIOD);
			sb.append(columnName3);
			sb.append(" = SyncDLObject.");
			sb.append(columnName3);
		}

		sb.append(")) where (");

		for (int i = 0; i < types.length; i++) {
			sb.append("type_ = '");
			sb.append(types[i]);
			sb.append(StringPool.APOSTROPHE);

			if ((i + 1) < types.length) {
				sb.append(" or ");
			}
		}

		sb.append(") and (");
		sb.append(columnName1);
		sb.append(" is null or ");
		sb.append(columnName1);
		sb.append(" = ");
		sb.append(columnDefaultValue);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		runSQL(sb.toString());
	}

}