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

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticlePermission {

	public static void check(
			PermissionChecker permissionChecker, KBArticle kbArticle,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, kbArticle, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, resourcePrimKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, KBArticle kbArticle,
			String actionId)
		throws PortalException, SystemException {

		if (actionId.equals(ActionKeys.VIEW) &&
			_PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			long parentResourceClassNameId =
				kbArticle.getParentResourceClassNameId();
			long parentResourcePrimKey = kbArticle.getParentResourcePrimKey();

			long kbFolderClassNameId = PortalUtil.getClassNameId(
				KBFolderConstants.getClassName());

			if ((parentResourcePrimKey ==
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) ||
				(parentResourceClassNameId == kbFolderClassNameId)) {

				if (!KBFolderPermission.contains(
						permissionChecker, kbArticle.getGroupId(),
						parentResourcePrimKey, actionId)) {

					return false;
				}
			}
			else {
				KBArticle parentKBArticle =
					KBArticleLocalServiceUtil.getKBArticle(
						parentResourcePrimKey);

				if (!contains(permissionChecker, parentKBArticle, actionId)) {
					return false;
				}
			}
		}

		if (permissionChecker.hasOwnerPermission(
				kbArticle.getCompanyId(), KBArticle.class.getName(),
				kbArticle.getRootResourcePrimKey(), kbArticle.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kbArticle.getGroupId(), KBArticle.class.getName(),
			kbArticle.getRootResourcePrimKey(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		return contains(permissionChecker, kbArticle, actionId);
	}

	private static final boolean _PERMISSIONS_VIEW_DYNAMIC_INHERITANCE =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE));

}