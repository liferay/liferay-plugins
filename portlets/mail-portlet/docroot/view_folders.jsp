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
	<div class="controls-list well">
		<aui:nav cssClass="nav-list">
			<aui:nav-item
				cssClass="compose-message"
				data-messageId="0"
				data-messageType="new"
				data-replyMessageId="0"
				href="javascript:;"
				iconClass="icon-envelope"
				label="compose"
			/>

			<aui:nav-item cssClass="divider" />

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

				<aui:nav-item
					cssClass="messages-link"
					data-accountId="<%= accountId %>"
					data-folderId="<%= folder.getFolderId() %>"
					data-keywords=""
					data-orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>"
					data-orderByType="desc"
					data-pageNumber="1"
					href="javascript:;"
					iconClass="<%= folderIcon %>"
					label='<%= folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")" %>'
				/>

				<%
				}
				%>

				<aui:nav-item cssClass="divider" />

				<aui:nav-item
					cssClass="manage-folders"
					data-messageId="0"
					data-messageType="new"
					data-replyMessageId="0"
					href="javascript:;"
					iconClass="icon-cogs"
					label="manage-folders"
				/>

				<aui:nav-item
					cssClass="edit-account"
					data-messageId="0"
					data-messageType="new"
					data-replyMessageId="0"
					href="javascript:;"
					iconClass="icon-cog"
					label="edit-account"
				/>
			</aui:nav>
	</div>
</c:if>