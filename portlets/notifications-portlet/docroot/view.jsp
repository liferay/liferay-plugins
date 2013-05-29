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

<li class="dropdown toggle-controls" id="userNotifications">
	<a class="dropdown-toggle user-notification-link" href="javascript:;">
		<i class="icon-white icon-globe"></i>

		<span class="new-user-notifications-count hide" id="newUserNotificationsCount"></span>
	</a>

	<ul class="dropdown-menu user-notifications-container"></ul>

	<aui:script use="aui-base,aui-io-plugin-deprecated,liferay-poller">
			var userNotifications = A.one('#userNotifications');

			var newNotificationsCount = userNotifications.one('#newUserNotificationsCount');

			var onPollerUpdate = function(response, chunkId) {
				var count = Number(response.count);

				if (newNotificationsCount) {
					if (count > 0) {
						newNotificationsCount.removeClass("hide");
						newNotificationsCount.setHTML(count);
					}
					else {
						newNotificationsCount.addClass("hide");
						newNotificationsCount.setHTML(count);
					}
				}
			}

			Liferay.Poller.addListener('1_WAR_notificationsportlet', onPollerUpdate, this);

			userNotifications.delegate(
				'click',
				function(event) {
					event.preventDefault();

					var uri = event.currentTarget.get('href');

					var markAsReadURL = event.currentTarget.getAttribute('data-markAsReadURL');

					var userNotification = event.currentTarget.ancestor('.user-notification');

					if (markAsReadURL) {
						A.io.request(
							markAsReadURL,
							{
								dataType: 'json',
								after: {
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										if (responseData.success) {
											userNotification.removeClass ('unread');
										}
									}
								}
							}
						);
					}

					if (uri != "") {
						userNotifications.toggleClass('open')

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

			userNotifications.on(
				'click',
				function(event) {
					if (!event.target.ancestor('.user-notification')) {
						event.currentTarget.toggleClass('open');

						if (event.currentTarget.hasClass('open')) {
							var userNotificationsContainer = userNotifications.one('.user-notifications-container');

							if (!userNotificationsContainer.io) {
								userNotificationsContainer.plug(
									A.Plugin.IO,
									{
										autoLoad: false,
										method: 'POST'
									}
								);
							}

							userNotificationsContainer.io.set('uri', '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/view_entries.jsp" /></portlet:renderURL>');

							userNotificationsContainer.io.start();

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