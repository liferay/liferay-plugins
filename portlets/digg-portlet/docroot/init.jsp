<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="javax.portlet.PortletPreferences" %>

<portlet:defineObjects />

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String newsType = preferences.getValue("newsType", "domain");

boolean domain = newsType.equals("domain");
boolean friends = newsType.equals("friends");
boolean front = newsType.equals("front");
boolean search = newsType.equals("search");
boolean top = newsType.equals("top");
boolean user = newsType.equals("user");

boolean polling = GetterUtil.getBoolean(preferences.getValue("polling", "true"));
boolean showDesc = GetterUtil.getBoolean(preferences.getValue("showDesc", "false"));
boolean showDiggs = GetterUtil.getBoolean(preferences.getValue("showDiggs", "true"));
boolean showThumbs = GetterUtil.getBoolean(preferences.getValue("showThumbs", "false"));
boolean targetBlank = GetterUtil.getBoolean(preferences.getValue("targetBlank", "true"));

int storyCount = GetterUtil.getInteger(preferences.getValue("storyCount", StringPool.BLANK), 5);

String apiSearch = preferences.getValue("apiSearch", StringPool.BLANK);
String friendsUsername = preferences.getValue("friendsUsername", StringPool.BLANK);
String minDate = preferences.getValue("minDate", StringPool.BLANK);
String newsFriends = preferences.getValue("newsFriends", "dugg");
String newsFront = preferences.getValue("newsFront", StringPool.BLANK);
String newsTop = preferences.getValue("newsTop", StringPool.BLANK);
String newsUser = preferences.getValue("newsUser", "dugg");
String pollingRate = preferences.getValue("pollingRate", "60");
String searchSort = preferences.getValue("searchSort", StringPool.BLANK);
String sourcePopOrUp = preferences.getValue("sourcePopOrUp", "popular");
String searchTopics = preferences.getValue("searchTopics", StringPool.BLANK);
String url = preferences.getValue("url", StringPool.BLANK);
String urlSort = preferences.getValue("urlSort", "promote_date-desc");
String userUsername = preferences.getValue("userUsername", StringPool.BLANK);
%>