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

<%
String userNotificationEventUuids = StringPool.BLANK;

List<NotificationEvent> notificationEvents = ChannelHubManagerUtil.getNotificationEvents(user.getCompanyId(), user.getUserId(), true);

int notificationEventsCount = notificationEvents.size();
%>

<div class="aui-menu aui-overlaycontext-hidden user-notification-events" id="<portlet:namespace />notificationsMenuContainer">
	<div class="aui-menu-content user-notification-events-container" id="<portlet:namespace />notificationsMenuContent">

		<%
		if (!notificationEvents.isEmpty()) {
			for (NotificationEvent notificationEvent : notificationEvents) {
				if (notificationEvent.getType().equals(PortletKey.SO_NOTIFICATION)) {
					userNotificationEventUuids = StringUtil.add(userNotificationEventUuids, notificationEvent.getUuid());
				}
				else {
					notificationEventsCount--;

					continue;
				}

				JSONObject notificationEventJSON = notificationEvent.getPayload();

				String portletId = notificationEventJSON.getString("portletId");

				long userId = notificationEventJSON.getLong("userId");

				String userFullName = PortalUtil.getUserName(userId, StringPool.BLANK);

				String userDisplayURL = StringPool.BLANK;
				String userPortaitURL = StringPool.BLANK;

				User curUser = UserLocalServiceUtil.fetchUserById(userId);

				if (curUser != null) {
					userDisplayURL = curUser.getDisplayURL(themeDisplay);
					userPortaitURL = curUser.getPortraitURL(themeDisplay);
				}
			%>

				<c:choose>
					<c:when test='<%= portletId.equals("<%= PortletKeys.SO_INVITE_MEMBERS %>") %>'>
						<%@ include file="/notifications/view_member_request.jspf" %>
					</c:when>
					<c:when test='<%= portletId.equals("1_WAR_contactsportlet") %>'>
						<%@ include file="/notifications/view_social_request.jspf" %>
					</c:when>
					<c:otherwise>
						<%@ include file="/notifications/view_notification.jspf" %>
					</c:otherwise>
				</c:choose>

		<%
			}
		}
		%>

		<c:if test="<%= notificationEventsCount <= 0 %>">
			<div class="user-notification-event-header">
				<liferay-ui:message key="you-have-no-new-notifications" />
			</div>
		</c:if>

		<div class="user-notification-event-footer">
			<span class="dismiss-notifications">
				<c:if test="<%= notificationEventsCount > 0 %>">
					<a class="dismiss-notifications" href="javascript:;"><liferay-ui:message key="mark-as-read" /></a>
				</c:if>
			</span>

			<span class="view-all">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.SO_NOTIFICATION %>" var="viewAllNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="jspPage" value="/notifications/view.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<a href="<%= viewAllNotifications %>"><liferay-ui:message key="view-all" />&raquo;</a>
			</span>
		</div>
	</div>
</div>

<a class="menu-button user-notification-events-icon" href="javascript:;">
	<span class="notification-count"><%= notificationEventsCount %></span>
</a>

<aui:script use="aui-base">
	var userNotificationEvents = A.one('.dockbar .user-notification-events');
	var userNotificationsContainer = userNotificationEvents.one('.user-notification-events-container');

	<c:if test="<%= notificationEventsCount > 0 %>">
		userNotificationEvents.delegate(
			'click',
			function(event) {
				var portletURL = event.currentTarget.getAttribute('data-portletUrl');

				if (portletURL) {
					window.location = portletURL;
				}
			},
			'.user-notification-event-content'
		);

		var dismissNotifications = userNotificationEvents.one('.dismiss-notifications');

		if (dismissNotifications) {
			dismissNotifications.on(
				'click',
				function(event) {
					A.io.request(
						'<liferay-portlet:actionURL name="dismissUserNotificationEvents" portletName="<%= PortletKeys.SO_NOTIFICATION %>" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="userNotificationEventUuids" value="<%= userNotificationEventUuids %>" /></liferay-portlet:actionURL>',
						{
							after: {
								success: function(event, id, obj) {
									window.location.reload();
								}
							}
						}
					);
				}
			);
		}
	</c:if>
</aui:script>