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

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

int articlesDelta = GetterUtil.getInteger(preferences.getValue("articles-delta", StringPool.BLANK), 5);
String articlesDisplayStyle = preferences.getValue("articles-display-style", "full-content");
String childArticlesDisplayStyle = preferences.getValue("child-articles-display-style", "abstract");
boolean enableArticleDescription = GetterUtil.getBoolean(preferences.getValue("enable-article-description", null));
boolean enableArticleComments = GetterUtil.getBoolean(preferences.getValue("enable-article-comments", null), true);
boolean enableArticleCommentRatings = GetterUtil.getBoolean(preferences.getValue("enable-article-comment-ratings", null));

int templatesDelta = GetterUtil.getInteger(preferences.getValue("templates-delta", StringPool.BLANK), 5);
String templatesDisplayStyle = preferences.getValue("templates-display-style", "full-content");
boolean enableTemplateDescription = GetterUtil.getBoolean(preferences.getValue("enable-template-description", null));
boolean enableTemplateComments = GetterUtil.getBoolean(preferences.getValue("enable-template-comments", null), true);
boolean enableTemplateCommentRatings = GetterUtil.getBoolean(preferences.getValue("enable-template-comment-ratings", null));

int rssDelta = GetterUtil.getInteger(preferences.getValue("rss-delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = preferences.getValue("rss-display-style", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
String rssFormat = preferences.getValue("rss-format", "atom10");

String rssFormatType = RSSUtil.getFormatType(rssFormat);
double rssFormatVersion = RSSUtil.getFormatVersion(rssFormat);

String rootPortletId = portletDisplay.getRootPortletId();

String jspPath = portletConfig.getInitParameter("jsp-path");

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>