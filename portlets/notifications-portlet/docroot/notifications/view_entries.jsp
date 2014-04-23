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

<%
String filter = ParamUtil.getString(request, "filter");
boolean dockbar = ParamUtil.getBoolean(request, "dockbar", false);
boolean fullView = ParamUtil.getBoolean(request, "fullView", true);
int start = ParamUtil.getInteger(request, "start", 0);
int end = ParamUtil.getInteger(request, "end", delta);

List<UserNotificationEvent> userNotificationEvents = null;
int userNotificationEventsCount = 0;

List<Long> userNotificationEventIds = new ArrayList<Long>();

if (filter.equals("unread")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), false, start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), false);
}
else {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getUserNotificationEvents(themeDisplay.getUserId(), start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getUserNotificationEventsCount(themeDisplay.getUserId());
}
%>

<c:choose>
	<c:when test="<%= userNotificationEvents.isEmpty() %>">
		<li class="message">
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
			<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
			<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
		</li>
	</c:when>
</c:choose>

<c:if test="<%= fullView %>">
	<div class="fullViewMarkAllAsRead"></div>
</c:if>

<%
for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
	UserNotificationFeedEntry userNotificationFeedEntry = UserNotificationManagerUtil.interpret(StringPool.BLANK, userNotificationEvent, ServiceContextFactory.getInstance(request));

	if (userNotificationFeedEntry == null) {
		continue;
	}

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
				<div class="clearfix user-notification-link" data-href="<%= userNotificationFeedEntry.getLink() %>" data-openDialog="<%= String.valueOf(userNotificationFeedEntry.isOpenDialog()) %>">
			</c:when>
			<c:otherwise>

				<%
				userNotificationEventIds.add(userNotificationEvent.getUserNotificationEventId());
				%>

				<liferay-portlet:actionURL name="markAsRead" var="markAsReadURL"><portlet:param name="userNotificationEventId" value="<%= String.valueOf(userNotificationEvent.getUserNotificationEventId()) %>" /></liferay-portlet:actionURL>

				<div class="clearfix user-notification-link" data-href="<%= userNotificationFeedEntry.getLink() %>" data-markAsReadURL="<%= markAsReadURL %>" data-openDialog="<%= String.valueOf(userNotificationFeedEntry.isOpenDialog()) %>">
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

			<c:if test='<%= !filter.equals("unread") %>'>
				<div class="read">
					<liferay-ui:message key='<%= read ? "read" : "unread" %>' />
				</div>
			</c:if>
		</div>
	</li>

<%
}
%>

<c:if test="<%= !userNotificationEvents.isEmpty() && fullView %>">
	<li class="clearfix message">
		<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
		<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
		<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
	</li>
</c:if>

<c:if test="<%= dockbar %>">

	<%
	long notificationsPlid = themeDisplay.getPlid();

	if (layout.isTypeControlPanel()) {
		notificationsPlid = LayoutLocalServiceUtil.getDefaultPlid(user.getGroupId(), true);

		if (notificationsPlid == LayoutConstants.DEFAULT_PLID) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.GUEST);

			notificationsPlid = LayoutLocalServiceUtil.getDefaultPlid(guestGroup.getGroupId(), false);
		}
	}
	%>

	<li class="bottom message">
		<liferay-portlet:renderURL plid="<%= notificationsPlid %>" portletName="<%= PortletKeys.NOTIFICATIONS %>" var="viewAllNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/notifications/view.jsp" />
		</liferay-portlet:renderURL>

		<a href="<%= viewAllNotifications %>"><liferay-ui:message key="view-all-notifications" /></a>

		<c:if test="<%= !userNotificationEventIds.isEmpty() %>">
			<div class="dropDownMarkAllAsRead"></div>
		</c:if>
	</li>
</c:if>

<aui:script use="aui-base,aui-io-plugin-deprecated">
	<c:if test='<%= filter.equals("unread") %>'>
		var unreadCount = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %> .user-notifications-sidebar .unread .count');

		if (unreadCount) {
			unreadCount.setHTML('<%= userNotificationEventsCount %>');
		}
	</c:if>

	var dockbarUserNotificationsList = A.one('.dockbar-user-notifications .user-notifications-list');

	var userNotificationsList = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %> .user-notifications-list-container .user-notifications-list');

	var createMarkAllAsReadNode = function() {
		<c:if test="<%= !userNotificationEventIds.isEmpty() && (start == 0) %>">
			<liferay-portlet:actionURL name="dismissNotifications" var="dismissNotificationsURL">
				<portlet:param name="userNotificationEventIds" value="<%= StringUtil.merge(userNotificationEventIds) %>" />
			</liferay-portlet:actionURL>

			var nodeHTML = '<a class="dismiss-notifications" href="<%= dismissNotificationsURL %>"><%= LanguageUtil.format(pageContext, "mark-all-as-read-x", String.valueOf(userNotificationEventIds.size()), false) %></a>';

			var dockbarDismiss = A.one('.dropDownMarkAllAsRead');

			if (dockbarDismiss) {
				dockbarDismiss.get('parentNode').replaceChild(A.Node.create(nodeHTML), dockbarDismiss);
			}

			var fullviewDismiss = A.one('.fullViewMarkAllAsRead');

			if (fullviewDismiss) {
				fullviewDismiss.get('parentNode').replaceChild(A.Node.create(nodeHTML), fullviewDismiss);
			}
		</c:if>
	};

	var delegateNotifications = function() {

		<c:choose>
			<c:when test="<%= fullView %>">
				getDismissDelegation(userNotificationsList, false, '.user-notification .btn-action');
				getDismissDelegation(userNotificationsList, true, '.dismiss-notifications');
			</c:when>
			<c:otherwise>
				getDismissDelegation(dockbarUserNotificationsList, false, '.user-notification .btn-action');
				getDismissDelegation(dockbarUserNotificationsList, true, '.dismiss-notifications');
			</c:otherwise>
		</c:choose>

		getPaginateDelegation(false, '.message .next a');
		getPaginateDelegation(true, '.message .previous a');

		getViewDelegation(dockbarUserNotificationsList, '.user-notification .user-notification-link');
		getViewDelegation(userNotificationsList, '.user-notification .user-notification-link');
	};

	var dismissNotifications = function(event, markAllAsRead) {
		var currentRow;

		var loadingRow = A.Node.create('<div class="loading-animation"></div>');

		if (!markAllAsRead) {
			currentRow = event.currentTarget.ancestor('.user-notification');
			currentRow.hide().placeAfter(loadingRow);
		}

		A.io.request(
			event.currentTarget.attr('href'),
			{
				after: {
					success: function(event) {
						var response = this.get('responseData');

						if (response.success) {
							getNotificationsCount(event, currentRow, loadingRow, markAllAsRead);
						}
					}
				},
				dataType: 'json'
			}
		);
	};

	var getDismissDelegation = function(notificationsList, markAllAsRead, selector) {
		if (notificationsList) {
			notificationsList.delegate(
				'click',
				function(event) {
					event.preventDefault();

					dismissNotifications(event, markAllAsRead);
				},
				selector
			);
		}
	};

	var getNotificationsCount = function(event, currentRow, loadingRow, markAllAsRead) {
		event.preventDefault();

		A.io.request(
			'<portlet:resourceURL id="notifcationsCount" />',
			{
				on: {
					success: function(event) {
						var response = this.get('responseData');

						if (response.success) {
							updateNotificationsCount(response["newUserNotificationsCount"], response["unreadUserNotificationsCount"]);

							if (!markAllAsRead) {
								loadingRow.remove();
								currentRow.remove();
							}

							<portlet:renderURL var="dockbarURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="mvcPath" value="/dockbar_notifications/view.jsp" />
								<portlet:param name="filter" value="unread" />
								<portlet:param name="dockbar" value="<%= Boolean.TRUE.toString() %>" />
								<portlet:param name="fullView" value="<%= Boolean.FALSE.toString() %>" />
								<portlet:param name="menuOpen" value="<%= Boolean.TRUE.toString() %>" />
							</portlet:renderURL>

							<portlet:renderURL var="fullviewURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
								<portlet:param name="filter" value="<%= filter %>" />
								<portlet:param name="dockbar" value="<%= Boolean.FALSE.toString() %>" />
								<portlet:param name="fullView" value="<%= Boolean.TRUE.toString() %>" />
								<portlet:param name="start" value="<%= String.valueOf(start) %>" />
								<portlet:param name="end" value="<%= String.valueOf(end) %>" />
							</portlet:renderURL>

							<c:choose>
								<c:when test="<%= fullView %>">
									Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= fullviewURL %>');
								</c:when>
								<c:otherwise>
									if (userNotificationsList) {
										Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= fullviewURL %>');
									}

									Liferay.Notifications.renderNotificationsList(dockbarUserNotificationsList, '<%= dockbarURL %>');
								</c:otherwise>
							</c:choose>
						}
					}
				},
				dataType: 'json'
			}
		);
	};

	var getPaginateDelegation = function(previous, selector) {
		if (userNotificationsList) {
			userNotificationsList.delegate(
				'click',
				function(event) {
					event.preventDefault();

					paginateNotifications(previous);
				},
				selector
			);
		}
	};

	var getViewDelegation = function(notificationsList, selector) {
		if (notificationsList) {
			notificationsList.delegate(
				'click',
				function(event) {
					Liferay.Notifications.viewNotification(event);
				},
				selector
			);
		}
	}

	var paginateNotifications = function(previous) {
		if (userNotificationsList) {
			<portlet:renderURL var="nextURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
				<portlet:param name="filter" value="<%= filter %>" />
				<portlet:param name="fullView" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="start" value="<%= String.valueOf(start + delta) %>" />
				<portlet:param name="end" value="<%= String.valueOf(end + delta) %>" />
			</portlet:renderURL>

			<portlet:renderURL var="previousURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/notifications/view_entries.jsp" />
				<portlet:param name="filter" value="<%= filter %>" />
				<portlet:param name="fullView" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="start" value="<%= String.valueOf(start - delta) %>" />
				<portlet:param name="end" value="<%= String.valueOf(end - delta) %>" />
			</portlet:renderURL>

			if (previous) {
				Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= previousURL %>');
			}
			else {
				Liferay.Notifications.renderNotificationsList(userNotificationsList, '<%= nextURL %>');
			}
		}
	}

	var updateNotificationsCount = function(newUserNotificationsCount, unreadUserNotificationsCount) {
		var dockbarUserNotifications = A.one('.dockbar-user-notifications');

		if (dockbarUserNotifications) {
			var dockbarUserNotificationsCount = dockbarUserNotifications.one('.user-notifications-count');

			if (dockbarUserNotificationsCount) {
				dockbarUserNotificationsCount.toggleClass('alert', (newUserNotificationsCount > 0));

				dockbarUserNotificationsCount.setHTML(unreadUserNotificationsCount);
			}
		}

		var sidebarUserNotifications = A.one('.user-notifications-sidebar');

		if (sidebarUserNotifications) {
			var sidebarUnreadCount = sidebarUserNotifications.one('.count');

			if (sidebarUnreadCount) {
				sidebarUnreadCount.setHTML(unreadUserNotificationsCount);
			}
		}
	};

	A.on(
		'domready',
		function() {
			createMarkAllAsReadNode();

			delegateNotifications();
		}
	);
</aui:script>