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

<%@ include file="/html/portlet/sample_struts_portlet/init.jsp" %>

<%
Locale locale = (Locale)session.getAttribute("org.apache.struts.action.LOCALE");

ResourceBundle bundle = ResourceBundle.getBundle("content.test.Language", locale);
%>

<!--
You can use the Struts tag library or build your own Java resource bundle. Both
refer to the same properties files in located in
/WEB-INF/classes/content/test/Language.properties.

<strong>View <bean:message key="hello-there" /></strong>
-->

<strong>View <%= bundle.getString("hello-there") %></strong>