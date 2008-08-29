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

<%@ include file="/knowledge_base_aggregator/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String tabs2 = ParamUtil.getString(request, "tabs2", "display-settings");

String companyName = company.getName();
String groupName = StringPool.BLANK;

if (group.isCommunity()) {
	groupName = group.getName();
}
else if (group.isOrganization()) {
	groupName = organization.getName();
}
else if (group.isUser() || group.isUserGroup()) {
	groupName = user2.getFullName();
}
%>

<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="portletURL" portletConfiguration="true">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<script type="text/javascript">
	function <portlet:namespace />toggleScopeName() {
		var scope = document.<portlet:namespace />fm['<portlet:namespace />scope'].value;

		if (scope == "company") {
			document.<portlet:namespace />fm.<portlet:namespace />companyId.value = <%= company.getCompanyId() %>;
			document.<portlet:namespace />fm.<portlet:namespace />groupId.value = 0;

			document.getElementById("<portlet:namespace />companyName").style.display = "";
			document.getElementById("<portlet:namespace />groupName").style.display = "none";
		}
		else {
			document.<portlet:namespace />fm.<portlet:namespace />companyId.value = 0;
			document.<portlet:namespace />fm.<portlet:namespace />groupId.value = <%= group.getGroupId() %>;

			document.getElementById("<portlet:namespace />companyName").style.display = "none";
			document.getElementById("<portlet:namespace />groupName").style.display = "";
		}
	}
</script>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />tabs2" type="hidden" value="<%= HtmlUtil.escape(tabs2) %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />companyId" type="hidden" value="" />
<input name="<portlet:namespace />groupId" type="hidden" value="" />

<liferay-ui:tabs
	names="display-settings,rss"
	param="tabs2"
	url="<%= portletURL %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("display-settings") %>'>
		<fieldset>
			<legend><liferay-ui:message key="articles" /></legend>

			<table class="lfr-table">
			<tr>
				<th>
					&nbsp;
				</th>
				<th>
					<liferay-ui:message key="scope" />
				</th>
				<th>
					<liferay-ui:message key="name" />
				</th>
				<th>
					&nbsp;
				</th>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="current" />
				</td>
				<td>
					<select name="<portlet:namespace />scope" onchange="<portlet:namespace/>toggleScopeName();">
						<option <%= (groupId <= 0) ? "selected" : "" %> value="company"><liferay-ui:message key="company" /></option>
						<option <%= (groupId > 0) ? "selected" : "" %> value="group"><liferay-ui:message key="group" /></option>
					</select>
				</td>
				<td>
					<span id="<portlet:namespace />companyName"><%= companyName %></span>
					<span id="<portlet:namespace />groupName"><%= groupName %></span>
				</td>
				<td>
					<liferay-ui:icon-help message="select-the-scope-to-search-for-knowledge-base-articles" />
				</td>
			</tr>
			</table>
		</fieldset>

		<br />

		<fieldset>
			<legend><liferay-ui:message key="article" /></legend>

			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="display-style" />
				</td>
				<td>
					<select name="<portlet:namespace />displayStyle">
						<option <%= (displayStyle.equals(KBAggregatorUtil.DISPLAY_STYLE_ABSTRACT)) ? "selected" : "" %> value="<%= KBAggregatorUtil.DISPLAY_STYLE_ABSTRACT %>"><liferay-ui:message key="abstract" /></option>
						<option <%= (displayStyle.equals(KBAggregatorUtil.DISPLAY_STYLE_ABSTRACT_AND_IMAGE)) ? "selected" : "" %> value="<%= KBAggregatorUtil.DISPLAY_STYLE_ABSTRACT_AND_IMAGE %>"><liferay-ui:message key="abstract-and-image" /></option>
						<option <%= (displayStyle.equals(KBAggregatorUtil.DISPLAY_STYLE_BODY)) ? "selected" : "" %> value="<%= KBAggregatorUtil.DISPLAY_STYLE_BODY %>"><liferay-ui:message key="body" /></option>
						<option <%= (displayStyle.equals(KBAggregatorUtil.DISPLAY_STYLE_BODY_AND_IMAGE)) ? "selected" : "" %> value="<%= KBAggregatorUtil.DISPLAY_STYLE_BODY_AND_IMAGE %>"><liferay-ui:message key="body-and-image" /></option>
						<option <%= (displayStyle.equals(KBAggregatorUtil.DISPLAY_STYLE_QUOTE)) ? "selected" : "" %> value="<%= KBAggregatorUtil.DISPLAY_STYLE_QUOTE %>"><liferay-ui:message key="quote" /></option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="maximum-items-to-display" />
				</td>
				<td>
					<select name="<portlet:namespace />maxItems">
						<option <%= maxItems == KBAggregatorUtil.MAX_ITEMS_10 ? "selected" : "" %> value="<%= KBAggregatorUtil.MAX_ITEMS_10 %>"><%= KBAggregatorUtil.MAX_ITEMS_10 %></option>
						<option <%= maxItems == KBAggregatorUtil.MAX_ITEMS_20 ? "selected" : "" %> value="<%= KBAggregatorUtil.MAX_ITEMS_20 %>"><%= KBAggregatorUtil.MAX_ITEMS_20 %></option>
						<option <%= maxItems == KBAggregatorUtil.MAX_ITEMS_30 ? "selected" : "" %> value="<%= KBAggregatorUtil.MAX_ITEMS_30 %>"><%= KBAggregatorUtil.MAX_ITEMS_30 %></option>
						<option <%= maxItems == KBAggregatorUtil.MAX_ITEMS_50 ? "selected" : "" %> value="<%= KBAggregatorUtil.MAX_ITEMS_50 %>"><%= KBAggregatorUtil.MAX_ITEMS_50 %></option>
						<option <%= maxItems == KBAggregatorUtil.MAX_ITEMS_100 ? "selected" : "" %> value="<%= KBAggregatorUtil.MAX_ITEMS_100 %>"><%= KBAggregatorUtil.MAX_ITEMS_100 %></option>
					</select>
				</td>
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
					<liferay-ui:message key="rss-type-s" />
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

<script type="text/javascript">
	if (<%= tabs2.equals("display-settings") && (groupId > 0) %>) {
		document.<portlet:namespace />fm.<portlet:namespace />companyId.value = <%= company.getCompanyId() %>;
		document.<portlet:namespace />fm.<portlet:namespace />groupId.value = 0;

		document.getElementById("<portlet:namespace />companyName").style.display = "none";
		document.getElementById("<portlet:namespace />groupName").style.display = "";

		document.<portlet:namespace />fm['<portlet:namespace />scope'].selectedIndex = 1;

	}
	else if (<%= tabs2.equals("display-settings") %>) {
		document.<portlet:namespace />fm.<portlet:namespace />companyId.value = 0;
		document.<portlet:namespace />fm.<portlet:namespace />groupId.value = <%= group.getGroupId() %>;

		document.getElementById("<portlet:namespace />companyName").style.display = "";
		document.getElementById("<portlet:namespace />groupName").style.display = "none";

		document.<portlet:namespace />fm['<portlet:namespace />scope'].selectedIndex = 0;
	}
</script>