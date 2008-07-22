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

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.base.KBArticleServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
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
			long groupId, String title, String content, String description,
			boolean minorEdit, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return kbArticleLocalService.addArticle(
			getUserId(), groupId, title, content, description, minorEdit, prefs,
			themeDisplay);
	}

	public void addArticleAttachments(
			long groupId, String title,
			List<ObjectValuePair<String, byte[]>> files)
		throws PortalException, SystemException {

		kbArticleLocalService.addArticleAttachments(groupId, title, files);
	}

	public void changeParent(
			long groupId, String title, String newParentTitle,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		kbArticleLocalService.changeParent(
			getUserId(), groupId, title, newParentTitle, prefs, themeDisplay);
	}

	public void deleteArticle(long groupId, String title)
		throws PortalException, SystemException {

		kbArticleLocalService.deleteArticle(groupId, title);
	}

	public void deleteArticleAttachment(
			long groupId, String title, String fileName)
		throws PortalException, SystemException {

		kbArticleLocalService.deleteArticleAttachment(groupId, title, fileName);
	}

	public List<KBArticle> getGroupArticles(long groupId, int max)
		throws PortalException, SystemException {

		List<KBArticle> articles = new ArrayList<KBArticle>();

		Iterator<KBArticle> itr = kbArticleLocalService.getArticles(
			groupId, true, 0, _MAX_END).iterator();

		while (itr.hasNext() && (articles.size() < max)) {
			KBArticle article = itr.next();

			articles.add(article);
		}

		return articles;
	}

	public KBArticle getArticle(long groupId, String title)
		throws PortalException, SystemException {

		return kbArticleLocalService.getArticle(groupId, title);
	}

	public KBArticle getArticle(long groupId, String title, double version)
		throws PortalException, SystemException {

		return kbArticleLocalService.getArticle(groupId, title, version);
	}

	public void moveArticle(
			long groupId, String title, String newTitle,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		kbArticleLocalService.moveArticle(
			getUserId(), groupId, title, newTitle, prefs, themeDisplay);
	}

	public KBArticle revertArticle(
			long groupId, String title, double version,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return kbArticleLocalService.revertArticle(
			getUserId(), groupId, title, version, prefs, themeDisplay);
	}

	public void subscribe(long groupId)
		throws PortalException, SystemException {

		kbArticleLocalService.subscribe(getUserId(), groupId);
	}

	public void subscribeArticle(long groupId, String title)
		throws PortalException, SystemException {

		kbArticleLocalService.subscribeArticle(getUserId(), groupId, title);
	}

	public void unsubscribe(long groupId)
		throws PortalException, SystemException {

		kbArticleLocalService.unsubscribe(getUserId(), groupId);
	}

	public void unsubscribeArticle(long groupId, String title)
		throws PortalException, SystemException {

		kbArticleLocalService.unsubscribeArticle(getUserId(), groupId, title);
	}

	public KBArticle updateArticle(
			long groupId, String title, double version, String content,
			String description, boolean minorEdit, String parentTitle,
			String[] tagsEntries, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return kbArticleLocalService.updateArticle(
			getUserId(), groupId, title, version, content, description,
			minorEdit, parentTitle, tagsEntries, prefs, themeDisplay);
	}

	private static final int _MAX_END = 200;

}