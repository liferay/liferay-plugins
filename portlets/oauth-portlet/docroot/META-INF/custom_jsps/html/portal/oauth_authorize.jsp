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


<c:if test='<%= SessionErrors.contains(request, "token_expired") %>'>
	<div class="portlet-msg-error">
		<liferay-ui:message key="token-expired" />
	</div>
</c:if>

<c:if test='<%= name != null %>'>

	<aui:layout>

		<aui:column columnWidth="50">
			<h3><liferay-ui:message arguments="<%= name %>" key="authorize-application-to-use-your-information" /></h3>

			<div>
				<span><liferay-ui:message key="this-application-will-be-able-to" /></span>
				<br/>
				<span>
					<c:choose>
						<c:when test="<%= accessLevel == 0 %>">
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
			<br/>
			<div>
				<aui:form action='<%= PortalUtil.getPathMain() + "/portal/oauth/authorize" %>' method="POST" name="authForm">
					<aui:input name="oauth_token" type="hidden" value="<%= token %>" />
					<aui:input name="oauth_callback" type="hidden" value="<%= callback %>" />
					<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
					<aui:button-row>
						<aui:button name="authorize" type="submit" value="authorize" />
					</aui:button-row>
				</aui:form>
			</div>
			<c:if test='<%= SessionMessages.contains(request, "success_authorization") %>'>
				<div class="portlet-msg-info">
					<liferay-ui:message arguments="<%= verifier %>" key="authorization-successfull-verification" />
				</div>
			</c:if>
		</aui:column>
		<aui:column columnWidth="50">
			<img width="90px" height="90px"/>
			<br/>
			<h4><%=name%></h4>
			<span><%= webSite %></span>
			<br/>
			<span><%= description %></span>
		</aui:column>
	</aui:layout>

	<br/>

	<div>
		<liferay-ui:message key="you-can-revoke-application-at-any-time"/>
	</div>
</c:if>