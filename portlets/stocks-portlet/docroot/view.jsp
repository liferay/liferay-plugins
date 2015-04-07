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

<portlet:renderURL var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>" />

<aui:form action="<%= portletURL %>" cssClass="stock-options-form" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<c:choose>
		<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">
			<table class="lfr-table">

				<%
				for (int i = 0; i < symbols.length; i++) {
					Stocks stocks = StocksUtil.getStocks(symbols[i]);

					if (stocks != null) {
				%>

						<tr>
							<td>
								<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/stocks/view" /><portlet:param name="symbol" value="<%= stocks.getSymbol() %>" /></portlet:renderURL>" style="font-size: xx-small; font-weight: bold;"><%= stocks.getSymbol() %></a>
							</td>
							<td align="right">
								<%= stocks.isLastTradeAvailable() ? decimalFormat.format(stocks.getLastTrade()) : LanguageUtil.get(request, "not-available") %>
							</td>
							<td align="right">
								<c:if test="<%= stocks.getChange() < 0 %>">
									<span class="alert alert-danger">
										<%= stocks.isChangeAvailable() ? decimalFormat.format(stocks.getChange()) : LanguageUtil.get(request, "not-available") %>
									</span>
								</c:if>

								<c:if test="<%= stocks.getChange() > 0 %>">
									<span class="alert alert-success">
										+<%= stocks.isChangeAvailable() ? decimalFormat.format(stocks.getChange()) : LanguageUtil.get(request, "not-available") %>
									</span>
								</c:if>

								<c:if test="<%= stocks.getChange() == 0 %>">
									<span style="font-size: xx-small;">
										<%= stocks.isChangeAvailable() ? decimalFormat.format(stocks.getChange()) : LanguageUtil.get(request, "not-available") %>
									</span>
								</c:if>
							</td>
						</tr>

				<%
					}
				}
				%>

			</table>

			<br />

			<aui:input label="" maxlength="10" name="symbol" size="10" type="text" />

			<aui:button-row>
				<aui:button type="submit" value="get-quote" />
			</aui:button-row>
		</c:when>
		<c:otherwise>

			<%
			String symbol = StringUtil.toUpperCase(ParamUtil.getString(request, "symbol"));

			Stocks stocks = null;

			try {
				stocks = StocksUtil.getStocks(symbol);
			}
			catch (Exception e) {
			}

			int time = ParamUtil.getInteger(request, "time", 8);

			if ((time < 1) || ((time > 13) && (time < 18)) || (time > 20)) {
				time = 8;
			}

			String freq = "1";

			if (time == 1) {
				freq = "9";
			}
			else if (time == 2) {
				freq = "6";
			}
			else if ((time == 3) || (time == 18)) {
				freq = "7";
			}
			else if ((time == 10) || (time == 11) || (time == 12) || (time == 13) || (time == 20)) {
				freq = "2";
			}
			%>

			<aui:input label="" maxlength="10" name="symbol" size="10" type="text" value="<%= symbol %>" />

			<aui:select class="stock-options" label="" name="time" onChange="submitForm(document.<portlet:namespace />fm);" value="<%= time %>">
				<aui:option value="1">1 <liferay-ui:message key="day" /></aui:option>
				<aui:option value="2">2 <liferay-ui:message key="days" /></aui:option>
				<aui:option value="3">5 <liferay-ui:message key="days" /></aui:option>
				<aui:option value="18">10 <liferay-ui:message key="days" /></aui:option>
				<aui:option value="4">1 <liferay-ui:message key="month" /></aui:option>
				<aui:option value="5">2 <liferay-ui:message key="months" /></aui:option>
				<aui:option value="6">3 <liferay-ui:message key="months" /></aui:option>
				<aui:option value="7">6 <liferay-ui:message key="months" /></aui:option>
				<aui:option value="19"><liferay-ui:message key="year-to-date" /></aui:option>
				<aui:option value="8">1 <liferay-ui:message key="year" /></aui:option>
				<aui:option value="9">2 <liferay-ui:message key="years" /></aui:option>
				<aui:option value="10">3 <liferay-ui:message key="years" /></aui:option>
				<aui:option value="11">4 <liferay-ui:message key="years" /></aui:option>
				<aui:option value="12">5 <liferay-ui:message key="years" /></aui:option>
				<aui:option value="13">1 <liferay-ui:message key="decade" /></aui:option>
				<aui:option value="20"><liferay-ui:message key="all-data" /></aui:option>
			</aui:select>

			<aui:button type="submit" value="get-quote" />

			<br /><br />

			<c:choose>
				<c:when test="<%= stocks != null %>">
					<table border="1" cellpadding="0" cellspacing="0" width="579">
						<tr>
							<td align="center">
								<liferay-ui:message key="last-trade" /><br />
								<strong><%= decimalFormat.format(stocks.getLastTrade()) %></strong>
							</td>
							<td align="center">
								<liferay-ui:message key="change" /><br />

								<c:if test="<%= stocks.isChangeAvailable() && stocks.isPreviousCloseAvailable() %>">
									<c:if test="<%= stocks.getChange() < 0 %>">
										<span class="alert alert-danger">
											<strong><%= decimalFormat.format(stocks.getChange()) %> / <%= decimalFormat.format(stocks.getChange() / stocks.getPreviousClose() * 100) %>%</strong>
										</span>
									</c:if>

									<c:if test="<%= stocks.getChange() > 0 %>">
										<span class="alert alert-success">
											<strong>+<%= decimalFormat.format(stocks.getChange()) %> / <%= decimalFormat.format(stocks.getChange() / stocks.getPreviousClose() * 100) %>%</strong>
										</span>
									</c:if>

									<c:if test="<%= stocks.getChange() == 0 %>">
										<strong><%= decimalFormat.format(stocks.getChange()) %> / <%= decimalFormat.format(stocks.getChange() / stocks.getPreviousClose() * 100) %>%</strong>
									</c:if>
								</c:if>

								<c:if test="<%= !stocks.isChangeAvailable() || !stocks.isPreviousCloseAvailable() %>">
									<strong><liferay-ui:message key="not-available" /></strong>
								</c:if>
							</td>
							<td align="center">
								<liferay-ui:message key="day-high" /><br />
								<strong><%= stocks.isDayHighAvailable() ? decimalFormat.format(stocks.getDayHigh()) : LanguageUtil.get(request, "not-available") %></strong>
							</td>
							<td align="center">
								<liferay-ui:message key="day-low" /><br />
								<strong><%= stocks.isDayLowAvailable() ? decimalFormat.format(stocks.getDayLow()) : LanguageUtil.get(request, "not-available") %></strong>
							</td>
							<td align="center">
								<liferay-ui:message key="open" /><br />
								<strong><%= stocks.isOpenAvailable() ? decimalFormat.format(stocks.getOpen()) : LanguageUtil.get(request, "not-available") %></strong>
							</td>
							<td align="center">
								<liferay-ui:message key="previous-close" /><br />
								<strong><%= stocks.isPreviousCloseAvailable() ? decimalFormat.format(stocks.getPreviousClose()) : LanguageUtil.get(request, "not-available") %></strong>
							</td>
							<td align="center">
								<liferay-ui:message key="volume" /><br />
								<strong><%= stocks.isVolumeAvailable() ? numberFormat.format(stocks.getVolume()) : LanguageUtil.get(request, "not-available") %></strong>
							</td>
						</tr>
					</table>

					<br />

					<div>
						<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="chart" />" src="<%= HttpUtil.getProtocol(request) %>://bigcharts.marketwatch.com/kaavio.Webhost/charts/big.chart?symb=<%= symbol %>&freq=<%= freq %>&time=<%= time %>" />
					</div>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.format(request, "no-information-was-found-associated-with-the-symbol-x", symbol, false) %>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />symbol);
	</aui:script>
</c:if>