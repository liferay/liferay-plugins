<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
WindowState windowState = renderRequest.getWindowState();

if (windowState.equals(WindowState.MAXIMIZED)) {
	String cur = ParamUtil.getString(request, "cur", "1");
%>

	<img src="<%= request.getContextPath() %>/photo_gallery/photo_<%= cur %>_large.jpg" /><br />

	<%
	if (cur.equals("1")) {
	%>

		<a href="<portlet:renderURL><portlet:param name="cur" value="2" /></portlet:renderURL>">Next</a><br />

	<%
	}
	else {
	%>

		<a href="<portlet:renderURL><portlet:param name="cur" value="1" /></portlet:renderURL>">Previous</a><br />

	<%
	}
	%>

<%
}
else {
%>

	<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" />">
	<img src="<%= request.getContextPath() %>/photo_gallery/photo_1_thumbnail.jpg" />
	</a>

<%
}
%>