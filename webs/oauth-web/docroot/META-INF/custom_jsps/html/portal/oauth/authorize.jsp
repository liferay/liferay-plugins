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

<%@ include file="/html/common/init.jsp" %>

<%
	String name = (String)request.getAttribute("NAME");
	String description = (String)request.getAttribute("DESCRIPTION");
	String webSite = (String)request.getAttribute("WEB_SITE");
	String token = (String)request.getAttribute("TOKEN");
	String callback = (String)request.getAttribute("CALLBACK");
	Integer accessLevel = (Integer)request.getAttribute("ACCESS_LEVEL");
	String verifier = (String)request.getAttribute("VERIFIER");

	if(callback == null)
		callback = "none";
%>

<html>
	<head>
		<liferay-theme:include page="top_head.jsp"/>
	</head>
	<body>
		<div class="banner">
			<h1><img src="<%= themeDisplay.getPathImage() + "/company_logo?img_id=0" %>"></img></h1>
		</div>

		<c:if test='<%= SessionErrors.contains(request, "token_expired") %>'>

			<liferay-ui:message key="token-expired" />
		</c:if>

		<c:if test='<%= SessionMessages.contains(request, "success_authorization") %>'>

			<liferay-ui:message arguments="<%= verifier %>" key="success-authorization" />
		</c:if>

		<c:if test='<%= name != null %>'>

			<aui:layout>

				<aui:column>
					<h3>"<%= name %>" is trying to access your information.</h3>

					<div>
						<%= accessLevel %>
					</div>

					<aui:form action='<%= PortalUtil.getPathMain() + "/portal/oauth/authorize" %>' method="POST" name="authForm">
						<aui:input name="oauth_token" type="hidden" value="<%= token %>" />
						<aui:input name="oauth_callback" type="hidden" value="<%= callback %>" />
						<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
						<aui:input name="Authorize" type="submit" value="Authorize" />
					</aui:form>
				</aui:column>
				<aui:column>
					<h4>"<%=name%>"</h4>
					<div><%= webSite %></div>
					<div><%= description %></div>
				</aui:column>
			</aui:layout>
			<div>
				You can revoke access to any application at any time from the Applications tab of your Settings page.
				By authorizing an application you continue to operate under ....'s Terms of Service. In particular, some usage information will be shared back with ..... For more, see our Privacy Policy.
			</div>

		</c:if>
	</body>
</html>

