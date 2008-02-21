<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ page import="com.liferay.client.portal.model.OrganizationSoap" %>
<%@ page import="com.liferay.client.portal.service.http.OrganizationServiceSoap" %>
<%@ page import="com.liferay.client.portal.service.http.OrganizationServiceSoapServiceLocator" %>
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