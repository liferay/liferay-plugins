<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "organizations");

String keywords = ParamUtil.getString(request, "keywords");
String searchFilter = ParamUtil.getString(request, "searchFilter");

Role role = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

RowChecker organizationRoleChecker = (RowChecker)InstanceFactory.newInstance(PortalClassLoaderUtil.getClassLoader(), "com.liferay.portlet.rolesadmin.search.OrganizationRoleChecker", new Class<?>[] {RenderResponse.class, Role.class}, new Object[] {renderResponse, role});
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="searchFilter" value="<%= searchFilter %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-organization-was-found-that-is-a-member-of-social-office"
	iteratorURL="<%= portletURL %>"
	rowChecker="<%= organizationRoleChecker %>"
>

	<%
	LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	if (searchFilter.equals("current")) {
		if (Validator.isNotNull(role)) {
			params.put("organizationsRoles", role.getRoleId());
		}

		total = OrganizationLocalServiceUtil.searchCount(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, keywords, null, null, null, params);

		searchContainer.setTotal(total);

		searchContainer.setResults(OrganizationLocalServiceUtil.search(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, keywords, null, null, null, params, searchContainer.getStart(), searchContainer.getEnd()));
	}
	else {
		total = OrganizationLocalServiceUtil.searchCount(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, keywords, null, null, null, null);

		searchContainer.setTotal(total);

		searchContainer.setResults(OrganizationLocalServiceUtil.search(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, keywords, null, null, null, null, searchContainer.getStart(), searchContainer.getEnd()));
	}
	%>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="group.groupId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="parent-organization"
		>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="type"
			orderable="<%= true %>"
			value="<%= LanguageUtil.get(request, organization.getType()) %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>