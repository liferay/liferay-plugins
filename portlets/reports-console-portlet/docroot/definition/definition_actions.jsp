<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ReportDefinition definition = (ReportDefinition)row.getObject();

String definitionId = String.valueOf(definition.getDefinitionId());
%>
<liferay-ui:icon-menu
	cssClass=""
>
<c:if test="<%= ReportDefinitionPermission.contains(permissionChecker, definition, ActionKeys.UPDATE) %>">
	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
		<portlet:param name="jspPage" value="/definition/edit_definition.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="<%= editURL %>" />
</c:if>

<c:if test="<%= ReportDefinitionPermission.contains(permissionChecker, definition, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="<%= ReportDefinition.class.getName() %>"
		modelResourceDescription='<%= definition.getDefinitionName() %>'
		resourcePrimKey="<%= definitionId %>"
		var="permissionsURL"
	/>

	<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
</c:if>

	<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="generateImmdiatelyURL" name="generateImmdiately">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="submit" message="generate-immdiately" url="<%= generateImmdiatelyURL %>" />

<c:if test="<%= ReportDefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_INSTANCE) %>">
	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addScheduleURL">
		<portlet:param name="jspPage" value="/definition/edit_definition.jsp" />
        <portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="time" message="add-schedule" url="<%= addScheduleURL %>" />
</c:if>

    <portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewTemplateScheduleURL">
		<portlet:param name="jspPage" value="/view_template_events.jsp" />
        <portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="manage_task" message="view-schedule" url="<%= viewTemplateScheduleURL %>" />

	<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="deleteDefinition,searchDefinition"  var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="delete" message="delete-template" url="<%= deleteURL %>" />
</liferay-ui:icon-menu>

