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
	<aui:a cssClass="compose-message" href="javascript:;" messageId="0">compose-message</aui:a>

	<br />
	<br />

	<%
	long accountId = ParamUtil.getLong(request, "accountId");

	List<Folder> folders = FolderLocalServiceUtil.getFolders(accountId);

	for (Folder folder : folders) {
		String folderLabel = folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")";
		%>

		<aui:layout>
			<aui:a cssClass="messages-link" href="javascript:;" folderId="<%= folder.getFolderId() %>" label="<%= folderLabel %>" pageNumber="1" />
		</aui:layout>

		<%
	}
	%>

	<br />

	<aui:a accountId="<%= accountId %>" cssClass="manage-folders" href="javascript:;">manage-folders</aui:a>

	<br />

	<aui:a accountId="<%= accountId %>" cssClass="edit-account" href="javascript:;">edit-account</aui:a>
</c:if>