<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/calendar/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);

PortletURL tabs1URL = renderResponse.createRenderURL();

tabs1URL.setParameter("struts_action", "/calendar/view");
tabs1URL.setParameter("month", String.valueOf(selMonth));
tabs1URL.setParameter("day", String.valueOf(selDay));
tabs1URL.setParameter("year", String.valueOf(selYear));
%>

<div class="calendar-tabs">
	<h1 class="title">
		<liferay-ui:message key="calendar" />
	</h1>

	<%
	tabs1URL.setParameter("tabs1", "month");
	%>

	<a class="month <%= tabs1.equals("month") ? "selected" : "" %>" href="<%= tabs1URL.toString() %>"><liferay-ui:message key="month" /></a>

	<%
	tabs1URL.setParameter("tabs1", "week");
	%>

	<a class="week <%= tabs1.equals("week") ? "selected" : "" %>" href="<%= tabs1URL.toString() %>"><liferay-ui:message key="week" /></a>

	<%
	tabs1URL.setParameter("tabs1", "day");
	%>

	<a class="day <%= tabs1.equals("day") ? "selected" : "" %>" href="<%= tabs1URL.toString() %>"><liferay-ui:message key="day" /></a>
</div>