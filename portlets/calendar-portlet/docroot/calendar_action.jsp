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

Calendar calendar = (Calendar)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= CalendarPermission.contains(permissionChecker, calendar, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/edit_calendar.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="calendarId" value="<%= String.valueOf(calendar.getCalendarId()) %>" />
			<portlet:param name="calendarResourceId" value="<%= String.valueOf(calendar.getCalendarResourceId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW) %>">
		<liferay-portlet:resourceURL id="exportCalendar" var="exportURL">
			<portlet:param name="calendarId" value="<%= String.valueOf(calendar.getCalendarId()) %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:icon
			image="export"
			url="<%= exportURL %>"
		/>
	</c:if>

	<c:if test="<%= CalendarPermission.contains(permissionChecker, calendar, ActionKeys.UPDATE) %>">
		<liferay-portlet:resourceURL id="importCalendar" var="importURL">
			<portlet:param name="calendarId" value="<%= String.valueOf(calendar.getCalendarId()) %>" />
		</liferay-portlet:resourceURL>

		<%
		StringBundler sb = new StringBundler(6);

		sb.append("javascript:");
		sb.append(renderResponse.getNamespace());
		sb.append("importCalendar");
		sb.append("('");
		sb.append(importURL);
		sb.append("');");
		%>

		<liferay-ui:icon
			image="download"
			message="import"
			url="<%= sb.toString() %>"
		/>
	</c:if>

	<c:if test="<%= CalendarPermission.contains(permissionChecker, calendar, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Calendar.class.getName() %>"
			modelResourceDescription="<%= calendar.getName(locale) %>"
			resourceGroupId="<%= calendar.getGroupId() %>"
			resourcePrimKey="<%= String.valueOf(calendar.getCalendarId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= enableRSS %>">
		<liferay-portlet:resourceURL id="calendarBookingsRSS" varImpl="calendarRSSURL">
			<portlet:param name="calendarId" value="<%= String.valueOf(calendar.getCalendarId()) %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:rss
			resourceURL="<%= calendarRSSURL %>"
		/>
	</c:if>

	<c:if test="<%= CalendarPermission.contains(permissionChecker, calendar, ActionKeys.DELETE) && !calendar.isDefaultCalendar() %>">
		<portlet:actionURL name="deleteCalendar" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="calendarId" value="<%= String.valueOf(calendar.getCalendarId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>