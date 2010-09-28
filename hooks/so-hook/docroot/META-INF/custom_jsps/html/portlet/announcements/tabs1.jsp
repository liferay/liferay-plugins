<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/html/portlet/announcements/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "entries");
%>

<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, PortletKeys.ANNOUNCEMENTS, ActionKeys.ADD_ENTRY) && tabs1.equals("entries") %>'>
	<div class="edit-controls">
		<input type="button" value='<liferay-ui:message key="add-entry" />' onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/announcements/edit_entry" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>'" />

		<input type="button" value='<liferay-ui:message key="manage-entries" />' onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/announcements/view" /><portlet:param name="tabs1" value="manage-entries" /></portlet:renderURL>'" />
	</div>
</c:if>