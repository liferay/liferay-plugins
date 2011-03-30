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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(2);

			sb.append("select articleId, resourcePrimKey, status from ");
			sb.append("KB_Article order by resourcePrimKey desc, version desc");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			long resourcePrimKey = -1;
			int status = -1;

			while (rs.next()) {
				long curArticleId = rs.getLong("articleId");
				long curResourcePrimKey = rs.getLong("resourcePrimKey");
				int curStatus = rs.getInt("status");

				String latest = "FALSE";
				String main = "FALSE";

				if (curResourcePrimKey != resourcePrimKey) {
					resourcePrimKey = curResourcePrimKey;
					status = -1;

					latest = "TRUE";
				}

				if ((curStatus != status) && (curStatus == _STATUS_APPROVED)) {
					status = curStatus;

					main = "TRUE";
				}

				sb = new StringBundler(6);

				sb.append("update KB_Article set latest = ");
				sb.append(latest);
				sb.append(", main = ");
				sb.append(main);
				sb.append(" where articleId = ");
				sb.append(curArticleId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final int _STATUS_APPROVED = 0;

}