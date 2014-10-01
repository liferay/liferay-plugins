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

<%@ include file="/admin/init.jsp" %>

<%
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey");
%>

<portlet:renderURL var="importURL">
	<portlet:param name="mvcPath" value="/admin/import.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="parentKBFolderId" value="<%= String.valueOf(parentResourcePrimKey) %>" />
</portlet:renderURL>

<aui:nav-item
	href="<%= importURL %>"
	iconCssClass="icon-hdd"
	label="import"
/>