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

<div>

	<%
	CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);
	%>

	<c:if test="<%= Validator.isNotNull(calendarBooking.getDescription(locale)) %>">
		<p>
			<%= HtmlUtil.escape(calendarBooking.getDescription(locale)) %>
		</p>
	</c:if>

	<p>
		<liferay-ui:icon
			image="../common/user_icon"
			message="owner"
		/>

		<%
		Calendar calendar = calendarBooking.getCalendar();
		%>

		<strong><%= HtmlUtil.escape(calendar.getName(locale)) %></strong>

		<%
		List<CalendarBooking> childCalendarBookings = calendarBooking.getChildCalendarBookings();
		%>

		<c:if test="<%= !childCalendarBookings.isEmpty() %>">
			<br />

			<liferay-ui:icon
				image="../common/organization_icon"
				message="resources"
			/>

			<liferay-ui:message key="resources" />:

			<%
			List<String> calendarResourcesNames = new ArrayList<String>();

			for (CalendarBooking childCalendarBooking : childCalendarBookings) {
				CalendarResource calendarResource = childCalendarBooking.getCalendarResource();

				calendarResourcesNames.add(calendarResource.getName(locale));
			}
			%>

			<%= StringUtil.merge(calendarResourcesNames, ", ") %>
		</c:if>

		<c:if test="<%= calendarBooking.isRecurring() %>">
			<br /><br />

			<liferay-ui:icon
				image="../common/site_template"
				message="recurring"
			/>

			<liferay-ui:message key="recurring" />
		</c:if>

		<br /><br />

		<liferay-ui:icon
			image="../common/revision"
			message="start-date"
		/>

		<%
		java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(calendarBooking.getStartDate(), user.getTimeZone());
		%>

		<liferay-ui:message key="start-date" />: <%= dateFormatLongDate.format(startDateJCalendar.getTime()) + ", " + dateFormatTime.format(startDateJCalendar.getTime()) %>

		<br />

		<liferay-ui:icon
			image="../common/revision"
			message="end-date"
		/>

		<%
		java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(calendarBooking.getEndDate(), user.getTimeZone());
		%>

		<liferay-ui:message key="end-date" />: <%= dateFormatLongDate.format(endDateJCalendar.getTime()) + ", " + dateFormatTime.format(endDateJCalendar.getTime()) %>

		<c:if test="<%= Validator.isNotNull(calendarBooking.getLocation()) %>">
			<br /><br />

			<liferay-ui:icon
				image="../common/view_locations"
				message="location"
			/>

			<liferay-ui:message key="location" />: <a href="https://maps.google.com.br/maps?q=<%= HtmlUtil.escapeHREF(calendarBooking.getLocation()) %>" target="_blank"><%= HtmlUtil.escape(calendarBooking.getLocation()) %></a>
		</c:if>
	</p>
</div>

<br />