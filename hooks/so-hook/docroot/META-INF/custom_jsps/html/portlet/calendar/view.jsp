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

<%@ include file="/html/portlet/calendar/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "month");

String eventType = ParamUtil.getString(request, "eventType");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("struts_action", "/calendar/view");
portletURL.setParameter("tabs1", tabs1);
%>

<form method="post" name="<portlet:namespace />fm">

<%
Set data = new HashSet();

for (int i = 1; i <= selCal.getActualMaximum(Calendar.DATE); i++) {
	Calendar tempCal = (Calendar)selCal.clone();

	tempCal.set(Calendar.MONTH, selMonth);
	tempCal.set(Calendar.DATE, i);
	tempCal.set(Calendar.YEAR, selYear);

	boolean hasEvents = CalEventServiceUtil.hasEvents(scopeGroupId, tempCal, eventType);

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

<liferay-util:include page="/html/portlet/calendar/tabs1.jsp" />

<br />

<c:choose>
	<c:when test='<%= tabs1.equals("day") %>'>
		<%@ include file="/html/portlet/calendar/day.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("week") %>'>
		<%@ include file="/html/portlet/calendar/week.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("month") %>'>
		<%@ include file="/html/portlet/calendar/month.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("export-import") %>'>
		<%@ include file="/html/portlet/calendar/export_import.jspf" %>
	</c:when>
</c:choose>

</form>

<c:if test='<%= !tabs1.equals("export-import") %>'>

	<%
	String className = StringPool.BLANK;

	if (tabs1.equals("day")) {
		className += " day-view";
	}
	%>

	<form method="post" name="<portlet:namespace />fm1">

	<div class="export-import-calendar<%= className %>">
		<input name="exportFileName" type="hidden" value="<%= layout.getGroup().getName() %>.ics" />

		<c:if test="<%= CalendarPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_EVENT) %>">
			<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/calendar/view" /><portlet:param name="tabs1" value="export-import" /></portlet:renderURL>"><liferay-ui:message key="import" /></a>
		</c:if>

		<c:if test="<%= CalendarPermission.contains(permissionChecker, scopeGroupId, ActionKeys.EXPORT_ALL_EVENTS) %>">
			<a class="export-events" href="javascript:;" onClick="document.<portlet:namespace />fm1.action = '<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/calendar/export_events" /></portlet:actionURL>'; document.<portlet:namespace />fm1.submit();"><liferay-ui:message key="export" /></a>
		</c:if>

		<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
			<liferay-security:permissionsURL
				modelResource="com.liferay.portlet.calendar"
				modelResourceDescription="<%= portletDisplay.getTitle() %>"
				resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
				var="permissionsURL"
			/>

			<a class="permissions" href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
		</c:if>
	</div>

	</form>
</c:if>

<aui:script use="aui-dialog">
	function <portlet:namespace />updateCalendar(month, day, year) {
		location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="tabs1" value="<%= tabs1 %>" /><portlet:param name="eventType" value="<%= eventType %>" /></portlet:renderURL>&<portlet:namespace />month=' + month + '&<portlet:namespace />day=' + day + '&<portlet:namespace />year=' + year;
	}

	Liferay.namespace('SO');

	Liferay.SO.Calendar = {
		closePopup: function() {
			var instance = this;

			var popup = instance._getPopup();

			popup.hide();
		},

		displayPopup: function(url, title) {
			var instance = this;

			var viewportRegion = A.getBody().get('viewportRegion');

			var popup = instance._getPopup();

			popup.show();

			popup.set('title', title);
			popup.set('xy', [viewportRegion.left + 20, viewportRegion.top + 20]);

			popup.io.set('uri', url);
			popup.io.start();
		},

		_getPopup: function() {
			var instance = this;

			if (!instance._popup) {
				instance._popup = new A.Dialog(
					{
						resizable: false,
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{autoLoad: false}
				).render();
			}

			return instance._popup;
		}
	}

	A.one('.portlet-calendar').delegate(
		'click',
		function(event) {
			var href = event.currentTarget.getAttribute('href');

			var expr = /_struts_action(=\%2f|\%3d\%252f)calendar(\%2f|\%252f)view_event/;

			if (expr.test(href.toLowerCase())) {
				href = href.replace(/p_p_state=(<%= LiferayWindowState.NORMAL %>|<%= LiferayWindowState.MAXIMIZED %>)/gim, 'p_p_state=<%= LiferayWindowState.EXCLUSIVE %>');

				Liferay.SO.Calendar.displayPopup(href, "Calendar Event");

				event.halt();
			}
		},
		'a'
	);
</aui:script>