<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<liferay-util:include page="/html/portlet/message_boards/sidebar.jsp" />

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "categories");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);
%>

<c:choose>
	<c:when test='<%= (tabs1.equals("categories")) && (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) %>'>
		<h6><liferay-ui:message key="forums" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("my_posts") %>'>
		<h6><liferay-ui:message key="my-posts" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("my_subscriptions") %>'>
		<h6><liferay-ui:message key="my-subscriptions" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("recent_posts") %>'>
		<h6><liferay-ui:message key="recent-posts" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("statistics") %>'>
		<h6><liferay-ui:message key="statistics" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("banned_users") %>'>
		<h6><liferay-ui:message key="banned-users" /></h6>
	</c:when>
</c:choose>