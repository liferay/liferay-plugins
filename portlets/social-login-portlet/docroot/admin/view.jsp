<%
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
%>

<%@ include file="/admin/init.jsp" %>

<%
boolean qqConnectAuthEnabled = QqConnectUtil.isEnabled(company.getCompanyId());
String qqConnectAppId = QqConnectUtil.getClientId(company.getCompanyId());
String qqConnectAppKey = QqConnectUtil.getClientSecret(company.getCompanyId());
String qqConnectRedirectURI = QqConnectUtil.getRedirectURI(company.getCompanyId());
String qqConnectScope = QqConnectUtil.getScope(company.getCompanyId());
%>

<portlet:actionURL name="updateQqAuthSetting" var="updateQqAuthSettingURL" />

<aui:form action="<%= updateQqAuthSettingURL %>" method="post" name="fm">
	<aui:fieldset>
		<aui:input label="enabled" name='<%= "settings--" + PropsKeys.QQ_CONNECT_AUTH_ENABLED + "--" %>' type="checkbox" value="<%= qqConnectAuthEnabled %>" />

		<aui:input label="qq-app-id" name='<%= "settings--" + PropsKeys.QQ_CONNECT_APP_ID + "--" %>' type="text" value="<%= qqConnectAppId %>" />

		<aui:input label="qq-app-key" name='<%= "settings--" + PropsKeys.QQ_CONNECT_APP_KEY + "--" %>' type="text" value="<%= qqConnectAppKey %>" />

		<aui:input label="qq-redirect-uri" name='<%= "settings--" + PropsKeys.QQ_CONNECT_OAUTH_REDIRECT_URI + "--" %>' type="text" value="<%= qqConnectRedirectURI %>" />

		<aui:input label="qq-scope" name='<%= "settings--" + PropsKeys.QQ_CONNECT_OAUTH_SCOPE + "--" %>' type="text" value="<%= qqConnectScope %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>