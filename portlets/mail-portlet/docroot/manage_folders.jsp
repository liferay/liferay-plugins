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
MailManager mailManager = MailManager.getInstance(request);

long accountId = ParamUtil.getLong(request, "accountId");
%>

<c:if test="<%= mailManager != null %>">
	<div class="add-folder-container">
		<aui:input name="displayName" value="" />

		<aui:button name="addFolder" value="add-folder" />
	</div>

	<br />

	<table class="folder-list">

		<%
		List<Folder> folders = mailManager.getFolders(accountId, false, true);

		for (int i = 0; i < folders.size(); i++) {
			Folder folder = folders.get(i);
		%>

			<tr class="results-row no-hover<%= ((i % 2) == 0) ? " alt" : "" %>">
				<td class="name">
					<%= folder.getDisplayName() %>
				</td>
				<td class="action">
					<a class="delete-folder" data-folderId="<%= folder.getFolderId() %>" href="javascript:;"><liferay-ui:message key="delete-folder" /></a>

					<a class="rename-folder" data-folderId="<%= folder.getFolderId() %>" href="javascript:;"><liferay-ui:message key="rename-folder" /></a>
				</td>
			</tr>

		<%
		}
		%>

	</table>

	<aui:script use="aui-io">
		var <portlet:namespace />onIOFailure = function(event, id, obj) {
			Liferay.Mail.setStatus('error', '<liferay-ui:message key="unable-to-connect-with-mail-server" />');
		}

		var <portlet:namespace />onIOSuccess = function(event, id, obj) {
			var responseData = this.get('responseData');

			Liferay.Mail.setStatus(responseData.status, responseData.message);

			if (responseData.status == 'success') {
				Liferay.Mail.loadFolders(<%= accountId %>);
				Liferay.Mail.loadManageFolders(<%= accountId %>);
			}
		}

		A.one('#<portlet:namespace />addFolder').on(
			'click',
			function(event) {
				Liferay.Mail.setStatus('info', '<liferay-ui:message key="adding-folder" />', true);

				var displayName = A.one('#<portlet:namespace />displayName').get('value');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/add_folder',
					{
						data: {
							accountId: <%= accountId %>,
							displayName: displayName
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: <portlet:namespace />onIOFailure,
							success: <portlet:namespace />onIOSuccess
						}
					}
				);
			}
		);

		A.all('.mail-portlet .delete-folder').on(
			'click',
			function(event) {
				if (!confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-folder" />')) {
					return;
				}

				Liferay.Mail.setStatus('info', '<liferay-ui:message key="deleting-folder" />', true);

				var folderId = event.currentTarget.getAttribute('data-folderId');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/delete_folder',
					{
						data: {folderId: folderId},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: <portlet:namespace />onIOFailure,
							success: <portlet:namespace />onIOSuccess
						}
					}
				);
			}
		);

		A.all('.mail-portlet .rename-folder').on(
			'click',
			function(event) {
				var folderId = event.currentTarget.getAttribute('data-folderId');

				new A.Dialog(
					{
						centered: true,
						cssClass: 'mail-dialog',
						destroyOnClose: true,
						modal: true,
						title: '<liferay-ui:message key="rename-folder" />',
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{
						data: {folderId: folderId},
						uri: themeDisplay.getLayoutURL() + '/-/mail/edit_folder'
					}
				).render();
			}
		);
	</aui:script>
</c:if>