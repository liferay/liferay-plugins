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
Template template = (Template)request.getAttribute("template_icons.jsp-template");

long templateId = ParamUtil.getLong(request, "templateId");
%>

<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_DISPLAY)) || TemplatePermission.contains(permissionChecker, template, ActionKeys.DELETE) || TemplatePermission.contains(permissionChecker, template, ActionKeys.PERMISSIONS) || TemplatePermission.contains(permissionChecker, template, ActionKeys.UPDATE) %>">
	<div class="kb-template-icons">
		<table class="lfr-table">
		<tr>
			<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_DISPLAY)) %>">
				<td>
					<liferay-portlet:renderURL var="checkURL">
						<portlet:param name="jspPage" value='<%= jspPath + "edit_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="../aui/check"
						label="<%= true %>"
						message="use-this-template"
						method="get"
						url="<%= checkURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.UPDATE) %>">
				<td>
					<liferay-portlet:renderURL var="editURL">
						<portlet:param name="jspPage" value='<%= jspPath + "edit_template.jsp" %>' />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						label="<%= true %>"
						method="get"
						url="<%= editURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.PERMISSIONS) %>">
				<td>
					<liferay-security:permissionsURL
						modelResource="<%= Template.class.getName() %>"
						modelResourceDescription="<%= template.getTitle() %>"
						resourcePrimKey="<%= String.valueOf(template.getTemplateId()) %>"
						var="permissionsURL"
					/>

					<liferay-ui:icon
						image="permissions"
						label="<%= true %>"
						method="get"
						url="<%= permissionsURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.DELETE) %>">
				<td>
					<liferay-portlet:renderURL var="homeURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view-jsp" %>' />
					</liferay-portlet:renderURL>

					<liferay-portlet:actionURL name="deleteTemplate" var="deleteURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_template.jsp" %>' />
						<portlet:param name="redirect" value="<%= (template.getTemplateId() == templateId) ? homeURL : currentURL %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</liferay-portlet:actionURL>

					<liferay-ui:icon-delete
						label="<%= true %>"
						url="<%= deleteURL %>"
					/>
				</td>
			</c:if>
		</tr>
		</table>
	</div>
</c:if>