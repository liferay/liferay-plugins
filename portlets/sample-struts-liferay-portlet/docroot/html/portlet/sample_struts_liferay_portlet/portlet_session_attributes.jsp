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

<%@ include file="/html/portlet/sample_struts_liferay_portlet/init.jsp" %>

<b>Remote User:</b>

<br><br>

<%= request.getRemoteUser() %>

<br><br>

<b>Session ID:</b>

<br><br>

<%= request.getRequestedSessionId() %>

<br><br>

<b>Portlet Session Attributes:</b>

<br><br>

<i>Portlet Scope</i>

<br><br>

<%
Enumeration enu = portletSession.getAttributeNames();

while (enu.hasMoreElements()) {
	String attrName = (String)enu.nextElement();

	Object attrValue = portletSession.getAttribute(attrName);
%>

	<%= attrName %>=<%= attrValue %><br>

<%
}
%>

<br>

<i>Application Scope</i>

<br><br>

<%
enu = portletSession.getAttributeNames(PortletSession.APPLICATION_SCOPE);

while (enu.hasMoreElements()) {
	String attrName = (String)enu.nextElement();

	Object attrValue = portletSession.getAttribute(attrName, PortletSession.APPLICATION_SCOPE);
%>

	<%= attrName %>=<%= attrValue %><br>

<%
}
%>