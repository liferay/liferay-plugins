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

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.chat.NoSuchEntryException" %><%@
page import="com.liferay.chat.model.Entry" %><%@
page import="com.liferay.chat.model.EntryClp" %><%@
page import="com.liferay.chat.service.EntryLocalService" %><%@
page import="com.liferay.chat.service.EntryLocalServiceUtil" %><%@
page import="com.liferay.chat.service.StatusLocalServiceUtil" %><%@
page import="com.liferay.journal.util.JournalContentUtil" %><%@
page import="com.liferay.portal.kernel.concurrent.ThreadPoolExecutor" %><%@
page import="com.liferay.portal.kernel.dao.db.DB" %><%@
page import="com.liferay.portal.kernel.dao.db.DBFactoryUtil" %><%@
page import="com.liferay.portal.kernel.dao.jdbc.DataAccess" %><%@
page import="com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.PortalCustomSQL" %><%@
page import="com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.executor.PortalExecutorManagerUtil" %><%@
page import="com.liferay.portal.kernel.format.PhoneNumberFormatUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.messaging.Message" %><%@
page import="com.liferay.portal.kernel.messaging.MessageBusUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" %><%@
page import="com.liferay.portal.kernel.search.SearchEngineUtil" %><%@
page import="com.liferay.portal.kernel.security.pacl.permission.PortalFilePermission" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.OSDetector" %><%@
page import="com.liferay.portal.kernel.util.PortalClassLoaderUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.ServerDetector" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.model.Group" %><%@
page import="com.liferay.portal.model.GroupWrapper" %><%@
page import="com.liferay.portal.model.Role" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.model.UserWrapper" %><%@
page import="com.liferay.portal.service.CompanyLocalServiceUtil" %><%@
page import="com.liferay.portal.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.service.ServiceContext" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.theme.ThemeDisplay" %><%@
page import="com.liferay.portal.util.Portal" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portlet.blogs.service.BlogsEntryLocalService" %><%@
page import="com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %><%@
page import="com.liferay.testpacl.hook.action.FailureStrutsAction" %><%@
page import="com.liferay.testpacl.hook.action.SuccessStrutsAction" %><%@
page import="com.liferay.testpacl.hook.indexer.OrganizationIndexerPostProcessor" %><%@
page import="com.liferay.testpacl.hook.indexer.UserIndexerPostProcessor" %><%@
page import="com.liferay.testpacl.model.Foo" %><%@
page import="com.liferay.testpacl.model.impl.FooImpl" %><%@
page import="com.liferay.testpacl.service.FooLocalService" %><%@
page import="com.liferay.testpacl.service.FooLocalServiceUtil" %><%@
page import="com.liferay.testpacl.util.TestPACLUtil" %><%@
page import="com.liferay.util.PwdGenerator" %>

<%@ page import="java.io.File" %><%@
page import="java.io.IOException" %><%@
page import="java.io.Writer" %>

<%@ page import="java.lang.reflect.Field" %>

<%@ page import="java.net.InetSocketAddress" %><%@
page import="java.net.ServerSocket" %><%@
page import="java.net.Socket" %><%@
page import="java.net.URL" %><%@
page import="java.net.URLClassLoader" %>

<%@ page import="java.sql.Connection" %><%@
page import="java.sql.PreparedStatement" %><%@
page import="java.sql.SQLException" %><%@
page import="java.sql.Statement" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.crypto.Cipher" %><%@
page import="javax.crypto.KeyGenerator" %><%@
page import="javax.crypto.Mac" %><%@
page import="javax.crypto.SecretKey" %><%@
page import="javax.crypto.spec.SecretKeySpec" %>

<%@ page import="javax.naming.Context" %><%@
page import="javax.naming.InitialContext" %>

<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />