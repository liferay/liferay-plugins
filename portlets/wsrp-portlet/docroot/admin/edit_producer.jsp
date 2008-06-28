<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

String cmd = ParamUtil.getString(request, Constants.CMD);

Producer producer = (Producer)request.getAttribute("producer");

String producerId = BeanParamUtil.getString(producer, request, "producerId");

String name = BeanParamUtil.getString(producer, request, "name");

String wsdlURL = BeanParamUtil.getString(producer, request, "wsdlURL");
%>

<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="actionURL">
	<portlet:param name="struts_action" value="/wsrp_admin/edit_producer" />
	<portlet:param name="redirect" value="<%= HtmlUtil.escape(redirect) %>" />
</portlet:actionURL>

<form action="<%= actionURL %>" method="post" name="<portlet:namespace />fm">

<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= cmd %>" />
<input name="<portlet:namespace />producerId" type="hidden" value="<%= producerId %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />name" type="text" value="<%= name %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="wsdl-url" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />wsdlURL" type="text" value="<%= wsdlURL %>" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />
<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>