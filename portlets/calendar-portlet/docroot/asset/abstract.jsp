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
Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarBooking.getCalendarId());

Date startDate = JCalendarUtil.getJCalendar(calendarBooking.getStartDate(), user.getTimeZone()).getTime();
%>

<div>
	<p>
		<%=  HtmlUtil.escape(calendarBooking.getTitle(locale)) %> (<strong><%=  HtmlUtil.escape(calendar.getName(locale)) %></strong>)
	</p>
	<br />
	<p>
		<liferay-ui:message key="start-date" />: <%= dateFormatLongDate.format(startDate) %>
	</p>
</div>