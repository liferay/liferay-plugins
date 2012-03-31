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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.chat.NoSuchEntryException" %><%@
page import="com.liferay.chat.model.Entry" %><%@
page import="com.liferay.chat.model.Status" %><%@
page import="com.liferay.chat.service.EntryLocalServiceUtil" %><%@
page import="com.liferay.chat.service.StatusLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.DynamicQuery" %><%@
page import="com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" %><%@
page import="com.liferay.portal.kernel.util.FileUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.OSDetector" %><%@
page import="com.liferay.portal.model.Group" %><%@
page import="com.liferay.portal.model.Role" %><%@
page import="com.liferay.portal.service.CompanyLocalServiceUtil" %><%@
page import="com.liferay.portal.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.theme.ThemeDisplay" %><%@
page import="com.liferay.testpacl.model.Foo" %><%@
page import="com.liferay.testpacl.service.FooLocalServiceUtil" %><%@
page import="com.liferay.testpacl.util.TestPACLUtil" %>

<%@ page import="java.io.File" %><%@
page import="java.io.IOException" %><%@
page import="java.io.Writer" %>

<%@ page import="java.net.ServerSocket" %><%@
page import="java.net.Socket" %>

<%@ page import="java.util.Map" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />