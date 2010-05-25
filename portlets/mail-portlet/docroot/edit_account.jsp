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

Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);
%>

<aui:form name="fm" onSubmit="event.preventDefault();">
	<aui:input name="accountId" type="hidden" value="<%= mailAccount.getAccountId() %>" />

	<aui:input name="personalName" value="<%= mailAccount.getPersonalName() %>" />

	<aui:input name="password" type="password" />

	<aui:input name="savePassword" type="checkbox" value="<%= mailAccount.isSavePassword() %>" />

	<aui:input name="signature" type="hidden" value="<%= mailAccount.getSignature() %>" />

	<aui:input name="useSignature" type="hidden" value="<%= mailAccount.getUseSignature() %>" />

	<aui:input name="folderPrefix" type="hidden" value="<%= mailAccount.getFolderPrefix() %>" />

	<aui:input name="defaultSender" type="hidden" value="<%= mailAccount.getDefaultSender() %>" />

	<aui:layout>
		<aui:column>
			<aui:button name="updateAccount" type="submit" value="update-account" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="close-edit-account" name="cancel" value="cancel" />
		</aui:column>
	</aui:layout>

	<aui:a href="javascript:;" onClick='<%= "Liferay.Mail.deleteAccount(" + accountId + ");" %>'><liferay-ui:message key="delete-account" /></aui:a>
</aui:form>

<aui:script>
	var A = AUI();

	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			event.preventDefault();

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/update_account',
				{
					form: {id: form.getDOM()},
					dataType: 'json',
					on: {
						failure: function (event, id, obj) {
							Liferay.Mail.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
						},
						success: function (event, id, obj) {
							var results = this.get('responseData');

							Liferay.Mail.setStatus(results.status, results.message);

							if (results.status == 'success') {

								// Close update window

							}
						}
					}
				}
			);
		}
	);
</aui:script>