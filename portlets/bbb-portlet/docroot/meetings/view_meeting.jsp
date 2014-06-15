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
String backURL = ParamUtil.getString(request, "backURL");

long bbbMeetingId = ParamUtil.getLong(request, "bbbMeetingId");

BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.fetchBBBMeeting(bbbMeetingId);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= bbbMeeting.getName() %>"
/>

<dt>
	<liferay-ui:message key="name" />
</dt>
<dd>
	<%= HtmlUtil.escape(bbbMeeting.getName()) %>
</dd>

<c:if test="<%= Validator.isNotNull(bbbMeeting.getDescription()) %>">
	<dt>
		<liferay-ui:message key="description" />
	</dt>
	<dd>
		<%= HtmlUtil.escape(bbbMeeting.getDescription()) %>
	</dd>
</c:if>

<dt>
	<liferay-ui:message key="participants" />
</dt>
<dd>
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-participants"
		total="<%= BBBParticipantLocalServiceUtil.getBBBParticipantsCount(bbbMeetingId) %>"
	>

		<liferay-ui:search-container-results>

			<%
			searchContainer.setResults(BBBParticipantLocalServiceUtil.getBBBParticipants(bbbMeetingId));
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.bbb.model.BBBParticipant"
			escapedModel="<%= true %>"
			keyProperty="bbbParticipantId"
			modelVar="bbbParticipant"
		>
			<liferay-ui:search-container-column-text
				name="name"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
				property="emailAddress"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				translate="<%= true %>"
				value="<%= BBBParticipantConstants.getTypeLabel(bbbParticipant.getType()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</dd>

<%
List<String> meetingRecordings = new ArrayList<String>();

if (bbbMeeting.getStatus() == BBBMeetingConstants.STATUS_COMPLETED) {
	meetingRecordings = BBBAPIUtil.getMeetingRecordings(bbbMeetingId);
}
%>

<c:if test="<%= !meetingRecordings.isEmpty() %>">
	<dt>
		<liferay-ui:message key="recordings" />
	</dt>

		<%
		for (String meetingRecording : meetingRecordings) {
		%>

			<dd>
				<a href="<%= HtmlUtil.escape(meetingRecording) %>" target="_blank">
					<%= HtmlUtil.escape(meetingRecording) %>
				</a>
			</dd>

		<%
		}
		%>

</c:if>