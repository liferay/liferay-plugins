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
		String url = ParamUtil.getString(request, "url");
		boolean all = ParamUtil.getBoolean(request, "all");

		String svnURL = "http://lportal.svn.sourceforge.net/svnroot/lportal" + url;

		SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(svnURL);

  		List<SVNRevision> svnRevisions = null;

		if (all) {
			svnRevisions = SVNRevisionLocalServiceUtil.getSVNRevisions(svnRepository.getSvnRepositoryId(), 0, 100);
		}
		else {
			String svnUserId = ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "sfUserId", user2.getUserId(), StringPool.BLANK);

			svnRevisions = SVNRevisionLocalServiceUtil.getSVNRevisions(svnUserId, svnRepository.getSvnRepositoryId(), 0, 100);
		}

		String title = null;

		if (all) {
			title = LanguageUtil.format(pageContext, "all-commits-on-x", svnURL);
		}
		else {
		    title = LanguageUtil.format(pageContext, "x's-commits-on-x", new Object[] {user2.getFullName(), svnURL});
		}

		SyndFeed syndFeed = new SyndFeedImpl();

		syndFeed.setFeedType(RSSUtil.DEFAULT_FEED_TYPE);
		syndFeed.setLink(PortalUtil.getPortalURL(request) + PortalUtil.getCurrentURL(request));
		syndFeed.setTitle(title);
		syndFeed.setDescription(title);

		List<SyndEntry> entries = new ArrayList<SyndEntry>();

		syndFeed.setEntries(entries);

		for (SVNRevision svnRevision : svnRevisions) {
			String link = "http://lportal.svn.sourceforge.net/viewvc/lportal?view=rev&revision=" + svnRevision.getRevisionNumber();

			SyndEntry syndEntry = new SyndEntryImpl();

		    syndEntry.setAuthor(user2.getFullName());
		    syndEntry.setTitle(LanguageUtil.get(pageContext, "revision") + StringPool.SPACE + svnRevision.getRevisionNumber());
		    syndEntry.setLink(link);
		    syndEntry.setPublishedDate(svnRevision.getCreateDate());

			Object[] jiraIssueAndComments = svnRevision.getJIRAIssueAndComments();

			JIRAIssue jiraIssue = null;
			String comments = svnRevision.getComments();

			if (jiraIssueAndComments != null) {
				jiraIssue = (JIRAIssue)jiraIssueAndComments[0];
				comments = (String)jiraIssueAndComments[1];

				StringMaker sm = new StringMaker();

				sm.append(comments);
				sm.append("<br />");

				sm.append(jiraIssue.getSummary());
				sm.append("<br />");

				sm.append("<a href=\"");
				sm.append(link);
				sm.append("\"><img border=\"0\" src=\"");
				sm.append(request.getContextPath());
				sm.append("/svn/icon.png\" />SVN</a><br />");

				sm.append("<a href=\"http://support.liferay.com/browse/");
				sm.append(jiraIssue.getKey());
				sm.append("\"><img border=\"0\" src=\"");
				sm.append(request.getContextPath());
				sm.append("/jira/icon.png\" />JIRA</a>");

				comments = sm.toString();
			}

		    SyndContent syndContent = new SyndContentImpl();

			syndContent.setType(RSSUtil.DEFAULT_ENTRY_TYPE);
			syndContent.setValue(comments);

			syndEntry.setDescription(syndContent);

			entries.add(syndEntry);
		}

		String feedXML = StringPool.BLANK;

		try {
			feedXML = RSSUtil.export(syndFeed);
		}
		catch (Exception e) {
		    _log.error(e, e);
		}

		response.setContentType(ContentTypes.TEXT_XML_UTF8);
		%>

		<%= feedXML %>
	</c:when>
	<c:otherwise>

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
						<h4 class="project-title">
							<a href="http://lportal.svn.sourceforge.net/svnroot/lportal" target="_blank">Liferay Portal</a>
						</h4>

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
									<h5 class="project-section">
										<%= svnRepository.getShortURL() %>
									</h5>

									<table class="lfr-table">
									<tr>
										<td>
											<a href="<%= userRevisionsURL %>">

											<c:choose>
												<c:when test="<%= user2.getUserId() == themeDisplay.getUserId() %>">
													<liferay-ui:message key="my-commits" />
												</c:when>
												<c:otherwise>
													<%= LanguageUtil.format(pageContext, "x's-commits", user2.getFirstName()) %>
												</c:otherwise>
											</c:choose>

											</a>
										</td>
										<td>
											<%= SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnUserId, svnRepository.getSvnRepositoryId()) %>
										</td>
										<td>
											<liferay-ui:icon
												image="rss"
												url='<%= PortalUtil.getPathFriendlyURLPublic() + StringPool.SLASH + user2.getScreenName() + "/profile/-/svn/rss/user" + svnRepository.getShortURL() %>'
											/>
										</td>
									</tr>
									<tr>
										<td>
											<a href="<%= allRevisionsURL %>"><liferay-ui:message key="all-commits" /></a>
										</td>
										<td>
											<%= SVNRevisionLocalServiceUtil.getSVNRevisionsCount(svnRepository.getSvnRepositoryId()) %>
										</td>
										<td>
											<liferay-ui:icon
												image="rss"
												url='<%= PortalUtil.getPathFriendlyURLPublic() + StringPool.SLASH + user2.getScreenName() + "/profile/-/svn/rss/all" + svnRepository.getShortURL() %>'
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

							Object[] jiraIssueAndComments = svnRevision.getJIRAIssueAndComments();

							JIRAIssue jiraIssue = null;
							String comments = svnRevision.getComments();

							if (jiraIssueAndComments != null) {
								jiraIssue = (JIRAIssue)jiraIssueAndComments[0];
								comments = (String)jiraIssueAndComments[1];
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

<%!
private static Log _log = LogFactoryUtil.getLog("svn.view.jsp");
%>