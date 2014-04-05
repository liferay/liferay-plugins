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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %>

<portlet:defineObjects />

<%
String newsType = portletPreferences.getValue("newsType", "domain");

boolean domain = newsType.equals("domain");
boolean friends = newsType.equals("friends");
boolean front = newsType.equals("front");
boolean search = newsType.equals("search");
boolean top = newsType.equals("top");
boolean user = newsType.equals("user");

boolean polling = GetterUtil.getBoolean(portletPreferences.getValue("polling", "true"));
boolean showDesc = GetterUtil.getBoolean(portletPreferences.getValue("showDesc", "false"));
boolean showDiggs = GetterUtil.getBoolean(portletPreferences.getValue("showDiggs", "true"));
boolean showThumbs = GetterUtil.getBoolean(portletPreferences.getValue("showThumbs", "false"));
boolean targetBlank = GetterUtil.getBoolean(portletPreferences.getValue("targetBlank", "true"));

int storyCount = GetterUtil.getInteger(portletPreferences.getValue("storyCount", StringPool.BLANK), 5);

String apiSearch = portletPreferences.getValue("apiSearch", StringPool.BLANK);
String friendsUsername = portletPreferences.getValue("friendsUsername", StringPool.BLANK);
String minDate = portletPreferences.getValue("minDate", StringPool.BLANK);
String newsFriends = portletPreferences.getValue("newsFriends", "dugg");
String newsFront = portletPreferences.getValue("newsFront", StringPool.BLANK);
String newsTop = portletPreferences.getValue("newsTop", StringPool.BLANK);
String newsUser = portletPreferences.getValue("newsUser", "dugg");
String pollingRate = portletPreferences.getValue("pollingRate", "60");
String searchSort = portletPreferences.getValue("searchSort", StringPool.BLANK);
String sourcePopOrUp = portletPreferences.getValue("sourcePopOrUp", "popular");
String searchTopics = portletPreferences.getValue("searchTopics", StringPool.BLANK);
String url = portletPreferences.getValue("url", StringPool.BLANK);
String urlSort = portletPreferences.getValue("urlSort", "promote_date-desc");
String userUsername = portletPreferences.getValue("userUsername", StringPool.BLANK);
%>