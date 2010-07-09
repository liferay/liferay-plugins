/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.knowledgebase.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link ArticleLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ArticleLocalService
 * @generated
 */
public class ArticleLocalServiceWrapper implements ArticleLocalService {
	public ArticleLocalServiceWrapper(ArticleLocalService articleLocalService) {
		_articleLocalService = articleLocalService;
	}

	public com.liferay.knowledgebase.model.Article addArticle(
		com.liferay.knowledgebase.model.Article article)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.addArticle(article);
	}

	public com.liferay.knowledgebase.model.Article createArticle(long articleId) {
		return _articleLocalService.createArticle(articleId);
	}

	public void deleteArticle(long articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.deleteArticle(articleId);
	}

	public void deleteArticle(com.liferay.knowledgebase.model.Article article)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.deleteArticle(article);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.knowledgebase.model.Article getArticle(long articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticle(articleId);
	}

	public com.liferay.knowledgebase.model.Article getArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticleByUuidAndGroupId(uuid, groupId);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticles(start, end);
	}

	public int getArticlesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticlesCount();
	}

	public com.liferay.knowledgebase.model.Article updateArticle(
		com.liferay.knowledgebase.model.Article article)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.updateArticle(article);
	}

	public com.liferay.knowledgebase.model.Article updateArticle(
		com.liferay.knowledgebase.model.Article article, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.updateArticle(article, merge);
	}

	public com.liferay.knowledgebase.model.Article addArticle(long userId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description, int priority,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.addArticle(userId, parentResourcePrimKey,
			title, content, description, priority, dirName, serviceContext);
	}

	public void addArticleResources(
		com.liferay.knowledgebase.model.Article article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.addArticleResources(article,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addArticleResources(
		com.liferay.knowledgebase.model.Article article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.addArticleResources(article, communityPermissions,
			guestPermissions);
	}

	public void addAttachment(long companyId, java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.addAttachment(companyId, dirName, shortFileName,
			inputStream);
	}

	public void checkAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.checkAttachments();
	}

	public void deleteAttachment(long companyId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.deleteAttachment(companyId, fileName);
	}

	public void deleteGroupArticles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.deleteGroupArticles(groupId);
	}

	public com.liferay.knowledgebase.model.Article getArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticle(resourcePrimKey, version);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticles(resourcePrimKey, start, end,
			orderByComparator);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticles(params, allVersions, start,
			end, orderByComparator);
	}

	public int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticlesCount(resourcePrimKey);
	}

	public int getArticlesCount(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getArticlesCount(params, allVersions);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getCompanyArticles(
		long companyId, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getCompanyArticles(companyId, allVersions,
			start, end, orderByComparator);
	}

	public int getCompanyArticlesCount(long companyId, boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getCompanyArticlesCount(companyId,
			allVersions);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getGroupArticles(groupId, allVersions,
			start, end, orderByComparator);
	}

	public int getGroupArticlesCount(long groupId, boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getGroupArticlesCount(groupId, allVersions);
	}

	public com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.getLatestArticle(resourcePrimKey);
	}

	public void subscribe(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.subscribe(groupId, userId);
	}

	public void subscribeArticle(java.lang.String portletId, long userId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.subscribeArticle(portletId, userId, resourcePrimKey);
	}

	public void unsubscribe(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.unsubscribe(groupId, userId);
	}

	public void unsubscribeArticle(long companyId, long userId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.unsubscribeArticle(companyId, userId,
			resourcePrimKey);
	}

	public void unsubscribeArticle(
		com.liferay.portal.model.Subscription subscription)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.unsubscribeArticle(subscription);
	}

	public com.liferay.knowledgebase.model.Article updateArticle(long userId,
		long resourcePrimKey, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, int priority, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.updateArticle(userId, resourcePrimKey,
			parentResourcePrimKey, title, content, description, priority,
			dirName, serviceContext);
	}

	public void updateArticleResources(
		com.liferay.knowledgebase.model.Article article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleLocalService.updateArticleResources(article,
			communityPermissions, guestPermissions);
	}

	public java.lang.String updateAttachments(long companyId,
		long resourcePrimKey, java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.updateAttachments(companyId,
			resourcePrimKey, dirName);
	}

	public com.liferay.knowledgebase.model.Article updateDisplayOrder(
		com.liferay.knowledgebase.model.Article article,
		long parentResourcePrimKey, int priority)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleLocalService.updateDisplayOrder(article,
			parentResourcePrimKey, priority);
	}

	public ArticleLocalService getWrappedArticleLocalService() {
		return _articleLocalService;
	}

	private ArticleLocalService _articleLocalService;
}