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
portletURL.setParameter("tabs1", "message-boards");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBMessage message = (MBMessage)row.getObject();

long messageBoardsPlid = PortalUtil.getPlidFromPortletId(message.getGroupId(), PortletKeys.MESSAGE_BOARDS);
%>

<liferay-ui:icon-menu>
	<liferay-portlet:renderURL plid="<%= messageBoardsPlid %>" portletName="<%= PortletKeys.MESSAGE_BOARDS %>" var="viewURL">
		<portlet:param name="struts_action" value="/message_boards/view_message" />
		<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon image="page" message="view-in-context" target="_blank" url="<%= viewURL %>" />

	<portlet:actionURL name="markNotSpamMBMessages" var="markAsHamURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="notSpamMBMessageIds" value="<%= String.valueOf(message.getMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="../mail/compose" message="not-spam" url="<%= markAsHamURL %>" />

	<portlet:actionURL name="deleteMBMessages" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="deleteMBMessageIds" value="<%= String.valueOf(message.getMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>