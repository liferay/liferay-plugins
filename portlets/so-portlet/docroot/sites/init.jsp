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

<%@ page import="com.liferay.so.util.SocialOfficeConstants" %>

<%
String portletResource = ParamUtil.getString(request, "portletResource");

int maxResultSize = GetterUtil.getInteger(portletPreferences.getValue("maxResultSize", null), 10);

portletPreferences = PortletPreferencesLocalServiceUtil.getPreferences(themeDisplay.getCompanyId(), themeDisplay.getCompanyId(), PortletKeys.PREFS_OWNER_TYPE_COMPANY, LayoutConstants.DEFAULT_PLID, PortletKeys.SO_CONFIGURATIONS);

boolean enableOpenSites = GetterUtil.getBoolean(portletPreferences.getValue("enableOpenSites", null), true);
boolean enablePublicRestrictedSites = GetterUtil.getBoolean(portletPreferences.getValue("enablePublicRestrictedSites", null), true);
boolean enablePrivateRestrictedSites = GetterUtil.getBoolean(portletPreferences.getValue("enablePrivateRestrictedSites", null), true);
boolean enablePrivateSites = GetterUtil.getBoolean(portletPreferences.getValue("enablePrivateSites", null), true);

PortletPreferences userPortletPreferences = PortletPreferencesLocalServiceUtil.getPreferences(themeDisplay.getCompanyId(), themeDisplay.getUserId(), PortletKeys.PREFS_OWNER_TYPE_USER, LayoutConstants.DEFAULT_PLID, PortletKeys.SO_SITES);
%>