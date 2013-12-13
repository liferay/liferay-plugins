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

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "meetings");

portletURL.setParameter("tabs1", tabs1);
portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<liferay-ui:tabs
	names="meetings,servers"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("meetings") %>'>
		<%@ include file="/admin/meetings.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/admin/servers.jspf" %>
	</c:otherwise>
</c:choose>