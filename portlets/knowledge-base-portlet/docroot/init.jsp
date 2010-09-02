<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.documentlibrary.DuplicateFileException" %>
<%@ page import="com.liferay.documentlibrary.FileSizeException" %>
<%@ page import="com.liferay.documentlibrary.NoSuchFileException" %>
<%@ page import="com.liferay.documentlibrary.service.DLServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.ArticleContentException" %>
<%@ page import="com.liferay.knowledgebase.ArticleTitleException" %>
<%@ page import="com.liferay.knowledgebase.NoSuchArticleException" %>
<%@ page import="com.liferay.knowledgebase.NoSuchTemplateException" %>
<%@ page import="com.liferay.knowledgebase.TemplateContentException" %>
<%@ page import="com.liferay.knowledgebase.TemplateTitleException" %>
<%@ page import="com.liferay.knowledgebase.model.Article" %>
<%@ page import="com.liferay.knowledgebase.model.Template" %>
<%@ page import="com.liferay.knowledgebase.model.ArticleConstants" %>
<%@ page import="com.liferay.knowledgebase.service.ArticleLocalServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.ArticleServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.TemplateServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.permission.AdminPermission" %>
<%@ page import="com.liferay.knowledgebase.service.permission.ArticlePermission" %>
<%@ page import="com.liferay.knowledgebase.service.permission.TemplatePermission" %>
<%@ page import="com.liferay.knowledgebase.util.KnowledgeBaseUtil" %>
<%@ page import="com.liferay.knowledgebase.util.PortletKeys" %>
<%@ page import="com.liferay.knowledgebase.util.WebKeys" %>
<%@ page import="com.liferay.knowledgebase.util.comparator.ArticleCreateDateComparator" %>
<%@ page import="com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator" %>
<%@ page import="com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator" %>
<%@ page import="com.liferay.knowledgebase.util.comparator.ArticleTitleComparator" %>
<%@ page import="com.liferay.knowledgebase.util.comparator.ArticleVersionComparator" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.search.Field" %>
<%@ page import="com.liferay.portal.kernel.search.Hits" %>
<%@ page import="com.liferay.portal.kernel.search.Indexer" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContext" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.ArrayUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ContentTypes" %>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.FileUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PropsKeys" %>
<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.SetUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ page import="com.liferay.portal.model.CompanyConstants" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.security.auth.PrincipalException" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.SubscriptionLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.permission.GroupPermissionUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portlet.PortletConfigFactoryUtil" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry" %>
<%@ page import="com.liferay.portlet.messageboards.NoSuchMessageException" %>
<%@ page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil" %>
<%@ page import="com.liferay.util.RSSUtil" %>
<%@ page import="com.liferay.util.TextFormatter" %>
<%@ page import="com.liferay.util.portlet.PortletProps" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%@ page import="javax.portlet.PortletConfig" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

String rootPortletId = portletDisplay.getRootPortletId();

String jspPath = portletConfig.getInitParameter("jsp-path");

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	Portlet selPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletResource);

	PortletConfig selPortletConfig = PortletConfigFactoryUtil.create(selPortlet, application);

	rootPortletId = selPortlet.getPluginId();

	jspPath = selPortletConfig.getInitParameter("jsp-path");
}
%>