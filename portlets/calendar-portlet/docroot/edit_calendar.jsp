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

<%@ include file="/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	redirect = PortalUtil.getCurrentURL(request);
}

String backURL = ParamUtil.getString(request, "backURL");

Calendar calendar = (Calendar)request.getAttribute(WebKeys.CALENDAR);

CalendarResource calendarResource = (CalendarResource)request.getAttribute(WebKeys.CALENDAR_RESOURCE);
%>

<liferay-portlet:renderURL var="portletURL">
	<portlet:param name="mvcPath" value="/edit_calendar.jsp" />
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="backURL" value="<%= backURL %>" />
	<portlet:param name="calendarId" value="<%= (calendar != null) ? String.valueOf(calendar.getCalendarId()) : StringPool.BLANK %>" />
	<portlet:param name="calendarResourceId" value="<%= (calendarResource != null) ? String.valueOf(calendarResource.getCalendarResourceId()) : StringPool.BLANK %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names='<%= (calendar == null) ? "general" : "general,notification-templates" %>'
	param="tabs2"
	url="<%= portletURL %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("general") %>'>
		<%@ include file="/edit_calendar_general.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("notification-templates") %>'>
		<%@ include file="/edit_calendar_notification_templates.jspf" %>
	</c:when>
</c:choose>