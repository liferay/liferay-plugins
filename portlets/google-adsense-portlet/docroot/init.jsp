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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %>

<portlet:defineObjects />

<%
String adClient = portletPreferences.getValue("adClient", StringPool.BLANK);
String adChannel = portletPreferences.getValue("adChannel", StringPool.BLANK);
int adFormat = GetterUtil.getInteger(portletPreferences.getValue("adFormat", StringPool.BLANK));
int adType = GetterUtil.getInteger(portletPreferences.getValue("adType", StringPool.BLANK));

String colorBorder = portletPreferences.getValue("colorBorder", StringPool.BLANK);
String colorBg = portletPreferences.getValue("colorBg", StringPool.BLANK);
String colorLink = portletPreferences.getValue("colorLink", StringPool.BLANK);
String colorText = portletPreferences.getValue("colorText", StringPool.BLANK);
String colorUrl = portletPreferences.getValue("colorUrl", StringPool.BLANK);

String[][] adFormats = {
	{"0", "0", "0", "(0 x 0) - " + LanguageUtil.get(request, "no-banner")},
	{"1", "728", "90", "(728 x 90) - " + LanguageUtil.get(request, "leaderboard")},
	{"2", "468", "60", "(468 x 60) - " + LanguageUtil.get(request, "banner")},
	{"3", "336", "280", "(336 x 280) - " + LanguageUtil.get(request, "large-rectangle")},
	{"4", "300", "250", "(300 x 250) - " + LanguageUtil.get(request, "medium-rectangle")},
	{"5", "250", "250", "(250 x 250) - " + LanguageUtil.get(request, "square")},
	{"6", "234", "60", "(234 x 60) - " + LanguageUtil.get(request, "half-banner")},
	{"7", "200", "200", "(200 x 200) - " + LanguageUtil.get(request, "small-square")},
	{"8", "180", "150", "(180 x 150) - " + LanguageUtil.get(request, "small-rectangle")},
	{"9", "160", "600", "(160 x 600) - " + LanguageUtil.get(request, "wide-skyscraper")},
	{"10", "125", "125", "(125 x 125) - " + LanguageUtil.get(request, "button")},
	{"11", "120", "600", "(120 x 600) - " + LanguageUtil.get(request, "skyscraper")},
	{"12", "120", "240", "(120 x 240) - " + LanguageUtil.get(request, "vertical-banner")}
};

String[][] adTypes = {
	{"0", LanguageUtil.get(request, "none"), "none"},
	{"1", LanguageUtil.get(request, "text"), "text"},
	{"2", LanguageUtil.get(request, "image"), "image"},
	{"3", LanguageUtil.get(request, "text-and-image"), "text_image"}
};

String adTypeValue = adTypes[adType][2];
int adTypeWidth = GetterUtil.getInteger(adFormats[adFormat][1]);
int adTypeHeight = GetterUtil.getInteger(adFormats[adFormat][2]);
%>