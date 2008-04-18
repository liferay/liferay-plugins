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

<%@ include file="/init.jsp" %>

<%
String svnUserId = "brianchandotcom";
%>

<c:choose>
	<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">

		<%
		int commitsCount = SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId);
		Date firstCommitDate = SVNRevisionLocalServiceUtil.getFirstSVNRevision(svnUserId).getCreateDate();
		Date lastCommitDate = SVNRevisionLocalServiceUtil.getLastSVNRevision(svnUserId).getCreateDate();
		%>

		<div>
			<%= user.getFullName() %> is a committer and has made over <b><%= numberFormat.format(commitsCount) %></b> commits. His first commit was on <%= dateFormatDate.format(firstCommitDate) %> and his last commit was on <%= dateFormatDate.format(lastCommitDate) %>.
		</div>

		<%
		for (String url : SVNConstants.URLS) {
			SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(url);

			String shortURL = url.substring(url.indexOf("/lportal/") + 8);
		%>

			<c:if test="<%= SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId, svnRepository.getSvnRepositoryId()) > 0 %>">
				<br />

				See his activity on <a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="url" value="<%= url %>" /></portlet:renderURL>"><%= shortURL %></a>.
			</c:if>

		<%
		}
		%>
	</c:when>
	<c:otherwise>

		<%
		String url = ParamUtil.getString(request, "url");

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("url", url);

		SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(url);

		List headerNames = new ArrayList();

		headerNames.add("revision");
		headerNames.add("comments");
		headerNames.add("date-and-time");

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

		int total = SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId, svnRepository.getSvnRepositoryId());

		searchContainer.setTotal(total);

		List<SVNRevision> results = SVNRevisionLocalServiceUtil.getSVNRevisions(svnUserId, svnRepository.getSvnRepositoryId(), searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			SVNRevision svnRevision = results.get(i);

			ResultRow row = new ResultRow(svnRevision, svnRevision.getSvnRevisionId(), i);

			String rowHREF = "http://lportal.svn.sourceforge.net/viewvc/lportal?view=rev&revision=" + svnRevision.getRevisionNumber();

			TextSearchEntry rowTextEntry = new TextSearchEntry(SearchEntry.DEFAULT_ALIGN, SearchEntry.DEFAULT_VALIGN, String.valueOf(svnRevision.getRevisionNumber()), rowHREF, "_blank", String.valueOf(svnRevision.getRevisionNumber()));

			// Revision number

			row.addText(rowTextEntry);

			// Comments

			rowTextEntry = (TextSearchEntry)rowTextEntry.clone();

			rowTextEntry.setName(svnRevision.getComments());

			row.addText(rowTextEntry);

			// Date

			rowTextEntry = (TextSearchEntry)rowTextEntry.clone();

			rowTextEntry.setName(dateFormatDateTime.format(svnRevision.getCreateDate()));

			row.addText(rowTextEntry);

			// Add result row

			resultRows.add(row);
		}
		%>

		<h4>
			<a href="<%= url %>" target="_blank"><%= url %></a>
		</h4>

		<br />

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</c:otherwise>
</c:choose>