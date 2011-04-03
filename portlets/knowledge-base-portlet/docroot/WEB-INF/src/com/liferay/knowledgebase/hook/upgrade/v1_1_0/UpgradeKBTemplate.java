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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeKBTemplate extends UpgradeProcess {

	protected void addKBTemplate(
			String uuid, long kbTemplateId, long groupId, long companyId,
			long userId, String userName, Date createDate, Date modifiedDate,
			String title, String content, String description)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("insert into KBTemplate (uuid_, kbTemplateId, groupId, ");
			sb.append("companyId, userId, userName, createDate, ");
			sb.append("modifiedDate, title, content, description) values (?, ");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setString(1, uuid);
			ps.setLong(2, kbTemplateId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setDate(7, createDate);
			ps.setDate(8, modifiedDate);
			ps.setString(9, title);
			ps.setString(10, content);
			ps.setString(11, description);

			if (_log.isDebugEnabled()) {
				_log.debug("Adding kb template " + kbTemplateId);
			}

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void deleteTable(String tableName) throws Exception {
		String template = "drop table " + tableName;

		if (_log.isDebugEnabled()) {
			_log.debug(template);
		}

		runSQL(template);
	}

	protected void doUpgrade() throws Exception {
		if (hasTable("KB_Template")) {
			updateTemplates();

			deleteTable("KB_Template");
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

	protected void updateTemplates() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from KB_Template");

			rs = ps.executeQuery();

			while (rs.next()) {
				String uuid = rs.getString("uuid_");
				long templateId = rs.getLong("templateId");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Date createDate = rs.getDate("createDate");
				Date modifiedDate = rs.getDate("modifiedDate");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String description = rs.getString("description");

				addKBTemplate(
					uuid, templateId, groupId, companyId, userId, userName,
					createDate, modifiedDate, title, content, description);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeKBTemplate.class);

}