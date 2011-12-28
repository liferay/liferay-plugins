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

import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleAttachmentsUtil;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleLatestUpgradeColumnImpl;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleMainUpgradeColumnImpl;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleRootResourcePrimKeyUpgradeColumnImpl;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.util.KBArticleTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.TempUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.util.PortalUtil;

import java.sql.Types;

/**
 * @author Peter Shin
 */
public class UpgradeKBArticle extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("KB_Article")) {
			renameTable();

			updateTable();
		}
	}

	protected void renameTable() throws Exception {
		runSQL(KBArticleTable.TABLE_SQL_DROP);

		runSQL("alter table KB_Article add kbArticleId LONG");
		runSQL("update KB_Article set kbArticleId = articleId");

		if (!tableHasColumn("KB_Article", "rootResourcePrimKey")) {
			runSQL("alter table KB_Article add rootResourcePrimKey LONG");
			runSQL("update KB_Article set rootResourcePrimKey = 0");
		}

		if (!tableHasColumn("KB_Article", "kbTemplateId")) {
			runSQL("alter table KB_Article add kbTemplateId LONG");
			runSQL("update KB_Article set kbTemplateId = 0");
		}

		if (!tableHasColumn("KB_Article", "sections")) {
			runSQL("alter table KB_Article add sections STRING null");
			runSQL("update KB_Article set sections = '_general_'");
		}

		if (!tableHasColumn("KB_Article", "viewCount")) {
			runSQL("alter table KB_Article add viewCount INTEGER");
			runSQL("update KB_Article set viewCount = 0");
		}

		if (!tableHasColumn("KB_Article", "latest")) {
			runSQL("alter table KB_Article add latest BOOLEAN");
			runSQL("update KB_Article set latest = FALSE");
		}

		if (!tableHasColumn("KB_Article", "main")) {
			runSQL("alter table KB_Article add main BOOLEAN");
			runSQL("update KB_Article set main = FALSE");
		}

		if (!tableHasColumn("KB_Article", "status")) {
			runSQL("alter table KB_Article add status INTEGER");
			runSQL("update KB_Article set status = 0");
		}

		if (!tableHasColumn("KB_Article", "statusByUserId")) {
			runSQL("alter table KB_Article add statusByUserId LONG");
			runSQL("update KB_Article set statusByUserId = userId");
		}

		if (!tableHasColumn("KB_Article", "statusByUserName")) {
			runSQL("alter table KB_Article add statusByUserName STRING null");
			runSQL("update KB_Article set statusByUserName = userName");
		}

		if (!tableHasColumn("KB_Article", "statusDate")) {
			runSQL("alter table KB_Article add statusDate DATE null");
			runSQL("update KB_Article set statusDate = modifiedDate");
		}

		Object[][] columns = {{"articleId", new Integer(Types.BIGINT)}};

		columns = ArrayUtil.append(columns, KBArticleTable.TABLE_COLUMNS);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			"KB_Article", columns);

		String createSQL = KBArticleTable.TABLE_SQL_CREATE;

		createSQL =
			createSQL.substring(0, createSQL.length() - 1) +
				",articleId VARCHAR(75) null)";

		upgradeTable.setCreateSQL(createSQL);

		upgradeTable.updateTable();

		runSQL("alter table KBArticle drop column articleId");
	}

	protected void updateTable() throws Exception {
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
			KBArticleTable.TABLE_NAME, KBArticleTable.TABLE_COLUMNS,
			kbArticleIdColumn, resourcePrimKeyColumn, rootResourcePrimKeyColumn,
			latestColumn, mainColumn);

		upgradeTable.setCreateSQL(KBArticleTable.TABLE_SQL_CREATE);

		upgradeTable.updateTable();

		KBArticleAttachmentsUtil.deleteAttachmentsDirectory(
			PortalUtil.getDefaultCompanyId());
	}

}