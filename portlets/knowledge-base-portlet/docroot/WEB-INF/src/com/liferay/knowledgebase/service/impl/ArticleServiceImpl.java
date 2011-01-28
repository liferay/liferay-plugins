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
import com.liferay.knowledgebase.service.base.ArticleServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
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

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleServiceImpl extends ArticleServiceBaseImpl {

	public Article addArticle(
			long parentResourcePrimKey, String title, String content,
			String description, long priority, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ARTICLE);

		return articleLocalService.addArticle(
			getUserId(), parentResourcePrimKey, title, content, description,
			priority, dirName, serviceContext);
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
			long resourcePrimKey, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		if (!ArticlePermission.contains(
				getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW)) {

			return Collections.emptyList();
		}

		return articleLocalService.getArticles(
			resourcePrimKey, status, start, end, orderByComparator);
	}

	public List<Article> getArticles(
			long groupId, long[] resourcePrimKeys, int status,
			long[] viewableParentResourcePrimKeys, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if ((resourcePrimKeys == null) || (resourcePrimKeys.length == 0)) {
			return Collections.emptyList();
		}

		viewableParentResourcePrimKeys = getViewableParentResourcePrimKeys(
			groupId, status, viewableParentResourcePrimKeys);

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterFindByR_G_P_L(
				resourcePrimKeys, groupId, viewableParentResourcePrimKeys,
				new int[] {ArticleConstants.LATEST_VERSION}, start, end,
				orderByComparator);
		}

		return articlePersistence.filterFindByR_G_P_L_S(
			resourcePrimKeys, groupId, viewableParentResourcePrimKeys,
			ArticleConstants.LATEST_ANY, status, start, end, orderByComparator);
	}

	public int getArticlesCount(long resourcePrimKey, int status)
		throws PortalException, SystemException {

		if (!ArticlePermission.contains(
				getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW)) {

			return 0;
		}

		return articleLocalService.getArticlesCount(resourcePrimKey, status);
	}

	public int getArticlesCount(
			long groupId, long[] resourcePrimKeys, int status,
			long[] viewableParentResourcePrimKeys)
		throws SystemException {

		if ((resourcePrimKeys == null) || (resourcePrimKeys.length == 0)) {
			return 0;
		}

		viewableParentResourcePrimKeys = getViewableParentResourcePrimKeys(
			groupId, status, viewableParentResourcePrimKeys);

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterCountByR_G_P_L(
				resourcePrimKeys, groupId, viewableParentResourcePrimKeys,
				new int[] {ArticleConstants.LATEST_VERSION});
		}

		return articlePersistence.filterCountByR_G_P_L_S(
			resourcePrimKeys, groupId, viewableParentResourcePrimKeys,
			ArticleConstants.LATEST_ANY, status);
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
			resourcePrimKey, themeDisplay.getPlid(),
			themeDisplay.getPortalURL());

		List<Article> articles = Collections.emptyList();

		if (ArticlePermission.contains(
				getPermissionChecker(), article, ActionKeys.VIEW)) {

			long[] resourcePrimKeys = getViewableResourcePrimKeys(
				article.getGroupId(), resourcePrimKey, status);

			articles = getArticles(
				article.getGroupId(), resourcePrimKeys, status, null, 0,
				rssDelta, new ArticleModifiedDateComparator());
		}

		return exportToRSS(
			rssDisplayStyle, rssFormat, name, description, feedURL, articles,
			themeDisplay);
	}

	public List<Article> getGroupArticles(
			long groupId, int status, long[] viewableParentResourcePrimKeys,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		viewableParentResourcePrimKeys = getViewableParentResourcePrimKeys(
			groupId, status, viewableParentResourcePrimKeys);

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterFindByG_P_L(
				groupId, viewableParentResourcePrimKeys,
				new int[] {ArticleConstants.LATEST_VERSION}, start, end,
				orderByComparator);
		}

		return articlePersistence.filterFindByG_P_L_S(
			groupId, viewableParentResourcePrimKeys,
			ArticleConstants.LATEST_ANY, status, start, end, orderByComparator);
	}

	public int getGroupArticlesCount(
			long groupId, int status, long[] viewableParentResourcePrimKeys)
		throws SystemException {

		viewableParentResourcePrimKeys = getViewableParentResourcePrimKeys(
			groupId, status, viewableParentResourcePrimKeys);

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterCountByG_P_L(
				groupId, viewableParentResourcePrimKeys,
				new int[] {ArticleConstants.LATEST_VERSION});
		}

		return articlePersistence.filterCountByG_P_L_S(
			groupId, viewableParentResourcePrimKeys,
			ArticleConstants.LATEST_ANY, status);
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
			group.getGroupId(), status, null, 0, rssDelta,
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
		throws PortalException, SystemException {

		if (!ArticlePermission.contains(
				getPermissionChecker(), parentResourcePrimKey,
				ActionKeys.VIEW)) {

			return Collections.emptyList();
		}

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
		throws PortalException, SystemException {

		if (!ArticlePermission.contains(
				getPermissionChecker(), parentResourcePrimKey,
				ActionKeys.VIEW)) {

			return 0;
		}

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.filterCountByG_P_L(
				groupId, parentResourcePrimKey,
				ArticleConstants.LATEST_VERSION);
		}

		return articlePersistence.filterCountByG_P_L_S(
			groupId, new long[] {parentResourcePrimKey},
			ArticleConstants.LATEST_ANY, status);
	}

	public long[] getViewableParentResourcePrimKeys(long groupId, int status)
		throws SystemException {

		return getViewableResourcePrimKeys(
			groupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, status);
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
			long resourcePrimKey, long parentResourcePrimKey, String title,
			String content, String description, long priority, String dirName,
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
				article.getResourcePrimKey(), themeDisplay.getPlid(),
				themeDisplay.getPortalURL());

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

	protected long[] getViewableParentResourcePrimKeys(
			long groupId, int status, long[] viewableParentResourcePrimKeys)
		throws SystemException {

		if (ArrayUtil.contains(
				viewableParentResourcePrimKeys,
				ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY)) {

			return viewableParentResourcePrimKeys;
		}

		return getViewableParentResourcePrimKeys(groupId, status);
	}

	protected long[] getViewableResourcePrimKeys(
			long groupId, long parentResourcePrimKey, int status)
		throws SystemException {

		long[] viewableResourcePrimKeys = new long[] {parentResourcePrimKey};

		long[] parentResourcePrimKeys = new long[] {parentResourcePrimKey};

		while (parentResourcePrimKeys.length > 0) {
			List<Article> articles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				articles = articlePersistence.filterFindByG_P_L(
					groupId, parentResourcePrimKeys,
					new int[] {ArticleConstants.LATEST_VERSION});
			}
			else {
				articles = articlePersistence.filterFindByG_P_L_S(
					groupId, parentResourcePrimKeys,
					ArticleConstants.LATEST_ANY, status);
			}

			parentResourcePrimKeys = StringUtil.split(
				ListUtil.toString(articles, "resourcePrimKey"), 0L);

			viewableResourcePrimKeys = ArrayUtil.append(
				viewableResourcePrimKeys, parentResourcePrimKeys);
		}

		return viewableResourcePrimKeys;
	}

}