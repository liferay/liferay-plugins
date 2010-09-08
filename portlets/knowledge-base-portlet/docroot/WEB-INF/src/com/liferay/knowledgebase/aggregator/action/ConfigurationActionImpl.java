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

package com.liferay.knowledgebase.aggregator.action;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl
	extends com.liferay.knowledgebase.admin.action.ConfigurationActionImpl {

	protected void postProcessPreferences(
			PortletPreferences preferences, ActionRequest actionRequest)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("selection-method")) {
			updateSelectionMethod(actionRequest, preferences);
		}
	}

	protected void updateDisplaySettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String articlesTitle = ParamUtil.getString(
			actionRequest, "articlesTitle");
		int articlesDelta = ParamUtil.getInteger(
			actionRequest, "articlesDelta");
		String articlesDisplayStyle = ParamUtil.getString(
			actionRequest, "articlesDisplayStyle");
		String articleWindowState = ParamUtil.getString(
			actionRequest, "articleWindowState");
		String childArticlesDisplayStyle = ParamUtil.getString(
			actionRequest, "childArticlesDisplayStyle");
		boolean enableArticleAssetCategories = ParamUtil.getBoolean(
			actionRequest, "enableArticleAssetCategories");
		boolean enableArticleAssetTags = ParamUtil.getBoolean(
			actionRequest, "enableArticleAssetTags");
		boolean enableArticleRatings = ParamUtil.getBoolean(
			actionRequest, "enableArticleRatings");

		preferences.setValue("articles-title", articlesTitle);
		preferences.setValue("articles-delta", String.valueOf(articlesDelta));
		preferences.setValue("articles-display-style", articlesDisplayStyle);
		preferences.setValue("article-window-state", articleWindowState);
		preferences.setValue(
			"child-articles-display-style", childArticlesDisplayStyle);
		preferences.setValue(
			"enable-article-asset-categories",
			String.valueOf(enableArticleAssetCategories));
		preferences.setValue(
			"enable-article-asset-tags",
			String.valueOf(enableArticleAssetTags));
		preferences.setValue(
			"enable-article-ratings", String.valueOf(enableArticleRatings));
	}

	protected void updateSelectionMethod(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String selectionMethod = ParamUtil.getString(
			actionRequest, "selectionMethod");
		long[] resourcePrimKeys = StringUtil.split(
			ParamUtil.getString(actionRequest, "resourcePrimKeys"), 0L);
		boolean allArticles = ParamUtil.getBoolean(
			actionRequest, "allArticles");
		String orderByColumn = ParamUtil.getString(
			actionRequest, "orderByColumn");
		boolean orderByAscending = ParamUtil.getBoolean(
			actionRequest, "orderByAscending");
		boolean assetEntryQueryContains = ParamUtil.getBoolean(
			actionRequest, "assetEntryQueryContains");
		boolean assetEntryQueryAndOperator = ParamUtil.getBoolean(
			actionRequest, "assetEntryQueryAndOperator");
		String assetEntryQueryName = ParamUtil.getString(
			actionRequest, "assetEntryQueryName");
		long[] assetCategoryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "assetCategoryIds"), 0L);
		String[] assetTagNames = StringUtil.split(
			ParamUtil.getString(actionRequest, "assetTagNames"));

		preferences.setValue("selection-method", selectionMethod);
		preferences.setValues(
			"resource-prim-keys", ArrayUtil.toStringArray(resourcePrimKeys));
		preferences.setValue("all-articles", String.valueOf(allArticles));
		preferences.setValue("order-by-column", orderByColumn);
		preferences.setValue(
			"order-by-ascending", String.valueOf(orderByAscending));
		preferences.setValue(
			"asset-entry-query-contains",
			String.valueOf(assetEntryQueryContains));
		preferences.setValue(
			"asset-entry-query-and-operator",
			String.valueOf(assetEntryQueryAndOperator));
		preferences.setValue("asset-entry-query-name", assetEntryQueryName);
		preferences.setValues(
			"asset-category-ids", ArrayUtil.toStringArray(assetCategoryIds));
		preferences.setValues("asset-tag-names", assetTagNames);
	}

}