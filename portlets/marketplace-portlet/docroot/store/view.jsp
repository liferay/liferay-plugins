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

<%
String remoteMVCPath = "/marketplace/view.jsp";

String portletId = portletDisplay.getId();

if (portletId.equals(PortletKeys.PURCHASED)) {
	remoteMVCPath = "/marketplace_server/view_purchased.jsp";
}
%>

<liferay-portlet:renderURL var="viewURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="remoteMVCPath" value="<%= remoteMVCPath %>" />
</liferay-portlet:renderURL>

<iframe frameborder="0" id="<portlet:namespace />frame" name="<portlet:namespace />frame" scrolling="no" src="<%= viewURL %>"></iframe>

<c:if test="<%= GetterUtil.getBoolean(request.getAttribute(WebKeys.OAUTH_AUTHORIZED)) %>">
	<div class="sign-out">
		<liferay-portlet:actionURL name="deauthorize" var="deauthorizeURL" />

		<aui:button onClick="<%= deauthorizeURL %>" value="sign-out" />
	</div>
</c:if>

<aui:script use="liferay-marketplace-messenger">
	var frame = A.one('#<portlet:namespace />frame');

	Liferay.MarketplaceMessenger.init(
		{
			targetFrame: frame
		}
	);

	Liferay.MarketplaceMessenger.receiveMessage(
		function(event) {
			var response = event.responseData;

			if (!response.cmd) {
				return;
			}

			var data = response.data;

			if ((response.cmd == 'resize') || (response.cmd == 'init')) {
				if (data.height) {
					frame.height(data.height + 50);
				}

				if (data.width) {
					frame.width(data.width);
				}
			}

			if ((response.cmd == 'scrollTo') || (response.cmd == 'init')) {
				var scrollX = data.scrollX || 0;
				var scrollY = data.scrollY || 0;

				window.scrollTo(scrollX, scrollY);
			}
		},
		A.Lang.emptyFnTrue
	);
</aui:script>