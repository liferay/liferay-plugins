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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

long webExAccountId = ParamUtil.getLong(request, "webExAccountId");

WebExAccount webExAccount = WebExAccountLocalServiceUtil.fetchWebExAccount(webExAccountId);

long webExSiteId = ParamUtil.getLong(request, "webExSiteId");
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (webExAccount != null) ? webExAccount.getLogin() : "new-account" %>'
/>

<portlet:actionURL name='<%= webExAccount != null ? "updateWebExAccount" : "addWebExAccount" %>' var="editAccountURL" />

<aui:form action="<%= editAccountURL %>" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/meetings/edit_account.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="webExAccountId" type="hidden" value="<%= String.valueOf(webExAccountId) %>" />
	<aui:input name="webExSiteId" type="hidden" value="<%= String.valueOf(webExSiteId) %>" />

	<liferay-ui:error exception="<%= WebExAccountLoginException.class %>" message="please-enter-a-valid-account-login" />
	<liferay-ui:error exception="<%= WebExAccountPasswordException.class %>" message="please-enter-a-valid-account-password" />

	<aui:model-context bean="<%= webExAccount %>" model="<%= WebExAccount.class %>" />

	<aui:fieldset>
		<aui:input disabled="<%= webExAccount != null %>" name="login" />

		<aui:input name="password" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>