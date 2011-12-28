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

<%@ include file="/init.jsp" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

long selNodeId = PrefsParamUtil.getLong(preferences, request, "selNodeId");
String selTitle = PrefsParamUtil.getString(preferences, request, "selTitle");

WikiPage wikiPage = null;

if ((selNodeId > 0) && Validator.isNotNull(selTitle)) {
	try {
		wikiPage = WikiPageServiceUtil.getPage(selNodeId, selTitle);
	}
	catch (Exception e) {
	}
}
%>