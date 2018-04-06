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

long webExSiteId = ParamUtil.getLong(request, "webExSiteId");

WebExSite webExSite = WebExSiteLocalServiceUtil.fetchWebExSite(webExSiteId);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (webExSite != null) ? webExSite.getName() : "new-site" %>'
/>

<portlet:actionURL name='<%= (webExSite != null) ? "updateWebExSite" : "addWebExSite" %>' var="editSiteURL" />

<aui:form action="<%= editSiteURL %>" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/meetings/edit_site.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="webExSiteId" type="hidden" value="<%= String.valueOf(webExSiteId) %>" />

	<liferay-ui:error exception="<%= WebExSiteAPIURLException.class %>" message="please-enter-a-valid-api-url" />
	<liferay-ui:error exception="<%= WebExSiteKeyException.class %>" message="please-enter-a-valid-site-key" />
	<liferay-ui:error exception="<%= WebExSiteLoginException.class %>" message="please-enter-a-valid-site-login" />
	<liferay-ui:error exception="<%= WebExSitePasswordException.class %>" message="please-enter-a-valid-site-password" />

	<aui:model-context bean="<%= webExSite %>" model="<%= WebExSite.class %>" />

	<aui:fieldset>
		<aui:input disabled="<%= webExSite != null %>" name="name" />

		<aui:input name="login" />

		<aui:input name="password" />

		<aui:input label="api-url" name="apiURL" />

		<aui:input disabled="<%= webExSite != null %>" name="partnerKey" />

		<aui:input disabled="<%= webExSite != null %>" name="siteKey" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>