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

<%@ page import="com.liferay.sampledao.util.ConnectionPool" %>

<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Properties" %>

<span class="alert alert-danger">
An unexpected error occurred.
</span>

Please check that these database settings are correct:

<br /><br />

<%
Properties props = ConnectionPool.getProperties();

Enumeration enu = props.propertyNames();

while (enu.hasMoreElements()) {
	String key = (String)enu.nextElement();

	String value = props.getProperty(key);
%>

	<strong><%= key %></strong>=<%= value%><br />

<%
}
%>

<br />

You can change the database settings by modifying /WEB-INF/classes/connection-pool.properties.

<br /><br />

The SQL script to build the database is found in /WEB-INF/sql/sample-dao-mysql.sql.

<br /><br />

This portlet requires access to a <a href="http://www.mysql.com">MySQL</a> server.