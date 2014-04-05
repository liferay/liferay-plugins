<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/friends_activities/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset>
		<aui:select label="maximum-activities-to-display" name="preferences--max--">
			<aui:option label="1" selected="<%= max == 1 %>" />
			<aui:option label="2" selected="<%= max == 2 %>" />
			<aui:option label="3" selected="<%= max == 3 %>" />
			<aui:option label="4" selected="<%= max == 4 %>" />
			<aui:option label="5" selected="<%= max == 5 %>" />
			<aui:option label="10" selected="<%= max == 10 %>" />
			<aui:option label="15" selected="<%= max == 15 %>" />
			<aui:option label="20" selected="<%= max == 20 %>" />
			<aui:option label="25" selected="<%= max == 25 %>" />
			<aui:option label="30" selected="<%= max == 30 %>" />
			<aui:option label="40" selected="<%= max == 40 %>" />
			<aui:option label="50" selected="<%= max == 50 %>" />
			<aui:option label="60" selected="<%= max == 60 %>" />
			<aui:option label="70" selected="<%= max == 70 %>" />
			<aui:option label="80" selected="<%= max == 80 %>" />
			<aui:option label="90" selected="<%= max == 90 %>" />
			<aui:option label="100" selected="<%= max == 100 %>" />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>