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
KBTemplate kbTemplate = (KBTemplate)request.getAttribute("template_icons.jsp-kb_template");

long kbTemplateId = ParamUtil.getLong(request, "kbTemplateId");
%>

<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) || KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.DELETE) || KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.PERMISSIONS) || KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.UPDATE) %>">
	<div class="kb-template-icons">
		<table class="lfr-table">
		<tr>
			<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) %>">
				<td>
					<liferay-portlet:renderURL var="useThisKBTemplateURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="../aui/check"
						label="<%= true %>"
						message="use-this-template"
						method="get"
						url="<%= useThisKBTemplateURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.UPDATE) %>">
				<td>
					<liferay-portlet:renderURL var="editURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "edit_template.jsp" %>' />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						label="<%= true %>"
						method="get"
						url="<%= editURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.PERMISSIONS) %>">
				<td>
					<liferay-security:permissionsURL
						modelResource="<%= KBTemplate.class.getName() %>"
						modelResourceDescription="<%= kbTemplate.getTitle() %>"
						resourcePrimKey="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>"
						var="permissionsURL"
						windowState="<%= LiferayWindowState.POP_UP.toString() %>"
					/>

					<liferay-ui:icon
						image="permissions"
						label="<%= true %>"
						method="get"
						url="<%= permissionsURL %>"
						useDialog="<%= true %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.DELETE) %>">
				<td>
					<liferay-portlet:renderURL var="homeURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "view.jsp" %>' />
					</liferay-portlet:renderURL>

					<liferay-portlet:actionURL name="deleteKBTemplate" var="deleteURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "view_template.jsp" %>' />
						<portlet:param name="redirect" value="<%= (kbTemplate.getKbTemplateId() == kbTemplateId) ? homeURL : currentURL %>" />
						<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
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