<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<c:choose>
	<c:when test="<%= Validator.isNotNull(curWidgetUrl) %>">

		<%
		renderResponse.setTitle(curWidgetTitle);

		String portletId = PortalUtil.getPortletId(renderRequest);

		StringBuilder sb = new StringBuilder();

		sb.append("http://nvmodules.netvibes.com/widget/frame/");
		sb.append("?uwaUrl=");
		sb.append(HttpUtil.encodeURL(curWidgetUrl));
		sb.append("&id=");
		sb.append(HttpUtil.encodeURL(portletId));
		sb.append("&ifproxyUrl=");
		sb.append(HttpUtil.encodeURL(request.getContextPath()));
		sb.append(HttpUtil.encodeURL("/proxy.html"));

		String iFrameURL = sb.toString();
		%>

		<script src="http://www.netvibes.com/js/UWA/Utils/IFrameMessaging.js" type="text/javascript" />
		<script type="text/javascript">//Do not remove this</script>

		<script type="text/javascript">
			msgHandler = function(message) {
				var id = message.id;
				switch (message.action) {
				case 'resizeHeight':
					var frame = document.getElementById('frame_' + id);
					if (frame) {
						frame.setAttribute('height', message.value);
					}
					break;
				default:
					break;
				}
			};

			UWA.MessageHandler = new UWA.iFrameMessaging;
			UWA.MessageHandler.init({
				'eventHandler': msgHandler,
				'trustedOrigin' : 'nvmodules.netvibes.com'
			});
		</script>

		<iframe id="frame_<%=portletId %>" frameborder="0" scrolling="no" src="<%= iFrameURL %>" width="100%"></iframe>

	</c:when>
	<c:otherwise>
		<%
		renderRequest.setAttribute("PORTLET_CONFIGURATOR_VISIBILITY", Boolean.TRUE);
		%>

		<div class="portlet-msg-info">
			<a href="<%= portletDisplay.getURLConfiguration() %>">
				<liferay-ui:message key="please-configure-this-portlet-to-make-it-visible-to-all-users" />
			</a>
		</div>
	</c:otherwise>
</c:choose>