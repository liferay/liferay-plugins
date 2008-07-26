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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.KnowledgeBaseKeys;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.base.KBArticleServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.service.permission.KBPermission;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * <a href="KBArticleServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleServiceImpl extends KBArticleServiceBaseImpl {

	public KBArticle addArticle(
			long plid, String title, String content, String description,
			boolean minorEdit, boolean template, long parentResourcePrimKey,
			String[] tagsEntries, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		if (template) {
			KBPermission.check(
				getPermissionChecker(), plid,
				KnowledgeBaseKeys.MANAGE_TEMPLATES);
		}
		else {
			KBPermission.check(
				getPermissionChecker(), plid, ActionKeys.ADD_ARTICLE);
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		return kbArticleLocalService.addArticle(
			getUserId(), layout.getGroupId(), title, content, description,
			minorEdit, template, parentResourcePrimKey, tagsEntries, prefs,
			themeDisplay);
	}

	public void addArticleAttachments(
			long resourcePrimKey, List<ObjectValuePair<String, byte[]>> files)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		kbArticleLocalService.addArticleAttachments(resourcePrimKey, files);
	}

	public void deleteArticle(long plid, long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticle article = kbArticleLocalService.getArticle(resourcePrimKey);

		if (article.isTemplate()) {
			KBPermission.check(
				getPermissionChecker(), plid,
				KnowledgeBaseKeys.MANAGE_TEMPLATES);
		}
		else {
			KBArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.DELETE);
		}

		kbArticleLocalService.deleteArticle(resourcePrimKey);
	}

	public void deleteArticleAttachment(long resourcePrimKey, String fileName)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		kbArticleLocalService.deleteArticleAttachment(
			resourcePrimKey, fileName);
	}

	public List<KBArticle> getGroupArticles(
			long groupId, boolean template, int max)
		throws PortalException, SystemException {

		List<KBArticle> articles = new ArrayList<KBArticle>();

		Iterator<KBArticle> itr = kbArticleLocalService.getArticles(
			groupId, true, template, 0, _MAX_END).iterator();

		while (itr.hasNext() && (articles.size() < max)) {
			KBArticle article = itr.next();

			if (KBArticlePermission.contains(getPermissionChecker(), article,
					ActionKeys.VIEW)) {

				articles.add(article);
			}
		}

		return articles;
	}

	public KBArticle getArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return kbArticleLocalService.getArticle(resourcePrimKey);
	}

	public KBArticle getArticle(long resourcePrimKey, double version)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return kbArticleLocalService.getArticle(resourcePrimKey, version);
	}

	public KBArticle getArticle(long groupId, String title)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), groupId, title, ActionKeys.VIEW);

		return kbArticleLocalService.getArticle(groupId, title);
	}

	public KBArticle getArticle(long groupId, String title, double version)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), groupId, title, ActionKeys.VIEW);

		return kbArticleLocalService.getArticle(groupId, title, version);
	}

	public KBArticle revertArticle(
			long resourcePrimKey, double version, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		return kbArticleLocalService.revertArticle(
			getUserId(), resourcePrimKey, version, prefs, themeDisplay);
	}

	public void subscribe(long plid)
		throws PortalException, SystemException {

		KBPermission.check(
			getPermissionChecker(), plid, ActionKeys.SUBSCRIBE);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		kbArticleLocalService.subscribe(getUserId(), layout.getGroupId());
	}

	public void subscribeArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		kbArticleLocalService.subscribeArticle(getUserId(), resourcePrimKey);
	}

	public void unsubscribe(long plid)
		throws PortalException, SystemException {

		KBPermission.check(
			getPermissionChecker(), plid, ActionKeys.SUBSCRIBE);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		kbArticleLocalService.unsubscribe(getUserId(), layout.getGroupId());
	}

	public void unsubscribeArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		kbArticleLocalService.unsubscribeArticle(getUserId(), resourcePrimKey);
	}

	public KBArticle updateArticle(
			long plid, long resourcePrimKey, double version, String title,
			String content, String description, boolean minorEdit,
			boolean template, long parentResourcePrimKey, String[] tagsEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		if (template) {
			KBPermission.check(
				getPermissionChecker(), plid,
				KnowledgeBaseKeys.MANAGE_TEMPLATES);
		}
		else {
			KBArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		return kbArticleLocalService.updateArticle(
			getUserId(), resourcePrimKey, version, title, content, description,
			minorEdit, template, parentResourcePrimKey, tagsEntries, prefs,
			themeDisplay);
	}

	private static final int _MAX_END = 200;

}