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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.bbb.model.BBBMeeting" %><%@
page import="com.liferay.bbb.model.BBBMeetingConstants" %><%@
page import="com.liferay.bbb.model.BBBParticipant" %><%@
page import="com.liferay.bbb.model.BBBParticipantConstants" %><%@
page import="com.liferay.bbb.model.BBBServer" %><%@
page import="com.liferay.bbb.model.impl.BBBParticipantImpl" %><%@
page import="com.liferay.bbb.service.BBBMeetingLocalServiceUtil" %><%@
page import="com.liferay.bbb.service.BBBParticipantLocalServiceUtil" %><%@
page import="com.liferay.bbb.service.BBBServerLocalServiceUtil" %><%@
page import="com.liferay.bbb.service.permission.BBBMeetingPermission" %><%@
page import="com.liferay.bbb.service.permission.MeetingsPermission" %><%@
page import="com.liferay.bbb.util.ActionKeys" %><%@
page import="com.liferay.bbb.util.BBBAPIUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Collections" %><%@
page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
PortletURL portletURL = renderResponse.createRenderURL();

String currentURL = PortalUtil.getCurrentURL(request);
%>