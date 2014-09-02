<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
List<NotificationEvent> notificationEvents = null;

try {
	notificationEvents = ChannelHubManagerUtil.getNotificationEvents(user.getCompanyId(), user.getUserId(), true);
}
catch (UnknownChannelException e) {
	Channel channel = ChannelHubManagerUtil.getChannel(user.getCompanyId(), user.getUserId(), true);

	notificationEvents = channel.getNotificationEvents();
}

notificationEvents = new ArrayList<NotificationEvent>(notificationEvents);

ListUtil.sort(notificationEvents, new NotificationEventComparator(false));

Iterator<NotificationEvent> iterator = notificationEvents.iterator();

while (iterator.hasNext()) {
	NotificationEvent notificationEvent = iterator.next();

	String type = notificationEvent.getType();

	if (!type.equals(PortletKeys.SO_NOTIFICATION)) {
		iterator.remove();
	}
}
%>

<div class="aui-menu aui-overlaycontext-hidden user-notification-events" id="<portlet:namespace />notificationsMenuContainer">
	<div class="aui-menu-content user-notification-events-container" id="<portlet:namespace />notificationsMenuContent">

		<%
		String userNotificationEventUuids = StringPool.BLANK;

		for (int i = 0; i < Math.min(notificationEvents.size(), PortletPropsValues.NOTIFICATIONS_DOCKBAR_MAX_ELEMENTS); i++) {
			NotificationEvent notificationEvent = notificationEvents.get(i);

			userNotificationEventUuids = StringUtil.add(userNotificationEventUuids, notificationEvent.getUuid());

			JSONObject notificationEventJSONObject = notificationEvent.getPayload();

			String portletId = notificationEventJSONObject.getString("portletId");

			long userId = notificationEventJSONObject.getLong("userId");

			String userFullName = HtmlUtil.escape(PortalUtil.getUserName(userId, StringPool.BLANK));

			String userDisplayURL = StringPool.BLANK;
			String userPortaitURL = StringPool.BLANK;

			User curUser = UserLocalServiceUtil.fetchUserById(userId);

			if (curUser != null) {
				userDisplayURL = curUser.getDisplayURL(themeDisplay);
				userPortaitURL = curUser.getPortraitURL(themeDisplay);
			}

			int daysBetween = DateUtil.getDaysBetween(new Date(notificationEvent.getTimestamp()), new Date(), timeZone);
		%>

			<c:choose>
				<c:when test="<%= portletId.equals(PortletKeys.ANNOUNCEMENTS) || portletId.equals(PortletKeys.SO_ANNOUNCEMENTS) %>">
					<%@ include file="/notifications/view_announcement.jspf" %>
				</c:when>
				<c:when test="<%= portletId.equals(PortletKeys.SO_INVITE_MEMBERS) %>">
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
		%>

		<c:if test="<%= notificationEvents.size() <= 0 %>">
			<div class="user-notification-event-header">
				<liferay-ui:message key="you-have-no-new-notifications" />
			</div>
		</c:if>

		<div class="user-notification-event-footer">
			<span class="dismiss-notifications">
				<c:if test="<%= notificationEvents.size() > 0 %>">
					<a class="dismiss-notifications" href="javascript:;"><liferay-ui:message key="mark-all-as-read" /></a>
				</c:if>
			</span>

			<span class="view-all">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.SO_NOTIFICATION %>" var="viewAllNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/notifications/view.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<a href="<%= viewAllNotifications %>"><liferay-ui:message key="view-all" />&raquo;</a>
			</span>
		</div>
	</div>
</div>

<a class="menu-button user-notification-events-icon" href="javascript:;">
	<span class="notification-count"><%= notificationEvents.size() %></span>
</a>

<aui:script use="aui-base,aui-io">
	var userNotificationEvents = A.one('#<portlet:namespace />notificationsMenuContainer');
	var userNotificationsContainer = userNotificationEvents.one('.user-notification-events-container');

	<c:if test="<%= notificationEvents.size() > 0 %>">
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