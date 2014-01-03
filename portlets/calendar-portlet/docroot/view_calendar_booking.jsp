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
String backURL = ParamUtil.getString(request, "backURL");

CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);

Calendar calendar = calendarBooking.getCalendar();

long startTime = BeanParamUtil.getLong(calendarBooking, request, "startTime");

java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime, userTimeZone);

long endTime = BeanParamUtil.getLong(calendarBooking, request, "endTime");

java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(endTime, userTimeZone);

boolean allDay = BeanParamUtil.getBoolean(calendarBooking, request, "allDay");

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), calendarBooking.getCalendarBookingId());
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= calendarBooking.getTitle(locale) %>"
/>

<aui:fieldset>
	<aui:field-wrapper label="start-date">
		<liferay-ui:input-resource url='<%= dateFormatLongDate.format(startTimeJCalendar.getTime()) + ", " + dateFormatTime.format(startTimeJCalendar.getTime()) %>' />
	</aui:field-wrapper>

	<aui:field-wrapper label="end-date">
		<liferay-ui:input-resource url='<%= dateFormatLongDate.format(endTimeJCalendar.getTime()) + ", " + dateFormatTime.format(endTimeJCalendar.getTime()) %>' />
	</aui:field-wrapper>

	<%
	List<CalendarBooking> childCalendarBookings = calendarBooking.getChildCalendarBookings();
	%>

	<c:if test="<%= !childCalendarBookings.isEmpty() %>">
		<aui:field-wrapper label="resources">

			<%
			List<String> calendarResourcesNames = new ArrayList<String>();

			for (CalendarBooking childCalendarBooking : childCalendarBookings) {
				CalendarResource calendarResource = childCalendarBooking.getCalendarResource();

				calendarResourcesNames.add(calendarResource.getName(locale));
			}
			%>

			<liferay-ui:input-resource url='<%= StringUtil.merge(calendarResourcesNames, ", ") %>' />
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= Validator.isNotNull(calendarBooking.getLocation()) %>">
		<aui:field-wrapper label="location">
			<liferay-ui:input-resource url="<%= calendarBooking.getLocation() %>" />
		</aui:field-wrapper>
	</c:if>

	<liferay-ui:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
		<liferay-ui:custom-attribute-list
			className="<%= CalendarBooking.class.getName() %>"
			classPK="<%= calendarBooking.getCalendarBookingId() %>"
			editable="<%= false %>"
			label="<%= true %>"
		/>
	</liferay-ui:custom-attributes-available>

	<aui:field-wrapper label="description">
		<aui:input disabled="<%= true %>" label="" name="descriptionDisplay" type="textarea" value="<%= calendarBooking.getDescription(locale) %>" />
	</aui:field-wrapper>

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

	<c:if test="<%= calendar.isEnableRatings() %>">
		<div class="entry-ratings">
			<liferay-ui:ratings
				className="<%= CalendarBooking.class.getName() %>"
				classPK="<%= calendarBooking.getCalendarBookingId() %>"
			/>
		</div>
	</c:if>
</aui:fieldset>

<c:if test="<%= calendar.isEnableComments() %>">
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
</c:if>

<portlet:actionURL name="invokeTransition" var="invokeTransitionURL" />

<aui:form action="<%= invokeTransitionURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBooking.getCalendarBookingId() %>" />
	<aui:input name="status" type="hidden" />

	<aui:fieldset>
		<aui:button-row>

			<%
			boolean hasManageBookingsPermission = CalendarPermission.contains(permissionChecker, calendar, ActionKeys.MANAGE_BOOKINGS);
			%>

			<c:if test="<%= hasManageBookingsPermission && (calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_APPROVED) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_APPROVED + ");" %>' value="accept" />
			</c:if>

			<c:if test="<%= hasManageBookingsPermission && (calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_MAYBE) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_MAYBE + ");" %>' value="maybe" />
			</c:if>

			<c:if test="<%= hasManageBookingsPermission && (calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_DENIED) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_DENIED + ");" %>' value="decline" />
			</c:if>
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />invokeTransition(status) {
		document.<portlet:namespace />fm.<portlet:namespace />status.value = status;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>