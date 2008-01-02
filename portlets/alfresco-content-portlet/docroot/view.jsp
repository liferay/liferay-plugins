<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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

<div class="portlet-alfresco-content">
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
			<liferay-ui:icon image="edit" message="edit-content" url='<%= "javascript: window.open(\'" + AlfrescoContentUtil.getEndpointAddress() + "/alfresco/integration/ice?nodeid=workspace://SpacesStore/" + uuid + "&p_p_id=" + renderResponse.getNamespace() + "&" + ssoSimulateParam + "\'); void(\'\');" %>' />
		</c:if>
	</c:if>
</div>

<%!
private static Log _log = LogFactoryUtil.getLog("alfresco_content_portlet.view.jsp");
%>