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

import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.comparator.ArticleCreateDateComparator;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.ArticleTitleComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.util.RSSUtil;
import com.liferay.util.UniqueList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.WindowState;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KnowledgeBaseUtil {

	public static String getArticleURL(
		long resourcePrimKey, long selPlid, String portalURL) {

		String articleURL =
			portalURL + PortalUtil.getPathMain() +
				"/portal/knowledge_base/find_article";

		articleURL = HttpUtil.setParameter(
			articleURL, "resourcePrimKey", resourcePrimKey);
		articleURL = HttpUtil.setParameter(articleURL, "selPlid", selPlid);

		return articleURL;
	}

	public static Map<String, Object> getPortletPreferencesArticlesMap(
			long groupId, String portletId, long assetCategoryId,
			String assetTagName, int start, int end,
			PortletPreferences preferences)
		throws PortalException, SystemException {

		if (!isValidPortletId(portletId)) {
			return Collections.emptyMap();
		}

		Map<String, String> preferencesMap = initPortletPreferencesMap(
			portletId, preferences);

		String selectionMethod = preferencesMap.get("selectionMethod");
		long[] resourcePrimKeys = StringUtil.split(
			preferencesMap.get("resourcePrimKeys"), 0L);
		boolean allArticles = GetterUtil.getBoolean(
			preferencesMap.get("allArticles"));
		String orderByColumn = preferencesMap.get("orderByColumn");
		boolean orderByAscending = GetterUtil.getBoolean(
			preferencesMap.get("orderByAscending"));

		long[] classPKs = getAssetEntriesClassPKs(
			groupId, assetCategoryId, assetTagName, preferencesMap);

		List<Article> articles = null;
		Integer count = null;

		if (selectionMethod.equals("group")) {
			OrderByComparator orderByComparator = getOrderByComparator(
				orderByColumn, orderByAscending);

			if (classPKs != null) {
				long[] viewableParentResourcePrimKeys = new long[] {
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY
				};

				if (allArticles) {
					viewableParentResourcePrimKeys =
						ArticleServiceUtil.getViewableParentResourcePrimKeys(
							groupId, WorkflowConstants.STATUS_APPROVED);
				}

				articles = ArticleServiceUtil.getArticles(
					groupId, classPKs, WorkflowConstants.STATUS_APPROVED,
					viewableParentResourcePrimKeys, start, end,
					orderByComparator);

				count = ArticleServiceUtil.getArticlesCount(
					groupId, classPKs, WorkflowConstants.STATUS_APPROVED,
					viewableParentResourcePrimKeys);
			}
			else if (allArticles) {
				long[] viewableParentResourcePrimKeys =
					ArticleServiceUtil.getViewableParentResourcePrimKeys(
						groupId, WorkflowConstants.STATUS_APPROVED);

				articles = ArticleServiceUtil.getGroupArticles(
					groupId, WorkflowConstants.STATUS_APPROVED,
					viewableParentResourcePrimKeys, start, end,
					orderByComparator);

				count = ArticleServiceUtil.getGroupArticlesCount(
					groupId, WorkflowConstants.STATUS_APPROVED,
					viewableParentResourcePrimKeys);
			}
			else {
				articles = ArticleServiceUtil.getSiblingArticles(
					groupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
					WorkflowConstants.STATUS_APPROVED, start, end,
					orderByComparator);

				count = ArticleServiceUtil.getSiblingArticlesCount(
					groupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
					WorkflowConstants.STATUS_APPROVED);
			}
		}

		if (selectionMethod.equals("articles")) {
			if (classPKs != null) {
				List<Long> list = new UniqueList<Long>();

				list.addAll(SetUtil.fromArray(resourcePrimKeys));
				list.retainAll(SetUtil.fromArray(classPKs));

				resourcePrimKeys = StringUtil.split(StringUtil.merge(list), 0L);
			}

			articles = ArticleServiceUtil.getArticles(
				groupId, resourcePrimKeys, WorkflowConstants.STATUS_APPROVED,
				null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			articles = sortPortletPreferencesArticles(
				resourcePrimKeys, articles);

			count = articles.size();

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				articles = ListUtil.subList(articles, start, end);
			}
		}

		Map<String, Object> articlesMap = new HashMap<String, Object>();

		articlesMap.put("articles", articles);
		articlesMap.put("count", count);

		return articlesMap;
	}

	public static boolean hasPortletPreferencesArticle(
			long plid, String portletId, long resourcePrimKey)
		throws PortalException, SystemException {

		if (!isValidPortletId(portletId)) {
			return false;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!ArticlePermission.contains(
				permissionChecker, resourcePrimKey, ActionKeys.VIEW)) {

			return false;
		}

		Article article = ArticleServiceUtil.getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		Map<String, String> preferencesMap = initPortletPreferencesMap(
			portletId, preferences);

		String selectionMethod = preferencesMap.get("selectionMethod");
		long[] resourcePrimKeys = StringUtil.split(
			preferencesMap.get("resourcePrimKeys"), 0L);
		boolean allArticles = GetterUtil.getBoolean(
			preferencesMap.get("allArticles"));
		String orderByColumn = preferencesMap.get("orderByColumn");
		boolean orderByAscending = GetterUtil.getBoolean(
			preferencesMap.get("orderByAscending"));

		long[] classPKs = getAssetEntriesClassPKs(
			article.getGroupId(), 0, null, preferencesMap);

		// Mimic behavior in KnowledgeBaseUtil#getPortletPreferencesArticlesMap

		List<Article> articles = null;

		if (selectionMethod.equals("group")) {
			OrderByComparator orderByComparator = getOrderByComparator(
				orderByColumn, orderByAscending);

			if (classPKs != null) {
				long[] viewableParentResourcePrimKeys = new long[] {
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY
				};

				if (allArticles) {
					viewableParentResourcePrimKeys = null;
				}

				articles = ArticleServiceUtil.getArticles(
					article.getGroupId(), classPKs,
					WorkflowConstants.STATUS_APPROVED,
					viewableParentResourcePrimKeys, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, orderByComparator);
			}
			else if (allArticles) {
				articles = ArticleServiceUtil.getGroupArticles(
					article.getGroupId(), WorkflowConstants.STATUS_APPROVED,
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					orderByComparator);
			}
			else {
				articles = ArticleServiceUtil.getSiblingArticles(
					article.getGroupId(),
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
					WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, orderByComparator);
			}
		}

		if (selectionMethod.equals("articles")) {
			if (classPKs != null) {
				List<Long> list = new UniqueList<Long>();

				list.addAll(SetUtil.fromArray(resourcePrimKeys));
				list.retainAll(SetUtil.fromArray(classPKs));

				resourcePrimKeys = StringUtil.split(StringUtil.merge(list), 0L);
			}

			articles = ArticleServiceUtil.getArticles(
				article.getGroupId(), resourcePrimKeys,
				WorkflowConstants.STATUS_APPROVED, null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);
			articles = sortPortletPreferencesArticles(
				resourcePrimKeys, articles);
		}

		// The Display portlet shows the first available article

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			articles = ListUtil.subList(articles, 0, 1);
		}

		// Users can navigate to the root article. See article_breadcrumbs.jsp.

		if (selectionMethod.equals("articles") || (classPKs != null) ||
			rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {

			List<Article> rootArticles = new UniqueList<Article>();

			for (Article curArticle : articles) {
				while (curArticle.getParentResourcePrimKey() !=
							ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

					curArticle = ArticleServiceUtil.getLatestArticle(
						curArticle.getParentResourcePrimKey(),
						WorkflowConstants.STATUS_APPROVED);
				}

				rootArticles.add(curArticle);
			}

			articles = rootArticles;
		}

		long[] selResourcePrimKeys = StringUtil.split(
			ListUtil.toString(articles, "resourcePrimKey"), 0L);

		while (resourcePrimKey !=
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			if (ArrayUtil.contains(selResourcePrimKeys, resourcePrimKey)) {
				return true;
			}

			Article curArticle = ArticleServiceUtil.getLatestArticle(
				resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

			resourcePrimKey = curArticle.getParentResourcePrimKey();
		}

		return false;
	}

	public static Map<String, String> initPortletPreferencesMap(
		String portletId, PortletPreferences preferences) {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			return AdminUtil.initPortletPreferencesMap(preferences);
		}

		if (!isValidPortletId(portletId)) {
			return Collections.emptyMap();
		}

		Map<String, Object> defaultPreferences = new HashMap<String, Object>();

		defaultPreferences.put("articlesTitle", StringPool.BLANK);
		defaultPreferences.put("articlesDelta", SearchContainer.DEFAULT_DELTA);
		defaultPreferences.put("articlesDisplayStyle", "title");
		defaultPreferences.put(
			"articleWindowState", WindowState.MAXIMIZED.toString());

		defaultPreferences.put("childArticlesDisplayStyle", "abstract");
		defaultPreferences.put("enableArticleAssetCategories", true);
		defaultPreferences.put("enableArticleAssetTags", true);
		defaultPreferences.put("enableArticleRatings", false);
		defaultPreferences.put("enableArticleComments", true);
		defaultPreferences.put("showArticleComments", true);

		defaultPreferences.put("selectionMethod", "group");
		defaultPreferences.put("resourcePrimKeys", new Long[0]);
		defaultPreferences.put("allArticles", true);
		defaultPreferences.put("orderByColumn", "modified-date");
		defaultPreferences.put("orderByAscending", false);
		defaultPreferences.put("assetEntryQueryContains", true);
		defaultPreferences.put("assetEntryQueryAndOperator", false);
		defaultPreferences.put("assetEntryQueryName", "asset-categories");
		defaultPreferences.put("assetCategoryIds", new Long[0]);
		defaultPreferences.put("assetTagNames", new String[0]);

		defaultPreferences.put("rssDelta", SearchContainer.DEFAULT_DELTA);
		defaultPreferences.put(
			"rssDisplayStyle", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
		defaultPreferences.put("rssFormat", "atom10");

		Map<String, String> preferencesMap = new HashMap<String, String>();

		for (Map.Entry<String, Object> entry : defaultPreferences.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value instanceof Object[]) {
				String[] values = ArrayUtil.toStringArray((Object[])value);

				value = StringUtil.merge(preferences.getValues(key, values));
			}
			else {
				value = preferences.getValue(key, String.valueOf(value));
			}

			preferencesMap.put(key, String.valueOf(value));
		}

		return Collections.unmodifiableMap(preferencesMap);
	}

	public static List<Article> sortPortletPreferencesArticles(
		long[] resourcePrimKeys, List<Article> articles) {

		long[] keys = StringUtil.split(
			ListUtil.toString(articles, "resourcePrimKey"), 0L);

		Map<Long, Article> map = new HashMap<Long, Article>();

		for (int i = 0; i < articles.size(); i++) {
			map.put(keys[i], articles.get(i));
		}

		List<Article> list = new ArrayList<Article>();

		for (long resourcePrimKey : resourcePrimKeys) {
			if (map.containsKey(resourcePrimKey)) {
				list.add(map.get(resourcePrimKey));
			}
		}

		return list;
	}

	protected static long[] getAssetEntriesClassPKs(
			long groupId, long assetCategoryId, String assetTagName,
			Map<String, String> preferencesMap)
		throws PortalException, SystemException {

		String selectionMethod = preferencesMap.get("selectionMethod");
		boolean assetEntryQueryContains = GetterUtil.getBoolean(
			preferencesMap.get("assetEntryQueryContains"));
		boolean assetEntryQueryAndOperator = GetterUtil.getBoolean(
			preferencesMap.get("assetEntryQueryAndOperator"));
		String assetEntryQueryName = preferencesMap.get("assetEntryQueryName");
		long[] assetCategoryIds = StringUtil.split(
			preferencesMap.get("assetCategoryIds"), 0L);
		String[] assetTagNames = StringUtil.split(
			preferencesMap.get("assetTagNames"));

		boolean assetCategories = false;
		boolean assetTags = false;

		if (selectionMethod.equals("group")) {
			assetCategories = assetEntryQueryName.equals("asset-categories");
			assetTags = assetEntryQueryName.equals("asset-tags");
		}

		long[] allCategoryIds = new long[0];
		long[] anyCategoryIds = new long[0];
		long[] notAllCategoryIds = new long[0];
		long[] notAnyCategoryIds = new long[0];

		if (assetCategories) {
			if (assetEntryQueryContains && assetEntryQueryAndOperator) {
				allCategoryIds = assetCategoryIds;
			}
			else if (assetEntryQueryContains && !assetEntryQueryAndOperator) {
				anyCategoryIds = assetCategoryIds;
			}
			else if (!assetEntryQueryContains && assetEntryQueryAndOperator) {
				notAllCategoryIds = assetCategoryIds;
			}
			else {
				notAnyCategoryIds = assetCategoryIds;
			}
		}

		long[] allTagIds = new long[0];
		long[] anyTagIds = new long[0];
		long[] notAllTagIds = new long[0];
		long[] notAnyTagIds = new long[0];

		if (assetTags) {
			long[] tagIds = AssetTagLocalServiceUtil.getTagIds(
				groupId, assetTagNames);

			if (assetEntryQueryContains && assetEntryQueryAndOperator) {
				allTagIds = tagIds;
			}
			else if (assetEntryQueryContains && !assetEntryQueryAndOperator) {
				anyTagIds = tagIds;
			}
			else if (!assetEntryQueryContains && assetEntryQueryAndOperator) {
				notAllTagIds = tagIds;
			}
			else {
				notAnyTagIds = tagIds;
			}
		}

		// Process public render parameters

		if (assetCategoryId > 0) {
			allCategoryIds = ArrayUtil.append(allCategoryIds, assetCategoryId);
		}

		if (Validator.isNotNull(assetTagName)) {
			String[] names = new String[] {assetTagName};

			allTagIds = ArrayUtil.append(
				allTagIds, AssetTagLocalServiceUtil.getTagIds(groupId, names));
		}

		// Mimic behavior in AssetEntryServiceImpl#setupQuery. Process
		// permission checks for AssetCategories and AssetTags.

		List<Long> categoryIds = new UniqueList<Long>();

		categoryIds.addAll(SetUtil.fromArray(allCategoryIds));
		categoryIds.addAll(SetUtil.fromArray(anyCategoryIds));

		for (long categoryId : categoryIds) {
			try {
				AssetCategoryServiceUtil.getCategory(categoryId);
			}
			catch (PrincipalException pe) {
				allCategoryIds = ArrayUtil.remove(allCategoryIds, categoryId);
				anyCategoryIds = ArrayUtil.remove(anyCategoryIds, categoryId);
			}
		}

		List<Long> tagIds = new UniqueList<Long>();

		tagIds.addAll(SetUtil.fromArray(allTagIds));
		tagIds.addAll(SetUtil.fromArray(anyTagIds));

		for (long tagId : tagIds) {
			try {
				AssetTagServiceUtil.getTag(tagId);
			}
			catch (PrincipalException pe) {
				allTagIds = ArrayUtil.remove(allTagIds, tagId);
				anyTagIds = ArrayUtil.remove(anyTagIds, tagId);
			}
		}

		// Check AssetEntryQuery properties

		if ((allCategoryIds.length == 0) && (allTagIds.length == 0) &&
			(anyCategoryIds.length == 0) && (anyTagIds.length == 0) &&
			(notAllCategoryIds.length == 0) && (notAllTagIds.length == 0) &&
			(notAnyCategoryIds.length == 0) && (notAnyTagIds.length == 0)) {

			if (!assetEntryQueryContains) {

				// Assets are not being used to filter articles

				return null;
			}
			else if (((assetCategoryIds.length > 0) && assetCategories) ||
					 ((assetTagNames.length > 0) && assetTags)) {

				// Selected assets not found. Return no classPKs

				return new long[0];
			}
			else {

				// Assets are not being used to filter articles

				return null;
			}
		}

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setClassName(Article.class.getName());
		assetEntryQuery.setGroupIds(new long[] {groupId});

		assetEntryQuery.setAllCategoryIds(allCategoryIds);
		assetEntryQuery.setAnyCategoryIds(anyCategoryIds);
		assetEntryQuery.setNotAllCategoryIds(notAllCategoryIds);
		assetEntryQuery.setNotAnyCategoryIds(notAnyCategoryIds);

		assetEntryQuery.setAllTagIds(allTagIds);
		assetEntryQuery.setAnyTagIds(anyTagIds);
		assetEntryQuery.setNotAllTagIds(notAllTagIds);
		assetEntryQuery.setNotAnyTagIds(notAnyTagIds);

		// Delegate article permission checks to ArticleServiceImpl

		List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil.getEntries(
			assetEntryQuery);

		return StringUtil.split(ListUtil.toString(assetEntries, "classPK"), 0L);
	}

	protected static OrderByComparator getOrderByComparator(
		String orderByColumn, boolean orderByAscending) {

		if (orderByColumn.equals("create-date")) {
			return new ArticleCreateDateComparator(orderByAscending);
		}
		else if (orderByColumn.equals("modified-date")) {
			return new ArticleModifiedDateComparator(orderByAscending);
		}
		else if (orderByColumn.equals("priority")) {
			return new ArticlePriorityComparator(orderByAscending);
		}
		else if (orderByColumn.equals("title")) {
			return new ArticleTitleComparator(orderByAscending);
		}

		return null;
	}

	protected static boolean isValidPortletId(String portletId) {
		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		String[] rootPortletIds = PortletKeys.KNOWLEDGE_BASE_PORTLETS;

		rootPortletIds = ArrayUtil.remove(
			rootPortletIds, PortletKeys.KNOWLEDGE_BASE_ADMIN);

		return ArrayUtil.contains(rootPortletIds, rootPortletId);
	}

}