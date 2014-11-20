/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.hook.upgrade.v1_3_3;

import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adolfo Pérez
 */
public class UpgradeKBFolder extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			Map<Long, String> urlTitles = _getInitialUrlTitles(con);

			for (Map.Entry<Long, String> entry : urlTitles.entrySet()) {
				String uniqueUrlTitle = _findUniqueUrlTitle(
					con, entry.getValue());

				for (int i = 1; uniqueUrlTitle == null; i++) {
					uniqueUrlTitle = _findUniqueUrlTitle(
						con, entry.getValue() + StringPool.DASH + i);
				}

				_updateKBFolder(con, entry.getKey(), uniqueUrlTitle);
			}
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	private String _findUniqueUrlTitle(Connection con, String urlTitle)
		throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(
				"select count(*) from KBFolder where KBFolder.urlTitle like ?");

			ps.setString(1, urlTitle + "%");

			rs = ps.executeQuery();

			if (!rs.next()) {
				return urlTitle;
			}

			int kbFolderCount = rs.getInt(1);

			if (kbFolderCount == 0) {
				return urlTitle;
			}

			return null;
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(rs);
		}
	}

	private Map<Long, String> _getInitialUrlTitles(Connection con)
		throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(
				"select kbFolderId, name from KBFolder where " +
					"(KBFolder.urlTitle is null) or (KBFolder.urlTitle = '')");

			rs = ps.executeQuery();

			Map<Long, String> urlTitles = new HashMap<Long, String>();

			while (rs.next()) {
				long kbFolderId = rs.getLong(1);
				String name = rs.getString(2);

				String urlTitle = KnowledgeBaseUtil.getUrlTitle(
					kbFolderId, name);

				urlTitles.put(kbFolderId, urlTitle);
			}

			return urlTitles;
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(rs);
		}
	}

	private void _updateKBFolder(
			Connection con, long kbFolderId, String urlTitle)
		throws SQLException {

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
				"update KBFolder set KBFolder.urlTitle = ? where " +
					"KBFolder.kbFolderId = ?");

			ps.setString(1, urlTitle);
			ps.setLong(2, kbFolderId);

			ps.execute();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

}