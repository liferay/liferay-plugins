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
 * <a href="KBArticleLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleLocalServiceUtil {
	public static com.liferay.kb.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		return getService().addKBArticle(kbArticle);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle createKBArticle(
		long articleId) {
		return getService().createKBArticle(articleId);
	}

	public static void deleteKBArticle(long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteKBArticle(articleId);
	}

	public static void deleteKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		getService().deleteKBArticle(kbArticle);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle getKBArticle(
		long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getKBArticle(articleId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getKBArticles(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getKBArticles(start, end);
	}

	public static int getKBArticlesCount()
		throws com.liferay.portal.SystemException {
		return getService().getKBArticlesCount();
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		return getService().updateKBArticle(kbArticle);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		long userId, long groupId, java.lang.String title,
		java.lang.String content, java.lang.String description,
		boolean minorEdit, boolean template, boolean draft,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addArticle(userId, groupId, title, content, description,
			minorEdit, template, draft, tagsEntries, prefs, themeDisplay);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		java.lang.String uuid, long userId, long groupId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean head,
		boolean template, boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addArticle(uuid, userId, groupId, title, version, content,
			description, minorEdit, head, template, draft, tagsEntries, prefs,
			themeDisplay);
	}

	public static void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().addArticleAttachments(resourcePrimKey, files);
	}

	public static void addArticleResources(long groupId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService()
			.addArticleResources(groupId, article, addCommunityPermissions,
			addGuestPermissions);
	}

	public static void addArticleResources(long groupId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService()
			.addArticleResources(groupId, article, communityPermissions,
			guestPermissions);
	}

	public static void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteArticle(resourcePrimKey);
	}

	public static void deleteArticle(
		com.liferay.kb.knowledgebase.model.KBArticle article)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteArticle(article);
	}

	public static void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteArticleAttachment(resourcePrimKey, fileName);
	}

	public static void deleteGroupArticles(long groupId, boolean template)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteGroupArticles(groupId, template);
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

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getArticles(resourcePrimKey, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getService().getArticles(resourcePrimKey, start, end, obc);
	}

	public static int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.SystemException {
		return getService().getArticlesCount(resourcePrimKey);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getCompanyArticles(
		long companyId, boolean head, boolean template, boolean draft,
		int start, int end) throws com.liferay.portal.SystemException {
		return getService()
				   .getCompanyArticles(companyId, head, template, draft, start,
			end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getGroupArticles(groupId, head, template, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		return getService().getGroupArticles(groupId, head, template, draft);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, boolean draft, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticles(groupId, head, template, draft, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getGroupArticles(groupId, title, head, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticlesIncludingUserDrafts(groupId, template,
			userId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticlesIncludingUserDrafts(groupId, template,
			userId, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupsArticles(
		long[] groupIds, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getGroupsArticles(groupIds, start, end);
	}

	public static int getGroupArticlesCount(long groupId, boolean head,
		boolean template) throws com.liferay.portal.SystemException {
		return getService().getGroupArticlesCount(groupId, head, template);
	}

	public static int getGroupArticlesCount(long groupId,
		java.lang.String title, boolean head)
		throws com.liferay.portal.SystemException {
		return getService().getGroupArticlesCount(groupId, title, head);
	}

	public static int getGroupArticlesCount(long groupId, boolean head,
		boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		return getService().getGroupArticlesCount(groupId, head, template, draft);
	}

	public static int getGroupArticlesIncludingUserDraftsCount(long groupId,
		boolean template, long userId)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getGroupArticlesIncludingUserDraftsCount(groupId, template,
			userId);
	}

	public static int getGroupsArticlesCount(long[] groupIds)
		throws com.liferay.portal.SystemException {
		return getService().getGroupsArticlesCount(groupIds);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId) throws com.liferay.portal.SystemException {
		return getService().getSubscribedArticles(userId, groupId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getSubscribedArticles(userId, groupId, start, end);
	}

	public static int getSubscribedArticlesCount(long userId, long groupId)
		throws com.liferay.portal.SystemException {
		return getService().getSubscribedArticlesCount(userId, groupId);
	}

	public static void reIndex(java.lang.String[] ids)
		throws com.liferay.portal.SystemException {
		getService().reIndex(ids);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle revertArticle(
		long userId, long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .revertArticle(userId, resourcePrimKey, version, prefs,
			themeDisplay);
	}

	public static com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().search(companyId, groupId, keywords, start, end);
	}

	public static void subscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().subscribe(userId, groupId);
	}

	public static void subscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().subscribeArticle(userId, resourcePrimKey);
	}

	public static void unsubscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().unsubscribe(userId, groupId);
	}

	public static void unsubscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().unsubscribeArticle(userId, resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle updateArticle(
		long userId, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateArticle(userId, resourcePrimKey, version, title,
			content, description, minorEdit, template, draft, tagsEntries,
			prefs, themeDisplay);
	}

	public static void updateTagsAsset(long userId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		java.lang.String[] tagsEntries)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().updateTagsAsset(userId, article, tagsEntries);
	}

	public static void validateTitle(java.lang.String title)
		throws com.liferay.portal.PortalException {
		getService().validateTitle(title);
	}

	public static KBArticleLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("knowledge-base-portlet",
					KBArticleLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("knowledge-base-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new KBArticleLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KBArticleLocalService service) {
		_service = service;
	}

	private static KBArticleLocalService _service;
}