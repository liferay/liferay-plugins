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

JSONObject preconfiguredAccounts = JSONFactoryUtil.createJSONObject(PortletProps.get("preconfigured.mail.accounts"));

JSONArray accounts = preconfiguredAccounts.getJSONArray("accounts");

String tabs = "";

for (int i = 0; i < accounts.length(); i++) {
	JSONObject account = accounts.getJSONObject(i);

	String titleLanguageKey = account.getString("titleLanguageKey");

	tabs += titleLanguageKey;

	if (i != (accounts.length() - 1)) {
		tabs += ",";
	}
}

String[] incomingPorts = StringUtil.split(PortletProps.get("incoming.ports"), ",");
String[] outgoingPorts = StringUtil.split(PortletProps.get("outgoing.ports"), ",");
%>

<liferay-ui:tabs
	names="<%= tabs %>"
	refresh="<%= false %>"
>

<%
for (int i = 0; i < accounts.length(); i++) {
	JSONObject account = accounts.getJSONObject(i);

	String titleLanguageKey = account.getString("titleLanguageKey");
	String descriptionLanguageKey = account.getString("descriptionLanguageKey");
	String address = account.getString("address");
	String protocol = account.getString("protocol");
	boolean useLocalPartAsLogin = account.getBoolean("useLocalPartAsLogin");
	boolean hideSettings = account.getBoolean("hideSettings");
	String incomingHostName = account.getString("incomingHostName");
	int incomingPort = account.getInt("incomingPort");
	boolean incomingSecure = account.getBoolean("incomingSecure");
	String outgoingHostName = account.getString("outgoingHostName");
	int outgoingPort = account.getInt("outgoingPort");
	boolean outgoingSecure = account.getBoolean("outgoingSecure");
	String folderPrefix = account.getString("folderPrefix");
	%>

	<liferay-ui:section>
		<aui:fieldset>
			<liferay-ui:message key="<%= descriptionLanguageKey %>" />

			<c:choose>
				<c:when test="<%= hideSettings %>">
					<aui:input name="address" value="<%= address %>" />
					<aui:input type="hidden" name="incomingHostName" value="<%= incomingHostName %>" />
					<aui:input type="hidden" name="incomingPort" value="<%= incomingPort %>" />
					<aui:input type="hidden" name="incomingSecure" value="<%= incomingSecure %>" />
					<aui:input type="hidden" name="outgoingHostName" value="<%= outgoingHostName %>" />
					<aui:input type="hidden" name="outgoingPort" value="<%= outgoingPort %>" />
					<aui:input type="hidden" name="outgoingSecure" value="<%= outgoingSecure %>" />

					<c:if test="<%= !useLocalPartAsLogin %>">
						<aui:input name="login" value="" />
					</c:if>

					<aui:input type="password" name="password" />
					<aui:input type="checkbox" name="savePassword" value="false" />
					<aui:input type="hidden" name="folderPrefix" value="<%= folderPrefix %>" />
					<aui:input type="hidden" name="useLocalPartAsLogin" value="<%= useLocalPartAsLogin %>" />
				</c:when>
				<c:otherwise>
					<aui:fieldset label="account-settings">
						<aui:input name="address" value="<%= address %>" />
						<c:if test="<%= !useLocalPartAsLogin %>">
							<aui:input name="login" value="" />
						</c:if>

						<aui:input type="password" name="password" />
						<aui:input type="checkbox" name="savePassword" value="false" />
					</aui:fieldset>
					<aui:fieldset label="incoming-settings">
						<aui:input name="incomingHostName" />
						<aui:select name="incomingPort">

						<%
						for (String tempIncomingPort : incomingPorts) {
						%>

							<aui:option selected="<%= tempIncomingPort.equals(incomingPort) %>" value="<%= tempIncomingPort %>"><%= tempIncomingPort %></aui:option>

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
						for (String tempOutgoingPort : outgoingPorts) {
						%>

							<aui:option selected="<%= tempOutgoingPort.equals(outgoingPort) %>" value="<%= tempOutgoingPort %>"><%= tempOutgoingPort %></aui:option>

						<%
						}
						%>

						</aui:select>
						<aui:input label="use-secure-outgoing-connection" name="outgoingSecure" type="checkbox" />
					</aui:fieldset>

					<aui:input type="hidden" name="useLocalPartAsLogin" value="true" />
				</c:otherwise>
			</c:choose>

			<aui:input type="hidden" name="personalName" value="" />
			<aui:input type="hidden" name="protocol" value="<%= protocol %>" />
			<aui:input type="hidden" name="signature" value="" />
			<aui:input type="hidden" name="useSignature" value="false" />
			<aui:input type="hidden" name="folderPrefix" value="<%= folderPrefix %>" />
			<aui:input type="hidden" name="defaultSender" value="false" />
			<aui:button onClick="" value="Add Account" />
		</aui:fieldset>
	</liferay-ui:section>

	<%
}
%>

</liferay-ui:tabs>

<aui:script>
var A = AUI();

A.one('#<portlet:namespace />addAccount').on('click', function() {
	var address = A.one('#<portlet:namespace />address').get('value');
	var personalName = A.one('#<portlet:namespace />personalName').get('value');
	var protocol = A.one('#<portlet:namespace />protocol').get('value');
	var incomingHostName = A.one('#<portlet:namespace />incomingHostName').get('value');
	var incomingPort = A.one('#<portlet:namespace />incomingPort').get('value');
	var incomingSecure = A.one('#<portlet:namespace />incomingSecure').get('value');
	var outgoingHostName = A.one('#<portlet:namespace />outgoingHostName').get('value');
	var outgoingPort = A.one('#<portlet:namespace />outgoingPort').get('value');
	var outgoingSecure = A.one('#<portlet:namespace />outgoingSecure').get('value');
	var login = A.one('#<portlet:namespace />login').get('value');
	var password = A.one('#<portlet:namespace />password').get('value');
	var savePassword = A.one('#<portlet:namespace />savePassword').get('value');
	var signature = A.one('#<portlet:namespace />signature').get('value');
	var useSignature = A.one('#<portlet:namespace />useSignature').get('value');
	var folderPrefix = A.one('#<portlet:namespace />folderPrefix').get('value');
	var defaultSender = A.one('#<portlet:namespace />defaultSender').get('value');
	var useLocalPartAsLogin = A.one('#<portlet:namespace />useLocalPartAsLogin').get('value');

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

</aui:script>