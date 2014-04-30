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

long accountId = ParamUtil.getLong(request, "accountId");
%>

<c:if test="<%= mailManager != null %>">
	<div class="add-folder-container">
		<aui:input name="displayName" value="" />

		<aui:button name="addFolder" onClick='<%= renderResponse.getNamespace() + "addFolder()" %>' value="add-folder" />
	</div>

	<br />

	<table class="folder-list">

		<%
		List<Folder> folders = mailManager.getFolders(accountId, false, true);

		for (int i = 0; i < folders.size(); i++) {
			Folder folder = folders.get(i);
		%>

			<tr class="no-hover<%= ((i % 2) == 0) ? " alt" : "" %> results-row">
				<td class="name">
					<%= folder.getDisplayName() %>
				</td>
				<td class="action">
					<aui:button cssClass="delete-folder" onClick='<%= renderResponse.getNamespace() + "deleteFolder(" + folder.getFolderId() + ")" %>' value="delete-folder" />

					<aui:button cssClass="rename-folder" onClick='<%= renderResponse.getNamespace() + "renameFolder(" + folder.getFolderId() + ")" %>' value="rename-folder" />
				</td>
			</tr>

		<%
		}
		%>

	</table>

	<aui:script>
		function <portlet:namespace />onIOFailure(event, id, obj) {
			Liferay.Mail.setStatus('error', '<liferay-ui:message key="unable-to-connect-with-mail-server" />');
		}

		function <portlet:namespace />onIOSuccess(event, id, obj) {
			var responseData = this.get('responseData');

			Liferay.Mail.setStatus(responseData.status, responseData.message);

			if (responseData.status == 'success') {
				Liferay.Mail.loadFolders(<%= accountId %>);
				Liferay.Mail.loadManageFolders(<%= accountId %>);
			}
		}

		Liferay.provide(
			window,
			'<portlet:namespace />addFolder',
			function(event) {
				var A = AUI();

				Liferay.Mail.setStatus('info', '<liferay-ui:message key="adding-folder" />', true);

				var displayName = A.one('#<portlet:namespace />displayName').get('value');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/add_folder',
					{
						data: Liferay.Util.ns(
							'<portlet:namespace />',
							{
								accountId: <%= accountId %>,
								displayName: displayName
							}
						),
						dataType: 'JSON',
						method: 'POST',
						on: {
							failure: <portlet:namespace />onIOFailure,
							success: <portlet:namespace />onIOSuccess
						}
					}
				);
			},
			['aui-io-deprecated']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />deleteFolder',
			function(id) {
				var A = AUI();

				if (!confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-folder" />')) {
					return;
				}

				Liferay.Mail.setStatus('info', '<liferay-ui:message key="deleting-folder" />', true);

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/delete_folder',
					{
						data: Liferay.Util.ns(
							'<portlet:namespace />',
							{
								folderId: id
							}
						),
						dataType: 'JSON',
						method: 'POST',
						on: {
							failure: <portlet:namespace />onIOFailure,
							success: <portlet:namespace />onIOSuccess
						}
					}
				);
			},
			['aui-io-deprecated']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />renameFolder',
			function(id) {
				var A = AUI();

				Liferay.Util.Window.getWindow(
					{
						dialog: {
							centered: true,
							cssClass: 'mail-dialog',
							destroyOnClose: true,
							modal: true,
							width: 600
						},
						title: '<liferay-ui:message key="rename-folder" />'
					}
				).plug(
					A.Plugin.IO,
					{
						data: Liferay.Util.ns(
							'<portlet:namespace />',
							{
								folderId: id
							}
						),
						uri: themeDisplay.getLayoutURL() + '/-/mail/edit_folder'
					}
				).render();
			},
			['aui-io-deprecated', 'liferay-util-window']
		);
	</aui:script>
</c:if>