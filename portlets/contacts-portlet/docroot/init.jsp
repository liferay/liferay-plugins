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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.contacts.model.Entry" %><%@
page import="com.liferay.contacts.service.EntryLocalServiceUtil" %><%@
page import="com.liferay.contacts.util.ContactsConstants" %><%@
page import="com.liferay.contacts.util.ContactsExtensionsUtil" %><%@
page import="com.liferay.contacts.util.ContactsUtil" %><%@
page import="com.liferay.contacts.util.PortletKeys" %><%@
page import="com.liferay.contacts.util.SocialRelationConstants" %><%@
page import="com.liferay.contacts.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayPortletResponse" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.ServletContextPool" %><%@
page import="com.liferay.portal.kernel.util.CharPool" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PrefsParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.model.Address" %><%@
page import="com.liferay.portal.model.BaseModel" %><%@
page import="com.liferay.portal.model.Contact" %><%@
page import="com.liferay.portal.model.Country" %><%@
page import="com.liferay.portal.model.EmailAddress" %><%@
page import="com.liferay.portal.model.Group" %><%@
page import="com.liferay.portal.model.GroupConstants" %><%@
page import="com.liferay.portal.model.Phone" %><%@
page import="com.liferay.portal.model.Region" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.model.UserConstants" %><%@
page import="com.liferay.portal.model.Website" %><%@
page import="com.liferay.portal.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.service.AddressServiceUtil" %><%@
page import="com.liferay.portal.service.EmailAddressServiceUtil" %><%@
page import="com.liferay.portal.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.service.PhoneServiceUtil" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.service.WebsiteServiceUtil" %><%@
page import="com.liferay.portal.service.permission.UserPermissionUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portal.util.comparator.UserLastNameComparator" %><%@
page import="com.liferay.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portlet.asset.model.AssetTag" %><%@
page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %><%@
page import="com.liferay.portlet.social.model.SocialRequestConstants" %><%@
page import="com.liferay.portlet.social.service.SocialActivityLocalServiceUtil" %><%@
page import="com.liferay.portlet.social.service.SocialRelationLocalServiceUtil" %><%@
page import="com.liferay.portlet.social.service.SocialRequestLocalServiceUtil" %><%@
page import="com.liferay.so.service.SocialOfficeServiceUtil" %><%@
page import="com.liferay.users.admin.kernel.util.UsersAdminUtil" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.Set" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

String portletResource = ParamUtil.getString(request, "portletResource");

int displayStyle = PrefsParamUtil.getInteger(portletPreferences, request, "displayStyle", ContactsConstants.DISPLAY_STYLE_FULL);

boolean showAdditionalEmailAddresses = PrefsParamUtil.getBoolean(portletPreferences, request, "showAdditionalEmailAddresses", true);
boolean showAddresses = PrefsParamUtil.getBoolean(portletPreferences, request, "showAddresses", true);
boolean showComments = PrefsParamUtil.getBoolean(portletPreferences, request, "showComments", true);
boolean showCompleteYourProfile = PrefsParamUtil.getBoolean(portletPreferences, request, "showCompleteYourProfile", false);
boolean showIcon = PrefsParamUtil.getBoolean(portletPreferences, request, "showIcon", true);
boolean showInstantMessenger = PrefsParamUtil.getBoolean(portletPreferences, request, "showInstantMessenger", true);
boolean showPhones = PrefsParamUtil.getBoolean(portletPreferences, request, "showPhones", true);
boolean showRecentActivity = PrefsParamUtil.getBoolean(portletPreferences, request, "showRecentActivity", false);

boolean showOnlySiteMembers = false;

String portletId = portletDisplay.getId();

if (portletId.equals(PortletKeys.MEMBERS)) {
	showOnlySiteMembers = true;
}

boolean showSites = PrefsParamUtil.getBoolean(portletPreferences, request, "showSites", false);
boolean showSMS = PrefsParamUtil.getBoolean(portletPreferences, request, "showSMS", true);
boolean showSocialNetwork = PrefsParamUtil.getBoolean(portletPreferences, request, "showSocialNetwork", true);
boolean showTags = PrefsParamUtil.getBoolean(portletPreferences, request, "showTags", false);
boolean showUsersInformation = PrefsParamUtil.getBoolean(portletPreferences, request, "showUsersInformation", true);
boolean showWebsites = PrefsParamUtil.getBoolean(portletPreferences, request, "showWebsites", true);
%>