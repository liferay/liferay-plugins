<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<form action="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" />" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">

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
						<%= stocks.isLastTradeAvailable() ? decimalFormat.format(stocks.getLastTrade()) : LanguageUtil.get(pageContext, "not-available") %>
					</td>
					<td align="right">
						<c:if test="<%= stocks.getChange() < 0 %>">
							<span class="portlet-msg-error">
								<%= stocks.isChangeAvailable() ? decimalFormat.format(stocks.getChange()) : LanguageUtil.get(pageContext, "not-available") %>
							</span>
						</c:if>

						<c:if test="<%= stocks.getChange() > 0 %>">
							<span class="portlet-msg-success">
								+<%= stocks.isChangeAvailable() ? decimalFormat.format(stocks.getChange()) : LanguageUtil.get(pageContext, "not-available") %>
							</span>
						</c:if>

						<c:if test="<%= stocks.getChange() == 0 %>">
							<span style="font-size: xx-small;">
								<%= stocks.isChangeAvailable() ? decimalFormat.format(stocks.getChange()) : LanguageUtil.get(pageContext, "not-available") %>
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

		<input maxlength="10" name="<portlet:namespace />symbol" size="10" type="text" /> <input type="submit" value="<liferay-ui:message key="get-quote" />" />
	</c:when>
	<c:otherwise>

		<%
		String symbol = ParamUtil.getString(request, "symbol").toUpperCase();

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

		<input maxlength="10" name="<portlet:namespace />symbol" size="10" type="text" value="<%= symbol %>" />

		<select name="<portlet:namespace />time" onChange="submitForm(document.<portlet:namespace />fm);">
			<option <%= (time == 1) ? "selected" : "" %> value="1">1 <liferay-ui:message key="day" /></option>
			<option <%= (time == 2) ? "selected" : "" %> value="2">2 <liferay-ui:message key="days" /></option>
			<option <%= (time == 3) ? "selected" : "" %> value="3">5 <liferay-ui:message key="days" /></option>
			<option <%= (time == 18) ? "selected" : "" %> value="18">10 <liferay-ui:message key="days" /></option>
			<option <%= (time == 4) ? "selected" : "" %> value="4">1 <liferay-ui:message key="month" /></option>
			<option <%= (time == 5) ? "selected" : "" %> value="5">2 <liferay-ui:message key="months" /></option>
			<option <%= (time == 6) ? "selected" : "" %> value="6">3 <liferay-ui:message key="months" /></option>
			<option <%= (time == 7) ? "selected" : "" %> value="7">6 <liferay-ui:message key="months" /></option>
			<option <%= (time == 19) ? "selected" : "" %> value="19"><liferay-ui:message key="year-to-date" /></option>
			<option <%= (time == 8) ? "selected" : "" %> value="8">1 <liferay-ui:message key="year" /></option>
			<option <%= (time == 9) ? "selected" : "" %> value="9">2 <liferay-ui:message key="years" /></option>
			<option <%= (time == 10) ? "selected" : "" %> value="10">3 <liferay-ui:message key="years" /></option>
			<option <%= (time == 11) ? "selected" : "" %> value="11">4 <liferay-ui:message key="years" /></option>
			<option <%= (time == 12) ? "selected" : "" %> value="12">5 <liferay-ui:message key="years" /></option>
			<option <%= (time == 13) ? "selected" : "" %> value="13">1 <liferay-ui:message key="decade" /></option>
			<option <%= (time == 20) ? "selected" : "" %> value="20"><liferay-ui:message key="all-data" /></option>
		</select>

		<input type="submit" value="<liferay-ui:message key="get-quote" />" />

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
								<span class="portlet-msg-error">
								<strong><%= decimalFormat.format(stocks.getChange()) %> / <%= decimalFormat.format(stocks.getChange() / stocks.getPreviousClose() * 100) %>%</strong>
								</span>
							</c:if>

							<c:if test="<%= stocks.getChange() > 0 %>">
								<span class="portlet-msg-success">
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
						<strong><%= stocks.isDayHighAvailable() ? decimalFormat.format(stocks.getDayHigh()) : LanguageUtil.get(pageContext, "not-available") %></strong>
					</td>
					<td align="center">
						<liferay-ui:message key="day-low" /><br />
						<strong><%= stocks.isDayLowAvailable() ? decimalFormat.format(stocks.getDayLow()) : LanguageUtil.get(pageContext, "not-available") %></strong>
					</td>
					<td align="center">
						<liferay-ui:message key="open" /><br />
						<strong><%= stocks.isOpenAvailable() ? decimalFormat.format(stocks.getOpen()) : LanguageUtil.get(pageContext, "not-available") %></strong>
					</td>
					<td align="center">
						<liferay-ui:message key="previous-close" /><br />
						<strong><%= stocks.isPreviousCloseAvailable() ? decimalFormat.format(stocks.getPreviousClose()) : LanguageUtil.get(pageContext, "not-available") %></strong>
					</td>
					<td align="center">
						<liferay-ui:message key="volume" /><br />
						<strong><%= stocks.isVolumeAvailable() ? numberFormat.format(stocks.getVolume()) : LanguageUtil.get(pageContext, "not-available") %></strong>
					</td>
				</tr>
				</table>

				<br />

				<table border="0" cellpadding="0" cellspacing="0" width="579">
				<tr>
					<td align="center">
						<img  alt="<liferay-ui:message key="chart" />" src="<%= HttpUtil.getProtocol(request) %>://chart.bigcharts.com/bc3/quickchart/chart.asp?symb=<%= symbol %>&freq=<%= freq %>&time=<%= time %>" />
					</td>
				</tr>
				</table>
			</c:when>
			<c:otherwise>
				<%= LanguageUtil.format(pageContext, "no-information-was-found-associated-with-the-symbol-x", symbol) %>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />symbol);
	</script>
</c:if>