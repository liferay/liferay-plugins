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

<%@ include file="/calendar/init.jsp" %>

<%
CalendarResourceDisplayTerms displayTerms = new CalendarResourceDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_resource_search"
>
	<aui:fieldset>
		<aui:input name="<%= displayTerms.CODE %>" size="20" value="<%= displayTerms.getCode() %>" />
		<aui:input name="<%= displayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />
		<aui:input name="<%= displayTerms.DESCRIPTION %>" size="20" value="<%= displayTerms.getDescription() %>" />
		<aui:select name="type">
			<aui:option label="all" value="" />
			<%
			for (String resourceType : PortletPropsValues.ECALENDAR_RESOURCE_TYPES) {
			%>
				<aui:option label="<%= resourceType %>" value="<%= resourceType %>" selected="<%= displayTerms.getType().equals(resourceType) %>" />
			<%
			}
			%>
		</aui:select>
		<aui:select name="<%= displayTerms.ACTIVE %>">
			<aui:option label="yes" selected="<%= displayTerms.isActive() %>" value="1" />
			<aui:option label="no" selected="<%= !displayTerms.isActive() %>" value="0" />
		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>