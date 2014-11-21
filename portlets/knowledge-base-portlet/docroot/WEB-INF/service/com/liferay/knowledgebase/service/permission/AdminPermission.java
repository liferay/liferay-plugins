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

import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminPermission {

	public static final String RESOURCE_NAME =
		"com.liferay.knowledgebase.admin";

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return permissionChecker.hasPermission(
			groupId, RESOURCE_NAME, groupId, actionId);
	}

	public static boolean hasAccessToSuggestions(
			PermissionChecker permissionChecker, long groupId, String className,
			long classPK)
		throws PortalException {

		if (!AdminPermission.contains(
				permissionChecker, groupId, ActionKeys.VIEW_KB_SUGGESTIONS)) {

			return false;
		}

		if (className.equals(KBArticleConstants.getClassName())) {
			return KBArticlePermission.contains(
				permissionChecker, classPK, ActionKeys.UPDATE);
		}
		else if (className.equals(KBFolderConstants.getClassName())) {
			throw new IllegalArgumentException(
				"KBFolders don't support comments");
		}
		else {
			throw new IllegalArgumentException(
				String.format(
					"Received className %s; expected %s", className,
					KBArticleConstants.getClassName()));
		}
	}

}