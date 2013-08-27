<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/admin/init.jsp" %>

<portlet:renderURL var="editOAuthConnectionURL">
	<portlet:param name="mvcPath" value="/admin/edit_oauth_connection.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:renderURL>

<aui:button-row>
	<aui:button href="<%= editOAuthConnectionURL %>" value="add-oauth-connection" />
</aui:button-row>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-oauth-connection"
>

	<liferay-ui:search-container-results
		results="<%= OAuthConnectionLocalServiceUtil.getOAuthConnections(searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= OAuthConnectionLocalServiceUtil.getOAuthConnectionsCount() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.oauthlogin.model.OAuthConnection"
		modelVar="oAuthConnection"
	>

		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcPath" value="/admin/edit_oauth_connection.jsp" />
			<portlet:param name="oAuthConnectionId" value="<%= String.valueOf(oAuthConnection.getOAuthConnectionId()) %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="id"
			value="<%= String.valueOf(oAuthConnection.getOAuthConnectionId()) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="name"
			value="<%= oAuthConnection.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="enabled"
			value="<%= String.valueOf(oAuthConnection.getEnabled()) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/oauth_connection_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>