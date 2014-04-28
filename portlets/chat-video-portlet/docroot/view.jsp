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

	<liferay-util:html-bottom>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/js/main.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
	</liferay-util:html-bottom>

	<div class="portlet-chat-video" id="chatVideo">
		<audio preload loop id="chatVideoInRingtone" src="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/audio/chat-video-in-ringtone.ogg", portlet.getTimestamp()) %>"></audio>
		<audio preload loop id="chatVideoOutRingtone" src="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/audio/chat-video-out-ringtone.ogg", portlet.getTimestamp()) %>"></audio>
		<div class="hide" id="chatVideoOverlay"></div>
		<div class="unmuted hide" id="chatVideoMuteCtrl"></div>
		<input id="chatVideoPortletId" type="hidden" value="<%= portletDisplay.getId() %>" />
		<input id="chatVideoPortletPollerNotificationsTimeout" type="hidden" value="<%= PropsUtil.get(PropsKeys.POLLER_NOTIFICATIONS_TIMEOUT) %>" />
		<input id="chatVideoPortletPollerRequestTimeout" type="hidden" value="<%= PropsUtil.get(PropsKeys.POLLER_REQUEST_TIMEOUT) %>" />
	</div>
</c:if>