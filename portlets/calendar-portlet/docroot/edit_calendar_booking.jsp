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
String activeView = ParamUtil.getString(request, "activeView", defaultView);

java.util.Calendar nowJCalendar = CalendarFactoryUtil.getCalendar(userTimeZone);

long date = ParamUtil.getLong(request, "date", nowJCalendar.getTimeInMillis());

CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);

long calendarBookingId = BeanParamUtil.getLong(calendarBooking, request, "calendarBookingId");

long calendarId = BeanParamUtil.getLong(calendarBooking, request, "calendarId", userDefaultCalendar.getCalendarId());
String title = BeanParamUtil.getString(calendarBooking, request, "titleCurrentValue");

long startTime = BeanParamUtil.getLong(calendarBooking, request, "startTime", nowJCalendar.getTimeInMillis());

java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime, userTimeZone);

java.util.Calendar defaultEndTimeJCalendar = (java.util.Calendar)nowJCalendar.clone();

defaultEndTimeJCalendar.add(java.util.Calendar.HOUR, 1);

long endTime = BeanParamUtil.getLong(calendarBooking, request, "endTime", defaultEndTimeJCalendar.getTimeInMillis());

java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(endTime, userTimeZone);

boolean allDay = BeanParamUtil.getBoolean(calendarBooking, request, "allDay");

if (!allDay) {
	com.liferay.portal.kernel.util.CalendarUtil.roundByMinutes(startTimeJCalendar, 30);
	com.liferay.portal.kernel.util.CalendarUtil.roundByMinutes(endTimeJCalendar, 30);
}

long firstReminder = BeanParamUtil.getLong(calendarBooking, request, "firstReminder");
String firstReminderType = BeanParamUtil.getString(calendarBooking, request, "firstReminderType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);
long secondReminder = BeanParamUtil.getLong(calendarBooking, request, "secondReminder");
String secondReminderType = BeanParamUtil.getString(calendarBooking, request, "secondReminderType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);

JSONArray acceptedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray declinedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray maybeCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray pendingCalendarsJSONArray = JSONFactoryUtil.createJSONArray();

boolean invitable = true;
boolean recurring = false;
Recurrence recurrence = null;

Calendar calendar = CalendarServiceUtil.fetchCalendar(calendarId);

if (calendarBooking != null) {
	acceptedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED));
	declinedCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_DENIED));
	maybeCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_MAYBE));
	pendingCalendarsJSONArray = CalendarUtil.toCalendarBookingsJSONArray(themeDisplay, CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_PENDING));

	if (!calendarBooking.isMasterBooking()) {
		invitable = false;
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

<liferay-portlet:actionURL name="updateCalendarBooking" var="updateCalendarBookingURL" />

<aui:form action="<%= updateCalendarBookingURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarBooking();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/edit_calendar_booking.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBookingId %>" />
	<aui:input name="childCalendarIds" type="hidden" />
	<aui:input name="oldStartTime" type="hidden" value="<%= startTimeJCalendar.getTimeInMillis() %>" />
	<aui:input name="allFollowing" type="hidden" />
	<aui:input name="updateCalendarBookingInstance" type="hidden" />

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= calendarBooking %>" model="<%= CalendarBooking.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<div class="<%= allDay ? "allday-class-active" : "" %>" id="<portlet:namespace />startDateContainer">
			<aui:input label="start-date" name="startTime" value="<%= startTimeJCalendar %>" />
		</div>

		<div class="<%= allDay ? "allday-class-active" : "" %>" id="<portlet:namespace />endDateContainer">
			<aui:input label="end-date" name="endTime" value="<%= endTimeJCalendar %>" />
		</div>

		<aui:input checked="<%= allDay %>" name="allDay" />

		<aui:field-wrapper cssClass="calendar-portlet-recurrence-container" inlineField="<%= true %>" label="">
			<aui:input checked="<%= recurring %>" name="repeat" type="checkbox" />

			<a class="calendar-portlet-recurrence-summary" href="javascript:;" id="<portlet:namespace />summary"></a>
		</aui:field-wrapper>
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

						<aui:option selected="<%= curCalendar.getCalendarId() == calendarId %>" value="<%= curCalendar.getCalendarId() %>"><%= curCalendar.getName(locale) %></aui:option>

					<%
					}
					%>

				</aui:select>

				<aui:input name="description" />

				<aui:input name="location" />

				<liferay-ui:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
					<liferay-ui:custom-attribute-list
						className="<%= CalendarBooking.class.getName() %>"
						classPK="<%= (calendarBooking != null) ? calendarBooking.getCalendarBookingId() : 0 %>"
						editable="<%= true %>"
						label="<%= true %>"
					/>
				</liferay-ui:custom-attributes-available>
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

			<liferay-ui:panel collapsible="<%= true %>" defaultState="open" extended="<%= false %>" id="calendarBookingInvitationPanel" persistState="<%= true %>" title="invitations">
				<c:if test="<%= invitable %>">
					<aui:input inputCssClass="calendar-portlet-invite-resources-input" label="" name="inviteResource" placeholder="add-people-groups-rooms" type="text" />

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
						<a class="aui-toggler-header-collapsed calendar-portlet-list-header" href="javascript:;" id="<portlet:namespace />checkAvailability">
							<span class="calendar-portlet-list-arrow"></span>

							<span class="calendar-portlet-list-text"><liferay-ui:message key="resources-availability" /></span>
						</a>

						<div class="calendar-portlet-availability">
							<div class="aui-toggler-content-collapsed" id="<portlet:namespace />schedulerContainer">
								<div id="<portlet:namespace />message"></div>

								<liferay-util:include page="/scheduler.jsp" servletContext="<%= application %>">
									<liferay-util:param name="activeView" value="<%= activeView %>" />
									<liferay-util:param name="date" value="<%= String.valueOf(date) %>" />
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
		</liferay-ui:panel-container>
	</aui:fieldset>

	<%@ include file="/calendar_booking_recurrence_container.jspf" %>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel" value="close" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />filterCalendarBookings(calendarBooking) {
		return <%= calendarBookingId %> !== calendarBooking.calendarBookingId;
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateCalendarBooking',
		function() {
			var A = AUI();

			<c:if test="<%= invitable %>">
				var calendarId = A.one('#<portlet:namespace />calendarId').val();
				var childCalendarIds = A.Object.keys(Liferay.CalendarUtil.availableCalendars);

				A.Array.remove(childCalendarIds, A.Array.indexOf(childCalendarIds, calendarId));

				A.one('#<portlet:namespace />childCalendarIds').val(childCalendarIds.join(','));
			</c:if>

			<c:if test="<%= calendarBooking == null %>">
				submitForm(document.<portlet:namespace />fm);
			</c:if>

			<c:if test="<%= (calendarBooking != null) && (calendar != null) %>">
				<c:choose>
					<c:when test="<%= recurring %>">
						Liferay.RecurrenceUtil.openConfirmationPanel(
							'update',
							'<%= calendarBooking.isMasterBooking() %>',
							function() {
								A.one('#<portlet:namespace />updateCalendarBookingInstance').val('true');

								submitForm(document.<portlet:namespace />fm);
							},
							function() {
								A.one('#<portlet:namespace />allFollowing').val('true');
								A.one('#<portlet:namespace />updateCalendarBookingInstance').val('true');

								submitForm(document.<portlet:namespace />fm);
							},
							function() {
								submitForm(document.<portlet:namespace />fm);
							}
						);
					</c:when>
					<c:when test="<%= calendarBooking.isMasterBooking() %>">
						submitForm(document.<portlet:namespace />fm);
					</c:when>
					<c:otherwise>
						var content = [
							'<p class="calendar-portlet-confirmation-text">',
							A.Lang.sub(
								Liferay.Language.get('you-are-about-to-make-changes-that-will-only-effect-your-calendar-x'),
								['<%= calendar.getName(locale) %>']
							),
							'</p>'
						].join('');

						Liferay.CalendarMessageUtil.confirm(
							content,
							Liferay.Language.get('continue'),
							Liferay.Language.get('dont-change-the-event'),
							function() {
								submitForm(document.<portlet:namespace />fm);

								this.close();
							},
							function() {
								this.close();
							}
						);
					</c:otherwise>
				</c:choose>
			</c:if>
		},
		['liferay-calendar-message-util', 'json']
	);

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);

	<c:if test="<%= calendarBooking == null %>">
		document.<portlet:namespace />fm.<portlet:namespace />title_<%= LanguageUtil.getLanguageId(request) %>.value = '<%= HtmlUtil.escapeJS(title) %>';
	</c:if>
</aui:script>

<aui:script use="json,liferay-calendar-date-picker-util,liferay-calendar-list,liferay-calendar-recurrence-util,liferay-calendar-reminders,liferay-calendar-simple-menu">
	var defaultCalendarId = <%= calendarId %>;

	var scheduler = window.<portlet:namespace />scheduler;

	var removeCalendarResource = function(calendarList, calendar, menu) {
		calendarList.remove(calendar);

		if (menu) {
			menu.hide();
		}
	}

	var syncCalendarsMap = function() {
		Liferay.CalendarUtil.syncCalendarsMap(
			window.<portlet:namespace />calendarListAccepted,
			window.<portlet:namespace />calendarListDeclined,
			window.<portlet:namespace />calendarListMaybe,
			window.<portlet:namespace />calendarListPending
		);

		A.each(
			Liferay.CalendarUtil.availableCalendars,
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
						var activeView = scheduler.get('activeView');

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
				caption: '<%= UnicodeLanguageUtil.get(pageContext, "check-availability") %>',
				fn: function(event) {
					var instance = this;

					A.each(
						Liferay.CalendarUtil.availableCalendars,
						function(item, index, collection) {
							item.set('visible', false);
						}
					);

					var calendarList = instance.get('host');

					calendarList.activeItem.set('visible', true);

					<portlet:namespace />toggler.expand();

					instance.hide();

					return false;
				},
				id: 'check-availability'
			}
			<c:if test="<%= invitable %>">
				,{
					caption: '<%= UnicodeLanguageUtil.get(pageContext, "remove") %>',
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
			borderStyle: 'dashed',
			borderWidth: '2px',
			color: '#F8F8F8',
			content: '&nbsp;',
			editingEvent: true,
			endDate: Liferay.CalendarUtil.toUserTimeZone(new Date(<%= endTime %>)),
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
			startDate: Liferay.CalendarUtil.toUserTimeZone(new Date(<%= startTime %>))
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
		A.one('#<portlet:namespace />calendarId').on(
			'valueChange',
			function(event) {
				var calendarId = parseInt(event.target.val(), 10);
				var calendarJSON = Liferay.CalendarUtil.manageableCalendars[calendarId];

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

	var allDayCheckbox = A.one('#<portlet:namespace />allDayCheckbox');

	allDayCheckbox.after(
		'click',
		function () {
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