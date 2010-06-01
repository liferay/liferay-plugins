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
	int messageNumber = ParamUtil.getInteger(request, "messageNumber");
	int messagesPerPage = ParamUtil.getInteger(request, "messagesPerPage", 25);
	String orderByField = ParamUtil.getString(request, "orderByField");
	String orderByType = ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessageDisplay messageDisplay = mailManager.getMessageDisplay(folderId, messageNumber, orderByField, orderByType, keywords);

	Message message = messageDisplay.getMessage();
	List<Attachment> attachments = messageDisplay.getAttachments();
	int messageCount = messageDisplay.getMessageCount();

	int pageNumber = (int)(Math.ceil(messageNumber / (double) messagesPerPage));
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

			<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber %>" href="javascript:;" label="<%= LanguageUtil.format(pageContext, "back-to-x", folderName) %>" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="delete-message" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber %>" value="delete" />
		</aui:column>
		<aui:column cssClass="message-count">
			<c:if test="<%= messageNumber > 1 %>">
				<aui:a cssClass="message-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageNumber="<%= messageNumber - 1 %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" href="javascript:;">&lt; <liferay-ui:message key="newer" /></aui:a>
			</c:if>

			<liferay-ui:message key="x-of-x" arguments='<%= new Object[] {String.valueOf(messageNumber), String.valueOf(messageCount)} %>' />

			<c:if test="<%= messageNumber < messageCount %>">
				<aui:a cssClass="message-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageNumber="<%= messageNumber + 1 %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" href="javascript:;"><liferay-ui:message key="older" /> &gt;</aui:a>
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
			<div id="messageContentContainer"></div>
		</c:when>
		<c:otherwise>
			<%= message.getBody() %>

			<c:if test="<%= !attachments.isEmpty() %>">
				<hr />

				<ul>

					<%
					for (Attachment attachment : attachments) {
					%>

						<liferay-portlet:resourceURL var="attachmentURL">
							<liferay-portlet:param name="jspPage" value="/attachment.jsp" />
							<liferay-portlet:param name="attachmentId" value="<%= String.valueOf(attachment.getAttachmentId()) %>" />
						</liferay-portlet:resourceURL>

						<li>
							<aui:a href="<%= attachmentURL %>"><%= attachment.getFileName() %> - <%= attachment.getSize() %></aui:a>
						</li>

					<%
					}
					%>

				</ul>
			</c:if>
		</c:otherwise>
	</c:choose>

	<aui:layout>
		<aui:column>
			<aui:a href="javascript:;" cssClass="compose-message" data-messageType="reply" data-replyMessageId="<%= message.getMessageId() %>"><liferay-ui:message key="reply" /></aui:a>
		</aui:column>
		<aui:column>
			<aui:a href="javascript:;" cssClass="compose-message" data-messageType="reply-all" data-replyMessageId="<%= message.getMessageId() %>"><liferay-ui:message key="reply-all" /></aui:a>
		</aui:column>
		<aui:column>
			<aui:a href="javascript:;" cssClass="compose-message" data-messageType="forward" data-replyMessageId="<%= message.getMessageId() %>"><liferay-ui:message key="forward" /></aui:a>
		</aui:column>
	</aui:layout>

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