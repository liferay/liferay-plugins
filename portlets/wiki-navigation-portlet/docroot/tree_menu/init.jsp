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

int depth = PrefsParamUtil.getInteger(preferences, request, "depth", WikiNavigationConstants.DEPTH_ALL);
long selNodeId = PrefsParamUtil.getLong(preferences, request, "selNodeId");

if (selNodeId <= 0) {
	String wikiName = PropsUtil.get("wiki.initial.node.name");

	try {
		WikiNode node = WikiNodeServiceUtil.getNode(themeDisplay.getScopeGroupId(), wikiName);

		selNodeId = node.getNodeId();
	}
	catch (Exception e) {
	}
}

themeDisplay.setThemeJsBarebone(false);
%>