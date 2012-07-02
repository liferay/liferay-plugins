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
String activeView = ParamUtil.getString(request, "activeView", "week");
long currentDate = ParamUtil.getLong(request, "currentDate", now.getTimeInMillis());
String editCalendarBookingURL = ParamUtil.getString(request, "editCalendarBookingURL");
String filterCalendarBookings = ParamUtil.getString(request, "filterCalendarBookings", null);
boolean readOnly = ParamUtil.getBoolean(request, "readOnly");
%>

<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>

<script id="<portlet:namespace />eventRecorderTpl" type="text/x-alloy-template">
	<%@ include file="/event_recorder.jspf" %>
</script>

<aui:script use="aui-toggler,liferay-calendar-list,liferay-scheduler,liferay-store,json">
	Liferay.CalendarUtil.PORTLET_NAMESPACE = '<portlet:namespace />';
	Liferay.CalendarUtil.USER_TIMEZONE_OFFSET = <%= JCalendarUtil.getTimeZoneOffset(timeZone) %>;

	window.<portlet:namespace />dayView = new A.SchedulerDayView(
		{
			headerDateFormat: '<%= dayViewHeaderDateFormat %>',
			height: 700,
			isoTime: <%= isoTimeFormat %>,
			navigationDateFormat: '<%= navigationHeaderDateFormat %>',
			readOnly: <%= readOnly %>
		}
	);

	window.<portlet:namespace />weekView = new A.SchedulerWeekView(
		{
			headerDateFormat: '<%= dayViewHeaderDateFormat %>',
			height: 700,
			isoTime: <%= isoTimeFormat %>,
			navigationDateFormat: '<%= navigationHeaderDateFormat %>',
			readOnly: <%= readOnly %>
		}
	);

	window.<portlet:namespace />monthView = new A.SchedulerMonthView(
		{
			height: 700,
			navigationDateFormat: '<%= navigationHeaderDateFormat %>',
			readOnly: <%= readOnly %>
		}
	);

	var eventRecorder;

	<c:if test="<%= !readOnly %>">
		eventRecorder = new Liferay.SchedulerEventRecorder(
			{
				calendarId: <%= userDefaultCalendar.getCalendarId() %>,
				duration: 30,
				editCalendarBookingURL: '<%= HtmlUtil.escapeJS(editCalendarBookingURL) %>',
				portletNamespace: '<portlet:namespace />',
				template: new A.Template(A.one('#<portlet:namespace />eventRecorderTpl').text())
			}
		);
	</c:if>

	var activeView;

	<c:choose>
		<c:when test='<%= activeView.equals("day") %>'>
			activeView = window.<portlet:namespace />dayView;
		</c:when>
		<c:when test='<%= activeView.equals("month") %>'>
			activeView = window.<portlet:namespace />monthView;
		</c:when>
		<c:otherwise>
			activeView = window.<portlet:namespace />weekView;
		</c:otherwise>
	</c:choose>

	window.<portlet:namespace />scheduler = new Liferay.Scheduler(
		{
			activeView: activeView,
			boundingBox: '#<portlet:namespace />scheduler',
			currentDate: new Date(<%= currentDate %>),
			eventClass: Liferay.SchedulerEvent,
			eventRecorder: eventRecorder,
			events: A.Object.values(Liferay.CalendarUtil.visibleCalendars),
			filterCalendarBookings: <%= filterCalendarBookings %>,
			portletNamespace: '<portlet:namespace />',
			render: true,
			views: [
				window.<portlet:namespace />weekView,
				window.<portlet:namespace />dayView,
				window.<portlet:namespace />monthView
			]
		}
	);
</aui:script>