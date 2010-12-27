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

import com.liferay.knowledgebase.model.Article;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.util.PortalUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeRatings extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		if (!tableHasData("KB_Article")) {
			return;
		}

		updateRatingsEntries();
		updateRatingsStats();
	}

	protected void updateRatingsEntries() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(2);

			sb.append("select entryId, score from RatingsEntry where ");
			sb.append("classNameId = ?");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, PortalUtil.getClassNameId(Article.class));

			rs = ps.executeQuery();

			while (rs.next()) {
				long entryId = rs.getLong("entryId");
				double score = rs.getDouble("score");

				sb = new StringBundler(4);

				sb.append("update RatingsEntry set score = ");
				sb.append(score * 2);
				sb.append(" where entryId = ");
				sb.append(entryId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateRatingsStats() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(3);

			sb.append("select statsId, totalScore, averageScore ");
			sb.append("from RatingsStats where ");
			sb.append("classNameId = ?");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, PortalUtil.getClassNameId(Article.class));

			rs = ps.executeQuery();

			while (rs.next()) {
				long statsId = rs.getLong("statsId");
				double totalScore = rs.getDouble("totalScore");
				double averageScore = rs.getDouble("averageScore");

				sb = new StringBundler(4);

				sb.append("update RatingsStats set totalScore = ");
				sb.append(totalScore * 2);
				sb.append(", averageScore = ");
				sb.append(averageScore * 2);
				sb.append(" where statsId = ");
				sb.append(statsId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}