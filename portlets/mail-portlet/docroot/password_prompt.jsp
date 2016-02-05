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
long accountId = ParamUtil.getLong(request, "accountId");
long inboxFolderId = ParamUtil.getLong(request, "inboxFolderId");

MailManager mailManager = MailManager.getInstance(request);
%>

<div class="mail-status"></div>

<aui:form name="dialogFm" onSubmit="event.preventDefault();">
	<aui:input name="accountId" type="hidden" value="<%= accountId %>" />

	<%
	Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);

	String taglibLabel = LanguageUtil.format(request, "please-enter-your-password-for-x", mailAccount.getAddress(), false);
	%>

	<aui:input label="<%= taglibLabel %>" name="password" type="password" />

	<aui:button-row>
		<aui:button name="login" type="submit" value="login" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-io-deprecated">
	var form = A.one('#<portlet:namespace />dialogFm');

	A.one('#<portlet:namespace />login').on(
		'click',
		function(event) {
			event.preventDefault();

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/store_password',
				{
					dataType: 'JSON',
					form: {
						id: form.getDOMNode()
					},
					method: 'POST',
					on: {
						failure: function(event, id, obj) {
							Liferay.Mail.setStatus('error', '<liferay-ui:message key="unable-to-connect-with-mail-server" />');
						},
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							Liferay.Mail.setStatus(responseData.status, responseData.message);

							if (responseData.status == 'success') {
								Liferay.Mail.loadAccount(<%= accountId %>, <%= inboxFolderId %>);
							}
						}
					}
				}
			);
		}
	);
</aui:script>