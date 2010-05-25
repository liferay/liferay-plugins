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

	MessageDisplay messageDisplay = mailManager.getMessageDisplay(messageId);

	List<Attachment> attachments = messageDisplay.getAttachments();
	%>

	<%= messageDisplay.getMessage().getBody() %>

	<c:if test="<%= !attachments.isEmpty() %>">
		<hr />

		<ul>

		<%
		for (Attachment attachment : attachments) {
		%>

			<liferay-portlet:resourceURL var="attachmentLink">
				<liferay-portlet:param name="jspPage" value="/attachment.jsp" />
				<liferay-portlet:param name="attachmentId" value="<%= String.valueOf(attachment.getAttachmentId()) %>" />
			</liferay-portlet:resourceURL>

			<li>
				<aui:a href="<%= attachmentLink %>"><%= attachment.getFileName() + " - " + attachment.getSize() %></aui:a>
			</li>

		<%
		}
		%>

		</ul>
	</c:if>
</c:if>