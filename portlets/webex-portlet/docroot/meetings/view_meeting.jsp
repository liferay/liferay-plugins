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
String backURL = ParamUtil.getString(request, "redirect");

long meetingKey = ParamUtil.getLong(request, "meetingKey");

long webExAccountId = ParamUtil.getLong(request, "webExAccountId");

WebExAccount webExAccount = WebExAccountServiceUtil.getWebExAccount(webExAccountId);

Meeting meeting = MeetingServiceUtil.getMeeting(meetingKey, webExAccount.getMeetingContext());

request.setAttribute("view_meeting.jsp-meeting", meeting);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= meeting.getName() %>"
/>

<aui:row cssClass="meeting">
	<aui:col cssClass="folder-column folder-column-first" width="<%= 75 %>">
		<dl class="property-list">
			<dt>
				<liferay-ui:message key="meeting-name" />:
			</dt>
			<dd>
				<%= HtmlUtil.escape(meeting.getName()) %>
			</dd>
			<dt>
				<liferay-ui:message key="meeting-key" />:
			</dt>
			<dd>
				<%= HtmlUtil.escape(String.valueOf(meeting.getMeetingKey())) %>
			</dd>
			<dt>
				<liferay-ui:message key="date" />:
			</dt>

			<%
			Calendar startCalendar = meeting.getStartCalendar();

			Date date = startCalendar.getTime();
			%>

			<dd>
				<%= HtmlUtil.escape(dateFormatDate.format(date)) %>
			</dd>
			<dt>
				<liferay-ui:message key="time" />:
			</dt>
			<dd>
				<%= HtmlUtil.escape(dateFormatTime.format(date)) %>
			</dd>
			<dt>
				<liferay-ui:message key="duration" />:
			</dt>
			<dd>

				<%
				long duration = meeting.getDuration();
				%>

				<%= (duration / 60) %>

				<liferay-ui:message key="hours" />

				<%= (duration % 60) %>

				<liferay-ui:message key="minutes" />
			</dd>

			<c:if test="<%= Validator.isNotNull(meeting.getDescription()) %>">
				<dt>
					<liferay-ui:message key="description" />:
				</dt>
				<dd>
					<%= HtmlUtil.escape(meeting.getDescription()) %>
				</dd>
			</c:if>

			<%
			List<String> emailAddresses = new ArrayList<String>();

			for (MeetingParticipant meetingParticipant : meeting.getMeetingParticipants()) {
				emailAddresses.add(meetingParticipant.getEmailAddress());
			}

			String emailAddressesString = StringUtil.merge(emailAddresses, StringPool.COMMA_AND_SPACE);
			%>

			<c:if test="<%= Validator.isNotNull(emailAddressesString) %>">
				<dt>
					<liferay-ui:message key="participants" />:
				</dt>
				<dd>
					<%= HtmlUtil.escape(emailAddressesString) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(meeting.getPassword()) %>">
				<dt>
					<liferay-ui:message key="password" />:
				</dt>
				<dd>
					<%= HtmlUtil.escape(meeting.getPassword()) %>
				</dd>
			</c:if>
		</dl>
	</aui:col>

	<aui:col cssClass="detail-column detail-column-last" width="<%= 25 %>">
		<div class="folder-icon">
			<liferay-ui:icon
				cssClass="folder-avatar"
				image="../file_system/large/calendar"
				message='<%= LanguageUtil.get(request, "meeting") %>'
			/>

			<div class="meeting-name">
				<h4><%= HtmlUtil.escape(meeting.getName()) %></h4>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/meetings/meeting_action.jsp" servletContext="<%= application %>" />
	</aui:col>
</aui:row>