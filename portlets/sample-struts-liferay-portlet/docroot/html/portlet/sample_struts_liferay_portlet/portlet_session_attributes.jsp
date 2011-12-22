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

<%@ include file="/html/portlet/sample_struts_liferay_portlet/init.jsp" %>

<strong>Remote User:</strong>

<br /><br />

<%= request.getRemoteUser() %>

<br /><br />

<strong>Session ID:</strong>

<br /><br />

<%= request.getRequestedSessionId() %>

<br /><br />

<strong>Portlet Session Attributes:</strong>

<br /><br />

<em>Portlet Scope</em>

<br /><br />

<%
Enumeration enu = portletSession.getAttributeNames();

while (enu.hasMoreElements()) {
	String attrName = (String)enu.nextElement();

	Object attrValue = portletSession.getAttribute(attrName);
%>

	<%= attrName %>=<%= attrValue %><br />

<%
}
%>

<br />

<em>Application Scope</em>

<br /><br />

<%
enu = portletSession.getAttributeNames(PortletSession.APPLICATION_SCOPE);

while (enu.hasMoreElements()) {
	String attrName = (String)enu.nextElement();

	Object attrValue = portletSession.getAttribute(attrName, PortletSession.APPLICATION_SCOPE);
%>

	<%= attrName %>=<%= attrValue %><br />

<%
}
%>