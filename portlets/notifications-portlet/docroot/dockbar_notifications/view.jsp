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

<li class="dropdown toggle-controls user-notifications" id="userNotifications">
	<a class="dropdown-toggle user-notification-link" href="javascript:;">
		<i class="icon-globe icon-white"></i>

		<span class="hide user-notifications-count" id="userNotificationsCount"></span>
	</a>

	<ul class="dropdown-menu user-notifications-list"></ul>

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
				Liferay.Poller.addListener('<%= portletDisplay.getId() %>', onPollerUpdate, this);
			}
		);

		userNotifications.delegate(
			'click',
			function(event) {
				event.preventDefault();

				var currentTarget = event.currentTarget;

				var markAsReadURL = currentTarget.attr('data-markAsReadURL');
				var uri = currentTarget.attr('href');

				if (markAsReadURL) {
					A.io.request(
						markAsReadURL,
						{
							after: {
								success: function(event, id, obj) {
									var responseData = this.get('responseData');

									if (responseData.success) {
										var userNotification = currentTarget.ancestor('.user-notification');

										if (userNotification) {
											userNotification.removeClass('unread');
										}
									}
								}
							},
							dataType: 'json'
						}
					);
				}

				if (uri) {
					userNotifications.toggleClass('open');

					Liferay.Util.openWindow(
						{
							id: 'notificationsWindow',
							uri: uri
						}
					);
				}
			},
			'.user-notification a'
		);

		var userNotificationsList = userNotifications.one('.user-notifications-list');

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
				var target = event.target;

				if (!target.hasClass('user-notification') && !target.ancestor('.user-notification')) {
					var currentTarget = event.currentTarget;

					currentTarget.toggleClass('open');

					if (currentTarget.hasClass('open')) {
						userNotificationsList.io.set('uri', '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/dockbar_notifications/view_entries.jsp" /></portlet:renderURL>');

						userNotificationsList.io.start();

						A.io.request('<liferay-portlet:actionURL name="setDelivered" />');
					}

				}
				else {
					event.halt();
				}
			}
		);
	</aui:script>
</li>