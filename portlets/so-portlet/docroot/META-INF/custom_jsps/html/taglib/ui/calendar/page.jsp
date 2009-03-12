<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

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

DateFormat dateFormat = new SimpleDateFormat("MMMM, yyyy", locale);
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/taglib/ui/calendar/page.portal.jsp" />
</liferay-util:buffer>

<%
int x = html.indexOf("<tr class=\"calendar-header\">");
int y = html.indexOf("</tr>", x);
%>

<%= html.substring(0, x) %>

<tr>
	<th valign="center">
		<a href="javascript: <%= namespace %>updateCalendar(<%= selMonth - 1 %>, <%= selDay %>, <%= selYear %>);">
			<img border="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/01_left.png" />
		</a>
	</th>
	<th valign="center" colspan="5">
		<%= dateFormat.format(selCal.getTime()) %>
	</th>
	<th valign="center">
		<a href="javascript: <%= namespace %>updateCalendar(<%= selMonth + 1 %>, <%= selDay %>, <%= selYear %>);">
			<img border="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/01_right.png" />
		</a>
	</th>
</tr>

<%= html.substring(y + 5) %>