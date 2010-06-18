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
	int pageNumber = ParamUtil.getInteger(request, "pageNumber", 1);
	int messagesPerPage = ParamUtil.getInteger(request, "messagesPerPage", 25);
	String orderByField = ParamUtil.getString(request, "orderByField");
	String orderByType = ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessagesDisplay messagesDisplay = mailManager.getMessagesDisplay(folderId, pageNumber, messagesPerPage, orderByField, orderByType, keywords);
	%>

	<aui:layout>
		<aui:column>
			<aui:button cssClass="delete-messages" value="delete" />
		</aui:column>
		<aui:column>
			<aui:select cssClass="flag-messages" name="flagMessages" showEmptyOption="true">
				<aui:option value="4,true"><liferay-ui:message key="flag-as-important" /></aui:option>
				<aui:option value="4,false"><liferay-ui:message key="remove-flag" /></aui:option>
				<aui:option value="6,true"><liferay-ui:message key="mark-as-read" /></aui:option>
				<aui:option value="6,false"><liferay-ui:message key="mark-as-unread" /></aui:option>
			</aui:select>
		</aui:column>
		<aui:column>
			<aui:select cssClass="move-messages" name="moveMessages" showEmptyOption="true">

				<%
				Folder folder = FolderLocalServiceUtil.getFolder(folderId);

				List<Folder> folders = FolderLocalServiceUtil.getFolders(folder.getAccountId());

				for (Folder curFolder : folders) {
				%>

					<aui:option value="<%= curFolder.getFolderId() %>"><%= curFolder.getDisplayName() %></aui:option>

				<%
				}
				%>

			</aui:select>
		</aui:column>
		<aui:column cssClass="search">
			<aui:input name="keywords" value="<%= keywords %>" />

			<aui:button cssClass="search-messages" value="search" />
		</aui:column>
	</aui:layout>

	<br />

	<aui:layout>
		<aui:column>
			<liferay-ui:message key="select" />:

			<a class="select-all" href="javascript:;"><liferay-ui:message key="all" /></a>

			<a class="select-none" href="javascript:;"><liferay-ui:message key="none" /></a>
		</aui:column>
		<aui:column cssClass="pagination">
			<c:if test="<%= messagesDisplay.getPageNumber() > 2 %>">
				<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="1" href="javascript:;" label="&lt;&lt; Newest" />&nbsp;
			</c:if>

			<c:if test="<%= messagesDisplay.getPageNumber() > 1 %>">
				<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber - 1 %>" href="javascript:;" label="&lt; Newer" />
			</c:if>

			<liferay-ui:message key="x-of-x" arguments='<%= new Object[] {messagesDisplay.getStartMessageNumber() + " - " + messagesDisplay.getEndMessageNumber(), messagesDisplay.getMessageCount()} %>' />

			<c:if test="<%= messagesDisplay.getPageNumber() < messagesDisplay.getPageCount() %>">
				<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber + 1 %>" href="javascript:;" label="Older &gt;" />&nbsp;
			</c:if>
		</aui:column>
	</aui:layout>

	<br />

	<c:choose>
		<c:when test="<%= messagesDisplay.getMessageCount() == 0 %>">
			<aui:layout>
				<c:choose>
					<c:when test="<%= Validator.isNull(keywords) %>">
						<liferay-ui:message key="there-are-no-message-in-this-folder" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="no-messages-matched-your-search" />
					</c:otherwise>
				</c:choose>
			</aui:layout>
		</c:when>
		<c:otherwise>
			<aui:layout>
				<aui:column columnWidth="5">
					&nbsp;
				</aui:column>
				<aui:column columnWidth="95">
					<aui:column columnWidth="25">

						<%
						String addressOrderByType = "asc";

						if (orderByField.equals(MailConstants.ORDER_BY_ADDRESS) && orderByType.equals("asc")) {
							addressOrderByType = "desc";
						}
						%>

						<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= MailConstants.ORDER_BY_ADDRESS %>" data-orderByType="<%= addressOrderByType %>" data-pageNumber="1" href="javascript:;" label="address" />
					</aui:column>
					<aui:column cssClass="subject" columnWidth="55">

						<%
						String subjectOrderByType = "asc";

						if (orderByField.equals(MailConstants.ORDER_BY_SUBJECT) && orderByType.equals("asc")) {
							subjectOrderByType = "desc";
						}
						%>

						<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= MailConstants.ORDER_BY_SUBJECT %>" data-orderByType="<%= subjectOrderByType %>" data-pageNumber="1" href="javascript:;" label="subject" />
					</aui:column>
					<aui:column cssClass="date" columnWidth="15">

						<%
						String dateOrderByType = "desc";

						if (orderByField.equals(MailConstants.ORDER_BY_SENT_DATE) && orderByType.equals("desc")) {
							dateOrderByType = "asc";
						}
						%>

						<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>" data-orderByType="<%= dateOrderByType %>" data-pageNumber="1" href="javascript:;" label="date" />
					</aui:column>
					<aui:column columnWidth="5">
						&nbsp;
					</aui:column>
				</aui:column>
			</aui:layout>

			<%
			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			Account mailAccount = AccountLocalServiceUtil.getAccount(folder.getAccountId());

			int messageNumber = messagesDisplay.getStartMessageNumber();

			List<Message> messages = messagesDisplay.getMessages();

			String cssClass = "message-link";

			if (mailAccount.getDraftFolderId() == folderId) {
				cssClass = "draft-link";
			}

			for (Message message : messages) {
				String address = StringPool.BLANK;
				String date = StringPool.BLANK;

				if (mailAccount.getSentFolderId() == folderId) {
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

				if (mailAccount.getDraftFolderId() == folderId) {
					date = dateFormatDateTime.format(message.getModifiedDate());
				}
				else {
					date = dateFormatDateTime.format(message.getSentDate());
				}
			%>

				<aui:layout>
					<aui:column columnWidth="5">
						<aui:input id="message<%= message.getMessageId() %>" label="" messageId="<%= message.getMessageId() %>" name="message" type="checkbox" value="<%= message.getMessageId() %>" />
					</aui:column>
					<aui:column columnWidth="95" cssClass="<%= cssClass %>" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-messageNumber="<%= messageNumber %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>">
						<aui:column cssClass="address" columnWidth="25">
							<%= HtmlUtil.escape(address) %>
						</aui:column>
						<aui:column cssClass="subject" columnWidth="55">
							<%= HtmlUtil.escape(message.getSubject()) %>
						</aui:column>
						<aui:column cssClass="date" columnWidth="15">
							<%= HtmlUtil.escape(date) %>
						</aui:column>
						<aui:column cssClass="attachment" columnWidth="5">
							<c:if test="<%= !AttachmentLocalServiceUtil.getAttachments(message.getMessageId()).isEmpty() %>">
								<liferay-ui:icon
									image="../mail/clip"
								/>
							</c:if>
						</aui:column>
					</aui:column>
				</aui:layout>

			<%
				messageNumber++;
			}
			%>

		</c:otherwise>
	</c:choose>
</c:if>