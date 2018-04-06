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

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Meeting meeting = null;

if (row != null) {
	meeting = (Meeting)row.getObject();
}
else {
	meeting = (Meeting)request.getAttribute("view_meeting.jsp-meeting");
}

long webExAccountId = ParamUtil.getLong(request, "webExAccountId");

WebExAccount webExAccount = WebExAccountLocalServiceUtil.fetchWebExAccount(webExAccountId);
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showExpanded="<%= row == null %>">
	<c:if test="<%= WebExAccountPermission.contains(permissionChecker, webExAccount, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/meetings/edit_meeting.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="meetingKey" value="<%= String.valueOf(meeting.getMeetingKey()) %>" />
			<portlet:param name="webExAccountId" value="<%= String.valueOf(webExAccountId) %>" />
			<portlet:param name="meetingName" value="<%= meeting.getName() %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-edit"
			message="edit"
			url="<%= editURL.toString() %>"
		/>

		<liferay-ui:icon
			iconCssClass="icon-cogs"
			message="start-meeting"
			target="_blank"
			url="<%= MeetingServiceUtil.getHostURL(meeting.getMeetingKey(), webExAccount.getMeetingContext()) %>"
		/>

		<portlet:actionURL name="deleteMeeting" var="deleteURL">
			<portlet:param name="redirect" value="<%= (row != null) ? currentURL : redirect %>" />
			<portlet:param name="meetingKey" value="<%= String.valueOf(meeting.getMeetingKey()) %>" />
			<portlet:param name="webExAccountId" value="<%= String.valueOf(webExAccountId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>