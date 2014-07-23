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
boolean fullView = ParamUtil.getBoolean(request, "fullView", true);
int start = ParamUtil.getInteger(request, "start", 0);
int end = ParamUtil.getInteger(request, "end", fullView ? fullViewDelta : dockbarViewDelta);

List<UserNotificationEvent> userNotificationEvents = null;

int userNotificationEventsCount = 0;

int unreadActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false);
int unreadNonActionableUserNotificationsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false);

List<Long> userNotificationEventIds = new ArrayList<Long>();

if (filter.equals("dockbar")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false);
}
else if (filter.equals("unread-actionable")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false, start, end);
	userNotificationEventsCount = unreadActionableUserNotificationsCount;
}
else if (filter.equals("unread-non-actionable")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false, start, end);
	userNotificationEventsCount = unreadNonActionableUserNotificationsCount;
}
else {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE);
}
%>

<li class='message<%= userNotificationEvents.isEmpty() ? "" : " hide" %>'>
	<c:choose>
		<c:when test='<%= filter.equals("dockbar") || filter.equals("unread-actionable") || filter.equals("unread-non-actionable") %>'>
			<a><liferay-ui:message key="you-do-not-have-any-unread-notifications" /></a>
		</c:when>
		<c:otherwise>
			<a><liferay-ui:message key="you-do-not-have-any-notifications" /></a>
		</c:otherwise>
	</c:choose>
</li>

<c:if test="<%= fullView && (userNotificationEventsCount > fullViewDelta) %>">
	<li class="clearfix message top">
		<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
		<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
		<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
	</li>
</c:if>

<c:choose>
	<c:when test='<%= filter.equals("dockbar") %>'>
		<div class="dockbar-user-notifications-list">

			<%
			List<UserNotificationEvent> actionableUserNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false, start, end);
			%>

			<c:if test="<%= unreadActionableUserNotificationsCount > 0 %>">

				<%
				for (UserNotificationEvent userNotificationEvent : actionableUserNotificationEvents) {
				%>

					<%@ include file="/notifications/notification_entry.jspf" %>

				<%
				}
				%>

			</c:if>

			<c:if test="<%= unreadActionableUserNotificationsCount <= dockbarViewDelta %>">

				<%
				List<UserNotificationEvent> nonActionableUserNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false, start, (end - unreadActionableUserNotificationsCount));
				%>

				<c:if test="<%= !nonActionableUserNotificationEvents.isEmpty() %>">
					<c:if test="<%= unreadActionableUserNotificationsCount > 0 %>">
						<hr class="separator">
					</c:if>

					<div class="non-actionable-user-notifications-list">

						<%
						for (UserNotificationEvent userNotificationEvent : nonActionableUserNotificationEvents) {
							userNotificationEventIds.add(userNotificationEvent.getUserNotificationEventId());
						%>

							<%@ include file="/notifications/notification_entry.jspf" %>

						<%
						}
						%>

					</div>
				</c:if>
			</c:if>

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

		</div>
		<li class="message">
			<div class="dockbarMarkAllAsRead"></div>
		</li>
		<li class="bottom message">
			<liferay-portlet:renderURL plid="<%= notificationsPlid %>" portletName="<%= PortletKeys.NOTIFICATIONS %>" var="viewAllNotifications" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/notifications/view.jsp" />
			</liferay-portlet:renderURL>

			<a href="<%= viewAllNotifications %>"><liferay-ui:message key="view-all-notifications" /></a>
		</li>
	</c:when>
	<c:when test='<%= filter.equals("unread-actionable") %>'>
		<c:if test="<%= userNotificationEventsCount > 0 %>">

			<%
			for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
			%>

				<%@ include file="/notifications/notification_entry.jspf" %>

			<%
			}
			%>

			<li class="clearfix message">
				<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
				<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
				<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
			</li>
		</c:if>
	</c:when>
	<c:when test='<%= filter.equals("unread-non-actionable") %>'>
		<c:if test="<%= userNotificationEventsCount > 0 %>">
			<div class="fullViewMarkAllAsRead"></div>

			<div class="non-actionable-user-notifications-list">

				<%
				for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
					userNotificationEventIds.add(userNotificationEvent.getUserNotificationEventId());
				%>

					<%@ include file="/notifications/notification_entry.jspf" %>

				<%
				}
				%>

			</div>

			<li class="clearfix message">
				<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
				<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
				<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
			</li>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="<%= userNotificationEventsCount > 0 %>">

			<%
			for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
			%>

				<%@ include file="/notifications/notification_entry.jspf" %>

			<%
			}
			%>

			<li class="clearfix message">
				<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
				<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
				<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
			</li>
		</c:if>
	</c:otherwise>
</c:choose>

<aui:script use="aui-base">
	Liferay.Notifications.init(
		{
			baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
			baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
			baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
			currentPageNotificationEventsCount: <%= userNotificationEventIds.size() %>,
			delta: <%= fullViewDelta %>,
			dockbarViewDelta: '<%= dockbarViewDelta %>',
			end: <%= end %>,
			filter: '<%= HtmlUtil.escape(filter) %>',
			namespace: '<portlet:namespace />',
			start: <%= start %>,
			unreadActionableUserNotificationsCount: <%= unreadActionableUserNotificationsCount %>,
			unreadNonActionableUserNotificationsCount: <%= unreadNonActionableUserNotificationsCount %>,
			userNotificationEventsCount: <%= userNotificationEventsCount %>,
			userNotificationEventIds: '<%= StringUtil.merge(userNotificationEventIds) %>'
		}
	);
</aui:script>