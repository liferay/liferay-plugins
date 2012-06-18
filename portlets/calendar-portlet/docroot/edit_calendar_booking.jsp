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

String activeView = ParamUtil.getString(request, "activeView", "week");

redirect = HttpUtil.setParameter(redirect, renderResponse.getNamespace() + "activeView", activeView);

long currentDate = ParamUtil.getLong(request, "currentDate", now.getTimeInMillis());

redirect = HttpUtil.setParameter(redirect, renderResponse.getNamespace() + "currentDate", currentDate);

CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);

long calendarBookingId = BeanParamUtil.getLong(calendarBooking, request, "calendarBookingId");

long calendarId = BeanParamUtil.getLong(calendarBooking, request, "calendarId", userDefaultCalendar.getCalendarId());
String title = BeanParamUtil.getString(calendarBooking, request, "titleCurrentValue");

long startDate = ParamUtil.getLong(request, "startDate", now.getTimeInMillis());

java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(startDate, timeZone);

java.util.Calendar defaultEndDateJCalendar = (java.util.Calendar)now.clone();

defaultEndDateJCalendar.add(java.util.Calendar.HOUR, 1);

long endDate = ParamUtil.getLong(request, "endDate", defaultEndDateJCalendar.getTimeInMillis());

java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(endDate, timeZone);

boolean allDay = ParamUtil.getBoolean(request, "allDay");

if (!allDay) {
	com.liferay.portal.kernel.util.CalendarUtil.roundByMinutes(startDateJCalendar, 30);
	com.liferay.portal.kernel.util.CalendarUtil.roundByMinutes(endDateJCalendar, 30);
}

long firstReminder = BeanParamUtil.getLong(calendarBooking, request, "firstReminder");
String firstReminderType = BeanParamUtil.getString(calendarBooking, request, "firstReminderType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);
long secondReminder = BeanParamUtil.getLong(calendarBooking, request, "secondReminder");
String secondReminderType = BeanParamUtil.getString(calendarBooking, request, "secondReminderType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);

JSONArray acceptedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray declinedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray maybeCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray pendingCalendarsJSONArray = JSONFactoryUtil.createJSONArray();

boolean invitable = false;
boolean recurring = false;

Recurrence recurrence = null;

Calendar calendar = CalendarServiceUtil.fetchCalendar(calendarId);

if (calendarBooking != null) {
	startDateJCalendar.setTime(calendarBooking.getStartDate());
	endDateJCalendar.setTime(calendarBooking.getEndDate());

	acceptedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED));
	declinedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_DENIED));
	maybeCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_MAYBE));
	pendingCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_PENDING));

	if (calendarBooking.isMasterBooking()) {
		invitable = true;
	}

	if (calendarBooking.isRecurring()) {
		recurring = true;
	}

	recurrence = calendarBooking.getRecurrenceObj();
}
else if (calendar != null) {
	JSONObject calendarJSONObject = CalendarUtil.toCalendarJSONObject(themeDisplay, calendar);

	if (calendar.getUserId() == themeDisplay.getUserId()) {
		acceptedCalendarsJSONArray.put(calendarJSONObject);
	}
	else {
		pendingCalendarsJSONArray.put(calendarJSONObject);
	}
}

List<Calendar> manageableCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, null, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new CalendarNameComparator(true), ActionKeys.MANAGE_BOOKINGS);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= ((calendarBooking != null) && Validator.isNotNull(title)) ? title : "new-calendar-booking" %>'
/>

<liferay-portlet:actionURL name="updateCalendarBooking" var="updateCalendarBookingURL">
	<liferay-portlet:param name="mvcPath" value="/edit_calendar_booking.jsp" />
	<liferay-portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateCalendarBookingURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarBooking();" %>'>
	<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBookingId %>" />
	<aui:input name="childCalendarIds" type="hidden" />

	<aui:model-context bean="<%= calendarBooking %>" model="<%= CalendarBooking.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:input name="startDate" value="<%= startDateJCalendar %>" />

		<div id="<portlet:namespace />endDateContainer">
			<aui:input name="endDate" value="<%= endDateJCalendar %>" />
		</div>

		<aui:input name="allDay" />
	</aui:fieldset>

	<aui:fieldset>
		<liferay-ui:panel-container extended="<%= false %>" id="calendarBookingDetailsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="calendarBookingDetailsPanel" persistState="<%= true %>" title="details">
				<aui:select label="calendar" name="calendarId">

					<%
					for (Calendar curCalendar : manageableCalendars) {
						if ((calendarBooking != null) && (curCalendar.getCalendarId() != calendarId) && (CalendarBookingLocalServiceUtil.getCalendarBookingsCount(curCalendar.getCalendarId(), calendarBooking.getParentCalendarBookingId()) > 0)) {
							continue;
						}
					%>

						<aui:option selected="<%= curCalendar.getCalendarId() == calendarId %>" value="<%= curCalendar.getCalendarId() %>"><%= curCalendar.getName(locale) %></aui:option>

					<%
					}
					%>

				</aui:select>

				<aui:input name="description" />

				<aui:input name="location" />
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="calendarBookingReminderPanel" persistState="<%= true %>" title="reminders">
				<div id="<portlet:namespace />reminders"></div>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:fieldset>

	<liferay-ui:tabs
		names="invitations"
		refresh="<%= false %>"
	>
		<liferay-ui:section>
			<c:if test="<%= invitable %>">
				<aui:input inputCssClass="calendar-portlet-invite-resources-input" label="invite-resource" name="inviteResource" placeholder="add-guests-groups-rooms" type="text" />

				<div class="separator"><!-- --></div>
			</c:if>

			<aui:layout cssClass="calendar-booking-invitations">
				<aui:column columnWidth="25" first="true">
					<label class="aui-field-label">
						<liferay-ui:message key="pending" /> (<span id="<portlet:namespace />pendingCounter"><%= pendingCalendarsJSONArray.length() %></span>)
					</label>

					<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListPending"></div>
				</aui:column>
				<aui:column columnWidth="25">
					<label class="aui-field-label">
						<liferay-ui:message key="accepted" /> (<span id="<portlet:namespace />acceptedCounter"><%= acceptedCalendarsJSONArray.length() %></span>)
					</label>

					<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListAccepted"></div>
				</aui:column>
				<aui:column columnWidth="25" last="true">
					<label class="aui-field-label">
						<liferay-ui:message key="maybe" /> (<span id="<portlet:namespace />maybeCounter"><%= maybeCalendarsJSONArray.length() %></span>)
					</label>

					<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListMaybe"></div>
				</aui:column>
				<aui:column columnWidth="25" last="true">
					<label class="aui-field-label">
						<liferay-ui:message key="declined" /> (<span id="<portlet:namespace />declinedCounter"><%= declinedCalendarsJSONArray.length() %></span>)
					</label>

					<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListDeclined"></div>
				</aui:column>

				<aui:column columnWidth="100">
					<a class="aui-toggler-header-collapsed calendar-portlet-list-header" href="javascript:void(0);" id="<portlet:namespace />checkAvailability">
						<span class="calendar-portlet-list-arrow"></span>

						<span class="calendar-portlet-list-text"><liferay-ui:message key="resources-availability" /></span>
					</a>

					<div class="aui-toggler-content-collapsed" id="<portlet:namespace />schedulerContainer">
						<div id="<portlet:namespace />message"></div>

						<liferay-util:include page="/scheduler.jsp" servletContext="<%= application %>">
							<liferay-util:param name="activeView" value="<%= activeView %>" />
							<liferay-util:param name="currentDate" value="<%= String.valueOf(currentDate) %>" />
							<liferay-util:param name="readOnly" value="<%= String.valueOf(true) %>" />
						</liferay-util:include>
					</div>
				</aui:column>
			</aui:layout>
		</liferay-ui:section>
	</liferay-ui:tabs>

	<%@ include file="/calendar_booking_recurrence_container.jspf" %>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateCalendarBooking',
		function() {
			var A = AUI();

			<c:if test="<%= invitable %>">
				var calendarId = A.one('#<portlet:namespace />calendarId').val();
				var childCalendarIds = A.Object.keys(Liferay.CalendarUtil.visibleCalendars);

				A.Array.remove(childCalendarIds, A.Array.indexOf(childCalendarIds, calendarId));

				A.one('#<portlet:namespace />childCalendarIds').val(childCalendarIds.join(','));
			</c:if>

			submitForm(document.<portlet:namespace />fm);
		},
		['aui-base', 'json']
	);

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);

	Liferay.Util.toggleBoxes('<portlet:namespace />allDayCheckbox', '<portlet:namespace />endDateContainer', true);

	<c:if test="<%= calendarBooking == null %>">
		document.<portlet:namespace />fm.<portlet:namespace />title_<%= LanguageUtil.getLanguageId(request) %>.value = decodeURIComponent('<%= HtmlUtil.escapeURL(title) %>');
	</c:if>
</aui:script>

<aui:script use="aui-dialog,json,liferay-calendar-list,liferay-calendar-simple-menu">
	Liferay.CalendarUtil.PORTLET_NAMESPACE = '<portlet:namespace />';
	Liferay.CalendarUtil.USER_TIMEZONE_OFFSET = <%= JCalendarUtil.getTimeZoneOffset(timeZone) %>;

	var <portlet:namespace />count = A.one('#<portlet:namespace />count');
	var <portlet:namespace />endsRadio = A.all('[name=<portlet:namespace />ends]');
	var <portlet:namespace />frequency = A.one('#<portlet:namespace />frequency');
	var <portlet:namespace />interval = A.one('#<portlet:namespace />interval');
	var <portlet:namespace />intervalLabel = A.one('#<portlet:namespace />intervalLabel');
	var <portlet:namespace />recurrenceContainer = A.one('#<portlet:namespace />recurrenceContainer');
	var <portlet:namespace />repeatCheckbox = A.one('#<portlet:namespace />repeatCheckbox');
	var <portlet:namespace />summary = A.one('#<portlet:namespace />summary');
	var <portlet:namespace />summaryContainer = A.one('#<portlet:namespace />summaryContainer');
	var <portlet:namespace />summaryEditLink = A.one('#<portlet:namespace />summaryEditLink');
	var <portlet:namespace />summaryPreview = A.one('#<portlet:namespace />summaryPreview');
	var <portlet:namespace />untilDateDay = A.one('#<portlet:namespace />untilDateDay');
	var <portlet:namespace />untilDateMonth = A.one('#<portlet:namespace />untilDateMonth');
	var <portlet:namespace />untilDateYear = A.one('#<portlet:namespace />untilDateYear');
	var <portlet:namespace />weeklyView = A.one('#<portlet:namespace />view<%= Frequency.WEEKLY %>');

	var <portlet:namespace />recurrenceDialog = new A.Dialog(
		{
			bodyContent: <portlet:namespace />recurrenceContainer,
			buttons: [
				{
					handler: function() {
						<portlet:namespace />summary.setContent(<portlet:namespace />summaryPreview.text());

						<portlet:namespace />summaryContainer.show();

						this.hide();
					},
					label: Liferay.Language.get('done')
				},
				{
					handler: function() {
						if (!<portlet:namespace />summary.text()) {
							<portlet:namespace />repeatCheckbox.set('checked', false);
						}

						this.hide();
					},
					label: Liferay.Language.get('cancel')
				}
			],
			centered: true,
			modal: true,
			title: Liferay.Language.get('repeat'),
			visible : false,
			width: 400
		}
	).render('#<portlet:namespace />fm');

	function <portlet:namespace />updateSummaryPreview() {
		var weekdays = [];

		if (<portlet:namespace />frequency.val() == '<%= Frequency.WEEKLY %>') {
			for (var i in Liferay.CalendarUtil.WEEKDAYS) {
				if (A.one('#<portlet:namespace />' + i).val() == 'true') {
						weekdays.push(Liferay.CalendarUtil.WEEKDAYS[i]);
				}
			}
		}

		var count;
		var until;

		var endsIndex = A.Array.indexOf(<portlet:namespace />endsRadio.get('checked'), true);

		if (endsIndex == 1) {
			count = <portlet:namespace />count.val();
		}
		else if (endsIndex == 2) {
			var untilDateYear = <portlet:namespace />untilDateYear.val();
			var untilDateMonth = <portlet:namespace />untilDateMonth.val();
			var untilDateDay = <portlet:namespace />untilDateDay.val();

			until = new Date(untilDateYear, untilDateMonth, untilDateDay);
		}

		var recurrence = {
			count: count,
			endsIndex: endsIndex,
			frequency: <portlet:namespace />frequency.val(),
			interval: <portlet:namespace />interval.val(),
			until: until,
			weekdays: weekdays
		}

		var summary = Liferay.CalendarUtil.getSummary(recurrence);

		<portlet:namespace />summaryPreview.setContent(summary);
	}

	function <portlet:namespace />updateView(frequency) {
		if (frequency == '<%= Frequency.WEEKLY %>') {
			<portlet:namespace />weeklyView.show();
		}
		else {
			<portlet:namespace />weeklyView.hide();
		}

		var intervalLabel = Liferay.CalendarUtil.getIntervalLabel(frequency);

		<portlet:namespace />intervalLabel.setContent(intervalLabel);
	}

	<portlet:namespace />recurrenceContainer.delegate(
		'change',
		function(event) {
			var target = event.target;

			if (target.test('#<portlet:namespace />frequency')) {
				<portlet:namespace />updateView(target.val());
			}

			var selectedIndex = A.Array.indexOf(<portlet:namespace />endsRadio.get('checked'), true);

			<portlet:namespace />count.set('disabled', true);
			<portlet:namespace />untilDateDay.set('disabled', true);
			<portlet:namespace />untilDateMonth.set('disabled', true);
			<portlet:namespace />untilDateYear.set('disabled', true);

			if (selectedIndex == 1) {
				<portlet:namespace />count.set('disabled', false);
			}
			else if (selectedIndex == 2) {
				<portlet:namespace />untilDateDay.set('disabled', false);
				<portlet:namespace />untilDateMonth.set('disabled', false);
				<portlet:namespace />untilDateYear.set('disabled', false);
			}

			<portlet:namespace />updateSummaryPreview();
		},
		'select,input'
	);

	<portlet:namespace />repeatCheckbox.on(
		'click',
		function(event) {
			if (event.currentTarget.get('checked')) {
				if (<portlet:namespace />summary.text()) {
					<portlet:namespace />summaryContainer.show();
				}
				else {
					<portlet:namespace />recurrenceDialog.show()
				}
			}
			else {
				<portlet:namespace />summaryContainer.hide();
			}
		}
	);

	<portlet:namespace />summaryEditLink.on(
		'click',
		function(event) {
			<portlet:namespace />recurrenceDialog.show();
		}
	);

	<portlet:namespace />updateSummaryPreview();
	<portlet:namespace />updateView(<portlet:namespace />frequency.val());

	<c:if test="<%= recurring %>">
		var summary = <portlet:namespace />summaryPreview.text();

		<portlet:namespace />summary.setContent(summary);
	</c:if>

	var defaultCalendarId = <%= calendarId %>;

	var removeCalendarResource = function(calendarList, calendar, menu) {
		calendarList.remove(calendar);

		if (menu) {
			menu.hide();
		}
	}

	var syncVisibleCalendarsMap = function() {
		Liferay.CalendarUtil.syncVisibleCalendarsMap(
			window.<portlet:namespace />calendarListAccepted,
			window.<portlet:namespace />calendarListDeclined,
			window.<portlet:namespace />calendarListMaybe,
			window.<portlet:namespace />calendarListPending
		);

		A.each(
			Liferay.CalendarUtil.visibleCalendars,
			function(item, index, collection) {
				item.set('disabled', true);
			}
		);
	}

	window.<portlet:namespace />toggler = new A.Toggler(
		{
			after: {
				expandedChange: function(event) {
					if (event.newVal) {
						var activeView = <portlet:namespace />scheduler.get('activeView');

						activeView._fillHeight();
					}
				}
			},
			animated: true,
			content: '#<portlet:namespace />schedulerContainer',
			expanded: false,
			header: '#<portlet:namespace />checkAvailability'
		}
	);

	var calendarsMenu = {
		items: [
			{
				caption: '<liferay-ui:message key="check-availability" />',
				fn: function(event) {
					var instance = this;

					A.each(
						Liferay.CalendarUtil.visibleCalendars,
						function(item, index, collection) {
							item.set('visible', false);
						}
					);

					var calendarList = instance.get('host');

					calendarList.activeItem.set('visible', true);

					if (!<portlet:namespace />toggler.get('expanded')) {
						<portlet:namespace />toggler.expand();
					}

					instance.hide();

					return false;
				},
				id: 'check-availability'
			}
			<c:if test="<%= invitable %>">
				,{
					caption: '<liferay-ui:message key="remove" />',
					fn: function(event) {
						var instance = this;

						var calendarList = instance.get('host');

						removeCalendarResource(calendarList, calendarList.activeItem, instance);
					},
					id: 'remove'
				}
			</c:if>
		],
		<c:if test="<%= invitable %>">
			on: {
				visibleChange: function(event) {
					var instance = this;

					if (event.newVal) {
						var calendarList = instance.get('host');
						var calendar = calendarList.activeItem;

						var hiddenItems = [];

						if (calendar.get('calendarId') === defaultCalendarId) {
							hiddenItems.push('remove');
						}

						instance.set('hiddenItems', hiddenItems);
					}
				}
			}
		</c:if>
	}

	window.<portlet:namespace />calendarListPending = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					var instance = this;

					A.one('#<portlet:namespace />pendingCounter').html(event.newVal.length);

					syncVisibleCalendarsMap();

					window.<portlet:namespace />scheduler.loadCalendarBookings();
				}
			},
			boundingBox: '#<portlet:namespace />calendarListPending',
			calendars: <%= pendingCalendarsJSONArray %>,
			simpleMenu: calendarsMenu,
			strings: {
				emptyMessage: '<liferay-ui:message key="no-pending-invites" />'
			}
		}
	).render();

	window.<portlet:namespace />calendarListAccepted = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					var instance = this;

					A.one('#<portlet:namespace />acceptedCounter').html(event.newVal.length);

					syncVisibleCalendarsMap();

					window.<portlet:namespace />scheduler.loadCalendarBookings();
				}
			},
			boundingBox: '#<portlet:namespace />calendarListAccepted',
			calendars: <%= acceptedCalendarsJSONArray %>,
			simpleMenu: calendarsMenu,
			strings: {
				emptyMessage: '<liferay-ui:message key="no-accepted-invites" />'
			}
		}
	).render();

	window.<portlet:namespace />calendarListDeclined = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					var instance = this;

					A.one('#<portlet:namespace />declinedCounter').html(event.newVal.length);

					syncVisibleCalendarsMap();

					window.<portlet:namespace />scheduler.loadCalendarBookings();
				}
			},
			boundingBox: '#<portlet:namespace />calendarListDeclined',
			calendars: <%= declinedCalendarsJSONArray %>,
			simpleMenu: calendarsMenu,
			strings: {
				emptyMessage: '<liferay-ui:message key="no-declined-invites" />'
			}
		}
	).render();

	window.<portlet:namespace />calendarListMaybe = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					var instance = this;

					A.one('#<portlet:namespace />maybeCounter').html(event.newVal.length);

					syncVisibleCalendarsMap();

					window.<portlet:namespace />scheduler.loadCalendarBookings();
				}
			},
			boundingBox: '#<portlet:namespace />calendarListMaybe',
			calendars: <%= maybeCalendarsJSONArray %>,
			simpleMenu: calendarsMenu,
			strings: {
				emptyMessage: '<liferay-ui:message key="no-outstanding-invites" />'
			}
		}
	).render();

	syncVisibleCalendarsMap();

	var formNode = A.one(document.<portlet:namespace />fm);

	var currentEvent = new Liferay.SchedulerEvent(
		{
			after: {
				endDateChange: function(event) {
					Liferay.CalendarUtil.syncDatePickerFields(formNode, 'endDate', event.newVal);
				},
				startDateChange: function(event) {
					Liferay.CalendarUtil.syncDatePickerFields(formNode, 'startDate', event.newVal);
				}
			},
			color: '#F8F8F8',
			content: '',
			endDate: Liferay.CalendarUtil.toUserTimeZone(new Date(<%= endDate %>)),
			on: {
				startDateChange: function(event) {
					event.stopPropagation();
				}
			},
			scheduler: window.<portlet:namespace />scheduler,
			startDate: Liferay.CalendarUtil.toUserTimeZone(new Date(<%= startDate %>))
		}
	);

	Liferay.CalendarUtil.linkDatePickerToEvent(formNode, 'endDate', currentEvent);
	Liferay.CalendarUtil.linkDatePickerToEvent(formNode, 'startDate', currentEvent);

	window.<portlet:namespace />scheduler.after(
		{
			eventsChange: function(event) {
				var instance = this;

				var events = event.newVal;

				A.Array.each(
					events,
					function(item, index, collection) {
						if (item.get('calendarBookingId') === <%= calendarBookingId %>) {
							A.Array.removeItem(events, item);
						}
					}
				);

				events.push(currentEvent);
			}
		}
	);

	currentEvent.get('node').addClass('calendar-portlet-editting-event');

	<c:if test="<%= invitable %>">
		A.one('#<portlet:namespace />calendarId').on(
			'valueChange',
			function(event) {
				var calendarId = parseInt(event.target.val(), 10);

				var calendarJSON = Liferay.CalendarUtil.getCalendarJSONById(<%= CalendarUtil.toCalendarsJSONArray(themeDisplay, manageableCalendars) %>, calendarId);

				A.Array.each(
					[<portlet:namespace />calendarListAccepted, <portlet:namespace />calendarListDeclined, <portlet:namespace />calendarListMaybe, <portlet:namespace />calendarListPending],
					function(calendarList) {
						calendarList.remove(calendarList.getCalendar(calendarId));
						calendarList.remove(calendarList.getCalendar(defaultCalendarId));
					}
				);

				<portlet:namespace />calendarListPending.add(calendarJSON);

				defaultCalendarId = calendarId;
			}
		);

		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL"></liferay-portlet:resourceURL>

		var inviteResourcesInput = A.one('#<portlet:namespace />inviteResource');

		Liferay.CalendarUtil.createCalendarsAutoComplete(
			'<%= calendarResourcesURL %>',
			inviteResourcesInput,
			function(event) {
				var calendar = event.result.raw;

				calendar.disabled = true;

				<portlet:namespace />calendarListPending.add(calendar);

				inviteResourcesInput.val('');
			}
		);
	</c:if>

	window.<portlet:namespace />reminders = new Liferay.Reminders(
		{
			portletNamespace: '<portlet:namespace />',
			render: '#<portlet:namespace />reminders',
			values: [
				{
					interval: <%= firstReminder %>,
					type: '<%= HtmlUtil.escape(firstReminderType) %>'
				},
				{
					interval: <%= secondReminder %>,
					type: '<%= HtmlUtil.escape(secondReminderType) %>'
				}
			]
		}
	);
</aui:script>