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
CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);
Calendar calendar = CalendarServiceUtil.getCalendar(calendarBooking.getCalendarId());

Date startDate = JCalendarUtil.getJCalendar(calendarBooking.getStartDate(), user.getTimeZone()).getTime();
Date endDate = JCalendarUtil.getJCalendar(calendarBooking.getEndDate(), user.getTimeZone()).getTime();
%>

<liferay-ui:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
	<liferay-ui:custom-attribute-list
		className="<%= CalendarBooking.class.getName() %>"
		classPK="<%= (calendarBooking != null) ? calendarBooking.getCalendarBookingId() : 0 %>"
		editable="<%= false %>"
		label="<%= true %>"
	/>
</liferay-ui:custom-attributes-available>

<div>
	<p><span class="aui-field-label-inline-label"><liferay-ui:message key="calendar" /></span>: <%=  HtmlUtil.escape(calendar.getName(locale)) %></p>
	<c:if test="<%= Validator.isNotNull(calendarBooking.getDescription(locale)) %>">
		<p class="calendar-booking-description"><%=  HtmlUtil.escape(calendarBooking.getDescription(locale)) %></p>
	</c:if>
	<c:if test="<%=  Validator.isNotNull(calendarBooking.getLocation()) %>">
		<p>
			<span class="aui-field-label-inline-label"><liferay-ui:message key="location" /></span>: <%= HtmlUtil.escape(calendarBooking.getLocation()) %>
		</p>
	</c:if>
	<p>
		<liferay-ui:icon
			image="../common/date"
		/>
		<span class="aui-field-label-inline-label"><liferay-ui:message key="start-date" /></span>: <%= dateFormatLongDate.format(startDate) %>
	</p>
	<p>
		<liferay-ui:icon
			image="../common/date"
		/>
		<span class="aui-field-label-inline-label"><liferay-ui:message key="end-date" /></span>: <%= dateFormatLongDate.format(endDate) %>
	</p>
</div>