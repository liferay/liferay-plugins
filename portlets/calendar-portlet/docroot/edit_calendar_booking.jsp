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

JSONArray acceptedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray declinedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray maybeCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray pendingCalendarsJSONArray = JSONFactoryUtil.createJSONArray();

boolean invitable = true;

Calendar calendar = CalendarServiceUtil.fetchCalendar(calendarId);

if (calendarBooking != null) {
	startDateJCalendar.setTime(calendarBooking.getStartDate());
	endDateJCalendar.setTime(calendarBooking.getEndDate());

	acceptedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED));
	declinedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_DENIED));
	maybeCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_MAYBE));
	pendingCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_PENDING));

	if (!calendarBooking.isMasterBooking()) {
		invitable = false;
	}
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

boolean reminderActive = (calendarBooking !=  null) && ((calendarBooking.getFirstReminder() > 0) || (calendarBooking.getSecondReminder() > 0));
int firstReminder = BeanParamUtil.getInteger(calendarBooking, request, "firstReminder", (int)Time.MINUTE * 15);
int secondReminder = BeanParamUtil.getInteger(calendarBooking, request, "secondReminder", (int)Time.MINUTE * 5);
User bookingUser = UserLocalServiceUtil.getUser(BeanParamUtil.getLong(calendarBooking, request, "userId", user.getUserId()));

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
	<aui:model-context bean="<%= calendarBooking %>" model="<%= CalendarBooking.class %>" />

	<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBookingId %>" />
	<aui:input name="childCalendarIds" type="hidden" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:input name="startDate" value="<%= startDateJCalendar %>" />

		<div id="<portlet:namespace />endDateContainer">
			<aui:input name="endDate" value="<%= endDateJCalendar %>" />
		</div>

		<aui:input name="allDay" />

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
	</aui:fieldset>

	<aui:fieldset>
		<liferay-ui:panel-container extended="<%= false %>" id="templateDetailsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="calendarBookingDetailsSectionPanel" persistState="<%= true %>" title="details">
				<aui:input name="description" />

				<aui:input name="location" />
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="calendarBookingReminderSectionPanel" persistState="<%= true %>" title="reminder">
				<span class="aui-field-row">
					<span class="aui-field-content">
						<aui:input
							name="reminder"
							checked="<%= !reminderActive %>"
							type="radio"
							value="<%= StringPool.FALSE %>"
							label="do-not-send-a-reminder"
						/>
					</span>
				</span>
				<span class="aui-field-row">
					<aui:input
						name="reminder"
						checked="<%= reminderActive %>"
						type="radio"
						label=''
						value="<%= StringPool.TRUE %>"
						inlineField="true"
					/>

					<aui:select inlineField="<%= true %>" inlineLabel="left" label="remind-me" name="firstReminder">

						<%
						for (int i = 0; i < CalendarBookingConstants.REMINDERS.length; i++) {
						%>

							<aui:option selected="<%= (firstReminder == CalendarBookingConstants.REMINDERS[i]) %>" value="<%= CalendarBookingConstants.REMINDERS[i] %>"><%= LanguageUtil.getTimeDescription(pageContext, CalendarBookingConstants.REMINDERS[i]) %></aui:option>

						<%
						}
						%>

					</aui:select>

					<aui:select inlineField="<%= true %>" inlineLabel="left" label="before-and-again" name="secondReminder" suffix="before-the-event-by">

						<%
						for (int i = 0; i < CalendarBookingConstants.REMINDERS.length; i++) {
						%>

							<aui:option selected="<%= (secondReminder == CalendarBookingConstants.REMINDERS[i]) %>" value="<%= CalendarBookingConstants.REMINDERS[i] %>"><%= LanguageUtil.getTimeDescription(pageContext, CalendarBookingConstants.REMINDERS[i]) %></aui:option>

						<%
						}
						%>

					</aui:select>

					<%= bookingUser.getEmailAddress() %>
				</span>
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
			</aui:layout>
		</liferay-ui:section>
	</liferay-ui:tabs>

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

<aui:script use="json,liferay-calendar-list,liferay-calendar-simple-menu">
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
	}

	var calendarsMenu = {
		items: [
			{
				caption: '<liferay-ui:message key="check-availability" />',
				fn: function(event) {
					var instance = this;

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

				<portlet:namespace />calendarListPending.add(calendar);

				inviteResourcesInput.val('');
			}
		);
	</c:if>
</aui:script>