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

<aui:script use="aui-base,aui-io-plugin-deprecated">
	var userNotifications = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %>');

	var userNotificationsList = userNotifications.one('.user-notifications-list-container .user-notifications-list');

	<portlet:renderURL var="unreadURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
		<portlet:param name="filter" value="unread" />
	</portlet:renderURL>

	Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= unreadURL %>');

	var userNotificationsSidebar = userNotifications.one('.user-notifications-sidebar');

	var clickSidebarMenu = function(node, uri) {
		if (node) {
			node.on(
				'click',
				function(event) {
					Liferay.Notifications.renderNotificationsList(userNotificationsList, uri);

					A.io.request('<liferay-portlet:actionURL name="setDelivered" />');

					userNotificationsSidebar.all('.nav a').removeClass('selected');

					node.addClass('selected');
				}
			);
		}
	};

	var unreadNav = userNotificationsSidebar.one('.unread');

	clickSidebarMenu(unreadNav, '<%= unreadURL %>');

	var allNotificationsNav = userNotificationsSidebar.one('.all-notifications');

	<portlet:renderURL var="allNotificationsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
	</portlet:renderURL>

	clickSidebarMenu(allNotificationsNav, '<%= allNotificationsURL %>');

	var manageNav = userNotificationsSidebar.one('.manage');

	<portlet:renderURL var="configurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcPath" value="/notifications/configuration.jsp" />
	</portlet:renderURL>

	clickSidebarMenu(manageNav, '<%= configurationURL %>');
</aui:script>