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

TasksEntry tasksEntry = null;

try {
	tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(tasksEntryId);
}
catch (NoSuchTasksEntryException nstee) {
}

long priority = BeanParamUtil.getLong(tasksEntry, request, "priority", TasksEntryConstants.PRIORITY_NORMAL);
long assigneeUserId = BeanParamUtil.getLong(tasksEntry, request, "assigneeUserId");

boolean neverDue = true;

if ((tasksEntry != null) && (tasksEntry.getDueDate() != null)) {
	neverDue = false;
}
%>

<portlet:actionURL name="updateTasksEntry" var="updateTasksEntryURL" >
	<portlet:param name="mvcPath" value="/tasks/edit_task.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTasksEntryURL %>" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveForm();" %>'>
	<aui:input name="tasksEntryId" type="hidden" value="<%= tasksEntryId %>" />
	<aui:input name="userId" type="hidden" value="<%= user.getUserId() %>" />
	<aui:input name="resolverUserId" type="hidden" value="<%= user.getUserId() %>" />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= tasksEntry %>" model="<%= TasksEntry.class %>" />

	<aui:fieldset>
		<aui:input cssClass="input-task-description" label="description" name="title" />

		<aui:select label="assignee" name="assigneeUserId">
			<c:choose>
				<c:when test="<%= group.isUser() %>">
					<aui:option label="<%= HtmlUtil.escape(user.getFullName()) %>" selected="<%= (assigneeUserId == 0) %>" value="<%= user.getUserId() %>" />

					<optgroup label="<liferay-ui:message key="contacts" />">
				</c:when>
				<c:otherwise>
					<aui:option label="" selected="<%= (assigneeUserId == 0) %>" value="0" />

					<aui:option label="<%= HtmlUtil.escape(user.getFullName()) %>" selected="<%= (assigneeUserId == user.getUserId()) %>" />

					<c:if test="<%= (tasksEntry != null) && (assigneeUserId > 0) && (assigneeUserId != user.getUserId()) %>">
						<aui:option label="<%= PortalUtil.getUserName(assigneeUserId, tasksEntry.getAssigneeFullName()) %>" selected="<%= true %>" value="<%= assigneeUserId %>" />
					</c:if>

					<optgroup label="<liferay-ui:message key="members" />">
				</c:otherwise>
			</c:choose>

			<%
			List<User> users = null;

			if (group.isUser()) {
				users = UserLocalServiceUtil.getSocialUsers(group.getClassPK(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ContactFirstNameComparator(true));
			}
			else {
				LinkedHashMap userParams = new LinkedHashMap();

				userParams.put("usersGroups", new Long(themeDisplay.getScopeGroupId()));

				users = UserLocalServiceUtil.search(company.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ContactFirstNameComparator(true));
			}

			for (User curUser : users) {
				long curUserId = curUser.getUserId();

				if (curUserId == user.getUserId()) {
					continue;
				}
			%>

				<aui:option label="<%= HtmlUtil.escape(curUser.getFullName()) %>" selected="<%= (assigneeUserId == curUserId) %>" value="<%= curUserId %>" />

			<%
			}
			%>

			</optgroup>
		</aui:select>

		<aui:select name="priority">
			<aui:option label="high" value="1" selected="<%= (priority == 1) %>" />
			<aui:option label="normal" value="2" selected="<%= (priority == 2) %>" />
			<aui:option label="low" value="3" selected="<%= (priority == 3) %>" />
		</aui:select>

		<aui:input disabled="<%= neverDue %>" label="due-date" name="dueDate" />

		<%
		String taglibNeverReviewOnClick = renderResponse.getNamespace() + "disableInputDate('dueDate', this.checked);";
		%>

		<aui:input name="neverDue" onClick="<%= taglibNeverReviewOnClick %>" type="checkbox" value="<%= neverDue %>" />

		<c:if test="<%= tasksEntry != null %>">
			<aui:select name="status">

				<%
				for (int curStatus : TasksEntryConstants.STATUSES) {
				%>

					<aui:option label="<%= TasksEntryConstants.getStatusLabel(curStatus) %>" selected="<%= tasksEntry.getStatus() == curStatus %>" />

				<%
				}
				%>

			</aui:select>
		</c:if>

		<aui:input name="tags" type="assetTags" />

		<aui:button-row cssClass="task-action">
			<aui:button type="submit" />

			<c:if test="<%= tasksEntryId > 0 %>">
				<portlet:renderURL var="viewURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="mvcPath" value="/tasks/view_task.jsp" />
					<portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" />
				</portlet:renderURL>

				<%
				String taglibOnClick = "Liferay.Tasks.openTask('" + viewURL.toString() + "');";
				%>

				<aui:button onClick="<%= taglibOnClick %>" type="cancel" />
			</c:if>

			<div class="task-action-right">
				<aui:button onClick="Liferay.Tasks.closePopup();" value="close" />
			</div>
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />getSuggestionsContent() {
		var content = document.<portlet:namespace />fm1.<portlet:namespace/>title.value + ' ';

		return content;
	}

	Liferay.provide(
		window,
		'<portlet:namespace />disableInputDate',
		function(date, checked) {
			var A = AUI();

			document.<portlet:namespace />fm1["<portlet:namespace />" + date + "Month"].disabled = checked;
			document.<portlet:namespace />fm1["<portlet:namespace />" + date + "Day"].disabled = checked;
			document.<portlet:namespace />fm1["<portlet:namespace />" + date + "Year"].disabled = checked;
			document.<portlet:namespace />fm1["<portlet:namespace />" + date + "Hour"].disabled = checked;
			document.<portlet:namespace />fm1["<portlet:namespace />" + date + "Minute"].disabled = checked;
			document.<portlet:namespace />fm1["<portlet:namespace />" + date + "AmPm"].disabled = checked;

			var calendarWidget = A.Widget.getByNode(document.<portlet:namespace />fm1["<portlet:namespace />" + date + "Month"]);

			if (calendarWidget) {
				calendarWidget.set('disabled', checked);
			}
		},
		['aui-base']
	);

	function <portlet:namespace />saveForm() {
		var A = AUI();

		var form = A.one(document.<portlet:namespace />fm1);

		var popup = Liferay.Tasks.getPopup();

		popup.io.set('form', {id: form.getDOM()});
		popup.io.set('uri', form.getAttribute('action'));

		popup.io.once(
			'success',
			function() {
				Liferay.Tasks.updateTaskList();
			}
		);

		popup.io.start();
	}
</aui:script>