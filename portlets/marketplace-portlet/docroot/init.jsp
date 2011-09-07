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

<%@ page import="com.liferay.marketplace.util.MarketplaceConstants" %>
<%@ page import="com.liferay.marketplace.util.PortletKeys" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String iFrameURL = MarketplaceConstants.MARKETPLACE_URL_HOME;

if (portletName.equals(PortletKeys.MY_MARKETPLACE)) {
	iFrameURL = MarketplaceConstants.MARKETPLACE_URL_MANAGE_APPS;
}
%>