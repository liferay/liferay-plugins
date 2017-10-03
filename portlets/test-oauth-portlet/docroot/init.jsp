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
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.testoauth.oauth.LiferayOAuthJSONWSClient" %><%@
page import="com.liferay.testoauth.oauth.OAuthServiceHandler" %><%@
page import="com.liferay.testoauth.oauth.OAuthUtil" %><%@
page import="com.liferay.testoauth.util.PortletPropsValues" %>

<%@ page import="org.scribe.model.Token" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String accessSecret = portletPreferences.getValue("accessSecret", StringPool.BLANK);
String accessToken = portletPreferences.getValue("accessToken", StringPool.BLANK);
String accessURI = portletPreferences.getValue("accessURI", PortletPropsValues.OAUTH_ACCESS_TOKEN_URI);
String authorizeURI = portletPreferences.getValue("authorizeURI", PortletPropsValues.OAUTH_AUTHORIZE_URI);
String hostName = portletPreferences.getValue("hostName", PortletPropsValues.OAUTH_HOST_NAME);
String hostPort = portletPreferences.getValue("hostPort", PortletPropsValues.OAUTH_HOST_PORT);
String key = portletPreferences.getValue("key", StringPool.BLANK);
String requestURI = portletPreferences.getValue("requestURI", PortletPropsValues.OAUTH_REQUEST_TOKEN_URI);
String secret = portletPreferences.getValue("secret", StringPool.BLANK);
boolean useServerProvidedCallbackUrl = GetterUtil.getBoolean(portletPreferences.getValue("useServerProvidedCallbackUrl", null));
%>