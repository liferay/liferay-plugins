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
 * <a href="KBArticleLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBArticleLocalService {
	public com.liferay.kb.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public void deleteKBArticle(long articleId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle getKBArticle(
		long articleId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getKBArticles(
		int start, int end) throws com.liferay.portal.SystemException;

	public int getKBArticlesCount() throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		long userId, long groupId, java.lang.String title,
		java.lang.String content, java.lang.String description,
		boolean minorEdit, boolean template, boolean draft,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		java.lang.String uuid, long userId, long groupId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean head,
		boolean template, boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addArticleResources(long groupId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addArticleResources(long groupId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticle(
		com.liferay.kb.knowledgebase.model.KBArticle article)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteGroupArticles(long groupId, boolean template)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getCompanyArticles(
		long companyId, boolean head, boolean template, boolean draft,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long userId, long groupId, boolean head, boolean template, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long userId, long groupId, boolean head, boolean template,
		boolean draft, int start, int end)
		throws com.liferay.portal.SystemException;

	public int getGroupArticlesCount(long groupId, boolean head,
		boolean template) throws com.liferay.portal.SystemException;

	public int getGroupArticlesCount(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException;

	public int getGroupArticlesCount(long userId, long groupId, boolean head,
		boolean template, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public int getSubscribedArticlesCount(long userId, long groupId)
		throws com.liferay.portal.SystemException;

	public void reIndex(java.lang.String[] ids)
		throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle revertArticle(
		long userId, long resourcePrimKey, double version,
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

	public void subscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void unsubscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void unsubscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBArticle updateArticle(
		long userId, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void updateTagsAsset(long userId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		java.lang.String[] tagsEntries)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void validateTitle(java.lang.String title)
		throws com.liferay.portal.PortalException;
}