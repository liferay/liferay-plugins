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

<c:choose>
	<c:when test="<%= Validator.isNotNull(link) %>">

		<%
		String iframeURL = "http://nvmodules.netvibes.com/widget/frame?uwaUrl=" + HttpUtil.encodeURL(link) + "&id=" + HttpUtil.encodeURL(PortalUtil.getPortletId(renderRequest)) + "&ifproxyUrl=" + HttpUtil.encodeURL(request.getContextPath() + "/proxy.jsp");
		%>

		<iframe alt="<%= alt %>" height="<%= windowState.equals(WindowState.MAXIMIZED) ? heightMaximized : heightNormal %>" id="<portlet:namespace />iframe" name="<portlet:namespace />iframe" src="<%= iframeURL %>" width="<%= width %>"></iframe>

		<liferay-util:html-bottom>
			<script src="http://www.netvibes.com/js/UWA/Utils/IFrameMessaging.js" type="text/javascript"></script>
		</liferay-util:html-bottom>

		<aui:script position="inline">
			UWA.MessageHandler = new UWA.iFrameMessaging;

			UWA.MessageHandler.init(
				{
					'trustedOrigin': 'nvmodules.netvibes.com'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="portlet-msg-info">
			<liferay-ui:message key="please-configure-this-portlet-to-make-it-visible-to-all-users" />
		</div>
	</c:otherwise>
</c:choose>