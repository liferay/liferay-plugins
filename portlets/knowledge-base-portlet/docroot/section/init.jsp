<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ page import="com.liferay.knowledgebase.section.search.KBArticleSearch" %>

<%
boolean showKBArticlesSectionsTitle = GetterUtil.getBoolean(portletPreferences.getValue("showKBArticlesSectionsTitle", null));
String[] kbArticlesSections = portletPreferences.getValues("kbArticlesSections", new String[0]);
String kbArticleDisplayStyle = portletPreferences.getValue("kbArticleDisplayStyle", StringPool.BLANK);
String kbArticleWindowState = portletPreferences.getValue("kbArticleWindowState", StringPool.BLANK);
String kbArticlesOrderByCol = portletPreferences.getValue("kbArticlesOrderByCol", StringPool.BLANK);
String kbArticlesOrderByType = portletPreferences.getValue("kbArticlesOrderByType", StringPool.BLANK);
int kbArticlesDelta = GetterUtil.getInteger(portletPreferences.getValue("kbArticlesDelta", null));
boolean showKBArticlesPagination = GetterUtil.getBoolean(portletPreferences.getValue("showKBArticlesPagination", null));

boolean enableKBArticleDescription = GetterUtil.getBoolean(portletPreferences.getValue("enableKBArticleDescription", null));
boolean enableKBArticleRatings = GetterUtil.getBoolean(portletPreferences.getValue("enableKBArticleRatings", null));
boolean showKBArticleAssetEntries = GetterUtil.getBoolean(portletPreferences.getValue("showKBArticleAssetEntries", null));
boolean enableKBArticleKBComments = GetterUtil.getBoolean(portletPreferences.getValue("enableKBArticleKBComments", null));
boolean showKBArticleKBComments = GetterUtil.getBoolean(portletPreferences.getValue("showKBArticleKBComments", null));
boolean enableKBArticleViewCountIncrement = GetterUtil.getBoolean(portletPreferences.getValue("enableKBArticleViewCountIncrement", null));

int rssDelta = GetterUtil.getInteger(portletPreferences.getValue("rssDelta", null));
String rssDisplayStyle = portletPreferences.getValue("rssDisplayStyle", StringPool.BLANK);
String rssFormat = portletPreferences.getValue("rssFormat", StringPool.BLANK);
%>