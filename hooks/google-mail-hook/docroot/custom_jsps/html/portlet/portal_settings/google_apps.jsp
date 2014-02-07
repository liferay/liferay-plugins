<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
String googleAppsUsername = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.GOOGLE_APPS_USERNAME);

String googleAppsPassword = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.GOOGLE_APPS_PASSWORD);

if (Validator.isNotNull(googleAppsPassword)) {
	googleAppsPassword = Portal.TEMP_OBFUSCATION_VALUE;
}
%>

<h3><liferay-ui:message key="google-apps" /></h3>

<aui:fieldset>
	<aui:input autocomplete="off" cssClass="lfr-input-text-container" label="user-name" name='<%= "settings--" + PropsKeys.GOOGLE_APPS_USERNAME + "--" %>' type="text" value="<%= googleAppsUsername %>" />

	<aui:input autocomplete="off" cssClass="lfr-input-text-container" label="password" name='<%= "settings--" + PropsKeys.GOOGLE_APPS_PASSWORD + "--" %>' type="password" value="<%= googleAppsPassword %>" />
</aui:fieldset>