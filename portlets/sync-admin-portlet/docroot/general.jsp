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

portletPreferences = SyncDLObjectServiceUtil.getPortletPreferences();

boolean allowUserPersonalSites = PrefsPropsUtil.getBoolean(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_ALLOW_USER_PERSONAL_SITES);
boolean enabled = PrefsPropsUtil.getBoolean(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_SERVICES_ENABLED);
int maxConnections = PrefsPropsUtil.getInteger(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS);
int pollInterval = PrefsPropsUtil.getInteger(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL);
%>

<liferay-portlet:actionURL var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updatePreferences();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset>
		<aui:input name="enabled" type="checkbox" value="<%= enabled %>" />
	</aui:fieldset>

	<aui:fieldset>
		<aui:input label="allow-user-personal-sites" name="allowUserPersonalSites" type="checkbox" value="<%= allowUserPersonalSites %>" />
	</aui:fieldset>

	<aui:input helpMessage="max-connections-help" label="max-connections" name="maxConnections" type="text" value="<%= maxConnections %>" wrapperCssClass="lfr-input-text-container">
		<aui:validator name="digits" />
		<aui:validator name="min">1</aui:validator>
	</aui:input>

	<aui:input helpMessage="poll-interval-help" label="poll-interval" name="pollInterval" type="text" value="<%= pollInterval %>" wrapperCssClass="lfr-input-text-container">
		<aui:validator name="digits" />
		<aui:validator name="min">1</aui:validator>
	</aui:input>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />updatePreferences() {
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="updatePreferences" />');
	}
</aui:script>