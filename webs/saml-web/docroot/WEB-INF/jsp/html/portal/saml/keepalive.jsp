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
<%@ page contentType="text/javascript; charset=UTF-8" %>

<%@ page import="com.liferay.portal.util.WebKeys" %>
<%@ page import="com.liferay.util.PwdGenerator" %>

<%@ page import="java.util.List" %>

<% 
	List<String> urls = (List<String>)request.getAttribute(WebKeys.KEEPALIVE_URL_LIST);
	String rand = PwdGenerator.getPassword();

	for (String url : urls) {
		%>
		document.write('<img src="<%= url %>?rand=<%= rand %>" alt=""/>');
		<%
	}
%>