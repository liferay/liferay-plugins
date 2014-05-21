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
	<c:when test="<%= (userNotificationEventsCount > fullViewDelta) && fullView %>">
		<li class="clearfix message top">
			<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
			<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
			<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
		</li>
	</c:when>
</c:choose>

<c:if test='<%= fullView && filter.equals("unread") %>'>
	<div class="fullViewMarkAllAsRead"></div>
</c:if>

<%
List<Long> userNotificationEventIds = new ArrayList<Long>();

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

				<div class="clearfix user-notification-link" data-href="<%= userNotificationFeedEntry.getLink() %>" data-markAsReadURL="<%= markAsReadURL %>"  data-openDialog="<%= String.valueOf(userNotificationFeedEntry.isOpenDialog()) %>">
			</c:otherwise>
		</c:choose>

		<div class="sender">
			<span class="user-thumbnail">
				<img alt="<%= HtmlUtil.escapeAttribute(userFullName) %>" src="<%= userPortaitURL %>" />
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

<c:if test="<%= !fullView %>">

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
			<div class="dockbarMarkAllAsRead"></div>
		</c:if>
	</li>
</c:if>

<aui:script use="aui-base">
	Liferay.Notifications.init(
		{
			baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
			baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
			baseResourceURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE) %>',
			currentPageNotificationEventsCount: <%= userNotificationEventIds.size() %>,
			delta: <%= fullViewDelta %>,
			end: <%= end %>,
			filter: '<%= HtmlUtil.escape(filter) %>',
			start: <%= start %>,
			userNotificationEventsCount: <%= userNotificationEventsCount %>,
			userNotificationEventIds: '<%= StringUtil.merge(userNotificationEventIds) %>'
		}
	);
</aui:script>