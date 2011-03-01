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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Template template = (Template)row.getObject();
%>

<liferay-ui:icon-menu cssClass="kb-template-action">
	<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.VIEW) %>">
		<portlet:renderURL var="viewURL">
			<portlet:param name="jspPage" value="/admin/view_template.jsp" />
			<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="view"
			url="<%= viewURL %>"
		/>
	</c:if>

	<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/admin/edit_template.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Template.class.getName() %>"
			modelResourceDescription="<%= template.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(template.getTemplateId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteTemplate" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>