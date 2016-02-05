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

	<aui:nav-bar>
		<aui:nav>
			<aui:nav-item dropdown="<%= true %>" label="select">
				<aui:nav-item cssClass="select-all" label="all" />
				<aui:nav-item cssClass="select-none" label="none" />
			</aui:nav-item>

			<aui:nav-item cssClass="delete-messages" iconClass="icon-remove" label="delete" />

			<aui:nav-item dropdown="<%= true %>" label="flag">
				<aui:nav-item cssClass="flag-messages" data-flagToggle="true" data-flagType="4" label="flag-as-important" />
				<aui:nav-item cssClass="flag-messages" data-flagToggle="false" data-flagType="4" label="remove-flag" />
				<aui:nav-item cssClass="flag-messages" data-flagToggle="true" data-flagType="6" label="mark-as-read" />
				<aui:nav-item cssClass="flag-messages" data-flagToggle="false" data-flagType="6" label="mark-as-unread" />
			</aui:nav-item>

			<aui:nav-item dropdown="<%= true %>" label="move">

				<%
				Folder folder = FolderLocalServiceUtil.getFolder(folderId);

				long accountId = folder.getAccountId();

				long selectedFolderId = 0;
				%>

				<%@ include file="/select_folder.jspf" %>

			</aui:nav-item>

			<aui:nav-bar-search cssClass="form-search pull-right search-messages">
				<liferay-ui:input-search id="keywords" placeholder='<%= LanguageUtil.get(request, "keywords") %>' title='<%= LanguageUtil.get(request, "search-messages") %>' />
			</aui:nav-bar-search>
		</aui:nav>
	</aui:nav-bar>

	<c:choose>
		<c:when test="<%= messagesDisplay.getMessageCount() == 0 %>">
			<div class="alert alert-info">
				<c:choose>
					<c:when test="<%= Validator.isNull(keywords) %>">
						<liferay-ui:message key="there-are-no-message-in-this-folder" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="no-messages-matched-your-search" />
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:otherwise>
			<div class="table-container">
				<table class="table table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th class="check"></th>
							<th class="address">

								<%
								String addressOrderByType = "asc";

								if (orderByField.equals(MailConstants.ORDER_BY_ADDRESS) && orderByType.equals("asc")) {
									addressOrderByType = "desc";
								}
								%>

								<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= MailConstants.ORDER_BY_ADDRESS %>" data-orderByType="<%= addressOrderByType %>" data-pageNumber="1" href="javascript:;" label="address" />
							</th>
							<th class="subject">

								<%
								String subjectOrderByType = "asc";

								if (orderByField.equals(MailConstants.ORDER_BY_SUBJECT) && orderByType.equals("asc")) {
									subjectOrderByType = "desc";
								}
								%>

								<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= MailConstants.ORDER_BY_SUBJECT %>" data-orderByType="<%= subjectOrderByType %>" data-pageNumber="1" href="javascript:;" label="subject" />
							</th>
							<th class="attachments"></th>
							<th class="date">

								<%
								String dateOrderByType = "desc";

								if (orderByField.equals(MailConstants.ORDER_BY_SENT_DATE) && orderByType.equals("desc")) {
									dateOrderByType = "asc";
								}
								%>

								<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>" data-orderByType="<%= dateOrderByType %>" data-pageNumber="1" href="javascript:;" label="date" />
							</th>
						</tr>
					</thead>
					<tbody>

						<%
						Folder folder = FolderLocalServiceUtil.getFolder(folderId);

						Account mailAccount = AccountLocalServiceUtil.getAccount(folder.getAccountId());

						int messageNumber = messagesDisplay.getStartMessageNumber();

						List<Message> messages = messagesDisplay.getMessages();

						String messageCssClass = "message-link";

						if (mailAccount.getDraftFolderId() == folderId) {
							messageCssClass = "draft-link";
						}

						for (Message message : messages) {
							String address = StringPool.BLANK;
							String date = StringPool.DASH;

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
								if (message.getModifiedDate() != null) {
									date = dateFormatDateTime.format(message.getModifiedDate());
								}
							}
							else {
								if (message.getSentDate() != null) {
									date = dateFormatDateTime.format(message.getSentDate());
								}
							}

							String rowCssClass = "results-row";

							if (!message.hasFlag(MailConstants.FLAG_SEEN)) {
								rowCssClass += " unread";
							}

							if (message.hasFlag(MailConstants.FLAG_FLAGGED)) {
								rowCssClass += " important";
							}
						%>

							<tr class="<%= rowCssClass %>">
								<td class="check">
									<aui:input id='<%= "message" + message.getMessageId() %>' label="" messageId="<%= message.getMessageId() %>" name="message" type="checkbox" value="<%= message.getMessageId() %>" />
								</td>
								<td class="address">
									<div class="<%= messageCssClass %>" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-messageNumber="<%= messageNumber %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>">
										<%= HtmlUtil.escape(address) %>
									</div>
								</td>
								<td class="subject">
									<div class="<%= messageCssClass + (Validator.isNull(message.getSubject()) ? " no-subject" : StringPool.BLANK) %>" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-messageNumber="<%= messageNumber %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>">
										<%= Validator.isNull(message.getSubject()) ? LanguageUtil.get(request, "no-subject") : HtmlUtil.escape(message.getSubject()) %>
									</div>
								</td>
								<td class="attachments">
									<div class="<%= messageCssClass %>" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-messageNumber="<%= messageNumber %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>">
										<c:if test="<%= message.hasAttachments() %>">
											<liferay-ui:icon
												iconCssClass="icon-paper-clip"
												message="attachments"
											/>
										</c:if>
									</div>
								</td>
								<td class="date">
									<div class="<%= messageCssClass %>" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-messageNumber="<%= messageNumber %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>">
										<%= HtmlUtil.escape(date) %>
									</div>
								</td>
							</tr>

						<%
							messageNumber++;
						}
						%>

					</tbody>
				</table>
			</div>

			<ul class="message-pager pager pull-right">
				<li class="<%= (messagesDisplay.getPageNumber() > 2 ? StringPool.BLANK : "disabled ") + "previous" %>">
					<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="1" href="javascript:;">&larr; <liferay-ui:message key="newest" /></aui:a>
				</li>

				<li class="<%= (messagesDisplay.getPageNumber() > 1 ? StringPool.BLANK : "disabled ") + "previous" %>">
					<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber - 1 %>" href="javascript:;"><liferay-ui:message key="newer" /></aui:a>
				</li>

				<li class="message-count">
					<liferay-ui:message arguments='<%= new Object[] {messagesDisplay.getStartMessageNumber() + " - " + messagesDisplay.getEndMessageNumber(), messagesDisplay.getMessageCount()} %>' key="x-of-x" translateArguments="<%= false %>" />
				</li>

				<li class="<%= (messagesDisplay.getPageNumber() < messagesDisplay.getPageCount() ? StringPool.BLANK : "disabled ") + "next" %>">
					<aui:a cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber + 1 %>" href="javascript:;"><liferay-ui:message key="older" /> &rarr;</aui:a>
				</li>
			</ul>
		</c:otherwise>
	</c:choose>
</c:if>