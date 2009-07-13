<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
%>

<%@ include file="/html/portlet/search/init.jsp" %>

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), PortletKeys.CALENDAR);

String oldPortletTitle = PortalUtil.getPortletTitle(portlet, application, locale);
String newPortletTitle = LanguageUtil.get(pageContext, "events");

List<String> portletTitles = new ArrayList<String>();
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/search/search.portal.jsp" />
</liferay-util:buffer>

<%
int a = html.indexOf("<input type=\"button\" value=\"" + LanguageUtil.get(pageContext, "add-liferay-as-a-search-provider") + "\"");
int b = html.indexOf("/>", a);

if (a != -1) {
	html = StringUtil.remove(html, html.substring(a, b + 2), StringPool.BLANK);
}

int c = html.indexOf("<div class=\"search-msg\">");
int d = html.indexOf("</div>", c);
int e = html.indexOf("<div class=\"section-title\">");
%>

<c:choose>
	<c:when test="<%= c != -1 %>">

		<%
		String htmlFragment = html.substring(c + 24, d);

		htmlFragment = StringUtil.replaceFirst(htmlFragment, LanguageUtil.get(pageContext, "searched"), StringPool.BLANK).trim();
		htmlFragment = StringUtil.replaceFirst(htmlFragment, oldPortletTitle, newPortletTitle);

		portletTitles = ListUtil.toList(StringUtil.split(htmlFragment, StringPool.COMMA_AND_SPACE));
		portletTitles = ListUtil.sort(portletTitles, new StringComparator());
		%>

		<%= html.substring(0, c) %>

		<div class="search-msg">
			<liferay-ui:message key="searched" /> <%= StringUtil.merge(portletTitles, StringPool.COMMA_AND_SPACE) %>
		</div>

		<%= html.substring(d + 6, e) %>
	</c:when>
	<c:otherwise>
		<%= html.substring(0, e) %>
	</c:otherwise>
</c:choose>

<%
String[] htmlFragments = StringUtil.split(html.substring(e + 27), "<div class=\"section-title\">");

Map<String, String> map = new TreeMap<String, String>(new StringComparator());

for (int i = 0; i < htmlFragments.length; i++) {
	String htmlFragment = "<div class=\"section-title\">" + htmlFragments[i];

	String portletTitle = htmlFragment.substring(27, htmlFragment.indexOf(StringPool.OPEN_PARENTHESIS)).trim();

	if (portletTitle.equals(oldPortletTitle)) {
		htmlFragment = htmlFragment.substring(0, 27) + StringUtil.replaceFirst(htmlFragment.substring(27), oldPortletTitle, newPortletTitle);

		portletTitle = newPortletTitle;
	}

	int x = htmlFragment.indexOf("<div class=\"search-paginator-container\">");
%>

	<c:choose>
		<c:when test="<%= x != -1 %>">
			<%= htmlFragment %>
		</c:when>
		<c:otherwise>

			<%
			map.put(portletTitle, htmlFragment);
			%>

		</c:otherwise>
	</c:choose>

<%
}

Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();

while (itr.hasNext()) {
	Map.Entry<String, String> entry = itr.next();
%>

	<%= entry.getValue() %>

<%
}
%>

<%
String pageDescription = LanguageUtil.get(pageContext, "searched") + StringPool.SPACE + StringUtil.merge(portletTitles, StringPool.COMMA_AND_SPACE);

PortalUtil.setPageDescription(pageDescription, request);
%>