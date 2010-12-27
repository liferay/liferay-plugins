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

package com.liferay.knowledgebase.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author Peter Shin
 */
public class UpgradeArticle extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		if (hasColumn("status")) {
			return;
		}

		upgradeArticles();
	}

	protected boolean hasColumn(String columnName) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from KB_Article");

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				String curColumnName = rsmd.getColumnName(i + 1);

				if (curColumnName.equals(columnName)) {
					return true;
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return false;
	}

	protected void upgradeArticles() throws Exception {
		runSQL("alter table KB_Article add status INTEGER");
		runSQL("alter table KB_Article add statusByUserId LONG");
		runSQL("alter table KB_Article add statusByUserName VARCHAR(75)");
		runSQL("alter table KB_Article add statusDate DATE");

		runSQL("update KB_Article set status = 0");
		runSQL("update KB_Article set statusByUserId = userId");
		runSQL("update KB_Article set statusByUserName = userName");
		runSQL("update KB_Article set statusDate = modifiedDate");
	}

}