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
MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">

	<%
	long folderId = ParamUtil.getLong(request, "folderId");
	int messageNumber = ParamUtil.getInteger(request, "messageNumber");
	int messagesPerPage = ParamUtil.getInteger(request, "messagesPerPage", 25);
	String orderByField = ParamUtil.getString(request, "orderByField");
	String orderByType = ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessageDisplay messageDisplay = mailManager.getMessageDisplay(folderId, messageNumber, orderByField, orderByType, keywords);

	Message message = messageDisplay.getMessage();
	int messageCount = messageDisplay.getMessageCount();

	if (!message.hasFlag(MailConstants.FLAG_SEEN)) {
		mailManager.markAsRead(message.getAccountId(), folderId, message.getMessageId());
	}

	int pageNumber = (int)(Math.ceil(messageNumber / (double)messagesPerPage));
	%>

	<aui:layout>
		<aui:column>

			<%
			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			String folderName = folder.getDisplayName();

			if (Validator.isNotNull(keywords)) {
				folderName = LanguageUtil.get(pageContext, "search-results");
			}
			%>

			<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber %>" href="javascript:;" label='<%= LanguageUtil.format(pageContext, "back-to-x", folderName) %>' />
		</aui:column>
		<aui:column cssClass="compose-message-container">
			<aui:button cssClass="compose-message" data-messageType="reply" data-replyMessageId="<%= message.getMessageId() %>" value="reply" />

			<aui:button cssClass="compose-message" data-messageType="reply-all" data-replyMessageId="<%= message.getMessageId() %>" value="reply-all" />

			<aui:button cssClass="compose-message" data-messageType="forward" data-replyMessageId="<%= message.getMessageId() %>" value="forward" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="delete-message" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber %>" value="delete" />
		</aui:column>
		<aui:column cssClass="message-count">
			<c:if test="<%= messageNumber > 1 %>">
				<aui:a cssClass="message-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageNumber="<%= messageNumber - 1 %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" href="javascript:;">&lt; <liferay-ui:message key="newer" /></aui:a>
			</c:if>

			<liferay-ui:message arguments='<%= new Object[] {messageNumber, messageCount} %>' key="x-of-x" />

			<c:if test="<%= messageNumber < messageCount %>">
				<aui:a cssClass="message-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageNumber="<%= messageNumber + 1 %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" href="javascript:;"><liferay-ui:message key="older" /> &gt;</aui:a>
			</c:if>
		</aui:column>
	</aui:layout>

	<c:choose>
		<c:when test="<%= Validator.isNotNull(message.getSubject()) %>">
			<h3 class="subject"><%= HtmlUtil.escape(message.getSubject()) %></h3>
		</c:when>
		<c:otherwise>
			<h3 class="no-subject"><liferay-ui:message key="no-subject" /></h3>
		</c:otherwise>
	</c:choose>

	<div class="message-header">
		<table>
		<tr>
			<td class="message-hearder-label">
				<liferay-ui:message key="from" />
			</td>
			<td>
				<%= message.getSender() %>
			</td>
		</tr>
		<tr>
			<td class="message-hearder-label">
				<liferay-ui:message key="to" />
			</td>
			<td>
				<%= message.getTo() %>
			</td>
		</tr>

		<c:if test="<%= Validator.isNotNull(message.getCc()) %>">
			<tr>
				<td class="message-hearder-label">
					<liferay-ui:message key="cc" />
				</td>
				<td>
					<%= message.getCc() %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td class="message-hearder-label">
				<liferay-ui:message key="date" />
			</td>
			<td>
				<%= dateFormatDateTime.format(message.getSentDate()) %>
			</td>
		</tr>
		</table>
	</div>

	<div id="messageContentContainer">
		<c:if test="<%= Validator.isNotNull(message.getBody()) %>">
			<liferay-util:include page="/view_message_content.jsp" servletContext="<%= application %>" />
		</c:if>
	</div>

	<aui:button-row>
		<aui:button cssClass="compose-message" data-messageType="reply" data-replyMessageId="<%= message.getMessageId() %>" value="reply" />

		<aui:button cssClass="compose-message" data-messageType="reply-all" data-replyMessageId="<%= message.getMessageId() %>" value="reply-all" />

		<aui:button cssClass="compose-message" data-messageType="forward" data-replyMessageId="<%= message.getMessageId() %>" value="forward" />
	</aui:button-row>

	<c:if test="<%= Validator.isNull(message.getBody()) %>">
		<aui:script>
			var A = AUI();

			A.one('#messageContentContainer').plug(
				A.Plugin.IO,
				{
					data: {messageId: <%= message.getMessageId() %>},
					method: 'POST',
					uri: themeDisplay.getLayoutURL() + '/-/mail/view_message_content'
				}
			);
		</aui:script>
	</c:if>
</c:if>