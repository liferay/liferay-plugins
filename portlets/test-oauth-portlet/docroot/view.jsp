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
String webId = ParamUtil.getString(request, "webId", null);
%>

<aui:form name="fm">
	<liferay-ui:error key="authenticationFailed" message="authentication-failed" />

	<c:choose>
		<c:when test="<%= !OAuthUtil.isConsumerReady(portletPreferences) %>">
			<liferay-ui:header title="set-oauth-consumer-credentials" />

			<aui:input name="hostName" required="<%= true %>" value="<%= hostName %>" />

			<aui:input name="hostPort" required="<%= true %>" value="<%= hostPort %>" />

			<aui:input label="access-uri" name="accessURI" required="<%= true %>" value="<%= accessURI %>" />

			<aui:input label="authorize-uri" name="authorizeURI" required="<%= true %>" value="<%= authorizeURI %>" />

			<aui:input label="request-uri" name="requestURI" required="<%= true %>" value="<%= requestURI %>" />

			<aui:input helpMessage="check-to-test-server-side-defined-callback-url" label="use-server-provided-callback-url" name="useServerProvidedCallbackUrl" type="checkbox" />

			<aui:input name="key" required="<%= true %>" />

			<aui:input name="secret" required="<%= true %>" />

			<aui:select label="oauth-provider-authorize-window-state" name="windowState">
				<aui:option label="oauth-provider-default" value="" />
				<aui:option label="exclusive" value="<%= LiferayWindowState.EXCLUSIVE.toString() %>" />
				<aui:option label="maximized" value="<%= LiferayWindowState.MAXIMIZED.toString() %>" />
				<aui:option label="pop-up" value="<%= LiferayWindowState.POP_UP.toString() %>" />
			</aui:select>

			<aui:button-row>
				<aui:button onClick='<%= renderResponse.getNamespace() + "setupOAuthConsumer();" %>' value="save" />
			</aui:button-row>
		</c:when>
		<c:when test="<%= !OAuthUtil.isAccessorReady(portletPreferences) %>">
			<%@ include file="/view_oauth_settings.jspf" %>

			<liferay-ui:header title="obtain-oauth-accessor-credentials" />

			<%
			OAuthServiceHandler oAuthServiceHandler = OAuthUtil.getOAuthServiceHandler(portletPreferences);

			Token requestToken = oAuthServiceHandler.getRequestToken();

			portletSession.setAttribute(Token.class.getName(), requestToken);
			%>

			<c:choose>
				<c:when test="<%= requestToken == null %>">
					<liferay-ui:error message="uanble-to-generate-valid-request-token.-please-review-oauth-settings" />
				</c:when>
				<c:otherwise>
					<liferay-portlet:actionURL name="setupOAuth" var="setupOAuthURL" />

					<a href="<%= oAuthServiceHandler.getAuthorizeURL(requestToken, useServerProvidedCallbackUrl ? null : setupOAuthURL) %>"><liferay-ui:message key="authorize-access" /></a>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<%@ include file="/view_oauth_settings.jspf" %>

			<liferay-ui:header title="execute-platform-specific-task-using-oauth-authorized-request" />

			<%
			String oAuthResult = null;

			if (webId != null) {
				OAuthServiceHandler oAuthServiceHandler = OAuthUtil.getOAuthServiceHandler(portletPreferences);

				LiferayOAuthJSONWSClient liferayOAuthJSONWSClient = new LiferayOAuthJSONWSClient(hostName, hostPort, oAuthServiceHandler);

				oAuthResult = liferayOAuthJSONWSClient.getRemotePortalCompany(accessToken, accessSecret, webId);
			}
			%>

			<p>
				<liferay-ui:message key="liferay-portal-oauth-test-(show-remote-portal-information)" /><br />

				<c:choose>
					<c:when test="<%= oAuthResult == null %>">
						<aui:input helpMessage="web-id-help" name="webId" />

						<aui:button-row>
							<aui:button onClick='<%= renderResponse.getNamespace() + "testAuthorizedRequest();" %>' value="test" />
						</aui:button-row>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="test-results" />:<br />

						<pre class="lfr-code-block"><%= oAuthResult %></pre>
					</c:otherwise>
				</c:choose>
			</p>
		</c:otherwise>
	</c:choose>

	<hr />

	<div class="button-container">
		<liferay-portlet:actionURL name="resetOAuth" var="resetOAuthURL" />

		<a href="<%= resetOAuthURL %>"><liferay-ui:message key="reset-oauth-settings" /></a>
	</div>

	<hr />

	<p>
		<liferay-ui:message key="test-oauth-v1.0a-functionality" />
	</p>
</aui:form>

<aui:script>
	function <portlet:namespace />setupOAuthConsumer() {
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="setupOAuthConsumer" />');
	}

	function <portlet:namespace />testAuthorizedRequest() {
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:renderURL />');
	}
</aui:script>