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

package com.liferay.knowledgebase.service.permission;

import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Adolfo Pérez
 */
public class KBFolderPermission {

	public static void check(
			PermissionChecker permissionChecker, KBFolder kbFolder,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kbFolder, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long kbFolderId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, kbFolderId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kbFolderId,
			String actionId)
		throws PortalException, SystemException {

		KBFolder kbFolder = KBFolderLocalServiceUtil.getKBFolder(kbFolderId);

		check(permissionChecker, kbFolder, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, KBFolder kbFolder,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				kbFolder.getCompanyId(), KBFolder.class.getName(),
				kbFolder.getKbFolderId(), kbFolder.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kbFolder.getGroupId(), KBFolder.class.getName(),
			kbFolder.getKbFolderId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long kbFolderId,
			String actionId)
		throws PortalException, SystemException {

		if (kbFolderId == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return AdminPermission.contains(
				permissionChecker, groupId, actionId);
		}

		KBFolder kbFolder = KBFolderLocalServiceUtil.getKBFolder(kbFolderId);

		return contains(permissionChecker, kbFolder, actionId);
	}

}