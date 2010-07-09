/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticlePermission {

	public static void check(
			PermissionChecker permissionChecker, Article article,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, article, actionId)) {
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
			PermissionChecker permissionChecker, Article article,
			String actionId)
		throws PortalException, SystemException {

		if (actionId.equals(ActionKeys.VIEW)) {
			return _hasPermission(permissionChecker, article);
		}

		return _hasPermission(permissionChecker, article, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		Article article = ArticleLocalServiceUtil.getLatestArticle(
			resourcePrimKey);

		return contains(permissionChecker, article, actionId);
	}

	private static boolean _hasPermission(
			PermissionChecker permissionChecker, Article article)
		throws PortalException, SystemException {

		if (!permissionChecker.hasOwnerPermission(
				article.getCompanyId(), Article.class.getName(),
				article.getResourcePrimKey(), article.getUserId(),
				ActionKeys.VIEW) &&
			!permissionChecker.hasPermission(
				article.getGroupId(), Article.class.getName(),
				article.getResourcePrimKey(), ActionKeys.VIEW)) {

			return false;
		}

		if (article.getParentResourcePrimKey() !=
				ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			article = ArticleLocalServiceUtil.getLatestArticle(
				article.getParentResourcePrimKey());

			return _hasPermission(permissionChecker, article);
		}

		return true;
	}

	private static boolean _hasPermission(
			PermissionChecker permissionChecker, Article article,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.hasOwnerPermission(
				article.getCompanyId(), Article.class.getName(),
				article.getResourcePrimKey(), article.getUserId(), actionId) ||
			permissionChecker.hasPermission(
				article.getGroupId(), Article.class.getName(),
				article.getResourcePrimKey(), actionId)) {

			return true;
		}

		if (article.getParentResourcePrimKey() !=
				ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			article = ArticleLocalServiceUtil.getLatestArticle(
				article.getParentResourcePrimKey());

			return _hasPermission(permissionChecker, article, actionId);
		}

		return false;
	}

}