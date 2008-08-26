<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

<%@ include file="/knowledge_base/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "export-settings");

String redirect = ParamUtil.getString(request, "redirect");

String[] rssTypes = new String[] {atom, rss10, rss20};

String[] displayRSSTypes = prefs.getValues("displayRSSTypes",  new String[] {rss20});

String[] conversions = DocumentConversionUtil.getConversions("html");

String[] extensions = prefs.getValues("extensions", new String[] {"pdf"});
%>

<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="portletURL" portletConfiguration="true">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />actionName" type="hidden" value="<%= Constants.UPDATE %>" />
<input name="<portlet:namespace />tabs2" type="hidden" value="<%= HtmlUtil.escape(tabs2) %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />

<liferay-ui:tabs
	names="export-settings,rss"
	param="tabs2"
	url="<%= portletURL %>"
/>

<liferay-ui:message key="enable-the-openoffice-integration-first-then-select-which-are-the-possible-formats" />

<br /><br />

<c:choose>
	<c:when test='<%= tabs2.equals("export-settings") %>'>
		<table class="lfr-table">
			<tr valign="middle">

				<%
				for (String conversion : conversions) {
				%>

				<td>
					<input type="checkbox" <%= ArrayUtil.contains(extensions, conversion) ? "checked":"" %> name="<portlet:namespace />extensions" value="<%= conversion %>" />
				</td>
				<td>
					<%= conversion.toUpperCase() %>
				</td>

				<%
				}
				%>

			</tr>
		</table>
	</c:when>
	<c:when test='<%= tabs2.equals("rss") %>'>
		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="rss" />
			</td>
			<td>
				<table class="lfr-table">
					<tr valign="middle">

						<%
						for (String rssType : rssTypes) {
						%>

						<td>
							<input type="checkbox" <%= ArrayUtil.contains(displayRSSTypes, rssType) ? "checked":"" %> name="<portlet:namespace />displayRSSTypes" value="<%= rssType %>" />
						</td>
						<td>
							<%= rssType %>
						</td>

						<%
						}
						%>

					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="maximum-items-to-display" />
			</td>
			<td>
				<select name="<portlet:namespace />rssDelta">
					<option <%= (rssDelta == 1) ? "selected" : "" %> value="1">1</option>
					<option <%= (rssDelta == 2) ? "selected" : "" %> value="2">2</option>
					<option <%= (rssDelta == 3) ? "selected" : "" %> value="3">3</option>
					<option <%= (rssDelta == 4) ? "selected" : "" %> value="4">4</option>
					<option <%= (rssDelta == 5) ? "selected" : "" %> value="5">5</option>
					<option <%= (rssDelta == 10) ? "selected" : "" %> value="10">10</option>
					<option <%= (rssDelta == 15) ? "selected" : "" %> value="15">15</option>
					<option <%= (rssDelta == 20) ? "selected" : "" %> value="20">20</option>
					<option <%= (rssDelta == 25) ? "selected" : "" %> value="25">25</option>
					<option <%= (rssDelta == 30) ? "selected" : "" %> value="30">30</option>
					<option <%= (rssDelta == 40) ? "selected" : "" %> value="40">40</option>
					<option <%= (rssDelta == 50) ? "selected" : "" %> value="50">50</option>
					<option <%= (rssDelta == 60) ? "selected" : "" %> value="60">60</option>
					<option <%= (rssDelta == 70) ? "selected" : "" %> value="70">70</option>
					<option <%= (rssDelta == 80) ? "selected" : "" %> value="80">80</option>
					<option <%= (rssDelta == 90) ? "selected" : "" %> value="90">90</option>
					<option <%= (rssDelta == 100) ? "selected" : "" %> value="100">100</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="display-style" />
			</td>
			<td>
				<select name="<portlet:namespace />rssDisplayStyle">
					<option <%= (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) ? "selected" : "" %> value="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>"><liferay-ui:message key="full-content" /></option>
					<option <%= (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT)) ? "selected" : "" %> value="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>"><liferay-ui:message key="abstract" /></option>
					<option <%= (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE)) ? "selected" : "" %> value="<%= RSSUtil.DISPLAY_STYLE_TITLE %>"><liferay-ui:message key="title" /></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="abstract-length" />
			</td>
			<td>
				<select name="<portlet:namespace />abstractLength">
					<option <%= (abstractLength == 10) ? "selected" : "" %> value="10">10</option>
					<option <%= (abstractLength == 15) ? "selected" : "" %> value="15">15</option>
					<option <%= (abstractLength == 20) ? "selected" : "" %> value="20">20</option>
					<option <%= (abstractLength == 25) ? "selected" : "" %> value="25">25</option>
					<option <%= (abstractLength == 30) ? "selected" : "" %> value="30">30</option>
					<option <%= (abstractLength == 40) ? "selected" : "" %> value="40">40</option>
					<option <%= (abstractLength == 50) ? "selected" : "" %> value="50">50</option>
					<option <%= (abstractLength == 60) ? "selected" : "" %> value="60">60</option>
					<option <%= (abstractLength == 70) ? "selected" : "" %> value="70">70</option>
					<option <%= (abstractLength == 80) ? "selected" : "" %> value="80">80</option>
					<option <%= (abstractLength == 90) ? "selected" : "" %> value="90">90</option>
					<option <%= (abstractLength == 100) ? "selected" : "" %> value="100">100</option>
				</select>
			</td>
		</tr>
		</table>
	</c:when>
</c:choose>

<br /><br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>