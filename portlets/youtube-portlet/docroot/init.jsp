<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<portlet:defineObjects />

<%
int annotations = GetterUtil.getInteger(portletPreferences.getValue("annotations", StringPool.BLANK), 1);
boolean autoplay = GetterUtil.getBoolean(portletPreferences.getValue("autoplay", "false"));
String borderColor = portletPreferences.getValue("borderColor", StringPool.BLANK);
int closedCaptioning = GetterUtil.getInteger(portletPreferences.getValue("closedCaptioning", StringPool.BLANK), 0);
boolean enableEnhancedGenieMenu = GetterUtil.getBoolean(portletPreferences.getValue("enableEnhancedGenieMenu", "false"));
boolean enableFullscreen = GetterUtil.getBoolean(portletPreferences.getValue("enableFullscreen", "false"));
boolean enableKeyboardControls = GetterUtil.getBoolean(portletPreferences.getValue("enableKeyboardControls", "true"));
boolean enableRelatedVideos = GetterUtil.getBoolean(portletPreferences.getValue("enableRelatedVideos", "false"));
boolean enableSearch = GetterUtil.getBoolean(portletPreferences.getValue("enableSearch", "false"));
boolean hd = GetterUtil.getBoolean(portletPreferences.getValue("hd", "false"));
String height = portletPreferences.getValue("height", "360");
String inputTime = portletPreferences.getValue("inputTime", StringPool.BLANK);
boolean loop = GetterUtil.getBoolean(portletPreferences.getValue("loop", "false"));
String playerColor = portletPreferences.getValue("playerColor", StringPool.BLANK);
boolean showInfo = GetterUtil.getBoolean(portletPreferences.getValue("showInfo", "true"));
boolean showThickerBorder = GetterUtil.getBoolean(portletPreferences.getValue("showThickerBorder", "false"));
boolean showThumbnail = GetterUtil.getBoolean(portletPreferences.getValue("showThumbnail", "false"));
String startTime = portletPreferences.getValue("startTime", StringPool.BLANK);
String url = portletPreferences.getValue("url", StringPool.BLANK);
String width = portletPreferences.getValue("width", "480");

String id = url.replaceAll("^.*?v=([a-zA-Z0-9_-]+).*$", "$1");

String borderColorHex = "0x" + borderColor.replaceAll("#", "").replaceAll("^(.)(.)(.)$", "$1$1$2$2$3$3").toLowerCase();
String playerColorHex = "0x" + playerColor.replaceAll("#", "").replaceAll("^(.)(.)(.)$", "$1$1$2$2$3$3").toLowerCase();

String presetSize = width + "x" + height;

String imageURL = HttpUtil.getProtocol(request) + "://img.youtube.com/vi/" + id + "/0.jpg";
String swfURL = HttpUtil.getProtocol(request) + "://www.youtube.com/v/";
String watchURL = HttpUtil.getProtocol(request) + "://www.youtube.com/watch?v=";

if(Validator.isNotNull(inputTime)) {
	String minutes = "0";
	String seconds = "0";
	StringBuilder sb = new StringBuilder();
	for(int i =0;i < inputTime.length();i++) {
		if(inputTime.charAt(i) == ':') {
			minutes = sb.toString();
			sb.delete(0,i);
			continue;
		}
		sb.append(inputTime.charAt(i));
	}
	seconds = sb.toString();
	startTime = String.valueOf(Integer.parseInt(minutes)*60 + Integer.parseInt(seconds));
}
%>