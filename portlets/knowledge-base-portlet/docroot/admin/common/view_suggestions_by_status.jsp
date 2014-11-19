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

<%@ include file="/admin/init.jsp" %>

<%
KBSuggestionListDisplayContext kbSuggestionListDisplayContext = (KBSuggestionListDisplayContext)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_SUGGESTION_LIST_DISPLAY_CONTEXT);

String navItem = kbSuggestionListDisplayContext.getSelectedNavItem();
%>

<aui:nav-bar>
	<aui:nav cssClass="navbar-nav">

		<%
		int newKBCommentsCount = kbSuggestionListDisplayContext.getNewKBCommentsCount();

		String newKBCommentsLabel = String.format("%s (%s)", LanguageUtil.get(pageContext, "new"), newKBCommentsCount);
		%>

		<aui:nav-item
			href='<%= kbSuggestionListDisplayContext.getViewSuggestionURL(renderResponse, "viewNewSuggestions") %>'
			label="<%= newKBCommentsLabel %>"
			selected='<%= navItem.equals("viewNewSuggestions") %>'
		/>

		<%
		int inProgressKBCommentsCount = kbSuggestionListDisplayContext.getInProgressKBCommentsCount();

		String inProgressKBCommentsLabel = String.format("%s (%s)", LanguageUtil.get(pageContext, "in-progress"), inProgressKBCommentsCount);
		%>

		<aui:nav-item
			href='<%= kbSuggestionListDisplayContext.getViewSuggestionURL(renderResponse, "viewInProgressSuggestions") %>'
			label="<%= inProgressKBCommentsLabel %>"
			selected='<%= navItem.equals("viewInProgressSuggestions") %>'
		/>

		<%
		int completedKBCommentsCount = kbSuggestionListDisplayContext.getCompletedKBCommentsCount();

		String completedLabel = String.format("%s (%s)", LanguageUtil.get(pageContext, "resolved"), completedKBCommentsCount);
		%>

		<aui:nav-item
			href='<%= kbSuggestionListDisplayContext.getViewSuggestionURL(renderResponse, "viewCompletedSuggestions") %>'
			label="<%= completedLabel %>"
			selected='<%= navItem.equals("viewCompletedSuggestions") %>'
		/>
	</aui:nav>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= navItem.equals("viewInProgressSuggestions") %>'>
		<liferay-util:include page="/admin/view_in_progress_suggestions.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= navItem.equals("viewNewSuggestions") %>'>
		<liferay-util:include page="/admin/view_new_suggestions.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/admin/view_completed_suggestions.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>