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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

long meetingKey = ParamUtil.getLong(request, "meetingKey");

long webExAccountId = ParamUtil.getLong(request, "webExAccountId");

WebExAccount webExAccount = WebExAccountLocalServiceUtil.fetchWebExAccount(webExAccountId);

Meeting meeting = null;

if ((meetingKey > 0) && (webExAccount != null)) {
	meeting = MeetingServiceUtil.getMeeting(meetingKey, webExAccount.getMeetingContext());
}
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (meeting != null) ? meeting.getName() : "new-meeting" %>'
/>

<portlet:actionURL name='<%= (meeting != null) ? "updateMeeting" : "addMeeting" %>' var="editMeetingURL" />

<aui:form action="<%= editMeetingURL %>" method="POST" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/meetings/edit_meeting.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="meetingKey" type="hidden" value="<%= (meeting != null) ? String.valueOf(meeting.getMeetingKey()) : StringPool.BLANK %>" />
	<aui:input name="webExAccountId" type="hidden" value="<%= String.valueOf(webExAccountId) %>" />

	<liferay-ui:error exception="<%= MeetingException.class %>">

		<%
		MeetingException me = (MeetingException)errorException;
		%>

		<c:choose>
			<c:when test="<%= me.getType() == MeetingException.INVALID_EMAIL_ADDRESS %>">
				<liferay-ui:message key="please-enter-a-valid-email-address" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="please-check-your-webex-credentials" />
			</c:otherwise>
		</c:choose>
	</liferay-ui:error>

	<aui:model-context bean="<%= meeting %>" model="<%= Meeting.class %>" />

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" name="name" type="text" />

		<aui:input cssClass="lfr-textarea-container" name="description" type="textarea" />

		<aui:input cssClass="lfr-input-text-container" name="password" type="password" />

		<%
		Calendar startCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone("GMT"), themeDisplay.getLocale());

		if (meeting != null) {
			startCalendar = meeting.getStartCalendar();
		}
		%>

		<aui:field-wrapper name="date">
			<liferay-ui:input-date
				dayParam="startDateDay"
				dayValue="<%= startCalendar.get(Calendar.DATE) %>"
				monthParam="startDateMonth"
				monthValue="<%= startCalendar.get(Calendar.MONTH) %>"
				yearParam="startDateYear"
				yearValue="<%= startCalendar.get(Calendar.YEAR) %>"
			/>

			<liferay-ui:input-time
				amPmParam="startDateAmPm"
				amPmValue="<%= startCalendar.get(Calendar.AM_PM) %>"
				hourParam="startDateHour"
				hourValue="<%= startCalendar.get(Calendar.HOUR) %>"
				minuteInterval="<%= 10 %>"
				minuteParam="startDateMinute"
				minuteValue="<%= startCalendar.get(Calendar.MINUTE) %>"
			/>
		</aui:field-wrapper>

		<aui:field-wrapper name="duration">
			<aui:select cssClass="event-duration-hour" label="hours" name="durationHour">

				<%
				for (int i = 0; i <= 24; i++) {
				%>

					<aui:option value="<%= i %>"><%= i %></aui:option>

				<%
				}
				%>

			</aui:select>

			<aui:select label="minutes" name="durationMinute">

				<%
				for (int i = 0; i < 60; i = i + 5) {
				%>

					<aui:option value="<%= i %>"><%= i %></aui:option>

				<%
				}
				%>

			</aui:select>
		</aui:field-wrapper>

		<aui:input cssClass="lfr-textarea-container" name="emailAddresses" type="textarea" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>