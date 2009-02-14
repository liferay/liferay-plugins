<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
TaskSearch searchContainer = (TaskSearch)request.getAttribute("liferay-ui:search:searchContainer");

TaskDisplayTerms displayTerms = (TaskDisplayTerms)searchContainer.getDisplayTerms();
%>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="task-name" />
	</td>

	<c:if test='<%= !viewType.equals("user") %>'>
		<td>
			<liferay-ui:message key="definition-name" />
		</td>
	</c:if>

	<td>
		<liferay-ui:message key="assigned-to" />
	</td>
</tr>
<tr>
	<td>
		<input name="<portlet:namespace /><%= displayTerms.TASK_NAME %>" size="20" type="text" value="<%= HtmlUtil.escape(displayTerms.getTaskName()) %>" />
	</td>

	<c:if test='<%= !viewType.equals("user") %>'>
		<td>
			<input name="<portlet:namespace /><%= displayTerms.DEFINITION_NAME %>" size="20" type="text" value="<%= displayTerms.getDefinitionName() %>" />
		</td>
	</c:if>

	<td>
		<select name="<portlet:namespace /><%= displayTerms.ASSIGNED_TO %>">
			<option <%= displayTerms.getAssignedTo().equals("all") ? "selected" : "" %> value="all"><liferay-ui:message key="all" /></option>
			<option <%= displayTerms.getAssignedTo().equals("me") ? "selected" : "" %> value="me"><liferay-ui:message key="me" /></option>
			<option <%= displayTerms.getAssignedTo().equals("pool") ? "selected" : "" %> value="pool"><liferay-ui:message key="pool" /></option>
		</select>
	</td>
</tr>
</table>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="create-date" /> (<liferay-ui:message key="range" />)
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:input-field model="<%= WorkflowTask.class %>" field="<%= displayTerms.CREATE_DATE_GT %>" />

		<liferay-ui:message key="to" />

		<liferay-ui:input-field model="<%= WorkflowTask.class %>" field="<%= displayTerms.CREATE_DATE_LT %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="start-date" /> (<liferay-ui:message key="range" />)
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:input-field model="<%= WorkflowTask.class %>" field="<%= displayTerms.START_DATE_GT %>" />

		<liferay-ui:message key="to" />

		<liferay-ui:input-field model="<%= WorkflowTask.class %>" field="<%= displayTerms.START_DATE_LT %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="end-date" /> (<liferay-ui:message key="range" />)
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:input-field model="<%= WorkflowTask.class %>" field="<%= displayTerms.END_DATE_GT %>" />

		<liferay-ui:message key="to" />

		<liferay-ui:input-field model="<%= WorkflowTask.class %>" field="<%= displayTerms.END_DATE_LT %>" />

		<input <%= displayTerms.isHideEndedTasks() ? "checked" : "" %> name="<portlet:namespace /><%= displayTerms.HIDE_ENDED_TASKS %>" type="checkbox" onClick="<portlet:namespace />updateEndDates();"> <liferay-ui:message key="hide-tasks-that-have-already-ended" />
	</td>
</tr>
</table>

<br />

<table class="lfr-table">
<tr>
	<td>
		<select name="<portlet:namespace /><%= displayTerms.AND_OPERATOR %>">
			<option <%= displayTerms.isAndOperator() ? "selected" : "" %> value="1"><liferay-ui:message key="and" /></option>
			<option <%= !displayTerms.isAndOperator() ? "selected" : "" %> value="0"><liferay-ui:message key="or" /></option>
		</select>
	</td>
	<td>
		<input type="submit" value="<liferay-ui:message key="search" />" />
	</td>
</tr>
</table>

<script type="text/javascript">
	<portlet:namespace />updateEndDates();

	function <portlet:namespace />updateEndDates() {
		var form = document.<portlet:namespace />fm;

		var hideEndedTasks = form.<portlet:namespace /><%= displayTerms.HIDE_ENDED_TASKS %>.checked;

		for (var i = 0; i < form.elements.length; i++) {
			var e = form.elements[i];

			if (e.name.indexOf("<portlet:namespace />endDate") != -1) {
				e.disabled = hideEndedTasks;
			}
		}

		document.getElementById("<portlet:namespace />endDateGTImageInputId").disabled = hideEndedTasks;
		document.getElementById("<portlet:namespace />endDateLTImageInputId").disabled = hideEndedTasks;
	}
</script>