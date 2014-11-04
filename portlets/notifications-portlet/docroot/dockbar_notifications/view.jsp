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
	int newUserNotificationsCount = UserNotificationEventLocalServiceUtil.getDeliveredUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false);
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

	<li class="dockbar-user-notifications dropdown" id="<portlet:namespace />userNotifications">
		<a class="dropdown-toggle user-notification-link" href="javascript:;">
			<span class='user-notifications-count <%= (newUserNotificationsCount > 0) ? "alert" : StringPool.BLANK %>' id="<portlet:namespace />userNotificationsCount"><%= unreadUserNotificationsCount %></span>
		</a>

		<div class="dockbar-user-notifications-container">
			<ul class="dropdown-menu pull-right user-notifications-list">
				<%@ include file="/dockbar_notifications/non_actionable_notifications.jspf" %>

				<%@ include file="/dockbar_notifications/actionable_notifications.jspf" %>
			</ul>
		</div>
	</li>

	<aui:script use="aui-base,liferay-plugin-dockbar-notifications,liferay-plugin-notifications-list">
		var nonActionableNotificationsList = new Liferay.NotificationsList(
			{
				actionable: <%= false %>,
				baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
				baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
				baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
				delta: <%= dockbarViewDelta %>,
				fullView: <%= false %>,
				markAllAsReadNode: '.mark-all-as-read',
				namespace: '<portlet:namespace />',
				notificationsContainer: '.dockbar-user-notifications .dockbar-user-notifications-container .user-notifications-list .non-actionable',
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
				nonActionableNotificationsList: nonActionableNotificationsList,
				portletKey: '<%= portletDisplay.getId() %>'
			}
		);
	</aui:script>
</c:if>