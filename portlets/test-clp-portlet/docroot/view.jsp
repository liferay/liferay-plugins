<%--
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
--%>

<%@ page import="com.liferay.chat.exception.NoSuchStatusException" %>
<%@ page import="com.liferay.chat.model.Status" %>
<%@ page import="com.liferay.chat.service.StatusLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>

<%@ page import="java.util.List" %>

<%
List<Status> statuses = StatusLocalServiceUtil.getStatuses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

There are <%= statuses.size() %> statuses.

<br /><br />

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>
				Status ID
			</th>
			<th>
				User ID
			</th>
			<th>
				Modified Date
			</th>
			<th>
				Online
			</th>
			<th>
				Awake
			</th>
			<th>
				Active Panel ID
			</th>
			<th>
				Message
			</th>
			<th>
				Play Sound
			</th>
		</tr>
	</thead>

	<%
	for (Status status : statuses) {
	%>

		<tbody>
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
					<%= status.getActivePanelIds() %>
				</td>
				<td>
					<%= status.getMessage() %>
				</td>
				<td>
					<%= status.isPlaySound() %>
				</td>
			</tr>
		<tbody>

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