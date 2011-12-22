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

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/basic.jsp");
%>

<liferay-ui:search-container
	headerNames="email-address,screen-name"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results>

		<%
		results = UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd());
		total = UserLocalServiceUtil.getUsersCount();

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="curUser"
	>
		<liferay-ui:search-container-column-text
			name="email-address"
			property="emailAddress"
		/>

		<liferay-ui:search-container-column-text
			name="screen-name"
			property="screenName"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="separator"></div>

<a href="<portlet:renderURL />">&laquo; Back</a>