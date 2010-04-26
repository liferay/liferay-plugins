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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.base.ArticleServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="ArticleServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleServiceImpl extends ArticleServiceBaseImpl {

	public Article addArticle(
			long parentResourcePrimKey, String title, String content,
			String description, int priority, ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ARTICLE);

		return articleLocalService.addArticle(
			null, getUserId(), parentResourcePrimKey, title, content,
			description, priority, serviceContext);
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.DELETE);

		articleLocalService.deleteArticle(resourcePrimKey);
	}

	public Article getArticle(long resourcePrimKey, double version)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return articleLocalService.getArticle(resourcePrimKey, version);
	}

	public List<Article> getArticles(
			long resourcePrimKey, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getArticles(
			resourcePrimKey, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public int getArticlesCount(long resourcePrimKey) throws SystemException {
		return articleLocalService.getArticlesCount(resourcePrimKey);
	}

	public List<Article> getCompanyArticles(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getCompanyArticles(
			companyId, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public int getCompanyArticlesCount(long companyId) throws SystemException {
		return articleLocalService.getCompanyArticlesCount(companyId);
	}

	public List<Article> getGroupArticles(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getGroupArticles(
			groupId, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public List<Article> getGroupArticles(
			long groupId, long parentResourcePrimKey, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getGroupArticles(
			groupId, parentResourcePrimKey, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public int getGroupArticlesCount(long groupId) throws SystemException {
		return articleLocalService.getGroupArticlesCount(groupId);
	}

	public int getGroupArticlesCount(long groupId, long parentResourcePrimKey)
		throws SystemException {

		return articleLocalService.getGroupArticlesCount(
			groupId, parentResourcePrimKey);
	}

	public Article getLatestArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return articleLocalService.getLatestArticle(resourcePrimKey);
	}

	public Article updateArticle(
			long resourcePrimKey, long parentResourcePrimKey, String title,
			String content, String description, int priority,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		return articleLocalService.updateArticle(
			getUserId(), resourcePrimKey, parentResourcePrimKey, title, content,
			description, priority, serviceContext);
	}

	protected List<Article> filterArticles(List<Article> articles)
		throws PortalException, SystemException {

		articles = ListUtil.copy(articles);

		Iterator<Article> itr = articles.iterator();

		while (itr.hasNext()) {
			if (!ArticlePermission.contains(
					getPermissionChecker(), itr.next(), ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return articles;
	}

}