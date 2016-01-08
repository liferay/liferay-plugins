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
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<portlet:defineObjects />

<%
boolean annotations = GetterUtil.getBoolean(portletPreferences.getValue("annotations", "true"));
boolean autoplay = GetterUtil.getBoolean(portletPreferences.getValue("autoplay", "false"));
boolean closedCaptioning = GetterUtil.getBoolean(portletPreferences.getValue("closedCaptioning", "false"));
boolean enableKeyboardControls = GetterUtil.getBoolean(portletPreferences.getValue("enableKeyboardControls", "true"));
String height = portletPreferences.getValue("height", "360");
boolean loop = GetterUtil.getBoolean(portletPreferences.getValue("loop", "false"));
boolean showThumbnail = GetterUtil.getBoolean(portletPreferences.getValue("showThumbnail", "false"));
String startTime = portletPreferences.getValue("startTime", StringPool.BLANK);
String url = portletPreferences.getValue("url", StringPool.BLANK);
String width = portletPreferences.getValue("width", "480");

String id = url.replaceAll("^.*?v=([a-zA-Z0-9_-]+).*$", "$1");

String presetSize = width + "x" + height;

String embedURL = HttpUtil.getProtocol(request) + "://www.youtube.com/embed/";
String imageURL = HttpUtil.getProtocol(request) + "://img.youtube.com/vi/" + id + "/0.jpg";
String watchURL = HttpUtil.getProtocol(request) + "://www.youtube.com/watch?v=";
%>