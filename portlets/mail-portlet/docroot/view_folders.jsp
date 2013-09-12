<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
long accountId = ParamUtil.getLong(request, "accountId");

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= Validator.isNotNull(mailManager) %>">

	<aui:a cssClass="compose-message icon icon-envelope" data-messageId="0" data-messageType="new" data-replyMessageId="0" href="javascript: ;" >
		<liferay-ui:message key="compose" />
	</aui:a>

	<br /><br />

	<%
	Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);

	List<Folder> folders = mailManager.getFolders(accountId, true, true);

	for (Folder folder : folders) {
		String folderIcon = "icon-folder-open";

		if (folder.getFolderId() == mailAccount.getInboxFolderId()) {
			folderIcon = "icon-inbox";
		}
		else if (folder.getFolderId() == mailAccount.getDraftFolderId()) {
			folderIcon = "icon-pencil";
		}
		else if (folder.getFolderId() == mailAccount.getSentFolderId()) {
			folderIcon = "icon-folder-close";
		}
		else if (folder.getFolderId() == mailAccount.getTrashFolderId()) {
			folderIcon = "icon-trash";
		}
	%>

		<aui:a cssClass='<%= "messages-link icon " + folderIcon %>' data-accountId="<%= accountId %>" data-folderId="<%= folder.getFolderId() %>" data-keywords="" data-orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>" data-orderByType="desc" data-pageNumber="1" href="javascript:;">
			<%= folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")" %>
		</aui:a>

		<br/>

	<%
	}
	%>

	<br />

	<aui:a cssClass="icon icon-cogs manage-folders" href="javascript:;"><liferay-ui:message key="manage-folders" /></aui:a>

	<br />

	<aui:a cssClass="edit-account icon icon-cog" href="javascript:;"><liferay-ui:message key="edit-account" /></aui:a>
</c:if>