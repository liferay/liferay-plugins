<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<%
Template template = (Template)request.getAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE);

long templateId = ParamUtil.getLong(request, "templateId");
%>

<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) || TemplatePermission.contains(permissionChecker, template, ActionKeys.DELETE) || TemplatePermission.contains(permissionChecker, template, ActionKeys.PERMISSIONS) || TemplatePermission.contains(permissionChecker, template, ActionKeys.UPDATE) %>">
	<div class="kb-template-icons">
		<table class="lfr-table">
		<tr>
			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) %>">
				<td>
					<portlet:renderURL var="checkURL">
						<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="../aui/check"
						label="<%= true %>"
						message="use-this-template"
						url="<%= checkURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.UPDATE) %>">
				<td>
					<portlet:renderURL var="editURL">
						<portlet:param name="jspPage" value="/admin/edit_template.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						label="<%= true %>"
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
						url="<%= permissionsURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.DELETE) %>">
				<td>
					<portlet:renderURL var="templatesURL">
						<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
						<portlet:param name="topLink" value="templates" />
					</portlet:renderURL>

					<portlet:actionURL name="deleteTemplate" var="deleteURL">
						<portlet:param name="redirect" value="<%= (template.getTemplateId() == templateId) ? templatesURL : currentURL %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</portlet:actionURL>

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