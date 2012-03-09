<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<c:if test="<%= mailManager != null %>">
	<liferay-ui:icon
		image="../mail/compose"
	/>

	<aui:a cssClass="compose-message" data-messageId="0" data-messageType="new" data-replyMessageId="0" href="javascript:;"><liferay-ui:message key="compose-email" /></aui:a>

	<br /><br />

	<%
	Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);

	List<Folder> folders = mailManager.getFolders(accountId, true, true);

	for (Folder folder : folders) {
		String folderImage = "../common/folder";

		if (folder.getFolderId() == mailAccount.getInboxFolderId()) {
			folderImage = "../dock/home";
		}
		else if (folder.getFolderId() == mailAccount.getDraftFolderId()) {
			folderImage = "../mail/edit_draft";
		}
		else if (folder.getFolderId() == mailAccount.getSentFolderId()) {
			folderImage = "../mail/forward";
		}
		else if (folder.getFolderId() == mailAccount.getTrashFolderId()) {
			folderImage = "../common/delete";
		}
	%>

		<aui:layout>
			<liferay-ui:icon
				image="<%= folderImage %>"
			/>

			<aui:a cssClass="messages-link" data-accountId="<%= accountId %>" data-folderId="<%= folder.getFolderId() %>" data-keywords="" data-orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>" data-orderByType="desc" data-pageNumber="1" href="javascript:;" label='<%= folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")" %>' />
		</aui:layout>

	<%
	}
	%>

	<br />

	<liferay-ui:icon
		image="../mail/edit_folder"
	/>

	<aui:a cssClass="manage-folders" href="javascript:;"><liferay-ui:message key="manage-folders" /></aui:a>

	<br />

	<liferay-ui:icon
		image="../mail/edit_folder"
	/>

	<aui:a cssClass="edit-account" href="javascript:;"><liferay-ui:message key="edit-account" /></aui:a>
</c:if>