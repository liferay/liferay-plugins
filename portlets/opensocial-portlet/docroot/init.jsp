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
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.opensocial.DuplicateGadgetURLException" %>
<%@ page import="com.liferay.opensocial.GadgetPortletCategoryNamesException" %>
<%@ page import="com.liferay.opensocial.GadgetURLException" %>
<%@ page import="com.liferay.opensocial.NoSuchGadgetException" %>
<%@ page import="com.liferay.opensocial.NoSuchOAuthConsumerException" %>
<%@ page import="com.liferay.opensocial.model.Gadget" %>
<%@ page import="com.liferay.opensocial.model.OAuthConsumer" %>
<%@ page import="com.liferay.opensocial.model.OAuthConsumerConstants" %>
<%@ page import="com.liferay.opensocial.service.GadgetLocalServiceUtil" %>
<%@ page import="com.liferay.opensocial.service.OAuthConsumerLocalServiceUtil" %>
<%@ page import="com.liferay.opensocial.shindig.util.ShindigUtil" %>
<%@ page import="com.liferay.opensocial.util.PortletPropsValues" %>
<%@ page import="com.liferay.opensocial.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.repository.model.Folder" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.TreeNodeView" %>
<%@ page import="com.liferay.portal.kernel.util.TreeView" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>
<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.util.PortletLister" %>
<%@ page import="com.liferay.portal.util.PortletListerFactoryUtil" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoValueServiceUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@ page import="org.apache.shindig.gadgets.spec.GadgetSpec" %>
<%@ page import="org.apache.shindig.gadgets.spec.ModulePrefs" %>
<%@ page import="org.apache.shindig.gadgets.spec.OAuthService" %>
<%@ page import="org.apache.shindig.gadgets.spec.UserPref" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>