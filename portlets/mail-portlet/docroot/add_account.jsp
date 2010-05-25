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

JSONObject defaultAccountsJSONObject = JSONFactoryUtil.createJSONObject(PortletPropsValues.DEFAULT_ACCOUNTS);

JSONArray accountsJSONArray = defaultAccountsJSONObject.getJSONArray("accounts");

String tabs1Names = "";

for (int i = 0; i < accountsJSONArray.length(); i++) {
	JSONObject accountJSONObject = accountsJSONArray.getJSONObject(i);

	String titleLanguageKey = accountJSONObject.getString("titleLanguageKey");

	tabs1Names += titleLanguageKey;

	if (i != (accountsJSONArray.length() - 1)) {
		tabs1Names += ",";
	}
}
%>

<liferay-ui:tabs
	names="<%= tabs1Names %>"
	refresh="<%= false %>"
>

	<%
	for (int i = 0; i < accountsJSONArray.length(); i++) {
		JSONObject accountJSONObject = accountsJSONArray.getJSONObject(i);

		String titleLanguageKey = accountJSONObject.getString("titleLanguageKey");
		String descriptionLanguageKey = accountJSONObject.getString("descriptionLanguageKey");
		String address = accountJSONObject.getString("address");
		String protocol = accountJSONObject.getString("protocol");
		boolean useLocalPartAsLogin = accountJSONObject.getBoolean("useLocalPartAsLogin");
		boolean hideSettings = accountJSONObject.getBoolean("hideSettings");
		String incomingHostName = accountJSONObject.getString("incomingHostName");
		int incomingPort = accountJSONObject.getInt("incomingPort");
		boolean incomingSecure = accountJSONObject.getBoolean("incomingSecure");
		String outgoingHostName = accountJSONObject.getString("outgoingHostName");
		int outgoingPort = accountJSONObject.getInt("outgoingPort");
		boolean outgoingSecure = accountJSONObject.getBoolean("outgoingSecure");
		String folderPrefix = accountJSONObject.getString("folderPrefix");
	%>

		<liferay-ui:section>
			<aui:fieldset>
				<liferay-ui:message key="<%= descriptionLanguageKey %>" />

				<aui:fieldset label="account-settings">
					<aui:input name="address" value="<%= address %>" />

					<c:choose>
						<c:when test="<%= !useLocalPartAsLogin %>">
							<aui:input name="login" />
						</c:when>
						<c:otherwise>
							<aui:input name="login" type="hidden" />
						</c:otherwise>
					</c:choose>

					<aui:input name="password" type="password" />

					<aui:input name="savePassword" type="checkbox" value="false" />
				</aui:fieldset>

				<c:choose>
					<c:when test="<%= hideSettings %>">
						<aui:input name="incomingHostName" type="hidden" value="<%= incomingHostName %>" />

						<aui:input name="incomingPort" type="hidden" value="<%= incomingPort %>" />

						<aui:input name="incomingSecure" type="hidden" value="<%= incomingSecure %>" />

						<aui:input name="outgoingHostName" type="hidden" value="<%= outgoingHostName %>" />

						<aui:input name="outgoingPort" type="hidden" value="<%= outgoingPort %>" />

						<aui:input name="outgoingSecure" type="hidden" value="<%= outgoingSecure %>" />
					</c:when>
					<c:otherwise>
						<aui:fieldset label="incoming-settings">
							<aui:input name="incomingHostName" />

							<aui:select name="incomingPort">

								<%
								for (int curIncomingPort : PortletPropsValues.INCOMING_PORTS) {
								%>

									<aui:option selected="<%= incomingPort == curIncomingPort %>" value="<%= curIncomingPort %>"><%= curIncomingPort %></aui:option>

								<%
								}
								%>

							</aui:select>

							<aui:input label="use-secure-incoming-connection" name="incomingSecure" type="checkbox" />
						</aui:fieldset>

						<aui:fieldset label="outgoing-settings">
							<aui:input label="outgoing-smtp-server" name="outgoingHostName" />

							<aui:select name="outgoingPort">

								<%
								for (int curOutgoingPort : PortletPropsValues.OUTGOING_PORTS) {
								%>

									<aui:option selected="<%= outgoingPort == curOutgoingPort %>" value="<%= curOutgoingPort %>"><%= curOutgoingPort %></aui:option>

								<%
								}
								%>

							</aui:select>

							<aui:input label="use-secure-outgoing-connection" name="outgoingSecure" type="checkbox" />
						</aui:fieldset>
					</c:otherwise>
				</c:choose>

				<aui:input name="personalName" type="hidden" value="<%= user.getFullName() %>" />

				<aui:input name="protocol" type="hidden" value="<%= protocol %>" />

				<aui:input name="signature" type="hidden" />

				<aui:input name="useSignature" type="hidden" value="false" />

				<aui:input name="folderPrefix" type="hidden" value="<%= folderPrefix %>" />

				<aui:input name="defaultSender" type="hidden" value="false" />

				<aui:input name="useLocalPartAsLogin" type="hidden" value="<%= useLocalPartAsLogin %>" />

				<aui:button cssClass="add-account" value="add-account" />
			</aui:fieldset>
		</liferay-ui:section>

	<%
	}
	%>

</liferay-ui:tabs>

<aui:script>
	var A = AUI();

	A.all('.add-account').each(
		function(node, idx, lst) {
			node.on('click', function() {
				var parent = this.get('parentNode');

				console.log(parent);

				var address = parent.one('#<portlet:namespace />address').get('value');
				var personalName = parent.one('#<portlet:namespace />personalName').get('value');
				var protocol = parent.one('#<portlet:namespace />protocol').get('value');
				var incomingHostName = parent.one('#<portlet:namespace />incomingHostName').get('value');
				var incomingPort = parent.one('#<portlet:namespace />incomingPort').get('value');
				var incomingSecure = parent.one('#<portlet:namespace />incomingSecure').get('value');
				var outgoingHostName = parent.one('#<portlet:namespace />outgoingHostName').get('value');
				var outgoingPort = parent.one('#<portlet:namespace />outgoingPort').get('value');
				var outgoingSecure = parent.one('#<portlet:namespace />outgoingSecure').get('value');
				var login = parent.one('#<portlet:namespace />login').get('value');
				var password = parent.one('#<portlet:namespace />password').get('value');
				var savePassword = parent.one('#<portlet:namespace />savePassword').get('value');
				var signature = parent.one('#<portlet:namespace />signature').get('value');
				var useSignature = parent.one('#<portlet:namespace />useSignature').get('value');
				var folderPrefix = parent.one('#<portlet:namespace />folderPrefix').get('value');
				var defaultSender = parent.one('#<portlet:namespace />defaultSender').get('value');
				var useLocalPartAsLogin = parent.one('#<portlet:namespace />useLocalPartAsLogin').get('value');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/update_account',
					{
						data: {
							address: address,
							personalName: personalName,
							protocol: protocol,
							incomingHostName: incomingHostName,
							incomingPort: incomingPort,
							incomingSecure: incomingSecure,
							outgoingHostName: outgoingHostName,
							outgoingPort: outgoingPort,
							outgoingSecure: outgoingSecure,
							login: login,
							password: password,
							savePassword: savePassword,
							signature: signature,
							useSignature: useSignature,
							folderPrefix: folderPrefix,
							defaultSender: defaultSender,
							useLocalPartAsLogin: useLocalPartAsLogin
						},
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								var results = this.get('responseData');

								console.log(results);
							},
							success: function (event, id, obj) {
								var results = this.get('responseData');

								Liferay.Mail.setStatus(results.status, results.message);

								if (results.status = 'success') {
									Liferay.Mail.loadAccounts(Liferay.Mail.getAccountId());
								}
							}
						}
					}
				);
			});
		}
	);
</aui:script>