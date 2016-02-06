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

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/advanced.jsp");
%>

<liferay-ui:search-container
	delta="<%= 5 %>"
	headerNames="email-address,screen-name,user-id"
	iteratorURL="<%= portletURL %>"
	total="<%= UserLocalServiceUtil.getUsersCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="curUser"
	>
		<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/advanced_user_display.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="userId" value="<%= String.valueOf(curUser.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-row-parameter
			name="rowURL"
			value="<%= rowURL.toString() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="left"
			path="/advanced_column.jsp"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="email-address"
		>

			<%
			buffer.append("<a href=\"");
			buffer.append(rowURL.toString());
			buffer.append("\">");
			buffer.append(curUser.getEmailAddress());
			buffer.append("</a>");
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="screen-name"
			property="screenName"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="user-id"
			value="<%= String.valueOf(curUser.getUserId()) %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="separator"></div>

<a href="<portlet:renderURL />">&laquo; Back</a>