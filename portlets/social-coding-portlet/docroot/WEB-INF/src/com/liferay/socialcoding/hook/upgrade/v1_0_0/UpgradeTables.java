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

package com.liferay.socialcoding.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class UpgradeTables extends UpgradeProcess {

	private void changeTableName (
			String oldName, Object[][] columns, String sqlCreationScript)
		throws Exception {

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			oldName, columns);

		upgradeTable.setCreateSQL(sqlCreationScript);

		upgradeTable.updateTable();
	}

	private void deleteExistingTables() throws Exception {
		for (String tableName : _tableNames)	 {
			try {
				runSQL(TABLE_SQL_DROP + tableName);
			}
			catch (Exception e) {
			}
		}
	}

	protected void doUpgrade() throws Exception {
		if (hasUpdatedData()) {
			_log.info(
				"Ignoring data from older versions as there is updated data");
			return;
		}

		deleteExistingTables();

		changeTableName(
			SVN_REPOSITORY_TABLE_NAME, SVN_REPOSITORY_TABLE_COLUMNS,
			SVN_REPOSITORY_TABLE_SQL_CREATE);
		changeTableName(
			SVN_REVISION_TABLE_NAME, SVN_REVISION_TABLE_COLUMNS,
			SVN_REVISION_TABLE_SQL_CREATE);
	}

	protected boolean hasUpdatedData() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		for (String tableName : _tableNames)	 {
			try {
				con = DataAccess.getConnection();

				ps = con.prepareStatement(
					"select * from " + tableName);

				rs = ps.executeQuery();

				if (rs.next()) {
					_log.info("Existing data in table " + tableName +
						"SN info from WOL won't be updated.");

					return true;
				}
			}
			catch (Exception e){
			}
			finally {
				DataAccess.cleanUp(con, ps, rs);
			}

		}

		return false;
	}

	private final Object[][] SVN_REPOSITORY_TABLE_COLUMNS = {
		{ "svnRepositoryId", new Integer(Types.BIGINT) },
		{ "url", new Integer(Types.VARCHAR) },
		{ "revisionNumber", new Integer(Types.BIGINT) }
	};

	private final String SVN_REPOSITORY_TABLE_NAME = "WOL_SVNRepository";

	private final String SVN_REPOSITORY_NEW_TABLE_NAME = "SN_SVNRepository";

	private final String SVN_REPOSITORY_TABLE_SQL_CREATE = "create table SC_SVNRepository (svnRepositoryId LONG not null primary key,url VARCHAR(200) null,revisionNumber LONG)";
	
	private final Object[][] SVN_REVISION_TABLE_COLUMNS = {
		{ "svnRevisionId", new Integer(Types.BIGINT) },
		{ "svnUserId", new Integer(Types.VARCHAR) },
		{ "createDate", new Integer(Types.TIMESTAMP) },
		{ "svnRepositoryId", new Integer(Types.BIGINT) },
		{ "revisionNumber", new Integer(Types.BIGINT) },
		{ "comments", new Integer(Types.VARCHAR) }
	};

	private final String SVN_REVISION_TABLE_NAME = "WOL_SVNRevision";

	private final String SVN_REVISION_NEW_TABLE_NAME = "SN_SVNRevision";

	private final String SVN_REVISION_TABLE_SQL_CREATE = "create table SC_SVNRevision (svnRevisionId LONG not null primary key,svnUserId VARCHAR(75) null,createDate DATE null,svnRepositoryId LONG,revisionNumber LONG,comments STRING null)";

	private final String TABLE_SQL_DROP = "drop table ";

	private final String[] _tableNames = {
			SVN_REPOSITORY_NEW_TABLE_NAME,
			SVN_REVISION_NEW_TABLE_NAME};

	private static Log _log = LogFactoryUtil.getLog(UpgradeTables.class);

}