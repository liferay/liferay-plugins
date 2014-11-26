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
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Adolfo Pérez
 */
public class SuggestionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			KBArticle kbArticle, String actionId)
		throws PrincipalException {

		if (!actionId.equals(ActionKeys.VIEW_SUGGESTIONS)) {
			throw new IllegalArgumentException(
				"Suggestions only support the " + ActionKeys.VIEW_SUGGESTIONS +
					" permission");
		}

		if (AdminPermission.contains(
				permissionChecker, groupId, ActionKeys.VIEW_SUGGESTIONS) ||
			KBArticlePermission.contains(
				permissionChecker, kbArticle, ActionKeys.UPDATE)) {

			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String className,
			long classPK, String actionId)
		throws PortalException, SystemException {

		if (!className.equals(KBArticleConstants.getClassName())) {
			throw new IllegalArgumentException(
				"Only KB articles support suggestions");
		}

		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			classPK, WorkflowConstants.STATUS_ANY);

		return contains(permissionChecker, groupId, kbArticle, actionId);
	}

}