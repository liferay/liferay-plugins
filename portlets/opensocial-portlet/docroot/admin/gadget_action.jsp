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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Gadget gadget = (Gadget)row.getObject();

GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

Map<String, OAuthService> oAuthServices = ShindigUtil.getOAuthServices(gadgetSpec);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= (oAuthServices != null) && (oAuthServices.size() > 0) %>">
		<portlet:renderURL var="configureOAuthURL">
			<portlet:param name="jspPage" value="/admin/configure_oauth.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="portlet" message="configure-oauth" url="<%= configureOAuthURL %>" />
	</c:if>

	<portlet:actionURL name="deleteGadget" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>