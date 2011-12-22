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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.Randomizer" %>
<%@ page import="com.liferay.tich.model.Event" %>
<%@ page import="com.liferay.tich.util.TICHUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

List<Event> events = TICHUtil.getEvents();
%>

<c:choose>
	<c:when test="<%= events != null %>">
		<c:choose>
			<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">

				<%
				Event event = events.get(Randomizer.getInstance().nextInt(events.size()));
				%>

				<strong><em><%= event.getYear() %></em></strong>

				<br /><br />

				<%= event.getDescription() %>

				<br /><br />

				<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" />">
				<liferay-ui:message key="read-more" /> &raquo;</a>
			</c:when>
			<c:otherwise>
				<table class="lfr-table">

				<%
				for (int i = 0; i < events.size(); i++) {
					Event event = (Event)events.get(i);
				%>

					<tr>
						<td valign="top">
							<strong><em><%= event.getYear() %></em></strong>
						</td>
						<td>
							<%= event.getDescription() %>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<br />
						</td>
					</tr>

				<%
				}
				%>

				<tr>
					<td></td>
					<td>
						<liferay-ui:message key="source" />: William D. Blake</a>, <em>Almanac of the Christian Church</em>.
					</td>
				</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_error.jsp" />
	</c:otherwise>
</c:choose>