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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBCommentTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.sql.Types;

/**
 * @author Peter Shin
 */
public class UpgradeKBComment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("KB_Comment")) {
			renameTable();
		}
	}

	protected void renameTable() throws Exception {
		runSQL(KBCommentTable.TABLE_SQL_DROP);

		runSQL("alter table KB_Comment add kbCommentId LONG");
		runSQL("update KB_Comment set kbCommentId = commentId");

		Object[][] columns = {{"commentId", new Integer(Types.BIGINT)}};

		columns = ArrayUtil.append(columns, KBCommentTable.TABLE_COLUMNS);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			"KB_Comment", columns);

		String createSQL = KBCommentTable.TABLE_SQL_CREATE;

		createSQL =
			createSQL.substring(0, createSQL.length() - 1) +
				",commentId VARCHAR(75) null)";

		upgradeTable.setCreateSQL(createSQL);

		upgradeTable.updateTable();

		runSQL("alter table KBComment drop column commentId");
	}

}