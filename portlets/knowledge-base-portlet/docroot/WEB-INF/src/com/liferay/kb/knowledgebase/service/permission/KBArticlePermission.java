/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.kb.knowledgebase.service.permission;

import com.liferay.kb.knowledgebase.NoSuchArticleException;
import com.liferay.kb.knowledgebase.model.KBArticle;
import com.liferay.kb.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.tags.model.TagsEntry;
import com.liferay.portlet.tags.service.TagsEntryLocalServiceUtil;

import java.util.List;

/**
 * <a href="KBArticlePermission.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Bruno Farache
 *
 */
public class KBArticlePermission {

	public static void check(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, resourcePrimKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, String title,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, title, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, KBArticle article,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, article, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		KBArticle article = KBArticleLocalServiceUtil.getArticle(
			resourcePrimKey);

		return contains(permissionChecker, article, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String title,
			String actionId)
		throws PortalException, SystemException {

		try {
			KBArticle article = KBArticleLocalServiceUtil.getArticle(
				groupId, title);

			return contains(permissionChecker, article, actionId);
		}
		catch (NoSuchArticleException nspe) {
			return KBPermission.contains(
				permissionChecker, groupId, ActionKeys.ADD_PAGE);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, KBArticle article,
			String actionId)
		throws PortalException, SystemException {

		if (actionId.equals(ActionKeys.VIEW)) {
			List<TagsEntry> entries = TagsEntryLocalServiceUtil.getEntries(
				KBArticle.class.getName(), article.getResourcePrimKey(), false);

			for (TagsEntry entry : entries) {
				if (!permissionChecker.hasPermission(
						entry.getGroupId(), TagsEntry.class.getName(),
							entry.getEntryId(), actionId)) {

					return false;
				}
			}
		}

		return permissionChecker.hasPermission(
			article.getGroupId(), KBArticle.class.getName(),
			article.getResourcePrimKey(), actionId);
	}

}