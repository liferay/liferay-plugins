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

	if ((calendar != null) && CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW)) {
		CalendarResource calendarResource = calendar.getCalendarResource();

		if (calendarResource.isActive()) {
			otherCalendars.add(calendar);
		}
	}
}

Calendar defaultCalendar = null;

if ((userCalendars != null) && (userCalendars.size() > 0)) {
	for (Calendar userCalendar : userCalendars) {
		if (userCalendar.isDefaultCalendar()) {
			defaultCalendar = userCalendar;

			break;
		}
	}
}

JSONArray groupCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, groupCalendars);
JSONArray userCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, userCalendars);
JSONArray otherCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, otherCalendars);
%>

<aui:fieldset cssClass="calendar-portlet-column-parent">
	<aui:column cssClass="calendar-portlet-column-options">
		<div class="calendar-portlet-mini-calendar" id="<portlet:namespace />miniCalendarContainer"></div>

		<div id="<portlet:namespace />calendarListContainer">
			<c:if test="<%= themeDisplay.isSignedIn() %>">
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
			</c:if>

			<c:if test="<%= groupCalendarResource != null %>">
				<a class="aui-toggler-header-expanded calendar-portlet-list-header" href="javascript:void(0);">
					<span class="calendar-portlet-list-arrow"></span>

					<span class="calendar-portlet-list-text"><liferay-ui:message key="current-site-calendars" /></span>

					<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, groupCalendarResource, ActionKeys.ADD_CALENDAR) %>">
						<span class="aui-calendar-list-item-arrow" data-calendarResourceId="<%= groupCalendarResource.getCalendarResourceId() %>" tabindex="0"></span>
					</c:if>
				</a>

				<div class="calendar-portlet-calendar-list" id="<portlet:namespace />siteCalendarList"></div>
			</c:if>
		</div>

		<div id="<portlet:namespace />message"></div>
	</aui:column>

	<aui:column columnWidth="100">
		<liferay-util:include page="/scheduler.jsp" servletContext="<%= application %>">
			<liferay-util:param name="activeView" value="<%= activeView %>" />
			<liferay-util:param name="date" value="<%= String.valueOf(date) %>" />

			<portlet:renderURL var="editCalendarBookingURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/edit_calendar_booking.jsp" />
				<portlet:param name="activeView" value="{activeView}" />
				<portlet:param name="allDay" value="{allDay}" />
				<portlet:param name="calendarBookingId" value="{calendarBookingId}" />
				<portlet:param name="calendarId" value="{calendarId}" />
				<portlet:param name="date" value="{date}" />
				<portlet:param name="endTime" value="{endTime}" />
				<portlet:param name="startTime" value="{startTime}" />
				<portlet:param name="titleCurrentValue" value="{titleCurrentValue}" />
			</portlet:renderURL>

			<liferay-util:param name="editCalendarBookingURL" value="<%= editCalendarBookingURL %>" />

			<liferay-util:param name="readOnly" value="<%= String.valueOf(false) %>" />

			<portlet:renderURL var="viewCalendarBookingURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/view_calendar_booking.jsp" />
				<portlet:param name="calendarBookingId" value="{calendarBookingId}" />
			</portlet:renderURL>

			<liferay-util:param name="viewCalendarBookingURL" value="<%= viewCalendarBookingURL %>" />
		</liferay-util:include>
	</aui:column>
</aui:fieldset>

<%@ include file="/view_calendar_menus.jspf" %>

<aui:script use="aui-toggler,liferay-calendar-list,liferay-scheduler,liferay-store,json">
	Liferay.CalendarUtil.USER_CLASS_NAME_ID = <%= PortalUtil.getClassNameId(User.class) %>;

	Liferay.CalendarUtil.INVITEES_URL = '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarBookingInvitees" />';
	Liferay.CalendarUtil.RENDERING_RULES_URL = '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarRenderingRules" />';

	<c:if test="<%= defaultCalendar != null %>">
		Liferay.CalendarUtil.DEFAULT_USER_CALENDAR_ID = <%= defaultCalendar.getCalendarId() %>;
	</c:if>

	var syncCalendarsMap = function() {
		var calendarLists = [];

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			calendarLists.push(
				window.<portlet:namespace />myCalendarList,
				window.<portlet:namespace />otherCalendarList
			);
		</c:if>

		<c:if test="<%= groupCalendarResource != null %>">
			calendarLists.push(window.<portlet:namespace />siteCalendarList);
		</c:if>

		Liferay.CalendarUtil.syncCalendarsMap(calendarLists);
	}

	<c:if test="<%= themeDisplay.isSignedIn() %>">
		window.<portlet:namespace />myCalendarList = new Liferay.CalendarList(
			{
				after: {
					calendarsChange: syncCalendarsMap,
					'scheduler-calendar:visibleChange': function(event) {
						syncCalendarsMap();

						<portlet:namespace />refreshVisibleCalendarRenderingRules();
					}
				},
				boundingBox: '#<portlet:namespace />myCalendarList',

				<%
				updateCalendarsJSONArray(request, userCalendarsJSONArray);
				%>

				calendars: <%= userCalendarsJSONArray %>,
				scheduler: <portlet:namespace />scheduler,
				simpleMenu: window.<portlet:namespace />calendarsMenu
			}
		).render();

		window.<portlet:namespace />otherCalendarList = new Liferay.CalendarList(
			{
				after: {
					calendarsChange: function(event) {
						syncCalendarsMap();

						<portlet:namespace />scheduler.load();

						var calendarIds = A.Array.invoke(event.newVal, 'get', 'calendarId');

						Liferay.Store('otherCalendars', calendarIds.join());
					},
					'scheduler-calendar:visibleChange': function(event) {
						syncCalendarsMap();

						<portlet:namespace />refreshVisibleCalendarRenderingRules();
					}
				},
				boundingBox: '#<portlet:namespace />otherCalendarList',

				<%
				updateCalendarsJSONArray(request, otherCalendarsJSONArray);
				%>

				calendars: <%= otherCalendarsJSONArray %>,
				scheduler: <portlet:namespace />scheduler,
				simpleMenu: window.<portlet:namespace />calendarsMenu
			}
		).render();
	</c:if>

	<c:if test="<%= groupCalendarResource != null %>">
		window.<portlet:namespace />siteCalendarList = new Liferay.CalendarList(
			{
				after: {
					calendarsChange: syncCalendarsMap,
					'scheduler-calendar:visibleChange': function(event) {
						syncCalendarsMap();

						<portlet:namespace />refreshVisibleCalendarRenderingRules();
					}
				},
				boundingBox: '#<portlet:namespace />siteCalendarList',

				<%
				updateCalendarsJSONArray(request, groupCalendarsJSONArray);
				%>

				calendars: <%= groupCalendarsJSONArray %>,
				scheduler: <portlet:namespace />scheduler,
				simpleMenu: window.<portlet:namespace />calendarsMenu
			}
		).render();
	</c:if>

	syncCalendarsMap();

	A.each(
		Liferay.CalendarUtil.availableCalendars,
		function(item, index, collection) {
			item.on(
				{
					'visibleChange': function(event) {
						var instance = this;

						var calendar = event.currentTarget;

						Liferay.Store('calendar-portlet-calendar-' + calendar.get('calendarId') + '-visible', event.newVal);
					}
				}
			);
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

	<c:if test="<%= themeDisplay.isSignedIn() %>">
		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL" />

		var addOtherCalendarInput = A.one('#<portlet:namespace />addOtherCalendar');

		Liferay.CalendarUtil.createCalendarsAutoComplete(
			'<%= calendarResourcesURL %>',
			addOtherCalendarInput,
			function(event) {
				window.<portlet:namespace />otherCalendarList.add(event.result.raw);

				<portlet:namespace />refreshVisibleCalendarRenderingRules();

				addOtherCalendarInput.val('');
			}
		);
	</c:if>
</aui:script>

<aui:script use="aui-base,aui-datatype,calendar">
	var DateMath = A.DataType.DateMath;

	window.<portlet:namespace />refreshMiniCalendarSelectedDates = function() {
		<portlet:namespace />miniCalendar._clearSelection();

		var activeView = <portlet:namespace />scheduler.get('activeView');
		var viewDate = <portlet:namespace />scheduler.get('viewDate');

		var viewName = activeView.get('name');

		var total = 1;

		if (viewName == 'month') {
			total = A.Date.daysInMonth(viewDate);
		}
		else if (viewName == 'week') {
			total = 7;
		}

		var selectedDates = Liferay.CalendarUtil.getDatesList(viewDate, total);

		<portlet:namespace />miniCalendar.selectDates(selectedDates);

		<portlet:namespace />miniCalendar.set('date', viewDate);
	};

	window.<portlet:namespace />refreshVisibleCalendarRenderingRules = function() {
		var miniCalendarStartDate = DateMath.subtract(DateMath.toMidnight(window.<portlet:namespace />miniCalendar.get('date')), DateMath.WEEK, 1);

		var miniCalendarEndDate = DateMath.add(DateMath.add(miniCalendarStartDate, DateMath.MONTH, 1), DateMath.WEEK, 1);

		miniCalendarEndDate.setHours(23, 59, 59, 999);

		Liferay.CalendarUtil.getCalendarRenderingRules(
			A.Object.keys(Liferay.CalendarUtil.visibleCalendars),
			Liferay.CalendarUtil.toUTC(miniCalendarStartDate),
			Liferay.CalendarUtil.toUTC(miniCalendarEndDate),
			'busy',
			function(rulesDefinition) {
				window.<portlet:namespace />miniCalendar.set(
					'customRenderer',
					{
						filterFunction: function(date, node, rules) {
							node.addClass('lfr-busy-day');

							var selectedDates = this._getSelectedDatesList();

							DateMath.toMidnight(date);

							var selected = (selectedDates.length > 0) && A.Date.isInRange(date, selectedDates[0], selectedDates[selectedDates.length - 1]);

							node.toggleClass('yui3-calendar-day-selected', selected);
						},
						rules: rulesDefinition
					}
				);
			}
		);
	};

	window.<portlet:namespace />miniCalendar = new A.Calendar(
		{
			after: {
				dateChange: <portlet:namespace />refreshVisibleCalendarRenderingRules,
				dateClick: function(event) {
					<portlet:namespace />scheduler.setAttrs(
						{
							activeView: <portlet:namespace />dayView,
							date: event.date
						}
					);
				}
			},
			date: new Date(<%= String.valueOf(date) %>),
			locale: 'en',
			'strings.first_weekday': <%= weekStartsOn %>
		}
	).render('#<portlet:namespace />miniCalendarContainer');

	<portlet:namespace />scheduler.after(
		['*:add', '*:change', '*:load', '*:remove', '*:reset'],
		A.debounce(<portlet:namespace />refreshVisibleCalendarRenderingRules, 100)
	);

	<portlet:namespace />scheduler.after(
		['activeViewChange', 'dateChange'],
		<portlet:namespace />refreshMiniCalendarSelectedDates
	);

	<portlet:namespace />refreshVisibleCalendarRenderingRules();
	<portlet:namespace />refreshMiniCalendarSelectedDates();

	<portlet:namespace />scheduler.load();
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