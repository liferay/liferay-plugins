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
			MEETUP_ENTRY_TABLE_NAME, MEETUP_ENTRY_TABLE_COLUMNS,
			MEETUP_ENTRY_TABLE_SQL_CREATE);
		changeTableName(
			MEETUP_REGISTRATION_TABLE_NAME, MEETUP_REGISTRATION_TABLE_COLUMNS,
			MEETUP_REGISTRATION_TABLE_SQL_CREATE);
		changeTableName(
			WALL_ENTRY_TABLE_NAME, WALL_ENTRY_TABLE_COLUMNS,
			WALL_ENTRY_TABLE_SQL_CREATE);
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

	private final Object[][] MEETUP_ENTRY_TABLE_COLUMNS = {
		{ "meetupsEntryId", new Integer(Types.BIGINT) },
		{ "companyId", new Integer(Types.BIGINT) },
		{ "userId", new Integer(Types.BIGINT) },
		{ "userName", new Integer(Types.VARCHAR) },
		{ "createDate", new Integer(Types.TIMESTAMP) },
		{ "modifiedDate", new Integer(Types.TIMESTAMP) },
		{ "title", new Integer(Types.VARCHAR) },
		{ "description", new Integer(Types.VARCHAR) },
		{ "startDate", new Integer(Types.TIMESTAMP) },
		{ "endDate", new Integer(Types.TIMESTAMP) },
		{ "totalAttendees", new Integer(Types.INTEGER) },
		{ "maxAttendees", new Integer(Types.INTEGER) },
		{ "price", new Integer(Types.DOUBLE) },
		{ "thumbnailId", new Integer(Types.BIGINT) }
	};

	private final String MEETUP_ENTRY_TABLE_NAME = "WOL_MeetupsEntry";

	private final String MEETUP_ENTRY_NEW_TABLE_NAME = "SN_MeetupsEntry";

	private final String MEETUP_ENTRY_TABLE_SQL_CREATE = "create table SN_MeetupsEntry (meetupsEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,description STRING null,startDate DATE null,endDate DATE null,totalAttendees INTEGER,maxAttendees INTEGER,price DOUBLE,thumbnailId LONG)";

	private final Object[][] MEETUP_REGISTRATION_TABLE_COLUMNS = {
		{ "meetupsRegistrationId", new Integer(Types.BIGINT) },
		{ "companyId", new Integer(Types.BIGINT) },
		{ "userId", new Integer(Types.BIGINT) },
		{ "userName", new Integer(Types.VARCHAR) },
		{ "createDate", new Integer(Types.TIMESTAMP) },
		{ "modifiedDate", new Integer(Types.TIMESTAMP) },
		{ "meetupsEntryId", new Integer(Types.BIGINT) },
		{ "status", new Integer(Types.INTEGER) },
		{ "comments", new Integer(Types.VARCHAR) }
	};
	
	private final String MEETUP_REGISTRATION_TABLE_NAME = "WOL_MeetupsRegistration";

	private final String MEETUP_REGISTRATION_NEW_TABLE_NAME = "SN_MeetupsRegistration";

	private final String MEETUP_REGISTRATION_TABLE_SQL_CREATE = "create table SN_MeetupsRegistration (meetupsRegistrationId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,meetupsEntryId LONG,status INTEGER,comments STRING null)";

	private final Object[][] WALL_ENTRY_TABLE_COLUMNS = {
		{ "wallEntryId", new Integer(Types.BIGINT) },
		{ "groupId", new Integer(Types.BIGINT) },
		{ "companyId", new Integer(Types.BIGINT) },
		{ "userId", new Integer(Types.BIGINT) },
		{ "userName", new Integer(Types.VARCHAR) },
		{ "createDate", new Integer(Types.TIMESTAMP) },
		{ "modifiedDate", new Integer(Types.TIMESTAMP) },
		{ "comments", new Integer(Types.VARCHAR) }
	};

	private final String WALL_ENTRY_TABLE_NAME = "WOL_WallEntry";

	private final String WALL_ENTRY_NEW_TABLE_NAME = "SN_WallEntry";

	private final String WALL_ENTRY_TABLE_SQL_CREATE = "create table SN_WallEntry (wallEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,comments STRING null)";

	private final String TABLE_SQL_DROP = "drop table ";

	private final String[] _tableNames = {
			MEETUP_ENTRY_NEW_TABLE_NAME,
			MEETUP_REGISTRATION_NEW_TABLE_NAME,
			WALL_ENTRY_NEW_TABLE_NAME};

	private static Log _log = LogFactoryUtil.getLog(UpgradeTables.class);

}