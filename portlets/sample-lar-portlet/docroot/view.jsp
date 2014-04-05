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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="java.util.Date" %>

<portlet:defineObjects />

<%
long exportDate = Long.parseLong(portletPreferences.getValue("last-export-date", "0"));
long importDate = Long.parseLong(portletPreferences.getValue("last-import-date", "0"));
%>

This is the <strong>Sample LAR Portlet</strong>. This was made to demonstrate the portlet
LAR plugin feature.

<br /><br />

This portlet plugin allows you to store data in the LAR file (Liferay Archive)
when the portlet exists in the Community being exported.

<br /><br />

Date of last export:

<%
if (exportDate == 0) {
%>

	Never

<%
}
else {
%>

	<%= new Date(exportDate) %>

<%
}
%>

<br />

Date of last import:

<%
if (importDate == 0) {
%>

	Never

<%
}
else {
%>

	<%= new Date(importDate) %>

<%
}
%>