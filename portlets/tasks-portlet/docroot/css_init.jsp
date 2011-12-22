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

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.servlet.HttpHeaders" %><%@
page import="com.liferay.portal.kernel.util.ContentTypes" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.model.Theme" %><%@
page import="com.liferay.portal.service.ThemeLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %>

<%
long companyId = PortalUtil.getCompanyId(request);

String themeId = ParamUtil.getString(request, "themeId");

Theme theme = ThemeLocalServiceUtil.getTheme(companyId, themeId, false);

String themeContextPath = PortalUtil.getPathContext();

if (theme.isWARFile()) {
	themeContextPath = theme.getContextPath();
}

String cdnHost = PortalUtil.getCDNHost(request.isSecure());

String themeImagesPath = cdnHost + themeContextPath + theme.getImagesPath();

response.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.TEXT_CSS);
%>