<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<%
MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<%
	long folderId = ParamUtil.getLong(request, "folderId");
	String keywords = ParamUtil.getString(request, "keywords");
	long messageId = ParamUtil.getLong(request, "messageId");
	int messageNumber = ParamUtil.getInteger(request, "messageNumber");
	int messagesPerPage = 25; //ParamUtil.getInteger(request, "messagesPerPage");
	String orderByField = MailConstants.ORDER_BY_SENT_DATE; //ParamUtil.getString(request, "orderByField");
	String orderByType = "desc"; //ParamUtil.getString(request, "orderByType");

	MessageDisplay messageDisplay = mailManager.getMessageDisplay(folderId, keywords, messageNumber, orderByField, orderByType);

	Message message = messageDisplay.getMessage();
	int messageCount = messageDisplay.getMessageCount();

	String folderLink = "&lt; Back to ";

	if (Validator.isNull(keywords)) {
		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		folderLink += folder.getDisplayName();
	}
	else {
		folderLink += "Search Results";
	}

	int pageNumber = (int)(Math.ceil(messageNumber / (double) messagesPerPage));
	%>

	<aui:layout>
		<aui:column columnWidth="20">
			<aui:a cssClass="back-to-messages" folderId="<%= folderId %>" href="javascript:;" keywords="<%= keywords %>" label="<%= folderLink %>" pageNumber="<%= pageNumber %>" />
		</aui:column>
		<aui:column columnWidth="20">
			<aui:button cssClass="delete-message" folderId="<%= folderId %>" keywords="<%= keywords %>" messageId="<%= message.getMessageId() %>" pageNumber="<%= pageNumber %>" value="delete" />
		</aui:column>
		<aui:column columnWidth="40">
			&nbsp;
		</aui:column>
		<aui:column columnWidth="6">
			<c:if test="<%= messageNumber > 1 %>">
				<aui:a cssClass="message-link" folderId="<%= folderId %>" href="javascript:;" keywords="<%= keywords %>" label="&lt; Newer" messageNumber="<%= messageNumber - 1 %>" />&nbsp;
			</c:if>
		</aui:column>
		<aui:column columnWidth="8">
			<b><%= messageNumber %></b> of <b><%= messageCount %></b>
		</aui:column>
		<aui:column columnWidth="6">
			<c:if test="<%= messageNumber < messageCount %>">
				<aui:a cssClass="message-link" folderId="<%= folderId %>" href="javascript:;" keywords="<%= keywords %>" label="Older &gt;" messageNumber="<%= messageNumber + 1 %>" />
			</c:if>
		</aui:column>
	</aui:layout>

	<aui:layout>
		<aui:column columnWidth="10">
			<liferay-ui:message key="from" />:
		</aui:column>
		<aui:column columnWidth="20">
			<%= message.getSender() %>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column columnWidth="10">
			<liferay-ui:message key="to" />:
		</aui:column>
		<aui:column columnWidth="20">
			<%= message.getTo() %>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column columnWidth="10">
			<liferay-ui:message key="cc" />:
		</aui:column>
		<aui:column columnWidth="20">
			<%= message.getCc() %>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column columnWidth="10">
			<liferay-ui:message key="date" />:
		</aui:column>
		<aui:column columnWidth="20">
			<%= message.getSentDate() %>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column columnWidth="10">
			<liferay-ui:message key="subject" />:
		</aui:column>
		<aui:column columnWidth="20">
			<%= message.getSubject() %>
		</aui:column>
	</aui:layout>

	<c:choose>
		<c:when test="<%= Validator.isNull(message.getBody()) %>">
			<div id="messageContentContainer">
				[Loading message..]
			</div>
		</c:when>
		<c:otherwise>
			<%= message.getBody() %>
		</c:otherwise>
	</c:choose>

	<aui:layout>
		<aui:column>
			<aui:a href="javascript:;" cssClass="compose-message" messageId="<%= message.getMessageId() %>" messageType="reply">reply</aui:a>
		</aui:column>
		<aui:column>
			<aui:a href="javascript:;" cssClass="compose-message" messageId="<%= message.getMessageId() %>" messageType="reply-all">reply-all</aui:a>
		</aui:column>
		<aui:column>
			<aui:a href="javascript:;" cssClass="compose-message" messageId="<%= message.getMessageId() %>" messageType="forward">forward</aui:a>
		</aui:column>
	</aui:layout>

	<c:if test="<%= Validator.isNull(message.getBody()) %>">
		<aui:script>
			var A = AUI();

			A.one('#messageContentContainer').plug(
				A.Plugin.IO,
				{
					uri: themeDisplay.getLayoutURL() + '/-/mail/view_message_content',
					autoLoad: true,
					data: {
						messageId: <%= message.getMessageId() %>
					},
					method: 'POST'
				}
			);
		</aui:script>
	</c:if>
</c:if>