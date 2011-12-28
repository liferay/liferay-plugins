<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/notifications/view_dockbar_notifications.jsp" servletContext="<%= application %>" />

<aui:script use="liferay-dockbar">
	Liferay.once(
		'initDockbar',
		function() {
			var notificationsMenuVars = {
				container: A.one('#<portlet:namespace />notificationsMenuContainer'),
				contentBox: A.one('#<portlet:namespace />notificationsMenuContent'),
				trigger: A.one('#_145_notificationsMenu')
			};

			Liferay.Dockbar.addMenu(
				{
					align: {
						node: notificationsMenuVars.trigger,
							points: ['tr', 'br']
					},
					boundingBox: notificationsMenuVars.container,
					name: 'notificationsMenu',
					trigger: notificationsMenuVars.trigger,
					width: '300px'
				}
			);

			var notificationsMenuItems = notificationsMenuVars.container.all('.user-notification-event-content');

			notificationsMenuItems.on(
				['mouseover', 'mouseout'],
				function(event) {
					event.currentTarget.toggleClass('aui-focus');
				}
			);
		}
	);
</aui:script>