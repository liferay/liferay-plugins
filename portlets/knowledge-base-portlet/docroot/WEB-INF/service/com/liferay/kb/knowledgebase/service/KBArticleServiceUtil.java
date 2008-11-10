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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

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
		boolean draft, long parentResourcePrimKey,
		java.lang.String[] tagsEntries, java.lang.String[] categoriesEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addArticle(plid, title, content, description, minorEdit,
			template, draft, parentResourcePrimKey, tagsEntries,
			categoriesEntries, prefs, themeDisplay);
	}

	public static void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().addArticleAttachments(resourcePrimKey, files);
	}

	public static void deleteArticle(long plid, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteArticle(plid, resourcePrimKey);
	}

	public static void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteArticleAttachment(resourcePrimKey, fileName);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticle(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticle(resourcePrimKey, version);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version, long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticle(resourcePrimKey, version, plid);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticle(groupId, title);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticle(groupId, title, version);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version, long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticle(groupId, title, version, plid);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticles(resourcePrimKey, start, end);
	}

	public static java.lang.String getArticlesRSS(long resourcePrimKey,
		int max, java.lang.String type, double version,
		java.lang.String displayStyle, int abstractLength,
		java.lang.String feedURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getArticlesRSS(resourcePrimKey, max, type, version,
			displayStyle, abstractLength, feedURL);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getCompanyArticles(
		long companyId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getCompanyArticles(companyId, max);
	}

	public static java.lang.String getCompanyArticlesRSS(long companyId,
		int max, java.lang.String type, double version,
		java.lang.String displayStyle, int abstractLength,
		java.lang.String description, java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getCompanyArticlesRSS(companyId, max, type, version,
			displayStyle, abstractLength, description, feedURL, themeDisplay);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticlesIncludingUserDrafts(groupId, template,
			userId, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticlesIncludingUserDrafts(groupId, template,
			userId, max);
	}

	public static java.lang.String getGroupArticlesIncludingUserDraftsRSS(
		long groupId, int max, java.lang.String type, double version,
		java.lang.String displayStyle, int abstractLength,
		java.lang.String description, java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticlesIncludingUserDraftsRSS(groupId, max, type,
			version, displayStyle, abstractLength, description, feedURL,
			themeDisplay);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupsArticles(
		long[] groupIds, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getGroupsArticles(groupIds, max);
	}

	public static java.lang.String getGroupsArticlesRSS(long[] groupIds,
		int max, java.lang.String type, double version,
		java.lang.String displayStyle, int abstractLength,
		java.lang.String description, java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getGroupsArticlesRSS(groupIds, max, type, version,
			displayStyle, abstractLength, description, feedURL, themeDisplay);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getSubscribedArticles(userId, groupId, start, end);
	}

	public static void importDocbook(long groupId, java.io.File file,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws java.lang.Exception {
		getService().importDocbook(groupId, file, prefs, themeDisplay);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle revertArticle(
		long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .revertArticle(resourcePrimKey, version, prefs, themeDisplay);
	}

	public static void subscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().subscribe(plid);
	}

	public static void subscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().subscribeArticle(resourcePrimKey);
	}

	public static void unsubscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().unsubscribe(plid);
	}

	public static void unsubscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().unsubscribeArticle(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle updateArticle(
		long plid, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, long parentResourcePrimKey,
		java.lang.String[] tagsEntries, java.lang.String[] categoriesEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateArticle(plid, resourcePrimKey, version, title,
			content, description, minorEdit, template, draft,
			parentResourcePrimKey, tagsEntries, categoriesEntries, prefs,
			themeDisplay);
	}

	public static KBArticleService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("knowledge-base-portlet",
					KBArticleServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("knowledge-base-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new KBArticleServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KBArticleService service) {
		_service = service;
	}

	private static KBArticleService _service;
}