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

package com.liferay.knowledgebase.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.service.base.KBFolderServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.KBFolderPermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KBFolderServiceImpl extends KBFolderServiceBaseImpl {

	@Override
	public KBFolder addKBFolder(
			long groupId, long parentResourceClassNameId,
			long parentResourcePrimKey, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		KBFolderPermission.check(
			getPermissionChecker(), groupId, parentResourcePrimKey,
			ActionKeys.ADD_KB_FOLDER);

		return kbFolderLocalService.addKBFolder(
			getUserId(), groupId, parentResourceClassNameId,
			parentResourcePrimKey, name, description, serviceContext);
	}

	public KBFolder deleteKBFolder(long kbFolderId) throws PortalException {
		KBFolderPermission.check(
			getPermissionChecker(), kbFolderId, ActionKeys.DELETE);

		return kbFolderLocalService.deleteKBFolder(kbFolderId);
	}

	@Override
	public KBFolder getKBFolder(long kbFolderId) throws PortalException {
		KBFolderPermission.check(
			getPermissionChecker(), kbFolderId, ActionKeys.VIEW);

		return kbFolderLocalService.getKBFolder(kbFolderId);
	}

	@Override
	public List<KBFolder> getKBFolders(
			long groupId, long parentKBFolderId, int start, int end)
		throws PortalException {

		return kbFolderPersistence.filterFindByG_P(
			groupId, parentKBFolderId, start, end);
	}

	@Override
	public int getKBFoldersCount(long groupId, long parentKBFolderId)
		throws PortalException {

		return kbFolderPersistence.filterCountByG_P(groupId, parentKBFolderId);
	}

	@Override
	public KBFolder updateKBFolder(
			long parentResourceClassNameId, long parentResourcePrimKey,
			long kbFolderId, String name, String description)
		throws PortalException {

		KBFolderPermission.check(
			getPermissionChecker(), kbFolderId, ActionKeys.UPDATE);

		return kbFolderLocalService.updateKBFolder(
			parentResourceClassNameId, parentResourcePrimKey, kbFolderId, name,
			description);
	}

}