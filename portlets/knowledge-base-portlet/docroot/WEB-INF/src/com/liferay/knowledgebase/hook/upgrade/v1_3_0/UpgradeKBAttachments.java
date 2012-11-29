/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.hook.upgrade.v1_3_0;

import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.v6_2_0.BaseUpgradeAttachments;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Sergio González
 */
public class UpgradeKBAttachments extends BaseUpgradeAttachments {

	protected void deleteEmptyDirectories() throws Exception {
		for (long companyId : PortalUtil.getCompanyIds()) {
			try {
				DLStoreUtil.deleteDirectory(
					companyId, CompanyConstants.SYSTEM,
					"knowledgebase/kbarticles");
			}
			catch (NoSuchDirectoryException nsde) {
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateAttachments();
		deleteEmptyDirectories();
	}

	@Override
	protected long getClassNameId() {
		return PortalUtil.getClassNameId(
			"com.liferay.portal.repository.liferayrepository." +
				"LiferayRepository");
	}

	@Override
	protected long getContainerModelFolderId(
			long groupId, long companyId, long resourcePrimKey,
			long containerId, long userId, String userName,
			Timestamp createDate)
		throws Exception {

		long repositoryId = getRepositoryId(
			groupId, companyId, userId, userName, createDate, getClassNameId(),
			getPortletId());

		long repositoryFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, getPortletId(), false);

		long kbArticleFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			repositoryFolderId, String.valueOf(resourcePrimKey), false);

		return kbArticleFolderId;
	}

	@Override
	protected String getDirName(long containerModelId, long resourcePrimKey) {
		return "knowledgebase/kbarticles/" + resourcePrimKey;
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.KNOWLEDGE_BASE_ARTICLE;
	}

	@Override
	protected void updateAttachments() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select kbArticleId, resourcePrimKey, groupId, companyId, " +
					"userId, userName, status from KBArticle group by " +
						"resourcePrimKey");

			rs = ps.executeQuery();

			while (rs.next()) {
				long kbArticleId = rs.getLong("kbArticleId");
				long resourcePrimKey = rs.getLong("resourcePrimKey");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				int status = rs.getInt("status");

				long classPK = resourcePrimKey;

				if (status != WorkflowConstants.STATUS_APPROVED) {
					classPK = kbArticleId;
				}

				updateEntryAttachments(
					companyId, groupId, classPK, 0, userId, userName);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}