<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/calendar/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

CalEvent event = (CalEvent)request.getAttribute(WebKeys.CALENDAR_EVENT);
%>

<c:if test="<%= !windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<liferay-util:include page="/html/portlet/calendar/sidebar.jsp" />
</c:if>

<liferay-util:include page="/html/portlet/calendar/view_event.jsp" useCustomPage="<%= false %>" />

<c:if test="<%= CalEventPermission.contains(permissionChecker, event, ActionKeys.UPDATE) %>">
	<br />

	<portlet:renderURL windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>" var="editURL">
		<portlet:param name="struts_action" value="/calendar/edit_event" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="eventId" value="<%= String.valueOf(event.getEventId()) %>" />
	</portlet:renderURL>

	<input type="button" value="<liferay-ui:message key="edit" />" onclick="location.href='<%= HtmlUtil.escape(editURL) %>'" />
</c:if>

<c:if test="<%= CalEventPermission.contains(permissionChecker, event, ActionKeys.DELETE) %>">
	<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
		<portlet:param name="struts_action" value="/calendar/edit_event" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="eventId" value="<%= String.valueOf(event.getEventId()) %>" />
	</portlet:actionURL>

	<input type="button" value="<liferay-ui:message key="delete" />" onclick="javascript:if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) { submitForm(document.hrefFm, '<%= deleteURL %>'); } else { self.focus(); }" />
</c:if>