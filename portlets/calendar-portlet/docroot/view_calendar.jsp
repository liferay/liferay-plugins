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

List<Calendar> groupCalendars = null;

if (groupCalendarResource != null) {
	groupCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {groupCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> userCalendars = null;

if (userCalendarResource != null) {
	userCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {userCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> otherCalendars = new ArrayList<Calendar>();

long[] calendarIds = StringUtil.split(SessionClicks.get(request, "otherCalendars", StringPool.BLANK), 0L);

for (long calendarId : calendarIds) {
	Calendar calendar = CalendarLocalServiceUtil.fetchCalendar(calendarId);

	if ((calendar != null) && (CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW))) {
		otherCalendars.add(calendar);
	}
}

JSONArray groupCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, groupCalendars);
JSONArray userCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, userCalendars);
JSONArray otherCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, otherCalendars);
%>

<aui:fieldset cssClass="calendar-portlet-column-parent">
	<aui:column cssClass="calendar-portlet-column-options">
		<div id="<portlet:namespace />calendarListContainer">
			<a class="aui-toggler-header-expanded calendar-portlet-list-header" href="javascript:void(0);">
				<span class="calendar-portlet-list-arrow"></span>

				<span class="calendar-portlet-list-text"><liferay-ui:message key="my-calendars" /></span>

				<c:if test="<%= userCalendarResource != null %>">
					<span class="aui-calendar-list-item-arrow" data-calendarResourceId="<%= userCalendarResource.getCalendarResourceId() %>" tabindex="0"></span>
				</c:if>
			</a>

			<div class="calendar-portlet-calendar-list" id="<portlet:namespace />myCalendarList"></div>

			<a class="calendar-portlet-list-header aui-toggler-header-expanded" href="javascript:void(0);">
				<span class="calendar-portlet-list-arrow"></span>

				<span class="calendar-portlet-list-text"><liferay-ui:message key="other-calendars" /></span>
			</a>

			<div class="calendar-portlet-calendar-list" id="<portlet:namespace />otherCalendarList">
				<input class="calendar-portlet-add-calendars-input" id="<portlet:namespace />addOtherCalendar" placeholder="<liferay-ui:message key="add-other-calendars" />" type="text" />
			</div>

			<c:if test="<%= groupCalendarResource != null %>">
				<a class="aui-toggler-header-expanded calendar-portlet-list-header" href="javascript:void(0);">
					<span class="calendar-portlet-list-arrow"></span>

					<span class="calendar-portlet-list-text"><liferay-ui:message key="current-site-calendars" /></span>

					<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, groupCalendarResource, ActionKeys.VIEW) %>">
						<span class="aui-calendar-list-item-arrow" data-calendarResourceId="<%= groupCalendarResource.getCalendarResourceId() %>" tabindex="0"></span>
					</c:if>
				</a>

				<div class="calendar-portlet-calendar-list" id="<portlet:namespace />siteCalendarList"></div>
			</c:if>
		</div>

		<div id="<portlet:namespace />message"></div>
	</aui:column>

	<aui:column columnWidth="100">
		<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>
	</aui:column>
</aui:fieldset>

<script id="<portlet:namespace />eventRecorderTpl" type="text/x-alloy-template">
	<%@ include file="/event_recorder.jspf" %>
</script>

<%@ include file="/view_calendar_menus.jspf" %>

<aui:script use="aui-toggler,liferay-calendar-list,liferay-scheduler,liferay-store,json">
	Liferay.CalendarUtil.NOTIFICATION_DEFAULT_TYPE = '<%= PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE %>';
	Liferay.CalendarUtil.PORTLET_NAMESPACE = '<portlet:namespace />';
	Liferay.CalendarUtil.USER_TIMEZONE_OFFSET = <%= JCalendarUtil.getTimeZoneOffset(timeZone) %>;

	<c:if test="<%= userCalendars != null %>">
		Liferay.CalendarUtil.DEFAULT_CALENDAR = <%=CalendarUtil.toCalendarJSONObject(themeDisplay, userCalendars.get(0)) %>;
	</c:if>

	var syncVisibleCalendarsMap = function() {
		Liferay.CalendarUtil.syncVisibleCalendarsMap(
			window.<portlet:namespace />myCalendarList,
			window.<portlet:namespace />otherCalendarList,
			window.<portlet:namespace />siteCalendarList
		);
	}

	window.<portlet:namespace />myCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: syncVisibleCalendarsMap
			},
			boundingBox: '#<portlet:namespace />myCalendarList',

			<%
			updateCalendarsJSONArray(request, userCalendarsJSONArray);
			%>

			calendars: <%= userCalendarsJSONArray %>,
			simpleMenu: window.<portlet:namespace />calendarsMenu
		}
	).render();

	window.<portlet:namespace />otherCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					syncVisibleCalendarsMap();

					window.<portlet:namespace />scheduler.loadCalendarBookings();

					var calendarIds = A.Array.invoke(event.newVal, 'get', 'calendarId');

					Liferay.Store('otherCalendars', calendarIds.join());
				}
			},
			boundingBox: '#<portlet:namespace />otherCalendarList',

			<%
			updateCalendarsJSONArray(request, otherCalendarsJSONArray);
			%>

			calendars: <%= otherCalendarsJSONArray %>,
			simpleMenu: window.<portlet:namespace />calendarsMenu
		}
	).render();

	window.<portlet:namespace />siteCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: syncVisibleCalendarsMap
			},
			boundingBox: '#<portlet:namespace />siteCalendarList',

			<%
			updateCalendarsJSONArray(request, groupCalendarsJSONArray);
			%>

			calendars: <%= groupCalendarsJSONArray %>,
			simpleMenu: window.<portlet:namespace />calendarsMenu
		}
	).render();

	syncVisibleCalendarsMap();

	window.<portlet:namespace />dayView = new A.SchedulerDayView(
		{
			headerDateFormat: '<%= dayViewHeaderDateFormat %>',
			height: 700,
			isoTime: <%= isoTimeFormat %>,
			navigationDateFormat: '<%= navigationHeaderDateFormat %>'
		}
	);

	window.<portlet:namespace />weekView = new A.SchedulerWeekView(
		{
			headerDateFormat: '<%= dayViewHeaderDateFormat %>',
			height: 700,
			isoTime: <%= isoTimeFormat %>,
			navigationDateFormat: '<%= navigationHeaderDateFormat %>'
		}
	);

	window.<portlet:namespace />monthView = new A.SchedulerMonthView(
		{
			height: 700,
			navigationDateFormat: '<%= navigationHeaderDateFormat %>'
		}
	);

	<portlet:renderURL var="editCalendarBookingURL">
		<portlet:param name="jspPage" value="/edit_calendar_booking.jsp" />
		<portlet:param name="redirect" value="<%= String.valueOf(renderResponse.createRenderURL()) %>" />
		<portlet:param name="activeView" value="{activeView}" />
		<portlet:param name="allDay" value="{allDay}" />
		<portlet:param name="calendarBookingId" value="{calendarBookingId}" />
		<portlet:param name="calendarId" value="{calendarId}" />
		<portlet:param name="currentDate" value="{currentDate}" />
		<portlet:param name="endDate" value="{endDate}" />
		<portlet:param name="startDate" value="{startDate}" />
		<portlet:param name="titleCurrentValue" value="{titleCurrentValue}" />
	</portlet:renderURL>

	window.<portlet:namespace />recorder = new Liferay.SchedulerEventRecorder(
		{
			calendarId: <%= userDefaultCalendar.getCalendarId() %>,
			duration: 30,
			editCalendarBookingURL: '<%= editCalendarBookingURL %>',
			portletNamespace: '<portlet:namespace />',
			template: new A.Template(A.one('#<portlet:namespace />eventRecorderTpl').text())
		}
	);

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
			eventRecorder: window.<portlet:namespace />recorder,
			events: A.Object.values(Liferay.CalendarUtil.visibleCalendars),
			on: {
				'scheduler-calendar:visibleChange': function(event) {
					var instance = this;

					var calendar = event.target;

					Liferay.Store('calendar-portlet-calendar-' + calendar.get('calendarId') + '-visible', event.newVal);
				}
			},
			portletNamespace: '<portlet:namespace />',
			render: true,
			views: [
				window.<portlet:namespace />weekView,
				window.<portlet:namespace />dayView,
				window.<portlet:namespace />monthView
			]
		}
	);

	window.<portlet:namespace />toggler = new A.TogglerDelegate(
		{
			animated: true,
			container: '#<portlet:namespace />calendarListContainer',
			content: '.calendar-portlet-calendar-list',
			header: '.calendar-portlet-list-header'
		}
	);

	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL" />

	var addOtherCalendarInput = A.one('#<portlet:namespace />addOtherCalendar');

	Liferay.CalendarUtil.createCalendarsAutoComplete(
		'<%= calendarResourcesURL %>',
		addOtherCalendarInput,
		function(event) {
			window.<portlet:namespace />otherCalendarList.add(event.result.raw);

			addOtherCalendarInput.val('');
		}
	);
</aui:script>

<%!
protected void updateCalendarsJSONArray(HttpServletRequest request, JSONArray calendarsJSONArray) {
	for (int i = 0; i < calendarsJSONArray.length(); i++) {
		JSONObject jsonObject = calendarsJSONArray.getJSONObject(i);

		long calendarId = jsonObject.getLong("calendarId");

		jsonObject.put("color", GetterUtil.getString(SessionClicks.get(request, "calendar-portlet-calendar-" + calendarId + "-color", jsonObject.getString("color"))));
		jsonObject.put("visible", GetterUtil.getBoolean(SessionClicks.get(request, "calendar-portlet-calendar-" + calendarId + "-visible", "true")));
	}
}
%>