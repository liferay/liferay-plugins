/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * This class is a wrapper for {@link ArticleService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ArticleService
 * @generated
 */
public class ArticleServiceWrapper implements ArticleService {
	public ArticleServiceWrapper(ArticleService articleService) {
		_articleService = articleService;
	}

	public com.liferay.knowledgebase.model.Article addArticle(
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description, int priority,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.addArticle(parentResourcePrimKey, title,
			content, description, priority, dirName, serviceContext);
	}

	public void addAttachment(long companyId, long groupId,
		long resourcePrimKey, java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.addAttachment(companyId, groupId, resourcePrimKey,
			dirName, shortFileName, inputStream);
	}

	public void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.deleteArticle(resourcePrimKey);
	}

	public void deleteAttachment(long companyId, long groupId,
		long resourcePrimKey, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.deleteAttachment(companyId, groupId, resourcePrimKey,
			fileName);
	}

	public com.liferay.knowledgebase.model.Article getArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticle(resourcePrimKey, version);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticles(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		long groupId, long[] resourcePrimKeys, int status,
		long[] viewableParentResourcePrimKeys, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticles(groupId, resourcePrimKeys, status,
			viewableParentResourcePrimKeys, start, end, orderByComparator);
	}

	public int getArticlesCount(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticlesCount(resourcePrimKey, status);
	}

	public int getArticlesCount(long groupId, long[] resourcePrimKeys,
		int status, long[] viewableParentResourcePrimKeys)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticlesCount(groupId, resourcePrimKeys,
			status, viewableParentResourcePrimKeys);
	}

	public java.lang.String getArticleRSS(long resourcePrimKey, int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticleRSS(resourcePrimKey, status, rssDelta,
			rssDisplayStyle, rssFormat, themeDisplay);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, int status, long[] viewableParentResourcePrimKeys,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getGroupArticles(groupId, status,
			viewableParentResourcePrimKeys, start, end, orderByComparator);
	}

	public int getGroupArticlesCount(long groupId, int status,
		long[] viewableParentResourcePrimKeys)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getGroupArticlesCount(groupId, status,
			viewableParentResourcePrimKeys);
	}

	public java.lang.String getGroupArticlesRSS(int status, int rssDelta,
		java.lang.String rssDisplayStyle, java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getGroupArticlesRSS(status, rssDelta,
			rssDisplayStyle, rssFormat, themeDisplay);
	}

	public com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getLatestArticle(resourcePrimKey, status);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getSiblingArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getSiblingArticles(groupId,
			parentResourcePrimKey, status, start, end, orderByComparator);
	}

	public int getSiblingArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getSiblingArticlesCount(groupId,
			parentResourcePrimKey, status);
	}

	public long[] getViewableParentResourcePrimKeys(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getViewableParentResourcePrimKeys(groupId, status);
	}

	public void subscribeArticle(long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.subscribeArticle(groupId, resourcePrimKey);
	}

	public void subscribeGroupArticles(long groupId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.subscribeGroupArticles(groupId, portletId);
	}

	public void unsubscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.unsubscribeArticle(resourcePrimKey);
	}

	public void unsubscribeGroupArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.unsubscribeGroupArticles(groupId, portletId);
	}

	public com.liferay.knowledgebase.model.Article updateArticle(
		long resourcePrimKey, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, int priority, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.updateArticle(resourcePrimKey,
			parentResourcePrimKey, title, content, description, priority,
			dirName, serviceContext);
	}

	public java.lang.String updateAttachments(long companyId, long groupId,
		long resourcePrimKey, java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.updateAttachments(companyId, groupId,
			resourcePrimKey, dirName);
	}

	public ArticleService getWrappedArticleService() {
		return _articleService;
	}

	public void setWrappedArticleService(ArticleService articleService) {
		_articleService = articleService;
	}

	private ArticleService _articleService;
}