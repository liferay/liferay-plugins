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
CalendarResourceDisplayTerms displayTerms = new CalendarResourceDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_calendar_resource_search"
>
	<aui:fieldset>
		<aui:input name="<%= displayTerms.CODE %>" value="<%= displayTerms.getCode() %>" />

		<aui:input name="<%= displayTerms.NAME %>" value="<%= displayTerms.getName() %>" />

		<aui:input name="<%= displayTerms.DESCRIPTION %>" value="<%= displayTerms.getDescription() %>" />

		<aui:select name="type" value="">
			<aui:option label="all" value="<%= displayTerms.getType() %>" />

			<%
			for (String type : PortletPropsValues.CALENDAR_RESOURCE_TYPES) {
			%>

				<aui:option label="<%= type %>" value="<%= type %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select name="<%= displayTerms.ACTIVE %>" value="<%= displayTerms.isActive() %>">
			<aui:option label="yes" value="true" />
			<aui:option label="no" value="false" />
		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>