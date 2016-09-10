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

package com.liferay.sync.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.GroupActionableDynamicQuery;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil;
import com.liferay.sync.model.SyncDLObjectConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Dennis Ju
 */
public class VerifyUtil {

	public static void verify() throws Exception {
		VerifyUtil verifyUtil = new VerifyUtil();

		verifyUtil.doVerify();
	}

	protected void doVerify() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("truncate table SyncDLObject");

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}

		ActionableDynamicQuery actionableDynamicQuery =
			new GroupActionableDynamicQuery() {

				@Override
				protected void addCriteria(DynamicQuery dynamicQuery) {
					Property classNameId = PropertyFactoryUtil.forName(
						"classNameId");
					Property siteProperty = PropertyFactoryUtil.forName("site");

					dynamicQuery.add(
						RestrictionsFactoryUtil.or(
							classNameId.eq(
								PortalUtil.getClassNameId(User.class)),
							siteProperty.eq(true)));
				}

				@Override
				protected void performAction(Object object)
					throws PortalException, SystemException {

					Group group = (Group)object;

					if (group.isStaged()) {
						return;
					}

					try {
						verifyDLFileEntriesAndFolders(group.getGroupId());

						verifyLocks(group.getGroupId());
						verifyMacPackages(group.getGroupId());
					}
					catch (Exception e) {
						throw new PortalException(e);
					}
				}
			};

		actionableDynamicQuery.performActions();

		DLSyncEventLocalServiceUtil.deleteDLSyncEvents();
	}

	protected void verifyDLFileEntriesAndFolders(long groupId)
		throws Exception {

		StringBundler sb1 = new StringBundler(51);

		sb1.append("select DLFolder.companyId, DLFolder.userId, ");
		sb1.append("DLFolder.userName, DLFolder.createDate, ");
		sb1.append("DLFolder.modifiedDate, DLFolder.repositoryId, ");
		sb1.append("DLFolder.parentFolderId as parentFolderId, ");
		sb1.append("DLFolder.treePath, DLFolder.name, '' as extension, ");
		sb1.append("'' as mimeType, DLFolder.description, '' as ");
		sb1.append("changeLog, '' as version, 0 as versionId, 0 as size_, '");
		sb1.append(SyncDLObjectConstants.TYPE_FOLDER);
		sb1.append("' as type, DLFolder.folderId as typePK, ");
		sb1.append("DLFolder.uuid_ as typeUuid, DLFolder.status ");
		sb1.append("from DLFolder where DLFolder.repositoryId = ");
		sb1.append(groupId);
		sb1.append(" union all select DLFileVersion.companyId, ");
		sb1.append("DLFileVersion.userId, DLFileVersion.userName, ");
		sb1.append("DLFileVersion.createDate, DLFileVersion.modifiedDate, ");
		sb1.append("DLFileVersion.repositoryId, DLFileVersion.folderId as ");
		sb1.append("parentFolderId, DLFileVersion.treePath, ");
		sb1.append("DLFileVersion.title as name, DLFileVersion.extension, ");
		sb1.append("DLFileVersion.mimeType, DLFileVersion.description, ");
		sb1.append("DLFileVersion.changeLog, DLFileVersion.version, ");
		sb1.append("DLFileVersion.fileVersionId as versionId, ");
		sb1.append("DLFileVersion.size_ as size_, '");
		sb1.append(SyncDLObjectConstants.TYPE_FILE);
		sb1.append("' as type, DLFileVersion.fileEntryId as typePK, ");
		sb1.append("DLFileEntry.uuid_ as typeUuid, DLFileVersion.status ");
		sb1.append("from DLFileEntry, DLFileVersion where ");
		sb1.append("DLFileEntry.repositoryId = ");
		sb1.append(groupId);
		sb1.append(" and DLFileEntry.fileEntryId = DLFileVersion.fileEntryId ");
		sb1.append("and DLFileEntry.version = DLFileVersion.version ");
		sb1.append("union all select DLFileVersion.companyId, ");
		sb1.append("DLFileVersion.userId, DLFileVersion.userName, ");
		sb1.append("DLFileVersion.createDate, DLFileVersion.modifiedDate, ");
		sb1.append("DLFileVersion.repositoryId, DLFileVersion.folderId ");
		sb1.append("as parentFolderId, DLFileVersion.treePath, ");
		sb1.append("DLFileVersion.title as name, DLFileVersion.extension, ");
		sb1.append("DLFileVersion.mimeType, DLFileVersion.description, ");
		sb1.append("DLFileVersion.changeLog, DLFileVersion.version, ");
		sb1.append("DLFileVersion.fileVersionId as versionId, ");
		sb1.append("DLFileVersion.size_ as size_, '");
		sb1.append(SyncDLObjectConstants.TYPE_PRIVATE_WORKING_COPY);
		sb1.append("' as type, DLFileVersion.fileEntryId as typePK, ");
		sb1.append("DLFileEntry.uuid_ as typeUuid, DLFileVersion.status ");
		sb1.append("from DLFileEntry, DLFileVersion where ");
		sb1.append("DLFileEntry.repositoryId = ");
		sb1.append(groupId);
		sb1.append(" and DLFileEntry.fileEntryId = DLFileVersion.fileEntryId ");
		sb1.append("and DLFileVersion.version = '");
		sb1.append(DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION);
		sb1.append("'");

		StringBundler sb2 = new StringBundler(6);

		sb2.append("insert into SyncDLObject (syncDLObjectId, companyId, ");
		sb2.append("userId, userName, createTime, modifiedTime, ");
		sb2.append("repositoryId, parentFolderId, treePath, name, extension, ");
		sb2.append("mimeType, description, changeLog, version, versionId, ");
		sb2.append("size_, event, type_, typePK, typeUuid) values (?, ?, ?, ");
		sb2.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Connection con = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps1 = con.prepareStatement(sb1.toString());

			rs = ps1.executeQuery();

			PreparedStatement ps2 = con.prepareStatement(sb2.toString());

			while (rs.next()) {
				Timestamp createDate = rs.getTimestamp("createDate");
				Timestamp modifiedDate = rs.getTimestamp("modifiedDate");
				int status = rs.getInt("status");

				String event = StringPool.BLANK;

				if (status == WorkflowConstants.STATUS_IN_TRASH) {
					event = SyncDLObjectConstants.EVENT_TRASH;
				}
				else {
					event = SyncDLObjectConstants.EVENT_ADD;
				}

				ps2.setLong(1, CounterLocalServiceUtil.increment());
				ps2.setLong(2, rs.getLong("companyId"));
				ps2.setLong(3, rs.getLong("userId"));
				ps2.setString(4, rs.getString("userName"));
				ps2.setLong(5, createDate.getTime());
				ps2.setLong(6, modifiedDate.getTime());
				ps2.setLong(7, groupId);
				ps2.setLong(8, rs.getLong("parentFolderId"));
				ps2.setString(9, rs.getString("treePath"));
				ps2.setString(10, rs.getString("name"));
				ps2.setString(11, rs.getString("extension"));
				ps2.setString(12, rs.getString("mimeType"));
				ps2.setString(13, rs.getString("description"));
				ps2.setString(14, rs.getString("changeLog"));
				ps2.setString(15, rs.getString("version"));
				ps2.setLong(16, rs.getLong("versionId"));
				ps2.setLong(17, rs.getLong("size_"));
				ps2.setString(18, event);
				ps2.setString(19, rs.getString("type"));
				ps2.setLong(20, rs.getLong("typePK"));
				ps2.setString(21, rs.getString("typeUuid"));

				ps2.executeUpdate();
			}

			ps2.close();
		}
		finally {
			DataAccess.cleanUp(con, ps1, rs);
		}
	}

	protected void verifyLocks(long groupId) throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("select Lock_.expirationDate, Lock_.userId, ");
		sb.append("Lock_.userName, DLFileVersion.fileEntryId from ");
		sb.append("DLFileVersion, Lock_ where DLFileVersion.version = '");
		sb.append(DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION);
		sb.append("' and CAST_TEXT(DLFileVersion.fileEntryId) = Lock_.key_");

		String sql = PortalUtil.transformSQL(sb.toString());

		Connection con = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps1 = con.prepareStatement(PortalUtil.transformSQL(sql));

			rs = ps1.executeQuery();

			PreparedStatement ps2 = con.prepareStatement(
				"update SyncDLObject set lockExpirationDate = ?, " +
					"lockUserId = ?, lockUserName = ? where typePK = ? " +
					"and repositoryId = " + groupId);

			while (rs.next()) {
				ps2.setTimestamp(1, rs.getTimestamp("expirationDate"));
				ps2.setLong(2, rs.getLong("userId"));
				ps2.setString(3, rs.getString("userName"));
				ps2.setLong(4, rs.getLong("fileEntryId"));

				ps2.executeUpdate();
			}

			ps2.close();
		}
		finally {
			DataAccess.cleanUp(con, ps1, rs);
		}
	}

	protected void verifyMacPackages(long groupId) throws Exception {
		String[] fileNames =
			PortletPropsValues.SYNC_MAC_PACKAGE_METADATA_FILE_NAMES;

		StringBundler sb = new StringBundler((fileNames.length * 4) + 5);

		sb.append("select DLFolder.folderId, DLFolder.name from DLFolder, ");
		sb.append("DLFileEntry where DLFolder.repositoryId = ");
		sb.append(groupId);
		sb.append(" and DLFileEntry.folderId = DLFolder.folderId and ");
		sb.append("DLFileEntry.title in (");

		for (int i = 0; i < fileNames.length; i++) {
			sb.append(StringPool.APOSTROPHE);
			sb.append(fileNames[i]);
			sb.append(StringPool.APOSTROPHE);

			if (i != (fileNames.length - 1)) {
				sb.append(CharPool.COMMA);
			}
		}

		sb.append(")");

		Connection con = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps1 = con.prepareStatement(sb.toString());

			rs = ps1.executeQuery();

			PreparedStatement ps2 = con.prepareStatement(
				"update SyncDLObject set extraSettings = ? where typePK = ?");

			while (rs.next()) {
				long folderId = rs.getLong("folderId");
				String name = rs.getString("name");

				String extension = FileUtil.getExtension(name);

				if (!ArrayUtil.contains(
						PortletPropsValues.
						SYNC_MAC_PACKAGE_FOLDER_EXTENSIONS, extension)) {

					continue;
				}

				JSONObject extraSettingsJSONObject =
					JSONFactoryUtil.createJSONObject();

				extraSettingsJSONObject.put("macPackage", true);

				ps2.setString(1, extraSettingsJSONObject.toString());
				ps2.setLong(2, folderId);

				ps2.executeUpdate();
			}

			ps2.close();
		}
		finally {
			DataAccess.cleanUp(con, ps1, rs);
		}
	}

}