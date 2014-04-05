<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AnnouncementsEntry entry = (AnnouncementsEntry)row.getObject();
%>

<c:if test="<%= permissionChecker.hasPermission(entry.getGroupId(), AnnouncementsEntry.class.getName(), entry.getClassPK(), ActionKeys.UPDATE) %>">
	<span class="action edit-entry">
		<portlet:renderURL var="editURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/edit_entry.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
		</portlet:renderURL>

		<a href="<%= editURL %>">
			<liferay-ui:icon
				image="edit"
				label="<%= true %>"
			/>
		</a>
	</span>
</c:if>

<c:if test="<%= permissionChecker.hasPermission(entry.getGroupId(), AnnouncementsEntry.class.getName(), entry.getClassPK(), ActionKeys.DELETE) %>">
	<span class="action delete-entry" data-entryId="<%= String.valueOf(entry.getEntryId()) %>">
		<liferay-ui:icon-delete
			label="<%= true %>"
			url="javascript:;"
		/>
	</span>
</c:if>