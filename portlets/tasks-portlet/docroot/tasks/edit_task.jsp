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
long tasksEntryId = ParamUtil.getLong(request, "tasksEntryId");

TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(tasksEntryId);

long priority = BeanParamUtil.getLong(tasksEntry, request, "priority", TasksEntryConstants.PRIORITY_NORMAL);
long assigneeUserId = BeanParamUtil.getLong(tasksEntry, request, "assigneeUserId");

boolean addDueDate = false;
String dueDateHideClass = "hide";
String dueDateToggleText = LanguageUtil.get(request, "add-due-date");

if ((tasksEntry != null) && (tasksEntry.getDueDate() != null)) {
	addDueDate = true;
	dueDateHideClass = StringPool.BLANK;
	dueDateToggleText = LanguageUtil.get(request, "remove-due-date");
}

String dueDateControlGroupCssClass = renderResponse.getNamespace() + "dueDateControlGroup";

String dueDateWrapperCssClass = dueDateControlGroupCssClass + StringPool.SPACE + dueDateHideClass;
%>

<c:choose>
	<c:when test="<%= (tasksEntry == null) && (tasksEntryId > 0) %>">
		<span class="alert alert-danger"><liferay-ui:message key="task-could-not-be-found" /></span>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="updateTasksEntry" var="updateTasksEntryURL" />

		<aui:form action="<%= updateTasksEntryURL %>" method="post" name="fm1">
			<aui:input name="mvcPath" type="hidden" value="/tasks/edit_task.jsp" />
			<aui:input name="tasksEntryId" type="hidden" value="<%= tasksEntryId %>" />
			<aui:input name="userId" type="hidden" value="<%= user.getUserId() %>" />
			<aui:input name="resolverUserId" type="hidden" value="<%= user.getUserId() %>" />

			<liferay-ui:asset-tags-error />

			<aui:model-context bean="<%= tasksEntry %>" model="<%= TasksEntry.class %>" />

			<aui:fieldset>
				<aui:input cssClass="input-task-description" label="description" name="title">
					<aui:validator name="required" />
				</aui:input>

				<aui:select label="assignee" name="assigneeUserId">
					<c:choose>
						<c:when test="<%= group.isUser() %>">
							<aui:option label="<%= HtmlUtil.escape(user.getFullName()) %>" selected="<%= (assigneeUserId == 0) %>" value="<%= user.getUserId() %>" />

							<optgroup label="<liferay-ui:message key="contacts" />">
						</c:when>
						<c:otherwise>
							<aui:option label="unassigned" selected="<%= (assigneeUserId == 0) %>" value="0" />

							<aui:option label="<%= HtmlUtil.escape(user.getFullName()) %>" selected="<%= (assigneeUserId == user.getUserId()) %>" value="<%= user.getUserId() %>" />

							<c:if test="<%= (tasksEntry != null) && (assigneeUserId > 0) && (assigneeUserId != user.getUserId()) %>">
								<aui:option label="<%= PortalUtil.getUserName(assigneeUserId, tasksEntry.getAssigneeFullName()) %>" selected="<%= true %>" />
							</c:if>

							<optgroup label="<liferay-ui:message key="members" />">
						</c:otherwise>
					</c:choose>

					<%
					List<User> users = null;

					if (group.isUser()) {
						users = UserLocalServiceUtil.getSocialUsers(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));
					}
					else {
						LinkedHashMap userParams = new LinkedHashMap();

						userParams.put("inherit", Boolean.TRUE);
						userParams.put("usersGroups", Long.valueOf(themeDisplay.getScopeGroupId()));

						users = UserLocalServiceUtil.search(company.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));
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

				<aui:select name="priority" value="<%= priority %>">
					<aui:option label="high" value="1" />
					<aui:option label="normal" value="2" />
					<aui:option label="low" value="3" />
				</aui:select>

				<%
				String taglibAddDueDateOnClick = renderResponse.getNamespace() + "displayInputDate();";
				%>

				<label class="due-date-label field-label"><%= LanguageUtil.get(request, "due-date") %></label>

				<a class="due-date-toggle field-content" href="javascript:;" id="toggleDueDate" onClick="<%= taglibAddDueDateOnClick %>"><%= dueDateToggleText %></a>

				<aui:input id="addDueDate" name="addDueDate" type="hidden" value="<%= addDueDate %>" />

				<aui:input label="" name="dueDate" wrapperCssClass="<%= dueDateWrapperCssClass %>" />

				<c:if test="<%= tasksEntry != null %>">
					<aui:select name="status">

						<%
						for (int curStatus : TasksEntryConstants.STATUSES) {
						%>

							<aui:option label="<%= TasksEntryConstants.getStatusLabel(curStatus) %>" selected="<%= tasksEntry.getStatus() == curStatus %>" value="<%= curStatus %>" />

						<%
						}
						%>

					</aui:select>
				</c:if>

				<aui:input name="tags" type="assetTags" />

				<aui:button-row>
					<aui:button type="submit" />

					<c:if test="<%= tasksEntryId > 0 %>">
						<portlet:renderURL var="viewURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="mvcPath" value="/tasks/view_task.jsp" />
							<portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" />
						</portlet:renderURL>

						<aui:button onClick="<%= viewURL %>" value="cancel" />
					</c:if>
				</aui:button-row>
			</aui:fieldset>
		</aui:form>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />getSuggestionsContent() {
		var content = document.<portlet:namespace />fm1.<portlet:namespace />title.value + ' ';

		return content;
	}

	Liferay.provide(
		window,
		'<portlet:namespace />displayInputDate',
		function() {
			var A = AUI();

			var checkbox = A.one('#<portlet:namespace />addDueDate');

			if (checkbox) {
				var checkboxValue = checkbox.get('value');
				var dueDateToggle = A.one('#toggleDueDate');

				if (checkboxValue == 'true') {
					checkbox.set('value', false);
					dueDateToggle.html('<%= LanguageUtil.get(request, "add-due-date") %>');
				}
				else {
					checkbox.set('value', true);
					dueDateToggle.html('<%= LanguageUtil.get(request, "remove-due-date") %>');
				}
			}

			var inputs = A.one('.<%= dueDateControlGroupCssClass %>');

			if (inputs) {
				inputs.toggleClass('hide');
			}
		},
		['aui-base']
	);
</aui:script>