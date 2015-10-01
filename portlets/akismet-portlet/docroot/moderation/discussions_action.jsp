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

<%
portletURL.setParameter("tabs1", "discussions");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBMessage mbMessage = (MBMessage)row.getObject();
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">

	<%
	ExpandoBridge expandoBridge = mbMessage.getExpandoBridge();

	String contentURL = (String)expandoBridge.getAttribute("akismetContentURL", false);
	%>

	<c:if test="<%= Validator.isNotNull(contentURL) %>">
		<liferay-ui:icon iconCssClass="icon-search" message="view-in-context" target="_blank" url="<%= String.valueOf(contentURL) %>" />
	</c:if>

	<portlet:actionURL name="markNotSpamMBMessages" var="markAsHamURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="notSpamMBMessageIds" value="<%= String.valueOf(mbMessage.getMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon iconCssClass="icon-envelope-alt" message="not-spam" url="<%= markAsHamURL %>" />

	<portlet:actionURL name="deleteDiscussionMBMessages" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="deleteMBMessageIds" value="<%= String.valueOf(mbMessage.getMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>