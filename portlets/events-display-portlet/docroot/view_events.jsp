<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String searchContainerName = ParamUtil.getString(request, "searchContainerName");

List<CalendarBooking> calendarBookings = (List<CalendarBooking>)request.getAttribute("view.jsp-calendarBookings");
%>

<liferay-ui:search-container
	delta="<%= eventsPerPage %>"
	total="<%= calendarBookings.size() %>"
>

	<liferay-ui:search-container-results
		results="<%= calendarBookings.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.calendar.model.CalendarBooking"
		keyProperty="calendarBookingId"
		modelVar="calendarBooking"
	>
		<liferay-ui:search-container-column-text
			name="<%= searchContainerName %>"
		>

			<%
			Group group = GroupLocalServiceUtil.getGroup(calendarBooking.getGroupId());

			LiferayPortletURL groupURL = PortletURLFactoryUtil.create(request, MySitesPortletKeys.MY_SITES, layout.getPlid(), PortletRequest.ACTION_PHASE);

			groupURL.setWindowState(LiferayWindowState.NORMAL);

			groupURL.setParameter("struts_action", "/my_sites/view");
			groupURL.setParameter("groupId", String.valueOf(group.getGroupId()));

			if (group.hasPublicLayouts()) {
				groupURL.setParameter("privateLayout", "0");
			}
			else {
				groupURL.setParameter("privateLayout", "1");
			}

			String eventHREF = groupURL.toString();

			long selPlid = PortalUtil.getPlidFromPortletId(calendarBooking.getGroupId(), "1_WAR_calendarportlet");

			if (selPlid != LayoutConstants.DEFAULT_PLID) {
				LiferayPortletURL eventURL = PortletURLFactoryUtil.create(request, "1_WAR_calendarportlet", selPlid, PortletRequest.RENDER_PHASE);

				eventURL.setWindowState(LiferayWindowState.NORMAL);
				eventURL.setPortletMode(PortletMode.VIEW);

				eventURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
				eventURL.setParameter("redirect", PortalUtil.getCurrentURL(request));
				eventURL.setParameter("calendarBookingId", String.valueOf(calendarBooking.getCalendarBookingId()));

				eventHREF = eventURL.toString();
			}
			%>

			<div class="event">
				<span class="event-name">
					<a href="<%= eventHREF %>"><%= StringUtil.shorten(HtmlUtil.escape(calendarBooking.getTitle(locale)), 40) %></a>
				</span>

				<c:if test="<%= !calendarBooking.isAllDay() %>">
					<span class="event-time">
						<%= dateFormatTime.format(calendarBooking.getStartTime()) %>
					</span>
				</c:if>

				<c:if test="<%= group.isUser() %>">
					<span class="event-site">
						<a href="<%= groupURL.toString() %>"><%= group.getDescriptiveName(locale) %></a>
					</span>
				</c:if>
			</div>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator type="article" />
</liferay-ui:search-container>