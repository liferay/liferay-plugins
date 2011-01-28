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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class PriorityHelperUtil {

	public static final int INTERVAL_SIZE = 200;

	public static final long MINIMUM_INCREMENT_SIZE = 1000;

	public static int getHumanPriority(
			long groupId, long resourcePrimKey, long parentResourcePrimKey,
			long priority)
		throws PortalException, SystemException {

		if (resourcePrimKey <= 0) {
			return getMaxHumanPriority(groupId, parentResourcePrimKey, null);
		}

		int count = ArticleServiceUtil.getSiblingArticlesCount(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

		int start = 0;
		int end = count;

		while ((end - start) > INTERVAL_SIZE) {
			int mid = (end - start) / 2;

			List<Article> articles = ArticleServiceUtil.getSiblingArticles(
				groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY,
				mid - 1, mid, new ArticlePriorityComparator(true));

			Article article = articles.get(0);

			if (article.getPriority() < priority) {
				start = mid;
			}
			else {
				end = mid;
			}
		}

		List<Article> articles = ArticleServiceUtil.getSiblingArticles(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY,
			start, end, new ArticlePriorityComparator(true));

		for (Article article : articles) {
			if (article.getResourcePrimKey() == resourcePrimKey) {
				return start + articles.indexOf(article) + 1;
			}
		}

		return getMaxHumanPriority(groupId, parentResourcePrimKey, null);
	}

	public static int getMaxHumanPriority(
			long groupId, long parentResourcePrimKey, Article article)
		throws PortalException, SystemException {

		int count = ArticleServiceUtil.getSiblingArticlesCount(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

		if ((article == null) ||
			(article.getParentResourcePrimKey() != parentResourcePrimKey)) {

			return count + 1;
		}

		return count;
	}

	public static long getPriority(
			long groupId, long resourcePrimKey, long parentResourcePrimKey,
			int humanPriority)
		throws PortalException, SystemException {

		Article nextArticle = getNextArticle(
			groupId, parentResourcePrimKey, humanPriority);

		if (nextArticle == null) {
			List<Article> articles = ArticleLocalServiceUtil.getSiblingArticles(
				groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY, 0,
				1, new ArticlePriorityComparator());

			if (articles.isEmpty()) {
				return MINIMUM_INCREMENT_SIZE;
			}

			Article article = articles.get(0);

			return article.getPriority() + MINIMUM_INCREMENT_SIZE;
		}

		if (nextArticle.getResourcePrimKey() == resourcePrimKey) {
			return nextArticle.getPriority();
		}

		Article prevArticle = getPrevArticle(
			groupId, parentResourcePrimKey, nextArticle.getResourcePrimKey(),
			nextArticle.getPriority());

		if (prevArticle == null) {
			return nextArticle.getPriority() / 2;
		}

		if (nextArticle.getPriority() == (prevArticle.getPriority() + 1)) {
			return nextArticle.getPriority();
		}

		long prevPriority = prevArticle.getPriority();
		long nextPriority = nextArticle.getPriority();

		return prevPriority + (nextPriority - prevPriority) / 2;
	}

	protected static Article getNextArticle(
			long groupId, long parentResourcePrimKey, int humanPriority)
		throws PortalException, SystemException {

		if (humanPriority <= 0) {
			return null;
		}

		List<Article> articles = ArticleServiceUtil.getSiblingArticles(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY,
			humanPriority - 1, humanPriority,
			new ArticlePriorityComparator(true));

		if (articles.isEmpty()) {
			return null;
		}

		return articles.get(0);
	}

	protected static Article getPrevArticle(
			long groupId, long parentResourcePrimKey, long nextResourcePrimKey,
			long nextPriority)
		throws SystemException {

		int count = ArticleLocalServiceUtil.getSiblingArticlesCount(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

		int start = 0;
		int end = count;

		while ((end - start) > INTERVAL_SIZE) {
			int mid = (end - start) / 2;

			List<Article> articles = ArticleLocalServiceUtil.getSiblingArticles(
				groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY,
				mid - 1, mid, new ArticlePriorityComparator(true));

			Article article = articles.get(0);

			if (article.getPriority() < nextPriority) {
				start = mid;
			}
			else {
				end = mid;
			}
		}

		List<Article> articles = ArticleLocalServiceUtil.getSiblingArticles(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY, start,
			end, new ArticlePriorityComparator(true));

		for (int i = 0; i <= articles.size(); i++) {
			Article article = articles.get(i);

			if (article.getResourcePrimKey() != nextResourcePrimKey) {
				continue;
			}

			if (i <= 0) {
				return null;
			}

			return articles.get(i - 1);
		}

		return null;
	}

}