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

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.messaging.DestinationNames" %><%@
page import="com.liferay.portal.kernel.messaging.Message" %><%@
page import="com.liferay.portal.kernel.messaging.MessageBusUtil" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.FileUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.UnicodeProperties" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.model.Group" %><%@
page import="com.liferay.portal.model.Layout" %><%@
page import="com.liferay.portal.model.LayoutTypePortletConstants" %><%@
page import="com.liferay.portal.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntry" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFolderConstants" %><%@
page import="com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.journal.model.JournalArticle" %><%@
page import="com.liferay.portlet.journal.model.JournalStructure" %><%@
page import="com.liferay.portlet.journal.model.JournalTemplate" %><%@
page import="com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil" %><%@
page import="com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil" %>

<%@ page import="java.util.Map" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />