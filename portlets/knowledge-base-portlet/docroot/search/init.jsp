<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

int articlesDelta = GetterUtil.getInteger(preferences.getValue("articles-delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String childArticlesDisplayStyle = preferences.getValue("child-articles-display-style", "abstract");
boolean enableArticleAssetCategories = GetterUtil.getBoolean(preferences.getValue("enable-article-asset-categories", null), true);
boolean enableArticleAssetTags = GetterUtil.getBoolean(preferences.getValue("enable-article-asset-tags", null), true);
boolean enableArticleRatings = GetterUtil.getBoolean(preferences.getValue("enable-article-ratings", null));
boolean enableArticleComments = GetterUtil.getBoolean(preferences.getValue("enable-article-comments", null), true);

String selectionMethod = preferences.getValue("selection-method", "group");
long[] resourcePrimKeys = GetterUtil.getLongValues(preferences.getValues("resource-prim-keys", null));

boolean assetEntryQueryContains = GetterUtil.getBoolean(preferences.getValue("asset-entry-query-contains", null), true);
boolean assetEntryQueryAndOperator = GetterUtil.getBoolean(preferences.getValue("asset-entry-query-and-operator", null));
String assetEntryQueryName = preferences.getValue("asset-entry-query-name", "asset-categories");
long[] assetCategoryIds = GetterUtil.getLongValues(preferences.getValues("asset-category-ids", null));
String[] assetTagNames = preferences.getValues("asset-tag-names", new String[0]);

int rssDelta = GetterUtil.getInteger(preferences.getValue("rss-delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = preferences.getValue("rss-display-style", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
String rssFormat = preferences.getValue("rss-format", "atom10");

String rssFormatType = RSSUtil.getFormatType(rssFormat);
double rssFormatVersion = RSSUtil.getFormatVersion(rssFormat);

if (windowState.equals(WindowState.MAXIMIZED)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setWindowState(WindowState.NORMAL);

	portletDisplay.setURLBack(portletURL.toString());
}
%>