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
	int newUserNotificationsCount = UserNotificationEventLocalServiceUtil.getDeliveredUserNotificationEventsCount(themeDisplay.getUserId(), false);
	int unreadUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), false);

	long notificationsPlid = themeDisplay.getPlid();

	if (layout.isTypeControlPanel()) {
		notificationsPlid = LayoutLocalServiceUtil.getDefaultPlid(user.getGroupId(), true);

		if (notificationsPlid == LayoutConstants.DEFAULT_PLID) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.GUEST);

			notificationsPlid = LayoutLocalServiceUtil.getDefaultPlid(guestGroup.getGroupId(), false);
		}
	}
	%>

	<li class="dockbar-user-notifications dropdown toggle-controls" id="<portlet:namespace />userNotifications">
		<a class="dropdown-toggle user-notification-link" href="javascript:;">
			<span class='user-notifications-count <%= (newUserNotificationsCount > 0) ? "alert" : StringPool.BLANK %>' id="<portlet:namespace />userNotificationsCount"><%= unreadUserNotificationsCount %></span>
		</a>

		<div class="dockbar-user-notifications-container">
			<ul class="dropdown-menu pull-right user-notifications-list">
				<div class="non-actionable">
					<div class="user-notifications-header">

						<liferay-portlet:renderURL plid="<%= notificationsPlid %>" portletName="<%= PortletKeys.NOTIFICATIONS %>" var="viewAllNonActionableNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/notifications/view.jsp" />
							<portlet:param name="actionable" value="<%= Boolean.FALSE.toString() %>" />
						</liferay-portlet:renderURL>

						<span><a href="<%= viewAllNonActionableNotifications %>"><liferay-ui:message key="notifications" /> (<span class="count"></span>)</a></span>

						<span class="mark-all-as-read"><a class="hide" href="javascript:;"><liferay-ui:message key="mark-as-read" /></a></span>
					</div>

					<div class="user-notifications"></div>
				</div>

				<div class="actionable">
					<div class="clearfix user-notifications-header">
						<liferay-portlet:renderURL plid="<%= notificationsPlid %>" portletName="<%= PortletKeys.NOTIFICATIONS %>" var="viewAllActionableNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/notifications/view.jsp" />
							<portlet:param name="actionable" value="<%= Boolean.TRUE.toString() %>" />
						</liferay-portlet:renderURL>

						<span class="title"><a href="<%= viewAllActionableNotifications %>"><liferay-ui:message key="requests" /> (<span class="count"></span>)</a></span>
					</div>

					<div class="user-notifications"></div>
				</div>
			</ul>
		</div>

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
	</li>
</c:if>