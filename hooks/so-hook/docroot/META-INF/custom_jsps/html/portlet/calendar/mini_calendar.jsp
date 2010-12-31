<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<script type="text/javascript">
	function <portlet:namespace />updateCalendar(month, day, year) {
		location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="tabs1" value="<%= tabs1 %>" /><portlet:param name="eventType" value="<%= eventType %>" /></portlet:renderURL>&<portlet:namespace />month=' + month + '&<portlet:namespace />day=' + day + '&<portlet:namespace />year=' + year;
	}
</script>

<%
Set data = new HashSet();

for (int i = 1; i <= selCal.getActualMaximum(Calendar.DATE); i++) {
	Calendar tempCal = (Calendar)selCal.clone();

	tempCal.set(Calendar.MONTH, selMonth);
	tempCal.set(Calendar.DATE, i);
	tempCal.set(Calendar.YEAR, selYear);

	boolean hasEvents = CalEventLocalServiceUtil.hasEvents(scopeGroupId, tempCal, eventType);

	if (hasEvents) {
		data.add(new Integer(i));
	}
}
%>

<div class="mini-calendar">
	<div class="calendar-container">
		<liferay-ui:message key="today-is" />

		<div class="calendar-day">
			<h2 class="day-text"><%= DateUtil.getCurrentDate("EEEE", locale) %></h2>

			<h3 class="day-number"><%= curDay %></h3>
		</div>
	</div>

	<liferay-ui:calendar
		month="<%= selMonth %>"
		day="<%= selDay %>"
		year="<%= selYear %>"
		headerFormat='<%= new SimpleDateFormat("MMMM, yyyy", locale) %>'
		data="<%= data %>"
	/>
</div>