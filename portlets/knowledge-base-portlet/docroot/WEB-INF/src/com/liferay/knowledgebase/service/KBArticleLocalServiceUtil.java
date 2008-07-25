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

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		int start, int end) throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getKBArticles(start, end);
	}

	public static int getKBArticlesCount()
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getKBArticlesCount();
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
		boolean minorEdit, boolean template, long parentResourcePrimKey,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.addArticle(userId, groupId, title,
			content, description, minorEdit, template, parentResourcePrimKey,
			tagsEntries, prefs, themeDisplay);
	}

	public static com.liferay.knowledgebase.model.KBArticle addArticle(
		java.lang.String uuid, long userId, long groupId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean head,
		boolean template, long parentResourcePrimKey,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.addArticle(uuid, userId, groupId, title,
			version, content, description, minorEdit, head, template,
			parentResourcePrimKey, tagsEntries, prefs, themeDisplay);
	}

	public static void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.addArticleAttachments(resourcePrimKey, files);
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

	public static void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticle(resourcePrimKey);
	}

	public static void deleteArticle(
		com.liferay.knowledgebase.model.KBArticle article)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticle(article);
	}

	public static void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticleAttachment(resourcePrimKey, fileName);
	}

	public static void deleteArticles(long groupId, boolean template)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.deleteArticles(groupId, template);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getChildren(
		long parentResourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getChildren(parentResourcePrimKey, head);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticle(resourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticle(groupId, title);
	}

	public static com.liferay.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticle(resourcePrimKey, version);
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
		long groupId, boolean head, boolean template, int start, int end)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticles(groupId, head, template,
			start, end);
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

	public static int getArticlesCount(long groupId, boolean head,
		boolean template) throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticlesCount(groupId, head, template);
	}

	public static int getArticlesCount(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.getArticlesCount(groupId, title, head);
	}

	public static void reIndex(java.lang.String[] ids)
		throws com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.reIndex(ids);
	}

	public static com.liferay.knowledgebase.model.KBArticle revertArticle(
		long userId, long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.revertArticle(userId, resourcePrimKey,
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

	public static void subscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.subscribeArticle(userId, resourcePrimKey);
	}

	public static void unsubscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.unsubscribe(userId, groupId);
	}

	public static void unsubscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		kbArticleLocalService.unsubscribeArticle(userId, resourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateArticle(
		long userId, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		long parentResourcePrimKey, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		KBArticleLocalService kbArticleLocalService = KBArticleLocalServiceFactory.getService();

		return kbArticleLocalService.updateArticle(userId, resourcePrimKey,
			version, title, content, description, minorEdit, template,
			parentResourcePrimKey, tagsEntries, prefs, themeDisplay);
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