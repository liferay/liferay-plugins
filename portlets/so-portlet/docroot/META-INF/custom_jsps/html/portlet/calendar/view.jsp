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

<script type="text/javascript">
	function <portlet:namespace />displayPopup(popup, popupUrl, popupTitle) {
		if ((popup == null) || !(popup[0].parentNode && popup[0].parentNode.tagName)) {
			popup = new Liferay.Popup(
				{
					title: popupTitle,
					className: 'calendar-dialog',
					resizable: false,
					height: 'auto',
					position: [15,15],
					width: '600px',
					close: function() {
						popup = null;
					}
				}
			);
		}
		else {
			var popupContainer = popup.parents('.ui-dialog');

			popupContainer.find('.ui-dialog-title').text(popupTitle);

			var popupHelper = popupContainer.data('ui-helper-drag');

			if (popupHelper) {
				popupHelper.find('.ui-dialog-title').text(popupTitle);
			}
		}

		popup.html('<div class="loading-animation" />');

		jQuery.ajax(
			{
				url: popupUrl,
				success: function(message) {
					popup.html(message);
				}
			}
		);

		return popup;
	}

	jQuery(
		function() {
			var popup;

			var calendarLinks = jQuery('.portlet-calendar a');
			var eventEditLinks = jQuery('.portlet-calendar .event-edit a');

			calendarLinks.livequery(
				'click',
				function() {
					var event = jQuery(this);
					var eventURL = event.attr('href');

					var expr = /_struts_action(=\%2F|\%3D\%252F)calendar(\%2F|\%252F)(edit_event|view_event)/;

					if (expr.test(eventURL)) {
						eventURL = eventURL.replace(/p_p_state=<%= LiferayWindowState.MAXIMIZED %>/gim, 'p_p_state=<%= LiferayWindowState.EXCLUSIVE %>');
						eventURL += "&<portlet:namespace />callingPageURL=<%= currentURL %>";

						popup = <portlet:namespace />displayPopup(popup, eventURL, "Calendar Event");

						return false;
					}

					return true;
				}
			);

			eventEditLinks.removeAttr('onClick');
		}
	);
</script>