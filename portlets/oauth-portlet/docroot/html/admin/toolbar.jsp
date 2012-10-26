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

<%@ include file="/html/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, OAuthConstants.TOOLBAR_ITEM, "view-all");
%>

<div class="lfr-portlet-toolbar">
	<portlet:renderURL var="viewAppsURL">
		<portlet:param name="jspPage" value="/html/admin/view.jsp" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button view-button <%= toolbarItem.equals("view-all") ? "current" : "" %>">
		<a href="<%= viewAppsURL %>"><liferay-ui:message key='<%= adminUser ? "view-all":"my-applications" %>' /></a>
	</span>
<c:if test='<%= permissionChecker.hasPermission(layout.getGroupId(), "com.liferay.portlet.oauth", layout.getGroupId(), ActionKeys.ADD_ENTRY) %>'>
	<portlet:renderURL var="addApplicationURL">
		<portlet:param name="jspPage" value="/html/admin/edit.jsp" />
		<portlet:param name="referer" value="<%= currentURL %>" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button add-button <%= toolbarItem.equals("add") ? "current" : "" %>">
		<a href="<%= addApplicationURL %>"><liferay-ui:message key="add" /></a>
	</span>
</c:if>
</div>