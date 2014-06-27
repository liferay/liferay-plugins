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
long mbCategoryId = ParamUtil.getLong(request, "mbCategoryId");

portletURL = (PortletURL)request.getAttribute("manage_subscriptions.jsp-portletURL");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User user2 = (User)row.getObject();
%>

<c:choose>
	<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user2.getCompanyId(), user2.getUserId(), MBCategory.class.getName(), mbCategoryId) %>">
		<portlet:actionURL name="unsubscribeUsers" var="unsubscribeUsersURL">
			<portlet:param name="redirect" value="<%= String.valueOf(portletURL) %>" />
			<portlet:param name="mbCategoryId" value="<%= String.valueOf(mbCategoryId) %>" />
			<portlet:param name="userIds" value="<%= String.valueOf(user2.getUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			iconCssClass="icon-remove-sign"
			label="<%= true %>"
			message="unsubscribe"
			url="<%= unsubscribeUsersURL %>"
		/>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="subscribeUsers" var="subscribeUsersURL">
			<portlet:param name="redirect" value="<%= String.valueOf(portletURL) %>" />
			<portlet:param name="mbCategoryId" value="<%= String.valueOf(mbCategoryId) %>" />
			<portlet:param name="userIds" value="<%= String.valueOf(user2.getUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			iconCssClass="icon-ok-sign"
			label="<%= true %>"
			message="subscribe"
			url="<%= subscribeUsersURL %>"
		/>
	</c:otherwise>
</c:choose>