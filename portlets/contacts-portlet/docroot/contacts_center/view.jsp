<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
String topLink = ParamUtil.getString(request, "topLink", "contacts-home");
%>

<liferay-util:include page="/contacts_center/top_links.jsp" servletContext="<%= application %>" />

<c:choose>
	<c:when test='<%= topLink.equals("contacts-home") %>'>
		<liferay-util:include page="/contacts_center/view_contacts.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= topLink.equals("requests") %>'>
		<liferay-util:include page="/contacts_center/view_requests.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= topLink.equals("find-people") %>'>
		<liferay-util:include page="/contacts_center/view_find_people.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>