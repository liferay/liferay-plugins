<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post">
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

	<div class="portlet-msg-info">
		<a href="http://www.worldweatheronline.com/free-weather-feed.aspx" target="_blank"><liferay-ui:message key="you-can-get-an-api-key-directly-from-world-weather-online" /></a>
	</div>

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" label="world-weather-online-api-key" name="preferences--apiKey--" value="<%= apiKey %>" />

		<aui:input cssClass="lfr-textarea-container" label="enter-one-city-or-zip-code-per-line" name="preferences--zips--" type="textarea" value="<%= zipsString %>" />

		<aui:select label="temperature-format" name="preferences--fahrenheit--">
			<aui:option label="fahrenheit" selected="<%= fahrenheit %>" value="1" />
			<aui:option label="celsius" selected="<%= !fahrenheit %>" value="0" />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>