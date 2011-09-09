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
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);
String tabs2 = ParamUtil.getString(request, "tabs2", "open");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
%>

<%@ include file="/tabs1.jspf" %>

<div class="control-wrapper">
	<c:if test="<%= TasksPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.ADD_ENTRY) %>">
		<a class="add-task" href="javascript:;" onClick="Liferay.Tasks.displayPopup('<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="jspPage" value="/edit_task.jsp" /><portlet:param name="tabs1" value="<%= tabs1 %>" /><portlet:param name="tabs2" value="<%= tabs2 %>" /></portlet:renderURL>', 'Tasks');"><liferay-ui:message key="add-task" /></a>
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

<div class="filter-wrapper aui-helper-hidden">
	<%@ include file="/view_tasks_filter.jspf" %>
</div>

<div class="list-wrapper">
	<liferay-util:include page="/view_tasks.jsp" servletContext="<%= application %>" />
</div>

<div class="tasks-options">
	<table>
	<tr>
		<td>
			<input type="checkbox" name="all-tasks" onclick="Liferay.Tasks.updateTaskList(null, this.checked);" <%= (tabs2.equals("all") ? "checked" : StringPool.BLANK) %>/>
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
					currentTab: '<%= tabs1 %>',
					taskListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="jspPage" value="/view_tasks.jsp" /></portlet:renderURL>'
				}
			);
		}
	);
</aui:script>