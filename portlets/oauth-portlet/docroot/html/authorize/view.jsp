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

<%@ page import="com.liferay.portlet.oauth.OAuthWebKeys" %>

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

<%@ include file="/html/init.jsp" %>

<%
	OAuthAccessor accessor = (OAuthAccessor)request.getAttribute(OAuthWebKeys.OAUTH_ACCESSOR);
	String verifier = (String)request.getAttribute(OAuthWebKeys.VERIFIER);

	String callback = request.getParameter(OAuthConstants.OAUTH_CALLBACK);

	if ((callback == null) || (callback.length() <= 0)) {
		callback = OAuthConstants.NONE;
	}

	boolean alreadyAuthorized = GetterUtil.get(SessionErrors.contains(renderRequest, OAuthConstants.ALREADY_AUTHORIZED), false);
%>

<c:if test='<%= SessionErrors.contains(renderRequest, OAuthProblemException.class) %>'>
	<c:if test='<%= SessionErrors.get(renderRequest, OAuthProblemException.class) == OAuthProblemException.TOKEN_EXPIRED%>'>
		<div class="portlet-msg-error">
			<liferay-ui:message key="your-token-has-been-expired" />
		</div>
	</c:if>
</c:if>
<c:if test='<%= alreadyAuthorized %>'>
	<div class="portlet-msg-error">
		<liferay-ui:message key="you-are-already-authorized" />
	</div>
</c:if>

<c:if test="<%= accessor != null %>">
	<aui:layout>
		<aui:column columnWidth="50">
			<h3><liferay-ui:message arguments="<%= accessor.getConsumer().getOAuthApplication().getName() %>" key="authorize-application-to-use-your-information" /></h3>

			<div>
				<span><liferay-ui:message key="this-application-will-be-able-to" /></span>
				<br />
				<span>
					<c:choose>
						<c:when test="<%= accessor.getConsumer().getOAuthApplication().getAccessLevel() == 0 %>">
							<ul>
								<li><liferay-ui:message key="read-data-from-system" /></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<li><liferay-ui:message key="read-data-from-system" /></li>
								<li><liferay-ui:message key="write-data-from-system" /></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<br />
			<div>
				<liferay-portlet:actionURL name="authorize" var="authorizeURL" />

				<aui:form action='<%= authorizeURL.toString() %>' method="POST" name="authForm">
					<aui:input name="oauth_token" type="hidden" value="<%= accessor.getRequestToken() %>" />
					<aui:input name="oauth_callback" type="hidden" value="<%= callback %>" />
					<aui:button-row>
						<c:if test='<%= !alreadyAuthorized %>'>
							<aui:button name="authorize" type="submit" value="authorize" />
						</c:if>
					</aui:button-row>
				</aui:form>
			</div>
			<c:if test='<%= verifier != null %>'>
				<div class="portlet-msg-info">
					<liferay-ui:message arguments="<%= verifier %>" key="authorization-successfull-verification" />
				</div>
			</c:if>
		</aui:column>
		<aui:column columnWidth="50">
			<img height="90px" width="90px" />
			<br />
			<h4><%= accessor.getConsumer().getOAuthApplication().getName() %></h4>
			<span><%= accessor.getConsumer().getOAuthApplication().getWebsite() %></span>
			<br />
			<span><%= accessor.getConsumer().getOAuthApplication().getDescription() %></span>
		</aui:column>
	</aui:layout>

	<br />

	<div>
		<liferay-ui:message key="you-can-revoke-application-at-any-time" />
	</div>
</c:if>