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
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String license = preferences.getValue("license", StringPool.BLANK);
String mapAddress = preferences.getValue("mapAddress", StringPool.BLANK);
boolean mapInputEnabled = GetterUtil.getBoolean(preferences.getValue("mapInputEnabled", StringPool.BLANK));
String directionsAddress = preferences.getValue("directionsAddress", StringPool.BLANK);
boolean directionsInputEnabled = GetterUtil.getBoolean(preferences.getValue("directionsInputEnabled", StringPool.BLANK));
int height = GetterUtil.getInteger(preferences.getValue("height", StringPool.BLANK), 300);

String sesMapAddress = (String)session.getAttribute(renderResponse.getNamespace() + "mapAddress");

if (sesMapAddress != null) {
	mapAddress = sesMapAddress;
}

String sesDirectionsAddress = (String)session.getAttribute(renderResponse.getNamespace() + "directionsAddress");

if (sesDirectionsAddress != null) {
	directionsAddress = sesDirectionsAddress;
}
%>