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

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<%
String navItem = ParamUtil.getString(request, "navItem", "viewNewFeedback");
%>

<aui:nav-bar>
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="viewNewFeedbackURL">
			<portlet:param name="mvcPath" value="/admin/view_feedback.jsp" />
			<portlet:param name="navItem" value="viewNewFeedback" />
		</portlet:renderURL>

		<%
		int newKBCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(scopeGroupId, KBCommentConstants.STATUS_NEW);
		String newKBCommentsLabel = String.format("%s (%s)", LanguageUtil.get(pageContext, "new"), newKBCommentsCount);
		%>

		<aui:nav-item
			href="<%= viewNewFeedbackURL %>"
			label="<%= newKBCommentsLabel %>"
			selected='<%= navItem.equals("viewNewFeedback") %>'
		/>

		<portlet:renderURL var="viewInProgressFeedbackURL">
			<portlet:param name="mvcPath" value="/admin/view_feedback.jsp" />
			<portlet:param name="navItem" value="viewInProgressFeedback" />
		</portlet:renderURL>

		<%
		int inProgressKBCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(scopeGroupId, KBCommentConstants.STATUS_IN_PROGRESS);
		String inProgressKBCommentsLabel = String.format("%s (%s)", LanguageUtil.get(pageContext, "in-progress"), inProgressKBCommentsCount);
		%>

		<aui:nav-item
			href="<%= viewInProgressFeedbackURL %>"
			label="<%= inProgressKBCommentsLabel %>"
			selected='<%= navItem.equals("viewInProgressFeedback") %>'
		/>

		<portlet:renderURL var="viewCompletedFeedbackURL">
			<portlet:param name="mvcPath" value="/admin/view_feedback.jsp" />
			<portlet:param name="navItem" value="viewCompletedFeedback" />
		</portlet:renderURL>

		<%
		int completedKBCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(scopeGroupId, KBCommentConstants.STATUS_COMPLETED);
		String completedLabel = String.format("%s (%s)", LanguageUtil.get(pageContext, "completed"), completedKBCommentsCount);
		%>

		<aui:nav-item
			href="<%= viewCompletedFeedbackURL %>"
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