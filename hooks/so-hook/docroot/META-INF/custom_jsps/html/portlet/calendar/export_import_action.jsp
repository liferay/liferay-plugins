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

<%
String className = StringPool.BLANK;

if (tabs1.equals("day")) {
	className += " day-view";
}
%>

<form method="post" name="<portlet:namespace />fm1">

<div class="export-import-calendar<%= className %>">
	<input type="hidden" name="exportFileName" type="text" value="<%= layout.getGroup().getName() %>.ics">

	<c:if test="<%= CalendarPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_EVENT) %>">
		<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/calendar/view" /><portlet:param name="tabs1" value="export-import" /></portlet:renderURL>"><liferay-ui:message key="import" /></a>
	</c:if>

	<c:if test="<%= CalendarPermission.contains(permissionChecker, scopeGroupId, ActionKeys.EXPORT_ALL_EVENTS) %>">
		<a class="export-events" href="javascript:;" onClick="document.<portlet:namespace />fm1.action = '<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/calendar/export_events" /></portlet:actionURL>'; document.<portlet:namespace />fm1.submit();"><liferay-ui:message key="export" /></a>
	</c:if>

	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="com.liferay.portlet.calendar"
			modelResourceDescription="<%= portletDisplay.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
			var="permissionsURL"
		/>

		<a class="permissions" href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
	</c:if>
</div>

</form>