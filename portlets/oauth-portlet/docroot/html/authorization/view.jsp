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

<%@ page import="com.liferay.portal.kernel.servlet.SessionMessages" %>
<%@ page import="com.liferay.portal.oauth.model.ApplicationUser" %>
<%@ page import="com.liferay.portlet.oauth.search.OAuthApplicationDisplayTerms" %>
<%@ page import="com.liferay.portlet.oauth.search.OAuthApplicationSearchTerms" %>

<%@ include file="/html/init.jsp" %>

<%
long userId = themeDisplay.getUserId();
%>

<portlet:actionURL var="searchActionURL">
</portlet:actionURL>

<aui:form action="<%= searchActionURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<liferay-ui:search-container delta="5">

	<%
		List<ApplicationUser> applications = ApplicationUserLocalServiceUtil.getApplicationUsersByUserId(userId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
		int applicationsCount = ApplicationUserLocalServiceUtil.getApplicationUsersByUserIdCount(userId);
	%>

	<liferay-ui:search-container-results
	results="<%= applications %>"
	total="<%= applicationsCount %>"
	 />

	<liferay-ui:search-container-row
		className="com.liferay.portal.oauth.model.ApplicationUser"
		keyProperty="oaauId"
		modelVar="applicationUser"
		>

		<%
		Application app = ApplicationLocalServiceUtil.getApplication(applicationUser.getApplicationId());
		%>

		<liferay-ui:search-container-column-text
			name="oaauid"
			orderable="<%= true %>"
			value="<%= String.valueOf(applicationUser.getOaauId()) %>"
		/>
		<liferay-ui:search-container-column-text
			name="application-id-short"
			orderable="<%= true %>"
			value="<%= String.valueOf(applicationUser.getApplicationId()) %>"
		/>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= app.getName() %>"
		/>
		<liferay-ui:search-container-column-text
			name="access-token"
			value="<%= applicationUser.getAccessToken() %>"
		/>
		<liferay-ui:search-container-column-text
			name="access-type"
		>
			<liferay-ui:message key='<%= OAuthConstants.ACCESS_TYPE_SHORT.replace("{0}", Integer.toString(app.getAccessLevel())) %>' />
		</liferay-ui:search-container-column-text>

		<c:if test="<%= permissionChecker.hasPermission(0, ApplicationUser.class.getName(), applicationUser.getApplicationId(), ActionKeys.DELETE) %>">
				<liferay-portlet:actionURL name="deleteApplicationUser" var="revokeURL">
					<portlet:param name="<%= OAuthConstants.APPLICATION_ID %>" value="<%= String.valueOf(applicationUser.getApplicationId()) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:search-container-column-text
					href="<%= revokeURL %>"
				>
					<liferay-ui:message key="revoke" />
				</liferay-ui:search-container-column-text>
			</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
</aui:form>