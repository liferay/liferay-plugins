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

Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);
%>

<aui:layout cssClass="mail-status" />

<aui:form name="dialogFm" onSubmit="event.preventDefault();">
	<aui:fieldset column="<%= true %>" cssClass="aui-w50" label="general">
		<aui:input name="accountId" type="hidden" value="<%= mailAccount.getAccountId() %>" />
		<aui:input name="signature" type="hidden" value="<%= mailAccount.getSignature() %>" />
		<aui:input name="useSignature" type="hidden" value="<%= mailAccount.getUseSignature() %>" />
		<aui:input name="folderPrefix" type="hidden" value="<%= mailAccount.getFolderPrefix() %>" />
		<aui:input name="defaultSender" type="hidden" value="<%= mailAccount.getDefaultSender() %>" />

		<aui:input name="personalName" value="<%= mailAccount.getPersonalName() %>" />

		<aui:input name="password" type="password" />

		<aui:input name="savePassword" type="checkbox" value="<%= mailAccount.isSavePassword() %>" />
	</aui:fieldset>

	<aui:fieldset column="<%= true %>" cssClass="aui-w50" label="folders">
		<aui:select name="inboxFolderId" label="inbox">

			<%
			long selectedFolderId = mailAccount.getInboxFolderId();
			%>

			<%@ include file="/select_folder.jspf" %>
		</aui:select>

		<aui:select name="sentFolderId" label="sent">

			<%
			long selectedFolderId = mailAccount.getSentFolderId();
			%>

			<%@ include file="/select_folder.jspf" %>
		</aui:select>

		<aui:select name="draftFolderId" label="draft">

			<%
			long selectedFolderId = mailAccount.getDraftFolderId();
			%>

			<%@ include file="/select_folder.jspf" %>
		</aui:select>

		<aui:select name="trashFolderId" label="trash">

			<%
			long selectedFolderId = mailAccount.getTrashFolderId();
			%>

			<%@ include file="/select_folder.jspf" %>
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button name="updateAccount" type="submit" value="update-account" />
	</aui:button-row>
</aui:form>

<div>
	<a class="delete-account" href="javascript:;"><liferay-ui:message key="delete-account" /></a><br />

	<a class="synchronize-account" href="javascript:;"><liferay-ui:message key="synchronize-account" /></a> <liferay-ui:icon-help message="synchronizing-accounts-with-a-large-number-of-messages-may-take-minutes-to-complete" />
</div>

<aui:script use="aui-io">
	var form = A.one('#<portlet:namespace />dialogFm');

	form.on(
		'submit',
		function(event) {
			event.preventDefault();

			Liferay.Mail.setStatus('info', '<liferay-ui:message key="updating-account" />', true);

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/update_account',
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

							A.DialogManager.closeByChild(form);
						}
					}
				}
			);
		}
	);

	A.one('.mail-dialog .delete-account').on(
		'click',
		function(event) {
			if (!confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-account" />')) {
				return;
			}

			Liferay.Mail.setStatus('info', '<liferay-ui:message key="deleting-account" />');

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/delete_account',
				{
					data: {accountId: <%= accountId %>},
					dataType: 'json',
					method: 'POST',
					on: {
						failure: function(event, id, obj) {
							Liferay.Mail.setStatus('error', '<liferay-ui:message key="unable-to-connect-with-mail-server" />');
						},
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							Liferay.Mail.setStatus(responseData.status, responseData.message);

							if (responseData.status == 'success') {
								Liferay.Mail.reset();

								A.DialogManager.closeByChild(form);
							}
						}
					}
				}
			);
		}
	);

	A.one('.mail-dialog .synchronize-account').on(
		'click',
		function(event) {
			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/synchronize_account',
				{
					data: {accountId: <%= accountId %>},
					dataType: 'json',
					method: 'POST',
					on: {
						failure: function(event, id, obj) {
							Liferay.Mail.setStatus('error', '<liferay-ui:message key="unable-to-connect-with-mail-server" />');
						},
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							Liferay.Mail.setStatus('success', '<liferay-ui:message key="synchronizing-messages-in-the-background" />');
						}
					}
				}
			);
		}
	);
</aui:script>