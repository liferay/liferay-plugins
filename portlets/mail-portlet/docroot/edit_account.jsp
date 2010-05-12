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

Account account = AccountLocalServiceUtil.getAccount(accountId);
%>

<aui:input name="accountId" type="hidden" value="<%= account.getAccountId() %>" />

<aui:input name="personalName" value="<%= account.getPersonalName() %>" />

<aui:input name="password" type="password" />

<aui:input name="savePassword" type="checkbox" value="<%= account.isSavePassword() %>" />

<aui:input name="signature" value="<%= account.getSignature() %>" />

<aui:input name="useSignature" type="checkbox" value="<%= account.getUseSignature() %>" />

<aui:input name="folderPrefix" value="<%= account.getFolderPrefix() %>" />

<aui:input name="defaultSender" type="checkbox" value="<%= account.getDefaultSender() %>" />

<aui:layout>
	<aui:column>
		<aui:button name="deleteAccount" value="Delete Account" />
	</aui:column>

	<aui:column>
		<aui:button name="updateAccount" value="Update Account" />
	</aui:column>
</aui:layout>

<aui:script>
var A = AUI();

A.one('#<portlet:namespace />updateAccount').on('click', function() {
	var accountId = A.one('#<portlet:namespace />accountId').get('value');
	var personalName = A.one('#<portlet:namespace />personalName').get('value');
	var password = A.one('#<portlet:namespace />password').get('value');
	var savePassword = A.one('#<portlet:namespace />savePassword').get('value');
	var signature = A.one('#<portlet:namespace />signature').get('value');
	var useSignature = A.one('#<portlet:namespace />useSignature').get('value');
	var folderPrefix = A.one('#<portlet:namespace />folderPrefix').get('value');
	var defaultSender = A.one('#<portlet:namespace />defaultSender').get('value');

	A.io.request(
		themeDisplay.getLayoutURL() + '/-/mail/update_account',
		{
			data: {
				accountId: accountId,
				personalName: personalName,
				password: password,
				savePassword: savePassword,
				signature: signature,
				useSignature: useSignature,
				folderPrefix: folderPrefix,
				defaultSender: defaultSender
			},
			method: 'POST',
			on: {
				failure: function (event, id, obj) {
					var responseData = this.get('responseData');

					console.log(responseData);
				},
				success: function (event, id, obj) {
					var responseData = this.get('responseData');

					console.log(responseData);
				}
			}
		}
	);
});

A.one('#<portlet:namespace />deleteAccount').on('click', function() {
	var accountId = A.one('#<portlet:namespace />accountId').get('value');

	var answer = confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-account" />');

	if (answer) {

		A.io.request(
			themeDisplay.getLayoutURL() + '/-/mail/delete_account',
			{
				data: {
					accountId: accountId
				},
				method: 'POST',
				on: {
					failure: function (event, id, obj) {
						var responseData = this.get('responseData');

						console.log(responseData);
					},
					success: function (event, id, obj) {
						var responseData = this.get('responseData');

						console.log(responseData);
					}
				}
			}
		);
	}
});
</aui:script>