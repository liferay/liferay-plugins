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
long userId = ParamUtil.getLong(request, "userId");
long groupId = ParamUtil.getLong(request, "groupId");
%>

<liferay-ui:header
	title="community-roles"
/>

<%
String taglibRemoveRole = "javascript:opener." + renderResponse.getNamespace() + "updateRole('" +  userId +  "', '" + groupId + "', ''); window.close();";
%>

<aui:button-row>
	<aui:button onClick="<%= taglibRemoveRole %>" value="remove-role" />
</aui:button-row>

<liferay-ui:search-container>
	<liferay-ui:search-container-results>

		<%
		List<Role> roles = RoleLocalServiceUtil.search(company.getCompanyId(), null, null, new Integer[] {RoleConstants.TYPE_COMMUNITY}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new RoleNameComparator(false));

		roles = EnterpriseAdminUtil.filterGroupRoles(permissionChecker, groupId, roles);

		total = roles.size();
		results = ListUtil.subList(roles, searchContainer.getStart(), searchContainer.getEnd());

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Role"
		escapedModel="<%= false %>"
		keyProperty="roleId"
		modelVar="role"
	>

		<%
		String rowHREF = "javascript:opener." + renderResponse.getNamespace() + "updateRole('" +  userId +  "', '" + groupId + "', '" + role.getRoleId() + "'); window.close();";
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="title"
			value="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="description"
			value="<%= HtmlUtil.escape(role.getDescription()) %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>