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

<%@ include file="/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "manage");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/app_manager/view.jsp");
portletURL.setParameter("toolbarItem", toolbarItem);
%>

<aui:nav-bar>
	<aui:nav>

		<%
		portletURL.setParameter("toolbarItem", "manage");
		%>

		<aui:nav-item href="<%= portletURL.toString() %>" label="manage" selected='<%= toolbarItem.equals("manage") %>' />

		<%
		portletURL.setParameter("toolbarItem", "install");
		%>

		<aui:nav-item href="<%= portletURL.toString() %>" label="install" selected='<%= toolbarItem.equals("install") %>' />

		<%
		portletURL.setParameter("toolbarItem", toolbarItem);
		%>

	</aui:nav>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= toolbarItem.equals("install") %>'>
		<%@ include file="/app_manager/install_apps.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/app_manager/manage.jspf" %>
	</c:otherwise>
</c:choose>