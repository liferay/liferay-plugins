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

<h1><liferay-ui:message key="members" /></h1>

<liferay-ui:search-container
	delta="<%= 1000 %>"
>
	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.getGroupUsers(layout.getGroupId()) %>"
		total="<%= UserLocalServiceUtil.getGroupUsersCount(layout.getGroupId()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		keyProperty="userId"
		modelVar="curUser"
	>
		<liferay-ui:search-container-column-text
			buffer="buffer"
		>

			<%
			buffer.append("<a class=\"user\" href=\"javascript:;\" data-userId=\"");
			buffer.append(curUser.getUserId());
			buffer.append("\"><img alt=\"");
			buffer.append(curUser.getFullName());
			buffer.append("\" src=\"");
			buffer.append(curUser.getPortraitURL(themeDisplay));
			buffer.append("\" width=\"35\" /></a>");
			%>

		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
			buffer="buffer"
		>

			<%
			buffer.append("<a class=\"user\" href=\"javascript:;\" data-userId=\"");
			buffer.append(curUser.getUserId());
			buffer.append("\">");
			buffer.append(curUser.getFullName());
			buffer.append("</a><br />");

			if(!curUser.getContact().getJobTitle().equals(StringPool.BLANK)) {
				buffer.append(curUser.getContact().getJobTitle());
				buffer.append("<br />");
			}
			%>

		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%
List users = UserLocalServiceUtil.getGroupUsers(layout.getGroupId());

int invitedMembersCount = ParamUtil.getInteger(renderRequest, "invitedMembersCount");
%>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, layout.getGroupId(), ActionKeys.UPDATE) %>">
	<div class="invite-members">
		<a href="javascript:;"><liferay-ui:message key="invite-members" /></a>
	</div>
</c:if>

<c:if test="<%= invitedMembersCount > 0 %>">
	<div class="portlet-msg-success">
		<c:choose>
			<c:when test="<%= invitedMembersCount > 1 %>">
				<%= LanguageUtil.format(pageContext, "you-have-invited-x-friends", invitedMembersCount) %>
			</c:when>
			<c:otherwise>
				<%= LanguageUtil.format(pageContext, "you-have-invited-x-friend", invitedMembersCount) %>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>