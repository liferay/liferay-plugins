<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ page import="com.liferay.ams.model.Asset" %><%@
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
page import="com.liferay.util.bridges.alloy.AlloyController" %><%@
page import="com.liferay.util.bridges.alloy.AlloySearchResult" %><%@
page import="com.liferay.util.bridges.alloy.BaseAlloyIndexer" %>

<%@ page import="java.util.Locale" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@ include file="/WEB-INF/jsp/util/portlet_keys.jspf" %>