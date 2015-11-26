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

<%@ include file="/init.jsp" %>

<%
String zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);

zips = StringUtil.split(ParamUtil.getString(request, "zips", zipsString), StringPool.NEW_LINE);

zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-ui:error exception="<%= ValidatorException.class %>">

		<%
		ValidatorException ve = (ValidatorException)errorException;
		%>

		<liferay-ui:message key="the-following-are-invalid-cities-or-zip-codes" />

		<%
		Enumeration<String> enu = ve.getFailedKeys();

		while (enu.hasMoreElements()) {
			String zip = enu.nextElement();
		%>

			<strong><%= HtmlUtil.escape(zip) %></strong><%= (enu.hasMoreElements()) ? ", " : "." %>

		<%
		}
		%>

	</liferay-ui:error>

	<aui:fieldset>
		<aui:input label="openweathermap-api-key" name="preferences--apiKey--" type="text" value="<%= apiKey %>" />

		<aui:input label="enter-one-city-or-zip-code-per-line" name="preferences--zips--" type="textarea" value="<%= zipsString %>" wrapperCssClass="lfr-textarea-container" />

		<aui:select label="temperature-format" name="preferences--fahrenheit--">
			<aui:option label="fahrenheit" selected="<%= fahrenheit %>" value="1" />
			<aui:option label="celsius" selected="<%= !fahrenheit %>" value="0" />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>