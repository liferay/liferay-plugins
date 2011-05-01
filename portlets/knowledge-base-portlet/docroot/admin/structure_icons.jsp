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
KBStructure kbStructure = (KBStructure)request.getAttribute("structure_icons.jsp-kb_structure");

long kbStructureId = ParamUtil.getLong(request, "kbStructureId");
%>

<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) || KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.DELETE) || KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.PERMISSIONS) || KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.UPDATE) %>">
	<div class="kb-structure-icons">
		<table class="lfr-table">
		<tr>
			<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.UPDATE) %>">
				<td>
					<liferay-portlet:renderURL var="editURL">
						<portlet:param name="jspPage" value='<%= jspPath + "edit_structure.jsp" %>' />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructure.getKbStructureId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						label="<%= true %>"
						method="get"
						url="<%= editURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.PERMISSIONS) %>">
				<td>
					<liferay-security:permissionsURL
						modelResource="<%= KBStructure.class.getName() %>"
						modelResourceDescription="<%= kbStructure.getTitle(locale) %>"
						resourcePrimKey="<%= String.valueOf(kbStructure.getKbStructureId()) %>"
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

			<c:if test="<%= KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.DELETE) %>">
				<td>
					<liferay-portlet:renderURL var="homeURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view.jsp" %>' />
					</liferay-portlet:renderURL>

					<liferay-portlet:actionURL name="deleteKBStructure" var="deleteURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_structure.jsp" %>' />
						<portlet:param name="redirect" value="<%= (kbStructure.getKbStructureId() == kbStructureId) ? homeURL : currentURL %>" />
						<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructure.getKbStructureId()) %>" />
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