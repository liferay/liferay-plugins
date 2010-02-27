<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<%
String path = ParamUtil.getString(request, "path");

String content = null;

try {
	content = AlfrescoContentCacheUtil.getContent(userId, password, uuid, path, maximizeLinks, renderResponse);
}
catch (Exception e) {
	Throwable cause = e.getCause();

	if (cause != null) {
		_log.error(cause.getMessage());
	}
	else {
		_log.error(e.getMessage());
	}
}

boolean preview = ParamUtil.getBoolean(request, "preview");
%>

<div class="
	<c:if test="<%= preview %>">
		 preview
	</c:if>"
>
	<c:choose>
		<c:when test="<%= Validator.isNotNull(content) %>">
			<%= content %>
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="please-contact-the-administrator-to-setup-this-portlet" />
		</c:otherwise>
	</c:choose>
</div>

<c:if test="<%= themeDisplay.isSignedIn() && !preview %>">
	<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid.longValue(), portletConfig.getPortletName(), ActionKeys.CONFIGURATION) %>">
		<liferay-ui:icon image="configuration" message="select-content" url="<%= portletDisplay.getURLConfiguration() %>" />
	</c:if>

	<%
	String ssoSimulateParam = StringPool.BLANK;

	if (GetterUtil.getBoolean(PortletProps.get("content.one.step.edit.sso.simulate"))) {
		ssoSimulateParam = "user=" + user.getLogin() + "&";
	}
	%>

	<c:if test="<%= showEditIcon && Validator.isNotNull(uuid) && AlfrescoContentUtil.hasPermission(user.getLogin(), PortalUtil.getUserPassword(renderRequest), uuid, org.alfresco.webservice.util.Constants.WRITE) %>">

		<%
		String taglibEditURL = "javascript:window.open(\'" + AlfrescoContentUtil.getEndpointAddress() + "/alfresco/integration/ice?nodeid=workspace://SpacesStore/" + uuid + "&p_p_id=" + renderResponse.getNamespace() + "&" + ssoSimulateParam + "\'); void(\'\');";
		%>

		<liferay-ui:icon image="edit" message="edit-content" url='<%= taglibEditURL %>' />
	</c:if>
</c:if>

<%!
private static Log _log = LogFactoryUtil.getLog("alfresco_content_portlet.view.jsp");
%>