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

<%@ page import="com.liferay.client.soap.portal.model.OrganizationSoap" %>
<%@ page import="com.liferay.client.soap.portal.service.http.OrganizationServiceSoap" %>
<%@ page import="com.liferay.client.soap.portal.service.http.OrganizationServiceSoapServiceLocator" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<%@ page import="java.net.URL" %>

You belong to the following organizations:

<br /><br />

<%
String remoteUser = request.getRemoteUser();

long userId = GetterUtil.getLong(remoteUser);

OrganizationServiceSoapServiceLocator locator = new OrganizationServiceSoapServiceLocator();

OrganizationServiceSoap soap = locator.getPortal_OrganizationService(_getURL(remoteUser, "Portal_OrganizationService"));

OrganizationSoap[] organizations = soap.getUserOrganizations(userId);

for (int i = 0; i < organizations.length; i++) {
	OrganizationSoap organization = organizations[i];
%>

	<%= organization.getName() %><br />

<%
}
%>

<%!
private URL _getURL(String remoteUser, String serviceName) throws Exception {

	// Unathenticated url

	String url = "http://localhost:8080/tunnel-web/axis/" + serviceName;

	// Authenticated url

	if (true) {
		String password = "test";

		url = "http://" + remoteUser + ":" + password + "@localhost:8080/tunnel-web/secure/axis/" + serviceName;
	}

	return new URL(url);
}
%>