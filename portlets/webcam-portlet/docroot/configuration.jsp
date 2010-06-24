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

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<table class="lfr-table">
<tr>
	<td><liferay-ui:message key="url" /></td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />url" type="text" value="<%= url %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="height" />
	</td>
	<td>
		<input name="<portlet:namespace />height" size="4" type="text" value="<%= height %>" /> px
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="width" />
	</td>
	<td>
		<input name="<portlet:namespace />width" size="4" type="text" value="<%= width %>" /> px
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="message" />
	</td>
	<td>
		<textarea class="lfr-textarea" name="<portlet:namespace/>message" wrap="soft"><%= message %></textarea>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />url);
	</aui:script>
</c:if>