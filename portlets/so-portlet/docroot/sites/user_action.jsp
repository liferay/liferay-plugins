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

User curUser = (User)row.getObject();

long groupId = ParamUtil.getLong(request, "groupId");
%>

<c:if test="<%= user.getUserId() != curUser.getUserId() %>">
	<liferay-ui:icon-menu>
		<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, groupId, ActionKeys.UPDATE) %>">
			<liferay-portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.COMMUNITIES %>" var="removeMemberURL">
				<portlet:param name="struts_action" value="/communities/edit_community_assignments" />
				<portlet:param name="<%= Constants.CMD %>" value="group_users" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
				<portlet:param name="removeUserIds" value="<%= String.valueOf(curUser.getUserId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon image="leave" message="remove-member" url="<%= removeMemberURL %>" />
		</c:if>
	</liferay-ui:icon-menu>
</c:if>