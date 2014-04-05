<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBTemplate kbTemplate = (KBTemplate)row.getObject();
%>

<liferay-ui:icon-menu cssClass="kb-template-action">
	<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.VIEW) %>">
		<liferay-portlet:renderURL var="viewURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "view_template.jsp" %>' />
			<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="view"
			method="get"
			url="<%= viewURL %>"
		/>
	</c:if>

	<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "edit_template.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= KBTemplate.class.getName() %>"
			modelResourceDescription="<%= kbTemplate.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>"
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

	<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.DELETE) %>">
		<liferay-portlet:actionURL name="deleteKBTemplate" var="deleteURL">
			<portlet:param name="mvcPath" value="<%= mvcPath %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>