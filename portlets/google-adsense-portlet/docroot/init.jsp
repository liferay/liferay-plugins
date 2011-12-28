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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String adClient = preferences.getValue("adClient", StringPool.BLANK);
String adChannel = preferences.getValue("adChannel", StringPool.BLANK);
int adFormat = GetterUtil.getInteger(preferences.getValue("adFormat", StringPool.BLANK));
int adType = GetterUtil.getInteger(preferences.getValue("adType", StringPool.BLANK));

String colorBorder = preferences.getValue("colorBorder", StringPool.BLANK);
String colorBg = preferences.getValue("colorBg", StringPool.BLANK);
String colorLink = preferences.getValue("colorLink", StringPool.BLANK);
String colorText = preferences.getValue("colorText", StringPool.BLANK);
String colorUrl = preferences.getValue("colorUrl", StringPool.BLANK);

String[][] adFormats = {
	{"0", "0", "0", "(0 x 0) - " + LanguageUtil.get(pageContext, "no-banner")},
	{"1", "728", "90", "(728 x 90) - " + LanguageUtil.get(pageContext, "leaderboard")},
	{"2", "468", "60", "(468 x 60) - " + LanguageUtil.get(pageContext, "banner")},
	{"3", "336", "280", "(336 x 280) - " + LanguageUtil.get(pageContext, "large-rectangle")},
	{"4", "300", "250", "(300 x 250) - " + LanguageUtil.get(pageContext, "medium-rectangle")},
	{"5", "250", "250", "(250 x 250) - " + LanguageUtil.get(pageContext, "square")},
	{"6", "234", "60", "(234 x 60) - " + LanguageUtil.get(pageContext, "half-banner")},
	{"7", "200", "200", "(200 x 200) - " + LanguageUtil.get(pageContext, "small-square")},
	{"8", "180", "150", "(180 x 150) - " + LanguageUtil.get(pageContext, "small-rectangle")},
	{"9", "160", "600", "(160 x 600) - " + LanguageUtil.get(pageContext, "wide-skyscraper")},
	{"10", "125", "125", "(125 x 125) - " + LanguageUtil.get(pageContext, "button")},
	{"11", "120", "600", "(120 x 600) - " + LanguageUtil.get(pageContext, "skyscraper")},
	{"12", "120", "240", "(120 x 240) - " + LanguageUtil.get(pageContext, "vertical-banner")}
};

String[][] adTypes = {
	{"0", LanguageUtil.get(pageContext, "none"), "none"},
	{"1", LanguageUtil.get(pageContext, "text"), "text"},
	{"2", LanguageUtil.get(pageContext, "image"), "image"},
	{"3", LanguageUtil.get(pageContext, "text-and-image"), "text_image"}
};

String adTypeValue = adTypes[adType][2];
int adTypeWidth = GetterUtil.getInteger(adFormats[adFormat][1]);
int adTypeHeight = GetterUtil.getInteger(adFormats[adFormat][2]);
%>