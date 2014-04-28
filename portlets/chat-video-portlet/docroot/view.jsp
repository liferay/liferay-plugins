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

<%@ include file="/init.jsp" %>

<c:if test="<%= themeDisplay.isSignedIn() && !BrowserSnifferUtil.isIe(request) && !BrowserSnifferUtil.isMobile(request) %>">

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
	%>

	<liferay-util:html-top>
		<link href="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>

	<liferay-util:html-bottom>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/js/inject_chat_portlet.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/js/webrtc.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/js/webrtc_adapter.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
	</liferay-util:html-bottom>

	<div class="portlet-chat-video" id="chatVideo">
		<audio id="chatVideoInRingtone" loop="loop" preload="auto" src="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/audio/in_ringtone.ogg", portlet.getTimestamp()) %>"></audio>

		<audio id="chatVideoOutRingtone" loop="loop" preload="auto" src="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/audio/out_ringtone.ogg", portlet.getTimestamp()) %>"></audio>

		<div class="chat-video-overlay hide" id="chatVideoOverlay">
			<div class="call-time"></div>
		</div>

		<div class="hide unmuted" id="chatVideoMuteCtrl"></div>

		<input id="chatVideoPortletId" type="hidden" value="<%= portletDisplay.getId() %>" />

		<input id="chatVideoPortletPollerNotificationsTimeout" type="hidden" value="<%= PropsUtil.get(PropsKeys.POLLER_NOTIFICATIONS_TIMEOUT) %>" />

		<input id="chatVideoPortletPollerRequestTimeout" type="hidden" value="<%= PropsUtil.get(PropsKeys.POLLER_REQUEST_TIMEOUT) %>" />
	</div>
</c:if>