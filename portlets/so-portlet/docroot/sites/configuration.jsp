<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/sites/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:select label="maximum-result-size" name="preferences--maxResultSize--">
		<aui:option label="<%= 5 %>" selected="<%= 5 == maxResultSize %>" value="<%= 5 %>" />
		<aui:option label="<%= 10 %>" selected="<%= 10 == maxResultSize %>" value="<%= 10 %>" />
		<aui:option label="<%= 15 %>" selected="<%= 15 == maxResultSize %>" value="<%= 15 %>" />
		<aui:option label="<%= 20 %>" selected="<%= 20 == maxResultSize %>" value="<%= 20 %>" />
	</aui:select>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>