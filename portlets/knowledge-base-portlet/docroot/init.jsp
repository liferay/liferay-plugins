<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ page import="com.liferay.documentlibrary.DuplicateFileException" %>
<%@ page import="com.liferay.documentlibrary.FileSizeException" %>
<%@ page import="com.liferay.documentlibrary.NoSuchFileException" %>
<%@ page import="com.liferay.documentlibrary.service.DLLocalServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.ArticleContentException" %>
<%@ page import="com.liferay.knowledgebase.ArticlePriorityException" %>
<%@ page import="com.liferay.knowledgebase.ArticleTitleException" %>
<%@ page import="com.liferay.knowledgebase.CommentContentException" %>
<%@ page import="com.liferay.knowledgebase.NoSuchArticleException" %>
<%@ page import="com.liferay.knowledgebase.NoSuchCommentException" %>
<%@ page import="com.liferay.knowledgebase.NoSuchTemplateException" %>
<%@ page import="com.liferay.knowledgebase.TemplateContentException" %>
<%@ page import="com.liferay.knowledgebase.TemplateTitleException" %>
<%@ page import="com.liferay.knowledgebase.model.Article" %>
<%@ page import="com.liferay.knowledgebase.model.Comment" %>
<%@ page import="com.liferay.knowledgebase.model.Template" %>
<%@ page import="com.liferay.knowledgebase.model.ArticleConstants" %>
<%@ page import="com.liferay.knowledgebase.model.ArticleSearchDisplay" %>
<%@ page import="com.liferay.knowledgebase.model.TemplateSearchDisplay" %>
<%@ page import="com.liferay.knowledgebase.service.ArticleLocalServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.ArticleServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.CommentLocalServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.TemplateServiceUtil" %>
<%@ page import="com.liferay.knowledgebase.service.permission.AdminPermission" %>
<%@ page import="com.liferay.knowledgebase.service.permission.ArticlePermission" %>
<%@ page import="com.liferay.knowledgebase.service.permission.DisplayPermission" %>
<%@ page import="com.liferay.knowledgebase.service.permission.TemplatePermission" %>
<%@ page import="com.liferay.knowledgebase.util.ActionKeys" %>
<%@ page import="com.liferay.knowledgebase.util.KnowledgeBaseUtil" %>
<%@ page import="com.liferay.knowledgebase.util.PortletKeys" %>
<%@ page import="com.liferay.knowledgebase.util.WebKeys" %>
<%@ page import="com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator" %>
<%@ page import="com.liferay.portal.NoSuchSubscriptionException" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.search.Field" %>
<%@ page import="com.liferay.portal.kernel.search.Hits" %>
<%@ page import="com.liferay.portal.kernel.search.Indexer" %>
<%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContext" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ContentTypes" %>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.FileUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PropsKeys" %>
<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringBundler" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.TextFormatter" %>
<%@ page import="com.liferay.portal.kernel.util.Tuple" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ page import="com.liferay.portal.model.CompanyConstants" %>
<%@ page import="com.liferay.portal.model.Subscription" %>
<%@ page import="com.liferay.portal.security.auth.PrincipalException" %>
<%@ page import="com.liferay.portal.service.SubscriptionLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.permission.GroupPermissionUtil" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry" %>
<%@ page import="com.liferay.portlet.asset.model.AssetVocabulary" %>
<%@ page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.persistence.AssetEntryQuery" %>
<%@ page import="com.liferay.util.RSSUtil" %>

<%@ page import="java.math.BigDecimal" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

<%@ page import="org.apache.commons.lang.time.FastDateFormat" %>
<%@ page import="org.apache.commons.math.util.MathUtils" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

String jspPath = portletConfig.getInitParameter("jsp-path");

Format dateFormatDate = FastDateFormat.getDateInstance(FastDateFormat.LONG, timeZone, locale);
Format dateFormatDateTime = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, timeZone, locale);

Format dateFormatTime = FastDateFormatFactoryUtil.getTime(locale, timeZone);
%>