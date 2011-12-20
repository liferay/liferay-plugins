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

Calendar tempCal = (Calendar)cal.clone();

for (int i = 0; i < maxDaysDisplayed; i++) {
	for (Group curGroup : groups) {
		events.addAll(CalEventLocalServiceUtil.getEvents(curGroup.getGroupId(), tempCal));
	}

	tempCal.add(Calendar.DATE, 1);
}

if (events.size() > 1) {
	ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

	Class<?> classObj = classLoader.loadClass("com.liferay.portlet.calendar.util.comparator.EventTimeComparator");

	Constructor<?> constructor = classObj.getConstructor(TimeZone.class, Locale.class);

	ListUtil.sort(events, (Comparator)constructor.newInstance(timeZone, locale));
}

List<CalEvent> todayEvents = new ArrayList<CalEvent>();
List<CalEvent> upcomingEvents = new ArrayList<CalEvent>();

for (CalEvent event : events) {
	Date endDate = new Date(event.getStartDate().getTime() + (Time.HOUR * event.getDurationHour()) + (Time.MINUTE * event.getDurationMinute()));

	if (event.isTimeZoneSensitive()) {
		endDate = Time.getDate(endDate, timeZone);
	}

	if (endDate.compareTo(cal.getTime()) < 0) {
		continue;
	}

	if (endDate.getDate() == cal.get(Calendar.DAY_OF_MONTH)) {
		todayEvents.add(event);
	}
	else {
		upcomingEvents.add(event);
	}
}
%>

<c:choose>
	<c:when test="<%= todayEvents.isEmpty() && upcomingEvents.isEmpty() %>">
		<liferay-ui:message key="there-are-no-more-events-today" />
	</c:when>
	<c:otherwise>
		<c:if test="<%= !todayEvents.isEmpty() %>">
			<h2><liferay-ui:message key="todays-events" /></h2>

			<div class="today-events">

				<%
				request.setAttribute("view.jsp-events", todayEvents);
				%>

				<liferay-util:include page="/view_events.jsp" servletContext="<%= application %>" />
			</div>
		</c:if>

		<c:if test="<%= !upcomingEvents.isEmpty() %>">
			<h2><liferay-ui:message key="upcoming-events" /></h2>

			<div class="upcoming-events">

				<%
				request.setAttribute("view.jsp-events", upcomingEvents);
				%>

				<liferay-util:include page="/view_events.jsp" servletContext="<%= application %>" />
			</div>
		</c:if>
	</c:otherwise>
</c:choose>