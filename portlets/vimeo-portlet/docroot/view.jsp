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
			sb.append("autoplay=1&amp;");
		}

		sb.append("clip_id=" + id);
		sb.append("&amp;color=" + playerColorHex);
		sb.append("&amp;fullscreen=" + enableFullscreenBinary);
		sb.append("&amp;server=vimeo.com");
		sb.append("&amp;show_byline=" + showBylineBinary);
		sb.append("&amp;show_portrait=" + showPortraitBinary);
		sb.append("&amp;show_title=" + showTitleBinary);
		%>

		<liferay-ui:flash
			allowFullScreen="true"
			allowScriptAccess="true"
			height="<%= height %>"
			movie='<%= _SWF_URL + "?" + sb.toString() %>'
			width="<%= width %>"
			wmode="opaque"
		/>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>