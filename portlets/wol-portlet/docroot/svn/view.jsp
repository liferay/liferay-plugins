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
PortletURL portletURL = renderResponse.createRenderURL();

String svnUrl = SVNConstants.PORTAL_TRUNK_URL;
String svnUserId = "brianchandotcom";

SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(svnUrl);

List headerNames = new ArrayList();

headerNames.add("folder");
headerNames.add("num-of-folders");
headerNames.add("num-of-entries");
headerNames.add(StringPool.BLANK);

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

	// Revision number

	row.addText(String.valueOf(svnRevision.getRevisionNumber()), rowHREF);

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />