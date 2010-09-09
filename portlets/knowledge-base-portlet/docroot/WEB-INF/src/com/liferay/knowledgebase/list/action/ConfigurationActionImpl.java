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

package com.liferay.knowledgebase.list.action;

import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl
	extends com.liferay.knowledgebase.admin.action.ConfigurationActionImpl {

	protected void updateDisplaySettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String articlesTitle = ParamUtil.getString(
			actionRequest, "articlesTitle");
		boolean allArticles = ParamUtil.getBoolean(
			actionRequest, "allArticles");
		String orderByColumn = ParamUtil.getString(
			actionRequest, "orderByColumn");
		boolean orderByAscending = ParamUtil.getBoolean(
			actionRequest, "orderByAscending");
		int articlesDelta = ParamUtil.getInteger(
			actionRequest, "articlesDelta");
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
		boolean enableArticleComments = ParamUtil.getBoolean(
			actionRequest, "enableArticleComments");

		preferences.setValue("articles-title", articlesTitle);
		preferences.setValue("all-articles", String.valueOf(allArticles));
		preferences.setValue("order-by-column", orderByColumn);
		preferences.setValue(
			"order-by-ascending", String.valueOf(orderByAscending));
		preferences.setValue("articles-delta", String.valueOf(articlesDelta));
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
		preferences.setValue(
			"enable-article-comments", String.valueOf(enableArticleComments));
	}

}