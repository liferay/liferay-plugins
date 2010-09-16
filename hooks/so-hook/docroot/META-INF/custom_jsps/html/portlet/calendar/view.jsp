<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/html/portlet/calendar/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);

String eventType = ParamUtil.getString(request, "eventType");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("struts_action", "/calendar/view");
portletURL.setParameter("tabs1", tabs1);
%>

<form method="post" name="<portlet:namespace />fm">

<c:if test='<%= !tabs1.equals("events") %>'>
	<%@ include file="/html/portlet/calendar/mini_calendar.jsp" %>

	<liferay-util:include page="/html/portlet/calendar/tabs1.jsp" />

	<br />
</c:if>

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
	<c:when test='<%= tabs1.equals("events") %>'>
		<%@ include file="/html/portlet/calendar/events.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("export-import") %>'>
		<%@ include file="/html/portlet/calendar/export_import.jspf" %>
	</c:when>
</c:choose>

</form>

<c:if test='<%= !tabs1.equals("export-import") && !tabs1.equals("events") %>'>
	<%@ include file="/html/portlet/calendar/export_import_action.jsp" %>
</c:if>

<aui:script use="aui-dialog">
	Liferay.namespace('SO');

	Liferay.SO.Calendar = {
		closePopup: function() {
			var instance = this;

			var popup = instance._getPopup();

			popup.hide();
		},

		displayPopup: function(url, title) {
			var instance = this;

			var popup = instance._getPopup();

			popup.show();

			popup.set('title', title);

			popup.io.set('uri', url);
			popup.io.start();
		},

		_getPopup: function() {
			var instance = this;

			if (!instance._popup) {
				instance._popup = new A.Dialog(
					{
						resizable: false,
						width: 600,
						xy: [15,15]
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