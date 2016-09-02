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

<%@ include file="/init.jsp" %>

<%
StringBundler sb = new StringBundler(3);

sb.append(themeDisplay.getPortalURL());
sb.append(PortalUtil.getPathContext());
sb.append(pageContext.getServletContext().getContextPath());
%>

<p>
	Allowed File=<%= _testBinarySize(sb.toString() + "/public/icon_2.png", 467) %>
</p>

<p>
	Allowed Path=<%= _testContainsText(sb.toString() + "/public/view.jsp", "PASSED") %>
</p>

<p>
	Forbidden Extension=<%= _testContainsText(sb.toString() + "/public/web.xml", "403") %>
</p>

<p>
	Forbidden File =<%= _testContainsText(sb.toString() + "/public/icon_1.png", "403") %>
</p>

<p>
	Forbidden Path=<%= _testContainsText(sb.toString() + "/public/restricted/icon_3.png", "403") %>
</p>

<%!
private static String _testBinarySize(String location, long expected) throws Exception {
	byte[] bytes = HttpUtil.URLtoByteArray(location);

	if ((bytes != null) && (bytes.length == expected)) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private static String _testContainsText(String location, String expected) throws Exception {
	String text = HttpUtil.URLtoString(location);

	if (text.contains(expected)) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}
%>