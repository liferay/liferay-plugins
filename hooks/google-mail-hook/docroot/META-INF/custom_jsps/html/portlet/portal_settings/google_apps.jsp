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
String googleAppsUsername = PrefsPropsUtil.getString(company.getCompanyId(), "google.apps.username");

String googleAppsPassword = PrefsPropsUtil.getString(company.getCompanyId(), "google.apps.password");

if (Validator.isNotNull(googleAppsPassword)) {
	googleAppsPassword = Portal.TEMP_OBFUSCATION_VALUE;
}
%>

<h3><liferay-ui:message key="google-apps" /></h3>

<aui:fieldset>
	<aui:input autocomplete="off" label="user-name" name='<%= "settings--google.apps.username--" %>' type="text" value="<%= googleAppsUsername %>" wrapperCssClass="lfr-input-text-container" />

	<aui:input autocomplete="off" label="password" name='<%= "settings--google.apps.password--" %>' type="password" value="<%= googleAppsPassword %>" wrapperCssClass="lfr-input-text-container" />
</aui:fieldset>