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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.knowledgebase.DuplicateKBArticleUrlTitleException" %><%@
page import="com.liferay.knowledgebase.DuplicateKBFolderNameException" %><%@
page import="com.liferay.knowledgebase.InvalidKBArticleUrlTitleException" %><%@
page import="com.liferay.knowledgebase.InvalidKBFolderNameException" %><%@
page import="com.liferay.knowledgebase.KBArticleContentException" %><%@
page import="com.liferay.knowledgebase.KBArticleImportException" %><%@
page import="com.liferay.knowledgebase.KBArticlePriorityException" %><%@
page import="com.liferay.knowledgebase.KBArticleSourceURLException" %><%@
page import="com.liferay.knowledgebase.KBArticleTitleException" %><%@
page import="com.liferay.knowledgebase.KBCommentContentException" %><%@
page import="com.liferay.knowledgebase.KBTemplateContentException" %><%@
page import="com.liferay.knowledgebase.KBTemplateTitleException" %><%@
page import="com.liferay.knowledgebase.NoSuchArticleException" %><%@
page import="com.liferay.knowledgebase.NoSuchCommentException" %><%@
page import="com.liferay.knowledgebase.NoSuchTemplateException" %><%@
page import="com.liferay.knowledgebase.model.KBArticle" %><%@
page import="com.liferay.knowledgebase.model.KBArticleConstants" %><%@
page import="com.liferay.knowledgebase.model.KBArticleSearchDisplay" %><%@
page import="com.liferay.knowledgebase.model.KBComment" %><%@
page import="com.liferay.knowledgebase.model.KBFolder" %><%@
page import="com.liferay.knowledgebase.model.KBFolderConstants" %><%@
page import="com.liferay.knowledgebase.model.KBTemplate" %><%@
page import="com.liferay.knowledgebase.model.KBTemplateSearchDisplay" %><%@
page import="com.liferay.knowledgebase.service.KBArticleLocalServiceUtil" %><%@
page import="com.liferay.knowledgebase.service.KBArticleServiceUtil" %><%@
page import="com.liferay.knowledgebase.service.KBCommentLocalServiceUtil" %><%@
page import="com.liferay.knowledgebase.service.KBFolderLocalServiceUtil" %><%@
page import="com.liferay.knowledgebase.service.KBFolderServiceUtil" %><%@
page import="com.liferay.knowledgebase.service.KBTemplateServiceUtil" %><%@
page import="com.liferay.knowledgebase.service.permission.AdminPermission" %><%@
page import="com.liferay.knowledgebase.service.permission.DisplayPermission" %><%@
page import="com.liferay.knowledgebase.service.permission.KBArticlePermission" %><%@
page import="com.liferay.knowledgebase.service.permission.KBCommentPermission" %><%@
page import="com.liferay.knowledgebase.service.permission.KBFolderPermission" %><%@
page import="com.liferay.knowledgebase.service.permission.KBTemplatePermission" %><%@
page import="com.liferay.knowledgebase.service.permission.SuggestionPermission" %><%@
page import="com.liferay.knowledgebase.util.ActionKeys" %><%@
page import="com.liferay.knowledgebase.util.KBArticleURLHelper" %><%@
page import="com.liferay.knowledgebase.util.KBNavigationDisplayContext" %><%@
page import="com.liferay.knowledgebase.util.KnowledgeBaseUtil" %><%@
page import="com.liferay.knowledgebase.util.PortletKeys" %><%@
page import="com.liferay.knowledgebase.util.PortletPropsValues" %><%@
page import="com.liferay.knowledgebase.util.WebKeys" %><%@
page import="com.liferay.knowledgebase.util.comparator.KBArticlePriorityComparator" %><%@
page import="com.liferay.portal.NoSuchSubscriptionException" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.RowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntry" %><%@
page import="com.liferay.portal.kernel.search.Document" %><%@
page import="com.liferay.portal.kernel.search.Field" %><%@
page import="com.liferay.portal.kernel.search.Hits" %><%@
page import="com.liferay.portal.kernel.search.Indexer" %><%@
page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.search.SearchContext" %><%@
page import="com.liferay.portal.kernel.search.SearchContextFactory" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatConstants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.OrderByComparator" %><%@
page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.Time" %><%@
page import="com.liferay.portal.kernel.util.Tuple" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.model.Ticket" %><%@
page import="com.liferay.portal.model.TicketConstants" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.service.ClassNameLocalServiceUtil" %><%@
page import="com.liferay.portal.service.ServiceContext" %><%@
page import="com.liferay.portal.service.SubscriptionLocalServiceUtil" %><%@
page import="com.liferay.portal.service.TicketLocalServiceUtil" %><%@
page import="com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil" %><%@
page import="com.liferay.portal.service.permission.GroupPermissionUtil" %><%@
page import="com.liferay.portal.service.permission.PortletPermissionUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portlet.PortalPreferences" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %><%@
page import="com.liferay.portlet.admin.util.PortalSearchApplicationType" %><%@
page import="com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil" %><%@
page import="com.liferay.portlet.asset.model.AssetCategory" %><%@
page import="com.liferay.portlet.asset.model.AssetEntry" %><%@
page import="com.liferay.portlet.asset.model.AssetRenderer" %><%@
page import="com.liferay.portlet.asset.model.AssetRendererFactory" %><%@
page import="com.liferay.portlet.asset.model.AssetVocabulary" %><%@
page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.persistence.AssetEntryQuery" %><%@
page import="com.liferay.portlet.blogs.model.BlogsEntry" %><%@
page import="com.liferay.portlet.documentlibrary.DuplicateFileException" %><%@
page import="com.liferay.portlet.documentlibrary.FileNameException" %><%@
page import="com.liferay.portlet.documentlibrary.FileSizeException" %><%@
page import="com.liferay.portlet.documentlibrary.NoSuchFileException" %><%@
page import="com.liferay.portlet.messageboards.model.MBMessage" %><%@
page import="com.liferay.portlet.portletconfiguration.util.PortletConfigurationApplicationType" %><%@
page import="com.liferay.taglib.search.DateSearchEntry" %><%@
page import="com.liferay.taglib.search.ResultRow" %><%@
page import="com.liferay.util.RSSUtil" %><%@
page import="com.liferay.wiki.model.WikiPage" %>

<%@ page import="java.math.BigDecimal" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.TreeMap" %>

<%@ page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

String redirect = ParamUtil.getString(request, "redirect", currentURL);

String rootPortletId = portletDisplay.getRootPortletId();

String templatePath = portletConfig.getInitParameter("template-path");

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(FastDateFormatConstants.LONG, locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.LONG, FastDateFormatConstants.SHORT, locale, timeZone);
Format dateFormatTime = FastDateFormatFactoryUtil.getTime(locale, timeZone);
%>