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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.meeting.Meeting" %><%@
page import="com.liferay.meeting.MeetingException" %><%@
page import="com.liferay.meeting.MeetingParticipant" %><%@
page import="com.liferay.meeting.MeetingSummary" %><%@
page import="com.liferay.meeting.webex.MeetingServiceUtil" %><%@
page import="com.liferay.meeting.webex.exception.WebExAccountLoginException" %><%@
page import="com.liferay.meeting.webex.exception.WebExAccountPasswordException" %><%@
page import="com.liferay.meeting.webex.exception.WebExSiteAPIURLException" %><%@
page import="com.liferay.meeting.webex.exception.WebExSiteKeyException" %><%@
page import="com.liferay.meeting.webex.exception.WebExSiteLoginException" %><%@
page import="com.liferay.meeting.webex.exception.WebExSitePasswordException" %><%@
page import="com.liferay.meeting.webex.model.WebExAccount" %><%@
page import="com.liferay.meeting.webex.model.WebExSite" %><%@
page import="com.liferay.meeting.webex.service.WebExAccountLocalServiceUtil" %><%@
page import="com.liferay.meeting.webex.service.WebExAccountServiceUtil" %><%@
page import="com.liferay.meeting.webex.service.WebExSiteLocalServiceUtil" %><%@
page import="com.liferay.meeting.webex.service.WebExSiteServiceUtil" %><%@
page import="com.liferay.meeting.webex.service.permission.WebExAccountPermission" %><%@
page import="com.liferay.meeting.webex.service.permission.WebExPermission" %><%@
page import="com.liferay.meeting.webex.service.permission.WebExSitePermission" %><%@
page import="com.liferay.meeting.webex.util.ActionKeys" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TimeZoneUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
PortletURL currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);

String currentURL = currentURLObj.toString();

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(themeDisplay.getLocale());
Format dateFormatTime = FastDateFormatFactoryUtil.getTime(themeDisplay.getLocale());
%>