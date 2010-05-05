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
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.security.permission.ActionKeys;
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

import java.util.ArrayList;
import java.util.Collections;
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
			String shortFileName, byte[] bytes)
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
			companyId, dirName, shortFileName, bytes);
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

	public String getArticlesRSS(
			long resourcePrimKey, int max, String type, double version,
			String displayStyle, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Article article = articleLocalService.getLatestArticle(resourcePrimKey);

		String title = HtmlUtil.escape(article.getTitle());

		String name = title;
		String description = title;

		String layoutFullURL = PortalUtil.getLayoutFullURL(themeDisplay);

		String feedURL = layoutFullURL;
		String entryURL = layoutFullURL;

		String namespace = PortalUtil.getPortletNamespace(
			"1_WAR_knowledgebaseportlet");

		feedURL = HttpUtil.setParameter(
			feedURL, "p_p_id", "1_WAR_knowledgebaseportlet");
		feedURL = HttpUtil.setParameter(
			feedURL, namespace + "jspPage", "/admin/view_article.jsp");
		feedURL = HttpUtil.setParameter(
			feedURL, namespace + "resourcePrimKey",
			article.getResourcePrimKey());

		boolean isDynamicPortlet = true;

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		if (layoutTypePortlet.hasPortletId("1_WAR_knowledgebaseportlet")) {
			long scopeGroupId = PortalUtil.getScopeGroupId(
				themeDisplay.getLayout(), "1_WAR_knowledgebaseportlet");

			if (article.getGroupId() == scopeGroupId) {
				isDynamicPortlet = false;
			}
		}

		if (isDynamicPortlet) {
			feedURL = null;
			entryURL = null;
		}

		return exportToRSS(
			name, description, type, version, displayStyle, feedURL, entryURL,
			filterArticles(article, max), themeDisplay);
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

	public String getGroupArticlesRSS(
			int max, String type, double version, String displayStyle,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Group group = themeDisplay.getScopeGroup();

		String descriptiveName = HtmlUtil.escape(group.getDescriptiveName());

		String name = descriptiveName;
		String description = descriptiveName;

		String layoutFullURL = PortalUtil.getLayoutFullURL(themeDisplay);

		String feedURL = layoutFullURL;
		String entryURL = layoutFullURL;

		boolean isDynamicPortlet = true;

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		if (layoutTypePortlet.hasPortletId("1_WAR_knowledgebaseportlet")) {
			long scopeGroupId = PortalUtil.getScopeGroupId(
				themeDisplay.getLayout(), "1_WAR_knowledgebaseportlet");

			if (group.getGroupId() == scopeGroupId) {
				isDynamicPortlet = false;
			}
		}

		if (isDynamicPortlet) {
			feedURL = null;
			entryURL = null;
		}

		return exportToRSS(
			name, description, type, version, displayStyle, feedURL, entryURL,
			filterGroupArticles(group, max), themeDisplay);
	}

	public Article getLatestArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		ArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return articleLocalService.getLatestArticle(resourcePrimKey);
	}

	public void subscribe(long groupId, long resourcePrimKey)
		throws PortalException, SystemException {

		if (resourcePrimKey <= 0) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}
		else {
			ArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);
		}

		articleLocalService.subscribe(groupId, getUserId(), resourcePrimKey);
	}

	public void unsubscribe(long groupId, long resourcePrimKey)
		throws PortalException, SystemException {

		if (resourcePrimKey <= 0) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}
		else {
			ArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);
		}

		articleLocalService.unsubscribe(groupId, getUserId(), resourcePrimKey);
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
			long companyId, long groupId, long resourcePrimKey)
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
			companyId, resourcePrimKey);
	}

	protected String exportToRSS(
			String name, String description, String type, double version,
			String displayStyle, String feedURL, String entryURL,
			List<Article> articles, ThemeDisplay themeDisplay)
		throws SystemException {

		List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();

		for (Article article : articles) {
			String value = null;

			if (displayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT)) {
				value = StringUtil.shorten(
					HtmlUtil.extractText(article.getContent()), 200);
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
					}
				);
			}

			String author = null;

			String userName = PortalUtil.getUserName(
				article.getUserId(), article.getUserName());

			author = HtmlUtil.escape(userName);

			String link = entryURL;

			if (link != null) {
				String namespace = PortalUtil.getPortletNamespace(
					"1_WAR_knowledgebaseportlet");

				link = HttpUtil.setParameter(
					link, "p_p_id", "1_WAR_knowledgebaseportlet");
				link = HttpUtil.setParameter(
					link, namespace + "jspPage", "/kb/view_article.jsp");
				link = HttpUtil.setParameter(
					link, namespace + "resourcePrimKey",
					article.getResourcePrimKey());
			}

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

	protected List<Article> filterArticles(Article article, int max)
		throws PortalException, SystemException {

		List<Article> articles = ListUtil.toList(new Article[] {article});

		if (!ArticlePermission.contains(
				getPermissionChecker(), article, ActionKeys.VIEW)) {

			articles.remove(article);
		}

		int index = -1;

		while ((index = index + 1) < articles.size()) {
			Article curArticle = articles.get(index);

			List<Article> articles2 = getGroupArticles(
				curArticle.getGroupId(), curArticle.getResourcePrimKey(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			articles.addAll(articles2);
		}

		Collections.sort(articles, new ArticleModifiedDateComparator());

		return ListUtil.subList(articles, 0, max);
	}

	protected List<Article> filterGroupArticles(Group group, int max)
		throws PortalException, SystemException {

		List<Article> articles = new ArrayList<Article>();

		int lastIntervalStart = 0;
		boolean listNotExhausted = true;

		while ((articles.size() < max) && listNotExhausted) {
			List<Article> articleList = articleLocalService.getGroupArticles(
				group.getGroupId(), lastIntervalStart, lastIntervalStart + max,
				new ArticleModifiedDateComparator());

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