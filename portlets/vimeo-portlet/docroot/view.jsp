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

		params.append("clip_id=" + id);

		if (autoplay) {
			params.append("&amp;autoplay=1");
		}

		params.append("&amp;color=" + playerColorHex);

		params.append("&amp;fullscreen=" + enableFullscreenBinary);

		params.append("&amp;server=vimeo.com");

		params.append("&amp;show_byline=" + showBylineBinary);

		params.append("&amp;show_portrait=" + showPortraitBinary);

		params.append("&amp;show_title=" + showTitleBinary);
		%>	

		<%--
		<liferay-ui:flash allowScriptAccess="true" height="<%= height %>" movie="<%= swfURL + '?' + params.toString() %>" width="<%= width %>" wmode="opaque">
			<a href="<%= watchURL + id %>" rel="external" title="watch-this-video-at-vimeo"><img alt="vimeo-video" height="<%= height %>" src="<%= imageURL %>" width="<%= width %>" /></a>
		</liferay-ui:flash>
		--%>

		<object data="<%= swfURL + '?' + params.toString() %>" height="<%= height %>" type="application/x-shockwave-flash" width="<%= width %>">
			<param name="movie" value="<%= swfURL + '?' + params.toString() %>" />
			<param name="allowFullScreen" value="true" />
			<param name="allowScriptAccess" value="true" />
			<param name="wmode" value="opaque" />

			<a href="<%= watchURL + id %>" rel="external" title="<liferay-ui:message key="watch-this-video-at-vimeo" />"><liferay-ui:message key="vimeo-video" /></a>
		</object>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>