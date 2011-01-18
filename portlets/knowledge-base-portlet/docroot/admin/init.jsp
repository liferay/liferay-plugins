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

int articlesDelta = GetterUtil.getInteger(preferences.getValue("articlesDelta", StringPool.BLANK), 5);
String articlesDisplayStyle = preferences.getValue("articlesDisplayStyle", "full-content");

String childArticlesDisplayStyle = preferences.getValue("childArticlesDisplayStyle", "abstract");
boolean enableArticleDescription = GetterUtil.getBoolean(preferences.getValue("enableArticleDescription", null));
boolean enableArticleAssetCategories = GetterUtil.getBoolean(preferences.getValue("enableArticleAssetCategories", null), true);
boolean enableArticleAssetTags = GetterUtil.getBoolean(preferences.getValue("enableArticleAssetTags", null), true);
boolean enableArticleRatings = GetterUtil.getBoolean(preferences.getValue("enableArticleRatings", null));
boolean enableArticleComments = GetterUtil.getBoolean(preferences.getValue("enableArticleComments", null), true);

int templatesDelta = GetterUtil.getInteger(preferences.getValue("templatesDelta", StringPool.BLANK), 5);
String templatesDisplayStyle = preferences.getValue("templatesDisplayStyle", "full-content");
boolean enableTemplateDescription = GetterUtil.getBoolean(preferences.getValue("enableTemplateDescription", null));
boolean enableTemplateComments = GetterUtil.getBoolean(preferences.getValue("enableTemplateComments", null), true);

int rssDelta = GetterUtil.getInteger(preferences.getValue("rssDelta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = preferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
String rssFormat = preferences.getValue("rssFormat", "atom10");
%>