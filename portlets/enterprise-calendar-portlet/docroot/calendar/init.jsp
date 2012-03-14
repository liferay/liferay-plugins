<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.calendar.model.Calendar" %><%@
page import="com.liferay.calendar.model.CalendarResource" %><%@
page import="com.liferay.calendar.search.CalendarResourceDisplayTerms" %><%@
page import="com.liferay.calendar.search.CalendarResourceSearch" %><%@
page import="com.liferay.calendar.search.CalendarResourceSearchTerms" %><%@
page import="com.liferay.calendar.service.CalendarLocalServiceUtil" %><%@
page import="com.liferay.calendar.service.CalendarResourceServiceUtil" %><%@
page import="com.liferay.calendar.service.permission.CalendarResourcePermission" %><%@
page import="com.liferay.calendar.service.permission.EnterpriseCalendarPermission" %><%@
page import="com.liferay.calendar.util.ActionKeys" %><%@
page import="com.liferay.calendar.util.PortletPropsValues" %><%@
page import="com.liferay.calendar.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portlet.PortletURLUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String portletResource = ParamUtil.getString(request, "portletResource");

PortletURL currentURLObj = null;

if (renderRequest != null) {
	currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);
}
else if (resourceRequest != null) {
	currentURLObj = PortletURLUtil.getCurrent(resourceRequest, resourceResponse);
}

String currentURL = currentURLObj.toString();
%>