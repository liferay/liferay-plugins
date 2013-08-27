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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>

<%@ page import="com.liferay.oauthlogin.AccessTokenURLException" %><%@
page import="com.liferay.oauthlogin.AuthorizeURLException" %><%@
page import="com.liferay.oauthlogin.OAuthConnectionNameException" %><%@
page import="com.liferay.oauthlogin.RedirectURLException" %><%@
page import="com.liferay.oauthlogin.RequestTokenURLException" %><%@
page import="com.liferay.oauthlogin.SocialAccountIdURLException" %><%@
page import="com.liferay.oauthlogin.model.OAuthConnection" %><%@
page import="com.liferay.oauthlogin.service.OAuthConnectionLocalServiceUtil" %><%@
page import="com.liferay.oauthlogin.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.oauth.OAuthConstants" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %>

<portlet:defineObjects />

<theme:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>