<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= user2 != null %>">

	<%
	String url = ParamUtil.getString(request, "url");
	boolean all = ParamUtil.getBoolean(request, "all");

	String svnURL = "svn://svn.liferay.com/repos/public" + url;

	SVNRepository svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(svnURL);

	List<SVNRevision> svnRevisions = null;

	if (all) {
		svnRevisions = SVNRevisionLocalServiceUtil.getSVNRevisions(svnRepository.getSvnRepositoryId(), 0, 100);
	}
	else {
		String svnUserId = user2.getScreenName();

		svnRevisions = SVNRevisionLocalServiceUtil.getSVNRevisions(svnUserId, svnRepository.getSvnRepositoryId(), 0, 100);
	}

	String title = null;

	if (all) {
		title = LanguageUtil.format(request, "all-commits-on-x", svnURL, false);
	}
	else {
		title = LanguageUtil.format(request, "x's-commits-on-x", new Object[] {HtmlUtil.escape(user2.getFullName()), svnURL}, false);
	}

	SyndFeed syndFeed = new SyndFeedImpl();

	syndFeed.setFeedType(RSSUtil.FEED_TYPE_DEFAULT);
	syndFeed.setLink(currentURL);
	syndFeed.setTitle(title);
	syndFeed.setDescription(title);

	List<SyndEntry> entries = new ArrayList<SyndEntry>();

	syndFeed.setEntries(entries);

	for (SVNRevision svnRevision : svnRevisions) {
		String link = svnRevision.getWebRevisionNumberURL();

		SyndEntry syndEntry = new SyndEntryImpl();

		syndEntry.setAuthor(HtmlUtil.escape(user2.getFullName()));
		syndEntry.setTitle(LanguageUtil.get(request, "revision") + StringPool.SPACE + svnRevision.getRevisionNumber());
		syndEntry.setLink(link);
		syndEntry.setPublishedDate(svnRevision.getCreateDate());

		Object[] jiraIssueAndComments = svnRevision.getJIRAIssueAndComments();

		JIRAIssue jiraIssue = null;
		String comments = svnRevision.getComments();

		if (jiraIssueAndComments != null) {
			jiraIssue = (JIRAIssue)jiraIssueAndComments[0];
			comments = (String)jiraIssueAndComments[1];

			StringBuilder sb = new StringBuilder();

			sb.append(comments);
			sb.append("<br />");

			sb.append(HtmlUtil.escape(jiraIssue.getSummary()));
			sb.append("<br />");

			sb.append("<a href=\"");
			sb.append(link);
			sb.append("\"><img border=\"0\" src=\"");
			sb.append(PortalUtil.getPathContext(request));
			sb.append("/icons/svn.png\" />SVN</a><br />");

			sb.append("<a href=\"");
			sb.append(PortletPropsValues.JIRA_URL);
			sb.append("/browse/");
			sb.append(jiraIssue.getKey());
			sb.append("\"><img border=\"0\" src=\"");
			sb.append(PortalUtil.getPathContext(request));
			sb.append("/icons/jira.png\" />JIRA</a>");

			comments = sb.toString();
		}

		SyndContent syndContent = new SyndContentImpl();

		syndContent.setType(RSSUtil.ENTRY_TYPE_DEFAULT);
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
	%>

	<%= feedXML %>

</c:if>

<%!
private static Log _log = LogFactoryUtil.getLog("svn.view_feeds_jsp");
%>