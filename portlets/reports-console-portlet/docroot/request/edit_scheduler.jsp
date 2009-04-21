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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long definitionId = ParamUtil.getLong(request, "definitionId");
long requestId = ParamUtil.getLong(request, "requestId");
%>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="addScheduler" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" >
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />definitionId" type="hidden" value="<%= definitionId %>" />
<input name="<portlet:namespace />requestId" type="hidden" value="<%= requestId %>" />

<liferay-ui:error exception="<%= EventDurationException.class %>" message="please-enter-a-longer-duration" />
<liferay-ui:error exception="<%= EventStartDateException.class %>" message="please-enter-a-valid-start-date" />
<liferay-ui:error exception="<%= EventTitleException.class %>" message="please-enter-a-valid-title" />

<liferay-ui:input-scheduler />

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>

