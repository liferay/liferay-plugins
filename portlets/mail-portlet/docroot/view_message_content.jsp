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
	long messageId = ParamUtil.getLong(request, "messageId");
	int messageNumber = ParamUtil.getInteger(request, "messageNumber");
	String orderByField = ParamUtil.getString(request, "orderByField");
	String orderByType = ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessageDisplay messageDisplay = null;

	if (messageId > 0) {
		messageDisplay = mailManager.getMessageDisplay(messageId);
	}
	else {
		messageDisplay = mailManager.getMessageDisplay(folderId, messageNumber, orderByField, orderByType, keywords);
	}

	Message message = messageDisplay.getMessage();
	%>

	<%= message.getBody() %>

	<%
	List<Attachment> attachments = messageDisplay.getAttachments();
	%>

	<c:if test="<%= !attachments.isEmpty() %>">
		<hr />

		<ul>

			<%
			for (Attachment attachment : attachments) {
			%>

				<liferay-portlet:resourceURL var="attachmentURL">
					<portlet:param name="mvcPath" value="/attachment.jsp" />
					<portlet:param name="attachmentId" value="<%= String.valueOf(attachment.getAttachmentId()) %>" />
				</liferay-portlet:resourceURL>

				<li>
					<a href="<%= attachmentURL %>"><%= attachment.getFileName() %> - <%= attachment.getSize() %></a>
				</li>

			<%
			}
			%>

		</ul>
	</c:if>
</c:if>