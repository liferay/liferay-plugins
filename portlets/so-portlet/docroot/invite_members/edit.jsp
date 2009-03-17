<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
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