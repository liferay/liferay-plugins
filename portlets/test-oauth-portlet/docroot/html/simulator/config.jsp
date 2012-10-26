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

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ include file="/html/init.jsp" %>

<%
String token = (String)request.getAttribute("oauth-simulator-token");
String secret = (String)request.getAttribute("oauth-simulator-secret");
String oauthURL = (String)request.getAttribute("oauth-simulator-url");
String applicationId = (String)request.getAttribute("applicationId");
%>

<liferay-ui:error-marker key="errorSection" value="details" />

<liferay-ui:panel title="Test OAuth Configuration">
		<span>You decided to test OAuth feature? To complete fill values and configure portal by clicking the GO! button.
</liferay-ui:panel>

<liferay-portlet:actionURL name="setupOAuthSimulator" var="setupOAuthSimulatorURL">
		<portlet:param name="jspPage" value="/html/simulator/view.jsp" />
</liferay-portlet:actionURL>

		<aui:form action="<%= setupOAuthSimulatorURL %>" method="post">
			<aui:fieldset>
				<aui:input id="oauth-simulator-user-cnt" label="Number of users to generate" name="oauth-simulator-user-cnt" />
				<aui:input helpMessage="Accordinglly to given percentage, portlet will assign OAuth Developer role to this subset of generated users, and register test oauth applications owned by them. (ieg. enter 10 if you want 10% created users to be application developers)" id="oauth-simulator-vendor-percentage" label="Percentage of developers" name="oauth-simulator-vendor-percentage" />
				<aui:input helpMessage="This is number of test oauth applications that will be created for each developer. (ieg. enter 3 if you want that developer has 1 to 3 registered applications - final number is choosen randomly)" id="oauth-simulator-max-applications" label="MAX APPS. per developer" name="oauth-simulator-max-applications" />
				<aui:button-row>
				<aui:button type="submit" value="go" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>