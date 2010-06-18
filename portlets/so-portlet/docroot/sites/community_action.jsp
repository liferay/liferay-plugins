<%
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
%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Group group = (Group)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="editURL">
			<liferay-portlet:param name="struts_action" value="/communities/edit_community" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
		</liferay-portlet:renderURL>

		<%
		String taglibEditURL = "javascript:Liferay.SO.Sites.displayPopup('" + editURL + "','" + LanguageUtil.get(pageContext, "edit-site") + "');";
		%>

		<liferay-ui:icon
			image="edit"
			url="<%= taglibEditURL %>"
		/>
	</c:if>

	<c:if test="<%= permissionChecker.isCommunityAdmin(group.getGroupId()) %>">
		<liferay-portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="redirectURL">
			<liferay-portlet:param name="jspPage" value="/sites/view.jsp" />
		</liferay-portlet:renderURL>

		<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="manageMembersURL">
			<liferay-portlet:param name="jspPage" value="/sites/edit_members.jsp" />
			<liferay-portlet:param name="backURL" value="<%= redirectURL %>" />
			<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="assign"
			message="manage-members"
			url="<%= manageMembersURL %>"
		/>
	</c:if>

	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.MANAGE_TEAMS) %>">
		<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="manageTeamsURL">
			<liferay-portlet:param name="struts_action" value="/communities/view_teams" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="group"
			message="manage-teams"
			url="<%= manageTeamsURL %>"
		/>
	</c:if>

	<c:choose>
		<c:when test="<%= !GroupLocalServiceUtil.hasUserGroup(user.getUserId(), group.getGroupId()) %>">
			<c:if test="<%= group.getType() == GroupConstants.TYPE_COMMUNITY_OPEN %>">
				<liferay-portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="joinURL">
					<liferay-portlet:param name="struts_action" value="/communities/edit_community_assignments" />
					<liferay-portlet:param name="<%= Constants.CMD %>" value="group_users" />
					<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
					<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
					<liferay-portlet:param name="addUserIds" value="<%= String.valueOf(user.getUserId()) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:icon
					image="join"
					url="<%= joinURL %>"
				/>
			</c:if>
		</c:when>
		<c:when test="<%= !GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.DELETE) %>">
			<liferay-portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="leaveURL">
				<liferay-portlet:param name="struts_action" value="/communities/edit_community_assignments" />
				<liferay-portlet:param name="<%= Constants.CMD %>" value="group_users" />
				<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
				<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
				<liferay-portlet:param name="removeUserIds" value="<%= String.valueOf(user.getUserId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon
				image="leave"
				url="<%= leaveURL %>"
			/>
		</c:when>
	</c:choose>

	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.DELETE) %>">
		<liferay-portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="deleteURL">
			<liferay-portlet:param name="struts_action" value="/communities/edit_community" />
			<liferay-portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>