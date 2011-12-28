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
	<c:when test="<%= Validator.isNotNull(url) %>">

		<%
		StringBundler sb = new StringBundler();

		if (autoplay) {
			sb.append("&amp;autoplay=1");
		}

		if (showThickerBorder) {
			sb.append("&amp;border=1");
		}

		sb.append("&amp;cc_load_policy=" + closedCaptioning);

		if (Validator.isNotNull(borderColor)) {
			sb.append("&amp;color1=" + borderColorHex);
		}

		if (Validator.isNotNull(playerColor)) {
			sb.append("&amp;color2=" + playerColorHex);
		}

		if (!enableKeyboardControls) {
			sb.append("&amp;disablekb=1");
		}

		if (enableEnhancedGenieMenu) {
			sb.append("&amp;egm=1");
		}

		if (enableFullscreen) {
			sb.append("&amp;fs=1");
		}

		if (hd) {
			sb.append("&amp;hd=1");
		}

		sb.append("&amp;iv_load_policy=" + annotations);

		if (loop) {
			sb.append("&amp;loop=1");
		}

		if (enableRelatedVideos) {
			sb.append("&amp;rel=1");
		}

		if (!showInfo) {
			sb.append("&amp;showinfo=0");
		}

		if (!enableSearch) {
			sb.append("&amp;showsearch=0");
		}

		if (Validator.isNotNull(startTime)) {
			sb.append("&amp;start=" + startTime);
		}
		%>

		<liferay-ui:flash
			allowFullScreen="true"
			allowScriptAccess="true"
			height="<%= height %>"
			movie="<%= _SWF_URL + id + sb.toString() %>"
			width="<%= width %>"
			wmode="opaque"
		>
			<c:if test="<%= showThumbnail %>">
				<aui:a href="<%= _WATCH_URL + id %>" rel="external" title='<%= LanguageUtil.get(pageContext, "watch-this-video-at-youtube") %>'>
					<img alt="<liferay-ui:message key="youtube-video" />" height="<%= height %>" src="<%= imageURL %>" width="<%= width %>" />
				</aui:a>
			</c:if>
		</liferay-ui:flash>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>