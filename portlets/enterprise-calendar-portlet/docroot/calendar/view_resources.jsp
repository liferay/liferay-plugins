<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/calendar/init.jsp" %>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/calendar/view_resources.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<liferay-ui:search-form
		page="/calendar/resource_search.jsp"
		servletContext="<%= application %>"
	/>

</aui:form>

<div class="separator"><!-- --></div>

<c:if test="<%= EnterpriseCalendarPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.ADD_RESOURCE) %>">
	<aui:button-row>
		<liferay-portlet:renderURL var="editResourceURL">
			<liferay-portlet:param name="jspPage" value="/calendar/edit_resource.jsp" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
		</liferay-portlet:renderURL>

		<aui:button onClick="<%= editResourceURL %>" value="add-calendar-resource" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container searchContainer="<%= new CalendarResourceSearch(renderRequest, currentURLObj) %>">

	<%@ include file="/calendar/resource_search_results.jspf" %>

	<liferay-ui:search-container-results
		results="<%= resultsResources %>"
		total="<%= totalResources %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.calendar.model.CalendarResource"
		keyProperty="calendarResourceId"
		modelVar="resource"
	>
		<liferay-ui:search-container-column-text
			name="code"
			orderable="<%= true %>"
		/>

		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			value="<%= resource.getName(locale) %>"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			value="<%= StringUtil.shorten(resource.getDescription(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			name="type"
		/>

		<liferay-ui:search-container-column-text name="active">
			<c:choose>
				<c:when test="<%= resource.isActive() %>">
					<liferay-ui:message key="yes" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="no" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/calendar/resource_action.jsp"
		/>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>