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

long bbbMeetingId = ParamUtil.getLong(request, "bbbMeetingId");

BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.fetchBBBMeeting(bbbMeetingId);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= (bbbMeeting == null) %>"
	title='<%= (bbbMeeting != null) ? bbbMeeting.getName() : "new-meeting" %>'
/>

<liferay-portlet:actionURL name="updateBBBMeeting" var="editBBBMeetingURL" />

<aui:form action="<%= editBBBMeetingURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="bbbMeetingId" type="hidden" value="<%= String.valueOf(bbbMeetingId) %>" />

	<aui:model-context bean="<%= bbbMeeting %>" model="<%= BBBMeeting.class %>" />

	<aui:input name="name" />

	<aui:input name="description" />

	<liferay-util:include page="/meetings/participants.jsp" servletContext="<%= application %>">
		<liferay-util:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeetingId) %>" />
	</liferay-util:include>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>