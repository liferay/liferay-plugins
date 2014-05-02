<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<div class="event-display-portlet">
	<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

	<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="display-options">
			<aui:fieldset>
				<aui:select label="how-many-days-to-display" name="preferences--maxDaysDisplayed--" value="<%= maxDaysDisplayed %>">
					<aui:option label="1" value="<%= 1 %>" />
					<aui:option label="2" value="<%= 2 %>" />
					<aui:option label="3" value="<%= 3 %>" />
					<aui:option label="4" value="<%= 4 %>" />
					<aui:option label="5" value="<%= 5 %>" />
				</aui:select>

				<aui:select label="maximum-events-to-display" name="preferences--eventsPerPage--">

					<%
					int[] deltaValues = GetterUtil.getIntegerValues(PrefsPropsUtil.getStringArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES, StringPool.COMMA));

					for (int pageDeltaValue : deltaValues) {
					%>

						<aui:option label="<%= pageDeltaValue %>" selected="<%= eventsPerPage == pageDeltaValue %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>

		<aui:button type="submit" />
	</aui:form>
</div>