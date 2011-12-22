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
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
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

int annotations = GetterUtil.getInteger(preferences.getValue("annotations", StringPool.BLANK), 1);
boolean autoplay = GetterUtil.getBoolean(preferences.getValue("autoplay", "false"));
String borderColor = preferences.getValue("borderColor", StringPool.BLANK);
int closedCaptioning = GetterUtil.getInteger(preferences.getValue("closedCaptioning", StringPool.BLANK), 0);
boolean enableEnhancedGenieMenu = GetterUtil.getBoolean(preferences.getValue("enableEnhancedGenieMenu", "false"));
boolean enableFullscreen = GetterUtil.getBoolean(preferences.getValue("enableFullscreen", "false"));
boolean enableKeyboardControls = GetterUtil.getBoolean(preferences.getValue("enableKeyboardControls", "true"));
boolean enableRelatedVideos = GetterUtil.getBoolean(preferences.getValue("enableRelatedVideos", "false"));
boolean enableSearch = GetterUtil.getBoolean(preferences.getValue("enableSearch", "false"));
boolean hd = GetterUtil.getBoolean(preferences.getValue("hd", "false"));
String height = preferences.getValue("height", "360");
boolean loop = GetterUtil.getBoolean(preferences.getValue("loop", "false"));
String playerColor = preferences.getValue("playerColor", StringPool.BLANK);
boolean showInfo = GetterUtil.getBoolean(preferences.getValue("showInfo", "true"));
boolean showThickerBorder = GetterUtil.getBoolean(preferences.getValue("showThickerBorder", "false"));
boolean showThumbnail = GetterUtil.getBoolean(preferences.getValue("showThumbnail", "false"));
String startTime = preferences.getValue("startTime", StringPool.BLANK);
String url = preferences.getValue("url", StringPool.BLANK);
String width = preferences.getValue("width", "480");

String id = url.replaceAll("^.*?v=([a-zA-Z0-9_-]+).*$", "$1");
String imageURL = "http://img.youtube.com/vi/" + id + "/0.jpg";

String borderColorHex = "0x" + borderColor.replaceAll("#", "").replaceAll("^(.)(.)(.)$", "$1$1$2$2$3$3").toLowerCase();
String playerColorHex = "0x" + playerColor.replaceAll("#", "").replaceAll("^(.)(.)(.)$", "$1$1$2$2$3$3").toLowerCase();

String presetSize = width + "x" + height;
%>

<%!
private static final String _SWF_URL = "http://www.youtube.com/v/";

private static final String _WATCH_URL = "http://www.youtube.com/watch?v=";
%>