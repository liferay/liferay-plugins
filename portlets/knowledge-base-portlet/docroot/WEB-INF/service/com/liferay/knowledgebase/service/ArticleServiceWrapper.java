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
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticles(resourcePrimKey, start, end,
			orderByComparator);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticles(params, allVersions, start, end,
			orderByComparator);
	}

	public int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticlesCount(resourcePrimKey);
	}

	public int getArticlesCount(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticlesCount(params, allVersions);
	}

	public java.lang.String getArticlesRSS(java.lang.String portletId,
		long resourcePrimKey, int max, java.lang.String type, double version,
		java.lang.String displayStyle, boolean maximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getArticlesRSS(portletId, resourcePrimKey, max,
			type, version, displayStyle, maximized, themeDisplay);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getCompanyArticles(
		long companyId, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getCompanyArticles(companyId, allVersions,
			start, end, orderByComparator);
	}

	public int getCompanyArticlesCount(long companyId, boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getCompanyArticlesCount(companyId, allVersions);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getGroupArticles(groupId, allVersions, start,
			end, orderByComparator);
	}

	public int getGroupArticlesCount(long groupId, boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getGroupArticlesCount(groupId, allVersions);
	}

	public java.lang.String getGroupArticlesRSS(java.lang.String portletId,
		int max, java.lang.String type, double version,
		java.lang.String displayStyle, boolean maximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getGroupArticlesRSS(portletId, max, type,
			version, displayStyle, maximized, themeDisplay);
	}

	public com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _articleService.getLatestArticle(resourcePrimKey);
	}

	public void subscribe(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.subscribe(groupId);
	}

	public void subscribeArticle(java.lang.String portletId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.subscribeArticle(portletId, resourcePrimKey);
	}

	public void unsubscribe(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.unsubscribe(groupId);
	}

	public void unsubscribeArticle(long companyId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_articleService.unsubscribeArticle(companyId, resourcePrimKey);
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

	private ArticleService _articleService;
}