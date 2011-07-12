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
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
int month = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:calendar:month"));
int day = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:calendar:day"));
int year = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:calendar:year"));

Calendar selCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

selCal.set(Calendar.MONTH, month);
selCal.set(Calendar.DATE, day);
selCal.set(Calendar.YEAR, year);

int selMonth = selCal.get(Calendar.MONTH);
int selDay = selCal.get(Calendar.DATE);
int selYear = selCal.get(Calendar.YEAR);

Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM, yyyy", locale);
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/taglib/ui/calendar/page.jsp" useCustomPage="<%= false %>" />
</liferay-util:buffer>

<%
int x = html.indexOf("<tr class=\"calendar-header\">");
int y = html.indexOf("</tr>", x);
%>

<%= html.substring(0, x) %>

<tr>
	<th valign="center">
		<a href="javascript:<%= namespace %>updateCalendar(<%= selMonth - 1 %>, <%= selDay %>, <%= selYear %>);">
			<img border="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/01_left.png" />
		</a>
	</th>
	<th valign="center" colspan="5">
		<%= dateFormat.format(selCal.getTime()) %>
	</th>
	<th valign="center">
		<a href="javascript:<%= namespace %>updateCalendar(<%= selMonth + 1 %>, <%= selDay %>, <%= selYear %>);">
			<img border="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/01_right.png" />
		</a>
	</th>
</tr>

<%= html.substring(y + 5) %>