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

		<aui:nav-item
			href="<%= viewNewFeedbackURL %>"
			label="new"
			selected='<%= navItem.equals("viewNewFeedback") %>'
		/>

		<portlet:renderURL var="viewInProgressFeedbackURL">
			<portlet:param name="mvcPath" value="/admin/view_feedback.jsp" />
			<portlet:param name="navItem" value="viewInProgressFeedback" />
		</portlet:renderURL>

		<aui:nav-item
			href="<%= viewInProgressFeedbackURL %>"
			label="in-progress"
			selected='<%= navItem.equals("viewInProgressFeedback") %>'
		/>

		<portlet:renderURL var="viewCompletedFeedbackURL">
			<portlet:param name="mvcPath" value="/admin/view_feedback.jsp" />
			<portlet:param name="navItem" value="viewCompletedFeedback" />
		</portlet:renderURL>

		<aui:nav-item
			href="<%= viewCompletedFeedbackURL %>"
			label="completed"
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