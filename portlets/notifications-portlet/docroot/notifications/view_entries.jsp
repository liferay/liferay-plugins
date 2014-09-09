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

List<UserNotificationEvent> userNotificationEvents = new ArrayList<UserNotificationEvent>();
int userNotificationEventsCount = 0;

List<Long> userNotificationEventIds = new ArrayList<Long>();

if (filter.equals("actionable")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false, start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false);
}
else if (filter.equals("non-actionable")) {
	userNotificationEvents = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEvents(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false, start, end);
	userNotificationEventsCount = UserNotificationEventLocalServiceUtil.getArchivedUserNotificationEventsCount(themeDisplay.getUserId(), UserNotificationDeliveryConstants.TYPE_WEBSITE, false, false);
}
%>

<div class="notifications-header">
	<span><a><liferay-ui:message key="notifications" /><span class="non-actionable-user-notifications-count"></span></a></span>

	<c:if test='<%= filter.equals("non-actionable") %>'>
		<span class="markAsRead"></span>
	</c:if>
</div>

<div class="user-notifications-list <%= filter.equals("actionable") ? "actionable" : "non-actionable" %>">
	<li class='message<%= userNotificationEvents.isEmpty() ? "" : " hide" %>'>
		<a><liferay-ui:message key="you-do-not-have-any-notifications" /></a>
	</li>

	<c:if test="<%= fullView && (userNotificationEventsCount > fullViewDelta) %>">
		<li class="clearfix message top">
			<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
			<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
			<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
		</li>
	</c:if>

	<%
	for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
		userNotificationEventIds.add(userNotificationEvent.getUserNotificationEventId());
	%>

		<%@ include file="/notifications/view_entry.jspf" %>

	<%
	}
	%>

	<c:if test="<%= fullView && (userNotificationEventsCount > fullViewDelta) %>">
		<li class="clearfix message">
			<span class="left-nav <%= start == 0 ? "disabled" : "previous" %>"><a href="javascript:;"><liferay-ui:message key="previous" /></a></span>
			<span><liferay-ui:message arguments="<%= new Object[] {(start + 1), end <= userNotificationEventsCount ? end : userNotificationEventsCount, userNotificationEventsCount} %>" key="showing-x-x-of-x-results" translateArguments="<%= false %>" /></span>
			<span class="right-nav <%= userNotificationEventsCount <= end ? "disabled" : "next" %>"><a href="javascript:;"><liferay-ui:message key="next" /></a></span>
		</li>
	</c:if>
</div>



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
			userNotificationEventsCount: <%= userNotificationEventsCount %>,
			userNotificationEventIds: '<%= StringUtil.merge(userNotificationEventIds) %>'
		}
	);
</aui:script>