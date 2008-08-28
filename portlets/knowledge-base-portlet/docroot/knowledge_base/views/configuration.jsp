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
String redirect = ParamUtil.getString(request, "redirect");

String tabs2 = ParamUtil.getString(request, "tabs2", "export-settings");

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

<c:choose>
	<c:when test='<%= tabs2.equals("export-settings") %>'>
		<fieldset>
			<legend><liferay-ui:message key="document-conversion" /></legend>

			<liferay-ui:message key="enabling-openoffice-integration-provides-document-conversion-functionality" />

			<br /><br />

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
		</fieldset>
	</c:when>
	<c:when test='<%= tabs2.equals("rss") %>'>
		<fieldset>
			<legend><liferay-ui:message key="rss" /></legend>

			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="rss" />
				</td>
				<td>
					<table class="lfr-table">
						<tr valign="middle">

							<%
							for (String rssType : RSSUtil.RSS_TYPES) {
							%>

								<td>
									<input type="checkbox" <%= ArrayUtil.contains(rssTypes, rssType) ? "checked":"" %> name="<portlet:namespace />rssTypes" value="<%= rssType %>" />
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
					<select name="<portlet:namespace />rssMaxItems">
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_10) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_10 %>"><%= RSSUtil.MAX_ITEMS_10 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_20) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_20 %>"><%= RSSUtil.MAX_ITEMS_20 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_30) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_30 %>"><%= RSSUtil.MAX_ITEMS_30 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_50) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_50 %>"><%= RSSUtil.MAX_ITEMS_50 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_100) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_100 %>"><%= RSSUtil.MAX_ITEMS_100 %></option>
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
					<select name="<portlet:namespace />rssAbstractLength">
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_50) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_50 %>"><%= RSSUtil.ABSTRACT_LENGTH_50 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_100) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_100 %>"><%= RSSUtil.ABSTRACT_LENGTH_100 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_200) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_200 %>"><%= RSSUtil.ABSTRACT_LENGTH_200 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_300) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_300 %>"><%= RSSUtil.ABSTRACT_LENGTH_300 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_500) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_500 %>"><%= RSSUtil.ABSTRACT_LENGTH_500 %></option>
					</select>
				</td>
			</tr>
			</table>
		</fieldset>
	</c:when>
</c:choose>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="document.location = '<%= HtmlUtil.escape(redirect) %>'" />

</form>