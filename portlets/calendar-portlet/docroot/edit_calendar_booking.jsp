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
String activeView = ParamUtil.getString(request, "activeView", defaultView);

TimeZone calendarBookingTimeZone = userTimeZone;

boolean allDay = BeanParamUtil.getBoolean(calendarBooking, request, "allDay");

if (allDay) {
	calendarBookingTimeZone = utcTimeZone;
}

java.util.Calendar nowJCalendar = CalendarFactoryUtil.getCalendar(calendarBookingTimeZone);

long date = ParamUtil.getLong(request, "date", nowJCalendar.getTimeInMillis());

long calendarBookingId = BeanPropertiesUtil.getLong(calendarBooking, "calendarBookingId");

int instanceIndex = BeanParamUtil.getInteger(calendarBooking, request, "instanceIndex");

long calendarId = BeanParamUtil.getLong(calendarBooking, request, "calendarId", userDefaultCalendar.getCalendarId());

long startTime = BeanPropertiesUtil.getLong(calendarBooking, "startTime", nowJCalendar.getTimeInMillis());

java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime, calendarBookingTimeZone);

int startTimeYear = ParamUtil.getInteger(request, "startTimeYear", startTimeJCalendar.get(java.util.Calendar.YEAR));
int startTimeMonth = ParamUtil.getInteger(request, "startTimeMonth", startTimeJCalendar.get(java.util.Calendar.MONTH));
int startTimeDay = ParamUtil.getInteger(request, "startTimeDay", startTimeJCalendar.get(java.util.Calendar.DAY_OF_MONTH));
int startTimeHour = ParamUtil.getInteger(request, "startTimeHour", startTimeJCalendar.get(java.util.Calendar.HOUR_OF_DAY));
int startTimeMinute = ParamUtil.getInteger(request, "startTimeMinute", startTimeJCalendar.get(java.util.Calendar.MINUTE));

startTimeJCalendar = CalendarFactoryUtil.getCalendar(startTimeYear, startTimeMonth, startTimeDay, startTimeHour, startTimeMinute, 0, 0, calendarBookingTimeZone);

startTimeJCalendar.setFirstDayOfWeek(weekStartsOn + 1);

startTime = startTimeJCalendar.getTimeInMillis();

java.util.Calendar defaultEndTimeJCalendar = (java.util.Calendar)nowJCalendar.clone();

defaultEndTimeJCalendar.add(java.util.Calendar.MINUTE, defaultDuration);

long endTime = BeanPropertiesUtil.getLong(calendarBooking, "endTime", defaultEndTimeJCalendar.getTimeInMillis());

java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(endTime, calendarBookingTimeZone);

int endTimeYear = ParamUtil.getInteger(request, "endTimeYear", endTimeJCalendar.get(java.util.Calendar.YEAR));
int endTimeMonth = ParamUtil.getInteger(request, "endTimeMonth", endTimeJCalendar.get(java.util.Calendar.MONTH));
int endTimeDay = ParamUtil.getInteger(request, "endTimeDay", endTimeJCalendar.get(java.util.Calendar.DAY_OF_MONTH));
int endTimeHour = ParamUtil.getInteger(request, "endTimeHour", endTimeJCalendar.get(java.util.Calendar.HOUR_OF_DAY));
int endTimeMinute = ParamUtil.getInteger(request, "endTimeMinute", endTimeJCalendar.get(java.util.Calendar.MINUTE));

endTimeJCalendar = CalendarFactoryUtil.getCalendar(endTimeYear, endTimeMonth, endTimeDay, endTimeHour, endTimeMinute, 0, 0, calendarBookingTimeZone);

endTimeJCalendar.setFirstDayOfWeek(weekStartsOn + 1);

endTime = endTimeJCalendar.getTimeInMillis();

long firstReminder = BeanParamUtil.getLong(calendarBooking, request, "firstReminder");
String firstReminderType = BeanParamUtil.getString(calendarBooking, request, "firstReminderType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);
long secondReminder = BeanParamUtil.getLong(calendarBooking, request, "secondReminder");
String secondReminderType = BeanParamUtil.getString(calendarBooking, request, "secondReminderType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);

int status = BeanParamUtil.getInteger(calendarBooking, request, "status");

JSONArray acceptedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray declinedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray maybeCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray pendingCalendarsJSONArray = JSONFactoryUtil.createJSONArray();

boolean hasChildCalendarBookings = false;
boolean invitable = true;
boolean masterBooking = true;
Recurrence recurrence = null;
boolean recurring = false;

Calendar calendar = CalendarServiceUtil.fetchCalendar(calendarId);

if (calendarBooking != null) {
	acceptedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED));
	declinedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_DENIED));
	maybeCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_MAYBE));
	pendingCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_PENDING));

	if (calendarBooking.isMasterBooking()) {
		List<CalendarBooking> childCalendarBookings = CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId());

		if (childCalendarBookings.size() > 1) {
			hasChildCalendarBookings = true;
		}
	}
	else {
		invitable = false;
		masterBooking = false;
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

List<Calendar> manageableCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), new long[] {user.getGroupId(), scopeGroupId}, null, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new CalendarNameComparator(true), ActionKeys.MANAGE_BOOKINGS);

long[] otherCalendarIds = StringUtil.split(SessionClicks.get(request, "calendar-portlet-other-calendars", StringPool.BLANK), 0L);

for (long otherCalendarId : otherCalendarIds) {
	Calendar otherCalendar = CalendarServiceUtil.fetchCalendar(otherCalendarId);

	if (otherCalendar == null) {
		continue;
	}

	CalendarResource otherCalendarResource = otherCalendar.getCalendarResource();

	if (otherCalendarResource.isActive() && !manageableCalendars.contains(otherCalendar) && CalendarPermission.contains(themeDisplay.getPermissionChecker(), otherCalendar, ActionKeys.MANAGE_BOOKINGS)) {
		manageableCalendars.add(otherCalendar);
	}
}
%>

<liferay-portlet:actionURL name="updateCalendarBooking" var="updateCalendarBookingURL" />

<aui:form action="<%= updateCalendarBookingURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarBooking();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/edit_calendar_booking.jsp" />

	<liferay-portlet:renderURL var="redirectURL">
		<liferay-portlet:param name="mvcPath" value="/edit_calendar_booking.jsp" />
		<liferay-portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBookingId) %>" />
	</liferay-portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
	<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBookingId %>" />
	<aui:input name="instanceIndex" type="hidden" value="<%= instanceIndex %>" />
	<aui:input name="childCalendarIds" type="hidden" />
	<aui:input name="status" type="hidden" value ="<%= status %>" />
	<aui:input name="allFollowing" type="hidden" />
	<aui:input name="updateCalendarBookingInstance" type="hidden" />

	<liferay-ui:error exception="<%= CalendarBookingDurationException.class %>" message="please-enter-a-start-date-that-comes-before-the-end-date" />

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= calendarBooking %>" model="<%= CalendarBooking.class %>" />

	<aui:fieldset>
		<aui:input defaultLanguageId="<%= themeDisplay.getLanguageId() %>" name="title" />

		<div class="<%= allDay ? "allday-class-active" : "" %>" id="<portlet:namespace />startDateContainer">
			<aui:input ignoreRequestValue="<%= true %>" label="start-date" name="startTime" value="<%= startTimeJCalendar %>" />
		</div>

		<div class="<%= allDay ? "allday-class-active" : "" %>" id="<portlet:namespace />endDateContainer">
			<aui:input ignoreRequestValue="<%= true %>" label="end-date" name="endTime" value="<%= endTimeJCalendar %>" />
		</div>

		<aui:input checked="<%= allDay %>" name="allDay" />

		<aui:field-wrapper cssClass="calendar-portlet-recurrence-container" inlineField="<%= true %>" label="">
			<aui:input checked="<%= recurring %>" name="repeat" type="checkbox" />

			<a class="calendar-portlet-recurrence-summary" href="javascript:;" id="<portlet:namespace />summary"></a>
		</aui:field-wrapper>

		<aui:input defaultLanguageId="<%= themeDisplay.getLanguageId() %>" name="description" />
	</aui:fieldset>

	<aui:fieldset>
		<liferay-ui:panel-container extended="<%= true %>" id="calendarBookingDetailsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" id="calendarBookingDetailsPanel" persistState="<%= true %>" title="details">
				<aui:select label="calendar" name="calendarId">

					<%
					for (Calendar curCalendar : manageableCalendars) {
						if ((calendarBooking != null) && (curCalendar.getCalendarId() != calendarId) && (CalendarBookingLocalServiceUtil.getCalendarBookingsCount(curCalendar.getCalendarId(), calendarBooking.getParentCalendarBookingId()) > 0)) {
							continue;
						}
					%>

						<aui:option selected="<%= curCalendar.getCalendarId() == calendarId %>" value="<%= curCalendar.getCalendarId() %>"><%= HtmlUtil.escape(curCalendar.getName(locale)) %></aui:option>

					<%
					}
					%>

				</aui:select>

				<aui:input name="location" />

				<liferay-ui:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
					<liferay-ui:custom-attribute-list
						className="<%= CalendarBooking.class.getName() %>"
						classPK="<%= (calendarBooking != null) ? calendarBooking.getCalendarBookingId() : 0 %>"
						editable="<%= true %>"
						label="<%= true %>"
					/>
				</liferay-ui:custom-attributes-available>

				<c:if test="<%= calendarBooking == null %>">
					<aui:field-wrapper label="permissions">
						<liferay-ui:input-permissions
							modelName="<%= CalendarBooking.class.getName() %>"
						/>
					</aui:field-wrapper>
				</c:if>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" defaultState='<%= BrowserSnifferUtil.isMobile(request) ? "closed" : "open" %>' extended="<%= false %>" id="calendarBookingInvitationPanel" persistState="<%= true %>" title="invitations">
				<c:if test="<%= invitable %>">
					<aui:input inputCssClass="calendar-portlet-invite-resources-input" label="" name="inviteResource" placeholder="add-people-groups-rooms" type="text" />

					<div class="separator"><!-- --></div>
				</c:if>

				<aui:layout cssClass="calendar-booking-invitations">
					<aui:column columnWidth="<%= (calendarBooking != null) ? 25 : 50 %>" first="true">
						<label class="field-label">
							<liferay-ui:message key="pending" /> (<span id="<portlet:namespace />pendingCounter"><%= pendingCalendarsJSONArray.length() %></span>)
						</label>

						<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListPending"></div>
					</aui:column>
					<aui:column columnWidth="<%= (calendarBooking != null) ? 25 : 50 %>">
						<label class="field-label">
							<liferay-ui:message key="accepted" /> (<span id="<portlet:namespace />acceptedCounter"><%= acceptedCalendarsJSONArray.length() %></span>)
						</label>

						<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListAccepted"></div>
					</aui:column>

					<c:if test="<%= calendarBooking != null %>">
						<aui:column columnWidth="25" last="true">
							<label class="field-label">
								<liferay-ui:message key="maybe" /> (<span id="<portlet:namespace />maybeCounter"><%= maybeCalendarsJSONArray.length() %></span>)
							</label>

							<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListMaybe"></div>
						</aui:column>
						<aui:column columnWidth="25" last="true">
							<label class="field-label">
								<liferay-ui:message key="declined" /> (<span id="<portlet:namespace />declinedCounter"><%= declinedCalendarsJSONArray.length() %></span>)
							</label>

							<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListDeclined"></div>
						</aui:column>
					</c:if>

					<aui:column columnWidth="100">
						<div class="calendar-portlet-list-header toggler-header-collapsed" id="<portlet:namespace />checkAvailability">
							<span class="calendar-portlet-list-arrow"></span>

							<span class="calendar-portlet-list-text"><liferay-ui:message key="resources-availability" /></span>
						</div>

						<div class="calendar-portlet-availability">
							<div class="toggler-content-collapsed" id="<portlet:namespace />schedulerContainer">
								<div id="<portlet:namespace />message"></div>

								<liferay-util:include page="/scheduler.jsp" servletContext="<%= application %>">
									<liferay-util:param name="activeView" value="<%= activeView %>" />
									<liferay-util:param name="date" value="<%= String.valueOf(startTime) %>" />
									<liferay-util:param name="filterCalendarBookings" value='<%= "window." + renderResponse.getNamespace() + "filterCalendarBookings" %>' />
									<liferay-util:param name="hideAgendaView" value="<%= Boolean.TRUE.toString() %>" />
									<liferay-util:param name="hideMonthView" value="<%= Boolean.TRUE.toString() %>" />
									<liferay-util:param name="preventPersistence" value="<%= Boolean.TRUE.toString() %>" />
									<liferay-util:param name="readOnly" value="<%= Boolean.TRUE.toString() %>" />
								</liferay-util:include>
							</div>
						</div>
					</aui:column>
				</aui:layout>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" id="calendarBookingReminderPanel" persistState="<%= true %>" title="reminders">
				<div class="calendar-booking-reminders" id="<portlet:namespace />reminders"></div>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" id="calendarBookingCategorizationPanel" persistState="<%= true %>" title="categorization">
				<aui:input classPK="<%= calendarBookingId %>" name="categories" type="assetCategories" />

				<aui:input classPK="<%= calendarBookingId %>" name="tags" type="assetTags" />
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" id="calendarBookingAssetLinksPanel" persistState="<%= true %>" title="related-assets">
				<liferay-ui:input-asset-links
					className="<%= CalendarBooking.class.getName() %>"
					classPK="<%= calendarBookingId %>"
				/>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:fieldset>

	<%@ include file="/calendar_booking_recurrence_container.jspf" %>

	<aui:button-row>
		<aui:button type="submit" />

		<c:if test="<%= calendarBooking != null %>">
			<liferay-security:permissionsURL
				modelResource="<%= CalendarBooking.class.getName() %>"
				modelResourceDescription="<%= calendarBooking.getTitle(locale) %>"
				resourceGroupId="<%= calendarBooking.getGroupId() %>"
				resourcePrimKey="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>"
				var="permissionsCalendarBookingURL"
			/>

			<aui:button href="<%= permissionsCalendarBookingURL %>" value="permissions" />
		</c:if>
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />filterCalendarBookings(calendarBooking) {
		return <%= calendarBookingId %> !== calendarBooking.calendarBookingId;
	}

	function <portlet:namespace />getSuggestionsContent() {
		return document.<portlet:namespace />fm.<portlet:namespace />title.value + ' ' + window.<portlet:namespace />description.getHTML();
	}

	function <portlet:namespace />resolver(data) {
		var A = AUI();

		var answers = data.answers;

		if (!answers.cancel) {
			A.one('#<portlet:namespace />allFollowing').val(!!answers.allFollowing);
			A.one('#<portlet:namespace />updateCalendarBookingInstance').val(!!answers.updateInstance);

			submitForm(document.<portlet:namespace />fm);
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateCalendarBooking',
		function() {
			var A = AUI();

			<c:if test="<%= invitable %>">
				var calendarId = A.one('#<portlet:namespace />calendarId').val();
				var childCalendarIds = A.Object.keys(Liferay.CalendarUtil.availableCalendars);

				A.Array.remove(childCalendarIds, childCalendarIds.indexOf(calendarId));

				A.one('#<portlet:namespace />childCalendarIds').val(childCalendarIds.join(','));
			</c:if>

			Liferay.CalendarMessageUtil.promptSchedulerEventUpdate(
				{
					calendarName: '<%= HtmlUtil.escapeJS(calendar.getName(locale)) %>',
					hasChild: <%= hasChildCalendarBookings %>,
					masterBooking: <%= masterBooking %>,
					recurring: <%= recurring %>,
					resolver: <portlet:namespace />resolver
				}
			);
		},
		['liferay-calendar-message-util', 'json']
	);

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);

	<%
	String titleCurrentValue = ParamUtil.getString(request, "titleCurrentValue");
	%>

	<c:if test="<%= Validator.isNotNull(titleCurrentValue) && (calendarBooking == null || !Validator.equals(titleCurrentValue, calendarBooking.getTitle(locale))) %>">
		document.<portlet:namespace />fm.<portlet:namespace />title.value = '<%= HtmlUtil.escapeJS(titleCurrentValue) %>';
		document.<portlet:namespace />fm.<portlet:namespace />title_<%= themeDisplay.getLanguageId() %>.value = '<%= HtmlUtil.escapeJS(titleCurrentValue) %>';
	</c:if>
</aui:script>

<aui:script use="json,liferay-calendar-date-picker-util,liferay-calendar-list,liferay-calendar-recurrence-util,liferay-calendar-reminders,liferay-calendar-simple-menu">
	var defaultCalendarId = <%= calendarId %>;

	var scheduler = window.<portlet:namespace />scheduler;

	var syncCalendarsMap = function() {
		Liferay.CalendarUtil.syncCalendarsMap(
			[
				window.<portlet:namespace />calendarListAccepted,

				<c:if test="<%= calendarBooking != null %>">
					window.<portlet:namespace />calendarListDeclined,
					window.<portlet:namespace />calendarListMaybe,
				</c:if>

				window.<portlet:namespace />calendarListPending
			]
		);

		A.each(
			Liferay.CalendarUtil.availableCalendars,
			function(item, index) {
				item.set('disabled', true);
			}
		);
	};

	var calendarsMenu = Liferay.CalendarUtil.getCalendarsMenu(
		{
			content: '#<portlet:namespace />schedulerContainer',
			defaultCalendarId: defaultCalendarId,
			header: '#<portlet:namespace />checkAvailability',
			invitable: <%= invitable %>,
			scheduler: scheduler
		}
	);

	window.<portlet:namespace />calendarListPending = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					var instance = this;

					A.one('#<portlet:namespace />pendingCounter').html(event.newVal.length);

					syncCalendarsMap();

					scheduler.load();
				},
				'scheduler-calendar:visibleChange': syncCalendarsMap
			},
			boundingBox: '#<portlet:namespace />calendarListPending',
			calendars: <%= pendingCalendarsJSONArray %>,
			scheduler: <portlet:namespace />scheduler,
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

					syncCalendarsMap();

					scheduler.load();
				},
				'scheduler-calendar:visibleChange': syncCalendarsMap
			},
			boundingBox: '#<portlet:namespace />calendarListAccepted',
			calendars: <%= acceptedCalendarsJSONArray %>,
			scheduler: <portlet:namespace />scheduler,
			simpleMenu: calendarsMenu,
			strings: {
				emptyMessage: '<liferay-ui:message key="no-accepted-invites" />'
			}
		}
	).render();

	<c:if test="<%= calendarBooking != null %>">
		window.<portlet:namespace />calendarListDeclined = new Liferay.CalendarList(
			{
				after: {
					calendarsChange: function(event) {
						var instance = this;

						A.one('#<portlet:namespace />declinedCounter').html(event.newVal.length);

						syncCalendarsMap();

						scheduler.load();
					},
					'scheduler-calendar:visibleChange': syncCalendarsMap
				},
				boundingBox: '#<portlet:namespace />calendarListDeclined',
				calendars: <%= declinedCalendarsJSONArray %>,
				scheduler: <portlet:namespace />scheduler,
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

						syncCalendarsMap();

						scheduler.load();
					},
					'scheduler-calendar:visibleChange': syncCalendarsMap
				},
				boundingBox: '#<portlet:namespace />calendarListMaybe',
				calendars: <%= maybeCalendarsJSONArray %>,
				scheduler: <portlet:namespace />scheduler,
				simpleMenu: calendarsMenu,
				strings: {
					emptyMessage: '<liferay-ui:message key="no-outstanding-invites" />'
				}
			}
		).render();
	</c:if>

	syncCalendarsMap();

	var formNode = A.one(document.<portlet:namespace />fm);

	window.<portlet:namespace />placeholderSchedulerEvent = new Liferay.SchedulerEvent(
		{
			after: {
				endDateChange: function(event) {
					Liferay.DatePickerUtil.syncUI(formNode, 'endTime', event.newVal);
				},
				startDateChange: function(event) {
					Liferay.DatePickerUtil.syncUI(formNode, 'startTime', event.newVal);
				}
			},
			borderColor: '#000',
			borderStyle: 'dashed',
			borderWidth: '2px',
			color: '#F8F8F8',
			content: '&nbsp;',
			editingEvent: true,
			endDate: Liferay.CalendarUtil.toLocalTime(new Date(<%= endTime %>)),
			on: {
				endDateChange: function(event) {
					event.stopPropagation();
				},
				startDateChange: function(event) {
					event.stopPropagation();
				}
			},
			preventDateChange: true,
			scheduler: scheduler,
			startDate: Liferay.CalendarUtil.toLocalTime(new Date(<%= startTime %>))
		}
	);

	Liferay.DatePickerUtil.linkToSchedulerEvent('#<portlet:namespace />endDateContainer', window.<portlet:namespace />placeholderSchedulerEvent, 'endTime');
	Liferay.DatePickerUtil.linkToSchedulerEvent('#<portlet:namespace />startDateContainer', window.<portlet:namespace />placeholderSchedulerEvent, 'startTime');

	scheduler.after(
		'*:load',
		function(event) {
			scheduler.addEvents(window.<portlet:namespace />placeholderSchedulerEvent);

			scheduler.syncEventsUI();
		}
	);

	<c:if test="<%= invitable %>">
		var manageableCalendars = {};

		<%= CalendarUtil.toCalendarsJSONArray(themeDisplay, manageableCalendars) %>.forEach(
			function(item, index) {
				manageableCalendars[item.calendarId] = item;
			}
		);

		A.one('#<portlet:namespace />calendarId').on(
			'valueChange',
			function(event) {
				var calendarId = parseInt(event.target.val(), 10);

				var calendar = manageableCalendars[calendarId];

				[
					<portlet:namespace />calendarListAccepted,

					<c:if test="<%= calendarBooking != null %>">
						<portlet:namespace />calendarListDeclined, <portlet:namespace />calendarListMaybe,
					</c:if>

					<portlet:namespace />calendarListPending
				].forEach(
					function(calendarList) {
						calendarList.remove(calendarList.getCalendar(calendarId));
						calendarList.remove(calendarList.getCalendar(defaultCalendarId));
					}
				);

				<portlet:namespace />calendarListPending.add(calendar);

				defaultCalendarId = calendarId;
			}
		);

		var inviteResourcesInput = A.one('#<portlet:namespace />inviteResource');

		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL"></liferay-portlet:resourceURL>

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
					type: '<%= HtmlUtil.escapeJS(firstReminderType) %>'
				},
				{
					interval: <%= secondReminder %>,
					type: '<%= HtmlUtil.escapeJS(secondReminderType) %>'
				}
			]
		}
	);

	var allDayCheckbox = A.one('#<portlet:namespace />allDay');

	allDayCheckbox.after(
		'click',
		function() {
			var endDateContainer = A.one('#<portlet:namespace />endDateContainer');
			var startDateContainer = A.one('#<portlet:namespace />startDateContainer');

			var checked = allDayCheckbox.get('checked');

			if (checked) {
				window.<portlet:namespace />placeholderSchedulerEvent.set('allDay', true);
			}
			else {
				window.<portlet:namespace />placeholderSchedulerEvent.set('allDay', false);

				endDateContainer.show();
			}

			endDateContainer.toggleClass('allday-class-active', checked);
			startDateContainer.toggleClass('allday-class-active', checked);

			scheduler.syncEventsUI();
		}
	);

	scheduler.load();
</aui:script>