<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<div class="tasks-entries-container">
	<ul class="tasks-entries">

		<%
		List<TasksEntry> taskEntries = TasksEntryLocalServiceUtil.getTasksEntries(0, 0, user.getUserId(), 0, TasksEntryConstants.STATUS_OPEN, new long[0], new long[0], 0, 10);

		for (TasksEntry tasksEntry : taskEntries) {
			String taskHREF = null;

			if (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) {
				LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(PortletKeys.TASKS, PortletRequest.RENDER_PHASE);

				liferayPortletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
				liferayPortletURL.setParameter("tasksEntryId", String.valueOf(tasksEntry.getTasksEntryId()));
				liferayPortletURL.setWindowState(LiferayWindowState.POP_UP);

				taskHREF = liferayPortletURL.toString();
			}

			String cssClass = "tasks-title";

			if (tasksEntry.getPriority() == 1) {
				cssClass = cssClass.concat(" high");
			}
			else if (tasksEntry.getPriority() == 2) {
				cssClass = cssClass.concat(" normal");
			}
			else {
				cssClass = cssClass.concat(" low");
			}
		%>

			<li class="<%= cssClass %>">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(taskHREF) %>">
						<a href="javascript:;" onClick="Liferay.Tasks.openTask('<%= taskHREF %>');"><%= HtmlUtil.escape(tasksEntry.getTitle()) %></a>
					</c:when>
					<c:otherwise>
						<span><%= HtmlUtil.escape(tasksEntry.getTitle()) %></span>
					</c:otherwise>
				</c:choose>
			</li>

		<%
		}
		%>

	</ul>

	<div class="view-all-tasks">

		<%
		long tasksPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), PortletKeys.TASKS);

		PortletURL portletURL = null;

		if (tasksPlid != 0) {
			portletURL = PortletURLFactoryUtil.create(request, PortletKeys.TASKS, tasksPlid, PortletRequest.RENDER_PHASE);
		}
		%>

		<c:if test="<%= portletURL != null %>">
			<a href="<%= portletURL %>"><liferay-ui:message key="view-all-tasks" /></a>
		</c:if>
	</div>
</div>

<aui:script>
	AUI().ready(
		function() {
			Liferay.Tasks.initUpcomingTasks(
				{
					upcomingTasksListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/upcoming_tasks/view.jsp" /></portlet:renderURL>'
				}
			);
		}
	);
</aui:script>