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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");
%>

<h3><liferay-ui:message key="mentions" /></h3>

<%
UnicodeProperties typeSettingsProperties = null;

if (liveGroup != null) {
	typeSettingsProperties = liveGroup.getTypeSettingsProperties();
}
else {
	typeSettingsProperties = new UnicodeProperties();
}

boolean mentionsEnabled = GetterUtil.getBoolean(typeSettingsProperties.getProperty("mentionsEnabled"), true);

PortletPreferences companyPortletPreferences = PrefsPropsUtil.getPreferences(company.getCompanyId(), true);

boolean companyMentionsEnabled = PrefsParamUtil.getBoolean(companyPortletPreferences, request, "mentionsEnabled", true);
%>

<c:if test="<%= !companyMentionsEnabled %>">
	<div class="alert alert-warning">
		<liferay-ui:message key="mentions-are-disabled-in-the-portal" />
	</div>
</c:if>

<aui:input checked="<%= mentionsEnabled %>" disabled="<%= !companyMentionsEnabled %>" label="enable-mentions" name="TypeSettingsProperties--mentionsEnabled--" type="checkbox" value="<%= mentionsEnabled %>" />