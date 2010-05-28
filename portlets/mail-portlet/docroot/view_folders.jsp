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
long accountId = ParamUtil.getLong(request, "accountId");

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<liferay-ui:icon image="../mail/compose" />

	<aui:a cssClass="compose-message" href="javascript:;" data-messageId="0" data-messageType="new" data-replyMessageId="0"><liferay-ui:message key="compose-email" /></aui:a>

	<br /><br />

	<%
	Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);
	List<Folder> folders = mailManager.getFolders(accountId, true, true);

	for (Folder folder : folders) {
		String folderLabel = folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")";
		String iconImage = "../common/folder";

		if (folder.getFolderId() == mailAccount.getInboxFolderId()) {
			iconImage = "../dock/home";
		}
		else if (folder.getFolderId() == mailAccount.getDraftFolderId()) {
			iconImage = "../mail/edit_draft";
		}
		else if (folder.getFolderId() == mailAccount.getSentFolderId()) {
			iconImage = "../mail/forward";
		}
		else if (folder.getFolderId() == mailAccount.getTrashFolderId()) {
			iconImage = "../common/delete";
		}
	%>

		<aui:layout>
			<liferay-ui:icon image="<%= iconImage %>" />

			<aui:a accountId="<%= accountId %>" cssClass="messages-link" href="javascript:;" folderId="<%= folder.getFolderId() %>" keywords="" label="<%= folderLabel %>" orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>" orderByType="desc" pageNumber="1" />
		</aui:layout>

	<%
	}
	%>

	<br />

	<liferay-ui:icon image="../mail/edit_folder" />

	<aui:a cssClass="manage-folders" href="javascript:;"><liferay-ui:message key="manage-folders" /></aui:a>

	<br />

	<liferay-ui:icon image="../mail/edit_folder" />

	<aui:a cssClass="edit-account" href="javascript:;"><liferay-ui:message key="edit-account" /></aui:a>
</c:if>