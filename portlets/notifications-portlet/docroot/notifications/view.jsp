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

<div class="clearfix">
	<div class="nav-bar user-notifications-sidebar">
		<div class="nav">
			<a class="clearfix selected unread" href="javascript:;">
				<span class="title"><liferay-ui:message key="unread" /></span>

				<%
				int unreadUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), false);
				%>

				<span class="count"><%= unreadUserNotificationsCount %></span>
			</a>
		</div>
		<div class="nav">
			<a class="all-notifications clearfix" href="javascript:;">
				<span class="title"><liferay-ui:message key="all-notifications" /></span>
			</a>
		</div>
		<div class="nav">
			<a class="manage clearfix" href="javascript:;">
				<span class="title"><liferay-ui:message key="manage" /></span>
			</a>
		</div>
	</div>

	<div class="user-notifications-list-container">
		<ul class="user-notifications-list">
			<div class="loading-mask"></div>
		</ul>
	</div>
</div>

<aui:script use="aui-base,aui-io-plugin-deprecated">
	var userNotifications = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %>');

	var userNotificationsList = userNotifications.one('.user-notifications-list-container .user-notifications-list');

	var renderUserNotificationsList = function(uri) {
		if (userNotificationsList) {
			if (!userNotificationsList.io) {
				userNotificationsList.plug(
					A.Plugin.IO,
					{
					autoLoad: false
					}
				);
			}

			userNotificationsList.io.set('uri', uri);
			userNotificationsList.io.start();
		}
	}

	<portlet:renderURL var="unreadURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
		<portlet:param name="filter" value="unread" />
	</portlet:renderURL>

	renderUserNotificationsList('<%= unreadURL %>');

	var userNotificationsSidebar = userNotifications.one('.user-notifications-sidebar');

	var unreadNav = userNotificationsSidebar.one('.unread');

	if (unreadNav) {
		unreadNav.on(
			'click',
			function(event) {
				renderUserNotificationsList('<%= unreadURL %>');

				A.io.request('<liferay-portlet:actionURL name="setDelivered" />');

				userNotificationsSidebar.all('.nav a').removeClass('selected');

				unreadNav.addClass('selected');
			}
		)
	}

	var allNotificationsNav = userNotificationsSidebar.one('.all-notifications');

	if (allNotificationsNav) {
		allNotificationsNav.on(
			'click',
			function(event) {
				<portlet:renderURL var="allNotificationsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
				</portlet:renderURL>

				renderUserNotificationsList('<%= allNotificationsURL %>');

				A.io.request('<liferay-portlet:actionURL name="setDelivered" />');

				userNotificationsSidebar.all('.nav a').removeClass('selected');

				allNotificationsNav.addClass('selected');
			}
		)
	}

	var manageNav = userNotificationsSidebar.one('.manage');

	if (manageNav) {
		manageNav.on(
			'click',
			function(event) {
				<portlet:renderURL var="configurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="mvcPath" value="/notifications/configuration.jsp" />
				</portlet:renderURL>

				renderUserNotificationsList('<%= configurationURL %>');

				userNotificationsSidebar.all('.nav a').removeClass('selected');

				manageNav.addClass('selected');
			}
		)
	}

	userNotificationsList.delegate(
		'click',
		function(event) {
			event.preventDefault();

			Liferay.Notifications.viewNotification(event);
		},
		'.user-notification .user-notification-link'
	);
</aui:script>