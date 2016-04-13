<%
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
%>

<%@ include file="/init.jsp" %>

<%
StringBundler sb = new StringBundler();

sb.append(themeDisplay.getPortalURL());
sb.append(PortalUtil.getPathContext());
sb.append(pageContext.getServletContext().getContextPath());
%>
<p>
	testForbiddenPath=<%=_testContainsText(sb.toString() + "/public/restricted/icon.png", "403", "PASSED", "FAILED") %>
</p>
<p>
	testForbiddenExtension=<%=_testContainsText(sb.toString() + "/public/web.xml", "403", "PASSED", "FAILED") %>
</p>
<p>
	testAllowed=<%=_testContainsText(sb.toString() + "/public/view.jsp", "PASSED", "PASSED", "FAILED") %>
</p>
<p>
	testFileAllowed=<%=_testBinarySize(sb.toString() + "/public/icon2.png", 467, "PASSED", "FAILED") %>
</p>
<p>
	testFileForbidden=<%=_testContainsText(sb.toString() + "/public/icon.png", "403", "PASSED", "FAILED") %>
</p>
<%!

private static String _testContainsText(String location, String expected, String textPassed, String textFailed) throws Exception {
	String text = HttpUtil.URLtoString(location);

	if (text.contains(expected)) {
		return textPassed;
	}
	else {
		return textFailed;
	}
}

private static String _testBinarySize(String location, long expected, String textPassed, String textFailed) throws Exception {
	byte[] file = HttpUtil.URLtoByteArray(location);

	if (file != null && file.length == expected) {
		return textPassed;
	}
	else {
		return textFailed;
	}
}
%>