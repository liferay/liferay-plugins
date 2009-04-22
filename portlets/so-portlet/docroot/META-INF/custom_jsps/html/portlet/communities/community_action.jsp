<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Group group = (Group)row.getObject();
%>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.UPDATE) %>">
	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
		<portlet:param name="struts_action" value="/communities/edit_community" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
	</portlet:renderURL>

	<a class="edit-site" href="<%= editURL %>"><liferay-ui:message key="edit" /></a>
</c:if>

<c:choose>
	<c:when test="<%= !GroupLocalServiceUtil.hasUserGroup(user.getUserId(), group.getGroupId()) %>">
		<c:if test="<%= group.getType() == GroupConstants.TYPE_COMMUNITY_OPEN %>">
			<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="joinURL">
				<portlet:param name="struts_action" value="/communities/edit_community_assignments" />
				<portlet:param name="<%= Constants.CMD %>" value="group_users" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
				<portlet:param name="addUserIds" value="<%= String.valueOf(user.getUserId()) %>" />
			</portlet:actionURL>

			<a class="join-site" href="javascript: ;" onclick="return (submitForm(document.hrefFm, '<%= joinURL %>'));"><liferay-ui:message key="join" /></a>
		</c:if>
	</c:when>
	<c:when test="<%= !GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.DELETE) %>">
		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="leaveURL">
			<portlet:param name="struts_action" value="/communities/edit_community_assignments" />
			<portlet:param name="<%= Constants.CMD %>" value="group_users" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
			<portlet:param name="removeUserIds" value="<%= String.valueOf(user.getUserId()) %>" />
		</portlet:actionURL>

		<a class="leave-site" href="javascript: ;" onclick="return (submitForm(document.hrefFm, '<%= leaveURL %>'));"><liferay-ui:message key="leave" /></a>
	</c:when>
</c:choose>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.DELETE) %>">
	<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
		<portlet:param name="struts_action" value="/communities/edit_community" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
	</portlet:actionURL>

	<a class="delete-site" href="javascript: ;" onclick="return (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />') && submitForm(document.hrefFm, '<%= deleteURL %>'));"><liferay-ui:message key="delete" /></a>
</c:if>