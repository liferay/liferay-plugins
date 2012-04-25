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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.marketplace.util.MarketplaceConstants" %><%@
page import="com.liferay.marketplace.util.PortletKeys" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.ServerDetector" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %>

<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
long appId = ParamUtil.getLong(request, "appId");

String portletId = portletDisplay.getId();

String iFrameURL = MarketplaceConstants.MARKETPLACE_URL_LOGOUT;

String referer = StringPool.BLANK;

if (portletId.equals(PortletKeys.MY_MARKETPLACE)) {
	referer = MarketplaceConstants.getPathPurchased();
}
else if (portletId.equals(PortletKeys.STORE) && (appId > 0)) {
	referer = MarketplaceConstants.getPathStore() + "/application/" + appId;
}
else {
	referer = MarketplaceConstants.getPathStore();
}
%>