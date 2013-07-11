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

<%
String filter = ParamUtil.getString(request, "filter");
boolean fullView = ParamUtil.getBoolean(request, "fullView", true);
int start = ParamUtil.getInteger(request, "start", 0);
int end = ParamUtil.getInteger(request, "end", (delta - 1));

List<UserNotificationEvent> userNotificationEvents = null;
int userNotificationEventsCount = 0;

if (filter.equals("unread")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), false, start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), false);
}
else {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getUserNotificationEvents(themeDisplay.getUserId(), start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getUserNotificationEventsCount(themeDisplay.getUserId());
}
%>

<ul class="user-notifications-list">
	<c:choose>
		<c:when test="<%= userNotificationEvents.isEmpty() %>">
			<li class="message user-notification">
				<c:choose>
					<c:when test='<%= filter.equals("unread") %>'>
						<a><liferay-ui:message key="you-do-not-have-any-unread-notifications" /></a>
					</c:when>
					<c:otherwise>
						<a><liferay-ui:message key="you-do-not-have-any-notifications" /></a>
					</c:otherwise>
				</c:choose>
			</li>
		</c:when>
		<c:when test="<%= (userNotificationEventsCount > delta) && fullView %>">
			<li class="clearfix message top">
				<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
				<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), (end + 1), userNotificationEventsCount} %>" key="showing-x-x-of-x-results" /></span>
				<span class="right-nav <%= (userNotificationEventsCount - 1) <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
			</li>
		</c:when>
	</c:choose>

	<%
	for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
		UserNotificationFeedEntry userNotificationFeedEntry = UserNotificationManagerUtil.interpret(StringPool.BLANK, userNotificationEvent, ServiceContextFactory.getInstance(request));

		JSONObject userNotificationEventJSONObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());

		long userId = userNotificationEventJSONObject.getLong("userId");

		String userFullName = HtmlUtil.escape(PortalUtil.getUserName(userId, StringPool.BLANK));

		String userPortaitURL = StringPool.BLANK;

		User curUser = UserLocalServiceUtil.fetchUserById(userId);

		if (curUser != null) {
			userPortaitURL = curUser.getPortraitURL(themeDisplay);
		}

		boolean read = userNotificationEvent.isArchived();
	%>

		<li class="user-notification<%= read ? "" : " unread" %>">
			<c:choose>
				<c:when test="<%= read %>">
					<a class="clearfix user-notification-link" href="<%= userNotificationFeedEntry.getLink() %>">
				</c:when>
				<c:otherwise>
					<liferay-portlet:actionURL name="markAsRead" var="markAsReadURL"><portlet:param name="userNotificationEventId" value="<%= String.valueOf(userNotificationEvent.getUserNotificationEventId()) %>" /></liferay-portlet:actionURL>

					<a class="clearfix user-notification-link" data-markAsReadURL="<%= markAsReadURL %>" href="<%= userNotificationFeedEntry.getLink() %>">
				</c:otherwise>
			</c:choose>

				<div class="sender">
					<span class="user-thumbnail">
						<img alt="<%= userFullName %>" src="<%= userPortaitURL %>" />
					</span>
				</div>

				<div class="content">
					<div class="body">
						<%= userNotificationFeedEntry.getBody() %>
					</div>

					<div class="timestamp">
						<span class="portlet-icon">
							<liferay-portlet:icon-portlet
								portlet="<%= PortletLocalServiceUtil.getPortletById(company.getCompanyId(), userNotificationEvent.getType()) %>"
							/>
						</span>

						<%= simpleDateFormat.format(userNotificationEvent.getTimestamp()) %>
					</div>

					<div class="read">
						<liferay-ui:message key='<%= read ? "read" : "unread" %>' />
					</div>
				</div>
			</a>
		</li>

	<%
	}
	%>

	<c:if test="<%= !userNotificationEvents.isEmpty() && fullView %>">
		<li class="clearfix message">
			<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
			<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), (end + 1), userNotificationEventsCount} %>" key="showing-x-x-of-x-results" /></span>
			<span class="right-nav <%= (userNotificationEventsCount - 1) <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
		</li>
	</c:if>

	<c:if test="<%= !fullView %>">
		<li class="bottom message user-notification">
			<liferay-portlet:renderURL portletName="<%= PortletKeys.NOTIFICATIONS %>" var="viewAllNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/notifications/view.jsp" />
			</liferay-portlet:renderURL>

			<a href="<%= viewAllNotifications %>"><liferay-ui:message key="view-all-notifications" /></a></span>
		</li>
	</c:if>
</ul>

<aui:script use="aui-base,aui-io-plugin-deprecated">
	<c:if test='<%= filter.equals("unread") %>'>
		var unreadCount = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %> .user-notifications-sidebar .unread .count');

		if (unreadCount) {
			unreadCount.setHTML('<%= userNotificationEventsCount %>');
		}
	</c:if>

	userNotificationsList = A.one('.user-notifications-list');

	userNotificationsList.delegate(
		'click',
		function(event) {
			event.preventDefault();

			var currentTarget = event.currentTarget;

			var markAsReadURL = currentTarget.attr('data-markAsReadURL');

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

										var read = userNotification.one('.content .read');
										read.setHTML(Liferay.Language.get('read'));
									}
								}
							}
						},
						dataType: 'json'
					}
				);
			}

			var uri = currentTarget.attr('href');

			if (uri) {
				Liferay.Util.openWindow(
					{
						id: 'notificationsWindow',
						uri: uri
					}
				);
			}
		},
		'.user-notification .user-notification-link'
	);

	userNotificationsList.delegate(
		'click',
		function(event) {
			event.preventDefault();

			var userNotificationsList = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %> .user-notifications-list-container');

			if (userNotificationsList) {
				if (!userNotificationsList.io) {
					userNotificationsList.plug(
						A.Plugin.IO,
						{
						autoLoad: false
						}
					);
				}

				<portlet:renderURL var="nextURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
					<portlet:param name="filter" value="<%= filter %>" />
					<portlet:param name="fullView" value="<%= String.valueOf(fullView) %>" />
					<portlet:param name="start" value="<%= String.valueOf(start + delta) %>" />
					<portlet:param name="end" value="<%= String.valueOf(end + delta) %>" />
				</portlet:renderURL>

				userNotificationsList.io.set('uri', '<%= nextURL %>');
				userNotificationsList.io.start();
			}
		},
		'.message .next a'
	);

	userNotificationsList.delegate(
		'click',
		function(event) {
			event.preventDefault();

			var userNotificationsList = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %> .user-notifications-list-container');

			if (userNotificationsList) {
				if (!userNotificationsList.io) {
					userNotificationsList.plug(
						A.Plugin.IO,
						{
						autoLoad: false
						}
					);
				}

				<portlet:renderURL var="previousURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
					<portlet:param name="filter" value="<%= filter %>" />
					<portlet:param name="fullView" value="<%= String.valueOf(fullView) %>" />
					<portlet:param name="start" value="<%= String.valueOf(start - delta) %>" />
					<portlet:param name="end" value="<%= String.valueOf(end - delta) %>" />
				</portlet:renderURL>

				userNotificationsList.io.set('uri', '<%= previousURL %>');
				userNotificationsList.io.start();
			}
		},
		'.message .previous a'
	);
</aui:script>