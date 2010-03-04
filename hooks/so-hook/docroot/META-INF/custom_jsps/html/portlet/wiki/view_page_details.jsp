<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

int numOfVersions = WikiPageLocalServiceUtil.getPagesCount(wikiPage.getNodeId(), wikiPage.getTitle());
WikiPage initialPage = (WikiPage)WikiPageLocalServiceUtil.getPages(wikiPage.getNodeId(), wikiPage.getTitle(), numOfVersions - 1, numOfVersions).get(0);

User initialPageUser = UserLocalServiceUtil.getUser(initialPage.getUserId());
User wikiPageUser = UserLocalServiceUtil.getUser(wikiPage.getUserId());
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/wiki/view_page_details.portal.jsp" />
</liferay-util:buffer>

<%
String[] htmlFragments = StringUtil.split(html, "<th>");

for (String htmlFragment : htmlFragments) {
	int x = htmlFragment.indexOf("</th>");
%>

	<c:choose>
		<c:when test="<%= x == -1 %>">
			<%= htmlFragment %>
		</c:when>
		<c:otherwise>

			<%
			String header = htmlFragment.substring(0, x).trim();

			htmlFragment = "<th>" + htmlFragment;
			%>

			<c:choose>
				<c:when test='<%= header.equals(LanguageUtil.get(pageContext, "created-by")) && Validator.isNotNull(initialPageUser.getDisplayURL(themeDisplay)) %>'>

					<%
					int y = htmlFragment.indexOf("<td>");

					String oldSub = initialPage.getUserName();
					String newSub = "<a href=\"" + initialPageUser.getDisplayURL(themeDisplay) + "\">" + initialPage.getUserName() + "</a>";
					%>

					<%= htmlFragment.substring(0, y + 4) + StringUtil.replaceFirst(htmlFragment.substring(y + 4), oldSub, newSub) %>
				</c:when>
				<c:when test='<%= header.equals(LanguageUtil.get(pageContext, "last-changed-by")) && Validator.isNotNull(wikiPageUser.getDisplayURL(themeDisplay)) %>'>

					<%
					int y = htmlFragment.indexOf("<td>");

					String oldSub = wikiPage.getUserName();
					String newSub = "<a href=\"" + wikiPageUser.getDisplayURL(themeDisplay) + "\">" + wikiPage.getUserName() + "</a>";
					%>

					<%= htmlFragment.substring(0, y + 4) + StringUtil.replaceFirst(htmlFragment.substring(y + 4), oldSub, newSub) %>
				</c:when>
				<c:otherwise>
					<%= htmlFragment %>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

<%
}
%>