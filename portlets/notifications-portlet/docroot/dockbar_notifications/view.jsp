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

<c:if test="<%= PortletPropsValues.USER_NOTIFICATIONS_DOCKBAR_DISPLAY_ENABLED %>">

	<%
	int newActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getDeliveredUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, true);
	int newNonactionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getDeliveredUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false);
	int newUserNotificationsCount = UserNotificationEventLocalServiceUtil.getDeliveredUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false);
	int unreadActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false);
	int unreadNonactionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false);
	int unreadUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

	long notificationsPlid = themeDisplay.getPlid();

	if (layout.isTypeControlPanel()) {
		notificationsPlid = LayoutLocalServiceUtil.getDefaultPlid(user.getGroupId(), true);

		if (notificationsPlid == LayoutConstants.DEFAULT_PLID) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.GUEST);

			notificationsPlid = LayoutLocalServiceUtil.getDefaultPlid(guestGroup.getGroupId(), false);
		}
	}
	%>

	<c:choose>
		<c:when test="<%= PortletPropsValues.USER_NOTIFICATION_DOCKBAR_SPLIT %>">
			<li class="actionable-container dockbar-user-notifications dropdown split toggle-controls" id="<portlet:namespace />actionableUserNotifications">
				<a class="dropdown-toggle user-notification-link" href="javascript:;">

					<%
					String actionablableCssClass = StringPool.BLANK;

					if (unreadActionableUserNotificationsCount == 0) {
						actionablableCssClass += "hide";
					}

					if (newActionableUserNotificationsCount > 0) {
						actionablableCssClass += StringPool.SPACE;
						actionablableCssClass += "alert";
					}
					%>

					<span class="actionable-user-notifications-count <%= actionablableCssClass %>" id="<portlet:namespace />actionableUserNotificationsCount"><%= unreadActionableUserNotificationsCount %></span>
				</a>

				<div class="dockbar-user-notifications-container">
					<ul class="dropdown-menu pull-right user-notifications-list">
						<%@ include file="/dockbar_notifications/actionable_notifications.jspf" %>
					</ul>
				</div>
			</li>

			<li class="dockbar-user-notifications dropdown nonactionable-container split toggle-controls" id="<portlet:namespace />nonactionableUserNotifications">
				<a class="dropdown-toggle user-notification-link" href="javascript:;">

					<%
					String nonActionablableCssClass = StringPool.BLANK;

					if (unreadNonactionableUserNotificationsCount == 0) {
						nonActionablableCssClass += "hide";
					}

					if (newNonactionableUserNotificationsCount > 0) {
						nonActionablableCssClass += StringPool.SPACE;
						nonActionablableCssClass += "alert";
					}
					%>

					<span class="nonactionable-user-notifications-count <%= nonActionablableCssClass %>" id="<portlet:namespace />nonactionableUserNotificationsCount"><%= unreadNonactionableUserNotificationsCount %></span>
				</a>

				<div class="dockbar-user-notifications-container">
					<ul class="dropdown-menu pull-right user-notifications-list">
						<%@ include file="/dockbar_notifications/nonactionable_notifications.jspf" %>
					</ul>
				</div>
			</li>
		</c:when>
		<c:otherwise>
			<li class="actionable-container dockbar-user-notifications dropdown nonactionable-container toggle-controls" id="<portlet:namespace />userNotifications">
				<a class="dropdown-toggle user-notification-link" href="javascript:;">
					<span class='user-notifications-count <%= (newUserNotificationsCount > 0) ? "alert" : StringPool.BLANK %>' id="<portlet:namespace />userNotificationsCount"><%= unreadUserNotificationsCount %></span>
				</a>

				<div class="dockbar-user-notifications-container">
					<ul class="dropdown-menu pull-right user-notifications-list">
						<%@ include file="/dockbar_notifications/nonactionable_notifications.jspf" %>

						<%@ include file="/dockbar_notifications/actionable_notifications.jspf" %>
					</ul>
				</div>
			</li>
		</c:otherwise>
	</c:choose>

	<aui:script use="aui-base,liferay-plugin-dockbar-notifications,liferay-plugin-notifications-list">
		var nonactionableNotificationsList = new Liferay.NotificationsList(
			{
				actionable: <%= false %>,
				baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
				baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
				baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
				delta: <%= dockbarViewDelta %>,
				fullView: <%= false %>,
				markAllAsReadNode: '.mark-all-as-read',
				namespace: '<portlet:namespace />',
				notificationsContainer: '.dockbar-user-notifications .dockbar-user-notifications-container .user-notifications-list .nonactionable',
				notificationsCount: '.count',
				notificationsNode: '.user-notifications',
				portletKey: '<%= portletDisplay.getId() %>',
				start: 0
			}
		);

		var actionableNotificationsList = new Liferay.NotificationsList(
			{
				actionable: <%= true %>,
				baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
				baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
				baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
				delta: <%= dockbarViewDelta %>,
				fullView: <%= false %>,
				namespace: '<portlet:namespace />',
				notificationsContainer: '.dockbar-user-notifications .dockbar-user-notifications-container .user-notifications-list .actionable',
				notificationsCount: '.count',
				notificationsNode: '.user-notifications',
				portletKey: '<%= portletDisplay.getId() %>',
				start: 0
			}
		);

		new Liferay.DockbarNotifications(
			{
				actionableNotificationsList: actionableNotificationsList,
				baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
				baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
				nonactionableNotificationsList: nonactionableNotificationsList,
				portletKey: '<%= portletDisplay.getId() %>'
			}
		);
	</aui:script>
</c:if>