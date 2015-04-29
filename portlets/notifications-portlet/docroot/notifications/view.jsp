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

<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="please-sign-in-to-access-this-application" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		boolean actionable = ParamUtil.getBoolean(request, "actionable");
		%>

		<aui:container cssClass='<%= "user-notifications-container " + (actionable ? "actionable" : "nonactionable") %>'>
			<aui:row>
				<aui:col cssClass="nav-bar user-notifications-sidebar" width="<%= 25 %>">
					<div class="nav">
						<a class="clearfix nonactionable <%= !actionable ? "selected" : "" %>" href="javascript:;">
							<span class="title"><liferay-ui:message key="notifications" /></span>

							<%
							int unreadNonactionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false);
							%>

							<span class="count"><%= unreadNonactionableUserNotificationsCount %></span>
						</a>
					</div>

					<div class="nav">
						<a class="actionable clearfix <%= actionable ? "selected" : "" %>" href="javascript:;">
							<span class="title"><liferay-ui:message key="requests" /></span>

							<%
							int unreadActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false);
							%>

							<span class="count"><%= unreadActionableUserNotificationsCount %></span>
						</a>
					</div>

					<div class="nav">
						<a class="clearfix manage" href="javascript:;">
							<span class="title"><liferay-ui:message key="notification-delivery" /></span>
						</a>
					</div>
				</aui:col>

				<aui:col cssClass="user-notifications-list-container" width="<%= 75 %>">
					<ul class="unstyled user-notifications-list">
						<li class="clearfix pagination top">
							<span class="hide left-nav previous"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>

							<span class="hide page-info"></span>

							<span class="hide next right-nav"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
						</li>

						<div class="mark-all-as-read"><a class="hide" href="javascript:;"><liferay-ui:message key="mark-as-read" /></a></div>

						<div class="user-notifications"></div>

						<li class="bottom clearfix pagination">
							<span class="hide left-nav previous"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>

							<span class="hide page-info"></span>

							<span class="hide next right-nav"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
						</li>
					</ul>

					<div class="hide notifications-configurations"></div>
				</aui:col>
			</aui:row>
		</aui:container>

		<aui:script use="aui-base,liferay-plugin-notifications,liferay-plugin-notifications-list">
			var notificationsCount = '.nonactionable .count';

			if (<%= actionable %>) {
				notificationsCount = '.actionable .count';
			}

			var notificationsList = new Liferay.NotificationsList(
				{
					actionable: <%= actionable %>,
					baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
					baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
					baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
					delta: <%= fullViewDelta %>,
					fullView: <%= true %>,
					markAllAsReadNode: '.user-notifications-list .mark-all-as-read',
					namespace: '<portlet:namespace />',
					nextPageNode: '.pagination .next',
					notificationsContainer: '.notifications-portlet .user-notifications-container',
					notificationsCount: notificationsCount,
					notificationsNode: '.user-notifications-list .user-notifications',
					paginationInfoNode: '.pagination .page-info',
					portletKey: '<%= portletDisplay.getId() %>',
					previousPageNode: '.pagination .previous',
					start: 0
				}
			);

			new Liferay.Notifications(
				{
					baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
					namespace: '<portlet:namespace />',
					notificationsList: notificationsList
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>