<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
Group group = GroupLocalServiceUtil.getGroup(layout.getGroupId());

List<Group> groups = new ArrayList<Group>();

if (group.isRegularSite()) {
	groups.add(group);
}
else if (group.isUser() && themeDisplay.isSignedIn()) {
	groups.addAll(user.getMySites());
}
else {
	groups.add(GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.GUEST));
}

List<CalEvent> events = new ArrayList<CalEvent>();

for (Group curGroup : groups) {
	events.addAll(CalEventLocalServiceUtil.getEvents(curGroup.getGroupId(), cal));
}

if (events.size() > 1) {
	ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

	Class<?> classObj = classLoader.loadClass("com.liferay.portlet.calendar.util.comparator.EventTimeComparator");

	Constructor<?> constructor = classObj.getConstructor(TimeZone.class, Locale.class);

	ListUtil.sort(events, (Comparator)constructor.newInstance(timeZone, locale));
}

List<CalEvent> allDayEvents = new ArrayList<CalEvent>();
List<CalEvent> partDayEvents = new ArrayList<CalEvent>();

for (CalEvent event : events) {
	Date endDate = new Date(event.getStartDate().getTime() + (Time.HOUR * event.getDurationHour()) + (Time.MINUTE * event.getDurationMinute()));

	if (event.isTimeZoneSensitive()) {
		endDate = Time.getDate(endDate, timeZone);
	}

	if (endDate.compareTo(cal.getTime()) < 0) {
		continue;
	}

	if (event.isAllDay()) {
		allDayEvents.add(event);
	}
	else {
		partDayEvents.add(event);
	}
}
%>

<c:choose>
	<c:when test="<%= allDayEvents.isEmpty() && partDayEvents.isEmpty() %>">
		<liferay-ui:message key="there-are-no-more-events-today" />
	</c:when>
	<c:otherwise>
		<c:if test="<%= !allDayEvents.isEmpty() %>">
			<h2><liferay-ui:message key="all-day" /></h2>

			<div class="all-day-events">

				<%
				request.setAttribute("view.jsp-events", allDayEvents);
				%>

				<liferay-util:include page="/view_events.jsp" servletContext="<%= application %>" />
			</div>
		</c:if>

		<c:if test="<%= !partDayEvents.isEmpty() %>">
			<h2><liferay-ui:message key="upcoming" /></h2>

			<div class="part-day-events">

				<%
				request.setAttribute("view.jsp-events", partDayEvents);
				%>

				<liferay-util:include page="/view_events.jsp" servletContext="<%= application %>" />
			</div>
		</c:if>
	</c:otherwise>
</c:choose>