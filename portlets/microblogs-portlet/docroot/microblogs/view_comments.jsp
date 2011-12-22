<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "timeline");

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

long receiverMicroblogsEntryId = ParamUtil.getLong(request, "receiverMicroblogsEntryId");

List<MicroblogsEntry> microblogsEntries = MicroblogsEntryLocalServiceUtil.getReceiverMicroblogsEntryMicroblogsEntries(MicroblogsEntryConstants.TYPE_REPLY, receiverMicroblogsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new EntryCreateDateComparator(true));

request.setAttribute(WebKeys.MICROBLOGS_ENTRIES, microblogsEntries);

PortletURL microblogsEntriesURL = renderResponse.createRenderURL();

microblogsEntriesURL.setWindowState(WindowState.NORMAL);

microblogsEntriesURL.setParameter("jspPage", "/microblogs/view.jsp");
microblogsEntriesURL.setParameter("tabs1", tabs1);
microblogsEntriesURL.setParameter("cur", String.valueOf(cur));

request.setAttribute(WebKeys.MICROBLOGS_ENTRIES_URL, microblogsEntriesURL);
%>

<div class="comments-container-content">
	<c:if test="<%= !microblogsEntries.isEmpty() %>">
		<liferay-util:include page="/microblogs/view_microblogs_entries.jsp" servletContext="<%= application %>" />
	</c:if>

	<%
	request.setAttribute("view_comments.jsp-receiverMicroblogsEntryId", receiverMicroblogsEntryId);
	request.setAttribute("view_comments.jsp-comment", "true");
	%>

	<liferay-util:include page="/microblogs/edit_microblogs_entry.jsp" servletContext="<%= application %>" />
</div>