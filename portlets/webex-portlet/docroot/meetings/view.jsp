<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/meetings/view.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-sites"
	iteratorURL="<%= iteratorURL %>"
	total="<%= WebExSiteServiceUtil.getWebExSitesCount(themeDisplay.getScopeGroupId()) %>"
>
	<liferay-ui:search-container-results
		results="<%= WebExSiteServiceUtil.getWebExSites(themeDisplay.getScopeGroupId(), searchContainer.getStart(), searchContainer.getEnd(), null) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.meeting.webex.model.WebExSite"
		keyProperty="webExSiteId"
		modelVar="webExSite"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcPath" value="/meetings/view_site.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="webExSiteId" value="<%= String.valueOf(webExSite.getWebExSiteId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="api-url"
			property="apiURL"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			property="login"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/meetings/site_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<c:if test="<%= WebExPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.ADD_SITE) %>">
		<aui:button-row>
			<portlet:renderURL var="addSiteURL">
				<portlet:param name="mvcPath" value="/meetings/edit_site.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<aui:button onClick="<%= addSiteURL.toString() %>" value="add-site" />
		</aui:button-row>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>