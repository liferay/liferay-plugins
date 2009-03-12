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
PortletPreferences preferences = renderRequest.getPreferences();

String body = preferences.getValue("body", StringPool.BLANK);
String subject = preferences.getValue("subject", StringPool.BLANK);
%>

<style type="text/css">
	.profile-wrapper {
		display: none;
	}
</style>

<h1>
	<liferay-ui:message key="invite-members" />
</h1>

<form action="<liferay-portlet:actionURL portletMode="<%= PortletMode.VIEW.toString() %>" name="updateEmail" />" method="post" name="<portlet:namespace />fm">

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="subject" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />subject" type="text" value="<%= subject %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="body" />
	</td>
	<td>
		<textarea class="lfr-textarea" name="<portlet:namespace />body"><%= body %></textarea>
	</td>
</tr>
</table>

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>