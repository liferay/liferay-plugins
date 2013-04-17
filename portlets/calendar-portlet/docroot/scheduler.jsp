<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
String activeView = ParamUtil.getString(request, "activeView", defaultView);
long date = ParamUtil.getLong(request, "date", System.currentTimeMillis());
String editCalendarBookingURL = ParamUtil.getString(request, "editCalendarBookingURL");
String filterCalendarBookings = ParamUtil.getString(request, "filterCalendarBookings", null);
boolean hideAgendaView = ParamUtil.getBoolean(request, "hideAgendaView");
boolean hideDayView = ParamUtil.getBoolean(request, "hideDayView");
boolean hideMonthView = ParamUtil.getBoolean(request, "hideMonthView");
boolean hideWeekView = ParamUtil.getBoolean(request, "hideWeekView");
boolean preventPersistence = ParamUtil.getBoolean(request, "preventPersistence");
boolean readOnly = ParamUtil.getBoolean(request, "readOnly");
String viewCalendarBookingURL = ParamUtil.getString(request, "viewCalendarBookingURL");
%>

<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>

<script id="<portlet:namespace />eventRecorderTpl" type="text/x-alloy-template">
	<%@ include file="/event_recorder.jspf" %>
</script>

<aui:script use="aui-toggler,liferay-calendar-list,liferay-scheduler,liferay-store,json">
	Liferay.CalendarUtil.PORTLET_NAMESPACE = '<portlet:namespace />';
	Liferay.CalendarUtil.USER_TIMEZONE_OFFSET = <%= JCalendarUtil.getTimeZoneOffset(userTimeZone) %>;

	<c:if test="<%= !hideDayView %>">
		window.<portlet:namespace />dayView = new A.SchedulerDayView(
			{
				height: 700,
				isoTime: <%= isoTimeFormat %>,
				readOnly: <%= readOnly %>,
				strings: {
					allDay: '<liferay-ui:message key="all-day" />'
				}
			}
		);
	</c:if>

	<c:if test="<%= !hideWeekView %>">
		window.<portlet:namespace />weekView = new A.SchedulerWeekView(
			{
				height: 700,
				isoTime: <%= isoTimeFormat %>,
				readOnly: <%= readOnly %>
			}
		);
	</c:if>

	<c:if test="<%= !hideMonthView %>">
		window.<portlet:namespace />monthView = new A.SchedulerMonthView(
			{
				height: 700,
				readOnly: <%= readOnly %>
			}
		);
	</c:if>

	<c:if test="<%= !hideAgendaView %>">
		window.<portlet:namespace />agendaView = new A.SchedulerAgendaView(
			{
				height: 700,
				readOnly: <%= readOnly %>,
				strings: {
					noEvents: '<liferay-ui:message key="no-events" />'
				}
			}
		);
	</c:if>

	<c:if test="<%= !readOnly && (userDefaultCalendar != null) %>">
		window.<portlet:namespace />eventRecorder = new Liferay.SchedulerEventRecorder(
			{
				calendarId: <%= userDefaultCalendar.getCalendarId() %>,
				color: '<%= ColorUtil.toHexString(userDefaultCalendar.getColor()) %>',
				duration: <%= defaultDuration %>,
				editCalendarBookingURL: '<%= HtmlUtil.escapeJS(editCalendarBookingURL) %>',
				portletNamespace: '<portlet:namespace />',
				template: new A.Template(A.one('#<portlet:namespace />eventRecorderTpl').text()),
				viewCalendarBookingURL: '<%= HtmlUtil.escapeJS(viewCalendarBookingURL) %>'
			}
		);
	</c:if>

	window.<portlet:namespace />scheduler = new Liferay.Scheduler(
		{
			activeView: window.<portlet:namespace /><%= activeView %>View,
			boundingBox: '#<portlet:namespace />scheduler',
			date: new Date(<%= date %>),
			eventRecorder: window.<portlet:namespace />eventRecorder,
			filterCalendarBookings: <%= filterCalendarBookings %>,
			firstDayOfWeek: <%= weekStartsOn %>,
			items: A.Object.values(Liferay.CalendarUtil.availableCalendars),
			portletNamespace: '<portlet:namespace />',
			preventPersistence: <%= preventPersistence %>,
			render: true,
			strings: {
				agenda: '<liferay-ui:message key="agenda" />',
				day: '<liferay-ui:message key="day" />',
				month: '<liferay-ui:message key="month" />',
				today: '<liferay-ui:message key="today" />',
				week: '<liferay-ui:message key="week" />',
				year: '<liferay-ui:message key="year" />'
			},
			views: [
				<c:if test="<%= !hideDayView %>">
					window.<portlet:namespace />dayView,
				</c:if>

				<c:if test="<%= !hideWeekView %>">
					window.<portlet:namespace />weekView,
				</c:if>

				<c:if test="<%= !hideMonthView %>">
					window.<portlet:namespace />monthView,
				</c:if>

				<c:if test="<%= !hideAgendaView %>">
					window.<portlet:namespace />agendaView
				</c:if>
			]
		}
	);
</aui:script>