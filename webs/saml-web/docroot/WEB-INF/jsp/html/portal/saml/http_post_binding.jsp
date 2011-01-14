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

<%@ include file="/html/common/init.jsp" %>

<html dir="<liferay-ui:message key="lang.dir" />">

<head>
	<meta content="<%= ContentTypes.TEXT_HTML_UTF8 %>" http-equiv="content-type" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="0" http-equiv="Expires" />
</head>
<body onLoad="document.forms[0].submit()">
<noscript>
<p>
<strong>Note:</strong> Since your browser does not support
Javascript, you must press the Continue button once to proceed.
</p>
</noscript>
<%
	String action = (String)request.getAttribute("action");
	String relayState = (String)request.getAttribute("RelayState");
	String samlResponse = (String)request.getAttribute("SAMLResponse");
%>
<form action="<%= action %>" method="post">
<div>
<input type="hidden" name="SAMLResponse" value="<%= samlResponse %>"/>
<% if (relayState != null) { %>
<input type="hidden" name="RelayState" value="<%= relayState %>"/>
<% } %>
</div>

<noscript>
<div>
<input type="submit" value="Continue"/>
</div>
</noscript>
</form>

</body>
</html>
