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

<c:choose>
	<c:when test="<%= Validator.isNotNull(url) %>">

		<%
		StringBundler sb = new StringBundler();

		sb.append("?wmode=transparent");

		if (autoplay) {
			sb.append("&amp;autoplay=1");
		}

		if (closedCaptioning) {
			sb.append("&amp;cc_load_policy=1");
		}

		if (!enableKeyboardControls) {
			sb.append("&amp;disablekb=1");
		}

		if (annotations) {
			sb.append("&amp;iv_load_policy=1");
		}
		else {
			sb.append("&amp;iv_load_policy=3");
		}

		if (loop) {
			sb.append("&amp;loop=1&amp;playlist=");
			sb.append(id);
		}

		if (Validator.isNotNull(startTime)) {
			sb.append("&amp;start=");
			sb.append(startTime);
		}
		%>

		<c:if test="<%= showThumbnail %>">
			<aui:a href="<%= watchURL + id %>" rel="external" title='<%= HtmlUtil.escapeAttribute(LanguageUtil.get(request, "watch-this-video-at-youtube")) %>'>
				<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="youtube-video" />" height="<%= height %>" src="<%= imageURL %>" width="<%= width %>" />
			</aui:a>
		</c:if>

		<iframe allowfullscreen frameborder="0" height="<%= height %>" src="<%= embedURL + id + sb.toString() %>" width="<%= width %>" wmode="Opaque" /></iframe>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>