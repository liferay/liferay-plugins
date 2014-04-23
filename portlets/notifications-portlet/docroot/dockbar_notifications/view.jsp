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

<c:if test="<%= PortletPropsValues.NOTIFICATIONS_DOCKBAR_DISPLAY_ENABLED %>">

	<%
	int newUserNotificationsCount = UserNotificationEventLocalServiceUtil.getDeliveredUserNotificationEventsCount(themeDisplay.getUserId(), false);
	int unreadUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), false);
	%>

	<li class="dockbar-user-notifications dropdown toggle-controls" id="<portlet:namespace />userNotifications">
		<a class="dropdown-toggle user-notification-link" href="javascript:;">
			<span class='user-notifications-count <%= (newUserNotificationsCount > 0) ? "alert" : StringPool.BLANK %>' id="<portlet:namespace />userNotificationsCount"><%= unreadUserNotificationsCount %></span>
		</a>

		<ul class="dropdown-menu pull-right user-notifications-list"></ul>

		<aui:script use="aui-base,aui-io-plugin-deprecated,liferay-menu-toggle,liferay-poller">
			var userNotifications = A.one('#<portlet:namespace />userNotifications');

			var userNotificationsCount = userNotifications.one('#<portlet:namespace />userNotificationsCount');

			var onPollerUpdate = function(response, chunkId) {
				var newUserNotificationsCount = Number(response.newUserNotificationsCount);
				var unreadUserNotificationsCount = Number(response.unreadUserNotificationsCount);

				userNotificationsCount.toggleClass('alert', (newUserNotificationsCount > 0));

				userNotificationsCount.setHTML(unreadUserNotificationsCount);
			};

			A.on(
				'domready',
				function() {
					Liferay.Poller.addListener('<%= PortletKeys.DOCKBAR_NOTIFICATIONS %>', onPollerUpdate, this);
				}
			);

			var userNotificationsList = userNotifications.one('.dropdown-menu');

			<portlet:renderURL var="unreadURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
				<portlet:param name="filter" value="unread" />
				<portlet:param name="dockbar" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="fullView" value="<%= Boolean.FALSE.toString() %>" />
			</portlet:renderURL>

			new Liferay.MenuToggle(
				{
					after: {
						openChange: function(event) {
							if (event.newVal) {
								Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= unreadURL %>');

								A.io.request('<liferay-portlet:actionURL name="setDelivered" />');

								userNotificationsCount.removeClass('alert');
							}
						}
					},
					content: userNotifications,
					toggleTouch: true,
					trigger: '#<portlet:namespace />userNotifications .dropdown-toggle'
				}
			);
		</aui:script>
	</li>
</c:if>