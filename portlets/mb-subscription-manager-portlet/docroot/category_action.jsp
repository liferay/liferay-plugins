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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBCategory mbCategory = (MBCategory)row.getObject();
%>

<liferay-ui:icon-menu>
	<liferay-portlet:renderURL plid="<%= PortalUtil.getPlidFromPortletId(mbCategory.getGroupId(), PortletKeys.MESSAGE_BOARDS) %>" portletName="<%= PortletKeys.MESSAGE_BOARDS %>" var="viewURL">
		<portlet:param name="struts_action" value="/message_boards/view" />
		<portlet:param name="mbCategoryId" value="<%= String.valueOf(mbCategory.getCategoryId()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon image="view" message="view" target="_blank" url="<%= viewURL %>" />

	<liferay-portlet:renderURL var="editSubscriptionsURL">
		<portlet:param name="mvcPath" value="/manage_subscriptions.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="mbCategoryId" value="<%= String.valueOf(mbCategory.getCategoryId()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		message="manage-subscriptions"
		method="get"
		url="<%= editSubscriptionsURL %>"
	/>
</liferay-ui:icon-menu>