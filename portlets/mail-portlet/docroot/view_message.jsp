<%@ page import="com.liferay.portal.kernel.servlet.taglib.CustomAttributes" %>
<%--
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

			CustomAttributes customAttributes1 = CustomAttributes.getInstance("data-");

			customAttributes1.add("folderId", folderId, "keywords", keywords, "orderByField", orderByField, "orderByType", orderByType, "pageNumber", pageNumber);
			%>

			<aui:a cssClass="messages-link" customAttributes="<%= customAttributes1 %>" href="javascript:;" label='<%= LanguageUtil.format(pageContext, "back-to-x", folderName) %>' />
		</aui:column>
		<aui:column cssClass="compose-message-container">

			<%
				CustomAttributes customAttributes2 = CustomAttributes.getInstance("data-");

				customAttributes2.add("replyMessageId", message.getMessageId());
			%>

			<aui:button cssClass="compose-message" data-messageType="reply" customAttributes="<%= customAttributes2 %>" href="javascript:;" value="reply" />

			<aui:button cssClass="compose-message" data-messageType="reply-all" customAttributes="<%= customAttributes2 %>" href="javascript:;" value="reply-all" />

			<aui:button cssClass="compose-message" data-messageType="forward" customAttributes="<%= customAttributes2 %>" href="javascript:;" value="forward" />
		</aui:column>
		<aui:column>

			<%
				CustomAttributes customAttributes3 = CustomAttributes.getInstance("data-");

				customAttributes3.add("folderId", folderId, "keywords", keywords, "messageId", message.getMessageId(), "orderByField", orderByField, "orderByType", orderByType, "pageNumber", pageNumber);
			%>

			<aui:button cssClass="delete-message" customAttributes="<%= customAttributes3 %>" value="delete" />
		</aui:column>
		<aui:column cssClass="message-count">
			<c:if test="<%= messageNumber > 1 %>">

				<%
					CustomAttributes customAttributes4 = CustomAttributes.getInstance("data-");

					customAttributes4.add("folderId", folderId, "keywords", keywords, "messageNumber", messageNumber - 1, "orderByField", orderByField, "orderByType", orderByType);
				%>

				<aui:a cssClass="message-link" customAttributes="<%= customAttributes4 %>" href="javascript:;">&lt; <liferay-ui:message key="newer" /></aui:a>
			</c:if>

			<liferay-ui:message key="x-of-x" arguments='<%= new Object[] {messageNumber, messageCount} %>' />

			<c:if test="<%= messageNumber < messageCount %>">

				<%
					CustomAttributes customAttributes5 = CustomAttributes.getInstance("data-");

					customAttributes5.add("folderId", folderId, "keywords", keywords, "messageNumber", messageNumber + 1, "orderByField", orderByField, "orderByType", orderByType);
				%>

				<aui:a cssClass="message-link" customAttributes="<%= customAttributes5 %>" href="javascript:;"><liferay-ui:message key="older" /> &gt;</aui:a>
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
			<liferay-util:include page="/view_message_content.jsp" portletId="<%= portletDisplay.getId() %>" />
		</c:if>
	</div>

	<aui:button-row>

		<%
			CustomAttributes customAttributes6 = CustomAttributes.getInstance("data-");

			customAttributes6.add("replyMessageId", message.getMessageId());
		%>

		<aui:button cssClass="compose-message" data-messageType="reply" customAttributes="<%= customAttributes6 %>" href="javascript:;" value="reply" />

		<aui:button cssClass="compose-message" data-messageType="reply-all" customAttributes="<%= customAttributes6 %>" href="javascript:;" value="reply-all" />

		<aui:button cssClass="compose-message" data-messageType="forward" customAttributes="<%= customAttributes6 %>" href="javascript:;" value="forward" />
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