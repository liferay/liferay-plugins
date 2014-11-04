<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "timeline");

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

long parentMicroblogsEntryId = ParamUtil.getLong(request, "parentMicroblogsEntryId");

List<MicroblogsEntry> microblogsEntries = MicroblogsEntryLocalServiceUtil.getParentMicroblogsEntryMicroblogsEntries(MicroblogsEntryConstants.TYPE_REPLY, parentMicroblogsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new EntryCreateDateComparator(true));

request.setAttribute(WebKeys.MICROBLOGS_ENTRIES, microblogsEntries);

PortletURL microblogsEntriesURL = renderResponse.createRenderURL();

microblogsEntriesURL.setWindowState(WindowState.NORMAL);

microblogsEntriesURL.setParameter("mvcPath", "/microblogs/view.jsp");
microblogsEntriesURL.setParameter("tabs1", tabs1);
microblogsEntriesURL.setParameter("cur", String.valueOf(cur));

request.setAttribute(WebKeys.MICROBLOGS_ENTRIES_URL, microblogsEntriesURL);
%>

<div class="comments-container-content">
	<c:if test="<%= !microblogsEntries.isEmpty() %>">
		<liferay-util:include page="/microblogs/view_microblogs_entries.jsp" servletContext="<%= application %>" />
	</c:if>

	<%
	request.setAttribute("view_comments.jsp-parentMicroblogsEntryId", parentMicroblogsEntryId);
	request.setAttribute("view_comments.jsp-comment", "true");
	%>

	<liferay-util:include page="/microblogs/edit_microblogs_entry.jsp" servletContext="<%= application %>" />
</div>