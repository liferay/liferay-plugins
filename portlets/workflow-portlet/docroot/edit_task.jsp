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
String redirect = ParamUtil.getString(request, "redirect");

long taskId = ParamUtil.getLong(request, "taskId");
long instanceId = ParamUtil.getLong(request, "instanceId");

List taskFormElements = WorkflowComponentServiceUtil.getTaskFormElements(taskId);
List taskTransitions = WorkflowComponentServiceUtil.getTaskTransitions(taskId);

Map errors = (Map)SessionErrors.get(renderRequest, WorkflowPortlet.class.getName());
%>

<script language="JavaScript">
	function <portlet:namespace />getDate(displayName) {
		eval("var month = (parseInt(document.<portlet:namespace />fm.<portlet:namespace />" + displayName + "Month.value) + 1);");
		eval("var day = (parseInt(document.<portlet:namespace />fm.<portlet:namespace />" + displayName + "Day.value));");
		eval("var year = (parseInt(document.<portlet:namespace />fm.<portlet:namespace />" + displayName + "Year.value));");

		if (month < 10) {
			month = "0" + month;
		}

		if (day < 10) {
			day = "0" + day;
		}

		return month + "/" + day + "/" + year;
	}

	function <portlet:namespace />saveTask(taskTransition) {
		document.<portlet:namespace />fm.<portlet:namespace />taskTransition.value = taskTransition;

		<%
		for (int i = 0; i < taskFormElements.size(); i++) {
			WorkflowTaskFormElement taskFormElement = (WorkflowTaskFormElement)taskFormElements.get(i);

			String type = taskFormElement.getType();
			String displayName = taskFormElement.getDisplayName();

			if (type.equals(WorkflowTaskFormElement.TYPE_DATE) && taskFormElement.isWritable()) {
				displayName = JS.getSafeName(displayName);
		%>

				document.<portlet:namespace />fm.<portlet:namespace /><%= displayName %>.value = <portlet:namespace />getDate("<%= displayName %>");

		<%
			}
		}
		%>

		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateTask"><portlet:param name="jspPage" value="/edit_task.jsp" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />taskId" type="hidden" value="<%= taskId %>" />
<input name="<portlet:namespace />instanceId" type="hidden" value="<%= instanceId %>" />
<input name="<portlet:namespace />taskTransition" type="hidden" value="" />

<liferay-ui:tabs names="task" />

<table class="lfr-table">

<%
for (int i = 0; i < taskFormElements.size(); i++) {
	WorkflowTaskFormElement taskFormElement = (WorkflowTaskFormElement)taskFormElements.get(i);

	String type = taskFormElement.getType();
	String displayName = taskFormElement.getDisplayName();
	String value = taskFormElement.getValue();
	List valueList = taskFormElement.getValueList();

	String errorCode = null;

	if (errors != null) {
		errorCode = (String)errors.get(displayName);
	}
%>

	<c:if test="<%= Validator.isNotNull(errorCode) %>">
		<tr>
			<td colspan="2">
				<span class="portlet-msg-error">

				<c:choose>
					<c:when test='<%= errorCode.equals("required-value") %>'>
						<liferay-ui:message key="please-enter-a-value" />
					</c:when>
					<c:when test='<%= errorCode.equals("invalid-date") %>'>
						<liferay-ui:message key="please-enter-a-valid-date" />
					</c:when>
					<c:when test='<%= errorCode.equals("invalid-email") %>'>
						<liferay-ui:message key="please-enter-a-valid-email-address" />
					</c:when>
					<c:when test='<%= errorCode.equals("invalid-number") %>'>
						<liferay-ui:message key="please-enter-a-valid-number" />
					</c:when>
					<c:when test='<%= errorCode.equals("invalid-phone") %>'>
						<liferay-ui:message key="please-enter-a-valid-phone-number" />
					</c:when>
				</c:choose>

				</span>
			</td>
		</tr>
	</c:if>

	<tr>
		<td>
			<%= LanguageUtil.get(pageContext, displayName) %>

			<c:if test="<%= taskFormElement.isRequired() %>">
				<span class="portlet-msg-error">
				*
				</span>
			</c:if>
		</td>
		<td>
			<c:choose>
				<c:when test="<%= taskFormElement.isWritable() %>">
					<c:choose>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_CHECKBOX) %>">
							<liferay-ui:input-checkbox
								param="<%= displayName %>"
								defaultValue="<%= Validator.isNotNull(value) %>"
							/>
						</c:when>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_DATE) %>">

							<%
							displayName = JS.getSafeName(displayName);

							Calendar cal = null;

							if (Validator.isNotNull(value)) {
								cal = CalendarFactoryUtil.getCalendar();

								cal.setTime(WorkflowXMLUtil.parseDate(value));
							}
							%>

							<liferay-ui:input-field
								model="<%= WorkflowTask.class %>"
								field="<%= TaskDisplayTerms.CREATE_DATE_GT %>"
								fieldParam="<%= displayName %>"
								defaultValue="<%= cal %>"
							/>

							<input name="<portlet:namespace /><%= displayName %>" type="hidden" value="" />
						</c:when>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_EMAIL) || type.equals(WorkflowTaskFormElement.TYPE_NUMBER) || type.equals(WorkflowTaskFormElement.TYPE_PHONE) || type.equals(WorkflowTaskFormElement.TYPE_TEXT) %>">
							<input class="lfr-input-text" name="<portlet:namespace /><%= displayName %>" type="text" value="<%= value %>" />
						</c:when>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_PASSWORD) %>">
							<input class="lfr-input-text" name="<portlet:namespace /><%= displayName %>" type="password" value="<%= value %>" />
						</c:when>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_RADIO) %>">

							<%
							for (int j = 0; j < valueList.size(); j++) {
								String curValue = (String)valueList.get(j);
							%>

								<input <%= value.equals(curValue) ? "checked" : "" %> name="<portlet:namespace /><%= displayName %>" type="radio" value="<%= curValue %>"> <%= LanguageUtil.get(pageContext, curValue) %><br />

							<%
							}
							%>

						</c:when>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_SELECT) %>">
							<select name="<portlet:namespace /><%= displayName %>">

								<%
								for (int j = 0; j < valueList.size(); j++) {
									String curValue = (String)valueList.get(j);
								%>

									<option <%= value.equals(curValue) ? "selected" : "" %> value="<%= curValue %>"><%= LanguageUtil.get(pageContext, curValue) %></option>

								<%
								}
								%>

							</select>
						</c:when>
						<c:when test="<%= type.equals(WorkflowTaskFormElement.TYPE_TEXTAREA) %>">
							<textarea class="lfr-textarea" name="<portlet:namespace /><%= displayName %>" wrap="soft"><%= value %></textarea>
						</c:when>
					</c:choose>
				</c:when>
				<c:when test="<%= taskFormElement.isReadable() %>">

					<%
					value = taskFormElement.getValue();

					if (type.equals(WorkflowTaskFormElement.TYPE_DATE)) {
					}
					else if (type.equals(WorkflowTaskFormElement.TYPE_CHECKBOX)) {
						if (GetterUtil.getBoolean(value)) {
							value = LanguageUtil.get(pageContext, (String)taskFormElement.getValueList().get(0));
						}
					}
					else if (type.equals(WorkflowTaskFormElement.TYPE_RADIO) || type.equals(WorkflowTaskFormElement.TYPE_SELECT)) {
						value = LanguageUtil.get(pageContext, value);
					}

					if (Validator.isNull(value)) {
						value = LanguageUtil.get(pageContext, "not-available");
					}
					%>

					<%= value %>
				</c:when>
			</c:choose>
		</td>
	</tr>

<%
}
%>

</table>

<br />

<%
WorkflowTask task = null;

List instances = WorkflowComponentServiceUtil.getInstances(0, instanceId, null, null, null, null, null, null, false, true, true, 0, 1);

if (instances.size() > 0) {
	WorkflowInstance instance = (WorkflowInstance)instances.get(instances.size() - 1);

	List tasks = instance.getToken().getTasks();

	if (tasks.size() > 0) {
		task = (WorkflowTask)instance.getToken().getTasks().get(0);
	}
}

if ((task != null) && (task.getTaskId() == taskId)) {
	for (int i = 0; i < taskTransitions.size(); i++) {
		String taskTransition = (String)taskTransitions.get(i);
%>

	<input type="button" name="<%= LanguageUtil.get(pageContext, taskTransition) %>" value="<%= LanguageUtil.get(pageContext, taskTransition) %>" onClick="<portlet:namespace />saveTask('<%= taskTransition %>');" />

<%
	}
}
%>

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>