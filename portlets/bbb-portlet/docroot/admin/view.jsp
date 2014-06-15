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

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-servers"
	iteratorURL="<%= portletURL %>"
	total="<%= BBBServerLocalServiceUtil.getBBBServersCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= BBBServerLocalServiceUtil.getBBBServers(searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
	/>

	<aui:button-row>
		<portlet:renderURL var="addBBBServerURL">
			<portlet:param name="mvcPath" value="/admin/edit_server.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		</portlet:renderURL>

		<aui:button onClick="<%= addBBBServerURL.toString() %>" value="add-server" />
	</aui:button-row>

	<liferay-ui:search-container-row
		className="com.liferay.bbb.model.BBBServer"
		escapedModel="<%= true %>"
		keyProperty="bbbServerId"
		modelVar="bbbServer"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcPath" value="/admin/edit_server.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="bbbServerId" value="<%= String.valueOf(bbbServer.getBbbServerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="api-url"
			property="url"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="secret"
			property="secret"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="active"
			property="active"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/admin/server_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>