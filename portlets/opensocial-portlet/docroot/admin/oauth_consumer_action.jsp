<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
long gadgetId = ParamUtil.getLong(request, "gadgetId");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

OAuthService oAuthService = (OAuthService)row.getObject();

String serviceName = oAuthService.getName();

OAuthConsumer oAuthConsumer = null;

try {
	oAuthConsumer = OAuthConsumerLocalServiceUtil.getOAuthConsumer(gadgetId, serviceName);
}
catch (NoSuchOAuthConsumerException nsoce) {
}
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL">
		<portlet:param name="jspPage" value="/admin/edit_oauth_consumer.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="gadgetId" value="<%= String.valueOf(gadgetId) %>" />
		<portlet:param name="serviceName" value="<%= serviceName %>" />
	</portlet:renderURL>

	<c:choose>
		<c:when test="<%= oAuthConsumer == null %>">
			<liferay-ui:icon
				image="add"
				url="<%= editURL %>"
			/>
		</c:when>

		<c:otherwise>
			<liferay-ui:icon
				image="edit"
				url="<%= editURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>