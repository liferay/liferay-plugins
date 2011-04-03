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
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeRatingsStats extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		if (hasTable("KB_Article")) {
			updateRatingsStats();
		}
	}

	protected long getClassNameId(String className) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = "select classNameId from ClassName_ where value = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, className);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("classNameId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
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

	protected void updateRatingsStats() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("select statsId, totalScore, averageScore from ");
			sb.append("RatingsStats where classNameId = '");
			sb.append(getClassNameId(_ARTICLE_CLASS_NAME));
			sb.append("'");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long statsId = rs.getLong("statsId");
				double totalScore = rs.getDouble("totalScore");
				double averageScore = rs.getDouble("averageScore");

				sb = new StringBundler(7);

				sb.append("update RatingsStats set totalScore = '");
				sb.append(totalScore * 2);
				sb.append("', averageScore = '");
				sb.append(averageScore * 2);
				sb.append("' where statsId = '");
				sb.append(statsId);
				sb.append("'");

				if (_log.isDebugEnabled()) {
					_log.debug(sb.toString());
				}

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String _ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.Article";

	private static Log _log = LogFactoryUtil.getLog(UpgradeRatingsStats.class);

}