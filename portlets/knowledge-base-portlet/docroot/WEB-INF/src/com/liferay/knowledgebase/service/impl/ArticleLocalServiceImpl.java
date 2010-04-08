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

import com.liferay.knowledgebase.ArticleContentException;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.base.ArticleLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.ArticleVersionComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <a href="ArticleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ArticleLocalServiceImpl extends ArticleLocalServiceBaseImpl {

	public Article addArticle(
			String uuid, long userId, long parentResourcePrimKey, String title,
			String content, String description, int priority,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);
		long resourcePrimKey = counterLocalService.increment(
			Article.class.getName());
		long groupId = serviceContext.getScopeGroupId();
		double version = 1.0;
		Date now = new Date();

		validate(title, content);

		long articleId = counterLocalService.increment();

		Article article = articlePersistence.create(articleId);

		article.setUuid(uuid);
		article.setResourcePrimKey(resourcePrimKey);
		article.setGroupId(groupId);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(now);
		article.setModifiedDate(now);
		article.setParentResourcePrimKey(parentResourcePrimKey);
		article.setVersion(version);
		article.setTitle(title);
		article.setContent(content);
		article.setDescription(description);
		article.setPriority(priority);

		articlePersistence.update(article, false);

		// Articles

		updateDisplayOrder(article, parentResourcePrimKey, priority);

		return article;
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		Article article = articlePersistence.findByResourcePrimKey_First(
			resourcePrimKey, new ArticleVersionComparator());

		List<Article> articles = getGroupArticles(
			article.getGroupId(), article.getResourcePrimKey(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (Article curArticle : articles) {
			deleteArticle(curArticle.getResourcePrimKey());
		}

		deleteArticle(article);
	}

	public void deleteArticle(Article article) throws SystemException {

		// Article

		articlePersistence.removeByResourcePrimKey(
			article.getResourcePrimKey());
	}

	public void deleteArticles(long groupId)
		throws PortalException, SystemException {

		List<Article> articles = getGroupArticles(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (Article article : articles) {
			deleteArticle(article);
		}
	}

	public Article getArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		return articlePersistence.findByResourcePrimKey_First(
			resourcePrimKey, new ArticleVersionComparator());
	}

	public Article getArticle(long resourcePrimKey, double version)
		throws PortalException, SystemException {

		return articlePersistence.findByR_V(resourcePrimKey, version);
	}

	public List<Article> getArticles(
			long resourcePrimKey, int start, int end, OrderByComparator obc)
		throws SystemException {

		return articlePersistence.findByResourcePrimKey(
			resourcePrimKey, start, end, obc);
	}

	public int getArticlesCount(long resourcePrimKey) throws SystemException {
		return articlePersistence.countByResourcePrimKey(resourcePrimKey);
	}

	public List<Article> getCompanyArticles(
			long companyId, int start, int end, OrderByComparator obc)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("companyId", new Long(companyId));

		return articleFinder.findByMaxVersion(params, start, end, obc);
	}

	public int getCompanyArticlesCount(long companyId) throws SystemException {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("companyId", new Long(companyId));

		return articleFinder.countByMaxVersion(params);
	}

	public List<Article> getGroupArticles(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("groupId", new Long(groupId));

		return articleFinder.findByMaxVersion(params, start, end, obc);
	}

	public List<Article> getGroupArticles(
			long groupId, long parentResourcePrimKey, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("G_P", new Long[] {groupId, parentResourcePrimKey});

		return articleFinder.findByMaxVersion(params, start, end, obc);
	}

	public int getGroupArticlesCount(long groupId) throws SystemException {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("groupId", new Long(groupId));

		return articleFinder.countByMaxVersion(params);
	}

	public int getGroupArticlesCount(long groupId, long parentResourcePrimKey)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("G_P", new Long[] {groupId, parentResourcePrimKey});

		return articleFinder.countByMaxVersion(params);
	}

	public Article updateArticle(
			long userId, long resourcePrimKey, long parentResourcePrimKey,
			String title, String content, String description, int priority,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Article

		Article oldArticle = articlePersistence.findByResourcePrimKey_First(
			resourcePrimKey, new ArticleVersionComparator());
		User user = userPersistence.findByPrimaryKey(userId);
		double version = MathUtil.format(oldArticle.getVersion() + 0.1, 1, 1);
		Date now = new Date();

		validate(title, content);

		long articleId = counterLocalService.increment();

		Article article = articlePersistence.create(articleId);

		article.setResourcePrimKey(oldArticle.getResourcePrimKey());
		article.setGroupId(oldArticle.getGroupId());
		article.setCompanyId(oldArticle.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(oldArticle.getCreateDate());
		article.setModifiedDate(now);
		article.setParentResourcePrimKey(parentResourcePrimKey);
		article.setVersion(version);
		article.setTitle(title);
		article.setContent(content);
		article.setDescription(description);
		article.setPriority(priority);

		articlePersistence.update(article, false);

		// Articles

		updateDisplayOrder(article, parentResourcePrimKey, priority);

		return article;
	}

	public Article updateDisplayOrder(
			Article article, long parentResourcePrimKey, int priority)
		throws SystemException {

		List<Article> articles = ListUtil.copy(getGroupArticles(
			article.getGroupId(), article.getParentResourcePrimKey(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator(true)));

		articles.remove(article);
		articles.add(priority, article);

		for (int i = 0; i < articles.size(); i++) {
			Article curArticle = articles.get(i);

			long curParentResourcePrimKey =
				curArticle.getParentResourcePrimKey();

			if (priority == i) {
				curParentResourcePrimKey = parentResourcePrimKey;
			}

			List<Article> articles2 =
				articlePersistence.findByResourcePrimKey(
					curArticle.getResourcePrimKey());

			for (Article curArticle2 : articles2) {
				curArticle2.setParentResourcePrimKey(curParentResourcePrimKey);
				curArticle2.setPriority(i);

				articlePersistence.update(curArticle2, false);

				if (article.getArticleId() == curArticle2.getArticleId()) {
					article = curArticle2;
				}
			}
		}

		return article;
	}

	protected void validate(String title, String content)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new ArticleTitleException();
		}

		if (Validator.isNull(content)) {
			throw new ArticleContentException();
		}
	}

}