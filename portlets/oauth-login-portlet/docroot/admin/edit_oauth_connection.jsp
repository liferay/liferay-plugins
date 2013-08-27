<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

OAuthConnection oAuthConnection = (OAuthConnection)request.getAttribute(WebKeys.OAUTH_CONNECTION);

long oAuthConnectionId = BeanParamUtil.getLong(oAuthConnection, request, "oAuthConnectionId");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (oAuthConnection == null) ? "new-oauth-connection" : oAuthConnection.getName() %>'
/>

<portlet:actionURL name="updateOAuthConnection" var="updateOAuthConnectionURL">
	<portlet:param name="mvcPath" value="/admin/edit_oauth_connection.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= updateOAuthConnectionURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="oAuthConnectionId" type="hidden" value="<%= oAuthConnectionId %>" />

	<liferay-ui:error exception="<%= AccessTokenURLException.class %>" message="please-enter-a-valid-access-token-url" />
	<liferay-ui:error exception="<%= AuthorizeURLException.class %>" message="please-enter-a-valid-authorize-url" />
	<liferay-ui:error exception="<%= OAuthConnectionNameException.class %>" message="please-enter-a-valid-oauth-connection-name" />
	<liferay-ui:error exception="<%= RedirectURLException.class %>" message="please-enter-a-valid-redirect-url" />
	<liferay-ui:error exception="<%= RequestTokenURLException.class %>" message="please-enter-a-valid-request-token-url" />
	<liferay-ui:error exception="<%= SocialAccountIdURLException.class %>" message="please-enter-a-valid-social-account-id-url" />

	<aui:model-context bean="<%= oAuthConnection %>" model="<%= OAuthConnection.class %>" />

	<liferay-ui:panel-container extended="<%= true %>" id="oAuthConnectionPanelContainer" persistState="<%= true %>">
		<aui:fieldset>
			<aui:input name="enabled" type="checkbox" value="<%= (oAuthConnection != null) && (oAuthConnection.getEnabled()) %>" />

			<aui:input name="name" />

			<aui:input name="description" type="textarea" />

			<aui:input name="icon" type="file" />
		</aui:fieldset>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="oAuthConnectionOAuthInfo" persistState="<%= true %>" title="oauth-connection-oauth-info">
			<aui:fieldset>
				<aui:select label="oauth-version" name="oAuthVersion">
					<aui:option label="<%= OAuthConstants.LABEL_OAUTH_20 %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getOAuthVersion() == OAuthConstants.OAUTH_20) %>" value="<%= OAuthConstants.OAUTH_20 %>" />
					<aui:option label="<%= OAuthConstants.LABEL_OAUTH_10A %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getOAuthVersion() == OAuthConstants.OAUTH_10A) %>" value="<%= OAuthConstants.OAUTH_10A %>" />
				</aui:select>

				<aui:input name="key" />

				<aui:input name="secret" />

				<aui:input name="scope" />

				<aui:input label="authorize-url" name="authorizeURL" />

				<aui:input label="access-token-url" name="accessTokenURL" />

				<aui:select name="accessTokenVerb">
					<aui:option label="<%= OAuthConstants.LABEL_GET %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getAccessTokenVerb() == OAuthConstants.GET) %>" value="<%= OAuthConstants.GET %>" />
					<aui:option label="<%= OAuthConstants.LABEL_POST %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getAccessTokenVerb() == OAuthConstants.POST) %>" value="<%= OAuthConstants.POST %>" />
				</aui:select>

				<aui:select name="accessTokenExtractorType">
					<aui:option label="<%= OAuthConstants.LABEL_EXTRACTOR_DEFAULT %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getAccessTokenExtractorType() == OAuthConstants.EXTRACTOR_DEFAULT) %>" value="<%= OAuthConstants.EXTRACTOR_DEFAULT %>" />
					<aui:option label="<%= OAuthConstants.LABEL_EXTRACTOR_JSON_OBJECT %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getAccessTokenExtractorType() == OAuthConstants.EXTRACTOR_JSON_OBJECT) %>" value="<%= OAuthConstants.EXTRACTOR_JSON_OBJECT %>" />
				</aui:select>

				<div id="<portlet:namespace />requestTokenDiv">
					<aui:input label="request-token-url" name="requestTokenURL" />

					<aui:select name="requestTokenVerb">
						<aui:option label="<%= OAuthConstants.LABEL_GET %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getRequestTokenVerb() == OAuthConstants.GET) %>" value="<%= OAuthConstants.GET %>" />
						<aui:option label="<%= OAuthConstants.LABEL_POST %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getRequestTokenVerb() == OAuthConstants.POST) %>" value="<%= OAuthConstants.POST %>" />
					</aui:select>

					<aui:select name="SignatureServiceType">
						<aui:option label="<%= OAuthConstants.LABEL_SIGNATURESERVICE_HMACSHA1 %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getSignatureServiceType() == OAuthConstants.SIGNATURESERVICE_HMACSHA1) %>" value="<%= OAuthConstants.SIGNATURESERVICE_HMACSHA1 %>" />
						<aui:option label="<%= OAuthConstants.LABEL_SIGNATURESERVICE_PLAINTEXT %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getSignatureServiceType() == OAuthConstants.SIGNATURESERVICE_PLAINTEXT) %>" value="<%= OAuthConstants.SIGNATURESERVICE_PLAINTEXT %>" />
					</aui:select>
				</div>

				<aui:input label="redirect-url" name="redirectURL" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="oAuthConnectionSocialAccountIdInfo" persistState="<%= true %>" title="oAuthConnection-social-account-id-info">
			<aui:fieldset>
				<aui:input label="social-account-id-url" name="socialAccountIdURL" />

				<aui:select label="social-account-id-url-verb" name="socialAccountIdURLVerb">
					<aui:option label="<%= OAuthConstants.LABEL_GET %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getSocialAccountIdURLVerb() == OAuthConstants.GET) %>" value="<%= OAuthConstants.GET %>" />
					<aui:option label="<%= OAuthConstants.LABEL_POST %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getSocialAccountIdURLVerb() == OAuthConstants.POST) %>" value="<%= OAuthConstants.POST %>" />
				</aui:select>

				<div id="<portlet:namespace />socialAccountIdTypeDiv">
					<aui:select name="socialAccountIdType">
						<aui:option label="<%= OAuthConstants.LABEL_EXTRACTOR_JSON_OBJECT %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getSocialAccountIdType() == OAuthConstants.EXTRACTOR_JSON_OBJECT) %>" value="<%= OAuthConstants.EXTRACTOR_JSON_OBJECT %>" />
						<aui:option label="<%= OAuthConstants.LABEL_EXTRACTOR_CUSTOM %>" selected="<%= (oAuthConnection != null) && (oAuthConnection.getSocialAccountIdType() == OAuthConstants.EXTRACTOR_CUSTOM) %>" value="<%= OAuthConstants.EXTRACTOR_CUSTOM %>" />
					</aui:select>
				</div>

				<div id="<portlet:namespace />socialAccountIdFieldDiv">
					<aui:input label="social-account-id-field" name="socialAccountIdField" />
				</div>

				<div id="<portlet:namespace />socialAccountIdScriptDiv">
					<aui:input name="socialAccountIdScript" type="textarea" />
				</div>
			</aui:fieldset>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	var oAuthVersion = A.one('#<portlet:namespace />oAuthVersion');
	var requestTokenDiv = A.one('#<portlet:namespace />requestTokenDiv');

	var socialAccountIdType = A.one('#<portlet:namespace />socialAccountIdType');
	var socialAccountIdScriptDiv = A.one('#<portlet:namespace />socialAccountIdScriptDiv');
	var socialAccountIdFieldDiv = A.one('#<portlet:namespace />socialAccountIdFieldDiv');

	if (oAuthVersion.val() == <%= OAuthConstants.OAUTH_20 %>) {
		requestTokenDiv.hide();
	}

	if (socialAccountIdType.val != <%= OAuthConstants.EXTRACTOR_CUSTOM %>) {
		socialAccountIdScriptDiv.hide();
	}

	oAuthVersion.on(
		'change',
		function(event) {
			if (oAuthVersion.val() == <%= OAuthConstants.OAUTH_20 %>) {
				requestTokenDiv.hide();
			}
			else {
				requestTokenDiv.show();
			}
		}
	)

	socialAccountIdType.on(
		'change',
		function(event) {
			if (socialAccountIdType.val() == <%= OAuthConstants.EXTRACTOR_CUSTOM %>) {
				socialAccountIdScriptDiv.show();
				socialAccountIdFieldDiv.hide();
			}
			else {
				socialAccountIdFieldDiv.show();
				socialAccountIdScriptDiv.hide();
			}
		}
	)
</aui:script>