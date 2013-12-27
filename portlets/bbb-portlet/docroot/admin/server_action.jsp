<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

BBBServer bbbServer = null;

if (row != null) {
	bbbServer = (BBBServer)row.getObject();
}
%>

<liferay-ui:icon-menu showExpanded="<%= row == null %>">
	<c:if test="<%= BBBServerPermission.contains(permissionChecker, bbbServer, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/admin/edit_server.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="bbbServerId" value="<%= String.valueOf(bbbServer.getBbbServerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= BBBServerPermission.contains(permissionChecker, bbbServer, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= BBBServer.class.getName() %>"
			modelResourceDescription="<%= bbbServer.getName() %>"
			resourcePrimKey="<%= String.valueOf(bbbServer.getBbbServerId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= BBBServerPermission.contains(permissionChecker, bbbServer, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteBBBServer" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="bbbServerId" value="<%= String.valueOf(bbbServer.getBbbServerId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>