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

package com.liferay.knowledgebase.hook.upgrade.v1_2_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeArticle extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		if (tableHasData("KB_Article")) {
			updateArticles();
		}
	}

	protected void updateArticles() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("select articleId, groupId, parentResourcePrimKey from ");
			sb.append("KB_Article where latest = ");
			sb.append(_LATEST_VERSION);
			sb.append(" order by groupId asc, parentResourcePrimKey asc, ");
			sb.append("priority asc");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			long groupId = 0;
			long parentResourcePrimKey = 0;
			long priority = 0;

			while (rs.next()) {
				long curArticleId = rs.getLong("articleId");
				long curGroupId = rs.getLong("groupId");
				long curParentResourcePrimKey = rs.getLong(
					"parentResourcePrimKey");

				priority = priority + _MINIMUM_INCREMENT_SIZE;

				if ((curGroupId != groupId) ||
					(curParentResourcePrimKey != parentResourcePrimKey)) {

					groupId = curGroupId;
					parentResourcePrimKey = curParentResourcePrimKey;
					priority = _MINIMUM_INCREMENT_SIZE;
				}

				sb = new StringBundler(4);

				sb.append("update KB_Article set priority = ");
				sb.append(priority);
				sb.append(" where articleId = ");
				sb.append(curArticleId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final int _LATEST_VERSION = 1;

	private static final long _MINIMUM_INCREMENT_SIZE = 1000;

}