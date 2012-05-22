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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

CalendarResource calendarResource = (CalendarResource)request.getAttribute(WebKeys.CALENDAR_RESOURCE);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= LanguageUtil.format(pageContext, "x-calendars", calendarResource.getName(locale)) %>'
/>

<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, calendarResource, ActionKeys.ADD_CALENDAR) %>">
	<aui:button-row>
		<liferay-portlet:renderURL var="editCalendarURL">
			<liferay-portlet:param name="jspPage" value="/edit_calendar.jsp" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="calendarResourceId" value="<%= String.valueOf(calendarResource.getCalendarResourceId()) %>" />
		</liferay-portlet:renderURL>

		<aui:button onClick="<%= editCalendarURL %>" value="add-calendar" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-calendars-for-the-selected-resource"
	iteratorURL="<%= renderResponse.createRenderURL() %>"
>
	<liferay-ui:search-container-results
		results="<%= CalendarServiceUtil.search(themeDisplay.getCompanyId(), new long[] {calendarResource.getGroupId()}, new long[] {calendarResource.getCalendarResourceId()}, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new CalendarNameComparator(true)) %>"
		total="<%= CalendarServiceUtil.searchCount(themeDisplay.getCompanyId(), new long[] {calendarResource.getGroupId()}, new long[] {calendarResource.getCalendarResourceId()}, null, false) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.calendar.model.Calendar"
		keyProperty="calendarId"
		modelVar="calendar"
	>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= calendar.getName(locale) %>"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			value="<%= StringUtil.shorten(calendar.getDescription(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			align="center"
			name="color"
		>
			<span class="calendar-portlet-color-box" style="background-color:<%= ColorUtil.toHexString(calendar.getColor()) %>;">&nbsp;</span>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="default">
			<c:choose>
				<c:when test="<%= calendar.isDefaultCalendar() %>">
					<liferay-ui:message key="yes" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="no" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/calendar_action.jsp"
		/>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>