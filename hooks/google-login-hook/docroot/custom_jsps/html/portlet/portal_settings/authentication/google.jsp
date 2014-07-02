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
boolean googleAuthEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), "google-auth-enabled", true);
String googleClientId = PrefsPropsUtil.getString(company.getCompanyId(), "google-client-id");
String googleClientSecret = PrefsPropsUtil.getString(company.getCompanyId(), "google-client-secret");
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= "settings--google-auth-enabled--" %>' type="checkbox" value="<%= googleAuthEnabled %>" />

	<aui:input label="google-client-id" name='<%= "settings--google-client-id--" %>' type="text" value="<%= googleClientId %>" wrapperCssClass="lfr-input-text-container" />

	<aui:input label="google-client-secret" name='<%= "settings--google-client-secret--" %>' type="text" value="<%= googleClientSecret %>" wrapperCssClass="lfr-input-text-container" />
</aui:fieldset>