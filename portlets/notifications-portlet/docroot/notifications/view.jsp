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

<div class="clearfix notifications-container">
	<aui:row>
		<aui:col cssClass="nav-bar user-notifications-sidebar" width="<%= 25 %>">
			<div class="nav">
				<a class="all-notifications clearfix selected" href="javascript:;">
					<span class="title"><liferay-ui:message key="all-notifications" /></span>
				</a>
			</div>

			<div class="nav">
				<a class="clearfix unread-actionable" href="javascript:;">
					<span class="title"><liferay-ui:message key="unread-actionable-notifications" /></span>

					<%
					int unreadActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false);
					%>

					<span class="count"><%= unreadActionableUserNotificationsCount %></span>
				</a>
			</div>

			<div class="nav">
				<a class="clearfix unread-non-actionable" href="javascript:;">
					<span class="title"><liferay-ui:message key="unread-non-actionable-notifications" /></span>

					<%
					int unreadNonActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false);
					%>

					<span class="count"><%= unreadNonActionableUserNotificationsCount %></span>
				</a>
			</div>

			<div class="nav">
				<a class="manage clearfix" href="javascript:;">
					<span class="title"><liferay-ui:message key="notification-delivery" /></span>
				</a>
			</div>
		</aui:col>

		<aui:col cssClass="user-notifications-list-container" width="<%= 75 %>">
			<ul class="unstyled user-notifications-list">
				<div class="loading-mask"></div>
			</ul>
		</aui:col>
	</aui:row>
</div>

<aui:script use="aui-base">
	var userNotificationsList = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %> .user-notifications-list-container .user-notifications-list');

	<portlet:renderURL var="allNotificationsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
	</portlet:renderURL>

	Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= allNotificationsURL %>');
</aui:script>