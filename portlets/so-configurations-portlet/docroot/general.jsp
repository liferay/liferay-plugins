<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
Role soRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

boolean addSitePermission = ResourcePermissionLocalServiceUtil.hasResourcePermission(themeDisplay.getCompanyId(), PortletKeys.PORTAL, ResourceConstants.SCOPE_COMPANY, String.valueOf(themeDisplay.getCompanyId()), soRole.getRoleId(), ActionKeys.ADD_COMMUNITY);

portletPreferences = PortletPreferencesLocalServiceUtil.getPreferences(themeDisplay.getCompanyId(), themeDisplay.getCompanyId(), PortletKeys.PREFS_OWNER_TYPE_COMPANY, LayoutConstants.DEFAULT_PLID, PortletKeys.SO_CONFIGURATIONS);

boolean enableOpenSites = GetterUtil.getBoolean(portletPreferences.getValue("enableOpenSites", null), true);
boolean enablePublicRestrictedSites = GetterUtil.getBoolean(portletPreferences.getValue("enablePublicRestrictedSites", null), true);
boolean enablePrivateRestrictedSites = GetterUtil.getBoolean(portletPreferences.getValue("enablePrivateRestrictedSites", null), true);
boolean enablePrivateSites = GetterUtil.getBoolean(portletPreferences.getValue("enablePrivateSites", null), true);
%>

<h3><liferay-ui:message key="general" /></h3>

<portlet:actionURL name="updateGeneralConfigurations" var="updateGeneralConfigurationsURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= updateGeneralConfigurationsURL %>" method="post" name="fm">
	<aui:input checked="<%= addSitePermission %>" label="social-office-users-can-add-sites" name="addSitePermission" type="checkbox" />

	<liferay-ui:message key="select-which-type-of-sites-users-can-add-by-default-through-sites-portlet" />

	<liferay-ui:icon-help message="administrators-can-still-change-permissions-after-site-creation" />

	<div class="site-types" id="<portlet:namespace />siteTypes">
		<aui:input checked="<%= enableOpenSites %>" label="open" name="preferences--enableOpenSites--" type="checkbox" />
		<aui:input checked="<%= enablePublicRestrictedSites %>" label="public-restricted" name="preferences--enablePublicRestrictedSites--" type="checkbox" />
		<aui:input checked="<%= enablePrivateRestrictedSites %>" label="private-restricted" name="preferences--enablePrivateRestrictedSites--" type="checkbox" />
		<aui:input checked="<%= enablePrivateSites %>" label="private" name="preferences--enablePrivateSites--" type="checkbox" />
	</div>

	<aui:button type="submit" />
</aui:form>