/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeArticle extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		if (hasTable("KB_Article") && tableHasData("KB_Article")) {
			updateArticles();
		}
	}

	protected boolean hasTable(String tableName) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			DatabaseMetaData metadata = con.getMetaData();

			rs = metadata.getTables(null, null, tableName, null);

			while (rs.next()) {
				return true;
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return false;
	}

	protected void updateArticles() throws Exception {
		for (String template : _TEMPLATES) {
			if (_log.isDebugEnabled()) {
				_log.debug(template);
			}

			runSQL(template);
		}
	}

	private static final String[] _TEMPLATES = {
		"update KB_Article set status = '0'",
		"update KB_Article set statusByUserId = userId",
		"update KB_Article set statusByUserName = userName",
		"update KB_Article set statusDate = modifiedDate"
	};

	private static Log _log = LogFactoryUtil.getLog(UpgradeArticle.class);

}