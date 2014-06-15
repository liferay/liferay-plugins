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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

BBBMeeting bbbMeeting = null;

if (row != null) {
	bbbMeeting = (BBBMeeting)row.getObject();
}
%>

<liferay-ui:icon-menu showExpanded="<%= row == null %>">
	<c:if test="<%= BBBMeetingPermission.contains(permissionChecker, bbbMeeting, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/meetings/edit_meeting.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= BBBMeetingPermission.contains(permissionChecker, bbbMeeting, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= BBBMeeting.class.getName() %>"
			modelResourceDescription="<%= bbbMeeting.getName() %>"
			resourcePrimKey="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= BBBMeetingPermission.contains(permissionChecker, bbbMeeting, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteBBBMeeting" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>

	<c:if test="<%= BBBMeetingPermission.contains(permissionChecker, bbbMeeting, ActionKeys.UPDATE) %>">
		<c:if test="<%= bbbMeeting.getStatus() == BBBMeetingConstants.STATUS_IN_PROGRESS %>">
			<portlet:actionURL name="endBBBMeeting" var="endMeetingURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="unlink"
				message="end-meeting"
				url="<%= endMeetingURL %>"
			/>
		</c:if>

		<c:if test="<%= bbbMeeting.getStatus() == BBBMeetingConstants.STATUS_SCHEDULED %>">
			<portlet:actionURL name="startBBBMeeting" var="startMeetingURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="conversation"
				message="start-meeting"
				url="<%= startMeetingURL %>"
			/>

			<portlet:actionURL name="startBBBMeeting" var="startMeetingWithRecordingURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>" />
				<portlet:param name="recordMeeting" value="true" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="desktop"
				message="start-meeting-with-recording"
				url="<%= startMeetingWithRecordingURL %>"
			/>
		</c:if>

		<c:if test="<%= bbbMeeting.getStatus() == BBBMeetingConstants.STATUS_IN_PROGRESS %>">
			<portlet:actionURL name="joinBBBMeeting" var="joinMeetingURL">
				<portlet:param name="bbbMeetingId" value="<%= String.valueOf(bbbMeeting.getBbbMeetingId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="conversation"
				message="join-meeting"
				url="<%= joinMeetingURL %>"
			/>
		</c:if>
	</c:if>
</liferay-ui:icon-menu>