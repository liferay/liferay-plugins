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
OAuthApplication oAuthApplication = (OAuthApplication)request.getAttribute(OAuthConstants.WEB_APP_BEAN);

String actionName = "addOAuthApp";
if ((null != oAuthApplication) && (0L != oAuthApplication.getApplicationId())) {
	actionName = "editOAuthApp";
}

String backURL = ParamUtil.getString(request, "referer");
%>

<liferay-ui:error-marker key="errorSection" value="details" />

<aui:model-context bean="<%= oAuthApplication %>" model="<%= OAuthApplication.class %>" />

<liferay-ui:error exception="<%=RequiredFieldException.class %>" message="this-field-is-required" />
<liferay-ui:error exception="<%=MalformedURLException.class %>" message="please-enter-a-valid-url" />

<liferay-portlet:actionURL name="<%= actionName %>" var="addApplicationURL">
	<portlet:param name="jspPage" value="/html/admin/edit.jsp" />
	<portlet:param name="referer" value="<%= backURL %>" />
</liferay-portlet:actionURL>

<aui:a href="<%= backURL %>">back</aui:a>
<aui:form action="<%= addApplicationURL %>" method="post">
	<aui:fieldset>
		<aui:input id="applicationId" name="applicationId" type="hidden" />
		<aui:input id="<%= OAuthConstants.WEB_APP_NAME_ID %>" label="name" name="<%= OAuthConstants.WEB_APP_NAME %>" showRequiredLabel="true" />
		<aui:input label="description" name="<%= OAuthConstants.WEB_APP_DESCRIPTION %>" type="textarea" />
		<aui:input id="<%= OAuthConstants.WEB_APP_WEBSITE_ID %>" label="website" name="<%= OAuthConstants.WEB_APP_WEBSITE %>" showRequiredLabel="true"  />
		<aui:input id="<%= OAuthConstants.WEB_APP_CALLBACKURL_ID %>" label="callback-url" name="<%= OAuthConstants.WEB_APP_CALLBACKURL %>" showRequiredLabel="true" />

		<aui:select helpMessage="access-level-description" label="access-level" name="<%= OAuthConstants.WEB_APP_ACCESS_TYPE %>">
			<aui:option label="<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_OPTION.concat(Integer.toString(OAuthConstants.ACCESS_TYPE_READ)) %>" value="<%= OAuthConstants.ACCESS_TYPE_READ %>"></aui:option>
			<aui:option label="<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_OPTION.concat(Integer.toString(OAuthConstants.ACCESS_TYPE_WRITE)) %>" value="<%= OAuthConstants.ACCESS_TYPE_WRITE %>"></aui:option>
		</aui:select>

		<c:if test="<%= (null != oAuthApplication) && (null != oAuthApplication.getConsumerKey()) %>">
			<aui:field-wrapper helpMessage="application-credentials-description" label="application-credentials">
				<liferay-ui:message key="application-key" />: <%= oAuthApplication.getConsumerKey() %> <br />
				<liferay-ui:message key="application-secret" />: <%= oAuthApplication.getConsumerSecret() %>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
		<aui:button type="submit" />
		<aui:button href="<%= backURL %>" value="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>