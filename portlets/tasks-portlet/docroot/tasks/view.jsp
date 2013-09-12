<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);
String tabs2 = ParamUtil.getString(request, "tabs2", "open");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
%>

<%@ include file="/tasks/tabs1.jspf" %>

<div class="control-wrapper">
	<c:if test="<%= TasksPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.ADD_ENTRY) %>">
		<a class="add-task" href="javascript:;" onClick="Liferay.Tasks.displayPopup('<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/tasks/edit_task.jsp" /></portlet:renderURL>', '<liferay-ui:message key="add-task" />');"><liferay-ui:message key="add-task" /></a>
	</c:if>

	<c:if test="<%= TasksPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="com.liferay.tasks"
			modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
			resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
			var="permissionsURL"
		/>

		<a class="permission-tasks" href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
	</c:if>

	<a class="filter-tasks" href="javascript:;" onClick="Liferay.Tasks.toggleTasksFilter();"><liferay-ui:message key="filter" /></a>

	<div style="clear: both;"><!-- --></div>
</div>

<div class="filter-wrapper hide">
	<%@ include file="/tasks/view_tasks_filter.jspf" %>
</div>

<div class="list-wrapper">
	<liferay-util:include page="/tasks/view_tasks.jsp" servletContext="<%= application %>" />
</div>

<div class="tasks-options">
	<table>
	<tr>
		<td>
			<input name="all-tasks" onclick="Liferay.Tasks.updateTaskList(null, this.checked);" type="checkbox" <%= (tabs2.equals("all") ? "checked" : StringPool.BLANK) %>/>
		</td>
		<td>
			<liferay-ui:message key="show-completed-tasks" />
		</td>
	</tr>
	</table>
</div>

<aui:script>
	AUI().ready(
		function() {
			Liferay.Tasks.init(
				{
					currentTab: '<%= HtmlUtil.escape(tabs1) %>',
					namespace: '<portlet:namespace />',
					taskListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/tasks/view_tasks.jsp" /></portlet:renderURL>'
				}
			);
		}
	);
</aui:script>