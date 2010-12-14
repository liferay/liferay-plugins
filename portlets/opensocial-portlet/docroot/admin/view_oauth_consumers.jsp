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
String redirect = ParamUtil.getString(request, "redirect");
long gadgetId = ParamUtil.getLong(request, "gadgetId");

Gadget gadget = GadgetLocalServiceUtil.getGadget(gadgetId);

GadgetSpec gadgetSpec = null;

Map<String, OAuthService> oAuthServicesMap = null;

try {
	gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

	oAuthServicesMap = ShindigUtil.getOAuthServices(gadgetSpec);
}
catch (Exception e) {
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= gadget.getName() %>'
/>

<liferay-ui:search-container>
	<liferay-ui:search-container-results>
		<%
		List<OAuthService> oAuthServices = new ArrayList<OAuthService>();

		for (Map.Entry<String, OAuthService> oAuthServiceEntry : oAuthServicesMap.entrySet()) {
			oAuthServices.add(oAuthServiceEntry.getValue());
		}

		pageContext.setAttribute("results", oAuthServices);
		pageContext.setAttribute("total", oAuthServices.size());
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="org.apache.shindig.gadgets.spec.OAuthService"
		escapedModel="<%= false %>"
		keyProperty="name"
		modelVar="oAuthService"
	>

		<%
		String serviceName = oAuthService.getName();
		%>

		<portlet:renderURL var="rowURL">
			<portlet:param name="jspPage" value="/admin/edit_oauth_consumer.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadgetId) %>" />
			<portlet:param name="serviceName" value= "<%= serviceName %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="name"
			property="name"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/oauth_consumer_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<br />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-oauth"), currentURL);
%>