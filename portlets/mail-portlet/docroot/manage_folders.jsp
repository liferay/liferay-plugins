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

long accountId = ParamUtil.getLong(request, "accountId");
%>

<c:if test="<%= mailManager != null %>">
	<aui:layout>
		<aui:column>
			<aui:input name="displayName" value="" />
		</aui:column>
		<aui:column>
			<aui:button name="addFolder" value="add-folder" />
		</aui:column>
	</aui:layout>

	<%
	List<Folder> folders = mailManager.getFolders(accountId, false, true);

	for (Folder folder : folders) {
		String folderLabel = folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")";
	%>

		<aui:layout>
			<aui:column columnWidth="30">
				<%= folder.getDisplayName() %>
			</aui:column>
			<aui:column>
				<aui:a cssClass="delete-folder" folderId="<%= folder.getFolderId() %>" href="javascript:;">delete-folder</aui:a>
			</aui:column>
		</aui:layout>

	<%
	}
	%>
</c:if>

<aui:script use="aui-base, aui-io">
A.one('#<portlet:namespace />addFolder').on(
	'click',
	function() {
		var displayName = A.one('#<portlet:namespace />displayName').get('value');

		A.io.request(
			themeDisplay.getLayoutURL() + '/-/mail/add_folder',
			{
				data: {
					accountId: <%= accountId %>,
					displayName: displayName
				},
				method: 'POST',
				on: {
					failure: function (event, id, obj) {
						Liferay.Mail.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
					},
					success: function (event, id, obj) {
						var responseData = this.get('responseData');

						Liferay.Mail.setStatus(results.status, results.message);

						if (results.status == 'success') {
							Liferay.Mail.loadManageFolders(<%= accountId %>);
						}
					}
				}
			}
		);
	}
);
</aui:script>