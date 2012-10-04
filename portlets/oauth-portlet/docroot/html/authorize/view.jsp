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

<%@ page import="com.liferay.portal.kernel.oauth.OAuthException" %>

<%@ include file="/html/init.jsp" %>

<%
OAuthAccessor accessor = (OAuthAccessor)request.getAttribute(OAuthConstants.OAUTH_ACCESSOR);

String verifier = (String)request.getAttribute(OAuthConstants.VERIFIER);

String callback = ParamUtil.getString(request, OAuthConstants.OAUTH_CALLBACK, OAuthConstants.NONE);

boolean tokenExpired =
		GetterUtil.get(SessionErrors.contains(renderRequest, OAuthConstants.TOKEN_EXPIRED), false) ||
		OAuthConstants.TOKEN_EXPIRED.equals(SessionErrors.get(renderRequest, OAuthException.class));
%>

<c:if test="<%= tokenExpired %>">
	<div class="portlet-msg-error">
		<liferay-ui:message key="your-token-is-expired" />
	</div>
</c:if>

<c:if test="<%= accessor != null %>">
	<%
	Application oAuthApp = accessor.getConsumer().getApplication();
	%>
	<aui:layout>
		<aui:column columnWidth="50">
			<h3><liferay-ui:message arguments="<%= HtmlUtil.escape(oAuthApp.getName()) %>" key="authorize-x-to-use-your-account" /></h3>

			<div>
				<span><liferay-ui:message key="this-application-will-be-able-to" /></span>
				<br />
				<span>
					<c:choose>
						<c:when test="<%= oAuthApp.getAccessLevel() == 0 %>">
							<ul>
								<li><liferay-ui:message key="read-data-from-the-system-on-your-behalf" /></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<li><liferay-ui:message key="read-data-from-the-system-on-your-behalf" /></li>
								<li><liferay-ui:message key="write-data-to-the-system-on-your-behalf" /></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<br />
			<div>
				<liferay-portlet:actionURL name="authorize" var="authorizeURL" />

				<aui:form action="<%= authorizeURL.toString() %>" method="POST" name="authForm">
					<aui:input name="oauth_token" type="hidden" value="<%= accessor.getRequestToken() %>" />
					<aui:input name="oauth_callback" type="hidden" value="<%= callback %>" />
					<aui:button-row>
						<c:if test="<%= !tokenExpired %>">
							<aui:button name="authorize" type="submit" value="authorize" />
						</c:if>
					</aui:button-row>
				</aui:form>
			</div>
			<c:if test="<%= verifier != null %>">
				<div class="portlet-msg-info">
					<liferay-ui:message arguments="<%= verifier %>" key="authorization-was-successful-verification-code-is-x" />
				</div>
			</c:if>
		</aui:column>
		<aui:column columnWidth="50">
			<img height="90px" width="90px" />

			<br />

			<h3><%= HtmlUtil.escape(oAuthApp.getName()) %></h3>

			<strong><%= HtmlUtil.escape(oAuthApp.getWebsite()) %></strong>

			<br />

			<%
			String description = HtmlUtil.escape(oAuthApp.getDescription());
			%>

			<c:choose>
				<c:when test="<%= Validator.isNull(description) %>">
					<liferay-ui:message key="no-description" />
				</c:when>
				<c:otherwise>
					<span><%= description %></span>
				</c:otherwise>
			</c:choose>
		</aui:column>
	</aui:layout>

	<br />

	<div>
		<liferay-ui:message key="you-can-revoke-access-to-any-application-at-any-time" />
	</div>
</c:if>