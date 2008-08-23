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

package com.liferay.kb.knowledgebase.service;

/**
 * <a href="KBArticleServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleServiceUtil {
	public static com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		long plid, java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.addArticle(plid, title, content, description,
			minorEdit, template, draft, tagsEntries, prefs, themeDisplay);
	}

	public static void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.addArticleAttachments(resourcePrimKey, files);
	}

	public static void deleteArticle(long plid, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.deleteArticle(plid, resourcePrimKey);
	}

	public static void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.deleteArticleAttachment(resourcePrimKey, fileName);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean template, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getGroupArticles(groupId, template, max);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long userId, long groupId, boolean template, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getGroupArticles(userId, groupId, template, max);
	}

	public static java.lang.String getGroupArticlesRSS(long groupId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String feedURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getGroupArticlesRSS(groupId, max, type, version,
			displayStyle, abstractLength, feedURL);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getArticle(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getArticle(resourcePrimKey, version);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getArticle(groupId, title);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getArticle(groupId, title, version);
	}

	public static java.lang.String getArticlesRSS(long resourcePrimKey,
		int max, java.lang.String type, double version,
		java.lang.String displayStyle, int abstractLength,
		java.lang.String feedURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.getArticlesRSS(resourcePrimKey, max, type, version,
			displayStyle, abstractLength, feedURL);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle revertArticle(
		long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.revertArticle(resourcePrimKey, version, prefs,
			themeDisplay);
	}

	public static void subscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.subscribe(plid);
	}

	public static void subscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.subscribeArticle(resourcePrimKey);
	}

	public static void unsubscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.unsubscribe(plid);
	}

	public static void unsubscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		_service.unsubscribeArticle(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle updateArticle(
		long plid, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		return _service.updateArticle(plid, resourcePrimKey, version, title,
			content, description, minorEdit, template, draft, tagsEntries,
			prefs, themeDisplay);
	}

	public static KBArticleService getService() {
		return _service;
	}

	public void setService(KBArticleService service) {
		_service = service;
	}

	private static KBArticleService _service;
}