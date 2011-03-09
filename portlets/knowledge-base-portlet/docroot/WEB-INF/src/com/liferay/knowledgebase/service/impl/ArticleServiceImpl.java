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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.model.ArticleSearchDisplay;
import com.liferay.knowledgebase.model.impl.ArticleSearchDisplayImpl;
import com.liferay.knowledgebase.service.base.ArticleServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.RSSUtil;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleServiceImpl extends ArticleServiceBaseImpl {

	public Article addArticle(
			long parentResourcePrimKey, String title, String content,
			String description, String dirName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ARTICLE);

		return articleLocalService.addArticle(
			getUserId(), parentResourcePrimKey, title, content, description,
			dirName, serviceContext);
	}

	public void addAttachment(
			long companyId, long groupId, long resourcePrimKey, String dirName,
			String shortFileName, InputStream inputStream)
		throws PortalException, SystemException {

		if (resourcePrimKey <= 0) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.ADD_ARTICLE);
		}
		else {
			ArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		articleLocalService.addAttachment(
			companyId, dirName, shortFileName, inputStream);
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.DELETE);

		articleLocalService.deleteArticle(resourcePrimKey);
	}

	public void deleteArticles(long groupId, long[] resourcePrimKeys)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.DELETE_ARTICLES);

		articleLocalService.deleteArticles(resourcePrimKeys);
	}

	public void deleteAttachment(
			long companyId, long groupId, long resourcePrimKey, String fileName)
		throws PortalException, SystemException {

		if (resourcePrimKey <= 0) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.ADD_ARTICLE);
		}
		else {
			ArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		articleLocalService.deleteAttachment(companyId, fileName);
	}

	public Article getArticle(long resourcePrimKey, int version)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return articleLocalService.getArticle(resourcePrimKey, version);
	}

	public String getArticleRSS(
			long resourcePrimKey, int status, int rssDelta,
			String rssDisplayStyle, String rssFormat, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Article article = articleLocalService.getLatestArticle(
			resourcePrimKey, status);

		String name = HtmlUtil.escape(article.getTitle());
		String description = HtmlUtil.escape(article.getTitle());

		String feedURL = KnowledgeBaseUtil.getArticleURL(
			themeDisplay.getPlid(), resourcePrimKey,
			themeDisplay.getPortalURL(), false);

		List<Article> articles = getArticles(
			article.getGroupId(), resourcePrimKey, status,
			new ArticleModifiedDateComparator());

		return exportToRSS(
			rssDisplayStyle, rssFormat, name, description, feedURL,
			ListUtil.subList(articles, 0, rssDelta), themeDisplay);
	}

	public List<Article> getArticles(
			long groupId, long resourcePrimKey, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterFindByG_R_L(
				groupId, resourcePrimKey, ArticleConstants.LATEST_VERSION,
				start, end, orderByComparator);
		}

		return articlePersistence.filterFindByG_R_L_S(
			groupId, new long[] {resourcePrimKey}, ArticleConstants.LATEST_ANY,
			status, start, end, orderByComparator);
	}

	public List<Article> getArticles(
			long groupId, long resourcePrimKey, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<Article> articles = getArticles(
			groupId, new long[] {resourcePrimKey}, status, null);

		articles = ListUtil.copy(articles);

		Long[][] params = new Long[][] {new Long[] {resourcePrimKey}};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<Article> curArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curArticles = articlePersistence.filterFindByG_P_L(
					groupId, ArrayUtil.toArray(params[1]),
					new int[] {ArticleConstants.LATEST_VERSION});
			}
			else {
				curArticles = articlePersistence.filterFindByG_P_L_S(
					groupId, ArrayUtil.toArray(params[1]),
					ArticleConstants.LATEST_ANY, status);
			}

			articles.addAll(curArticles);

			long[] resourcePrimKeys = StringUtil.split(
				ListUtil.toString(curArticles, "resourcePrimKey"), 0L);

			params[0] = ArrayUtil.append(
				params[0], ArrayUtil.toArray(resourcePrimKeys));
		}

		if (orderByComparator != null) {
			articles = ListUtil.sort(articles, orderByComparator);
		}

		return new UnmodifiableList<Article>(articles);
	}

	public List<Article> getArticles(
			long groupId, long[] resourcePrimKeys, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<Article> articles = new ArrayList<Article>();

		Long[][] params = new Long[][] {ArrayUtil.toArray(resourcePrimKeys)};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<Article> curArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curArticles = articlePersistence.filterFindByG_R_L(
					groupId, ArrayUtil.toArray(params[1]),
					new int[] {ArticleConstants.LATEST_VERSION});
			}
			else {
				curArticles = articlePersistence.filterFindByG_R_L_S(
					groupId, ArrayUtil.toArray(params[1]),
					ArticleConstants.LATEST_ANY, status);
			}

			articles.addAll(curArticles);
		}

		if (orderByComparator != null) {
			articles = ListUtil.sort(articles, orderByComparator);
		}
		else {
			articles = KnowledgeBaseUtil.sort(resourcePrimKeys, articles);
		}

		return new UnmodifiableList<Article>(articles);
	}

	public int getArticlesCount(long groupId, long resourcePrimKey, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterCountByG_R_L(
				groupId, resourcePrimKey, ArticleConstants.LATEST_VERSION);
		}

		return articlePersistence.filterCountByG_R_L_S(
			groupId, new long[] {resourcePrimKey}, ArticleConstants.LATEST_ANY,
			status);
	}

	public ArticleSearchDisplay getArticleSearchDisplay(
			long groupId, String title, String content, int status,
			Date startDate, Date endDate, boolean andOperator,
			int[] curStartValues, int cur, int delta,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		// See LPS-9546

		int start = 0;

		if (curStartValues.length > (cur - SearchContainer.DEFAULT_CUR)) {
			start = curStartValues[cur - SearchContainer.DEFAULT_CUR];

			curStartValues = ArrayUtil.subset(
				curStartValues, 0, cur - SearchContainer.DEFAULT_CUR + 1);
		}
		else {
			cur = SearchContainer.DEFAULT_CUR;

			curStartValues = new int[] {0};
		}

		int end = start + _INTERVAL;

		List<Article> articles = new ArrayList<Article>();

		int curStartValue = 0;

		while (curStartValue == 0) {
			List<Article> curArticles = articleLocalService.search(
				groupId, title, content, status, startDate, endDate,
				andOperator, start, end, orderByComparator);

			if (curArticles.isEmpty()) {
				break;
			}

			for (int i = 0; i < curArticles.size(); i++) {
				Article curArticle = curArticles.get(i);

				if (!ArticlePermission.contains(
						getPermissionChecker(), curArticle, ActionKeys.VIEW)) {

					continue;
				}

				if (articles.size() == delta) {
					curStartValue = start + i;

					break;
				}

				articles.add(curArticle);
			}

			start = start + _INTERVAL;
			end = start + _INTERVAL;
		}

		int total = ((cur - 1) * delta) + articles.size();

		if (curStartValue > 0) {
			curStartValues = ArrayUtil.append(curStartValues, curStartValue);

			total = total + 1;
		}

		return new ArticleSearchDisplayImpl(articles, total, curStartValues);
	}

	public List<Article> getGroupArticles(
			long groupId, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterFindByG_L(
				groupId, new int[] {ArticleConstants.LATEST_VERSION}, start,
				end, orderByComparator);
		}

		return articlePersistence.filterFindByG_L_S(
			groupId, ArticleConstants.LATEST_ANY, status, start, end,
			orderByComparator);
	}

	public int getGroupArticlesCount(long groupId, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterCountByG_L(
				groupId, new int[] {ArticleConstants.LATEST_VERSION});
		}

		return articlePersistence.filterCountByG_L_S(
			groupId, ArticleConstants.LATEST_ANY, status);
	}

	public String getGroupArticlesRSS(
			int status, int rssDelta, String rssDisplayStyle, String rssFormat,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Group group = themeDisplay.getScopeGroup();

		String descriptiveName = HtmlUtil.escape(group.getDescriptiveName());

		String name = descriptiveName;
		String description = descriptiveName;

		String feedURL = PortalUtil.getLayoutFullURL(themeDisplay);

		List<Article> articles = getGroupArticles(
			group.getGroupId(), status, 0, rssDelta,
			new ArticleModifiedDateComparator());

		return exportToRSS(
			rssDisplayStyle, rssFormat, name, description, feedURL, articles,
			themeDisplay);
	}

	public Article getLatestArticle(long resourcePrimKey, int status)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return articleLocalService.getLatestArticle(resourcePrimKey, status);
	}

	public List<Article> getSiblingArticles(
			long groupId, long parentResourcePrimKey, int status, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterFindByG_P_L(
				groupId, parentResourcePrimKey, ArticleConstants.LATEST_VERSION,
				start, end, orderByComparator);
		}

		return articlePersistence.filterFindByG_P_L_S(
			groupId, new long[] {parentResourcePrimKey},
			ArticleConstants.LATEST_ANY, status, start, end, orderByComparator);
	}

	public int getSiblingArticlesCount(
			long groupId, long parentResourcePrimKey, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterCountByG_P_L(
				groupId, parentResourcePrimKey,
				ArticleConstants.LATEST_VERSION);
		}

		return articlePersistence.filterCountByG_P_L_S(
			groupId, new long[] {parentResourcePrimKey},
			ArticleConstants.LATEST_ANY, status);
	}

	public Article moveArticle(
			long resourcePrimKey, long parentResourcePrimKey, double priority)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.MOVE);

		return articleLocalService.moveArticle(
			getUserId(), resourcePrimKey, parentResourcePrimKey, priority);
	}

	public void subscribeArticle(long groupId, long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		articleLocalService.subscribeArticle(
			getUserId(), groupId, resourcePrimKey);
	}

	public void subscribeGroupArticles(long groupId, String portletId)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}

		articleLocalService.subscribeGroupArticles(getUserId(), groupId);
	}

	public void unsubscribeArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		articleLocalService.unsubscribeArticle(getUserId(), resourcePrimKey);
	}

	public void unsubscribeGroupArticles(long groupId, String portletId)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}

		articleLocalService.unsubscribeGroupArticles(getUserId(), groupId);
	}

	public Article updateArticle(
			long resourcePrimKey, String title, String content,
			String description, String dirName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		return articleLocalService.updateArticle(
			getUserId(), resourcePrimKey, title, content, description, dirName,
			serviceContext);
	}

	public String updateAttachments(
			long companyId, long groupId, long resourcePrimKey, String dirName)
		throws PortalException, SystemException {

		if (resourcePrimKey <= 0) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.ADD_ARTICLE);
		}
		else {
			ArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		return articleLocalService.updateAttachments(
			companyId, resourcePrimKey, dirName);
	}

	public void updatePriorities(
			long groupId, Map<Long, Double> resourcePrimKeyToPriorityMap)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId,
			ActionKeys.UPDATE_ARTICLES_PRIORITIES);

		articleLocalService.updatePriorities(resourcePrimKeyToPriorityMap);
	}

	protected String exportToRSS(
			String rssDisplayStyle, String rssFormat, String name,
			String description, String feedURL, List<Article> articles,
			ThemeDisplay themeDisplay)
		throws SystemException {

		List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();

		for (Article article : articles) {
			String value = null;

			if (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT)) {
				value = HtmlUtil.extractText(article.getDescription());

				if (Validator.isNull(value)) {
					value = StringUtil.shorten(
						HtmlUtil.extractText(article.getContent()), 200);
				}
			}
			else if (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE)) {
				value = StringPool.BLANK;
			}
			else {
				value = StringUtil.replace(
					article.getContent(),
					new String[] {
						"href=\"/",
						"src=\"/"
					},
					new String[] {
						"href=\"" + themeDisplay.getURLPortal() + "/",
						"src=\"" + themeDisplay.getURLPortal() + "/"
					});
			}

			String author = null;

			String userName = PortalUtil.getUserName(
				article.getUserId(), article.getUserName());

			author = HtmlUtil.escape(userName);

			String link = KnowledgeBaseUtil.getArticleURL(
				themeDisplay.getPlid(), article.getResourcePrimKey(),
				themeDisplay.getPortalURL(), false);

			SyndContent syndContent = new SyndContentImpl();

			syndContent.setType(RSSUtil.DEFAULT_ENTRY_TYPE);
			syndContent.setValue(value);

			SyndEntry syndEntry = new SyndEntryImpl();

			syndEntry.setAuthor(author);
			syndEntry.setDescription(syndContent);
			syndEntry.setLink(link);
			syndEntry.setPublishedDate(article.getCreateDate());
			syndEntry.setTitle(article.getTitle());
			syndEntry.setUpdatedDate(article.getModifiedDate());
			syndEntry.setUri(syndEntry.getLink());

			syndEntries.add(syndEntry);
		}

		String feedType = RSSUtil.getFeedType(
			RSSUtil.getFormatType(rssFormat),
			RSSUtil.getFormatVersion(rssFormat));

		SyndFeed syndFeed = new SyndFeedImpl();

		syndFeed.setDescription(description);
		syndFeed.setEntries(syndEntries);
		syndFeed.setFeedType(feedType);
		syndFeed.setLink(feedURL);
		syndFeed.setTitle(name);

		try {
			return RSSUtil.export(syndFeed);
		}
		catch (FeedException fe) {
			throw new SystemException(fe);
		}
	}

	private static final int _INTERVAL = 200;

}