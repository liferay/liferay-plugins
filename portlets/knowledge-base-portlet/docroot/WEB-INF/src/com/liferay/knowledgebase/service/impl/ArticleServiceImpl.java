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
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.permission.PortletPermissionUtil;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <a href="ArticleServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleServiceImpl extends ArticleServiceBaseImpl {

	public Article addArticle(
			long parentResourcePrimKey, String title, String content,
			String description, int priority, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ARTICLE);

		return articleLocalService.addArticle(
			null, getUserId(), parentResourcePrimKey, title, content,
			description, priority, dirName, serviceContext);
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

	public List<Article> getArticles(
			long resourcePrimKey, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getArticles(
			resourcePrimKey, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public List<Article> getArticles(
			Map<String, Object> params, boolean allVersions, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getArticles(
			params, allVersions, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public int getArticlesCount(long resourcePrimKey) throws SystemException {
		return articleLocalService.getArticlesCount(resourcePrimKey);
	}

	public int getArticlesCount(Map<String, Object> params, boolean allVersions)
		throws SystemException {

		return articleLocalService.getArticlesCount(params, allVersions);
	}

	public String getArticlesRSS(
			String portletId, long resourcePrimKey, int max, String type,
			double version, String displayStyle, boolean isMaximized,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Article article = articleLocalService.getLatestArticle(resourcePrimKey);

		String title = HtmlUtil.escape(article.getTitle());

		String name = title;
		String description = title;

		String layoutFullURL = PortalUtil.getLayoutFullURL(themeDisplay);
		String feedURL = KnowledgeBaseUtil.getArticleURL(
			portletId, resourcePrimKey, layoutFullURL, isMaximized);

		List<Article> articles = filterArticles(article, max);

		return exportToRSS(
			portletId, name, description, type, version, displayStyle,
			isMaximized, layoutFullURL, feedURL, articles, themeDisplay);
	}

	public List<Article> getCompanyArticles(
			long companyId, boolean allVersions, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getCompanyArticles(
			companyId, allVersions, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public int getCompanyArticlesCount(long companyId, boolean allVersions)
		throws SystemException {

		return articleLocalService.getCompanyArticlesCount(
			companyId, allVersions);
	}

	public List<Article> getGroupArticles(
			long groupId, boolean allVersions, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Article> articles = articleLocalService.getGroupArticles(
			groupId, allVersions, start, end, orderByComparator);

		return filterArticles(articles);
	}

	public int getGroupArticlesCount(long groupId, boolean allVersions)
		throws SystemException {

		return articleLocalService.getGroupArticlesCount(groupId, allVersions);
	}

	public String getGroupArticlesRSS(
			String portletId, int max, String type, double version,
			String displayStyle, boolean isMaximized, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Group group = themeDisplay.getScopeGroup();

		String descriptiveName = HtmlUtil.escape(group.getDescriptiveName());

		String name = descriptiveName;
		String description = descriptiveName;

		String layoutFullURL = PortalUtil.getLayoutFullURL(themeDisplay);
		String feedURL = layoutFullURL;

		List<Article> articles = filterGroupArticles(group, max);

		return exportToRSS(
			portletId, name, description, type, version, displayStyle,
			isMaximized, layoutFullURL, feedURL, articles, themeDisplay);
	}

	public Article getLatestArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return articleLocalService.getLatestArticle(resourcePrimKey);
	}

	public void subscribe(long groupId)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);

		articleLocalService.subscribe(groupId, getUserId());
	}

	public void subscribeArticle(String portletId, long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		articleLocalService.subscribeArticle(
			portletId, getUserId(), resourcePrimKey);
	}

	public void unsubscribe(long groupId)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);

		articleLocalService.unsubscribe(groupId, getUserId());
	}

	public void unsubscribeArticle(long companyId, long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		articleLocalService.unsubscribeArticle(
			companyId, getUserId(), resourcePrimKey);
	}

	public Article updateArticle(
			long resourcePrimKey, long parentResourcePrimKey, String title,
			String content, String description, int priority, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		return articleLocalService.updateArticle(
			getUserId(), resourcePrimKey, parentResourcePrimKey, title, content,
			description, priority, dirName, serviceContext);
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

	protected String exportToRSS(
			String portletId, String name, String description, String type,
			double version, String displayStyle, boolean isMaximized,
			String layoutFullURL, String feedURL, List<Article> articles,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		if (!PortletPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), themeDisplay.getPlid(),
				portletId, ActionKeys.VIEW)) {

			articles = Collections.EMPTY_LIST;
		}

		List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();

		for (Article article : articles) {
			String value = null;

			if (displayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT)) {
				value = HtmlUtil.extractText(article.getDescription());

				if (Validator.isNull(value)) {
					value = StringUtil.shorten(
						HtmlUtil.extractText(article.getContent()), 200);
				}
			}
			else if (displayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE)) {
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
				portletId, article.getResourcePrimKey(), layoutFullURL,
				isMaximized);

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

		SyndFeed syndFeed = new SyndFeedImpl();

		syndFeed.setDescription(description);
		syndFeed.setEntries(syndEntries);
		syndFeed.setFeedType(RSSUtil.getFeedType(type, version));
		syndFeed.setLink(feedURL);
		syndFeed.setTitle(name);

		try {
			return RSSUtil.export(syndFeed);
		}
		catch (FeedException fe) {
			throw new SystemException(fe);
		}
	}

	protected List<Article> filterArticles(Article article, int max)
		throws PortalException, SystemException {

		List<Article> articles = new ArrayList<Article>();

		if (ArticlePermission.contains(
				getPermissionChecker(), article, ActionKeys.VIEW)) {

			articles.add(article);
		}

		int index = -1;

		while ((index = index + 1) < articles.size()) {
			Article curArticle = articles.get(index);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("groupId", curArticle.getGroupId());
			params.put(
				"parentResourcePrimKey", curArticle.getResourcePrimKey());

			List<Article> childArticles = getArticles(
				params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			articles.addAll(childArticles);
		}

		Collections.sort(articles, new ArticleModifiedDateComparator());

		return ListUtil.subList(articles, 0, max);
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

	protected List<Article> filterGroupArticles(Group group, int max)
		throws PortalException, SystemException {

		List<Article> articles = new ArrayList<Article>();

		int lastIntervalStart = 0;
		boolean listNotExhausted = true;

		while ((articles.size() < max) && listNotExhausted) {
			List<Article> articleList = articleLocalService.getGroupArticles(
				group.getGroupId(), false, lastIntervalStart,
				lastIntervalStart + max, new ArticleModifiedDateComparator());

			Iterator<Article> itr = articleList.iterator();

			lastIntervalStart += max;
			listNotExhausted = (articleList.size() == max);

			while (itr.hasNext() && (articles.size() < max)) {
				Article article = itr.next();

				if (ArticlePermission.contains(
						getPermissionChecker(), article, ActionKeys.VIEW)) {

					articles.add(article);
				}
			}
		}

		return articles;
	}

}