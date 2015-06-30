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

<%@ page import="com.liferay.alloy.mvc.AlloyController" %><%@
page import="com.liferay.alloy.mvc.AlloyException" %><%@
page import="com.liferay.alloy.mvc.AlloySearchResult" %><%@
page import="com.liferay.alloy.mvc.BaseAlloyIndexer" %><%@
page import="com.liferay.alloy.mvc.jsonwebservice.JSONWebServiceMethod" %><%@
page import="com.liferay.ams.model.Asset" %><%@
page import="com.liferay.ams.model.Definition" %><%@
page import="com.liferay.ams.model.Type" %><%@
page import="com.liferay.ams.service.AssetLocalServiceUtil" %><%@
page import="com.liferay.ams.service.DefinitionLocalServiceUtil" %><%@
page import="com.liferay.ams.service.TypeLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.search.BooleanQuery" %><%@
page import="com.liferay.portal.kernel.search.Document" %><%@
page import="com.liferay.portal.kernel.search.Field" %><%@
page import="com.liferay.portal.kernel.search.Indexer" %><%@
page import="com.liferay.portal.kernel.search.SearchContext" %><%@
page import="com.liferay.portal.kernel.search.Summary" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.model.BaseModel" %>

<%@ page import="java.util.Locale" %><%@
page import="java.util.regex.Matcher" %><%@
page import="java.util.regex.Pattern" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %><%@
page import="javax.portlet.PortletURL" %>

<%@ include file="/WEB-INF/jsp/util/portlet_keys.jspf" %>