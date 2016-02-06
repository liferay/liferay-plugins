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

<%@ page import="com.liferay.client.soap.portal.model.OrganizationSoap" %>
<%@ page import="com.liferay.client.soap.portal.service.http.OrganizationServiceSoap" %>
<%@ page import="com.liferay.client.soap.portal.service.http.OrganizationServiceSoapServiceLocator" %>
<%@ page import="com.liferay.portal.kernel.model.Company" %>
<%@ page import="com.liferay.portal.kernel.model.CompanyConstants" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>

<%@ page import="java.net.URL" %>

<%@ page import="javax.xml.rpc.Stub" %>

You belong to the following organizations:

<br /><br />

<%
OrganizationServiceSoap organizationServiceSoap = _getOrganizationServiceSoap(request);

OrganizationSoap[] organizationSoaps = organizationServiceSoap.getUserOrganizations(_getUserId(request));

for (OrganizationSoap organizationSoap : organizationSoaps) {
%>

	<%= organizationSoap.getName() %><br />

<%
}
%>

<%!
private OrganizationServiceSoap _getOrganizationServiceSoap(HttpServletRequest request) throws Exception {
	OrganizationServiceSoapServiceLocator organizationServiceSoapServiceLocator = new OrganizationServiceSoapServiceLocator();

	OrganizationServiceSoap organizationServiceSoap = organizationServiceSoapServiceLocator.getPortal_OrganizationService(new URL("http://localhost:8080/api/axis/Portal_OrganizationService"));

	Stub stub = (Stub)organizationServiceSoap;

	stub._setProperty(Stub.PASSWORD_PROPERTY, "test");
	stub._setProperty(Stub.USERNAME_PROPERTY, _getUserName(request));

	return organizationServiceSoap;
}

private long _getUserId(HttpServletRequest request) throws Exception {
	String remoteUser = request.getRemoteUser();

	return GetterUtil.getLong(remoteUser);
}

private String _getUserName(HttpServletRequest request) throws Exception {
	long userId = _getUserId(request);

	User user = UserLocalServiceUtil.getUserById(userId);

	Company company = PortalUtil.getCompany(request);

	String authType = company.getAuthType();

	if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
		return user.getEmailAddress();
	}
	else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
		return user.getScreenName();
	}
	else {
		return String.valueOf(userId);
	}
}
%>