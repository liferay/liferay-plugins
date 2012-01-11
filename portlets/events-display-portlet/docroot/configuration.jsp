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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<div class="event-display-portlet">
	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="display-options">
			<aui:select label="how-many-days-to-display" name="preferences--maxDaysDisplayed--">
				<aui:option label="1" selected="<%= maxDaysDisplayed == 1 %>" value="<%= 1 %>" />
				<aui:option label="2" selected="<%= maxDaysDisplayed == 2 %>" value="<%= 2 %>" />
				<aui:option label="3" selected="<%= maxDaysDisplayed == 3 %>" value="<%= 3 %>" />
				<aui:option label="4" selected="<%= maxDaysDisplayed == 4 %>" value="<%= 4 %>" />
				<aui:option label="5" selected="<%= maxDaysDisplayed == 5 %>" value="<%= 5 %>" />
			</aui:select>
		</liferay-ui:panel>

		<aui:button type="submit" />
	</aui:form>
</div>