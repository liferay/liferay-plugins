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

package com.liferay.knowledgebase.service;

/**
 * <a href="KBArticleServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleServiceUtil {
	public static com.liferay.knowledgebase.model.KBArticle addArticle(
		long groupId, java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		return kbArticleService.addArticle(groupId, title, content,
			description, minorEdit, prefs, themeDisplay);
	}

	public static void addArticleAttachments(long groupId,
		java.lang.String title,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.addArticleAttachments(groupId, title, files);
	}

	public static void changeParent(long groupId, java.lang.String title,
		java.lang.String newParentTitle,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.changeParent(groupId, title, newParentTitle, prefs,
			themeDisplay);
	}

	public static void deleteArticle(long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.deleteArticle(groupId, title);
	}

	public static void deleteArticleAttachment(long groupId,
		java.lang.String title, java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.deleteArticleAttachment(groupId, title, fileName);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		return kbArticleService.getGroupArticles(groupId, max);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		return kbArticleService.getArticle(groupId, title);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		return kbArticleService.getArticle(groupId, title, version);
	}

	public static com.liferay.knowledgebase.model.KBArticle revertArticle(
		long groupId, java.lang.String title, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		return kbArticleService.revertArticle(groupId, title, version, prefs,
			themeDisplay);
	}

	public static void subscribe(long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.subscribe(groupId);
	}

	public static void subscribeArticle(long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.subscribeArticle(groupId, title);
	}

	public static void unsubscribe(long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.unsubscribe(groupId);
	}

	public static void unsubscribeArticle(long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		kbArticleService.unsubscribeArticle(groupId, title);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateArticle(
		long groupId, java.lang.String title, double version,
		java.lang.String content, java.lang.String description,
		boolean minorEdit, java.lang.String parentTitle,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		KBArticleService kbArticleService = KBArticleServiceFactory.getService();

		return kbArticleService.updateArticle(groupId, title, version, content,
			description, minorEdit, parentTitle, tagsEntries, prefs,
			themeDisplay);
	}
}