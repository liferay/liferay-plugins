<%
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
%>

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String articlesTitle = preferences.getValue("articles-title", StringPool.BLANK);
int articlesDelta = GetterUtil.getInteger(preferences.getValue("articles-delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String articlesDisplayStyle = preferences.getValue("articles-display-style", "title");
String articleWindowState = preferences.getValue("article-window-state", WindowState.MAXIMIZED.toString());
String childArticlesDisplayStyle = preferences.getValue("child-articles-display-style", "abstract");
boolean enableArticleComments = GetterUtil.getBoolean(preferences.getValue("enable-article-comments", null), true);
boolean enableArticleCommentRatings = GetterUtil.getBoolean(preferences.getValue("enable-article-comment-ratings", null));
%>