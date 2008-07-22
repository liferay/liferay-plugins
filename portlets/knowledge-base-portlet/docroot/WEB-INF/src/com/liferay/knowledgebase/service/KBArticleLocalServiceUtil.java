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
 * <a href="KBArticleLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleLocalServiceUtil {
	public static com.liferay.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.addKBArticle(kbArticle);
	}

	public static void deleteKBArticle(long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteKBArticle(articleId);
	}

	public static void deleteKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteKBArticle(kbArticle);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getKBArticle(articleId);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.updateKBArticle(kbArticle);
	}

	public static com.liferay.knowledgebase.model.KBArticle addArticle(
		long userId, long groupId, java.lang.String title,
		java.lang.String content, java.lang.String description,
		java.lang.String summary, boolean minorEdit,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.addArticle(userId, groupId, title,
			content, description, summary, minorEdit, prefs, themeDisplay);
	}

	public static com.liferay.knowledgebase.model.KBArticle addArticle(
		java.lang.String uuid, long userId, long groupId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String description, java.lang.String summary,
		boolean minorEdit, boolean head, java.lang.String parentTitle,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.addArticle(uuid, userId, groupId, title,
			version, content, description, summary, minorEdit, head,
			parentTitle, tagsEntries, prefs, themeDisplay);
	}

	public static void addArticleAttachments(long groupId,
		java.lang.String title,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.addArticleAttachments(groupId, title, files);
	}

	public static void addArticleResources(long groupId,
		com.liferay.knowledgebase.model.KBArticle article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.addArticleResources(groupId, article,
			addCommunityPermissions, addGuestPermissions);
	}

	public static void addArticleResources(long groupId,
		com.liferay.knowledgebase.model.KBArticle article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.addArticleResources(groupId, article,
			communityPermissions, guestPermissions);
	}

	public static void changeParent(long userId, long groupId,
		java.lang.String title, java.lang.String newParentTitle,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.changeParent(userId, groupId, title,
			newParentTitle, prefs, themeDisplay);
	}

	public static void deleteArticle(long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticle(groupId, title);
	}

	public static void deleteArticle(
		com.liferay.knowledgebase.model.KBArticle article)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticle(article);
	}

	public static void deleteArticleAttachment(long groupId,
		java.lang.String title, java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticleAttachment(groupId, title, fileName);
	}

	public static void deleteArticles(long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticles(groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getChildren(
		long groupId, boolean head, java.lang.String parentTitle)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getChildren(groupId, head, parentTitle);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticle(groupId, title);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticle(groupId, title, version);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticles(groupId, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, java.lang.String title, int start, int end)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticles(groupId, title, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticles(groupId, title, start, end, obc);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, boolean head, int start, int end)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticles(groupId, head, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticles(groupId, title, head, start,
			end);
	}

	public static int getArticlesCount(long groupId)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticlesCount(groupId);
	}

	public static int getArticlesCount(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticlesCount(groupId, title);
	}

	public static int getArticlesCount(long groupId, boolean head)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticlesCount(groupId, head);
	}

	public static int getArticlesCount(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticlesCount(groupId, title, head);
	}

	public static void moveArticle(long userId, long groupId,
		java.lang.String title, java.lang.String newTitle,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.moveArticle(userId, groupId, title, newTitle,
			prefs, themeDisplay);
	}

	public static void moveArticle(long userId, long groupId,
		java.lang.String title, java.lang.String newTitle, boolean strict,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.moveArticle(userId, groupId, title, newTitle,
			strict, prefs, themeDisplay);
	}

	public static void reIndex(java.lang.String[] ids)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.reIndex(ids);
	}

	public static com.liferay.knowledgebase.model.KBArticle revertArticle(
		long userId, long groupId, java.lang.String title, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.revertArticle(userId, groupId, title,
			version, prefs, themeDisplay);
	}

	public static com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.search(companyId, groupId, keywords,
			start, end);
	}

	public static void subscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.subscribe(userId, groupId);
	}

	public static void subscribeArticle(long userId, long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.subscribeArticle(userId, groupId, title);
	}

	public static void unsubscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.unsubscribe(userId, groupId);
	}

	public static void unsubscribeArticle(long userId, long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.unsubscribeArticle(userId, groupId, title);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateArticle(
		long userId, long groupId, java.lang.String title, double version,
		java.lang.String content, java.lang.String description,
		java.lang.String summary, boolean minorEdit,
		java.lang.String parentTitle, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.updateArticle(userId, groupId, title,
			version, content, description, summary, minorEdit, parentTitle,
			tagsEntries, prefs, themeDisplay);
	}

	public static void updateTagsAsset(long userId,
		com.liferay.knowledgebase.model.KBArticle article,
		java.lang.String[] tagsEntries)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.updateTagsAsset(userId, article, tagsEntries);
	}

	public static void validateTitle(java.lang.String title)
		throws com.liferay.portal.PortalException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.validateTitle(title);
	}
}