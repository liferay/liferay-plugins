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
CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);

long startTime = BeanParamUtil.getLong(calendarBooking, request, "startTime");

java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime, userTimeZone);

long endTime = BeanParamUtil.getLong(calendarBooking, request, "endTime");

java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(endTime, userTimeZone);

boolean allDay = BeanParamUtil.getBoolean(calendarBooking, request, "allDay");

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), calendarBooking.getCalendarBookingId());
%>

<liferay-ui:header
	title="<%= calendarBooking.getTitle(locale) %>"
/>

<aui:fieldset>
	<dl class="property-list">
		<dt>
			<liferay-ui:message key="start-date" />:
		</dt>
		<dd>
			<%= dateFormatLongDate.format(startTimeJCalendar.getTime()) + ", " + dateFormatTime.format(startTimeJCalendar.getTime()) %>
		</dd>
		<dt>
			<liferay-ui:message key="end-date" />:
		</dt>
		<dd>
			<%= dateFormatLongDate.format(endTimeJCalendar.getTime()) + ", " + dateFormatTime.format(endTimeJCalendar.getTime()) %>
		</dd>

		<%
		List<CalendarBooking> childCalendarBookings = calendarBooking.getChildCalendarBookings();
		%>

		<c:if test="<%= !childCalendarBookings.isEmpty() %>">
			<dt>
				<liferay-ui:message key="resources" />:
			</dt>
			<dd>

				<%
				List<String> calendarResourcesNames = new ArrayList<String>();

				for (CalendarBooking childCalendarBooking : childCalendarBookings) {
					CalendarResource calendarResource = childCalendarBooking.getCalendarResource();

					calendarResourcesNames.add(calendarResource.getName(locale));
				}
				%>

				<%= StringUtil.merge(calendarResourcesNames, ", ") %>
			</dd>
		</c:if>
		<c:if test="<%= Validator.isNotNull(calendarBooking.getLocation()) %>">
			<dt>
				<liferay-ui:message key="location" />:
			</dt>
			<dd>
				<span class="location"><%= HtmlUtil.escape(calendarBooking.getLocation()) %></span>
			</dd>
		</c:if>
	</dl>

	<liferay-ui:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
		<liferay-ui:custom-attribute-list
			className="<%= CalendarBooking.class.getName() %>"
			classPK="<%= calendarBooking.getCalendarBookingId() %>"
			editable="<%= false %>"
			label="<%= true %>"
		/>
	</liferay-ui:custom-attributes-available>

	<p>
		<%= calendarBooking.getDescription(locale) %>
	</p>

	<div class="entry-categories">
		<liferay-ui:asset-categories-summary
			className="<%= CalendarBooking.class.getName() %>"
			classPK="<%= calendarBooking.getCalendarBookingId() %>"
		/>
	</div>

	<div class="entry-tags">
		<liferay-ui:asset-tags-summary
			className="<%= CalendarBooking.class.getName() %>"
			classPK="<%= calendarBooking.getCalendarBookingId() %>"
			message="tags"
		/>
	</div>

	<div class="entry-links">
		<liferay-ui:asset-links
			assetEntryId="<%= layoutAssetEntry.getEntryId() %>"
		/>
	</div>

	<div class="entry-ratings">
		<liferay-ui:ratings
			className="<%= CalendarBooking.class.getName() %>"
			classPK="<%= calendarBooking.getCalendarBookingId() %>"
		/>
	</div>
</aui:fieldset>

<aui:fieldset>
	<liferay-ui:panel-container extended="<%= false %>" id="calendarBookingPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="calendarBookingCommentsPanel" persistState="<%= true %>" title="comments">
			<liferay-portlet:actionURL name="updateDiscussion" var="updateDiscussionURL" />

			<liferay-ui:discussion
				className="<%= CalendarBooking.class.getName() %>"
				classPK="<%= calendarBooking.getCalendarBookingId() %>"
				formAction="<%= updateDiscussionURL %>"
				formName="fm2"
				ratingsEnabled="true"
				redirect="<%= currentURL %>"
				subject="<%= calendarBooking.getTitle(locale) %>"
				userId="<%= calendarBooking.getUserId() %>"
			/>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</aui:fieldset>