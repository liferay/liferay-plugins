<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/blogs/init.jsp" %>

<div class="sidebar">
	<div class="search">
		<input class="search-input" id="<portlet:namespace />keywords" name="<portlet:namespace />keywords" type="text" />

		<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	<%
	List<BlogsEntry> results = BlogsEntryLocalServiceUtil.getGroupEntries(scopeGroupId, false, 0, 5);
	%>

	<c:choose>
		<c:when test="<%= results.size() > 0 %>">
			<h2><liferay-ui:message key="recent-entries" /></h2>

			<div class="recent-entries-list">
				<ul class="disc">

					<%
					for (BlogsEntry entry : results) {
						String title = entry.getTitle();
						String author = PortalUtil.getUserName(entry.getUserId(), entry.getUserName());
					%>

						<portlet:renderURL var="viewEntryURL">
							<portlet:param name="struts_action" value="/blogs/view_entry" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
						</portlet:renderURL>

						<li>
							<a href="<%= viewEntryURL %>"><%= title %></a> <span class="author"><%= author %></span>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<br />
		</c:otherwise>
	</c:choose>

	<%
	String rssURL = PortalUtil.getLayoutURL(layout, themeDisplay) + "/-/blogs/rss";

	if (Validator.isNotNull(rssURLParams)) {
		rssURL += "?" + rssURLParams;
	}
	%>

	<div class="subscribe">
		<liferay-ui:icon image="rss" message="subscribe-to-this-blog" url="<%= rssURL %>" target="_blank" label="<%= true %>" />
	</div>
</div>