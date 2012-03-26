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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CalendarResource calendarResource = (CalendarResource)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, calendarResource, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/edit_calendar_resource.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="calendarResourceId" value="<%= String.valueOf(calendarResource.getCalendarResourceId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, calendarResource, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= CalendarResource.class.getName() %>"
			modelResourceDescription="<%= calendarResource.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(calendarResource.getCalendarResourceId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, calendarResource, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteCalendarResource" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="calendarResourceId" value="<%= String.valueOf(calendarResource.getCalendarResourceId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>