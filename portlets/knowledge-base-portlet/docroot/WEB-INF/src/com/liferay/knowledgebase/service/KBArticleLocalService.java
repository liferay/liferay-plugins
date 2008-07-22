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
 * <a href="KBArticleLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBArticleLocalService {
	public com.liferay.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public void deleteKBArticle(long articleId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long articleId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle addArticle(long userId,
		long groupId, java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle addArticle(
		java.lang.String uuid, long userId, long groupId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean head,
		java.lang.String parentTitle, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addArticleAttachments(long groupId, java.lang.String title,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addArticleResources(long groupId,
		com.liferay.knowledgebase.model.KBArticle article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addArticleResources(long groupId,
		com.liferay.knowledgebase.model.KBArticle article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void changeParent(long userId, long groupId, java.lang.String title,
		java.lang.String newParentTitle,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticle(long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticle(com.liferay.knowledgebase.model.KBArticle article)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticleAttachment(long groupId, java.lang.String title,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticles(long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getChildren(
		long groupId, boolean head, java.lang.String parentTitle)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle getArticle(long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle getArticle(long groupId,
		java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, java.lang.String title, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, boolean head, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getArticles(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException;

	public int getArticlesCount(long groupId)
		throws com.liferay.portal.SystemException;

	public int getArticlesCount(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException;

	public int getArticlesCount(long groupId, boolean head)
		throws com.liferay.portal.SystemException;

	public int getArticlesCount(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException;

	public void moveArticle(long userId, long groupId, java.lang.String title,
		java.lang.String newTitle, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void moveArticle(long userId, long groupId, java.lang.String title,
		java.lang.String newTitle, boolean strict,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void reIndex(java.lang.String[] ids)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle revertArticle(
		long userId, long groupId, java.lang.String title, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.SystemException;

	public void subscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void subscribeArticle(long userId, long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void unsubscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void unsubscribeArticle(long userId, long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle updateArticle(
		long userId, long groupId, java.lang.String title, double version,
		java.lang.String content, java.lang.String description,
		boolean minorEdit, java.lang.String parentTitle,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void updateTagsAsset(long userId,
		com.liferay.knowledgebase.model.KBArticle article,
		java.lang.String[] tagsEntries)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void validateTitle(java.lang.String title)
		throws com.liferay.portal.PortalException;
}