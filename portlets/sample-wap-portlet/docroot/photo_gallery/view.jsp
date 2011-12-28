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

<%@ include file="/init.jsp" %>

<%
WindowState windowState = renderRequest.getWindowState();

if (windowState.equals(WindowState.MAXIMIZED)) {
	int cur = ParamUtil.getInteger(request, "cur", 1);
%>

	<img alt="large" src="<%= request.getContextPath() %>/photo_gallery/photo_<%= cur %>_large.jpg" /><br />

	<%
	if (cur == 1) {
	%>

		<a href="<portlet:renderURL><portlet:param name="cur" value="2" /></portlet:renderURL>">Next</a><br />

	<%
	}
	else {
	%>

		<a href="<portlet:renderURL><portlet:param name="cur" value="1" /></portlet:renderURL>">Previous</a><br />

	<%
	}
	%>

<%
}
else {
%>

	<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" />">
	<img alt="thumbnail" src="<%= request.getContextPath() %>/photo_gallery/photo_1_thumbnail.jpg" />
	</a>

<%
}
%>