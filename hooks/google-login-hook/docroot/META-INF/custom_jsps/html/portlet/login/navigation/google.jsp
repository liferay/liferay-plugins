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

<%@ include file="/html/portlet/login/init.jsp" %>

<%
boolean googleAuthEnabled = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), "google-auth-enabled", true);

if (Validator.isNull(PrefsPropsUtil.getString(company.getCompanyId(), "google-client-id")) || Validator.isNull(PrefsPropsUtil.getString(company.getCompanyId(), "google-client-secret"))) {
	googleAuthEnabled = false;
}
%>

<c:if test="<%= googleAuthEnabled %>">

	<%
	String googleAuthURL = PortalUtil.getPathContext() + "/c/portal/google_login?cmd=login";

	String taglibOpenGoogleLoginWindow = "javascript:var googleLoginWindow = window.open('" + googleAuthURL.toString() + "', 'google', 'align=center,directories=no,height=560,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1000'); void(''); googleLoginWindow.focus();";
	%>

	<liferay-ui:icon
		iconCssClass="icon-google-plus-sign"
		message="google"
		url="<%= taglibOpenGoogleLoginWindow %>"
	/>
</c:if>