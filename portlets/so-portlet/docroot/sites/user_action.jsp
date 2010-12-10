<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User curUser = (User)row.getObject();

long groupId = ParamUtil.getLong(request, "groupId");
%>

<c:if test="<%= user.getUserId() != curUser.getUserId() %>">
	<liferay-ui:icon-menu>
		<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, groupId, ActionKeys.UPDATE) %>">
			<liferay-portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="removeMemberURL">
				<liferay-portlet:param name="struts_action" value="/communities/edit_community_assignments" />
				<liferay-portlet:param name="<%= Constants.CMD %>" value="group_users" />
				<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
				<liferay-portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
				<liferay-portlet:param name="removeUserIds" value="<%= String.valueOf(curUser.getUserId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon
				image="leave"
				message="remove-member"
				url="<%= removeMemberURL %>"
			/>
		</c:if>

		<c:if test="<%= permissionChecker.isCommunityAdmin(groupId) %>">
			<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="changeRoleURL">
				<liferay-portlet:param name="jspPage" value="/sites/select_role.jsp" />
				<liferay-portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
				<liferay-portlet:param name="userId" value="<%= String.valueOf(curUser.getUserId()) %>" />
			</liferay-portlet:renderURL>

			<%
			String taglibChangeRoleURL = "javascript:" + renderResponse.getNamespace() + "openRoleSelector('" + changeRoleURL + "');";
			%>

			<liferay-ui:icon
				image="assign_user_roles"
				message="change-role"
				url="<%= taglibChangeRoleURL %>"
			/>
		</c:if>
	</liferay-ui:icon-menu>
</c:if>