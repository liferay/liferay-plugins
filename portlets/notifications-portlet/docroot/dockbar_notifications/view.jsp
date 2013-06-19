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

<li class="dockbar-user-notifications dropdown toggle-controls" id="userNotifications">
	<a class="dropdown-toggle user-notification-link" href="javascript:;">
		<i class="icon-globe icon-white"></i>

		<span class="hide user-notifications-count" id="userNotificationsCount"></span>
	</a>

	<span class="dropdown-menu"></span>

	<aui:script use="aui-base,aui-io-plugin-deprecated,liferay-poller">
		var userNotifications = A.one('#userNotifications');

		var userNotificationsCount = userNotifications.one('#userNotificationsCount');

		var onPollerUpdate = function(response, chunkId) {
			var count = Number(response.count);

			if (userNotificationsCount) {
				userNotificationsCount.toggleClass('hide', (count == 0));

				userNotificationsCount.setHTML(count);
			}
		}

		A.on(
			'domready',
			function() {
				Liferay.Poller.addListener('<%= PortletKeys.DOCKBAR_NOTIFICATIONS %>', onPollerUpdate, this);
			}
		);

		var userNotificationsList = userNotifications.one('.dropdown-menu');

		if (!userNotificationsList.io) {
			userNotificationsList.plug(
				A.Plugin.IO,
				{
					autoLoad: false
				}
			);
		}

		userNotifications.on(
			'click',
			function(event) {
				var currentTarget = event.currentTarget;

				var target = event.target;

				var handle = Liferay.Data['userNotificationsHandle'];

				if (!target.hasClass('user-notification') && !target.ancestor('.user-notification')) {
					currentTarget.toggleClass('open');

					var menuOpen = currentTarget.hasClass('open');

					if (menuOpen && !handle) {
						handle = currentTarget.on(
							'clickoutside',
							function(event) {
								Liferay.Data['userNotificationsHandle'] = null;

								handle.detach();

								currentTarget.removeClass('open');
							}
						);
					}
					else if (handle) {
						handle.detach();

						handle = null;
					}

					Liferay.Data['userNotificationsHandle'] = handle;

					if (menuOpen) {
						<portlet:renderURL var="unreadURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
							<portlet:param name="filter" value="unread" />
							<portlet:param name="fullView" value="false" />
						</portlet:renderURL>

						userNotificationsList.io.set('uri', '<%= unreadURL %>');

						userNotificationsList.io.start();

						A.io.request('<liferay-portlet:actionURL name="setDelivered" />');
					}

				}
				else {
					Liferay.Data['userNotificationsHandle'] = null;

					handle.detach();

					currentTarget.removeClass('open');
				}
			}
		);
	</aui:script>
</li>