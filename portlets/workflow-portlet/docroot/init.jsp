<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.DateFormats" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.util.JS" %>
<%@ page import="com.liferay.workflow.DefinitionXmlException" %>
<%@ page import="com.liferay.workflow.NoSuchDefinitionException" %>
<%@ page import="com.liferay.workflow.jbi.WorkflowXMLUtil" %>
<%@ page import="com.liferay.workflow.model.WorkflowDefinition" %>
<%@ page import="com.liferay.workflow.model.WorkflowInstance" %>
<%@ page import="com.liferay.workflow.model.WorkflowTask" %>
<%@ page import="com.liferay.workflow.model.WorkflowTaskFormElement" %>
<%@ page import="com.liferay.workflow.model.WorkflowToken" %>
<%@ page import="com.liferay.workflow.portlet.WorkflowPortlet" %>
<%@ page import="com.liferay.workflow.search.DefinitionDisplayTerms" %>
<%@ page import="com.liferay.workflow.search.DefinitionSearch" %>
<%@ page import="com.liferay.workflow.search.DefinitionSearchTerms" %>
<%@ page import="com.liferay.workflow.search.InstanceDisplayTerms" %>
<%@ page import="com.liferay.workflow.search.InstanceSearch" %>
<%@ page import="com.liferay.workflow.search.InstanceSearchTerms" %>
<%@ page import="com.liferay.workflow.search.TaskDisplayTerms" %>
<%@ page import="com.liferay.workflow.search.TaskSearch" %>
<%@ page import="com.liferay.workflow.search.TaskSearchTerms" %>
<%@ page import="com.liferay.workflow.service.WorkflowComponentServiceUtil" %>
<%@ page import="com.liferay.workflow.service.WorkflowDefinitionServiceUtil" %>
<%@ page import="com.liferay.workflow.service.permission.WorkflowDefinitionPermission" %>
<%@ page import="com.liferay.workflow.service.permission.WorkflowInstancePermission" %>
<%@ page import="com.liferay.workflow.service.permission.WorkflowTaskPermission" %>

<%@ page import="java.text.DateFormat" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page import="javax.portlet.ActionRequest" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

<%@ page import="javax.servlet.RequestDispatcher" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

PortletPreferences prefs = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String viewType = prefs.getValue("viewType", "administrator");
String definitionName = prefs.getValue("definitionName", StringPool.BLANK);

DateFormat dateFormatDateTime = DateFormats.getDateTime(locale, timeZone);
%>