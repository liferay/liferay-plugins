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
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.comparator.ArticleCreateDateComparator;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.ArticleTitleComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.util.RSSUtil;
import com.liferay.util.UniqueList;

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
public class PortletPreferencesHelper {

	public static Tuple getArticlesTuple(
			long groupId, String rootPortletId, long assetCategoryId,
			String assetTagName, int start, int end,
			PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<String, String> preferencesMap = initPreferencesMap(
			rootPortletId, preferences);

		String selectionMethod = preferencesMap.get("selectionMethod");
		long[] resourcePrimKeys = StringUtil.split(
			preferencesMap.get("resourcePrimKeys"), 0L);
		String orderByColumn = preferencesMap.get("orderByColumn");
		boolean orderByAscending = GetterUtil.getBoolean(
			preferencesMap.get("orderByAscending"));

		if (selectionMethod.equals("articles")) {
			if ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) {
				long[] classPKs = getAssetEntriesClassPKs(
					groupId, assetCategoryId, assetTagName, preferencesMap);

				List<Long> list = new UniqueList<Long>();

				list.addAll(SetUtil.fromArray(resourcePrimKeys));
				list.retainAll(SetUtil.fromArray(classPKs));

				resourcePrimKeys = StringUtil.split(StringUtil.merge(list), 0L);
			}

			List<Article> articles = ArticleServiceUtil.getArticles(
				resourcePrimKeys, WorkflowConstants.STATUS_APPROVED, null);

			return new Tuple(
				ListUtil.subList(articles, start, end), articles.size());
		}
		else if (selectionMethod.equals("filter")) {
			long[] classPKs = getAssetEntriesClassPKs(
				groupId, assetCategoryId, assetTagName, preferencesMap);

			List<Article> articles = ArticleServiceUtil.getArticles(
				classPKs, WorkflowConstants.STATUS_APPROVED,
				getOrderByComparator(orderByColumn, orderByAscending));

			return new Tuple(
				ListUtil.subList(articles, start, end), articles.size());
		}
		else if (selectionMethod.equals("group")) {
			if ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) {
				long[] classPKs = getAssetEntriesClassPKs(
					groupId, assetCategoryId, assetTagName, preferencesMap);

				List<Article> articles = ArticleServiceUtil.getArticles(
					classPKs, WorkflowConstants.STATUS_APPROVED,
					getOrderByComparator(orderByColumn, orderByAscending));

				return new Tuple(
					ListUtil.subList(articles, start, end), articles.size());
			}

			List<Article> articles = ArticleServiceUtil.getSiblingArticles(
				groupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				WorkflowConstants.STATUS_APPROVED, start, end,
				getOrderByComparator(orderByColumn, orderByAscending));

			int count = ArticleServiceUtil.getSiblingArticlesCount(
				groupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				WorkflowConstants.STATUS_APPROVED);

			return new Tuple(articles, count);
		}

		return new Tuple(Collections.emptyList(), 0);
	}

	public static HashMap<String, Object> getSearchContextAttribute(
			long groupId, String rootPortletId, PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<String, String> preferencesMap = initPreferencesMap(
			rootPortletId, preferences);

		String selectionMethod = preferencesMap.get("selectionMethod");
		long[] resourcePrimKeys = StringUtil.split(
			preferencesMap.get("resourcePrimKeys"), 0L);
		boolean assetEntryQueryAndOperator = GetterUtil.getBoolean(
			preferencesMap.get("assetEntryQueryAndOperator"));
		String assetEntryQueryName = preferencesMap.get("assetEntryQueryName");
		long[] assetCategoryIds = StringUtil.split(
			preferencesMap.get("assetCategoryIds"), 0L);
		String[] assetTagNames = StringUtil.split(
			preferencesMap.get("assetTagNames"));

		// Mimic behavior in PortletPreferencesHelper#getAssetEntriesClassPKs

		long[] selAssetCategoryIds = new long[0];

		for (long assetCategoryId : assetCategoryIds) {
			try {
				AssetCategoryServiceUtil.getCategory(assetCategoryId);
			}
			catch (Exception e) {
				continue;
			}

			selAssetCategoryIds = ArrayUtil.append(
				selAssetCategoryIds, assetCategoryId);
		}

		String[] selAssetTagNames = new String[0];

		long[] assetTagIds = AssetTagLocalServiceUtil.getTagIds(
			groupId, assetTagNames);

		for (long assetTagId : assetTagIds) {
			AssetTag assetTag = null;

			try {
				assetTag = AssetTagServiceUtil.getTag(assetTagId);
			}
			catch (Exception e) {
				continue;
			}

			selAssetTagNames = ArrayUtil.append(
				selAssetTagNames, assetTag.getName());
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("selectionMethod", selectionMethod);
		params.put("resourcePrimKeys", ArrayUtil.toArray(resourcePrimKeys));
		params.put(
			"assetEntryQueryAndOperator", (Boolean)assetEntryQueryAndOperator);
		params.put("assetEntryQueryName", assetEntryQueryName);
		params.put("assetCategoryIds", ArrayUtil.toArray(selAssetCategoryIds));
		params.put("assetTagNames", selAssetTagNames);

		return params;
	}

	public static boolean hasArticle(
			long plid, String portletId, long resourcePrimKey)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!ArticlePermission.contains(
				permissionChecker, resourcePrimKey, ActionKeys.VIEW)) {

			return false;
		}

		Article article = ArticleServiceUtil.getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		Map<String, String> preferencesMap = initPreferencesMap(
			rootPortletId, preferences);

		String selectionMethod = preferencesMap.get("selectionMethod");
		long[] resourcePrimKeys = StringUtil.split(
			preferencesMap.get("resourcePrimKeys"), 0L);
		String orderByColumn = preferencesMap.get("orderByColumn");
		boolean orderByAscending = GetterUtil.getBoolean(
			preferencesMap.get("orderByAscending"));

		if (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) &&
			 selectionMethod.equals("group")) {

			return true;
		}

		// Mimic behavior in PortletPreferencesHelper#getArticlesTuple

		List<Article> articles = null;

		if (selectionMethod.equals("articles")) {
			articles = ArticleServiceUtil.getArticles(
				resourcePrimKeys, WorkflowConstants.STATUS_APPROVED, null);
		}
		else if (selectionMethod.equals("filter")) {
			long[] classPKs = getAssetEntriesClassPKs(
				article.getGroupId(), 0, null, preferencesMap);

			articles = ArticleServiceUtil.getArticles(
				classPKs, WorkflowConstants.STATUS_APPROVED,
				getOrderByComparator(orderByColumn, orderByAscending));
		}
		else if (selectionMethod.equals("group")) {
			articles = ArticleServiceUtil.getSiblingArticles(
				article.getGroupId(),
				ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
				WorkflowConstants.STATUS_APPROVED, 0, 1,
				getOrderByComparator(orderByColumn, orderByAscending));
		}

		// The Display portlet shows the first available article

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			articles = ListUtil.subList(articles, 0, 1);
		}

		// Users can navigate to the root article. See article_breadcrumbs.jsp.

		long[] rootResourcePrimKeys = new long[0];

		for (Article curArticle : articles) {
			while (curArticle.getParentResourcePrimKey() !=
						ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

				curArticle = ArticleServiceUtil.getLatestArticle(
					curArticle.getParentResourcePrimKey(),
					WorkflowConstants.STATUS_APPROVED);
			}

			rootResourcePrimKeys = ArrayUtil.append(
				rootResourcePrimKeys, curArticle.getResourcePrimKey());
		}

		while (resourcePrimKey !=
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			if (ArrayUtil.contains(rootResourcePrimKeys, resourcePrimKey)) {
				return true;
			}

			Article curArticle = ArticleServiceUtil.getLatestArticle(
				resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

			resourcePrimKey = curArticle.getParentResourcePrimKey();
		}

		return false;
	}

	public static Map<String, String> initPreferencesMap(
		String rootPortletId, PortletPreferences preferences) {

		Map<String, Object> defaultPreferences = new HashMap<String, Object>();

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			defaultPreferences.put("articlesDelta", 5);
			defaultPreferences.put("articlesDisplayStyle", "full-content");

			defaultPreferences.put("childArticlesDisplayStyle", "abstract");
			defaultPreferences.put("enableArticleDescription", false);
			defaultPreferences.put("enableArticleAssetCategories", true);
			defaultPreferences.put("enableArticleAssetTags", true);
			defaultPreferences.put("enableArticleRatings", false);
			defaultPreferences.put("enableArticleComments", true);
			defaultPreferences.put("showArticleComments", true);

			defaultPreferences.put("templatesDelta", 5);
			defaultPreferences.put("templatesDisplayStyle", "full-content");
			defaultPreferences.put("enableTemplateDescription", false);
			defaultPreferences.put("enableTemplateComments", true);
			defaultPreferences.put("showTemplateComments", true);

			defaultPreferences.put("rssDelta", SearchContainer.DEFAULT_DELTA);
			defaultPreferences.put(
				"rssDisplayStyle", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
			defaultPreferences.put("rssFormat", "atom10");
		}
		else {
			defaultPreferences.put("articlesTitle", StringPool.BLANK);
			defaultPreferences.put(
				"articlesDelta", SearchContainer.DEFAULT_DELTA);
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
			defaultPreferences.put("orderByColumn", "modified-date");
			defaultPreferences.put("orderByAscending", false);
			defaultPreferences.put("assetEntryQueryAndOperator", false);
			defaultPreferences.put("assetEntryQueryName", "asset-tags");
			defaultPreferences.put("assetCategoryIds", new Long[0]);
			defaultPreferences.put("assetTagNames", new String[0]);

			defaultPreferences.put("rssDelta", SearchContainer.DEFAULT_DELTA);
			defaultPreferences.put(
				"rssDisplayStyle", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
			defaultPreferences.put("rssFormat", "atom10");
		}

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

	protected static long[] getAssetEntriesClassPKs(
			long groupId, long assetCategoryId, String assetTagName,
			Map<String, String> preferencesMap)
		throws PortalException, SystemException {

		String selectionMethod = preferencesMap.get("selectionMethod");
		boolean assetEntryQueryAndOperator = GetterUtil.getBoolean(
			preferencesMap.get("assetEntryQueryAndOperator"));
		String assetEntryQueryName = preferencesMap.get("assetEntryQueryName");
		long[] assetCategoryIds = StringUtil.split(
			preferencesMap.get("assetCategoryIds"), 0L);
		String[] assetTagNames = StringUtil.split(
			preferencesMap.get("assetTagNames"));

		long[] allCategoryIds = new long[0];
		long[] anyCategoryIds = new long[0];
		long[] allTagIds = new long[0];
		long[] anyTagIds = new long[0];

		if (selectionMethod.equals("filter")) {
			if (assetEntryQueryName.equals("asset-categories")) {
				if (assetEntryQueryAndOperator) {
					allCategoryIds = assetCategoryIds;
				}
				else {
					anyCategoryIds = assetCategoryIds;
				}
			}
			else if (assetEntryQueryName.equals("asset-tags")) {
				long[] tagIds = AssetTagLocalServiceUtil.getTagIds(
					groupId, assetTagNames);

				if (assetEntryQueryAndOperator) {
					allTagIds = tagIds;
				}
				else {
					anyTagIds = tagIds;
				}
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
			catch (Exception e) {
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
			catch (Exception e) {
				allTagIds = ArrayUtil.remove(allTagIds, tagId);
				anyTagIds = ArrayUtil.remove(anyTagIds, tagId);
			}
		}

		// Check AssetEntryQuery properties

		if ((allCategoryIds.length == 0) && (allTagIds.length == 0) &&
			(anyCategoryIds.length == 0) && (anyTagIds.length == 0)) {

			return new long[0];
		}

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setClassName(Article.class.getName());
		assetEntryQuery.setGroupIds(new long[] {groupId});

		assetEntryQuery.setAllCategoryIds(allCategoryIds);
		assetEntryQuery.setAnyCategoryIds(anyCategoryIds);
		assetEntryQuery.setAllTagIds(allTagIds);
		assetEntryQuery.setAnyTagIds(anyTagIds);

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

}