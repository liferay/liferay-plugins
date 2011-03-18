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

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

long resourcePrimKey = GetterUtil.getInteger(preferences.getValue("resourcePrimKey", null));

boolean enableArticleDescription = GetterUtil.getBoolean(preferences.getValue("enableArticleDescription", null));
boolean enableArticleAssetCategories = GetterUtil.getBoolean(preferences.getValue("enableArticleAssetCategories", null));
boolean enableArticleAssetTags = GetterUtil.getBoolean(preferences.getValue("enableArticleAssetTags", null));
boolean enableArticleRatings = GetterUtil.getBoolean(preferences.getValue("enableArticleRatings", null));
boolean enableArticleComments = GetterUtil.getBoolean(preferences.getValue("enableArticleComments", null));
boolean showArticleComments = GetterUtil.getBoolean(preferences.getValue("showArticleComments", null));
boolean enableArticleViewCountIncrement = GetterUtil.getBoolean(preferences.getValue("enableArticleViewCountIncrement", null));

int rssDelta = GetterUtil.getInteger(preferences.getValue("rssDelta", null));
String rssDisplayStyle = preferences.getValue("rssDisplayStyle", StringPool.BLANK);
String rssFormat = preferences.getValue("rssFormat", StringPool.BLANK);
%>