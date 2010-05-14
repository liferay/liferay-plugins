<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
		<liferay-ui:message key="please-contact-the-administrator-to-setup-this-portlet" />
	</c:otherwise>
</c:choose>