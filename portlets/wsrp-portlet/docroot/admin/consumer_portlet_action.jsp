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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)row.getObject();
%>

<liferay-ui:icon-menu>
	<%--<portlet:renderURL var="editURL">
		<portlet:param name="jspPage" value="/admin/edit_consumer_portlet.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="wsrpConsumerPortletId" value="<%= String.valueOf(wsrpConsumerPortlet.getWsrpConsumerPortletId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		url="<%= editURL %>"
	/>--%>

	<portlet:actionURL name="deleteWSRPConsumerPortlet" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="wsrpConsumerPortletId" value="<%= String.valueOf(wsrpConsumerPortlet.getWsrpConsumerPortletId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>