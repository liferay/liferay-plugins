<%
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
%>

<%@ include file="/init.jsp" %>

<%
String url = "http://gmodules.com/ig/ifr?url=" + gadgetId + "&synd=open&w=auto&title=&border=none&output=js";

String html = HttpUtil.URLtoString(url);

try {

	// Remove google footer

	int x = html.indexOf("/ig/add?");

	x = html.lastIndexOf("\\x3ctr", x);

	int y = html.indexOf("\\x3c/tr\\x3e", x);

	html = html.substring(0, x) + html.substring(y + 11);

	// Sometimes the gadget does not set its own height correctly

	html = html.replace("height\\x3d10 ", "height\\x3d100% ");
}
catch (Exception e) {
}
%>

<aui:script position="inline">
	<%= html %>
</aui:script>

<%--<script src="http://gmodules.com/ig/ifr?url=<%= gadgetId %>&synd=open&title=&border=none&output=js"></script>--%>
<%--<iframe frameborder="0" height="100%" src="http://www.gmodules.com/ig/ifr?nocache=1&url=<%= gadgetId %>" width="100%"></iframe>--%>