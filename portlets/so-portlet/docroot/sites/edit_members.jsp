<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
String redirect = ParamUtil.getString(request, "redirect");

long groupId = ParamUtil.getLong(request, "groupId");
%>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.getGroupUsers(groupId) %>"
		total="<%= UserLocalServiceUtil.getGroupUsersCount(groupId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		keyProperty="userId"
		modelVar="curUser"
	>
		<liferay-ui:search-container-column-text
			name="user"
			value="<%= curUser.getFullName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="screen-name"
			value="<%= curUser.getScreenName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="email"
			value="<%= curUser.getEmailAddress() %>"
		/>

		<%
		List<UserGroupRole> communityRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(curUser.getUserId(), groupId);

		Iterator<UserGroupRole> itr = communityRoles.iterator();

		while (itr.hasNext()) {
			UserGroupRole userGroupRole = itr.next();

			if (userGroupRole.getRole().getType() != RoleConstants.TYPE_COMMUNITY) {
				itr.remove();

				continue;
			}

			if (userGroupRole.getRole().getName().equals(RoleConstants.COMMUNITY_MEMBER)) {
				itr.remove();
			}
		}

		UserGroupRole communityRole = null;

		if (!communityRoles.isEmpty()) {
			communityRole = communityRoles.get(0);
		}
		%>

		<liferay-ui:search-container-column-text
			name="community-role"
			value="<%= (communityRole == null) ? StringPool.BLANK : communityRole.getRole().getName() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/sites/user_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<br />

<aui:button onClick="<%= redirect %>" value="back" />

<form id="<portlet:namespace />fm" action="<portlet:actionURL name="updateRoles" />" method="post" name="<portlet:namespace />fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />
	<input name="<portlet:namespace />userId" type="hidden" />
	<input name="<portlet:namespace />groupId" type="hidden" />
	<input name="<portlet:namespace />roleId" type="hidden" />
</form>

<aui:script>
	function <portlet:namespace />openRoleSelector(url) {
		var roleWindow = window.open(url, 'role', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		roleWindow.focus();
	}

	function <portlet:namespace />updateRole(userId, groupId, roleId) {
		document.<portlet:namespace />fm.<portlet:namespace />userId.value = userId;
		document.<portlet:namespace />fm.<portlet:namespace />groupId.value = groupId;
		document.<portlet:namespace />fm.<portlet:namespace />roleId.value = roleId;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>