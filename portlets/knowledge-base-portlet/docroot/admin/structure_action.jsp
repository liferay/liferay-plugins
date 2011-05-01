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

<%@ include file="/admin/init.jsp" %>

<%
String jspPage = ParamUtil.getString(request, "jspPage");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBStructure kbStructure = (KBStructure)row.getObject();
%>

<liferay-ui:icon-menu cssClass="kb-structure-action">
	<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.VIEW) %>">
		<liferay-portlet:renderURL var="viewURL">
			<portlet:param name="jspPage" value='<%= jspPath + "view_structure.jsp" %>' />
			<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructure.getKbStructureId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="view"
			method="get"
			url="<%= viewURL %>"
		/>
	</c:if>

	<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value='<%= jspPath + "edit_structure.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructure.getKbStructureId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= KBStructure.class.getName() %>"
			modelResourceDescription="<%= kbStructure.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(kbStructure.getKbStructureId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.DELETE) %>">
		<liferay-portlet:actionURL name="deleteKBStructure" var="deleteURL">
			<portlet:param name="jspPage" value="<%= jspPage %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructure.getKbStructureId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>