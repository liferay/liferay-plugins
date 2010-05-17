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

<%@ include file="init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(url) %>">
		<%
		StringBuffer params = new StringBuffer();

		if (autoplay) {
			params.append("&amp;autoplay=1");
		}

		if (showThickerBorder) {
			params.append("&amp;border=1");
		}

		params.append("&amp;cc_load_policy=" + closedCaptioning);

		if (Validator.isNotNull(borderColor)) {
			params.append("&amp;color1=" + borderColorHex);
		}

		if (Validator.isNotNull(playerColor)) {
			params.append("&amp;color2=" + playerColorHex);
		}

		if (!enableKeyboardControls) {
			params.append("&amp;disablekb=1");
		}

		if (enableEnhancedGenieMenu) {
			params.append("&amp;egm=1");
		}

		if (enableFullscreen) {
			params.append("&amp;fs=1");
		}

		if (hd) {
			params.append("&amp;hd=1");
		}

		params.append("&amp;iv_load_policy=" + annotations);

		if (loop) {
			params.append("&amp;loop=1");
		}

		if (enableRelatedVideos) {
			params.append("&amp;rel=1");
		}

		if (!showInfo) {
			params.append("&amp;showinfo=0");
		}

		if (!enableSearch) {
			params.append("&amp;showsearch=0");
		}

		if (Validator.isNotNull(startTime)) {
			params.append("&amp;start=" + startTime);
		}
		%>

		<%--
		<liferay-ui:flash allowScriptAccess="true" height="<%= height %>" movie="<%= swfURL + id + params.toString() %>" width="<%= width %>" wmode="opaque">
			<a href="<%= watchURL + id %>" rel="external" title="watch-this-video-at-youtube"><img alt="youtube-video" height="<%= height %>" src="<%= imageURL %>" width="<%= width %>" /></a>
		</liferay-ui:flash>
		--%>

		<object data="<%= swfURL + id + params.toString() %>" height="<%= height %>" type="application/x-shockwave-flash" width="<%= width %>">
			<param name="movie" value="<%= swfURL + id + params.toString() %>" />
			<param name="allowFullScreen" value="true" />
			<param name="allowScriptAccess" value="true" />
			<param name="wmode" value="opaque" />

			<a href="<%= watchURL + id %>" rel="external" title="<liferay-ui:message key="watch-this-video-at-youtube" />"><img alt="<liferay-ui:message key="youtube-video" />" height="<%= height %>" src="<%= imageURL %>" width="<%= width %>" /></a>
		</object>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>