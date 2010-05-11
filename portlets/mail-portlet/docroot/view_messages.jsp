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
	long messageId = ParamUtil.getLong(request, "messageId");
	long folderId = ParamUtil.getLong(request, "folderId");
	int pageNumber = ParamUtil.getInteger(request, "pageNumber", 1);
	int messagesPerPage = 25; //ParamUtil.getInteger(request, "messagesPerPage");
	String orderByField = MailConstants.ORDER_BY_SENT_DATE; //ParamUtil.getString(request, "orderByField");
	String orderByType = "desc"; //ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessagesDisplay messagesDisplay = mailManager.getMessagesDisplay(folderId, pageNumber, messagesPerPage, orderByField, orderByType, keywords);
	%>

	<aui:layout>
		<aui:column>
			<aui:button cssClass="delete-messages" value="Delete" />
		</aui:column>
		<aui:column>
			<aui:select cssClass="flag-messages" label="flagMessages" name="flagMessages" showEmptyOption="true">
				<aui:option value="4,true"><liferay-ui:message key="flag-as-important" /></aui:option>
				<aui:option value="4,false"><liferay-ui:message key="remove-flag" /></aui:option>
				<aui:option value="6,true"><liferay-ui:message key="mark-as-read" /></aui:option>
				<aui:option value="6,false"><liferay-ui:message key="mark-as-unread" /></aui:option>
			</aui:select>
		</aui:column>
		<aui:column>
			<aui:select cssClass="move-messages" label="moveMessages" name="moveMessages" showEmptyOption="true">
				<%
				Folder folder = FolderLocalServiceUtil.getFolder(folderId);

				List<Folder> folders = FolderLocalServiceUtil.getFolders(folder.getAccountId());

				for (Folder tempFolder : folders) {
				%>

				<aui:option value="<%= tempFolder.getFolderId() %>"><%= tempFolder.getDisplayName() %></aui:option>

				<%
				}
				%>
			</aui:select>
		</aui:column>
		<aui:column>
			<c:if test="<%= messagesDisplay.getPageNumber() > 2 %>">
				<aui:a cssClass="messages-link" folderId="<%= folderId %>" href="javascript:;" label="&lt;&lt; Newest" pageNumber="1" />&nbsp;
			</c:if>
			<c:if test="<%= messagesDisplay.getPageNumber() > 1 %>">
				<aui:a cssClass="messages-link" folderId="<%= folderId %>" href="javascript:;" label="&lt; Newer" pageNumber="<%= pageNumber - 1 %>" />
			</c:if>

			<%= messagesDisplay.getStartMessageNumber() %> - <%= messagesDisplay.getEndMessageNumber() %>  of <%= messagesDisplay.getMessageCount() %>

			<c:if test="<%= messagesDisplay.getPageNumber() < messagesDisplay.getPageCount() %>">
				<aui:a cssClass="messages-link" folderId="<%= folderId %>" href="javascript:;" label="Older >" pageNumber="<%= pageNumber + 1 %>" />&nbsp;
			</c:if>
			<c:if test="<%= messagesDisplay.getPageNumber() + 1 < messagesDisplay.getPageCount() %>">
				<aui:a cssClass="messages-link" folderId="<%= folderId %>" href="javascript:;" label="Oldest >>" pageNumber="<%= messagesDisplay.getPageCount() %>" />
			</c:if>
		</aui:column>
	</aui:layout>

	<aui:layout>
		<aui:column>
			<liferay-ui:message key="select" />
		</aui:column>
		<aui:column>
			<aui:a cssClass="select-all" href="javascript:;"><liferay-ui:message key="all" /></aui:a>
		</aui:column>
		<aui:column>
			<aui:a cssClass="select-none" href="javascript:;"><liferay-ui:message key="none" /></aui:a>
		</aui:column>
	</aui:layout>

	<br />

	<aui:layout>
		<aui:column columnWidth="5">
			&nbsp;
		</aui:column>

		<aui:column columnWidth="95">
			<aui:column columnWidth="25" first="true">
				<liferay-ui:message key="address" />
			</aui:column>

			<aui:column cssClass="subject" columnWidth="45" first="true">
				<liferay-ui:message key="subject" />
			</aui:column>

			<aui:column columnWidth="5" first="true">
				&nbsp;
			</aui:column>

			<aui:column cssClass="date" columnWidth="20" first="true">
				<liferay-ui:message key="date" />
			</aui:column>
		</aui:column>
	</aui:layout>

	<%
	List<Message> messages = messagesDisplay.getMessages();

	int currentMessageNumber = messagesDisplay.getStartMessageNumber();

	Folder folder = FolderLocalServiceUtil.getFolder(folderId);

	Account account = AccountLocalServiceUtil.getAccount(folder.getAccountId());

	for (Message message : messages) {
		String address = StringPool.BLANK;
		String date = StringPool.BLANK;

		if (account.getSentFolderId() == folderId) {
			address = message.getTo();

			if (Validator.isNotNull(message.getCc())) {
				address += ", " + message.getCc();
			}

			if (Validator.isNotNull(message.getBcc())) {
				address += ", " + message.getBcc();
			}
		}
		else {
			address = message.getSender();
		}

		if (account.getDraftFolderId() == folderId) {
			date = HtmlUtil.escape(message.getModifiedDate().toString());
		}
		else {
			date = HtmlUtil.escape(message.getSentDate().toString());
		}

	%>

		<aui:layout>
			<aui:column columnWidth="5">
				<aui:input id="message<%= message.getMessageId() %>" label="" messageId="<%= message.getMessageId() %>" name="message" type="checkbox" value="<%= message.getMessageId() %>" />
			</aui:column>

			<aui:column columnWidth="95" cssClass="message-link" folderId="<%= folderId %>" keywords="<%= keywords %>" messageNumber="<%= currentMessageNumber %>">
				<aui:column cssClass="address" columnWidth="25">
					<%= address %>
				</aui:column>

				<aui:column cssClass="subject" columnWidth="45">
					<%= HtmlUtil.escape(message.getSubject()) %>
				</aui:column>

				<aui:column cssClass="attachment" columnWidth="5">
					<c:choose>
						<c:when test="<%= AttachmentLocalServiceUtil.getAttachments(message.getMessageId()).isEmpty() %>">
							&nbsp;
						</c:when>
						<c:otherwise>
							[a]
						</c:otherwise>
					</c:choose>
				</aui:column>

				<aui:column cssClass="date" columnWidth="20">
					<%= date %>
				</aui:column>
			</aui:column>
		</aui:layout>

	<%

		currentMessageNumber++;
	}
	%>

</c:if>