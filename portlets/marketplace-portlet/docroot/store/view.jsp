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
PortletURL viewURL = renderResponse.createRenderURL();

String portletId = portletDisplay.getId();

if (portletId.equals(PortletKeys.STORE)) {
	long appEntryId = ParamUtil.getLong(request, "appEntryId");

	if (appEntryId <= 0) {
		viewURL.setParameter("remoteMVCPath", "/marketplace/view.jsp");
	}
	else {
		viewURL.setParameter("remoteMVCPath", "/marketplace/view_app_entry.jsp");
		viewURL.setParameter("appEntryId", String.valueOf(appEntryId));
	}
}
else {
	viewURL.setParameter("remoteMVCPath", "/marketplace_server/view_purchased.jsp");
}

viewURL.setWindowState(LiferayWindowState.EXCLUSIVE);
%>

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

			if ((response.cmd == 'resize') || (response.cmd == 'init')) {
				if (response.height) {
					frame.height(response.height + 50);
				}

				if (response.width) {
					frame.width(response.width);
				}
			}

			if ((response.cmd == 'scrollTo') || (response.cmd == 'init')) {
				var scrollX = response.scrollX || 0;
				var scrollY = response.scrollY || 0;

				window.scrollTo(scrollX, scrollY);
			}

			if (response.cmd == 'goto') {
				var url = '<%= themeDisplay.getURLControlPanel() %>';

				if (response.panel == 'purchased') {
					url = '<liferay-portlet:renderURL doAsGroupId="<%= themeDisplay.getScopeGroupId() %>" portletName="<%= PortletKeys.PURCHASED %>" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>" />';
				}
				else if (response.panel == 'store') {
					url = '<liferay-portlet:renderURL doAsGroupId="<%= themeDisplay.getScopeGroupId() %>" portletName="<%= PortletKeys.STORE %>" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>" />';

					if (response.appEntryId) {
						url = Liferay.Util.addParams('<%= PortalUtil.getPortletNamespace(PortletKeys.STORE) %>appEntryId=' + response.appEntryId, url);
					}
				}

				window.location = url;
			}
		},
		A.Lang.emptyFnTrue
	);
</aui:script>