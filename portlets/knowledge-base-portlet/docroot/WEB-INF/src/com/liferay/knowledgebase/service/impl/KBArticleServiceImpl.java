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

import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBArticleSearchDisplay;
import com.liferay.knowledgebase.model.impl.KBArticleSearchDisplayImpl;
import com.liferay.knowledgebase.service.base.KBArticleServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.DisplayPermission;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.KBArticleModifiedDateComparator;
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
public class KBArticleServiceImpl extends KBArticleServiceBaseImpl {

	public void addAttachment(
			String portletId, long resourcePrimKey, String dirName,
			String shortFileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if ((resourcePrimKey <= 0) &&
			(portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN))) {

			AdminPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_ARTICLE);
		}
		else if ((resourcePrimKey <= 0) &&
				 (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY))) {

			DisplayPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_ARTICLE);
		}
		else {
			KBArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		kbArticleLocalService.addAttachment(
			dirName, shortFileName, inputStream, serviceContext);
	}

	public KBArticle addKBArticle(
			String portletId, long parentResourcePrimKey, String title,
			String content, String description, String[] sections,
			String dirName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_ARTICLE);
		}
		else if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			DisplayPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_ARTICLE);
		}

		return kbArticleLocalService.addKBArticle(
			getUserId(), parentResourcePrimKey, title, content, description,
			sections, dirName, serviceContext);
	}

	public void deleteAttachment(
			long companyId, long groupId, String portletId,
			long resourcePrimKey, String fileName)
		throws PortalException, SystemException {

		if ((resourcePrimKey <= 0) &&
			(portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN))) {

			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.ADD_KB_ARTICLE);
		}
		else if ((resourcePrimKey <= 0) &&
				 (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY))) {

			DisplayPermission.check(
				getPermissionChecker(), groupId, ActionKeys.ADD_KB_ARTICLE);
		}
		else {
			KBArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		kbArticleLocalService.deleteAttachment(companyId, fileName);
	}

	public void deleteKBArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.DELETE);

		kbArticleLocalService.deleteKBArticle(resourcePrimKey);
	}

	public void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.DELETE_KB_ARTICLES);

		kbArticleLocalService.deleteKBArticles(resourcePrimKeys);
	}

	public List<KBArticle> getGroupKBArticles(
			long groupId, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterFindByG_L(
				groupId, true, start, end, orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.filterFindByG_M(
				groupId, true, start, end, orderByComparator);
		}

		return kbArticlePersistence.filterFindByG_S(
			groupId, status, start, end, orderByComparator);
	}

	public int getGroupKBArticlesCount(long groupId, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterCountByG_L(groupId, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.filterCountByG_M(groupId, true);
		}

		return kbArticlePersistence.filterCountByG_S(groupId, status);
	}

	public String getGroupKBArticlesRSS(
			int status, int rssDelta, String rssDisplayStyle, String rssFormat,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Group group = themeDisplay.getScopeGroup();

		String descriptiveName = HtmlUtil.escape(group.getDescriptiveName());

		String name = descriptiveName;
		String description = descriptiveName;

		String feedURL = PortalUtil.getLayoutFullURL(themeDisplay);

		List<KBArticle> kbArticles = getGroupKBArticles(
			group.getGroupId(), status, 0, rssDelta,
			new KBArticleModifiedDateComparator());

		return exportToRSS(
			rssDisplayStyle, rssFormat, name, description, feedURL, kbArticles,
			themeDisplay);
	}

	public KBArticle getKBArticle(long resourcePrimKey, int version)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return kbArticleLocalService.getKBArticle(resourcePrimKey, version);
	}

	public List<KBArticle> getKBArticleAndAllDescendants(
			long groupId, long resourcePrimKey, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<KBArticle> kbArticles = getKBArticles(
			groupId, new long[] {resourcePrimKey}, status, null);

		kbArticles = ListUtil.copy(kbArticles);

		Long[][] params = new Long[][] {new Long[] {resourcePrimKey}};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<KBArticle> curKBArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curKBArticles = kbArticlePersistence.filterFindByG_P_L(
					groupId, ArrayUtil.toArray(params[1]), true);
			}
			else if (status == WorkflowConstants.STATUS_APPROVED) {
				curKBArticles = kbArticlePersistence.filterFindByG_P_M(
					groupId, ArrayUtil.toArray(params[1]), true);
			}
			else {
				curKBArticles = kbArticlePersistence.filterFindByG_P_S(
					groupId, ArrayUtil.toArray(params[1]), status);
			}

			kbArticles.addAll(curKBArticles);

			long[] resourcePrimKeys = StringUtil.split(
				ListUtil.toString(curKBArticles, "resourcePrimKey"), 0L);

			params[0] = ArrayUtil.append(
				params[0], ArrayUtil.toArray(resourcePrimKeys));
		}

		if (orderByComparator != null) {
			kbArticles = ListUtil.sort(kbArticles, orderByComparator);
		}

		return new UnmodifiableList<KBArticle>(kbArticles);
	}

	public String getKBArticleRSS(
			long resourcePrimKey, int status, int rssDelta,
			String rssDisplayStyle, String rssFormat, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		KBArticle kbArticle = kbArticleLocalService.getLatestKBArticle(
			resourcePrimKey, status);

		String name = HtmlUtil.escape(kbArticle.getTitle());
		String description = HtmlUtil.escape(kbArticle.getTitle());

		String feedURL = KnowledgeBaseUtil.getKBArticleURL(
			themeDisplay.getPlid(), resourcePrimKey,
			status, themeDisplay.getPortalURL(), false);

		List<KBArticle> kbArticles = getKBArticleAndAllDescendants(
			kbArticle.getGroupId(), resourcePrimKey, status,
			new KBArticleModifiedDateComparator());

		return exportToRSS(
			rssDisplayStyle, rssFormat, name, description, feedURL,
			ListUtil.subList(kbArticles, 0, rssDelta), themeDisplay);
	}

	public KBArticleSearchDisplay getKBArticleSearchDisplay(
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

		List<KBArticle> kbArticles = new ArrayList<KBArticle>();

		int curStartValue = 0;

		while (curStartValue == 0) {
			List<KBArticle> curKBArticles = kbArticleLocalService.search(
				groupId, title, content, status, startDate, endDate,
				andOperator, start, end, orderByComparator);

			if (curKBArticles.isEmpty()) {
				break;
			}

			for (int i = 0; i < curKBArticles.size(); i++) {
				KBArticle curKBArticle = curKBArticles.get(i);

				if (!KBArticlePermission.contains(
						getPermissionChecker(), curKBArticle,
						ActionKeys.VIEW)) {

					continue;
				}

				if (kbArticles.size() == delta) {
					curStartValue = start + i;

					break;
				}

				kbArticles.add(curKBArticle);
			}

			start = start + _INTERVAL;
			end = start + _INTERVAL;
		}

		int total = ((cur - 1) * delta) + kbArticles.size();

		if (curStartValue > 0) {
			curStartValues = ArrayUtil.append(curStartValues, curStartValue);

			total = total + 1;
		}

		return new KBArticleSearchDisplayImpl(
			kbArticles, total, curStartValues);
	}

	public List<KBArticle> getKBArticleVersions(
			long groupId, long resourcePrimKey, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterFindByR_G(
				resourcePrimKey, groupId, start, end, orderByComparator);
		}

		return kbArticlePersistence.filterFindByR_G_S(
			resourcePrimKey, groupId, status, start, end, orderByComparator);
	}

	public int getKBArticleVersionsCount(
			long groupId, long resourcePrimKey, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterCountByR_G(
				resourcePrimKey, groupId);
		}

		return kbArticlePersistence.filterCountByR_G_S(
			resourcePrimKey, groupId, status);
	}

	public List<KBArticle> getKBArticles(
			long groupId, long[] resourcePrimKeys, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<KBArticle> kbArticles = new ArrayList<KBArticle>();

		Long[][] params = new Long[][] {ArrayUtil.toArray(resourcePrimKeys)};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<KBArticle> curKBArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curKBArticles = kbArticlePersistence.filterFindByR_G_L(
					ArrayUtil.toArray(params[1]), groupId, true);
			}
			else if (status == WorkflowConstants.STATUS_APPROVED) {
				curKBArticles = kbArticlePersistence.filterFindByR_G_M(
					ArrayUtil.toArray(params[1]), groupId, true);
			}
			else {
				curKBArticles = kbArticlePersistence.filterFindByR_G_S(
					ArrayUtil.toArray(params[1]), groupId, status);
			}

			kbArticles.addAll(curKBArticles);
		}

		if (orderByComparator != null) {
			kbArticles = ListUtil.sort(kbArticles, orderByComparator);
		}
		else {
			kbArticles = KnowledgeBaseUtil.sort(resourcePrimKeys, kbArticles);
		}

		return new UnmodifiableList<KBArticle>(kbArticles);
	}

	public KBArticle getLatestKBArticle(long resourcePrimKey, int status)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.VIEW);

		return kbArticleLocalService.getLatestKBArticle(
			resourcePrimKey, status);
	}

	public List<KBArticle> getSectionsKBArticles(
			long groupId, String[] sections, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] array = AdminUtil.escapeSections(sections);

		for (int i = 0; i < array.length; i++) {
			array[i] = StringUtil.quote(array[i], StringPool.PERCENT);
		}

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterFindByG_P_S_L(
				groupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				array, true, start, end, orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.filterFindByG_P_S_M(
				groupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				array, true, start, end, orderByComparator);
		}

		return kbArticlePersistence.filterFindByG_P_S_S(
			groupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, array,
			status, start, end, orderByComparator);
	}

	public int getSectionsKBArticlesCount(
			long groupId, String[] sections, int status)
		throws SystemException {

		String[] array = AdminUtil.escapeSections(sections);

		for (int i = 0; i < array.length; i++) {
			array[i] = StringUtil.quote(array[i], StringPool.PERCENT);
		}

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterCountByG_P_S_L(
				groupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				array, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.filterCountByG_P_S_M(
				groupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				array, true);
		}

		return kbArticlePersistence.filterCountByG_P_S_S(
			groupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, array,
			status);
	}

	public List<KBArticle> getSiblingKBArticles(
			long groupId, long parentResourcePrimKey, int status, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterFindByG_P_L(
				groupId, parentResourcePrimKey, true, start, end,
				orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.filterFindByG_P_M(
				groupId, parentResourcePrimKey, true, start, end,
				orderByComparator);
		}

		return kbArticlePersistence.filterFindByG_P_S(
			groupId, parentResourcePrimKey, status, start, end,
			orderByComparator);
	}

	public int getSiblingKBArticlesCount(
			long groupId, long parentResourcePrimKey, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.filterCountByG_P_L(
				groupId, parentResourcePrimKey, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.filterCountByG_P_M(
				groupId, parentResourcePrimKey, true);
		}

		return kbArticlePersistence.filterCountByG_P_S(
			groupId, parentResourcePrimKey, status);
	}

	public void moveKBArticle(
			long resourcePrimKey, long parentResourcePrimKey, double priority)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey,
			ActionKeys.MOVE_KB_ARTICLE);

		kbArticleLocalService.moveKBArticle(
			getUserId(), resourcePrimKey, parentResourcePrimKey, priority);
	}

	public void subscribeGroupKBArticles(long groupId, String portletId)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}
		else if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			DisplayPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}

		kbArticleLocalService.subscribeGroupKBArticles(getUserId(), groupId);
	}

	public void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		kbArticleLocalService.subscribeKBArticle(
			getUserId(), groupId, resourcePrimKey);
	}

	public void unsubscribeGroupKBArticles(long groupId, String portletId)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}
		else if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			DisplayPermission.check(
				getPermissionChecker(), groupId, ActionKeys.SUBSCRIBE);
		}

		kbArticleLocalService.unsubscribeGroupKBArticles(getUserId(), groupId);
	}

	public void unsubscribeKBArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.SUBSCRIBE);

		kbArticleLocalService.unsubscribeKBArticle(
			getUserId(), resourcePrimKey);
	}

	public String updateAttachments(
			String portletId, long resourcePrimKey, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if ((resourcePrimKey <= 0) &&
			(portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN))) {

			AdminPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_ARTICLE);
		}
		else if ((resourcePrimKey <= 0) &&
				 (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY))) {

			DisplayPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_ARTICLE);
		}
		else {
			KBArticlePermission.check(
				getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);
		}

		return kbArticleLocalService.updateAttachments(
			resourcePrimKey, dirName, serviceContext);
	}

	public KBArticle updateKBArticle(
			long resourcePrimKey, String title, String content,
			String description, String[] sections, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KBArticlePermission.check(
			getPermissionChecker(), resourcePrimKey, ActionKeys.UPDATE);

		return kbArticleLocalService.updateKBArticle(
			getUserId(), resourcePrimKey, title, content, description, sections,
			dirName, serviceContext);
	}

	public void updateKBArticlesPriorities(
			long groupId, Map<Long, Double> resourcePrimKeyToPriorityMap)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId,
			ActionKeys.UPDATE_KB_ARTICLES_PRIORITIES);

		kbArticleLocalService.updateKBArticlesPriorities(
			resourcePrimKeyToPriorityMap);
	}

	protected String exportToRSS(
			String rssDisplayStyle, String rssFormat, String name,
			String description, String feedURL, List<KBArticle> kbArticles,
			ThemeDisplay themeDisplay)
		throws SystemException {

		List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();

		for (KBArticle kbArticle : kbArticles) {
			String value = null;

			if (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT)) {
				value = HtmlUtil.extractText(kbArticle.getDescription());

				if (Validator.isNull(value)) {
					value = StringUtil.shorten(
						HtmlUtil.extractText(kbArticle.getContent()), 200);
				}
			}
			else if (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE)) {
				value = StringPool.BLANK;
			}
			else {
				value = StringUtil.replace(
					kbArticle.getContent(),
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
				kbArticle.getUserId(), kbArticle.getUserName());

			author = HtmlUtil.escape(userName);

			String link = KnowledgeBaseUtil.getKBArticleURL(
				themeDisplay.getPlid(), kbArticle.getResourcePrimKey(),
				kbArticle.getStatus(), themeDisplay.getPortalURL(), false);

			SyndContent syndContent = new SyndContentImpl();

			syndContent.setType(RSSUtil.ENTRY_TYPE_DEFAULT);
			syndContent.setValue(value);

			SyndEntry syndEntry = new SyndEntryImpl();

			syndEntry.setAuthor(author);
			syndEntry.setDescription(syndContent);
			syndEntry.setLink(link);
			syndEntry.setPublishedDate(kbArticle.getCreateDate());
			syndEntry.setTitle(kbArticle.getTitle());
			syndEntry.setUpdatedDate(kbArticle.getModifiedDate());
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