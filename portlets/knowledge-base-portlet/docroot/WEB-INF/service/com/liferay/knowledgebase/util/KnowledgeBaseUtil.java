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
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticleCreateDateComparator;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DiffHtmlUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.WindowState;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KnowledgeBaseUtil {

	public static String getArticleDiff(
			long resourcePrimKey, int sourceVersion, int targetVersion,
			String parameter, String portalURL)
		throws Exception {

		if (sourceVersion == targetVersion) {
			Article article = ArticleLocalServiceUtil.getArticle(
				resourcePrimKey, targetVersion);

			Object object = BeanPropertiesUtil.getObject(article, parameter);

			return String.valueOf(object);
		}

		Article sourceArticle = ArticleLocalServiceUtil.getArticle(
			resourcePrimKey, sourceVersion);
		Article targetArticle = ArticleLocalServiceUtil.getArticle(
			resourcePrimKey, targetVersion);

		Object sourceObject = BeanPropertiesUtil.getObject(
			sourceArticle, parameter);
		Object targetObject = BeanPropertiesUtil.getObject(
			targetArticle, parameter);

		String sourceHtml = String.valueOf(sourceObject);
		String targetHtml = String.valueOf(targetObject);

		return getDiff(sourceHtml, targetHtml, portalURL);
	}

	public static String getArticleDiff(
			long resourcePrimKey, int version, String parameter,
			String portalURL)
		throws Exception {

		int sourceVersion = version;
		int targetVersion = version;

		if (sourceVersion != ArticleConstants.DEFAULT_VERSION) {
			sourceVersion = sourceVersion - 1;
		}

		return getArticleDiff(
			resourcePrimKey, sourceVersion, targetVersion, parameter,
			portalURL);
	}

	public static String getArticleURL(
			String portletId, long resourcePrimKey, String portalURL)
		throws Exception {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			Article article = ArticleLocalServiceUtil.getLatestArticle(
				resourcePrimKey, WorkflowConstants.STATUS_ANY);

			String layoutFullURL = PortalUtil.getControlPanelFullURL(
				article.getGroupId(), PortletKeys.KNOWLEDGE_BASE_ADMIN, null);

			return getArticleURL(
				portletId, resourcePrimKey, layoutFullURL, false);
		}

		Object[] plidAndWindowState = getPlidAndWindowState(
			portletId, rootPortletId, resourcePrimKey, portalURL);

		long plid = (Long)plidAndWindowState[0];
		WindowState windowState = (WindowState)plidAndWindowState[1];

		if (plid == LayoutConstants.DEFAULT_PLID) {
			return StringPool.BLANK;
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		String layoutActualURL = PortalUtil.getLayoutActualURL(layout);
		String layoutFullURL = portalURL + layoutActualURL;

		boolean maximized = windowState.equals(WindowState.MAXIMIZED);

		return getArticleURL(
			portletId, resourcePrimKey, layoutFullURL, maximized);
	}

	public static String getArticleURL(
		String portletId, long resourcePrimKey, String layoutFullURL,
		boolean maximized) {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);
		String namespace = PortalUtil.getPortletNamespace(portletId);

		String jspPage = null;

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			jspPage = "/admin/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_AGGREGATOR)) {
			jspPage = "/aggregator/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			jspPage = "/display/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_LIST)) {
			jspPage = "/list/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SEARCH)) {
			jspPage = "/search/view_article.jsp";
		}

		String articleURL = layoutFullURL;

		if (maximized) {
			articleURL = HttpUtil.setParameter(
				articleURL, "p_p_state", WindowState.MAXIMIZED.toString());
		}

		articleURL = HttpUtil.setParameter(articleURL, "p_p_id", portletId);
		articleURL = HttpUtil.setParameter(
			articleURL, namespace + "jspPage", jspPage);
		articleURL = HttpUtil.setParameter(
			articleURL, namespace + "resourcePrimKey", resourcePrimKey);

		return articleURL;
	}

	public static List<Article> getArticles(
			long groupId, long[] resourcePrimKeys, int start, int end,
			boolean checkPermission)
		throws Exception {

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			start = 0;
			end = resourcePrimKeys.length;
		}

		List<Long> selResourcePrimKeys = new ArrayList<Long>();

		for (int i = start; (i < end) && (i < resourcePrimKeys.length); i++) {
			selResourcePrimKeys.add(resourcePrimKeys[i]);
		}

		resourcePrimKeys = StringUtil.split(
			StringUtil.merge(selResourcePrimKeys), 0L);

		List<Article> unsortedArticles = null;

		if (checkPermission) {
			unsortedArticles = ArticleServiceUtil.getArticles(
				groupId, resourcePrimKeys, WorkflowConstants.STATUS_APPROVED,
				null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}
		else {
			unsortedArticles = ArticleLocalServiceUtil.getArticles(
				resourcePrimKeys, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}

		unsortedArticles = ListUtil.copy(unsortedArticles);

		List<Article> articles = new ArrayList<Article>();

		for (long resourcePrimKey : resourcePrimKeys) {
			for (int i = 0; i < unsortedArticles.size(); i++) {
				Article article = unsortedArticles.get(i);

				if (article.getResourcePrimKey() == resourcePrimKey) {
					articles.add(article);
					unsortedArticles.remove(article);

					break;
				}
			}
		}

		return articles;
	}

	public static List<AssetEntry> getAssetEntries(
			long plid, String portletId, long assetCategoryId,
			String assetTagName)
		throws Exception {

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);
		Group group = layout.getGroup();

		PortletPreferences jxPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		String selectionMethod = jxPreferences.getValue(
			"selection-method", "group");

		boolean assetEntryQueryContains = GetterUtil.getBoolean(
			jxPreferences.getValue("asset-entry-query-contains", null), true);
		boolean assetEntryQueryAndOperator = GetterUtil.getBoolean(
			jxPreferences.getValue("asset-entry-query-and-operator", null));
		String assetEntryQueryName = jxPreferences.getValue(
			"asset-entry-query-name", "asset-categories");
		long[] assetCategoryIds = GetterUtil.getLongValues(
			jxPreferences.getValues("asset-category-ids", null));
		String[] assetTagNames = jxPreferences.getValues(
			"asset-tag-names", new String[0]);

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_LIST) ||
			selectionMethod.equals("articles")) {

			if ((assetCategoryId <= 0) && Validator.isNull(assetTagName)) {
				return null;
			}

			assetEntryQueryName = StringPool.BLANK;
		}

		long[] allAssetCategoryIds = new long[0];
		long[] anyAssetCategoryIds = new long[0];
		long[] notAllAssetCategoryIds = new long[0];
		long[] notAnyAssetCategoryIds = new long[0];

		if (assetEntryQueryName.equals("asset-categories")) {
			if ((assetCategoryId <= 0) && Validator.isNull(assetTagName) &&
				(assetCategoryIds.length <= 0)) {

				return null;
			}

			if (assetEntryQueryContains && assetEntryQueryAndOperator) {
				allAssetCategoryIds = assetCategoryIds;
			}
			else if (assetEntryQueryContains && !assetEntryQueryAndOperator) {
				anyAssetCategoryIds = assetCategoryIds;
			}
			else if (!assetEntryQueryContains && assetEntryQueryAndOperator) {
				notAllAssetCategoryIds = assetCategoryIds;
			}
			else {
				notAnyAssetCategoryIds = assetCategoryIds;
			}
		}

		long[] allAssetTagIds = new long[0];
		long[] anyAssetTagIds = new long[0];
		long[] notAllAssetTagIds = new long[0];
		long[] notAnyAssetTagIds = new long[0];

		if (assetEntryQueryName.equals("asset-tags")) {
			if ((assetCategoryId <= 0) && Validator.isNull(assetTagName) &&
				(assetTagNames.length <= 0)) {

				return null;
			}

			long[] assetTagIds = AssetTagLocalServiceUtil.getTagIds(
				group.getGroupId(), assetTagNames);

			if ((assetTagIds.length <= 0) && assetEntryQueryContains &&
				(assetCategoryId <= 0) && Validator.isNull(assetTagName)) {

				return new ArrayList<AssetEntry>();
			}

			if (assetEntryQueryContains && assetEntryQueryAndOperator) {
				allAssetTagIds = assetTagIds;
			}
			else if (assetEntryQueryContains && !assetEntryQueryAndOperator) {
				anyAssetTagIds = assetTagIds;
			}
			else if (!assetEntryQueryContains && assetEntryQueryAndOperator) {
				notAllAssetTagIds = assetTagIds;
			}
			else {
				notAnyAssetTagIds = assetTagIds;
			}
		}

		if (assetCategoryId > 0) {
			allAssetCategoryIds = ArrayUtil.append(
				allAssetCategoryIds, assetCategoryId);
		}

		if (Validator.isNotNull(assetTagName)) {
			long[] assetTagIds = AssetTagLocalServiceUtil.getTagIds(
				group.getGroupId(), new String[] {assetTagName});

			allAssetTagIds = ArrayUtil.append(allAssetTagIds, assetTagIds);
		}

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setAllCategoryIds(allAssetCategoryIds);
		assetEntryQuery.setAllTagIds(allAssetTagIds);
		assetEntryQuery.setAnyCategoryIds(anyAssetCategoryIds);
		assetEntryQuery.setAnyTagIds(anyAssetTagIds);
		assetEntryQuery.setClassName(Article.class.getName());
		assetEntryQuery.setGroupIds(new long[] {group.getGroupId()});
		assetEntryQuery.setNotAllCategoryIds(notAllAssetCategoryIds);
		assetEntryQuery.setNotAllTagIds(notAllAssetTagIds);
		assetEntryQuery.setNotAnyCategoryIds(notAnyAssetCategoryIds);
		assetEntryQuery.setNotAnyTagIds(notAnyAssetTagIds);

		return AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
	}

	public static Article getDisplayArticle(
			long plid, String portletId, long assetCategoryId,
			String assetTagName, PermissionChecker permissionChecker)
		throws Exception {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			return null;
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);
		Group group = layout.getGroup();

		PortletPreferences jxPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		String selectionMethod = jxPreferences.getValue(
			"selection-method", "group");
		long[] resourcePrimKeys = GetterUtil.getLongValues(
			jxPreferences.getValues("resource-prim-keys", null));

		boolean allArticles = GetterUtil.getBoolean(
			jxPreferences.getValue("all-articles", null), true);
		String orderByColumn = jxPreferences.getValue(
			"order-by-column", "modified-date");
		boolean orderByAscending = GetterUtil.getBoolean(
			jxPreferences.getValue("order-by-ascending", null));

		OrderByComparator orderByComparator = null;

		if (orderByColumn.equals("create-date")) {
			orderByComparator = new ArticleCreateDateComparator(
				orderByAscending);
		}
		else if (orderByColumn.equals("modified-date")) {
			orderByComparator = new ArticleModifiedDateComparator(
				orderByAscending);
		}

		List<Article> articles = new ArrayList<Article>();

		if (selectionMethod.equals("articles")) {
			List<AssetEntry> assetEntries = getAssetEntries(
				plid, portletId, assetCategoryId, assetTagName);

			if (assetEntries != null) {
				long[] classPKs = StringUtil.split(
					ListUtil.toString(assetEntries, "classPK"), 0L);

				Set<Long> classPKsSet = SetUtil.fromArray(classPKs);
				Set<Long> resourcePrimKeysSet = SetUtil.fromArray(
					resourcePrimKeys);

				resourcePrimKeysSet.retainAll(classPKsSet);

				resourcePrimKeys = StringUtil.split(
					StringUtil.merge(resourcePrimKeysSet), 0L);
			}

			articles = getArticles(
				group.getGroupId(), resourcePrimKeys, 0, 1, true);
		}
		else if (selectionMethod.equals("group")) {
			List<AssetEntry> assetEntries = getAssetEntries(
				plid, portletId, assetCategoryId, assetTagName);

			if (assetEntries != null) {
				long[] classPKs = StringUtil.split(
					ListUtil.toString(assetEntries, "classPK"), 0L);

				articles = ArticleServiceUtil.getArticles(
					group.getGroupId(), classPKs,
					WorkflowConstants.STATUS_APPROVED, null, 0, 1,
					orderByComparator);
			}
			else if (!allArticles) {
				articles = ArticleServiceUtil.getSiblingArticles(
					group.getGroupId(),
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
					WorkflowConstants.STATUS_APPROVED, 0, 1, orderByComparator);
			}
			else {
				articles = ArticleServiceUtil.getGroupArticles(
					group.getGroupId(), WorkflowConstants.STATUS_APPROVED,
					null, 0, 1, orderByComparator);
			}
		}

		if (articles.isEmpty()) {
			return null;
		}

		return articles.get(0);
	}

	protected static Object[] getPlidAndWindowState(
			String portletId, long resourcePrimKey, boolean checkWindowState)
		throws Exception {

		Article article = ArticleLocalServiceUtil.getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		long plid = PortalUtil.getPlidFromPortletId(
			article.getGroupId(), portletId);

		if (plid == LayoutConstants.DEFAULT_PLID) {
			return new Object[] {plid, WindowState.NORMAL};
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		PortletPreferences jxPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		WindowState windowState = WindowState.NORMAL;

		if (checkWindowState) {
			String articleWindowState = jxPreferences.getValue(
				"article-window-state", WindowState.MAXIMIZED.toString());

			if (articleWindowState.equals(WindowState.MAXIMIZED.toString())) {
				windowState = WindowState.MAXIMIZED;
			}
		}

		if (hasArticle(article, jxPreferences)) {
			return new Object[] {plid, windowState};
		}

		return new Object[] {LayoutConstants.DEFAULT_PLID, WindowState.NORMAL};
	}

	protected static String getDiff(
			String sourceHtml, String targetHtml, String portalURL)
		throws Exception {

		String diff = DiffHtmlUtil.diff(
			new UnsyncStringReader(sourceHtml),
			new UnsyncStringReader(targetHtml));

		diff = StringUtil.replace(
			diff,
			new String[] {
				"changeType=\"diff-added-image\"",
				"changeType=\"diff-changed-image\"",
				"changeType=\"diff-removed-image\"",
				"class=\"diff-html-added\"",
				"class=\"diff-html-changed\"",
				"class=\"diff-html-removed\""
			},
			new String[] {
				"style=\"border: 10px solid #CFC;\"",
				"style=\"border: 10px solid #C6C6FD;\"",
				"style=\"border: 10px solid #FDC6C6;\"",
				"style=\"background-color: #CFC;\"",
				"style=\"background-color: #C6C6FD\"",
				"style=\"background-color: #FDC6C6; " +
					"text-decoration: line-through;\""
			});

		if (Validator.isNotNull(portalURL)) {
			diff = StringUtil.replace(
				diff,
				new String[] {
					"href=\"/",
					"src=\"/"
				},
				new String[] {
					"href=\"" + portalURL + "/",
					"src=\"" + portalURL + "/"
				});
		}

		int i = diff.indexOf("<img ");

		while (i != -1) {
			String oldImg = diff.substring(i, diff.indexOf("/>", i) + 2);

			int x = oldImg.indexOf("style=\"");
			int y = oldImg.indexOf("style=\"", x + 7);
			int z = oldImg.indexOf(StringPool.QUOTE, y + 7);

			if (y != -1) {
				String style = oldImg.substring(y, z + 1);

				String newImg = StringUtil.replace(
					oldImg,
					new String[] {
						style,
						"style=\""
					},
					new String[] {
						StringPool.BLANK,
						style.substring(0, style.length() - 1)
					});

				diff = StringUtil.replace(diff, oldImg, newImg);
			}

			i = diff.indexOf("<img ", i + 5);
		}

		return diff;
	}

	protected static Object[] getPlidAndWindowState(
			String portletId, String rootPortletId, long resourcePrimKey,
			String portalURL)
		throws Exception {

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_AGGREGATOR)) {
			return getPlidAndWindowState(portletId, resourcePrimKey, true);
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			return getPlidAndWindowState(portletId, resourcePrimKey, false);
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_LIST)) {
			return getPlidAndWindowState(portletId, resourcePrimKey, true);
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SEARCH)) {
			return getPlidAndWindowState(portletId, resourcePrimKey, false);
		}

		return new Object[] {LayoutConstants.DEFAULT_PLID, WindowState.NORMAL};
	}

	protected static boolean hasArticle(
			Article article, PortletPreferences jxPreferences)
		throws Exception {

		String selectionMethod = jxPreferences.getValue(
			"selection-method", "group");
		long[] resourcePrimKeys = GetterUtil.getLongValues(
			jxPreferences.getValues("resource-prim-keys", null));

		boolean hasArticle = ArrayUtil.contains(
			resourcePrimKeys, article.getResourcePrimKey());

		if ((selectionMethod.equals("group")) ||
			(selectionMethod.equals("articles") && hasArticle)) {

			return true;
		}

		if (selectionMethod.equals("articles")) {

			// Retrieving all parent and children articles for each selected
			// article can be expensive. Skip this check for better performance.

			return true;
		}

		return false;
	}

}