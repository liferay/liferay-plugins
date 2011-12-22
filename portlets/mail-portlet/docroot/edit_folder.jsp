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
long folderId = ParamUtil.getLong(request, "folderId");

Folder folder = FolderLocalServiceUtil.getFolder(folderId);
%>

<aui:layout cssClass="mail-status" />

<aui:form name="dialogFm" onSubmit="event.preventDefault();">
	<aui:input name="folderId" type="hidden" value="<%= folderId %>" />

	<aui:input name="displayName" value="<%= folder.getDisplayName() %>" />

	<aui:button-row>
		<aui:button name="updateFolder" type="submit" value="update-folder" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-io">
	var A = AUI();

	var form = A.one('#<portlet:namespace />dialogFm');

	form.on(
		'submit',
		function(event) {
			event.preventDefault();

			Liferay.Mail.setStatus('info', '<liferay-ui:message key="updating-folder" />', true);

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/rename_folder',
				{
					dataType: 'json',
					form: {
						id: form.getDOM()
					},
					on: {
						failure: function(event, id, obj) {
							Liferay.Mail.setStatus('error', '<liferay-ui:message key="unable-to-connect-with-mail-server" />');
						},
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							Liferay.Mail.setStatus(responseData.status, responseData.message);

							if (responseData.status == 'success') {
								Liferay.Mail.loadFolders(Liferay.Mail.accountId);

								A.DialogManager.closeByChild(form);
							}
						}
					}
				}
			);
		}
	);
</aui:script>