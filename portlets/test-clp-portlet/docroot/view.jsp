<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ page import="com.liferay.chat.NoSuchStatusException" %>
<%@ page import="com.liferay.chat.model.Status" %>
<%@ page import="com.liferay.chat.service.StatusLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>

<%@ page import="java.util.List" %>

<%
List<Status> statuses = StatusLocalServiceUtil.getStatuses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

There are <%= statuses.size() %> statuses.

<br /><br />

<table border="1" cellpadding="4" cellspacing="0" width="100%">
<tr>
	<td>
		<strong>Status ID</strong>
	</td>
	<td>
		<strong>User ID</strong>
	</td>
	<td>
		<strong>Modified Date</strong>
	</td>
	<td>
		<strong>Online</strong>
	</td>
	<td>
		<strong>Awake</strong>
	</td>
	<td>
		<strong>Active Panel ID</strong>
	</td>
	<td>
		<strong>Message</strong>
	</td>
	<td>
		<strong>Play Sound</strong>
	</td>
</tr>

<%
for (Status status : statuses) {
%>

	<tr>
		<td>
			<%= status.getStatusId() %>
		</td>
		<td>
			<%= status.getUserId() %>
		</td>
		<td>
			<%= status.getModifiedDate() %>
		</td>
		<td>
			<%= status.isOnline() %>
		</td>
		<td>
			<%= status.isAwake() %>
		</td>
		<td>
			<%= status.getActivePanelId() %>
		</td>
		<td>
			<%= status.getMessage() %>
		</td>
		<td>
			<%= status.isPlaySound() %>
		</td>
	</tr>

<%
}
%>

</table>

<br />

<%
try {
	StatusLocalServiceUtil.getStatus(1);
}
catch (NoSuchStatusException nsse) {
%>

	NoSuchStatusException was properly caught.

<%
}
%>