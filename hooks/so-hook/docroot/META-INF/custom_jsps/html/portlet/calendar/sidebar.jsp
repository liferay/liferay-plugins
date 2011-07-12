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
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);

String redirect = ParamUtil.getString(request, "redirect");

PortletURL tabs1URL = renderResponse.createRenderURL();

tabs1URL.setParameter("struts_action", "/calendar/view");
tabs1URL.setParameter("month", String.valueOf(selMonth));
tabs1URL.setParameter("day", String.valueOf(selDay));
tabs1URL.setParameter("year", String.valueOf(selYear));
%>

<div class="sidebar">
	<h2>
		<liferay-ui:message key="quick-links" />
	</h2>

	<ul class="disc">
		<li>

			<%
			tabs1URL.setParameter("tabs1", "month");
			%>

			<a href="<%= tabs1URL.toString() %>"><liferay-ui:message key="month" /></a>
		</li>
		<li>

			<%
			tabs1URL.setParameter("tabs1", "week");
			%>

			<a href="<%= tabs1URL.toString() %>"><liferay-ui:message key="week" /></a>
		</li>
		<li>

			<%
			tabs1URL.setParameter("tabs1", "day");
			%>

			<a href="<%= tabs1URL.toString() %>"><liferay-ui:message key="day" /></a>
		</li>
	</ul>
	<ul class="disc">
		<li>
			<a href="<%= PortalUtil.escapeRedirect(redirect) %>"><liferay-ui:message key="back" /></a>
		</li>
	</ul>
</div>