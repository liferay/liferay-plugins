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

<c:choose>
	<c:when test="<%= themeDisplay.isStateExclusive() %>">

		<%
		response.setContentType(ContentTypes.TEXT_XML_UTF8);

		String url = ParamUtil.getString(request, "url");
		boolean all = ParamUtil.getBoolean(request, "all");
		%>

		hello
	</c:when>
	<c:otherwise>
<style type="text/css" media="screen">
	.wol-portlet-svn .project-title {
		margin-bottom: 0.5em;
	}
	
	.wol-portlet-svn .project-section {
		font-size: 1em;
		margin-bottom: 0.2em;
	}

	.wol-portlet-svn .project-details .lfr-table {
		margin: 0 0 1em;
		width: 100%;
	}
	
	.wol-portlet-svn .project-details .lfr-table td {
		padding-top: 2px;
	}
</style>
		<%
		for (String url : SVNConstants.SVN_URLS) {
			try {
				SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(url);
			}
			catch (NoSuchSVNRepositoryException nssvnre) {
		%>

				<div class="portlet-msg-error">
					The SVN repositories have not been initialized. This should not be the case except the first time this portlet is deployed when the SVN tables have not been populated. To populate the SVN tables, make sure the property "svn.synchronization.interval" is set to a value greater than 0.
				</div>

		<%
				return;
			}
		}
		%>

		<%
		String svnUserId = ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "sfUserId", user2.getUserId(), StringPool.BLANK);
		%>

		<c:choose>
			<c:when test="<%= Validator.isNotNull(svnUserId) %>">
				<c:choose>
					<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">
				<%
					String svnURL = ParamUtil.getString(request, "url");
				 %>
				<h4 class="project-title"><a href="<%= svnURL %>">Liferay Portal</a></h4>
						<%
						for (String url : SVNConstants.SVN_URLS) {
							SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(url);
						%>

							<c:if test="<%= SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId, svnRepository.getSvnRepositoryId()) > 0 %>">
								<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="userRevisionsURL">
									<portlet:param name="url" value="<%= url %>" />
								</portlet:renderURL>

								<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="allRevisionsURL">
									<portlet:param name="url" value="<%= url %>" />
									<portlet:param name="all" value="true" />
								</portlet:renderURL>

						<div class="project-details">
							<h5 class="project-section"><%= svnRepository.getShortURL() %></h5>

							<table class="lfr-table">
								<tr>
									<td>
										<a href="<%= userRevisionsURL %>">My Commits</a>
									</td>
									<td>
										<%= SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId, svnRepository.getSvnRepositoryId()) %> 
									</td>
									<td>
										<liferay-ui:icon
											image="rss"
											url=".."
										/>
									</td>
								</tr>
								<tr>
									<td>
										<a href="<%= allRevisionsURL %>">All Commits</a> 
									</td>
									<td>
										<%= SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnRepository.getSvnRepositoryId()) %>
									</td>
									<td>
										<liferay-ui:icon
										image="rss"
										url=".."
										/>
									</td>
								</tr>
							</table>
						</div>
					</c:if>
						<%
						}
						%>

					</c:when>
					<c:otherwise>

						<%
						String url = ParamUtil.getString(request, "url");
						boolean all = ParamUtil.getBoolean(request, "all");

						PortletURL portletURL = renderResponse.createRenderURL();

						portletURL.setParameter("url", url);
						portletURL.setParameter("all", String.valueOf(all));

						SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(url);

						List headerNames = new ArrayList();

						headerNames.add("revision");
						headerNames.add("comments");
						headerNames.add("date-and-time");
						headerNames.add(StringPool.BLANK);

						SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

						int total = 0;

						if (all) {
							total = SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnRepository.getSvnRepositoryId());
						}
						else {
							total = SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId, svnRepository.getSvnRepositoryId());
						}

						searchContainer.setTotal(total);

						List<SVNRevision> results = null;

						if (all) {
							results = SVNRevisionLocalServiceUtil.getSVNRevisions(svnRepository.getSvnRepositoryId(), searchContainer.getStart(), searchContainer.getEnd());
						}
						else {
							results = SVNRevisionLocalServiceUtil.getSVNRevisions(svnUserId, svnRepository.getSvnRepositoryId(), searchContainer.getStart(), searchContainer.getEnd());
						}

						searchContainer.setResults(results);

						List resultRows = searchContainer.getResultRows();

						for (int i = 0; i < results.size(); i++) {
							SVNRevision svnRevision = results.get(i);

							String rowHREF = "http://lportal.svn.sourceforge.net/viewvc/lportal?view=rev&revision=" + svnRevision.getRevisionNumber();

							JIRAIssue jiraIssue = null;

							String comments = svnRevision.getComments();

							if (comments.startsWith("LEP-")) {
								comments = StringUtil.replace(comments, "\n", " ");

								int pos = comments.indexOf(" ");

								if (pos == -1) {
									pos = comments.length();
								}
								else {
									comments = comments.substring(0, pos);
								}

								String key = comments.substring(4, pos);

								if (Validator.isNumber(key)) {
									try {
										jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue("LEP-" + key);
									}
									catch (Exception e) {
									}
								}
							}

							ResultRow row = new ResultRow(new Object[] {svnRevision, rowHREF, jiraIssue}, svnRevision.getSvnRevisionId(), i);

							TextSearchEntry rowTextEntry = new TextSearchEntry(SearchEntry.DEFAULT_ALIGN, SearchEntry.DEFAULT_VALIGN, String.valueOf(svnRevision.getRevisionNumber()), rowHREF, "_blank", String.valueOf(svnRevision.getRevisionNumber()));

							// Revision number

							row.addText(rowTextEntry);

							// Comments

							rowTextEntry = (TextSearchEntry)rowTextEntry.clone();

							if (jiraIssue == null) {
								rowTextEntry.setName(comments);
							}
							else {
								rowTextEntry.setName(comments + "<br />" + jiraIssue.getSummary());
							}

							row.addText(rowTextEntry);

							// Date

							rowTextEntry = (TextSearchEntry)rowTextEntry.clone();

							rowTextEntry.setName("<nobr>" + dateFormatDateTime.format(svnRevision.getCreateDate()) + "</nobr>");

							row.addText(rowTextEntry);

							// Action

							row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/svn/revision_action.jsp", application, request, response);

							// Add result row

							resultRows.add(row);
						}
						%>

						<h4>
							<c:choose>
								<c:when test="<%= all %>">
									<%= LanguageUtil.format(pageContext, "all-commits-on-x", "<a href=\"" + url + "\" target=\"_blank\">" + url + "</a>") %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(pageContext, "x's-commits-on-x", new Object[] {user2.getFirstName(), "<a href=\"" + url + "\" target=\"_blank\">" + url + "</a>"}) %>
								</c:otherwise>
							</c:choose>
						</h4>

						<br />

						<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="<%= UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
						<a href="<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="1_WAR_wolportlet" />">Set your SourceForge login.</a>
					</c:when>
					<c:otherwise>
						<%= LanguageUtil.format(pageContext, (user2.isMale() ? "x-has-not-configured-his-sourceforge-login" : "x-has-not-configured-her-sourceforge-login"), user2.getFullName()) %>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>