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

<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>

<%@ include file="/html/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, OAuthConstants.TOOLBAR_ITEM, OAuthConstants.TOOLBAR_ITEM_VIEW_ALL);
int myAppsCount = ParamUtil.getInteger(request, "myAppsCount", 0);
%>

<div class="lfr-portlet-toolbar">
	<portlet:renderURL var="viewAppsURL">
		<portlet:param name="jspPage" value="/html/authorization/view.jsp" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button view-button <%= OAuthConstants.TOOLBAR_ITEM_VIEW_ALL.equals(toolbarItem) ? "current" : "" %>">
		<a href="<%= viewAppsURL %>"><liferay-ui:message key="<%= adminUser ? OAuthConstants.TOOLBAR_ITEM_VIEW_ALL : OAuthConstants.TOOLBAR_ITEM_MY_AUTHORISATIONS %>" /></a>
	</span>
<c:if test="<%= myAppsCount > 0 %>">
	<portlet:renderURL var="myApplicationsURL">
		<portlet:param name="jspPage" value="/html/authorization/view.jsp" />
		<portlet:param name="referer" value="<%= currentURL %>" />
		<portlet:param name="toolbarItem" value="<%= OAuthConstants.TOOLBAR_ITEM_MY_APPS %>" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button view-button <%= OAuthConstants.TOOLBAR_ITEM_MY_APPS.equals(toolbarItem) ? "current" : "" %>">
		<a href="<%= myApplicationsURL %>"><liferay-ui:message key="<%= OAuthConstants.TOOLBAR_ITEM_MY_APPS %>" /></a>
	</span>
</c:if>
</div>