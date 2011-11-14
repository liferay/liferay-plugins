<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<div class="loading-animation">
	<iframe class="aui-helper-hidden-accessible" id="<portlet:namespace />frame" scrolling="no" src="<%= iFrameURL %>"></iframe>
</div>

<div class="aui-helper-hidden time-out-message portlet-msg-error">
	<liferay-ui:message key="could-not-connect-to-the-liferay-marketplace" />
</div>

<aui:script use="aui-base,aui-io,aui-messaging">
	var frame = A.one('#<portlet:namespace />frame');

	var timeout = setTimeout(
		function() {
			frame.ancestor().removeClass('loading-animation');
			A.one('.time-out-message').show();
		},
		120000
	);

	A.receiveMessage(
		function(event) {
			var response = event.responseData;

			if (response.height) {
				clearTimeout(timeout);

				frame.removeClass('aui-helper-hidden-accessible');
				frame.ancestor().removeClass('loading-animation');

				frame.height(response.height + 50);
			}

			if (response.panel) {
				var url = null;

				if (response.panel === "control-panel") {
					url = '<%= themeDisplay.getURLControlPanel() %>';
				}
				else {
					url = '<liferay-portlet:renderURL doAsGroupId="<%= themeDisplay.getScopeGroupId() %>" portletName="<%= portletId.equals(PortletKeys.STORE) ? PortletKeys.MY_MARKETPLACE : PortletKeys.STORE %>" windowState="<%= WindowState.MAXIMIZED.toString() %>" />';

					if (response.appId) {
						url = Liferay.Util.addParams('appId=' + response.appId, url);
					}
				}

				window.location = url;
			}

			if (response.cmd) {
				A.io.request(
					'<portlet:actionURL />',
					{
						data: response,
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								A.postMessage(response, '<%= iFrameURL %>', frame);
							}
						}
					}
				);
			}
		},
		A.Lang.emptyFnTrue
	);

	frame.on(
		'load',
		function() {
			A.postMessage(
				{
					message: 'success',
					clientURL: '<%= themeDisplay.getURLPortal() %>'
				},
				'<%= iFrameURL %>',
				frame
			);
		}
	);
</aui:script>