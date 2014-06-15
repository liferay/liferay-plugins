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

long bbbServerId = ParamUtil.getLong(request, "bbbServerId");

BBBServer bbbServer = BBBServerLocalServiceUtil.fetchBBBServer(bbbServerId);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= (bbbServer == null) %>"
	title='<%= (bbbServer != null) ? bbbServer.getName() : "new-server" %>'
/>

<liferay-portlet:actionURL name="updateBBBServer" var="editBBBServerURL" />

<aui:form action="<%= editBBBServerURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="bbbServerId" type="hidden" value="<%= String.valueOf(bbbServerId) %>" />

	<aui:model-context bean="<%= bbbServer %>" model="<%= BBBServer.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input label="api-url" name="url" />

		<aui:input name="secret" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>