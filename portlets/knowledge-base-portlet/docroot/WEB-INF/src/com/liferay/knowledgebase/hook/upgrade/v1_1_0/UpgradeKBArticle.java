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

import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.ServiceContext;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeKBArticle extends UpgradeProcess {

	protected void addKBArticle(
			String uuid, long kbArticleId, long resourcePrimKey, long groupId,
			long companyId, long userId, String userName, Date createDate,
			Date modifiedDate, long rootResourcePrimKey,
			long parentResourcePrimKey, int version, String title,
			String content, String description, double priority, int viewCount,
			boolean latest, boolean main, int status, long statusByUserId,
			String statusByUserName, Date statusDate)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(8);

			sb.append("insert into KBArticle (uuid_, kbArticleId, ");
			sb.append("resourcePrimKey, groupId, companyId, userId, ");
			sb.append("userName, createDate, modifiedDate, ");
			sb.append("rootResourcePrimKey, parentResourcePrimKey, version, ");
			sb.append("title, content, description, priority, viewCount, ");
			sb.append("latest, main, status, statusByUserId, ");
			sb.append("statusByUserName, statusDate) values (?, ?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setString(1, uuid);
			ps.setLong(2, kbArticleId);
			ps.setLong(3, resourcePrimKey);
			ps.setLong(4, groupId);
			ps.setLong(5, companyId);
			ps.setLong(6, userId);
			ps.setString(7, userName);
			ps.setDate(8, createDate);
			ps.setDate(9, modifiedDate);
			ps.setLong(10, rootResourcePrimKey);
			ps.setLong(11, parentResourcePrimKey);
			ps.setInt(12, version);
			ps.setString(13, title);
			ps.setString(14, content);
			ps.setString(15, description);
			ps.setDouble(16, priority);
			ps.setInt(17, viewCount);
			ps.setBoolean(18, latest);
			ps.setBoolean(19, main);
			ps.setInt(20, status);
			ps.setLong(21, statusByUserId);
			ps.setString(22, statusByUserName);
			ps.setDate(23, statusDate);

			if (_log.isDebugEnabled()) {
				_log.debug("Adding kb article " + kbArticleId);
			}

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void addKBArticleAttachments(long companyId, long folderId) {
		try {
			String newDirName = _KB_ARTICLE_DIR_NAME_PREFIX + folderId;
			String oldDirName = _ARTICLE_DIR_NAME_PREFIX + folderId;

			DLLocalServiceUtil.addDirectory(
				companyId, CompanyConstants.SYSTEM, newDirName);

			String[] fileNames = DLLocalServiceUtil.getFileNames(
				companyId, CompanyConstants.SYSTEM, oldDirName);

			for (String fileName : fileNames) {
				String shortFileName = FileUtil.getShortFileName(fileName);
				byte[] bytes = DLLocalServiceUtil.getFile(
					companyId, CompanyConstants.SYSTEM, fileName);

				ServiceContext serviceContext = new ServiceContext();

				DLLocalServiceUtil.addFile(
					companyId, CompanyConstants.SYSTEM_STRING,
					GroupConstants.DEFAULT_PARENT_GROUP_ID,
					CompanyConstants.SYSTEM,
					newDirName + StringPool.SLASH + shortFileName, 0,
					StringPool.BLANK, serviceContext.getModifiedDate(null),
					serviceContext, bytes);
			}

			DLLocalServiceUtil.deleteDirectory(
				companyId, CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM, oldDirName);
		}
		catch (Exception e) {
			_log.error(e.getMessage());
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
		if (hasTable("KB_Article")) {
			updateArticles();

			deleteTable("KB_Article");
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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(2);

			sb.append("select * from KB_Article order by resourcePrimKey ");
			sb.append("desc, version desc");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			long selResourcePrimKey = -1;
			int selStatus = -1;

			while (rs.next()) {
				String uuid = rs.getString("uuid_");
				long articleId = rs.getLong("articleId");
				long resourcePrimKey = rs.getLong("resourcePrimKey");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Date createDate = rs.getDate("createDate");
				Date modifiedDate = rs.getDate("modifiedDate");
				long rootResourcePrimKey = rs.getLong("rootResourcePrimKey");
				long parentResourcePrimKey = rs.getLong(
					"parentResourcePrimKey");
				int version = rs.getInt("version");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String description = rs.getString("description");
				double priority = rs.getDouble("priority");
				int viewCount = rs.getInt("viewCount");
				int status = rs.getInt("status");
				long statusByUserId = rs.getLong("statusByUserId");
				String statusByUserName = rs.getString("statusByUserName");
				Date statusDate = rs.getDate("statusDate");

				boolean latest = false;
				boolean main = false;

				if (resourcePrimKey != selResourcePrimKey) {
					selResourcePrimKey = resourcePrimKey;
					selStatus = -1;

					latest = true;
				}

				if ((status != selStatus) && (status == _STATUS_APPROVED)) {
					selStatus = status;

					main = true;
				}

				addKBArticle(
					uuid, articleId, resourcePrimKey, groupId, companyId,
					userId, userName, createDate, modifiedDate,
					rootResourcePrimKey, parentResourcePrimKey, version, title,
					content, description, priority, viewCount, latest, main,
					status, statusByUserId, statusByUserName, statusDate);

				if (main) {
					addKBArticleAttachments(companyId, resourcePrimKey);
				}
				else if (latest) {
					addKBArticleAttachments(companyId, articleId);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String _ARTICLE_DIR_NAME_PREFIX =
		"knowledgebase/articles/";

	private static final String _KB_ARTICLE_DIR_NAME_PREFIX =
		"knowledgebase/kbarticles/";

	private static final int _STATUS_APPROVED = 0;

	private static Log _log = LogFactoryUtil.getLog(UpgradeKBArticle.class);

}