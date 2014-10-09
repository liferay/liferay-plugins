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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<liferay-ui:error-marker key="errorSection" value="authentication" />

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/portal_settings/authentication.portal.jsp" />
</liferay-util:buffer>

<liferay-util:buffer var="tab">
	<li class="aui-tab aui-state-default" id="<portlet:namespace />tabs1<%= StringUtil.toCharCode("shibboleth") %>TabsId">
		<span class="aui-tab-content">
			<a class="aui-tab-label" href="#"><liferay-ui:message key="shibboleth" /></a>
		</span>
	</li>
</liferay-util:buffer>

<liferay-util:buffer var="section">
	<div class="aui-helper-hidden" id="<portlet:namespace />tabs1<%= StringUtil.toCharCode("shibboleth") %>TabsSection">
		<liferay-util:include page="/html/portlet/portal_settings/authentication/shibboleth.jsp" />
	</div>
</liferay-util:buffer>

<%
String[] companySettingsFormAuthentication = StringUtil.split(_COMPANY_SETTINGS_FORM_AUTHENTICATION_VALUE);

String tabNamesJS = JS.toScript(companySettingsFormAuthentication);

int index = html.indexOf("aui-tabview-list");

index = html.indexOf("</ul>", index);

html = html.substring(0, index) + tab + html.substring(index);

html = html.concat(section);
%>

<%= html %>

<aui:script use="aui-base">
	var curTabs = "<%= tabNamesJS %>";
	var tabs = <%= tabNamesJS %>;

	for (i = 0; i < tabs.length; i++) {
		var tab = tabs[i];

		var namespaceId = '<portlet:namespace/>tabs1' + Liferay.Util.toCharCode(tab);

		var tabNode = A.one('#' + namespaceId + 'TabsId');

		var link = tabNode.one('a');

		link.set('href', "javascript:Liferay.Portal.Tabs.show('<portlet:namespace />tabs1', " + curTabs + ", '" + tab + "');");

		if (tabNode.hasClass('last')) {
			tabNode.removeClass('last');
		}

		if (i == (tabs.length - 1)) {
			tabNode.addClass('last');
		}
	}
</aui:script>

<%!
private static final String _COMPANY_SETTINGS_FORM_AUTHENTICATION_KEY = "company.settings.form.authentication";

private static final String _COMPANY_SETTINGS_FORM_AUTHENTICATION_VALUE = GetterUtil.getString(PropsUtil.get(_COMPANY_SETTINGS_FORM_AUTHENTICATION_KEY));
%>