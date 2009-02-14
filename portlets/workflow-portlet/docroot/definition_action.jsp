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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WorkflowDefinition definition = (WorkflowDefinition)row.getObject();

String definitionId = String.valueOf(definition.getDefinitionId());
%>

<c:if test="<%= WorkflowDefinitionPermission.contains(permissionChecker, definition, ActionKeys.UPDATE) %>">
	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
		<portlet:param name="jspPage" value="/edit_definition.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="<%= editURL %>" />
</c:if>

<c:if test="<%= WorkflowDefinitionPermission.contains(permissionChecker, definition, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="<%= WorkflowDefinition.class.getName() %>"
		modelResourceDescription='<%= definition.getName() + " " + definition.getVersion() %>'
		resourcePrimKey="<%= definitionId %>"
		var="permissionsURL"
	/>

	<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
</c:if>

<c:if test="<%= WorkflowDefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_INSTANCE) %>">
	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addInstanceRedirectURL">
		<portlet:param name="tabs1" value="instances" />
	</portlet:renderURL>

	<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addInstanceURL" name="addInstance">
		<portlet:param name="redirect" value="<%= addInstanceRedirectURL %>" />
		<portlet:param name="definitionId" value="<%= definitionId %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="add_instance" message="add-instance" url="<%= addInstanceURL %>" />
</c:if>

<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewInstancesURL">
	<portlet:param name="tabs1" value="instances" />
	<portlet:param name="definitionId" value="<%= definitionId %>" />
</portlet:renderURL>

<liferay-ui:icon image="view_instances" message="view-instances" url="<%= viewInstancesURL %>" />