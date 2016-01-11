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

<%@ page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<portlet:defineObjects />

<%
boolean autoplay = GetterUtil.getBoolean(portletPreferences.getValue("autoplay", "false"));
boolean enableFullscreen = GetterUtil.getBoolean(portletPreferences.getValue("enableFullscreen", "false"));
String height = portletPreferences.getValue("height", "360");
String playerColor = portletPreferences.getValue("playerColor", StringPool.BLANK);
boolean showByline = GetterUtil.getBoolean(portletPreferences.getValue("showByline", "true"));
boolean showPortrait = GetterUtil.getBoolean(portletPreferences.getValue("showPortrait", "true"));
boolean showTitle = GetterUtil.getBoolean(portletPreferences.getValue("showTitle", "true"));
String url = portletPreferences.getValue("url", StringPool.BLANK);
String width = portletPreferences.getValue("width", "480");

String id = url.replaceAll(".*vimeo\\.com\\/([0-9]+).*", "$1");

String enableFullscreenBinary = enableFullscreen ? "1" : "0";
String playerColorHex = StringUtil.toLowerCase(playerColor.replaceAll("#", ""));
String presetSize = width + "x" + height;
String showBylineBinary = showByline ? "1" : "0";
String showPortraitBinary = showPortrait ? "1" : "0";
String showTitleBinary = showTitle ? "1" : "0";
%>

<%!
private static final String _SWF_URL = "://vimeo.com/moogaloop.swf";

private static final String _WATCH_URL = "://vimeo.com/";
%>