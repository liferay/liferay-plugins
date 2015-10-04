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

<%
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);
String tabs2 = ParamUtil.getString(request, "tabs2", "open");

long[] assetTagIds = StringUtil.split(ParamUtil.getString(request, "assetTagIds"), 0L);

long groupId = ParamUtil.getLong(request, "groupId");

if (group.isRegularSite()) {
	groupId = group.getGroupId();
}

long userId = 0;

if (tabs1.equals("i-have-created")) {
	userId = user.getUserId();
}

long assigneeUserId = 0;

if (tabs1.equals("assigned-to-me")) {
	assigneeUserId = user.getUserId();
}

int status = TasksEntryConstants.STATUS_ALL;

if (tabs2.equals("open")) {
	status = TasksEntryConstants.STATUS_OPEN;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);

PortletURL taskListURL = renderResponse.createRenderURL();

taskListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

taskListURL.setParameter("mvcPath", "/tasks/view_tasks.jsp");
taskListURL.setParameter("tabs1", tabs1);
taskListURL.setParameter("tabs2", tabs2);
%>

<liferay-ui:search-container
	emptyResultsMessage="no-tasks-were-found"
	headerNames="description,due, "
	iteratorURL="<%= portletURL %>"
	total= "<%= TasksEntryLocalServiceUtil.getTasksEntriesCount(groupId, userId, 0, assigneeUserId, status, assetTagIds, new long[0]) %>"
>
	<liferay-ui:search-container-results
		results="<%= TasksEntryLocalServiceUtil.getTasksEntries(groupId, userId, 0, assigneeUserId, status, assetTagIds, new long[0], searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.tasks.model.TasksEntry"
		escapedModel="<%= true %>"
		keyProperty="tasksEntryId"
		modelVar="tasksEntry"
	>

		<%
		String rowHREF = null;

		if (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) {
			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setWindowState(LiferayWindowState.POP_UP);

			rowURL.setParameter("mvcPath", "/tasks/view_task.jsp");
			rowURL.setParameter("tasksEntryId", String.valueOf(tasksEntry.getTasksEntryId()));

			rowHREF = rowURL.toString();
		}
		%>

		<liferay-ui:search-container-column-text
			name="description"
		>

			<%
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

			<div class="result-title">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(rowHREF) %>">
						<a class="<%= cssClass %>" href="javascript:;" onClick="Liferay.Tasks.openTask('<%= rowHREF %>', <%= tasksEntry.getTasksEntryId() %>);">
							<i class="icon-circle"></i>

							<%= tasksEntry.getTitle() %>
						</a>
					</c:when>
					<c:otherwise>
						<span class="<%= cssClass %>">
							<i class="icon-circle"></i>

							<%= tasksEntry.getTitle() %>
						</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="result-data">
				<c:if test="<%= group.isUser() %>">

					<%
					Group curGroup = GroupLocalServiceUtil.fetchGroup(tasksEntry.getGroupId());
					%>

					<c:if test="<%= (curGroup != null) && curGroup.isRegularSite() %>">
						<span><liferay-ui:message key="site" />: <%= HtmlUtil.escape(curGroup.getDescriptiveName(locale)) %></span>
					</c:if>
				</c:if>

				<c:if test='<%= !tabs1.equals("assigned-to-me") %>'>
					<span><liferay-ui:message key="assignee" />:
						<c:choose>
							<c:when test="<%= tasksEntry.getAssigneeUserId() > 0 %>">
								<%= HtmlUtil.escape(tasksEntry.getAssigneeFullName()) %>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="unassigned" />
							</c:otherwise>
						</c:choose>
					</span>
				</c:if>

				<c:if test='<%= !tabs1.equals("i-have-created") %>'>
					<span><liferay-ui:message key="reporter" />: <%= HtmlUtil.escape(tasksEntry.getReporterFullName()) %></span>
				</c:if>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="due"
		>
			<c:choose>
				<c:when test="<%= (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) %>">

					<%
					int curStatus = tasksEntry.getStatus();

					int width = 0;

					if (curStatus == TasksEntryConstants.STATUS_PERCENT_TWENTY) {
						width = 20;
					}
					else if (curStatus == TasksEntryConstants.STATUS_PERCENT_FORTY) {
						width = 40;
					}
					else if (curStatus == TasksEntryConstants.STATUS_PERCENT_SIXTY) {
						width = 60;
					}
					else if (curStatus == TasksEntryConstants.STATUS_PERCENT_EIGHTY) {
						width = 80;
					}
					else if (curStatus == TasksEntryConstants.STATUS_RESOLVED) {
						width = 100;
					}
					%>

					<div class="progress-wrapper">
						<div class="current">
							<div class="progress" style="width:<%= width %>%">
								<!-- -->
							</div>

							<c:if test="<%= tasksEntry.getDueDate() != null %>">
								<div class="due-date <%= (DateUtil.compareTo(new Date(), tasksEntry.getDueDate()) >= 0) ? "past-due" : StringPool.BLANK %>">
									<%= dateFormatDateTime.format(tasksEntry.getDueDate()) %>
								</div>
							</c:if>
						</div>

						<div class="hide progress-picker">
							<div class="new-progress"><!-- --></div>
							<div class="progress-indicator"></div>
							<div class="progress-selector">

								<%
								for (int i = TasksEntryConstants.STATUS_PERCENT_TWENTY; i <= TasksEntryConstants.STATUS_RESOLVED; i++) {
								%>

									<portlet:actionURL name="updateTasksEntryStatus" var="statusURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
										<portlet:param name="redirect" value="<%= taskListURL.toString() %>" />
										<portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" />
										<portlet:param name="resolverUserId" value="<%= String.valueOf(user.getUserId()) %>" />
										<portlet:param name="status" value="<%= String.valueOf(i) %>" />
									</portlet:actionURL>

									<a class="progress-<%= (i - 1) * 20 %>" href="<%= statusURL %>"><!-- --></a>

								<%
								}
								%>

							</div>
						</div>
					</div>
				</c:when>
				<c:when test="<%= tasksEntry.getDueDate() != null %>">
					<%= dateFormatDateTime.format(tasksEntry.getDueDate()) %>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="<%= StringPool.BLANK %>"
		>

			<%
			List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(TasksEntry.class.getName(), tasksEntry.getTasksEntryId());
			%>

			<c:if test="<%= !assetTags.isEmpty() %>">
				<div class="tags-wrapper">
					<i class="icon-tag"></i>

					<div class="hide tags">
						<%= ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR) %>
					</div>
				</div>
			</c:if>

		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>