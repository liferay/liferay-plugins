<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

if (Validator.isNull(tabs1)) {
	if (viewType.equals("user")) {
		tabs1 = "my-tasks";
	}
	else {
		tabs1 = "definitions";
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("tabs1", tabs1);
%>

<form action="<%= portletURL.toString() %>" method="post" name="<portlet:namespace />fm">

<%
String tabs1Names = "definitions,instances,tasks";

if (viewType.equals("user")) {
	tabs1Names = "my-tasks,my-workflows";
}
%>

<liferay-ui:tabs
	names="<%= tabs1Names %>"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("definitions") %>'>

		<%
		DefinitionSearch searchContainer = new DefinitionSearch(renderRequest, portletURL);

		List<String> headerNames = searchContainer.getHeaderNames();

		headerNames.add(StringPool.BLANK);
		%>

		<liferay-ui:search-form
			page="/definition_search.jsp"
			searchContainer="<%= searchContainer %>"
			servletContext="<%= application %>"
		/>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">

			<%
			DefinitionSearchTerms searchTerms = (DefinitionSearchTerms)searchContainer.getSearchTerms();

			if (Validator.isNull(definitionName)) {
				definitionName = searchTerms.getName();
			}

			int total = WorkflowComponentServiceUtil.getDefinitionsCount(searchTerms.getDefinitionId(), definitionName);

			searchContainer.setTotal(total);

			List results = WorkflowComponentServiceUtil.getDefinitions(searchTerms.getDefinitionId(), definitionName, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
			%>

			<div class="separator"><!-- --></div>

			<%
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				WorkflowDefinition definition = (WorkflowDefinition)results.get(i);

				String definitionId = String.valueOf(definition.getDefinitionId());

				ResultRow row = new ResultRow(definition, definitionId, i);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setWindowState(WindowState.MAXIMIZED);

				rowURL.setParameter("jspPage", "/edit_definition.jsp");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("definitionId", definitionId);

				row.setParameter("rowHREF", rowURL.toString());

				// Definition id

				row.addText(definitionId, rowURL);

				// Definition name

				row.addText(LanguageUtil.get(pageContext, definition.getName()), rowURL);

				// Definition version

				row.addText(String.valueOf(definition.getVersion()), rowURL);

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/definition_action.jsp", application, request, response);

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:if>
	</c:when>
	<c:when test='<%= tabs1.equals("instances") || tabs1.equals("my-workflows") %>'>

		<%
		boolean retrieveUserInstances = false;

		InstanceSearch searchContainer = new InstanceSearch(renderRequest, portletURL);

		List<String> headerNames = new ArrayList<String>();

		if (viewType.equals("user")) {
			headerNames.add("instance-id");
			headerNames.add("start-date");
			headerNames.add("end-date");
			headerNames.add("state");

			retrieveUserInstances = true;
		}
		else {
			headerNames = searchContainer.getHeaderNames();
		}

		headerNames.add(StringPool.BLANK);

		searchContainer.setHeaderNames(headerNames);

		if (viewType.equals("user") && Validator.isNotNull(definitionName)) {
			List definitions = WorkflowComponentServiceUtil.getDefinitions(0, definitionName, 0, 1);

			if (definitions.size() > 0) {
				WorkflowDefinition definition = (WorkflowDefinition)definitions.get(0);

				if (WorkflowDefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_INSTANCE)) {
					PortletURL addInstanceRedirectURL = renderResponse.createRenderURL();

					addInstanceRedirectURL.setWindowState(WindowState.MAXIMIZED);

					addInstanceRedirectURL.setParameter("tabs1", "my-workflows");

					PortletURL addInstanceURL = renderResponse.createActionURL();

					addInstanceURL.setWindowState(WindowState.MAXIMIZED);

					addInstanceURL.setParameter(ActionRequest.ACTION_NAME, "addInstance");
					addInstanceURL.setParameter("redirect", addInstanceRedirectURL.toString());
					addInstanceURL.setParameter("definitionId", String.valueOf(definition.getDefinitionId()));
					%>

					<table class="liferay-table">
					<tr>
						<td>
							<input type="button" onclick="location.href = '<%= addInstanceURL %>';" value="<liferay-ui:message key="start-a-new-workflow" />" />
						</td>
					</tr>
					</table>

					<%
				}
			}
		}
		%>

		<liferay-ui:search-form
			page="/instance_search.jsp"
			searchContainer="<%= searchContainer %>"
			servletContext="<%= application %>"
		/>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">

			<%
			InstanceSearchTerms searchTerms = (InstanceSearchTerms)searchContainer.getSearchTerms();

			if (Validator.isNull(definitionName)) {
				definitionName = searchTerms.getDefinitionName();
			}

			int total = WorkflowComponentServiceUtil.getInstancesCount(searchTerms.getDefinitionId(), searchTerms.getInstanceId(), definitionName, searchTerms.getDefinitionVersion(), searchTerms.getStartDateGT(), searchTerms.getStartDateLT(), searchTerms.getEndDateGT(), searchTerms.getEndDateLT(), searchTerms.isHideEndedTasks(), retrieveUserInstances, searchTerms.isAndOperator());

			searchContainer.setTotal(total);

			List results = WorkflowComponentServiceUtil.getInstances(searchTerms.getDefinitionId(), searchTerms.getInstanceId(), definitionName, searchTerms.getDefinitionVersion(), searchTerms.getStartDateGT(), searchTerms.getStartDateLT(), searchTerms.getEndDateGT(), searchTerms.getEndDateLT(), searchTerms.isHideEndedTasks(), retrieveUserInstances, searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
			%>

			<div class="separator"><!-- --></div>

			<%
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				WorkflowInstance instance = (WorkflowInstance)results.get(i);

				String instanceId = String.valueOf(instance.getInstanceId());

				WorkflowDefinition definition = instance.getDefinition();
				WorkflowToken token = instance.getToken();

				ResultRow row = new ResultRow(instance, instanceId, i);

				PortletURL rowURL = renderResponse.createRenderURL();

				if (viewType.equals("user")) {
					rowURL = null;
				}
				else {
					rowURL.setWindowState(WindowState.MAXIMIZED);

					rowURL.setParameter("tabs1", "definitions");
					rowURL.setParameter("definitionId", String.valueOf(definition.getDefinitionId()));

					row.setParameter("rowHREF", rowURL.toString());
				}

				// Instance id

				row.addText(instanceId, rowURL);

				if (!viewType.equals("user")) {
					// Definition name

					row.addText(LanguageUtil.get(pageContext, definition.getName()), rowURL);

					// Definition version

					row.addText(String.valueOf(definition.getVersion()), rowURL);
				}

				// Start date

				row.addText(dateFormatDateTime.format(instance.getStartDate()), rowURL);

				// End date

				if (instance.getEndDate() == null) {
					row.addText(LanguageUtil.get(pageContext, "not-available"), rowURL);
				}
				else {
					row.addText(dateFormatDateTime.format(instance.getEndDate()), rowURL);
				}

				// State

				row.addText(LanguageUtil.get(pageContext, token.getName()), rowURL);

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/instance_action.jsp", application, request, response);

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:if>
	</c:when>
	<c:when test='<%= tabs1.equals("tasks") || tabs1.equals("my-tasks") %>'>

		<%
		TaskSearch searchContainer = new TaskSearch(renderRequest, portletURL);

		List<String> headerNames = new ArrayList<String>();

		if (viewType.equals("user")) {
			headerNames.add("task-id");
			headerNames.add("task-name");
			headerNames.add("assigned-to");
			headerNames.add("create-date");
			headerNames.add("start-date");
			headerNames.add("end-date");
		}
		else {
			headerNames = searchContainer.getHeaderNames();
		}

		headerNames.add(StringPool.BLANK);

		searchContainer.setHeaderNames(headerNames);
		%>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<liferay-ui:search-form
				page="/task_search.jsp"
				searchContainer="<%= searchContainer %>"
				servletContext="<%= application %>"
			/>
		</c:if>

		<%
		TaskSearchTerms searchTerms = (TaskSearchTerms)searchContainer.getSearchTerms();

		if (Validator.isNull(definitionName)) {
			definitionName = searchTerms.getDefinitionName();
		}

		boolean hideEndedTasks = true;

		if (windowState.equals(WindowState.MAXIMIZED)) {
			hideEndedTasks = searchTerms.isHideEndedTasks();
		}

		int total = WorkflowComponentServiceUtil.getUserTasksCount(searchTerms.getInstanceId(), searchTerms.getTaskName(), definitionName, searchTerms.getAssignedTo(), searchTerms.getCreateDateGT(), searchTerms.getCreateDateLT(), searchTerms.getStartDateGT(), searchTerms.getStartDateLT(), searchTerms.getEndDateGT(), searchTerms.getEndDateLT(), hideEndedTasks, searchTerms.isAndOperator());

		searchContainer.setTotal(total);

		List results = WorkflowComponentServiceUtil.getUserTasks(searchTerms.getInstanceId(), searchTerms.getTaskName(), definitionName, searchTerms.getAssignedTo(), searchTerms.getCreateDateGT(), searchTerms.getCreateDateLT(), searchTerms.getStartDateGT(), searchTerms.getStartDateLT(), searchTerms.getEndDateGT(), searchTerms.getEndDateLT(), hideEndedTasks, searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);
		%>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<div class="separator"><!-- --></div>
		</c:if>

		<%
		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			WorkflowTask task = (WorkflowTask)results.get(i);

			String taskId = String.valueOf(task.getTaskId());

			WorkflowInstance instance = task.getInstance();

			String instanceId = String.valueOf(instance.getInstanceId());

			WorkflowDefinition definition = instance.getDefinition();

			ResultRow row = new ResultRow(task, taskId, i);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setWindowState(WindowState.MAXIMIZED);

			rowURL.setParameter("jspPage", "/edit_task.jsp");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("instanceId", instanceId);
			rowURL.setParameter("taskId", taskId);

			row.setParameter("rowHREF", rowURL.toString());

			// Task id

			row.addText(taskId, rowURL);

			// Task name

			row.addText(LanguageUtil.get(pageContext, task.getName()), rowURL);

			if (!viewType.equals("user")) {

				// Instance id

				row.addText(instanceId, rowURL);

				// Definition name

				row.addText(LanguageUtil.get(pageContext, definition.getName()), rowURL);
			}

			// Assigned to

			if (task.getAssignedUserId() == 0) {
				row.addText(LanguageUtil.get(pageContext, "pool"), rowURL);
			}
			else {
				row.addText(PortalUtil.getUserName(task.getAssignedUserId(), String.valueOf(task.getAssignedUserId()), request), rowURL);
			}

			// Create date

			row.addText(dateFormatDateTime.format(task.getCreateDate()), rowURL);

			// Start date

			if (task.getStartDate() == null) {
				row.addText(LanguageUtil.get(pageContext, "not-available"), rowURL);
			}
			else {
				row.addText(dateFormatDateTime.format(task.getStartDate()), rowURL);
			}

			// End date

			if (task.getEndDate() == null) {
				row.addText(LanguageUtil.get(pageContext, "not-available"), rowURL);
			}
			else {
				row.addText(dateFormatDateTime.format(task.getEndDate()), rowURL);
			}

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/task_action.jsp", application, request, response);

			// Add result row

			resultRows.add(row);
		}
		%>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</c:when>
</c:choose>

</form>