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
List<CalEvent> events = (List<CalEvent>)request.getAttribute("view.jsp-events");
%>

<liferay-ui:search-container
	delta="<%= events.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= events %>"
		total="<%= events.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.calendar.model.CalEvent"
		keyProperty="eventId"
		modelVar="event"
	>
		<liferay-ui:search-container-column-text>

			<%
			Group group = GroupLocalServiceUtil.getGroup(event.getGroupId());

			LiferayPortletURL groupURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_SITES, layout.getPlid(), PortletRequest.ACTION_PHASE);

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

			long selPlid = PortalUtil.getPlidFromPortletId(event.getGroupId(), PortletKeys.CALENDAR);

			if (selPlid != LayoutConstants.DEFAULT_PLID) {
				LiferayPortletURL eventURL = PortletURLFactoryUtil.create(request, PortletKeys.CALENDAR, selPlid, PortletRequest.RENDER_PHASE);

				eventURL.setWindowState(LiferayWindowState.NORMAL);
				eventURL.setPortletMode(PortletMode.VIEW);

				eventURL.setParameter("struts_action", "/calendar/view");
				eventURL.setParameter("tabs1", "day");
				eventURL.setParameter("month", String.valueOf(cal.get(Calendar.MONTH)));
				eventURL.setParameter("day", String.valueOf(cal.get(Calendar.DATE)));
				eventURL.setParameter("year", String.valueOf(cal.get(Calendar.YEAR)));

				eventHREF = eventURL.toString();
			}
			%>

			<div class="event">
				<div>
					<span class="event-name">
						<a href="<%= eventHREF %>"><%= StringUtil.shorten(event.getTitle(), 40) %></a>
					</span>

					<c:if test="<%= !event.isAllDay() %>">
						<span class="event-time">
							<c:choose>
								<c:when test="<%= event.isTimeZoneSensitive() %>">
									<%= dateFormatTime.format(Time.getDate(event.getStartDate(), timeZone)) %>
								</c:when>
								<c:otherwise>
									<%= dateFormatTime.format(event.getStartDate()) %>
								</c:otherwise>
							</c:choose>
						</span>
					</c:if>
				</div>

				<div>
					<c:if test="<%= group.isUser() %>">
						<span class="event-site">
							<a href="<%= groupURL.toString() %>"><%= group.getDescriptiveName(locale) %></a>
						</span>
					</c:if>

					<span class="event-type">
						<%= LanguageUtil.get(pageContext, event.getType()) %>
					</span>
				</div>
			</div>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>