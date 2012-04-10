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
long tasksEntryId = ParamUtil.getLong(request, "tasksEntryId");

TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(tasksEntryId);

tasksEntry = tasksEntry.toEscapedModel();

Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

boolean neverDue = true;
%>

<liferay-ui:header title="<%= HtmlUtil.unescape(tasksEntry.getTitle()) %>" />

<div class="task-data-container">
	<c:if test="<%= tasksEntry.getAssigneeUserId() > 0 %>">
		<div class="task-data assignee">
			<liferay-ui:message arguments="<%= PortalUtil.getUserName(tasksEntry.getAssigneeUserId(), tasksEntry.getAssigneeFullName(), request) %>" key="assigned-to-x" />
		</div>
	</c:if>

	<div class="task-data reporter">
		<liferay-ui:message arguments="<%= PortalUtil.getUserName(tasksEntry.getUserId(), tasksEntry.getReporterFullName(), request) %>" key="created-by-x" />
	</div>

	<div class="task-data last modified-date">
		<liferay-ui:message arguments="<%= dateFormatDateTime.format(tasksEntry.getModifiedDate()) %>" key="modified-on-x" />
	</div>
</div>

<table class="task-data-table lfr-table">
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="status" />
	</td>
	<td>
		<div class="task-data status">
			<liferay-ui:message key="<%= tasksEntry.getStatusLabel() %>" />
		</div>
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="priority" />
	</td>
	<td>
		<div class="task-data <%= tasksEntry.getPriorityLabel() %>">
			<liferay-ui:message key="<%= tasksEntry.getPriorityLabel() %>" />
		</div>
	</td>
</tr>

<c:if test="<%= tasksEntry.getDueDate() != null %>">
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="due-date" />
		</td>
		<td>
			<div class="task-data due-date">
				<%= dateFormatDateTime.format(tasksEntry.getDueDate()) %>
			</div>
		</td>
	</tr>
</c:if>

<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="tags" />
	</td>
	<td>
		<liferay-ui:asset-tags-summary
			className="<%= TasksEntry.class.getName() %>"
			classPK="<%= tasksEntry.getTasksEntryId() %>"
		/>
	</td>
</tr>
</table>

<div class="task-action">
	<c:if test="<%= TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE) %>">

		<%
		boolean resolved = (tasksEntry.getStatus() == TasksEntryConstants.STATUS_RESOLVED);
		%>

		<input type="button" value="<liferay-ui:message key='<%= resolved ? "reopen" : "resolve" %>' />" onClick="<portlet:namespace />updateStatus(<%= resolved ? TasksEntryConstants.STATUS_REOPENED : TasksEntryConstants.STATUS_RESOLVED %>)" />

		<span class="task-action-spacer">
			<input type="button" value="<liferay-ui:message key="edit" />" onClick="Liferay.Tasks.displayPopup('<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/tasks/edit_task.jsp" /><portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" /></portlet:renderURL>', '<liferay-ui:message key="update-task" />');" />

			<input type="button" value="<liferay-ui:message key="delete" />" onClick="if(confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')){<portlet:namespace />deleteTask()}" />
		</span>

		<aui:script use="aui-io">
			Liferay.provide(
				window,
				'<portlet:namespace />deleteTask',
				function() {
					A.io.request(
						'<portlet:actionURL name="deleteTasksEntry" />',
						{
							after: {
								success: function() {
									Liferay.Tasks.updateTaskList();

									Liferay.Tasks.closePopup();
								}
							},
							data: {
								tasksEntryId: <%= tasksEntry.getTasksEntryId() %>
							}
						}
					);
				}
			);

			Liferay.provide(
				window,
				'<portlet:namespace />updateStatus',
				function(status) {
					A.io.request(
						'<portlet:actionURL name="updateTasksEntryStatus" />',
						{
							after: {
								success: function() {
									Liferay.Tasks.updateTaskList();

									Liferay.Tasks.displayPopup('<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/tasks/view_task.jsp" /><portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" /></portlet:renderURL>', '<liferay-ui:message key="update-task" />');
								}
							},
							data: {
								tasksEntryId: <%= tasksEntry.getTasksEntryId() %>,
								resolverUserId: <%= user.getUserId() %>,
								status: status
							}
						}
					);
				}
			);
		</aui:script>
	</c:if>

	<div class="task-action-right">
		<input type="button" value="<liferay-ui:message key="close" />" onClick="Liferay.Tasks.closePopup();" />
	</div>
</div>

<liferay-ui:tabs names="comments" />

<%@ include file="/tasks/view_comments.jsp" %>