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

<c:choose>
	<c:when test="<%= Validator.isNotNull(apiKey) %>">
		<form name="<portlet:namespace />fm" onSubmit="submitForm(document.<portlet:namespace />fm, 'http://www.worldweatheronline.com/v2/weather.aspx', false); return false;" target="_blank">

		<table class="lfr-table">

		<%
		for (String zip : zips) {
			Weather weather = WeatherUtil.getWeather(apiKey, zip);

			if (weather != null) {
		%>

				<tr>
					<td>
						<a href="http://www.worldweatheronline.com/v2/weather.aspx?q=<%= HtmlUtil.escapeURL(weather.getZip()) %>" style="font-size: xx-small; font-weight: bold;" target="_blank"><%= HtmlUtil.escape(weather.getZip()) %></a>
					</td>
					<td align="right">
						<span style="font-size: xx-small;">

						<c:if test="<%= fahrenheit %>">
							<%= weather.getCurrentTemp() %> &deg;F
						</c:if>

						<c:if test="<%= !fahrenheit %>">
							<%= Math.round((.5555555555 * (weather.getCurrentTemp() + 459.67)) - 273.15) %> &deg;C
						</c:if>

						</span>
					</td>
					<td align="right">
						<img alt="" src="<%= weather.getIconURL() %>" />
					</td>
				</tr>

		<%
			}
		}
		%>

		</table>

		<br />

		<liferay-ui:message key="city-or-zip-code" />

		<input name="q" size="23" type="text" />

		<input type="submit" value="<liferay-ui:message key="search" />" />

		</form>

		<br />

		<liferay-ui:message key="powered-by" /> <a href="http://www.worldweatheronline.com" target="_blank">World Weather Online</a>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.where);
			</aui:script>
		</c:if>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>