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

<%
boolean shibbolethAuthEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), _SHIBBOLETH_AUTH_ENABLED_KEY, _SHIBBOLETH_AUTH_ENABLED_VALUE);
boolean shibbolethImportFromLdap = PrefsPropsUtil.getBoolean(company.getCompanyId(), _SHIBBOLETH_IMPORT_FROM_LDAP_KEY, _SHIBBOLETH_IMPORT_FROM_LDAP_VALUE);
String shibbolethLogoutURL = PrefsPropsUtil.getString(company.getCompanyId(), _SHIBBOLETH_LOGOUT_URL_KEY, _SHIBBOLETH_LOGOUT_URL_VALUE);
String shibbolethUserHeader = PrefsPropsUtil.getString(company.getCompanyId(), _SHIBBOLETH_USER_HEADER_KEY, _SHIBBOLETH_USER_HEADER_VALUE);
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= "settings--" + _SHIBBOLETH_AUTH_ENABLED_KEY + "--" %>' type="checkbox" value="<%= shibbolethAuthEnabled %>" />

	<aui:input helpMessage="import-shibboleth-users-from-ldap-help" label="import-shibboleth-users-from-ldap" name='<%= "settings--" + _SHIBBOLETH_IMPORT_FROM_LDAP_KEY + "--" %>' type="checkbox" value="<%= shibbolethImportFromLdap %>" />

	<aui:input label="logout-url" name='<%= "settings--" + _SHIBBOLETH_LOGOUT_URL_KEY + "--" %>' type="text" value="<%= shibbolethLogoutURL %>" wrapperCssClass="lfr-input-text-container" />

	<aui:input label="user-header" name='<%= "settings--" + _SHIBBOLETH_USER_HEADER_KEY + "--" %>' type="text" value="<%= shibbolethUserHeader %>" wrapperCssClass="lfr-input-text-container" />
</aui:fieldset>

<%!
private static final String _SHIBBOLETH_AUTH_ENABLED_KEY = "shibboleth.auth.enabled";

private static final boolean _SHIBBOLETH_AUTH_ENABLED_VALUE = GetterUtil.getBoolean(PropsUtil.get(_SHIBBOLETH_AUTH_ENABLED_KEY));

private static final String _SHIBBOLETH_IMPORT_FROM_LDAP_KEY = "shibboleth.import.from.ldap";

private static final boolean _SHIBBOLETH_IMPORT_FROM_LDAP_VALUE = GetterUtil.getBoolean(PropsUtil.get(_SHIBBOLETH_IMPORT_FROM_LDAP_KEY));

private static final String _SHIBBOLETH_LOGOUT_URL_KEY = "shibboleth.logout.url";

private static final String _SHIBBOLETH_LOGOUT_URL_VALUE = GetterUtil.getString(PropsUtil.get(_SHIBBOLETH_LOGOUT_URL_KEY));

private static final String _SHIBBOLETH_USER_HEADER_KEY = "shibboleth.user.header";

private static final String _SHIBBOLETH_USER_HEADER_VALUE = GetterUtil.getString(PropsUtil.get(_SHIBBOLETH_USER_HEADER_KEY));
%>