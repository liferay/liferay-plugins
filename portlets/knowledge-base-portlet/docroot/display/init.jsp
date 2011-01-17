<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

Map<String, String> preferencesMap = KnowledgeBaseUtil.initPortletPreferencesMap(rootPortletId, preferences);

String childArticlesDisplayStyle = preferencesMap.get("childArticlesDisplayStyle");
boolean enableArticleAssetCategories = GetterUtil.getBoolean(preferencesMap.get("enableArticleAssetCategories"));
boolean enableArticleAssetTags = GetterUtil.getBoolean(preferencesMap.get("enableArticleAssetTags"));
boolean enableArticleRatings = GetterUtil.getBoolean(preferencesMap.get("enableArticleRatings"));
boolean enableArticleComments = GetterUtil.getBoolean(preferencesMap.get("enableArticleComments"));

String selectionMethod = preferencesMap.get("selection-method");
long[] resourcePrimKeys = StringUtil.split(preferencesMap.get("resourcePrimKeys"), 0L);
boolean allArticles = GetterUtil.getBoolean(preferencesMap.get("allArticles"));
String orderByColumn = preferencesMap.get("orderByColumn");
boolean orderByAscending = GetterUtil.getBoolean(preferencesMap.get("orderByAscending"));
boolean assetEntryQueryContains = GetterUtil.getBoolean(preferencesMap.get("assetEntryQueryContains"));
boolean assetEntryQueryAndOperator = GetterUtil.getBoolean(preferencesMap.get("assetEntryQueryAndOperator"));
String assetEntryQueryName = preferencesMap.get("assetEntryQueryName");
long[] assetCategoryIds = StringUtil.split(preferencesMap.get("assetCategoryIds"), 0L);
String[] assetTagNames = StringUtil.split(preferencesMap.get("assetTagNames"));

int rssDelta = GetterUtil.getInteger(preferencesMap.get("rssDelta"));
String rssDisplayStyle = preferencesMap.get("rssDisplayStyle");
String rssFormat = preferencesMap.get("rssFormat");
%>