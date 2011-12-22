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
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);
String tabs2 = ParamUtil.getString(request, "tabs2", "open");

long tasksEntryId = ParamUtil.getLong(request, "tasksEntryId");

TasksEntry tasksEntry = null;

try {
	tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(tasksEntryId);
}
catch (NoSuchTasksEntryException nstee) {
}

long priority = BeanParamUtil.getLong(tasksEntry, request, "priority", TasksEntryConstants.PRIORITY_NORMAL);
long assigneeUserId = BeanParamUtil.getLong(tasksEntry, request, "assigneeUserId");

Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

boolean neverDue = true;

if (tasksEntry != null) {
	if (tasksEntry.getDueDate() != null) {
		dueDate.setTime(tasksEntry.getDueDate());

		neverDue = false;
	}
}

int status = BeanParamUtil.getInteger(tasksEntry, request, "status", TasksEntryConstants.STATUS_ALL);

long javaScriptLastModified = ServletContextUtil.getLastModified(application, "/html/js/", true);
%>

<script src="<%= HtmlUtil.escape(PortalUtil.getStaticResourceURL(request, themeDisplay.getPathJavaScript() + "/liferay/service.js", javaScriptLastModified)) %>" type="text/javascript"></script>

<form action="<portlet:actionURL name="updateTasksEntry" />" method="post" name="<portlet:namespace />fm1">
<input name="<portlet:namespace />tasksEntryId" type="hidden" value="<%= tasksEntryId %>" />
<input name="<portlet:namespace />userId" type="hidden" value="<%= user.getUserId() %>" />
<input name="<portlet:namespace />resolverUserId" type="hidden" value="<%= user.getUserId() %>" />

<liferay-ui:header title='<%= (tasksEntry == null) ? "add-task" : "update-task" %>' />

<table class="lfr-table">
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="description" />
	</td>
	<td>
		<liferay-ui:input-field cssClass="input-task-description" model="<%= TasksEntry.class %>" bean="<%= tasksEntry %>" field="title" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="assignee" />
	</td>
	<td>
		<select name="<portlet:namespace />assigneeUserId">
			<c:choose>
				<c:when test="<%= group.isUser() %>">
					<option <%= (assigneeUserId == user.getUserId()) ? "selected" : StringPool.BLANK %> value="<%= user.getUserId() %>"><%= HtmlUtil.escape(user.getFullName()) %></option>

					<optgroup label="<liferay-ui:message key="contacts" />">
				</c:when>
				<c:otherwise>
					<option <%= (assigneeUserId == 0) ? "selected" : StringPool.BLANK %> value="0"></option>

					<option <%= (assigneeUserId == user.getUserId()) ? "selected" : StringPool.BLANK %> value="<%= user.getUserId() %>"><%= HtmlUtil.escape(user.getFullName()) %></option>

					<c:if test="<%= (tasksEntry != null) && (assigneeUserId > 0) && (assigneeUserId != user.getUserId()) %>">
						<option selected value="<%= assigneeUserId %>"><%= PortalUtil.getUserName(assigneeUserId, tasksEntry.getAssigneeFullName()) %></option>
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

			long defaultUserId = themeDisplay.getDefaultUserId();

			for (User curUser : users) {
				long curUserId = curUser.getUserId();

				if ((curUserId == user.getUserId()) || (assigneeUserId == curUserId)) {
					continue;
				}
			%>

				<option <%= (assigneeUserId == curUserId) ? "selected" : StringPool.BLANK %> value="<%= curUserId %>"><%= HtmlUtil.escape(curUser.getFullName()) %></option>

			<%
			}
			%>

			</optgroup>
		</select>
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="priority" />
	</td>
	<td>
		<select name="<portlet:namespace />priority">
			<option value="1" <%= (priority == 1) ? "selected" : StringPool.BLANK %>><liferay-ui:message key="high" /></option>
			<option value="2" <%= (priority == 2) ? "selected" : StringPool.BLANK %>><liferay-ui:message key="normal" /></option>
			<option value="3" <%= (priority == 3) ? "selected" : StringPool.BLANK %>><liferay-ui:message key="low" /></option>
		</select>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="due-date" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= TasksEntry.class %>" bean="<%= tasksEntry %>" field="dueDate" defaultValue="<%= dueDate %>" disabled="<%= neverDue %>" />

		<div style="clear: both;">

			<%
			String taglibNeverReviewOnClick = renderResponse.getNamespace() + "disableInputDate('dueDate', this.checked);";
			%>

			<aui:input inlineLabel="left" label="never-due" name="neverDue" value="<%= neverDue %>" onClick="<%= taglibNeverReviewOnClick %>" type="checkbox" />
		</div>
	</td>
</tr>

<c:if test="<%= tasksEntry != null %>">
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="status" />
		</td>
		<td>
			<select name="<portlet:namespace />status">

				<%
				for (int curStatus : TasksEntryConstants.STATUSES) {
				%>

					<option value="<%= curStatus %>" <%= (status == curStatus) ? "selected" : StringPool.BLANK %>><liferay-ui:message key="<%= TasksEntryConstants.getStatusLabel(curStatus) %>" /></option>

				<%
				}
				%>

			</select>
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

		<%
		long classPK = 0;

		if (tasksEntry != null) {
			classPK = tasksEntry.getTasksEntryId();
		}
		%>

		<liferay-ui:asset-tags-selector
			className="<%= TasksEntry.class.getName() %>"
			classPK="<%= classPK %>"
			contentCallback='<%= renderResponse.getNamespace() + "getSuggestionsContent" %>'
		/>

	</td>
</tr>
</table>

<div class="task-action">
	<input id="<portlet:namespace />submit" type="submit" value="<liferay-ui:message key="save" />" />

	<c:if test="<%= tasksEntryId > 0 %>">
		<input type="button" value="<liferay-ui:message key="cancel" />" onClick="Liferay.Tasks.openTask('<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="jspPage" value="/view_task.jsp" /><portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" /></portlet:renderURL>');" />
	</c:if>

	<div class="task-action-right">
		<input type="button" value="<liferay-ui:message key="close" />" onClick="Liferay.Tasks.closePopup();" />
	</div>
</div>

</form>

<aui:script>
	function <portlet:namespace />getSuggestionsContent() {
		var content = document.<portlet:namespace />fm1.<portlet:namespace/>title.value;

		return content;
	}

	function <portlet:namespace />disableInputDate(date, checked) {
		eval("document.<portlet:namespace />fm1.<portlet:namespace />" + date + "Month.disabled = " + checked + ";");
		eval("document.<portlet:namespace />fm1.<portlet:namespace />" + date + "Day.disabled = " + checked + ";");
		eval("document.<portlet:namespace />fm1.<portlet:namespace />" + date + "Year.disabled = " + checked + ";");
		eval("document.<portlet:namespace />fm1.<portlet:namespace />" + date + "Hour.disabled = " + checked + ";");
		eval("document.<portlet:namespace />fm1.<portlet:namespace />" + date + "Minute.disabled = " + checked + ";");
		eval("document.<portlet:namespace />fm1.<portlet:namespace />" + date + "AmPm.disabled = " + checked + ";");
	}
</aui:script>

<aui:script use="aui-base">
	form = A.one(document.<portlet:namespace />fm1);

	form.on(
		'submit',
		function(event) {
			event.preventDefault();

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
	);
</aui:script>