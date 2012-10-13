<%@ page import="com.liferay.portal.webserver.WebServerServletTokenUtil" %>

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
Application app = (Application)request.getAttribute(OAuthConstants.BEAN_ID);

String backURL = ParamUtil.getString(request, "referer");

String logoURL = themeDisplay.getPathImage() + "/logo?img_id=" + app.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(company.getLogoId());
%>

<liferay-ui:error-marker key="errorSection" value="details" />

<aui:model-context bean="<%= app %>" model="<%= Application.class %>" />

<liferay-ui:message key="name" />: <%= app.getName() %> <br />
<liferay-ui:message key="description" />: <%= app.getDescription() %> <br />
<liferay-ui:message key="access-type" />: <liferay-ui:message key="<%= OAuthConstants.ACCESS_TYPE_OPTION.concat(Integer.toString(app.getAccessLevel())) %>" /> <br />
<liferay-ui:message key="website" />: <%= app.getWebsite() %> <br />
<liferay-ui:message key="callback-url" />: <%= app.getCallBackURL() %> <br />

<aui:field-wrapper helpMessage="application-credentials-description" label="application-credentials">
		<liferay-ui:message key="consumer-key" />: <%= app.getConsumerKey() %> <br />
		<liferay-ui:message key="consumer-secret" />: <%= app.getConsumerSecret() %>
</aui:field-wrapper>

<aui:field-wrapper label="logo">
	<img class="lfr-portrait-preview-img" src="<%= HtmlUtil.escape(logoURL) %>" />
</aui:field-wrapper>

<liferay-portlet:actionURL name="updateApplication" var="updateApplicationURL">
	<portlet:param name="applicationId" value="<%= String.valueOf(app.getApplicationId()) %>" />
	<portlet:param name="mvcPath" value="/html/admin/edit.jsp" />
	<portlet:param name="referer" value="<%= backURL %>" />
</liferay-portlet:actionURL>
<aui:form action="<%= updateApplicationURL %>" method="get">
	<aui:button-row>
		<aui:button type="submit" value="update" />
		<aui:button href="<%= backURL %>" value="back" />
	</aui:button-row>
</aui:form>