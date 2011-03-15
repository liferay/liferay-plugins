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

long[] assetTagIds = ParamUtil.getLongValues(request, "assetTagIds");
long groupId = ParamUtil.getLong(request, "groupId", 0);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);

PortletURL taskListURL = renderResponse.createRenderURL();

taskListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

taskListURL.setParameter("jspPage", "/tasks/view_tasks.jsp");
taskListURL.setParameter("tabs1", tabs1);
taskListURL.setParameter("tabs2", tabs2);
%>

<liferay-ui:search-container
	emptyResultsMessage="no-tasks-were-found"
	headerNames="description,due, "
	iteratorURL="<%= portletURL %>"
>

	<%
	if (isCommunity) {
		groupId = group.getGroupId();
	}

	long assigneeUserId = 0;
	long reporterUserId = 0;

	if (tabs1.equals("assigned-to-me")) {
		assigneeUserId = user.getUserId();
	}
	else if (tabs1.equals("ive-created")) {
		reporterUserId = user.getUserId();
	}

	int status = TasksConstants.STATUS_ALL;

	if (tabs2.equals("open")) {
		status = TasksConstants.STATUS_OPEN;
	}
	%>

	<liferay-ui:search-container-results
		results="<%= TasksEntryLocalServiceUtil.getTasksEntries(groupId, 0, assigneeUserId, reporterUserId, status, assetTagIds, new long[0], searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= TasksEntryLocalServiceUtil.getTasksEntriesCount(groupId, 0, assigneeUserId, reporterUserId, status, assetTagIds, new long[0]) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.tms.model.TasksEntry"
		escapedModel="<%= true %>"
		keyProperty="tasksEntryId"
		modelVar="tasksEntry"
	>

		<%
		String rowHREF = null;

		if (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) {
			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			rowURL.setParameter("jspPage", "/tasks/view_task.jsp");
			rowURL.setParameter("redirect", currentURL);
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
						<a class="<%= cssClass %>" href="javascript:;" onClick="Liferay.TMS.Tasks.displayPopup('<%= rowHREF %>', 'TasksEntry');"><%= tasksEntry.getTitle() %></a>
					</c:when>
					<c:otherwise>
						<span class="<%= cssClass %>"><%= tasksEntry.getTitle() %></span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="result-data">
				<c:if test="<%= isUser %>">

					<%
					Group curGroup = null;

					try {
						curGroup = GroupLocalServiceUtil.getGroup(tasksEntry.getGroupId());
					}
					catch (NoSuchGroupException nsge) {
					}
					%>

					<c:if test="<%= (curGroup != null) && curGroup.isCommunity() %>">
						<span><liferay-ui:message key="community" />: <%= HtmlUtil.escape(curGroup.getDescriptiveName()) %></span>
					</c:if>
				</c:if>

				<c:if test='<%= !tabs1.equals("assigned-to-me") && (tasksEntry.getAssigneeUserId() > 0) %>'>
					<span><liferay-ui:message key="assignee" />: <%= HtmlUtil.escape(tasksEntry.getAssigneeFullName()) %></span>
				</c:if>

				<c:if test='<%= !tabs1.equals("ive-created") %>'>
					<span><liferay-ui:message key="reporter" />: <%= HtmlUtil.escape(tasksEntry.getReporterFullName()) %></span>
				</c:if>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="due"
		>

			<%
			if (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) {
				buffer.append("<div class=\"progress-wrapper\">");
				buffer.append("<div class=\"current\">");
				buffer.append("<div class=\"progress\" style=\"width:");

				int curStatus = tasksEntry.getStatus();

				if (curStatus == TasksConstants.STATUS_PERCENT_TWENTY) {
					buffer.append("20%");
				}
				else if (curStatus == TasksConstants.STATUS_PERCENT_FORTY) {
					buffer.append("40%");
				}
				else if (curStatus == TasksConstants.STATUS_PERCENT_SIXTY) {
					buffer.append("60%");
				}
				else if (curStatus == TasksConstants.STATUS_PERCENT_EIGHTY) {
					buffer.append("80%");
				}
				else if (curStatus == TasksConstants.STATUS_RESOLVED) {
					buffer.append("100%");
				}
				else {
					buffer.append("0%");
				}

				buffer.append("\">");
				buffer.append("<!-- -->");
				buffer.append("</div>");
				buffer.append("<div class=\"due-date\">");

				if (tasksEntry.getDueDate() != null) {
					buffer.append(dateFormatDateTime.format(tasksEntry.getDueDate()));
				}

				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("<div class=\"progress-picker aui-helper-hidden\">");
				buffer.append("<div class=\"new-progress\"><!-- --></div>");
				buffer.append("<div class=\"progress-indicator\"></div>");
				buffer.append("<div class=\"progress-selector");

				buffer.append("\">");

				for (int i = 2; i <= 6; i++) {
			%>

					<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="statusURL" name="updateTasksEntryStatus">
						<portlet:param name="redirect" value="<%= taskListURL.toString() %>" />
						<portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" />
						<portlet:param name="resolverUserId" value="<%= String.valueOf(user.getUserId()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(i) %>" />
					</portlet:actionURL>

			<%
					buffer.append("<a class=\"progress-");
					buffer.append((i - 1) * 20);
					buffer.append("\" href=\"javascript:;\" onClick=\"Liferay.TMS.Tasks.displayPopup('");
					buffer.append(statusURL);
					buffer.append("', 'Status');\">");
					buffer.append("<!-- -->");
					buffer.append("</a>");
				}

				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
			}
			else {
				if (tasksEntry.getDueDate() != null) {
					buffer.append(dateFormatDateTime.format(tasksEntry.getDueDate()));
				}
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name=" "
		>

			<%
			List<AssetTag> tags = AssetTagLocalServiceUtil.getTags(TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

			if (tags.size() > 0) {
				buffer.append("<div class=\"tags-wrapper\"><div class=\"icon\"><!-- --></div><div class=\"tags aui-helper-hidden\">");
			}

			Iterator itr = tags.iterator();

			while (itr.hasNext()) {
				AssetTag tag = (AssetTag)itr.next();

				buffer.append("<nobr>");
				buffer.append(tag.getName());
				buffer.append("</nobr>");

				if (itr.hasNext()) {
					buffer.append(", ");
				}
			}

			if (tags.size() > 0) {
				buffer.append("</div></div>");
			}
			%>

		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>