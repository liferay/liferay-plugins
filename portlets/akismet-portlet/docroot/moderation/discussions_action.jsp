<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
portletURL.setParameter("tabs1", "discussions");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBMessage mbMessage = (MBMessage)row.getObject();

MBDiscussion mbDiscussion = MBDiscussionLocalServiceUtil.getThreadDiscussion(mbMessage.getThreadId());

long blogsPlid = PortalUtil.getPlidFromPortletId(mbMessage.getGroupId(), PortletKeys.BLOGS);
%>

<liferay-ui:icon-menu>
	<liferay-portlet:renderURL plid="<%= blogsPlid %>" portletName="<%= PortletKeys.BLOGS %>" varImpl="viewURL">
		<portlet:param name="struts_action" value="/blogs/view_entry" />
		<portlet:param name="entryId" value="<%= String.valueOf(mbDiscussion.getClassPK()) %>" />
	</liferay-portlet:renderURL>

	<%
	String className = PortalUtil.getClassName(mbDiscussion.getClassNameId());
	%>

	<c:if test="<%= className.equals(BlogsEntry.class.getName()) %>">
		<liferay-ui:icon image="page" message="view-in-context" target="_blank" url="<%= String.valueOf(viewURL) %>" />
	</c:if>

	<portlet:actionURL name="markNotSpamMBMessages" var="markAsHamURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="notSpamMBMessageIds" value="<%= String.valueOf(mbMessage.getMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="../mail/compose" message="not-spam" url="<%= markAsHamURL %>" />

	<portlet:actionURL name="deleteDiscussionMBMessages" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="deleteMBMessageIds" value="<%= String.valueOf(mbMessage.getMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>