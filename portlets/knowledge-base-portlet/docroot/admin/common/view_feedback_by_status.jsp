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
KBFeedbackListDisplayContext kbFeedbackListDisplayContext = (KBFeedbackListDisplayContext)request.getAttribute(WebKeys.KB_FEEDBACK_LIST_DISPLAY_CONTEXT);

String navItem = kbFeedbackListDisplayContext.getSelectedNavItem();
%>

<aui:nav-bar>
	<aui:nav cssClass="navbar-nav">

		<%
		int newKBCommentsCount = kbFeedbackListDisplayContext.getNewKBCommentsCount();

		String newKBCommentsLabel = String.format("%s (%s)", LanguageUtil.get(request, "new"), newKBCommentsCount);
		%>

		<aui:nav-item
			href='<%= kbFeedbackListDisplayContext.getViewFeedbackURL(renderResponse, "viewNewFeedback") %>'
			label="<%= newKBCommentsLabel %>"
			selected='<%= navItem.equals("viewNewFeedback") %>'
		/>

		<%
		int inProgressKBCommentsCount = kbFeedbackListDisplayContext.getInProgressKBCommentsCount();

		String inProgressKBCommentsLabel = String.format("%s (%s)", LanguageUtil.get(request, "in-progress"), inProgressKBCommentsCount);
		%>

		<aui:nav-item
			href='<%= kbFeedbackListDisplayContext.getViewFeedbackURL(renderResponse, "viewInProgressFeedback") %>'
			label="<%= inProgressKBCommentsLabel %>"
			selected='<%= navItem.equals("viewInProgressFeedback") %>'
		/>

		<%
		int completedKBCommentsCount = kbFeedbackListDisplayContext.getCompletedKBCommentsCount();

		String completedLabel = String.format("%s (%s)", LanguageUtil.get(request, "resolved"), completedKBCommentsCount);
		%>

		<aui:nav-item
			href='<%= kbFeedbackListDisplayContext.getViewFeedbackURL(renderResponse, "viewCompletedFeedback") %>'
			label="<%= completedLabel %>"
			selected='<%= navItem.equals("viewCompletedFeedback") %>'
		/>
	</aui:nav>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= navItem.equals("viewInProgressFeedback") %>'>
		<liferay-util:include page="/admin/view_in_progress_feedback.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= navItem.equals("viewNewFeedback") %>'>
		<liferay-util:include page="/admin/view_new_feedback.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/admin/view_completed_feedback.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>