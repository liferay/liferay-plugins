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

<iframe id="<portlet:namespace />frame" scrolling="no" src="<%= iFrameURL %>"></iframe>

<aui:script use="aui-base,aui-io,aui-messaging">
	var frame = A.one('#<portlet:namespace />frame');

	var processResponse = function(event) {
		var response = event.responseData;

		var cmd = response.cmd;

		if (cmd) {
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

		var height = response.height;

		if (height) {
			frame.height(height + 50);
		}
	}

	A.receiveMessage(processResponse, '<%= MarketplaceConstants.MARKETPLACE_DOMAIN %>');
</aui:script>