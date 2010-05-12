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
long inboxFolderId = ParamUtil.getLong(request, "inboxFolderId");

MailManager mailManager = MailManager.getInstance(request);
%>

<aui:input label="please-enter-your-password" name="password" type="password" />

<aui:button name="login" value="login" />

<aui:script use="liferay-plugin-mail">
	var A = AUI();

	A.one('#<portlet:namespace />login').on(
		'click',
		function() {
			var password = A.one('#<portlet:namespace />password').get('value');

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/store_password',
				{
					data: {
						accountId: '<%= accountId %>',
						password: password
					},
					dataType: 'json',
					method: 'POST',
					on: {
						failure: function (event, id, obj) {
							var results = this.get('responseData');

							console.log(responseData);
						},
						success: function (event, id, obj) {
							var results = this.get('responseData');

							if (results.status == 'success') {
								Liferay.Mail.loadAccount(<%= accountId %>, <%= inboxFolderId %>);
							}
							else {
								//Liferay.Mail.setStatusMessage('error', results.message);
							}
						}
					}
				}
			);
		}
	);
</aui:script>